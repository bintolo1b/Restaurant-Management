package BLL;

import java.util.ArrayList;

import DTO.TaiKhoan;
import DAL.TaiKhoanDAL;

public class TaiKhoanBLL {
	private static TaiKhoanBLL instance;
	
	private TaiKhoanBLL() {
	}
	
	public static TaiKhoanBLL getInstance(){
		if (instance==null)
			instance = new TaiKhoanBLL();
		return instance;
	}
	
	public void updateTaiKhoan(TaiKhoan object) {
		TaiKhoanDAL.getInstance().Update(object);
	}
	
	public TaiKhoan getTaiKhoan(String taiKhoan, String matKhau) {
		return TaiKhoanDAL.getInstance().getTaiKhoan(taiKhoan, matKhau);
	}
	
	public void AddNewTaiKhoan(TaiKhoan tk) {
		TaiKhoanDAL.getInstance().Insert(tk);
	}

	public void DeleteTaiKhoan(TaiKhoan tk) {
		TaiKhoanDAL.getInstance().Delete(tk);
	}
	public ArrayList<TaiKhoan> getAllTaiKhoan(){
		return TaiKhoanDAL.getInstance().getAll();
	}
	
	public TaiKhoan getTaiKhoanByUserName(String tenTK) {
		return TaiKhoanDAL.getInstance().selectByTK(tenTK);
	}
	
	
}
