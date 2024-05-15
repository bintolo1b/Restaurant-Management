package DTO;

public class BanAn {
	private int maBan;
	private int soGhe;
	private String trangThai;
	
	public BanAn(int maBan, int soGhe, String trangThai) {
		super();
		this.maBan = maBan;
		this.soGhe = soGhe;
		this.trangThai = trangThai;
	}

	public int getMaBan() {
		return maBan;
	}

	public void setMaBan(int maBan) {
		this.maBan = maBan;
	}

	public int getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	public String toString()
	{
		return String.valueOf(this.maBan);
	}
	
	
}
