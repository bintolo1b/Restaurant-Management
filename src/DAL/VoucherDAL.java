package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Voucher;

public class VoucherDAL implements DALInterface<Voucher> {
	private static VoucherDAL instance;
	private Connection cnt = JDBC.getConnection();
	
    private VoucherDAL(){
    }

    public static VoucherDAL getInstance() {
        if (instance == null) {
            instance = new VoucherDAL();
        }
        return instance;
    }
	
	@Override
	public void Insert(Voucher obj) {
		try {
			String query = "insert into voucher value "
					+ "(?,?,?,?)";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setObject(1, obj.getMaVoucher());
			pst.setObject(2, obj.getTinhTrangSuDung());
			pst.setObject(3, obj.getMaKhach());
			pst.setObject(4, obj.getMaDieuKien());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Update(Voucher obj) {
		try {
			String query = "update voucher "
					+ "set tinhTrangSuDung =? "
					+ "where maVoucher =?";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setObject(1, obj.getTinhTrangSuDung());
			pst.setObject(2, obj.getMaVoucher());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void Delete(Voucher obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Voucher> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Voucher> selectByCondition(String condition, Object... params) {
		ArrayList<Voucher> voucherArrayList = new ArrayList<Voucher>();
	  	try {
	        PreparedStatement pst = cnt.prepareStatement(condition);
	        
	        if (params!=null)
		        for (int i = 0; i < params.length; i++) {
		            pst.setObject(i + 1, params[i]);
		        }	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
	            Voucher newVoucher = new Voucher(res.getInt(1), res.getString(2), res.getInt(3), res.getInt(4));
	            voucherArrayList.add(newVoucher);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }	   
		return voucherArrayList;
	}
	
	public int getLatestID() {
		String Condition = "select* "
				+ "from qlnh.voucher vc  "
				+ "order by vc.maVoucher desc "
				+ "limit 1";
    	ArrayList<Voucher> voucherArrayList = VoucherDAL.getInstance().selectByCondition(Condition);
    	if (voucherArrayList.size()==0)
    		return 0;
    	else return voucherArrayList.get(0).getMaVoucher();
	}
	
	public ArrayList<Voucher> gettAllVoucherChuaSuDung(int IDKhach){
		ArrayList<Voucher> voucherArrayList;
		String query = "select* "
				+ "from qlnh.voucher vc "
				+ "where  vc.tinhTrangSuDung='Chưa sử dụng' and vc.maKhach=?";
		voucherArrayList = VoucherDAL.getInstance().selectByCondition(query, IDKhach);
		return voucherArrayList;
	}


}
