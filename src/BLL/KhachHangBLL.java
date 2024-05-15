package BLL;

import DAL.KhachHangDAL;
import DTO.KhachHang;

public class KhachHangBLL {
	private static KhachHangBLL instance;

    private KhachHangBLL(){
    }

    public static KhachHangBLL getInstance() {
        if (instance == null) {
            instance = new KhachHangBLL();
        }
        return instance;
    } 
    
    public int generateNewID() {
    	return KhachHangDAL.getInstance().getLatestID()+1;
    }
    
    public void addNewKhachHang(KhachHang newKH) {
    	KhachHangDAL.getInstance().Insert(newKH);
    }
    
    public void UpdateKhachHang(KhachHang KH) {
    	KhachHangDAL.getInstance().Update(KH);
    }
    
    public boolean isSdtExist(String sdt) {
    	return KhachHangDAL.getInstance().isSdtExist(sdt);
    }
    
    public KhachHang getKhachHangBySDT(String sdt) {
    	return KhachHangDAL.getInstance().getKhachHangBySDT(sdt);
    }
    
    public KhachHang getKhachHangByMaKH(int maKH) {
    	return KhachHangDAL.getInstance().getKhachHangByMaKH(maKH);
    }
    
    
}
