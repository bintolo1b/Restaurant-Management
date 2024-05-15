package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.QuanLy;

public class QuanLyDAL implements DALInterface<QuanLy> {
	private static QuanLyDAL instance;
	private Connection cnt = JDBC.getConnection();
	
    private QuanLyDAL(){
    }

    public static QuanLyDAL getInstance() {
        if (instance == null) {
            instance = new QuanLyDAL();
        }
        return instance;
    }
	
	@Override
	public void Insert(QuanLy obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update(QuanLy obj) {
		try {
			String condition = "UPDATE qlnh.quan_ly set ho = ?, tenDem = ?, ten = ?, ngaySinh = ?, gioiTinh = ?, diaChi = ?, soDienThoai = ? where taiKhoan = ?";
			PreparedStatement pst = cnt.prepareStatement(condition);
			
			pst.setString(1, obj.getHo());
			pst.setString(2, obj.getTenDem());
			pst.setString(3, obj.getTen());
			pst.setString(4, obj.getNgaySinh());
			pst.setInt(5, obj.getGioiTinh());
			pst.setString(6, obj.getDiaChi());
			pst.setString(7, obj.getSoDienThoai());
			pst.setString(8, obj.getTaiKhoan());
			pst.executeUpdate();
			
			System.out.println("Update successfully");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void Delete(QuanLy obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<QuanLy> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<QuanLy> selectByCondition(String condition, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public QuanLy getQuanLy() {
		QuanLy newQuanLy = null;
		try {
			String query = "select* from quan_ly";
			PreparedStatement pst = cnt.prepareStatement(query);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				newQuanLy = new QuanLy(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6), res.getString(7), res.getString(8));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newQuanLy;
	}

}
