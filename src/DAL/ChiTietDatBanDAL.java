package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.ChiTietDatBan;


public class ChiTietDatBanDAL implements DALInterface<ChiTietDatBan> {
	private static ChiTietDatBanDAL instance;
	private Connection cnt = JDBC.getConnection();
	
    private ChiTietDatBanDAL(){
    }

    public static ChiTietDatBanDAL getInstance() {
        if (instance == null) {
            instance = new ChiTietDatBanDAL();
        }
        return instance;
    }
    
	@Override
	public void Insert(ChiTietDatBan obj) 
	{
		try {
		String query = "";
		if (!obj.getNgayGioNhan_Huy().isEmpty()) {
		     query= "INSERT INTO qlnh.chi_tiet_dat_ban " +
		                 "VALUES ('" + obj.getNgayGioDat() + "', '" + obj.getNgayGioNhan_Huy() + "', " +
		                 obj.getMaBan() + ", '" + obj.getSoDienThoaiKH() + "', " + obj.getMaNhanVien() + ", '" + obj.getTrangThai() + "')";
		} else {

		     query= "INSERT INTO qlnh.chi_tiet_dat_ban (ngayGioDat, maBan, soDienThoaiKH, maNhanVien, trangThai)  " +
		                 "VALUES ('" + obj.getNgayGioDat() + "', " +
		                 obj.getMaBan() + ", '" + obj.getSoDienThoaiKH() + "', " + obj.getMaNhanVien() + ", '" + obj.getTrangThai() + "')";
		}
		PreparedStatement pst = cnt.prepareStatement(query);
		pst.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}	
	}

	@Override
	public void Update(ChiTietDatBan obj) {
		try {			
			String query = "update qlnh.chi_tiet_dat_ban "
					+ "set ngayGioNhan_Huy = '"+ obj.getNgayGioNhan_Huy()+"', trangThai = '" + obj.getTrangThai()+"' "
					+" where ngayGioDat = '" + obj.getNgayGioDat() +"' and maBan = " + obj.getMaBan();
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	@Override
	public void Delete(ChiTietDatBan obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ChiTietDatBan> getAll() {
		ArrayList<ChiTietDatBan> ctdbArrayList = new ArrayList<ChiTietDatBan>();
		try {
			String query = "select* from chi_tiet_dat_ban";
			PreparedStatement pst = cnt.prepareStatement(query);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				ChiTietDatBan newCTDB = new  ChiTietDatBan(res.getString(1), res.getString(2), res.getInt(3), res.getString(4), res.getInt(5), res.getString(6));
				ctdbArrayList.add(newCTDB);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ctdbArrayList;
	}

	@Override
	public ArrayList<ChiTietDatBan> selectByCondition(String condition, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public void UpdateCB(ChiTietDatBan obj, int maban) {
		try {			
			String query = "update qlnh.chi_tiet_dat_ban "
                    + "set maBan = " + obj.getMaBan() 
                    + " where ngayGioDat = '" + obj.getNgayGioDat() + "' and maBan = " + maban;
			
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
