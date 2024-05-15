package DTO;

public class QuanLy extends Nguoi {
	private String taiKhoan;
	public QuanLy(String ho, String tenDem, String ten, String ngaySinh, int gioiTinh, String diaChi,String soDienThoai, String taiKhoan) {
		super(ho, tenDem, ten, soDienThoai, diaChi, gioiTinh, ngaySinh);
		// TODO Auto-generated constructor stub
		this.taiKhoan = taiKhoan;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	
}
