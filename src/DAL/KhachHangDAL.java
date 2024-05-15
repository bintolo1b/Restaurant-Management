package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.KhachHang;


public class KhachHangDAL implements DALInterface<KhachHang> {
	private static KhachHangDAL instance;
	private Connection cnt = JDBC.getConnection();
	
    private KhachHangDAL(){
    }

    public static KhachHangDAL getInstance() {
        if (instance == null) {
            instance = new KhachHangDAL();
        }
        return instance;
    }
	
	@Override
	public void Insert(KhachHang obj) {
		try {
			String updStr = "insert into khach_hang "
					+ "value (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = cnt.prepareStatement(updStr) ;
			pst.setObject(1, obj.getMaKhach());
			pst.setObject(2, obj.getSoDienThoai());
			pst.setObject(3, obj.getHo());
			pst.setObject(4, obj.getTenDem());
			pst.setObject(5, obj.getTen());
			pst.setObject(6, obj.getDiaChi());
			pst.setObject(7, obj.getGioiTinh());
			pst.setObject(8, obj.getNgaySinh());
			pst.setObject(9, obj.getDiemTichLuy());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Update(KhachHang obj) {
		try {
			String query = "update khach_hang "
					+ "set soDienThoai = ?, ho = ?, tenDem = ?, ten = ?, diaChi = ?, gioiTinh = ?, ngaySinh = ?, diemTichLuy = ? "
					+ "where maKhach=?";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setObject(1, obj.getSoDienThoai());
			pst.setObject(2, obj.getHo());
			pst.setObject(3, obj.getTenDem());
			pst.setObject(4, obj.getTen());
			pst.setObject(5, obj.getDiaChi());
			pst.setObject(6, obj.getGioiTinh());
			pst.setObject(7, obj.getNgaySinh());
			pst.setObject(8, obj.getDiemTichLuy());
			pst.setObject(9, obj.getMaKhach());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void Delete(KhachHang obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<KhachHang> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<KhachHang> selectByCondition(String condition, Object... params) {
		ArrayList<KhachHang> KhachHangArrayList = new ArrayList<KhachHang>();
	    try {	    
	        PreparedStatement pst = cnt.prepareStatement(condition);
	        
	        if (params!=null)
		        for (int i = 0; i < params.length; i++) {
		            pst.setObject(i + 1, params[i]);
		        }
	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
	            KhachHang newKhachHang = new KhachHang(res.getInt(1), res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getInt(7),res.getString(8),res.getFloat(9));
	            KhachHangArrayList.add(newKhachHang);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return KhachHangArrayList;
	}
	
	public int getLatestID() {
		String Condition = 	"SELECT * "
    			+ "FROM qlnh.khach_hang kh "
    			+ "ORDER BY kh.maKhach DESC "
    			+ "LIMIT 1";
    	ArrayList<KhachHang> khachHangArrayList = KhachHangDAL.getInstance().selectByCondition(Condition);
    	if (khachHangArrayList.size()==0)
    		return 0;
    	else return khachHangArrayList.get(0).getMaKhach();
	}
	
	public boolean isSdtExist(String sdt) {
    	String Condition = "select* from qlnh.khach_hang kh where kh.soDienThoai=?";
    	ArrayList<KhachHang> khachHangArrayList = KhachHangDAL.getInstance().selectByCondition(Condition, sdt);
    	if (khachHangArrayList.size()==1)
    		return true;
    	else return false;
    }
	
	public KhachHang getKhachHangBySDT(String sdt) {
    	String Condition = "select* from qlnh.khach_hang kh where kh.soDienThoai=?";
    	ArrayList<KhachHang> khachHangArrayList = KhachHangDAL.getInstance().selectByCondition(Condition, sdt);
    	if (khachHangArrayList.size()==1)
    		return khachHangArrayList.get(0);
    	else
    		return null;
    }
	
	public KhachHang getKhachHangByMaKH(int maKH) {
    	String Condition = "select* from qlnh.khach_hang kh where kh.maKhach=?";
    	ArrayList<KhachHang> khachHangArrayList = KhachHangDAL.getInstance().selectByCondition(Condition, maKH);
    	if (khachHangArrayList.size()==1)
    		return khachHangArrayList.get(0);
    	else
    		return null;
    }

}
