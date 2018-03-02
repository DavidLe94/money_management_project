package com.example.androidlayout_asigment;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterThuChi extends BaseAdapter{
	Activity context;
	ArrayList<ThuChi> list;
	
	public AdapterThuChi(Activity context, ArrayList<ThuChi> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.thu_chi_one_row, null);
		//anh xa tu aactivity thu_chi_one_row
		TextView tvTenTaiKhoan = (TextView)row.findViewById(R.id.tvTenTaiKhoan);
		TextView tvSoTien = (TextView)row.findViewById(R.id.tvSoTien);
		//lay vi tri cua doi tuong thuchi trong database
		ThuChi thuchi = list.get(position);
		//hien thi du lieu len textview
		tvTenTaiKhoan.setText(thuchi.getTentaikhoan());
		tvSoTien.setText(String.valueOf(thuchi.getSotien()));
		
		return row;
	}

}
