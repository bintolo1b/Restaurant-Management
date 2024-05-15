package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DTO.NhanVien;


public class NhanVienDAL implements DALInterface<NhanVien> {
	private static NhanVienDAL instance;
	private Connection cnt = JDBC.getConnection();
    private NhanVienDAL(){
    }

    public static NhanVienDAL getInstance() {
        if (instance == null) {
            instance = new NhanVienDAL();
        }
        return instance;
    }
	
	@Override
	public void Insert(NhanVien obj) {
		obj.setMaNhanVien(GenerationNewID());
		String sql = "INSERT INTO nhan_vien(maNhanVien, ho, tenDem, ten, ngaySinh, soDienThoai, diaChi, gioiTinh, taiKhoan, trangThai)"
				+ "VALUES('"
				+ obj.getMaNhanVien() + "' , '"
				+ obj.getHo()+ "' , '"
				+ obj.getTenDem() + "' , '"
				+ obj.getTen() + "' , '"
				+ obj.getNgaySinh() +"' , '"
				+ obj.getSoDienThoai() + "' , '"
				+ obj.getDiaChi() + "' , '"
				+ obj.getGioiTinh()+ "' , '"
				+ obj.getTaiKhoan() + "' , '"
				+ obj.getTrangThai() + "')";
 		try {
			Statement stmt = cnt.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public int GenerationNewID() {
		if(getAll().size() == 0) return 1;
		else return getAll().size() + 1;
	}
	@Override
	public void Update(NhanVien obj) {
		String sql = "UPDATE nhan_vien "
				+ "SET "
				+ "ho = '" + obj.getHo()
				+ "' , tenDem = '" + obj.getTenDem()
				+ "' , ten = '" + obj.getTen()
				+ "' , ngaySinh = '" + obj.getNgaySinh()
				+ "' , soDienThoai = '" + obj.getSoDienThoai()
				+ "' , diaChi = '" + obj.getDiaChi() 
				+ "' , gioiTinh = '" + obj.getGioiTinh()
				+ "' , taiKhoan = '" + obj.getTaiKhoan()
				+ "' , trangThai = '" + obj.getTrangThai()
				+ "' WHERE maNhanVien = " + obj.getMaNhanVien();
		try {
			Statement stmt = cnt.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void Delete(NhanVien obj) {
		String sql = "DELETE from nhan_vien "
				+ "WHERE maNhanVien = " + obj.getMaNhanVien();
		try{
			Statement stmt = cnt.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public ArrayList<NhanVien> getAll() {
		ArrayList<NhanVien> list = new ArrayList<NhanVien>();
		String sql = "SELECT * FROM nhan_vien";
		try{
			Statement stmt = cnt.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				int id = rs.getInt("maNhanVien");
				String ho = rs.getString("ho");
				String tendem = rs.getString("tenDem");
				String ten = rs.getString("ten");
				String ngaysinh = rs.getString("ngaySinh");
				String sdt = rs.getString("soDienThoai");
				String diachi = rs.getString("diaChi");
				int gioitinh = rs.getInt("gioiTinh");	
				String taikhoan = rs.getString("taiKhoan");
				String trangthai = rs.getString("trangThai");
				NhanVien res = new NhanVien(id, ho, tendem, ten, ngaysinh, sdt, diachi, gioitinh,  taikhoan, trangthai);
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


	@Override
	public ArrayList<NhanVien> selectByCondition(String condition, Object... params) {
		ArrayList<NhanVien> NhanVienArrayList = new ArrayList<NhanVien>();
	    try {
	        PreparedStatement pst = cnt.prepareStatement(condition);
	        
	        if (params!=null)
		        for (int i = 0; i < params.length; i++) {
		            pst.setObject(i + 1, params[i]);
		        }
	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
	            NhanVien newNhanVien = new NhanVien(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getInt(8), res.getString(9),res.getString(10));
	            NhanVienArrayList.add(newNhanVien);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return NhanVienArrayList;
	}

	public NhanVien getNhanVienByID(int ID) {
		String condition = "select* "
				+ "from qlnh.nhan_vien nv "
				+ "where nv.maNhanVien = ?";
		ArrayList<NhanVien> nvArrayList = NhanVienDAL.getInstance().selectByCondition(condition, ID);
		if (nvArrayList.size()==0)
			return null;
		else return nvArrayList.get(0);
	}	
	
	public NhanVien getNhanVienByTaiKhoan(String taiKhoan) {
		String condition = "select* "
				+ "from nhan_vien nv "
				+ "where nv.taiKhoan=?";
		ArrayList<NhanVien> nhanVienArrayList  = NhanVienDAL.getInstance().selectByCondition(condition, taiKhoan);
		if (nhanVienArrayList.size()==0)
			return null;
		else 
			return nhanVienArrayList.get(0);
	}
}
