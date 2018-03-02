package com.example.androidlayout_asigment;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterCaiDat extends BaseAdapter{
	
	Context context;
	ArrayList<CaiDat> list;
	
	
	public AdapterCaiDat(Context context, ArrayList<CaiDat> list) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = layoutInflater.inflate(R.layout.cai_dat_one_row, null);
		
		TextView tvKhoanThuChi = (TextView)row.findViewById(R.id.tvKhoanThuChi);
		TextView tvLoaiThuChi = (TextView)row.findViewById(R.id.tvLoaiThuChi);
		
		CaiDat caidat = list.get(position);
		
		tvKhoanThuChi.setText(caidat.getKhoanThuChi().toString());
		tvLoaiThuChi.setText(caidat.getLoaiThuChi().toString());
		return row;
	}

}
