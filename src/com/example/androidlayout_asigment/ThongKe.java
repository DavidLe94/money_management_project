package com.example.androidlayout_asigment;

public class ThongKe {
	private String phanNhom;
	private float soTienGiaoDich;
	
	
	public ThongKe(String phanNhom, float soTienGiaoDich) {
		super();
		this.phanNhom = phanNhom;
		this.soTienGiaoDich = soTienGiaoDich;
	}
	
	public String getPhanNhom() {
		return phanNhom;
	}
	public void setPhanNhom(String phanNhom) {
		this.phanNhom = phanNhom;
	}
	public float getSoTienGiaoDich() {
		return soTienGiaoDich;
	}
	public void setSoTienGiaoDich(float soTienGiaoDich) {
		this.soTienGiaoDich = soTienGiaoDich;
	}
	
	
}
