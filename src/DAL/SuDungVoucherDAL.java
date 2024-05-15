package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.SuDungVoucher;

public class SuDungVoucherDAL implements DALInterface<SuDungVoucher> {
	private static SuDungVoucherDAL instance;
	Connection cnt = JDBC.getConnection();
	
	private SuDungVoucherDAL() {
	}
	
	public static SuDungVoucherDAL getInstance() {
		if (instance==null)
			instance = new SuDungVoucherDAL();
		return instance;
	}
	
	
	
	@Override
	public void Insert(SuDungVoucher obj) {
		try {
			String query = "insert into su_dung_voucher value "
					+ "(?,?,?)";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setObject(1, obj.getMaHoaDon());
			pst.setObject(2, obj.getMaVoucher());
			pst.setObject(3, obj.getPhanTramGiam());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void Update(SuDungVoucher obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(SuDungVoucher obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<SuDungVoucher> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SuDungVoucher> selectByCondition(String condition, Object... params) {
		ArrayList<SuDungVoucher> SDVCArrayList = new ArrayList<SuDungVoucher>();
	  	try {
	        PreparedStatement pst = cnt.prepareStatement(condition);
	        
	        if (params!=null)
		        for (int i = 0; i < params.length; i++) {
		            pst.setObject(i + 1, params[i]);
		        }	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
	            SuDungVoucher newSDVC = new SuDungVoucher(res.getInt(1), res.getInt(2), res.getInt(3));
	            SDVCArrayList.add(newSDVC);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }	   
		return SDVCArrayList;
	}
	
	public ArrayList<SuDungVoucher> getAllSuDungVoucher(int maHoaDon) {
		String query = "select* from qlnh.su_dung_voucher sdvc where maHoaDon = ?";
		ArrayList<SuDungVoucher> arrayList = SuDungVoucherDAL.getInstance().selectByCondition(query, maHoaDon);
		return arrayList;
	}

}
