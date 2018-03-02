package com.example.androidlayout_asigment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	final String DATABASE_NAME = "QuanLyThuChi.sqlite";
	SQLiteDatabase Database;
	
	//Khoi tao cho phan tab thu chi
	float tienluong,tietkiem,tindung,sodu;
	ListView lvThuChi;
	ArrayList<ThuChi> listThuChi;
	AdapterThuChi adpThuChi;
	Button btnThemGiaoDich, btnGioiThieu;
	
	//Khoi tao thanh phan cho tab thong ke
	ListView lvThu, lvChi;
	ArrayList<ThongKe> listThongKe;
	AdapterThongKe adpThongKe;
	
	//Khoi tao thanh phan cho tab cai dat 
	ListView lvCaiDat;
	ArrayList<CaiDat> listCaiDat;
	AdapterCaiDat adpCaiDat;
	ImageButton btnThemMoi;
	EditText edtKhoanThuChi;
	Spinner spnLoaiThuChi;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost();
        readDataThuchi();
        readDataToSpinner();
        readDataThu();
        readDataChi();
        readDataToCaiDat();
        loadDataToSpinnerCaiDat();
        themMoiKhoanThuChi();
    }
    //menthod de ket noi cac activity vao trong main activity
    private void TabHost() {
		// TODO Auto-generated method stub
    	 TabHost tabs = (TabHost)findViewById(R.id.tabhost);
    	 tabs.setup();
    	//Khoi tao tab thu chi
    	 TabHost.TabSpec spec;
    	 spec = tabs.newTabSpec("Thuchi");
    	 spec.setContent(R.id.tabThuchi);
    	 spec.setIndicator("Thu Chi");
    	 tabs.addTab(spec);
    	 
    	 spec = tabs.newTabSpec("Thongke");
    	 spec.setContent(R.id.tabThongke);
    	 spec.setIndicator("Thống Kê");
    	 tabs.addTab(spec);
    	 
    	 spec = tabs.newTabSpec("Caidat");
    	 spec.setContent(R.id.tabCaidat);
    	 spec.setIndicator("Cài Đặt");
    	 tabs.addTab(spec);
    	 tabs.setCurrentTab(0);
	}
    
    private void readDataThuchi() {
		// TODO Auto-generated method stub
    	//anh xa 
		lvThuChi = (ListView)findViewById(R.id.lvThuChi);
		btnThemGiaoDich = (Button)findViewById(R.id.btnThemgiaodich);
		btnGioiThieu = (Button) findViewById(R.id.btnGioiThieu);
		//khoi tao ArrayList
		listThuChi = new ArrayList<ThuChi>();
		//Khoi tao Adapter
		adpThuChi = new AdapterThuChi(this, listThuChi);
		//set Adapter vao ListView
		lvThuChi.setAdapter(adpThuChi);
		
		//Doc du lieu tu Database
		Database = database.initDatabase(this, DATABASE_NAME);
		Cursor cursor = Database.rawQuery("SELECT * FROM TaiKhoan", null);
		listThuChi.clear();
		//dung vong lap quet toan bo bang ThuChi
		for(int i = 0; i<cursor.getCount();i++){
			cursor.moveToPosition(i);
			int id = cursor.getInt(0);
			String tentk = cursor.getString(1);
			float sotien = cursor.getFloat(2);
			//them mot doi tuong vua doc duoc vao ListView
			listThuChi.add(new ThuChi(id, tentk, sotien));
		}
		//den dong dau tien trong bang de lay ra tien luong
		for(int i = 0; i<cursor.getCount();i++){
			cursor.moveToPosition(0);
			tienluong = cursor.getFloat(2);
		}
		//di den dong thu 2 trong bang de lay ra tien tiet kiem
		for(int i = 0; i<cursor.getCount();i++){
			cursor.moveToPosition(1);
			tietkiem = cursor.getFloat(2);
		}
		//di den dong thu 3 trong bang de lay ra tien trong the tin dung
		for(int i = 0; i<cursor.getCount();i++){
			cursor.moveToPosition(2);
			tindung = cursor.getFloat(2);
		}
		//tinh so du
		sodu = tienluong+tietkiem+tindung;
		//gan du lieu len textview
		TextView tvSoDu = (TextView)findViewById(R.id.tvSoDu);
		tvSoDu.setText(String.valueOf(sodu));
		//Cap nhat ListView
		adpThuChi.notifyDataSetChanged();
		
		btnThemGiaoDich.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, ThemGiaoDichActivity.class);
				startActivity(intent);
			}
		});
		
		btnGioiThieu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(MainActivity.this);
				dialog.setTitle("Giới Thiệu");
				dialog.setContentView(R.layout.dialog_gioi_thieu);
				dialog.show();
			}
		});
	}
    
    //menthod doc du lieu tu database len tab thong ke
    private void readDataThu() {
		// TODO Auto-generated method stub
		lvThu = (ListView)findViewById(R.id.lvThu);
		listThongKe = new ArrayList<ThongKe>();
		adpThongKe = new AdapterThongKe(this, listThongKe);
		lvThu.setAdapter(adpThongKe);
		
		Database = database.initDatabase(this, DATABASE_NAME);
		Cursor cursor = Database.rawQuery("SELECT * FROM GiaoDich WHERE LoaiGiaoDich = 'Khoản Thu'", null);
		listThongKe.clear();
		for(int i = 0; i <cursor.getCount(); i++){
			cursor.moveToPosition(i);
			
			listThongKe.add(new ThongKe(cursor.getString(5),cursor.getFloat(3)));
		}
		adpThongKe.notifyDataSetChanged();
	}
    
    //menthod doc du lieu tu database len tab thu chi
    private void readDataChi() {
		// TODO Auto-generated method stub
		lvChi = (ListView)findViewById(R.id.lvChi);
		listThongKe = new ArrayList<ThongKe>();
		adpThongKe = new AdapterThongKe(this, listThongKe);
		lvChi.setAdapter(adpThongKe);
		
		Database = database.initDatabase(this, DATABASE_NAME);
		Cursor cursor = Database.rawQuery("SELECT * FROM GiaoDich WHERE LoaiGiaoDich = 'Khoản Chi'", null);
		listThongKe.clear();
		for(int i = 0; i <cursor.getCount(); i++){
			cursor.moveToPosition(i);
			listThongKe.add(new ThongKe(cursor.getString(5), cursor.getFloat(3)));
		}
		adpThongKe.notifyDataSetChanged();
	}
	
    //menthod dua du lieu tu ArrayList vao Spinner
    private void readDataToSpinner(){
    	Spinner spin = (Spinner) findViewById(R.id.spinnerThongKe);
    	//Tạo ra mảng dữ liệu đưa vào adpater
    	String arr[] = {
    			"Hôm nay",
    			"Tuần này",
    			"Tháng này",
    			"Năm nay"
    	};
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arr);
    	adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
    	spin.setAdapter(adapter);
    }
    
    private void readDataToCaiDat(){
    	lvCaiDat = (ListView)findViewById(R.id.lvPhannhom);
    	listCaiDat = new ArrayList<CaiDat>();
    	adpCaiDat = new AdapterCaiDat(this, listCaiDat);
    	lvCaiDat.setAdapter(adpCaiDat);
    	
    	Database = database.initDatabase(this, DATABASE_NAME);
    	Cursor cursor = Database.rawQuery("SELECT * FROM KhoanThuChi", null);
    	listCaiDat.clear();
    	
    	for(int i = 0; i< cursor.getCount(); i++){
    		cursor.moveToPosition(i);
    		listCaiDat.add(new CaiDat(cursor.getInt(0), cursor.getString(2), cursor.getString(1)));
    	}
    	adpCaiDat.notifyDataSetChanged();
    	registerForContextMenu(lvCaiDat);
    }
    
    private void loadDataToSpinnerCaiDat(){
    	final Spinner spin = (Spinner) findViewById(R.id.spPhanNhom);
    	//Tạo ra mảng dữ liệu đưa vào adpater
    	String arr[] = {
    			"Sắp Xếp",
    			"Khoản Thu",
    			"Khoản Chi"
    	};
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arr);
    	adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
    	spin.setAdapter(adapter);
    	spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String str = spin.getSelectedItem().toString();
				if(str.equalsIgnoreCase("Khoản Thu")){
					Database = database.initDatabase(MainActivity.this, DATABASE_NAME);
			    	Cursor cursor = Database.rawQuery("SELECT * FROM KhoanThuChi WHERE KhoanThuChi = 'Khoản Thu'", null);
			    	listCaiDat.clear();
			    	
			    	for(int i = 0; i< cursor.getCount(); i++){
			    		cursor.moveToPosition(i);
			    		listCaiDat.add(new CaiDat(cursor.getInt(0), cursor.getString(2), cursor.getString(1)));
			    	}
			    	adpCaiDat.notifyDataSetChanged();
			    	
				}else if(str.equalsIgnoreCase("Khoản Chi")){
					Database = database.initDatabase(MainActivity.this, DATABASE_NAME);
			    	Cursor cursor = Database.rawQuery("SELECT * FROM KhoanThuChi WHERE KhoanThuChi = 'Khoản Chi'", null);
			    	listCaiDat.clear();
			    	
			    	for(int i = 0; i< cursor.getCount(); i++){
			    		cursor.moveToPosition(i);
			    		listCaiDat.add(new CaiDat(cursor.getInt(0), cursor.getString(2), cursor.getString(1)));
			    	}
			    	adpCaiDat.notifyDataSetChanged();
				}else{
					readDataToCaiDat();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    }    
    private void themMoiKhoanThuChi(){
    	btnThemMoi = (ImageButton)findViewById(R.id.ibtnThemkhoan);
    	btnThemMoi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialogThemMoi();
			}
		});
    }
    
    private void showDialogThemMoi(){
    	
    	final Dialog dialog = new Dialog(this);
    	dialog.setTitle("Thêm Mới Khoản Thu Chi");
    	dialog.setContentView(R.layout.dialog_them_moi_khoan_thu_chi);
    	
    	edtKhoanThuChi = (EditText)dialog.findViewById(R.id.edtKhoanThuChi);
    	spnLoaiThuChi = (Spinner)dialog.findViewById(R.id.spinnerLoaiThuChi);
    	//edtSoTien = (EditText)dialog.findViewById(R.id.edtSoTien);
    	Button btnThemMoi = (Button)dialog.findViewById(R.id.btnThemMoi);
    	Button btnHuy = (Button)dialog.findViewById(R.id.btnHuyThemMoi);
    	
    	//Tạo ra mảng dữ liệu đưa vào adpater
    	String arr[] = {
    			"---Loại thu chi---",
    			"Khoản Thu",
    			"Khoản Chi"
    	};
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arr);
    	adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
    	spnLoaiThuChi.setAdapter(adapter);
    	
    	
    	btnThemMoi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				if(!validateDialog()){
					Toast toast = Toast.makeText(MainActivity.this, "Bạn chưa nhập đầy đủ thông tin!", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				}else{
					ContentValues contentValues = new ContentValues();
					
					contentValues.put("KhoanThuChi", spnLoaiThuChi.getSelectedItem().toString());
					contentValues.put("LoaiThuChi", edtKhoanThuChi.getText().toString());
					
					//contentValues.put("SoTien", edtSoTien.getText().toString());
					
					//thêm dữ liệu vào database
					SQLiteDatabase Database = database.initDatabase(MainActivity.this, DATABASE_NAME);
					Database.insert("KhoanThuChi", null, contentValues);
					Toast.makeText(MainActivity.this, "Thêm mới thành công!", Toast.LENGTH_SHORT).show();

					readDataToCaiDat();
					
			    	dialog.dismiss();
				}
			}
		});
    	btnHuy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
    	dialog.show();
    }
    
    private boolean validateDialog(){
    	if(edtKhoanThuChi.getText().toString().isEmpty()){
    		return false;
    	}
    	return true;
    }
    
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.context_menu_cai_dat, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
		CaiDat caidat = listCaiDat.get(info.position);
		int idCaiDat = caidat.getId();
		
		switch (id) {
		case R.id.action_delete:
			deleteItemCaiDat(idCaiDat);
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	private void deleteItemCaiDat(final int id){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Câu Hỏi");
		builder.setMessage("Bạn chắc chắn, bạn muốn xóa?");
		builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		
		builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Database = database.initDatabase(MainActivity.this, DATABASE_NAME);
				Database.delete("KhoanThuChi", "ID = ?", new String[]{id+""});
				
				//readDataChi();
				//readDataThu();
				readDataToCaiDat();
				
				Toast.makeText(MainActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
			}
		});
		builder.setIcon(R.drawable.icon_question);
		builder.create().show();
	}
}
