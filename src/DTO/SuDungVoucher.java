package DTO;

public class SuDungVoucher {
	private int maHoaDon;
	private int maVoucher;
	private float phanTramGiam;
	
	public SuDungVoucher(int maHoaDon, int maVoucher, float phanTramGiam) {
		super();
		this.maHoaDon = maHoaDon;
		this.maVoucher = maVoucher;
		this.phanTramGiam = phanTramGiam;
	}

	public int getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public int getMaVoucher() {
		return maVoucher;
	}

	public void setMaVoucher(int maVoucher) {
		this.maVoucher = maVoucher;
	}

	public float getPhanTramGiam() {
		return phanTramGiam;
	}

	public void setPhanTramGiam(float phanTramGiam) {
		this.phanTramGiam = phanTramGiam;
	}
	
}
