package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.DieuKienVoucher;


public class DieuKienVoucherDAL implements DALInterface<DieuKienVoucher> {
	private static DieuKienVoucherDAL instance;
	private Connection cnt = JDBC.getConnection();
	
    private DieuKienVoucherDAL(){
    }

    public static DieuKienVoucherDAL getInstance() {
        if (instance == null) {
            instance = new DieuKienVoucherDAL();
        }
        return instance;
    }
	
	@Override
	public void Insert(DieuKienVoucher obj) {
		try {
			String query = "insert into dieu_kien_voucher value "
					+ "(?,?,?,?)";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setObject(1, obj.getMaDieuKien());
			pst.setObject(2, obj.getDiemYeuCau());
			pst.setObject(3, obj.getPhanTram());
			pst.setObject(4, obj.getTinhTrang());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void Update(DieuKienVoucher obj) {
		try {
			String query = "update dieu_kien_voucher "
					+ "set diemYeuCau=?, phanTram=?, tinhTrang=? "
					+ "where maDieuKien=?";
			PreparedStatement pst = cnt.prepareStatement(query);
			pst.setObject(1, obj.getDiemYeuCau());
			pst.setObject(2, obj.getPhanTram());
			pst.setObject(3, obj.getTinhTrang());
			pst.setObject(4, obj.getMaDieuKien());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Delete(DieuKienVoucher obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<DieuKienVoucher> getAll() {
		ArrayList<DieuKienVoucher> dkvcArrayList = new ArrayList<DieuKienVoucher>();
		try {
			String query = "select* from dieu_kien_voucher";
			PreparedStatement pst =  cnt.prepareStatement(query);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				DieuKienVoucher newDKVC = new DieuKienVoucher(res.getInt(1), res.getFloat(2), res.getFloat(3), res.getString(4));
				dkvcArrayList.add(newDKVC);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return dkvcArrayList;
	}


	@Override
	public ArrayList<DieuKienVoucher> selectByCondition(String condition, Object... params) {
		ArrayList<DieuKienVoucher> dkvcArrayList = new ArrayList<DieuKienVoucher>();
	    try {	  
	        PreparedStatement pst = cnt.prepareStatement(condition);
	        
	        if (params!=null)
		        for (int i = 0; i < params.length; i++) {
		            pst.setObject(i + 1, params[i]);
		        }
	        
	        ResultSet res = pst.executeQuery();
	        while (res.next()) {
	        	DieuKienVoucher newDKVC = new DieuKienVoucher(res.getInt(1), res.getFloat(2), res.getFloat(3), res.getString(4));
	        	dkvcArrayList.add(newDKVC);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dkvcArrayList;
	}
	
	public int getLatestID() {
		String Condition = "select* "
				+ "from qlnh.dieu_kien_voucher dkvc  "
				+ "order by dkvc.maDieuKien desc "
				+ "limit 1";
    	ArrayList<DieuKienVoucher> dkvcArrayList = DieuKienVoucherDAL.getInstance().selectByCondition(Condition);
    	if (dkvcArrayList.size()==0)
    		return 0;
    	else return dkvcArrayList.get(0).getMaDieuKien();
	}
	
	public DieuKienVoucher getDKVCByID(int dkvcID) {
		String query = "select* from dieu_kien_voucher where maDieuKien=?";
		ArrayList<DieuKienVoucher> dkvcArrayList = DieuKienVoucherDAL.getInstance().selectByCondition(query, dkvcID);
		if (dkvcArrayList.size()==0)
			return null;
		else return dkvcArrayList.get(0);
	}
	
	public Float getPhanTramGiam(int dkvcID) {
		String query = "select * "
				+ "from qlnh.dieu_kien_voucher dkvc "
				+ "where dkvc.maDieuKien=?";
		ArrayList<DieuKienVoucher> dkvcArraylist = DieuKienVoucherDAL.getInstance().selectByCondition(query, dkvcID);
		if (dkvcArraylist.size()==1)
			return dkvcArraylist.get(0).getPhanTram();
		else 
			return null;
	}

	public ArrayList<DieuKienVoucher> getAllDieuKienVoucherChuaApDungOfKhachHang(float currentDiemTichLuy, int maKH) {
		String query ="select* from qlnh.dieu_kien_voucher dkvc "
				+ "where dkvc.tinhTrang='Đang mở' and dkvc.diemYeuCau<=? and dkvc.maDieuKien not in "
					+ "(select vc.maDieuKien "
					+ "from qlnh.Voucher vc "
					+ "where vc.maKhach=?)";
		ArrayList<DieuKienVoucher> dkvcArrayList = DieuKienVoucherDAL.getInstance().selectByCondition(query, currentDiemTichLuy, maKH);
		return dkvcArrayList;
	}

}
