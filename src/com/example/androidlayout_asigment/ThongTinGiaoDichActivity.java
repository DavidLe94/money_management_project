package com.example.androidlayout_asigment;

import java.util.ArrayList;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class ThongTinGiaoDichActivity extends ActionBarActivity {
	ListView lvThongTienGD;
	ArrayList<ThongTinGiaoDich> list;
	AdapterThongTinGD adapter;
	String DATABASE_NAME = "QuanLyThuChi.sqlite";
	SQLiteDatabase Database;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_tin_giao_dich);
		lvThongTienGD = (ListView)findViewById(R.id.lvThongTinGD);
		// lấy ActionBar
		ActionBar actionBar = getActionBar();
		// hiển thị nút Up ở Home icon
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		//lay du lieu len ListView
		list = new ArrayList<ThongTinGiaoDich>();
		adapter = new AdapterThongTinGD(this, list);
		lvThongTienGD.setAdapter(adapter);
		
		Database = database.initDatabase(this, DATABASE_NAME);
		Cursor cursor = Database.rawQuery("SELECT * FROM GiaoDich", null);
		list.clear();
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			list.add(new ThongTinGiaoDich(cursor.getString(7), cursor.getString(8), cursor.getString(1), 
					cursor.getString(5), cursor.getFloat(3)));
		}
		adapter.notifyDataSetChanged();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thong_tin_giao_dich, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_themGD) {
			Intent intent = new Intent(ThongTinGiaoDichActivity.this, ThemGiaoDichActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
