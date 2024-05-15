package DTO;

public class HoaDon {
	private int maHoaDon;
	private String ngayTao;
	private float tongTien;
	private int maNhanVien;
	private Integer maKhach;
	private int maBan;
	private String tinhTrang;
	
	public HoaDon(int maHoaDon, String ngayTao, float tongTien, int maNhanVien, Integer maKhach, int maBan,
			String tinhTrang) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayTao = ngayTao;
		this.tongTien = tongTien;
		this.maNhanVien = maNhanVien;
		this.maKhach = maKhach;
		this.maBan = maBan;
		this.tinhTrang = tinhTrang;
	}

	public int getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	public float getTongTien() {
		return tongTien;
	}

	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}

	public int getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public Integer getMaKhach() {
		return maKhach;
	}

	public void setMaKhach(Integer maKhach) {
		this.maKhach = maKhach;
	}

	public int getMaBan() {
		return maBan;
	}

	public void setMaBan(int maBan) {
		this.maBan = maBan;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}	
}
