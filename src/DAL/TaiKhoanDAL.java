package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.TaiKhoan;


public class TaiKhoanDAL implements DALInterface<TaiKhoan> {
	private static TaiKhoanDAL instance;
	private Connection cnt = JDBC.getConnection();
	
    private TaiKhoanDAL(){
    }

    public static TaiKhoanDAL getInstance() {
        if (instance == null) {
            instance = new TaiKhoanDAL();
        }
        return instance;
    }
	
	@Override
	public void Insert(TaiKhoan obj) {
		String sql = "INSERT INTO tai_khoan(taiKhoan, matKhau, loaiNguoiDung)"
				+ "VALUES('"
				+ obj.getTaiKhoan() + "' , '"
				+ obj.getMatKhau()+ "' , '"
				+ obj.getLoaiNguoiDung() +"')";
		try {
			Connection cnt = JDBC.getConnection();
			Statement stmt = cnt.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void Update(TaiKhoan obj) {
		try {
			String querry = "UPDATE qlnh.tai_khoan set matKhau = ? where taiKhoan = ?";
			PreparedStatement pst = cnt.prepareStatement(querry);
			
			pst.setString(1, obj.getMatKhau());
			pst.setString(2, obj.getTaiKhoan());
			
			pst.executeUpdate();
			
			System.out.println("Update Successful");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Delete(TaiKhoan obj) {
		String sql = "DELETE FROM tai_khoan "
				+ "WHERE taiKhoan = '" + obj.getTaiKhoan() + "'";
		try{
			Connection cnt = JDBC.getConnection();
			Statement stmt = cnt.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public ArrayList<TaiKhoan> getAll() {
		ArrayList<TaiKhoan> list = new ArrayList<TaiKhoan>();
		String sql = "SELECT * FROM tai_khoan";
		try{
			Connection cnt = JDBC.getConnection();
			Statement stmt = cnt.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				String taikhoan = rs.getString("taiKhoan");
				String matkhau = rs.getString("matKhau");
				String loainguoidung = rs.getString("loaiNguoiDung");
				TaiKhoan res = new TaiKhoan(taikhoan, matkhau, loainguoidung);
				list.add(res);
			}
			rs.close();
			stmt.executeQuery(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public TaiKhoan selectByTK(String tenTK) {
		TaiKhoan res = null;
		String sql = "SELECT * FROM tai_khoan WHERE taiKhoan = '" + tenTK +"'";
		try{
			Connection cnt = JDBC.getConnection();
			Statement stmt = cnt.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				String taikhoan = rs.getString("taiKhoan");
				String matkhau = rs.getString("matKhau");
				String loainguoidung = rs.getString("loaiNguoiDung");
				res = new TaiKhoan(taikhoan, matkhau, loainguoidung);
			}
			rs.close();
			stmt.executeQuery(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public ArrayList<TaiKhoan> selectByCondition(String condition, Object... params) {
		ArrayList<TaiKhoan> TaiKhoanArrayList = new ArrayList<TaiKhoan>();
	  	try {
	        PreparedStatement pst = cnt.prepareStatement(condition);
	        
	        if (params!=null)
		        for (int i = 0; i < params.length; i++) {
		            pst.setObject(i + 1, params[i]);
		        }	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
	            TaiKhoan newTaiKhoan = new TaiKhoan(res.getString(1), res.getString(2), res.getString(3));
	            TaiKhoanArrayList.add(newTaiKhoan);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }	   
		return TaiKhoanArrayList;
	}
	
	public TaiKhoan getTaiKhoan(String taiKhoan, String matKhau) {
		String condition = "select* "
				+ "from tai_khoan tk "
				+ "where tk.taiKhoan=? and tk.matKhau=?";
		ArrayList<TaiKhoan> taiKhoanArrayList = TaiKhoanDAL.getInstance().selectByCondition(condition, taiKhoan, matKhau);
		if (taiKhoanArrayList.size()==0)
			return null;
		else 
			return taiKhoanArrayList.get(0);
	}

}

