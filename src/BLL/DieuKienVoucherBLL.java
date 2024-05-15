package BLL;

import java.util.ArrayList;
import DAL.DieuKienVoucherDAL;
import DTO.DieuKienVoucher;

public class DieuKienVoucherBLL {
	private static DieuKienVoucherBLL instance;
	
	private DieuKienVoucherBLL() {
	}
	
	public static DieuKienVoucherBLL getInstance() {
		if (instance==null)
			instance=new DieuKienVoucherBLL();
		return instance;
	}
	
	public int generateNewID(){
		return DieuKienVoucherDAL.getInstance().getLatestID()+1;
	}
	
	public ArrayList<DieuKienVoucher> getAllDKVC(){
		return DieuKienVoucherDAL.getInstance().getAll();
	}
	
	public DieuKienVoucher getDKVCByID(int dkvcID) {
		return DieuKienVoucherDAL.getInstance().getDKVCByID(dkvcID);
	}
	
	public void addNewDKVC(DieuKienVoucher newDKVC) {
		DieuKienVoucherDAL.getInstance().Insert(newDKVC);
	}
	
	public void updateDKVC(DieuKienVoucher dkvc) {
		DieuKienVoucherDAL.getInstance().Update(dkvc);
	}
	
	public Float getPhanTramGiam(int dkvcID) {
		return DieuKienVoucherDAL.getInstance().getPhanTramGiam(dkvcID);
	}
	
	public ArrayList<DieuKienVoucher> getAllDieuKienVoucherChuaApDungOfKhachHang(float currentDiemTichLuy, int maKH){
		return DieuKienVoucherDAL.getInstance().getAllDieuKienVoucherChuaApDungOfKhachHang(currentDiemTichLuy, maKH);
	}
}
