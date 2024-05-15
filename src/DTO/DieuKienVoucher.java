package DTO;

public class DieuKienVoucher {
	private int maDieuKien;
	private float diemYeuCau;
	private float phanTram;
	private String tinhTrang;

	public DieuKienVoucher(int maDieuKien, float diemYeuCau, float phanTram, String tinhTrang) {
		super();
		this.maDieuKien = maDieuKien;
		this.diemYeuCau = diemYeuCau;
		this.phanTram = phanTram;
		this.tinhTrang = tinhTrang;
	}

	public int getMaDieuKien() {
		return maDieuKien;
	}

	public void setMaDieuKien(int maDieuKien) {
		this.maDieuKien = maDieuKien;
	}

	public float getDiemYeuCau() {
		return diemYeuCau;
	}

	public void setDiemYeuCau(float diemYeuCau) {
		this.diemYeuCau = diemYeuCau;
	}

	public float getPhanTram() {
		return phanTram;
	}

	public void setPhanTram(float phanTram) {
		this.phanTram = phanTram;
	}
	
	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
}
