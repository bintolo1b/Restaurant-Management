package DTO;

public class TaiKhoan {
	private String taiKhoan;
	private String matKhau;
	private String loaiNguoiDung;
	
	public TaiKhoan(String taiKhoan, String matKhau, String loaiNguoiDung) {
		super();
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.loaiNguoiDung = loaiNguoiDung;
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getLoaiNguoiDung() {
		return loaiNguoiDung;
	}

	public void setLoaiNguoiDung(String loaiNguoiDung) {
		this.loaiNguoiDung = loaiNguoiDung;
	}
}
