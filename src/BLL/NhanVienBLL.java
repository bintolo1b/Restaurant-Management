package BLL;

import java.util.ArrayList;

import DAL.NhanVienDAL;
import DTO.NhanVien;

public class NhanVienBLL {
	private static NhanVienBLL instance;
	
	private NhanVienBLL() {	
	}
	
	public static NhanVienBLL getInstance() {
		if (instance==null)
			instance = new NhanVienBLL();
		return instance;
	}
	
	public NhanVien getNhanVienByID(int ID) {
		return NhanVienDAL.getInstance().getNhanVienByID(ID);
	}	
	
	public NhanVien getNhanVienByTaiKhoan(String taiKhoan) {
		return NhanVienDAL.getInstance().getNhanVienByTaiKhoan(taiKhoan);
	}
	
	public int GenarateID() {
		return NhanVienDAL.getInstance().GenerationNewID();
	}
	public void AddNewNhanVien(NhanVien nv) {
		NhanVienDAL.getInstance().Insert(nv);
	}
	public void UpdateNhanVien(NhanVien nv) {
		NhanVienDAL.getInstance().Update(nv);
	}
	public void DeleteNhanVien(NhanVien nv) {
		NhanVienDAL.getInstance().Delete(nv);
	}
	
	public ArrayList<NhanVien> getAllNhanVien(){
		return NhanVienDAL.getInstance().getAll();
	}
}
