package BLL;

import java.util.ArrayList;

import DAL.SuDungVoucherDAL;
import DTO.SuDungVoucher;

public class SuDungVoucherBLL {
	private static SuDungVoucherBLL instance;
	
	private SuDungVoucherBLL() {
	}
	
	public static SuDungVoucherBLL getInStance() {
		if (instance==null)
			instance = new SuDungVoucherBLL();
		return instance;
	}
	
	public void addNewSuDungVoucher(SuDungVoucher newSDVC) {
		SuDungVoucherDAL.getInstance().Insert(newSDVC);
	}

	public ArrayList<SuDungVoucher> getAllSuDungVoucher(int maHoaDon) {
		return SuDungVoucherDAL.getInstance().getAllSuDungVoucher(maHoaDon);
	}
	
}
