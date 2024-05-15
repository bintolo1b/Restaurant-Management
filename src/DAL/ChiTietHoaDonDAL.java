package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import DTO.ChiTietHoaDon;

public class ChiTietHoaDonDAL implements DALInterface<ChiTietHoaDon> {
	private static ChiTietHoaDonDAL instance;
	private Connection cnt = JDBC.getConnection();
	
    private ChiTietHoaDonDAL(){
    }

    public static ChiTietHoaDonDAL getInstance() {
        if (instance == null) {
            instance = new ChiTietHoaDonDAL();
        }
        return instance;
    }
	
	
	@Override
	public void Insert(ChiTietHoaDon obj) {
		try {	
			String query = "insert into qlnh.chi_tiet_hoa_don value "
					+ "(?,?,?,?,?,?,?)";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setObject(1, obj.getMaHoaDon());
			pst.setObject(2, obj.getMaSanPham());
			pst.setObject(3, obj.getGiaSPTaiThoiDiem());
			pst.setObject(4, obj.getTenSPTaiThoiDiem());
			pst.setObject(5, obj.getSoLuong());
			pst.setObject(6, obj.getLanGoi());
			pst.setObject(7, obj.getTinhTrangPhucVu());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void Update(ChiTietHoaDon obj) {
		try {		
			String query = "update chi_tiet_hoa_don "
					+ "set soLuong=? , tinhTrangPhucVu=?"
					+ "where maHoaDon=? and maSanPham=? and lanGoi=?";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setObject(1, obj.getSoLuong());
			pst.setObject(2, obj.getTinhTrangPhucVu());
			pst.setObject(3, obj.getMaHoaDon());
			pst.setObject(4, obj.getMaSanPham());
			pst.setObject(5, obj.getLanGoi());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void Delete(ChiTietHoaDon obj) {
		try {		
			String query = "delete  "
					+ "from qlnh.chi_tiet_hoa_don cthd "
					+ "where cthd.maHoaDon=? and cthd.maSanPham=? and cthd.lanGoi=?";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setObject(1, obj.getMaHoaDon());
			pst.setObject(2, obj.getMaSanPham());
			pst.setObject(3, obj.getLanGoi());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public ArrayList<ChiTietHoaDon> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<ChiTietHoaDon> selectByCondition(String condition, Object... params) {
		ArrayList<ChiTietHoaDon> ChiTietHoaDonArrayList = new ArrayList<ChiTietHoaDon>();
	    try {	     
	        PreparedStatement pst = cnt.prepareStatement(condition);
	        
	        if (params!=null)
		        for (int i = 0; i < params.length; i++) {
		            pst.setObject(i + 1, params[i]);
		        }
	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
	            ChiTietHoaDon newChiTietHoaDon = new ChiTietHoaDon(res.getInt(1), res.getInt(2), res.getFloat(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7));
	            ChiTietHoaDonArrayList.add(newChiTietHoaDon);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ChiTietHoaDonArrayList;
	}
	
	public void exportChiTietHoaDonIntoServedModelTable(DefaultTableModel model, ArrayList<ChiTietHoaDon> cthdOfSpecificHoaDonArrayList) {
		if (cthdOfSpecificHoaDonArrayList.size()!=0) {
			try {
				int maHD = cthdOfSpecificHoaDonArrayList.get(0).getMaHoaDon();				
				String query = "select cthd.tenSPTaiThoiDiem, cthd.soLuong, cthd.giaSPTaiThoiDiem ,cthd.soLuong*cthd.giaSPTaiThoiDiem as thanhtien, cthd.lanGoi, cthd.tinhTrangPhucVu "
						+ "from qlnh.chi_tiet_hoa_don cthd "
						+ "where cthd.maHoaDon=? and cthd.tinhTrangPhucVu='Đã phục vụ' "
						+ "order by lanGoi";
				PreparedStatement pst = cnt.prepareStatement(query);
				pst.setObject(1, maHD);
				ResultSet res = pst.executeQuery();
				while (res.next()) {
					Object[] row = {res.getString(1),res.getInt(2),res.getFloat(3),res.getFloat(4),res.getInt(5), res.getString(6)};
					model.addRow(row);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}	
	}
	
	public ArrayList<ChiTietHoaDon> getAllChiTietHoaDonOfHoaDon(int maHD){
		String condition = "select* "
				+ "from qlnh.chi_tiet_hoa_don cthd "
				+ "where cthd.maHoaDon = ?";
		ArrayList<ChiTietHoaDon> ChiTietHoaDonArrayList = ChiTietHoaDonDAL.getInstance().selectByCondition(condition, maHD);
		return ChiTietHoaDonArrayList;
	}

	public ChiTietHoaDon getChiTietHoaDon(int maHoaDon, int maSanPham, int lanGoi) {
		String condition2 = "select* "
				+ "from qlnh.chi_tiet_hoa_don cthd "
				+ "where cthd.maHoaDon = ? and cthd.maSanPham=? and lanGoi=?";
		ArrayList<ChiTietHoaDon> ChiTietHoaDonArrayList = ChiTietHoaDonDAL.getInstance().selectByCondition(condition2, maHoaDon, maSanPham, lanGoi);
		if (ChiTietHoaDonArrayList.size()==0)
			return null;
		else return ChiTietHoaDonArrayList.get(0);
	}

	public ChiTietHoaDon getCTHDByLatestLanGoi(int maHD) {
		String condition = " select * "
				+ "from chi_tiet_hoa_don cthd "
				+ "where cthd.maHoaDon=? "
				+ "order by lanGoi desc "
				+ "limit 1";
		ArrayList<ChiTietHoaDon> ChiTietHoaDonArrayList = ChiTietHoaDonDAL.getInstance().selectByCondition(condition, maHD);
		if (ChiTietHoaDonArrayList.size()==0)
			return null;
		else
			return ChiTietHoaDonArrayList.get(0);
	}

	public void exportChiTietHoaDonIntoModelTableWithMergeSP(DefaultTableModel model, int maHoaDon) {
		try {	
			String query = "select tenSPTaiThoiDiem, sum(soLuong), giaSPTaiThoiDiem, sum(soLuong)*giaSPTaiThoiDiem as ThanhTien "
					+ "from chi_tiet_hoa_don cthd where "
					+ "maHoaDon = ? and tinhTrangPhucVu='Đã phục vụ' "
					+ "group by tenSPTaiThoiDiem, giaSPTaiThoiDiem";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setObject(1, maHoaDon);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				Object[] row = {res.getString(1),res.getInt(2),res.getFloat(3),res.getFloat(4)};
				model.addRow(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public boolean checkIfExistSpChuaPhucVu(int maHD) {
		try {	
			String query = "select count(*) "
					+ "from chi_tiet_hoa_don cthd where cthd.maHoaDon=? "
					+ "and cthd.tinhTrangPhucVu='Chưa phục vụ'";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setObject(1, maHD);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				if (res.getInt(1)==0)
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void exportChiTietHoaDonIntoWaitingModelTable(DefaultTableModel waitingModel, ArrayList<ChiTietHoaDon> cthdOfSpecificHoaDonArrayList) {
		if (cthdOfSpecificHoaDonArrayList.size()!=0) {
			try {
				int maHD = cthdOfSpecificHoaDonArrayList.get(0).getMaHoaDon();				
				String query = "select cthd.tenSPTaiThoiDiem, cthd.soLuong, cthd.giaSPTaiThoiDiem ,cthd.soLuong*cthd.giaSPTaiThoiDiem as thanhtien, cthd.lanGoi, cthd.tinhTrangPhucVu "
						+ "from qlnh.chi_tiet_hoa_don cthd "
						+ "where cthd.maHoaDon=? and cthd.tinhTrangPhucVu!='Đã phục vụ' "
						+ "order by lanGoi";
				PreparedStatement pst = cnt.prepareStatement(query);
				pst.setObject(1, maHD);
				ResultSet res = pst.executeQuery();
				while (res.next()) {
					Object[] row = {res.getString(1),res.getInt(2),res.getFloat(3),res.getFloat(4),res.getInt(5), res.getString(6)};
					waitingModel.addRow(row);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}

}
