package com.example.androidlayout_asigment;

public class CaiDat {
	String khoanThuChi, loaiThuChi;
	int id;

	public CaiDat(int Id, String khoanThuChi, String loaiThuChi) {
		super();
		this.khoanThuChi = khoanThuChi;
		this.loaiThuChi = loaiThuChi;
		this.id = Id;
	}

	public String getKhoanThuChi() {
		return khoanThuChi;
	}

	public void setKhoanThuChi(String khoanThuChi) {
		this.khoanThuChi = khoanThuChi;
	}

	public String getLoaiThuChi() {
		return loaiThuChi;
	}

	public void setLoaiThuChi(String loaiThuChi) {
		this.loaiThuChi = loaiThuChi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
