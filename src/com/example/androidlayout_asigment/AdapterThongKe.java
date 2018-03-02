package com.example.androidlayout_asigment;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterThongKe extends BaseAdapter{
	Activity context;
	ArrayList<ThongKe> list;
	
	public AdapterThongKe(Activity context, ArrayList<ThongKe> list) {
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
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.thong_ke_one_row, null);
		TextView tvKhoanThuChi = (TextView)row.findViewById(R.id.tvKhoanThuChi);
		TextView tvSoTienGiaoDich = (TextView)row.findViewById(R.id.tvSoTienGiaoDich);
		ThongKe thongke = list.get(position);
		tvKhoanThuChi.setText(thongke.getPhanNhom());
		tvSoTienGiaoDich.setText(String.valueOf(thongke.getSoTienGiaoDich()));
		return row;
	}

}
