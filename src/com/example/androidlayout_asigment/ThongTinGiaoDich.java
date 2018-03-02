package com.example.androidlayout_asigment;

public class ThongTinGiaoDich {
	private String ngayGD, gioGD, taiKhoan, phanNhom;
	private float soTienGD;
	
	public ThongTinGiaoDich(String ngayGD, String gioGD, String taiKhoan,
			String phanNhom, float soTienGD) {
		super();
		this.ngayGD = ngayGD;
		this.gioGD = gioGD;
		this.taiKhoan = taiKhoan;
		this.phanNhom = phanNhom;
		this.soTienGD = soTienGD;
	}

	public String getNgayGD() {
		return ngayGD;
	}

	public void setNgayGD(String ngayGD) {
		this.ngayGD = ngayGD;
	}

	public String getGioGD() {
		return gioGD;
	}

	public void setGioGD(String gioGD) {
		this.gioGD = gioGD;
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getPhanNhom() {
		return phanNhom;
	}

	public void setPhanNhom(String phanNhom) {
		this.phanNhom = phanNhom;
	}

	public float getSoTienGD() {
		return soTienGD;
	}

	public void setSoTienGD(float soTienGD) {
		this.soTienGD = soTienGD;
	}
	
	
	
}
