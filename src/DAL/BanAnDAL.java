package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.BanAn;


public class BanAnDAL implements DALInterface<BanAn> {
	private static BanAnDAL instance;
	private Connection cnt = JDBC.getConnection();
	
    private BanAnDAL(){
    }

    public static BanAnDAL getInstance() {
        if (instance == null) {
            instance = new BanAnDAL();
        }
        return instance;
    }
	
	@Override
	public void Insert(BanAn obj) {
		try {			
			String query = "insert into ban_an values(?,?,?)";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setInt(1, obj.getMaBan());
			pst.setInt(2, obj.getSoGhe());
			pst.setString(3, obj.getTrangThai());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void Update(BanAn obj) {		
		try {			
			String query = "update ban_an "
					+ "set soGhe=?, trangThai=? "
					+ "where maBan = ?";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setString(1, obj.getSoGhe()+"");
			pst.setString(2, obj.getTrangThai());
			pst.setString(3, obj.getMaBan()+"");
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void Delete(BanAn obj) {
	}

	@Override
	public ArrayList<BanAn> getAll() {
		ArrayList<BanAn> banAnArrayList = new ArrayList<BanAn>();
		try {
			String querry = "select *"
					      + " from qlnh.ban_an";
			PreparedStatement pst = cnt.prepareStatement(querry);
			ResultSet res = pst.executeQuery();		
			
			while (res.next()) {
				BanAn newBanAn = new BanAn(Integer.parseInt(res.getString(1)), Integer.parseInt(res.getString(2)), res.getString(3));
				banAnArrayList.add(newBanAn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return banAnArrayList;
	}

	@Override
	public ArrayList<BanAn> selectByCondition(String condition, Object... params) {
		ArrayList<BanAn> BanAnArrayList = new ArrayList<BanAn>();
	    try {      
	        PreparedStatement pst = cnt.prepareStatement(condition);
	        
	        if (params!=null)
		        for (int i = 0; i < params.length; i++) {
		            pst.setObject(i + 1, params[i]);
		        }
	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
	            BanAn newBanAn = new BanAn(res.getInt(1),res.getInt(2),res.getString(3));
	            BanAnArrayList.add(newBanAn);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return BanAnArrayList;
	}

	 public BanAn getBan(int tableID) {
	    	String condition = "select * "
	    			+ "from qlnh.ban_an ba "
	    			+ "where ba.maBan = ?";
	    	ArrayList<BanAn> banAnArrayList = BanAnDAL.getInstance().selectByCondition(condition, tableID);
	    	return banAnArrayList.get(0);
	 } 
}
