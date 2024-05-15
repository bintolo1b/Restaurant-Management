package DTO;

public class Voucher {
	private int maVoucher;
	private String tinhTrangSuDung;
	private int maKhach;
	private int maDieuKien;
	
	public Voucher(int maVoucher, String tinhTrangSuDung, int maKhach, int maDieuKien) {
		super();
		this.maVoucher = maVoucher;
		this.tinhTrangSuDung = tinhTrangSuDung;
		this.maKhach = maKhach;
		this.maDieuKien = maDieuKien;
	}

	public int getMaVoucher() {
		return maVoucher;
	}

	public void setMaVoucher(int maVoucher) {
		this.maVoucher = maVoucher;
	}

	public String getTinhTrangSuDung() {
		return tinhTrangSuDung;
	}

	public void setTinhTrangSuDung(String tinhTrangSuDung) {
		this.tinhTrangSuDung = tinhTrangSuDung;
	}

	public int getMaKhach() {
		return maKhach;
	}

	public void setMaKhach(int maKhach) {
		this.maKhach = maKhach;
	}

	public int getMaDieuKien() {
		return maDieuKien;
	}

	public void setMaDieuKien(int maDieuKien) {
		this.maDieuKien = maDieuKien;
	}
	
		
}
