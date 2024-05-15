package DTO;

public class SanPham {
	protected int maSanPham;
	protected String ten;
	protected float gia;
	protected String trangThai;
	protected int maDanhMuc;
	
	public SanPham(int maSanPham, String ten, float gia, String trangThai, int maDanhMuc) {
		super();
		this.maSanPham = maSanPham;
		this.ten = ten;
		this.gia = gia;
		this.trangThai = trangThai;
		this.maDanhMuc = maDanhMuc;
	}

	public int getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(int maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public float getGia() {
		return gia;
	}

	public void setGia(float gia) {
		this.gia = gia;
	}

	public int getMaDanhMuc() {
		return maDanhMuc;
	}

	public void setMaDanhMuc(int maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	
}
