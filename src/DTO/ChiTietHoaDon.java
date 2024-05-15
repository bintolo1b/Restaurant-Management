package DTO;

public class ChiTietHoaDon {
	private int maHoaDon;
	private int maSanPham;
	private float giaSPTaiThoiDiem;
	private String tenSPTaiThoiDiem;
	private int soLuong;
	private int lanGoi;
	private String tinhTrangPhucVu;
	
	public ChiTietHoaDon(int maHoaDon, int maSanPham, float giaSPTaiThoiDiem, String tenSPTaiThoiDiem, int soLuong,
			int lanGoi, String tinhTrangPhucVu) {
		super();
		this.maHoaDon = maHoaDon;
		this.maSanPham = maSanPham;
		this.giaSPTaiThoiDiem = giaSPTaiThoiDiem;
		this.tenSPTaiThoiDiem = tenSPTaiThoiDiem;
		this.soLuong = soLuong;
		this.lanGoi = lanGoi;
		this.tinhTrangPhucVu = tinhTrangPhucVu;
	}

	public int getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public int getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(int maSanPham) {
		this.maSanPham = maSanPham;
	}

	public float getGiaSPTaiThoiDiem() {
		return giaSPTaiThoiDiem;
	}

	public void setGiaSPTaiThoiDiem(float giaSPTaiThoiDiem) {
		this.giaSPTaiThoiDiem = giaSPTaiThoiDiem;
	}

	public String getTenSPTaiThoiDiem() {
		return tenSPTaiThoiDiem;
	}

	public void setTenSPTaiThoiDiem(String tenSPTaiThoiDiem) {
		this.tenSPTaiThoiDiem = tenSPTaiThoiDiem;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getLanGoi() {
		return lanGoi;
	}

	public void setLanGoi(int lanGoi) {
		this.lanGoi = lanGoi;
	}

	public String getTinhTrangPhucVu() {
		return tinhTrangPhucVu;
	}

	public void setTinhTrangPhucVu(String tinhTrangPhucVu) {
		this.tinhTrangPhucVu = tinhTrangPhucVu;
	}
	
	
}
