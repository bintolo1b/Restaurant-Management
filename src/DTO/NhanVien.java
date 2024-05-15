	package DTO;

public class NhanVien extends Nguoi {
	private int maNhanVien;
	private String taiKhoan;
	private String trangThai;
	
	public NhanVien(int maNhanVien, String ho, String tenDem, String ten,String ngaySinh, String soDienThoai, String diaChi, int gioiTinh, String taiKhoan, String trangThai) {
		super(ho, tenDem, ten, soDienThoai, diaChi, gioiTinh, ngaySinh);
		this.maNhanVien = maNhanVien;
		this.taiKhoan = taiKhoan;
		this.trangThai = trangThai;
	}

	public int getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
}
