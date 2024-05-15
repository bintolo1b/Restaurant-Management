package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.jfree.data.category.DefaultCategoryDataset;

import DTO.HoaDon;


public class HoaDonDAL implements DALInterface<HoaDon> {
	private static HoaDonDAL instance;
	private Connection cnt = JDBC.getConnection();
	
    private HoaDonDAL(){
    }

    public static HoaDonDAL getInstance() {
        if (instance == null) {
            instance = new HoaDonDAL();
        }
        return instance;
    }
	
	@Override
	public void Insert(HoaDon obj) {
		try {	
			String updStr = "insert into hoa_don "
					+ "values (?,?,?,?,?,?,?)";
			PreparedStatement pst = cnt.prepareStatement(updStr) ;
			pst.setObject(1, obj.getMaHoaDon()); 
			pst.setObject(2, obj.getNgayTao());
			pst.setObject(3, obj.getTongTien());
			pst.setObject(4, obj.getMaNhanVien());
			pst.setObject(5, obj.getMaKhach());
			pst.setObject(6, obj.getMaBan());
			pst.setObject(7, obj.getTinhTrang());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Update(HoaDon obj) {
		try {	
			String query = "update hoa_don "
					+ "set tongTien = ?, maBan = ?, tinhTrang = ?, ngayTao = ? "
					+ "where maHoaDon = ?";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setObject(1, obj.getTongTien());
			pst.setObject(2, obj.getMaBan());
			pst.setObject(3, obj.getTinhTrang());
			pst.setObject(4, obj.getNgayTao());
			pst.setObject(5, obj.getMaHoaDon());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Delete(HoaDon obj) {

	}

	@Override
	public ArrayList<HoaDon> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HoaDon> selectByCondition(String condition, Object... params) {
		ArrayList<HoaDon> HoaDonArrayList = new ArrayList<HoaDon>();
	    try {	  
	        PreparedStatement pst = cnt.prepareStatement(condition);
	        
	        if (params!=null)
		        for (int i = 0; i < params.length; i++) {
		            pst.setObject(i + 1, params[i]);
		        }
	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
	            HoaDon newHoaDon = new HoaDon(res.getInt(1), res.getString(2), res.getFloat(3), res.getInt(4), res.getInt(5), res.getInt(6),res.getString(7));
	            HoaDonArrayList.add(newHoaDon);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return HoaDonArrayList;
	}

	public ArrayList<HoaDon> getAllHoaDonDaThanhToan() {
		String condition = "select* "
				+ "from qlnh.hoa_don hd "
				+ "where tinhTrang=?";
		ArrayList<HoaDon> hoaDonArrayList = HoaDonDAL.getInstance().selectByCondition(condition,"Đã thanh toán");
		return hoaDonArrayList;
	}
	
	public int getLatestID() {
		String Condition = "select* "
				+ "from qlnh.hoa_don hd  "
				+ "order by hd.maHoaDon desc "
				+ "limit 1";
    	ArrayList<HoaDon> hoaDonArrayList = HoaDonDAL.getInstance().selectByCondition(Condition);
    	if (hoaDonArrayList.size()==0)
    		return 0;
    	else return hoaDonArrayList.get(0).getMaHoaDon();
	}
	
	public HoaDon getLatestHoaDonOfTable(int tableID) {
		String condition = "select* "
				+ "from qlnh.hoa_don hd "
				+ "where hd.maBan = ? "
				+ "order by hd.maHoaDon desc "
				+ "limit 1";
		ArrayList<HoaDon> hoaDonArrayList =  HoaDonDAL.getInstance().selectByCondition(condition, tableID);
		if (hoaDonArrayList.size() == 1) {
			return hoaDonArrayList.get(0);
		}
		else
			return null;
	}
	
	public HoaDon getHoaDonByID(int maHD) {
		String condition = "select* "
				+ "from qlnh.hoa_don hd "
				+ "where hd.maHoaDon=?";
		ArrayList<HoaDon> hoaDonArrayList = HoaDonDAL.getInstance().selectByCondition(condition, maHD);
		if (hoaDonArrayList.size()==0)
			return null;
		else
			return hoaDonArrayList.get(0);
	}

	
	public void updateRevenue(DefaultCategoryDataset dataset) {
	    String query = "SELECT DATE(hd.ngayTao) AS ngay,"
	    		+ " SUM(hd.tongTien) AS totalRevenue"
	    		+ " FROM hoa_don hd "
	    		+ " where hd.tinhTrang='Đã thanh toán' "
	    		+ " GROUP BY DATE(hd.ngayTao)"
	    		+ " ORDER BY DATE(hd.ngayTao);";

	    try {
	        PreparedStatement stmt = cnt.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();

	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	        while (rs.next()) {
	            Date date = rs.getDate("ngay"); 
	            double revenue = rs.getDouble("totalRevenue");
	            dataset.addValue(revenue, "Doanh thu", dateFormat.format(date));
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Lỗi update biểu đồ: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void updateChartFollowingDates(DefaultCategoryDataset dataset, Date startDate, Date endDate) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    if (startDate == null || endDate == null) {
	        JOptionPane.showMessageDialog(null, "Ngày không được chỉ định!", "Input Error", JOptionPane.ERROR_MESSAGE);
	        return;  
	    }

	    String query = "SELECT DATE(hd.ngayTao) AS ngay," +
	                   " SUM(hd.tongTien) AS totalRevenue" +
	                   " FROM hoa_don hd" +
	                   " WHERE DATE(hd.ngayTao) BETWEEN ? AND ? and hd.tinhTrang='Đã thanh toán' " +
	                   " GROUP BY DATE(hd.ngayTao)" +
	                   " ORDER BY DATE(hd.ngayTao)";
	    try {
	         PreparedStatement ptm = cnt.prepareStatement(query);
	        ptm.setString(1, dateFormat.format(startDate));
	        ptm.setString(2, dateFormat.format(endDate));
	        ResultSet rs = ptm.executeQuery();
	        dataset.clear();
	        while (rs.next()) {
	            Date date = rs.getDate("ngay");
	            double revenue = rs.getDouble("totalRevenue");
	            dataset.addValue(revenue, "Doanh thu", dateFormat.format(date));
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Lỗi cập nhật biểu đồ " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
