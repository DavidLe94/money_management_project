package com.example.androidlayout_asigment;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterThongTinGD extends BaseAdapter{
	Context context;
	ArrayList<ThongTinGiaoDich> list;
	
	

	public AdapterThongTinGD(Context context, ArrayList<ThongTinGiaoDich> list) {
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
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.thong_tin_gd_one_row, null);
		
		TextView tvNgayGio = (TextView)row.findViewById(R.id.tvNgayGio);
		TextView tvSoTien = (TextView)row.findViewById(R.id.textViewSoTien);
		TextView tvPhanNhom = (TextView)row.findViewById(R.id.tvPhanNhom);
		TextView tvTaiKhoan = (TextView)row.findViewById(R.id.tvTaiKhoan);
		
		ThongTinGiaoDich ttgd = list.get(position);
		tvNgayGio.setText(ttgd.getNgayGD() + " " + ttgd.getGioGD());
		tvSoTien.setText(ttgd.getSoTienGD()+" ");
		tvPhanNhom.setText(ttgd.getPhanNhom());
		tvTaiKhoan.setText(ttgd.getTaiKhoan());
		
		return row;
	}

}
