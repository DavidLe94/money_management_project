package com.example.androidlayout_asigment;

public class ThuChi {
	private int id;
	private String tentaikhoan;
	private float sotien;
	
	public ThuChi(int id, String tentaikhoan, float sotien) {
		super();
		this.id = id;
		this.tentaikhoan = tentaikhoan;
		this.sotien = sotien;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTentaikhoan() {
		return tentaikhoan;
	}
	public void setTentaikhoan(String tentaikhoan) {
		this.tentaikhoan = tentaikhoan;
	}
	public float getSotien() {
		return sotien;
	}
	public void setSotien(float sotien) {
		this.sotien = sotien;
	}
	
	
}
