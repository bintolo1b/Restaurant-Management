package DTO;

public class ChiTietDatBan {
	private String ngayGioDat;
	private String ngayGioNhan_Huy;
	private int maBan;
	private String soDienThoaiKH;
	private int maNhanVien;
	private String trangThai;
	
	public ChiTietDatBan(String ngayGioDat, String ngayGioNhan_Huy, int maBan, String soDienThoaiKH, int maNhanVien,
			String trangThai) {
		super();
		this.ngayGioDat = ngayGioDat;
		this.ngayGioNhan_Huy = ngayGioNhan_Huy;
		this.maBan = maBan;
		this.soDienThoaiKH = soDienThoaiKH;
		this.maNhanVien = maNhanVien;
		this.trangThai = trangThai;
	}

	public String getNgayGioDat() {
		return ngayGioDat;
	}

	public void setNgayGioDat(String ngayGioDat) {
		this.ngayGioDat = ngayGioDat;
	}

	public String getNgayGioNhan_Huy() {
		return ngayGioNhan_Huy;
	}

	public void setNgayGioNhan_Huy(String ngayGioNhan_Huy) {
		this.ngayGioNhan_Huy = ngayGioNhan_Huy;
	}

	public int getMaBan() {
		return maBan;
	}

	public void setMaBan(int maBan) {
		this.maBan = maBan;
	}

	public String getSoDienThoaiKH() {
		return soDienThoaiKH;
	}

	public void setSoDienThoaiKH(String soDienThoaiKH) {
		this.soDienThoaiKH = soDienThoaiKH;
	}

	public int getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	
}
