package BLL;

import java.util.ArrayList;
import DAL.VoucherDAL;
import DTO.DieuKienVoucher;
import DTO.Voucher;

public class VoucherBLL {
	private static VoucherBLL instance;
	
	private VoucherBLL() {
	}
	
	public static VoucherBLL getInStance() {
		if (instance==null)
			instance = new VoucherBLL();
		return instance;
	}
	
	public Voucher getVoucherByID(int maVoucher) {
		return VoucherDAL.getInstance().getVoucherByID(maVoucher);
	}
	
	public int generateNewID() {
		return VoucherDAL.getInstance().getLatestID()+1;
	}
	
	public void updateVoucher(Voucher newVoucher) {
		VoucherDAL.getInstance().Update(newVoucher);
	}
	
	public void addVoucher(Voucher newVoucher) {
		VoucherDAL.getInstance().Insert(newVoucher);
	}
	
	
	public ArrayList<Voucher> gettAllVoucherChuaSuDung(int IDKhach){
		return VoucherDAL.getInstance().gettAllVoucherChuaSuDung(IDKhach);
	}
	
	public void generateNewVoucherForKhachHang(int maKH, float currentDiemTichLuy) {
		ArrayList<DieuKienVoucher> dkvcArrayList = DieuKienVoucherBLL.getInstance().getAllDieuKienVoucherChuaApDungOfKhachHang(currentDiemTichLuy, maKH);
		for (int i = 0 ; i<dkvcArrayList.size() ; i++) {
			int newID =  generateNewID();
			Voucher newVoucher = new Voucher(newID, "Chưa sử dụng", maKH, dkvcArrayList.get(i).getMaDieuKien());
			addVoucher(newVoucher);
		}
			
	}
}
