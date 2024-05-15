package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.SanPham;


public class SanPhamDAL implements DALInterface<SanPham> {
	private static SanPhamDAL instance;
	private Connection cnt = JDBC.getConnection();
	
    private SanPhamDAL(){
    }

    public static SanPhamDAL getInstance() {
        if (instance == null) {
            instance = new SanPhamDAL();
        }
        return instance;
    }
	
	@Override
	public void Insert(SanPham obj) {
		try {
			PreparedStatement pst;
			String condition = "INSERT INTO qlnh.sanpham values(?,?,?,?,?)";
			pst = cnt.prepareStatement(condition);
			pst.setInt(1, obj.getMaSanPham());
			pst.setString(2, obj.getTen());
			pst.setFloat(3, obj.getGia());
			pst.setString(4, obj.getTrangThai());
			pst.setInt(5, obj.getMaDanhMuc());
			
			pst.executeUpdate();
			
			System.out.println("Insert successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		
	}

	@Override
	public void Update(SanPham obj) {
		try {
			String condition = "UPDATE qlnh.sanpham set ten = ?, gia = ?, trangThai = ? where maSanPham = ?";
			PreparedStatement pst = cnt.prepareStatement(condition);
			
			pst.setString(1, obj.getTen());
			pst.setFloat(2, obj.getGia());
			pst.setString(3, obj.getTrangThai());
			pst.setInt(4, obj.getMaSanPham());
			
			pst.executeUpdate();
			
			System.out.println("Update successfully");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Delete(SanPham obj) {
		try {
			PreparedStatement pst;
			String condition = "DELETE FROM qlnh.sanpham where maSanPham = ?";
			pst = cnt.prepareStatement(condition);
			pst.setInt(1, obj.getMaSanPham());
			
			pst.executeUpdate();
			
			System.out.println("Delete successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public ArrayList<SanPham> getAll() {
		ArrayList<SanPham> sanPhamArrayList = new ArrayList<SanPham>();
		try {
			String query = "select *"
					      + " from qlnh.sanpham";
			PreparedStatement pst = cnt.prepareStatement(query);
			ResultSet res = pst.executeQuery();		
			
			while (res.next()) {
				SanPham newSanPham = new SanPham(res.getInt(1),res.getString(2),res.getFloat(3),res.getString(4),res.getInt(5));
				sanPhamArrayList.add(newSanPham);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sanPhamArrayList;
	}


	@Override
	public ArrayList<SanPham> selectByCondition(String condition, Object... params) {
	    ArrayList<SanPham> SanPhamArrayList = new ArrayList<SanPham>();
	    try {
	        PreparedStatement pst = cnt.prepareStatement(condition);
	        
	        if (params!=null)
		        for (int i = 0; i < params.length; i++) {
		            pst.setObject(i + 1,params[i]);
		        }
	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
	            SanPham newSanPham = new SanPham(res.getInt(1), res.getString(2), res.getFloat(3), res.getString(4), res.getInt(5));
	            SanPhamArrayList.add(newSanPham);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return SanPhamArrayList;
	}

	public SanPham getSanPhamByTen(String tenSp) {
		String condiString = "select *"
    			+ "from qlnh.sanpham sp "
    			+ "where sp.ten=?";
    	ArrayList<SanPham> sanPhamArrayList = SanPhamDAL.getInstance().selectByCondition(condiString, tenSp);
    	if (sanPhamArrayList.size()==0)
    		return null;
    	else return sanPhamArrayList.get(0);
	}
	
	public int getLatestID() {
		String Condition = "select* "
				+ "from qlnh.sanpham sp "
				+ "order by sp.maSanPham desc "
				+ "limit 1";
    	ArrayList<SanPham> sanPhamArrayList = SanPhamDAL.getInstance().selectByCondition(Condition);
    	if (sanPhamArrayList.size()==0)
    		return 0;
    	else return sanPhamArrayList.get(0).getMaSanPham();
		}
	
	 public SanPham getSanPhamByID(int ID){
	    	String condition =  "select *"
	    			+"from sanpham "
	    			+"where sanpham.maSanPham = ?";
	    	ArrayList<SanPham> sanPhamArrayList = SanPhamDAL.getInstance().selectByCondition(condition, ID);
	    	if(sanPhamArrayList.size() == 0) {
	    		return null;
	    	}
	    	else {
	    		return sanPhamArrayList.get(0);
	    	}
	 }
	 
	 public boolean isSanPhamExist(int productID) {
	        String condition = "select * "
	                        + "from sanpham "
	                        + "where sanpham.maSanPham = ?";
	        ArrayList<SanPham> sanPhamArrayList = SanPhamDAL.getInstance().selectByCondition(condition, productID);
	        return !sanPhamArrayList.isEmpty(); 
	 }
	 
	 public ArrayList<SanPham> getAllSanPhamInDanhMuc(int categoryID) {
	    	String condtition = "select * "
					+ "from sanpham  "
					+ "where sanpham.maDanhMuc = ?";
	    	ArrayList<SanPham> sanPhamArrayList = SanPhamDAL.getInstance().selectByCondition(condtition,categoryID);
	    	return sanPhamArrayList;
	 }
	 
	 public ArrayList<SanPham> getAllSanPhamInDanhMucByName(String categoryName) {
	    	String condtition = "select * "
					+ "from sanpham sp join danh_muc dm on sp.maDanhMuc = dm.maDanhMuc "
					+ "where dm.tenDanhMuc = ?";
	    	ArrayList<SanPham> sanPhamArrayList = SanPhamDAL.getInstance().selectByCondition(condtition,categoryName);
	    	return sanPhamArrayList;
	 }
	 
	 public ArrayList<SanPham> getSanPhamBySubName(String productName){
	    	productName = "%"+productName+"%";
	    	String condition = "select * from sanpham where sanpham.ten like ?";
	    	ArrayList<SanPham> sanPhamArrayList = SanPhamDAL.getInstance().selectByCondition(condition, productName);
	    	return sanPhamArrayList;
	 }
	 
	 public float getGia(String tenSanPham) {
	    	String condiString = "select *"
	    			+ "from qlnh.sanpham sp "
	    			+ "where sp.ten=?";
	    	ArrayList<SanPham> sanPhamArrayList = SanPhamDAL.getInstance().selectByCondition(condiString, tenSanPham);
	    	return sanPhamArrayList.get(0).getGia();
	 }
}

