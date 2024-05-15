package DTO;

public class KhachHang extends Nguoi {
	private int maKhach;
	private float diemTichLuy;
	public KhachHang(int maKhach, String soDienThoai, String ho, String tenDem, String ten, String diaChi, int gioiTinh,
			String ngaySinh, float diemTichLuy) {
		super(ho, tenDem, ten, soDienThoai, diaChi, gioiTinh, ngaySinh);
		this.maKhach = maKhach;
		this.diemTichLuy = diemTichLuy;
	}
	public int getMaKhach() {
		return maKhach;
	}
	public void setMaKhach(int maKhach) {
		this.maKhach = maKhach;
	}
	public float getDiemTichLuy() {
		return diemTichLuy;
	}
	public void setDiemTichLuy(float diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}
	
}
