package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.DanhMuc;



public class DanhMucDAL implements DALInterface<DanhMuc> {
	private static DanhMucDAL instance;
	private Connection cnt = JDBC.getConnection();
	
    private DanhMucDAL(){
    }

    public static DanhMucDAL getInstance() {
        if (instance == null) {
            instance = new DanhMucDAL();
        }
        return instance;
    }
	
    @Override
	public void Insert(DanhMuc obj) {
		try {
			String querry = "INSERT INTO qlnh.danh_muc values (?,?,?)";
			PreparedStatement pst = cnt.prepareStatement(querry);
			pst.setInt(1, obj.getMaDanhMuc());
			pst.setString(2, obj.getTenDanhMuc());
			pst.setString(3, obj.getTrangThai());
			
			pst.executeUpdate();
			
			System.out.println("Successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		
	}

	@Override
	public void Update(DanhMuc obj) {
		try {
			String querry = "UPDATE qlnh.danh_muc set tenDanhMuc = ?, trangThai = ? where maDanhMuc = ?";
			PreparedStatement pst = cnt.prepareStatement(querry);
			
			pst.setString(1, obj.getTenDanhMuc());
			pst.setString(2, obj.getTrangThai());
			pst.setInt(3, obj.getMaDanhMuc());
			
			pst.executeUpdate();
			
			System.out.println("Update Successful");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Delete(DanhMuc obj) {
		try {
			String querry = "DELETE FROM qlnh.DanhMuc where maDanhMuc = ?";
			PreparedStatement pst;
			pst = cnt.prepareStatement(querry);
			pst.setString(1, obj.getTenDanhMuc());
			
			pst.executeUpdate();
			
			System.out.println("Successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public ArrayList<DanhMuc> getAll() {
		ArrayList<DanhMuc> danhMucArrayList = new ArrayList<DanhMuc>();
		try {
			String query = "select *"
					      + " from qlnh.danh_muc";
			PreparedStatement pst = cnt.prepareStatement(query);
			ResultSet res = pst.executeQuery();		
			
			while (res.next()) {
				DanhMuc newDanhMuc = new DanhMuc(res.getInt(1),res.getString(2),res.getString(3));
				danhMucArrayList.add(newDanhMuc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return danhMucArrayList;
	}


	@Override
	public ArrayList<DanhMuc> selectByCondition(String condition, Object... params) {
	    ArrayList<DanhMuc> danhMucArrayList = new ArrayList<>();
	    try {
	        PreparedStatement pst = cnt.prepareStatement(condition);
	        for (int i = 0; i < params.length; i++) {
	            pst.setObject(i + 1, params[i]);
	        }
	        ResultSet res = pst.executeQuery();

	        while (res.next()) {
	            DanhMuc newDanhMuc = new DanhMuc(res.getInt(1),res.getString(2),res.getString(3));
	            danhMucArrayList.add(newDanhMuc);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return danhMucArrayList; // Return an empty ArrayList if no results found
	}
	
	public int getLatestID() {
		String Condition = "select* "
				+ "from qlnh.danh_muc dm  "
				+ "order by dm.maDanhMuc desc "
				+ "limit 1";
    	ArrayList<DanhMuc> danhMucArrayList = DanhMucDAL.getInstance().selectByCondition(Condition);
    	if (danhMucArrayList.size()==0)
    		return 0;
    	else return danhMucArrayList.get(0).getMaDanhMuc();
	}
	
	 public DanhMuc getDanhMucByID(int ID){
	    	String condition =  "select *"
	    			+"from danh_muc "
	    			+"where danh_muc.maDanhMuc = ?";
	    	ArrayList<DanhMuc> danhMucArrayList = DanhMucDAL.getInstance().selectByCondition(condition, ID);
	    	if(danhMucArrayList.size() == 0) {
	    		return null;
	    	}
	    	else {
	    		return danhMucArrayList.get(0);
	    	}
	}
	 
	 public boolean isDanhMucExist(int categoryID) {
	        String condition = "select * "
	                        + "from danh_muc "
	                        + "where danh_muc.maDanhMuc = ?";
	        ArrayList<DanhMuc> danhMucArrayList = DanhMucDAL.getInstance().selectByCondition(condition, categoryID);
	        return !danhMucArrayList.isEmpty(); 
	}
}
