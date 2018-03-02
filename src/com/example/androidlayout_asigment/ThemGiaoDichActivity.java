package com.example.androidlayout_asigment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class ThemGiaoDichActivity extends ActionBarActivity {
	final String DATABASE_NAME = "QuanLyThuChi.sqlite";
	SQLiteDatabase Database;
	Spinner spnThemTaiKhoan, spnThemLoaiGiaoDich, spnTrangThai, spnThemPhanNhom;
	EditText edtThemTienGiaoDich, edtLyDoGiaoDich, edtNgay, edtGio;
	Button btnChonNgay, btnChonGio, btnDong, btnDongVaLuu, btnXemGD;
	Calendar cal;
	Date date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_giao_dich);
		init();
		loadDataToSpinner();
		
		cal=Calendar.getInstance();
        SimpleDateFormat dft=null;
        //Định dạng kiểu ngày / tháng /năm
        dft=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate=dft.format(cal.getTime());
        //hiển thị lên giao diện
        edtNgay.setText(strDate);
        
        //Định dạng giờ phút am/pm
        dft = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String strTime = dft.format(cal.getTime());
        //đưa lên giao diện
        edtGio.setText(strTime);
        //lấy giờ theo 24 để lập trình theo Tag
        dft = new SimpleDateFormat("HH:mm", Locale.getDefault());
        edtGio.setTag(dft.format(cal.getTime()));
        
		ganSuKienChoButton();
		
		
	}
	
	private void init(){
		spnThemTaiKhoan = (Spinner)findViewById(R.id.spnThemTaiKhoan);
		spnThemLoaiGiaoDich = (Spinner)findViewById(R.id.spnThemLoaiGiaoDich);
		spnTrangThai = (Spinner)findViewById(R.id.spnTrangThai);
		spnThemPhanNhom = (Spinner)findViewById(R.id.spnThemPhanNhom);
		edtLyDoGiaoDich = (EditText)findViewById(R.id.edtLyDoGiaoDich);
		edtThemTienGiaoDich = (EditText)findViewById(R.id.edtThemTienGiaoDich);
		edtNgay = (EditText)findViewById(R.id.edtNgay);
		edtGio = (EditText)findViewById(R.id.edtGio);
		btnChonNgay = (Button)findViewById(R.id.btnChonNgay);
		btnChonGio = (Button)findViewById(R.id.btnChonGio);
		btnDong = (Button)findViewById(R.id.btnDong);
		btnDongVaLuu = (Button)findViewById(R.id.btnDongVaLuu);
		btnXemGD = (Button)findViewById(R.id.btnXemGD);
	}
	
	private void loadDataToSpinner(){
		
		//lay du lieu do vao Spinner them tai khoan
		ArrayList<String> listTaiKhoan = new ArrayList<String>();
		ArrayAdapter<String> adapterThemTaiKhoan = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listTaiKhoan);
		adapterThemTaiKhoan.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		
		Database = database.initDatabase(this, DATABASE_NAME);
		Cursor cursorThemTaiKhoan = Database.rawQuery("SELECT * FROM TaiKhoan", null);
		listTaiKhoan.clear();
		for(int i = 0; i < cursorThemTaiKhoan.getCount(); i++){
			cursorThemTaiKhoan.moveToPosition(i);
			listTaiKhoan.add(cursorThemTaiKhoan.getString(1));
		}
		spnThemTaiKhoan.setAdapter(adapterThemTaiKhoan);
		
		
		//lay du lieu do vao Spinner loai giao dịch
		String arr[] = {
    			"Khoản Thu",
    			"Khoản Chi"
    	};
		ArrayAdapter<String> adapterLoaiGiaoDich = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arr);
		adapterLoaiGiaoDich.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spnThemLoaiGiaoDich.setAdapter(adapterLoaiGiaoDich);
		
		
		//lay du lieu do vao Spinner phan nhom
		ArrayList<String> listPhanNhom = new ArrayList<String>();
		ArrayAdapter<String> adapterPhanNhom = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listPhanNhom);
		adapterPhanNhom.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		
		Database = database.initDatabase(this, DATABASE_NAME);
		Cursor cursorPhanNhom = Database.rawQuery("SELECT * FROM KhoanThuChi", null);
		listPhanNhom.clear();
		for(int i = 0; i < cursorPhanNhom.getCount(); i++){
			cursorPhanNhom.moveToPosition(i);
			listPhanNhom.add(cursorPhanNhom.getString(2));
		}
		spnThemPhanNhom.setAdapter(adapterPhanNhom);
		
		
		//lay du lieu do vao Spinner trang thai
		String listTrangThai[] = {
    			"Đã thanh toán",
    			"Chưa thanh toán"
    	};
		ArrayAdapter<String> adapterTrangThai = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listTrangThai);
		adapterTrangThai.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spnTrangThai.setAdapter(adapterTrangThai);
	}
	
	private void ganSuKienChoButton(){
		btnDong.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ThemGiaoDichActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
		
		btnChonNgay.setOnClickListener(showDatePicker);
		
		btnChonGio.setOnClickListener(showTimePicker);
		
		btnDongVaLuu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(edtThemTienGiaoDich.getText().toString().isEmpty() || 
						edtLyDoGiaoDich.getText().toString().isEmpty() || 
						edtNgay.getText().toString().isEmpty() || 
						edtGio.getText().toString().isEmpty()){
					Toast toast = Toast.makeText(ThemGiaoDichActivity.this, "Bạn chưa nhập đầy đủ thông tin!!", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					
				}else{
					themGiaoDich();
					Intent intent = new Intent(ThemGiaoDichActivity.this, ThongTinGiaoDichActivity.class);
					startActivity(intent);
				}
			}
		});
		
		btnXemGD.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ThemGiaoDichActivity.this, ThongTinGiaoDichActivity.class);
				startActivity(intent);
			}
		});
		
	}
	
	// Sự kiện khi click vào nút changedate
    View.OnClickListener showDatePicker = new View.OnClickListener(){
        
        @Override
        public void onClick(View view){
            DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                //Sự kiện khi click vào nút Done trên Dialog
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    // Set text cho EditText
                    edtNgay.setText(day + "/" + (month +1) + "/" + year);
                    //Lưu vết lại ngày mới cập nhật
                    cal.set(year, month, day);
                    date = cal.getTime();
                }
            };
            String s=edtNgay.getText()+"";
            //Lấy ra chuỗi của textView Date
            String strArrtmp[]=s.split("/");
            int ngay=Integer.parseInt(strArrtmp[0]);
            int thang=Integer.parseInt(strArrtmp[1]) - 1;
            int nam=Integer.parseInt(strArrtmp[2]);
            //Hiển thị ra Dialog
            DatePickerDialog pic=new DatePickerDialog(
                    ThemGiaoDichActivity.this,
                    callback, nam, thang, ngay);
            pic.setTitle("Chọn ngày giao dịch");
            pic.show();
        }
    };
    


    View.OnClickListener showTimePicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    //Xử lý lưu giờ và AM,PM
                    String s = hour + ":" + minute;
                    int hourTam = hour;
                    if (hourTam > 12)
                        hourTam = hourTam - 12;
                    edtGio.setText
                            (hourTam + ":" + minute + (hour > 12 ? " PM" : " AM"));
                    //lưu giờ thực vào tag
                    edtGio.setTag(s);
                    //lưu vết lại giờ
                    cal.set(Calendar.HOUR_OF_DAY, hour);
                    cal.set(Calendar.MINUTE, minute);
                    date = cal.getTime();
                }
            };
            String s = edtGio.getTag() + "";
            String strArr[] = s.split(":");
            int gio = Integer.parseInt(strArr[0]);
            int phut = Integer.parseInt(strArr[1]);
            TimePickerDialog time = new TimePickerDialog(
                    ThemGiaoDichActivity.this,
                    callback, gio, phut, true);
            time.setTitle("Chọn giờ giao dịch");
            time.show();
        }
    };
    
    
    private void themGiaoDich(){
    	double soTien = 0;
		double sum = 0;
		//Kiểm tra xem người dùng chọn loại khoản thu hay khoản chi
    	if(spnThemLoaiGiaoDich.getSelectedItem().toString().equalsIgnoreCase("Khoản Thu")){
    		//Kiểm tra xem ngừi dùng chọn tài khoản nào
    		if(spnThemTaiKhoan.getSelectedItem().toString().equalsIgnoreCase("Tiền Lương")){
    			//Lấy ra số tiền trong database
    			Database = database.initDatabase(this, DATABASE_NAME);
    			Cursor cursor = Database.rawQuery("SELECT * FROM TaiKhoan WHERE TenTaiKhoan = 'Tiền Lương'", null);
    			for(int i=0;i<cursor.getCount(); i++){
    				cursor.moveToPosition(i);
    				soTien = cursor.getDouble(2);
    			}
    			//cộng thêm số tiền trên editText và gán cho biến sum
    			double d = Double.parseDouble(edtThemTienGiaoDich.getText().toString());
    			sum = soTien+d;
    			//update lại database
    			ContentValues conVal = new ContentValues();
    			conVal.put("SoTien", sum);
    			Database = database.initDatabase(ThemGiaoDichActivity.this, DATABASE_NAME);
    	    	Database.update("TaiKhoan", conVal, "TenTaiKhoan = ?", new String[]{"Tiền Lương"});
    		}else if(spnThemTaiKhoan.getSelectedItem().toString().equalsIgnoreCase("Thẻ Tín Dụng")){
    			
    			Database = database.initDatabase(this, DATABASE_NAME);
    			Cursor cursor = Database.rawQuery("SELECT * FROM TaiKhoan WHERE TenTaiKhoan = 'Thẻ Tín Dụng'", null);
    			for(int i=0;i<cursor.getCount(); i++){
    				cursor.moveToPosition(i);
    				soTien = cursor.getDouble(2);
    			}
    			double d = Double.parseDouble(edtThemTienGiaoDich.getText().toString());
    			sum = soTien+d;
    			
    			ContentValues conVal = new ContentValues();
    			conVal.put("SoTien", sum);
    			Database = database.initDatabase(ThemGiaoDichActivity.this, DATABASE_NAME);
    	    	Database.update("TaiKhoan", conVal, "TenTaiKhoan = ?", new String[]{"Thẻ Tín Dụng"});
    	    	
    		}else if(spnThemTaiKhoan.getSelectedItem().toString().equalsIgnoreCase("Tiền Tiết Kiệm")){
    			
    			Database = database.initDatabase(this, DATABASE_NAME);
    			Cursor cursor = Database.rawQuery("SELECT * FROM TaiKhoan WHERE TenTaiKhoan = 'Tiền Tiết Kiệm'", null);
    			for(int i=0;i<cursor.getCount(); i++){
    				cursor.moveToPosition(i);
    				soTien = cursor.getDouble(2);
    			}
    			double d = Double.parseDouble(edtThemTienGiaoDich.getText().toString());
    			sum = soTien+d;
    			
    			ContentValues conVal = new ContentValues();
    			conVal.put("SoTien", sum);
    			Database = database.initDatabase(ThemGiaoDichActivity.this, DATABASE_NAME);
    	    	Database.update("TaiKhoan", conVal, "TenTaiKhoan = ?", new String[]{"Tiền Tiết Kiệm"});
    		}
    		
    	}else if(spnThemLoaiGiaoDich.getSelectedItem().toString().equalsIgnoreCase("Khoản Chi")){
    		
    		if(spnThemTaiKhoan.getSelectedItem().toString().equalsIgnoreCase("Tiền Lương")){
    			//Lấy ra số tiền trong database
    			Database = database.initDatabase(this, DATABASE_NAME);
    			Cursor cursor = Database.rawQuery("SELECT * FROM TaiKhoan WHERE TenTaiKhoan = 'Tiền Lương'", null);
    			for(int i=0;i<cursor.getCount(); i++){
    				cursor.moveToPosition(i);
    				soTien = cursor.getDouble(2);
    			}
    			//cộng thêm số tiền trên editText và gán cho biến sum
    			double d = Double.parseDouble(edtThemTienGiaoDich.getText().toString());
    			sum = soTien-d;
    			//update lại database
    			ContentValues conVal = new ContentValues();
    			conVal.put("SoTien", sum);
    			Database = database.initDatabase(ThemGiaoDichActivity.this, DATABASE_NAME);
    	    	Database.update("TaiKhoan", conVal, "TenTaiKhoan = ?", new String[]{"Tiền Lương"});
    		}else if(spnThemTaiKhoan.getSelectedItem().toString().equalsIgnoreCase("Thẻ Tín Dụng")){
    			
    			Database = database.initDatabase(this, DATABASE_NAME);
    			Cursor cursor = Database.rawQuery("SELECT * FROM TaiKhoan WHERE TenTaiKhoan = 'Thẻ Tín Dụng'", null);
    			for(int i=0;i<cursor.getCount(); i++){
    				cursor.moveToPosition(i);
    				soTien = cursor.getDouble(2);
    			}
    			double d = Double.parseDouble(edtThemTienGiaoDich.getText().toString());
    			sum = soTien-d;
    			
    			ContentValues conVal = new ContentValues();
    			conVal.put("SoTien", sum);
    			Database = database.initDatabase(ThemGiaoDichActivity.this, DATABASE_NAME);
    	    	Database.update("TaiKhoan", conVal, "TenTaiKhoan = ?", new String[]{"Thẻ Tín Dụng"});
    	    	
    		}else if(spnThemTaiKhoan.getSelectedItem().toString().equalsIgnoreCase("Tiền Tiết Kiệm")){
    			
    			Database = database.initDatabase(this, DATABASE_NAME);
    			Cursor cursor = Database.rawQuery("SELECT * FROM TaiKhoan WHERE TenTaiKhoan = 'Tiền Tiết Kiệm'", null);
    			for(int i=0;i<cursor.getCount(); i++){
    				cursor.moveToPosition(i);
    				soTien = cursor.getDouble(2);
    			}
    			double d = Double.parseDouble(edtThemTienGiaoDich.getText().toString());
    			sum = soTien-d;
    			
    			ContentValues conVal = new ContentValues();
    			conVal.put("SoTien", sum);
    			Database = database.initDatabase(ThemGiaoDichActivity.this, DATABASE_NAME);
    	    	Database.update("TaiKhoan", conVal, "TenTaiKhoan = ?", new String[]{"Tiền Tiết Kiệm"});
    		}
    	}
    	
    	//thêm dữ liệu vào bảng Thông tin giao dịch
    	ContentValues contentValues = new ContentValues();
    	
    	contentValues.put("TaiKhoan", spnThemTaiKhoan.getSelectedItem().toString());
    	contentValues.put("LoaiGiaoDich", spnThemLoaiGiaoDich.getSelectedItem().toString());
    	contentValues.put("SoTienGD", edtThemTienGiaoDich.getText().toString());
    	contentValues.put("LyDoGD", edtLyDoGiaoDich.getText().toString());
    	contentValues.put("PhanNhom", spnThemPhanNhom.getSelectedItem().toString());
    	contentValues.put("TrangThai", spnTrangThai.getSelectedItem().toString());
    	contentValues.put("NgayGD", edtNgay.getText().toString());
    	contentValues.put("GioGD", edtGio.getText().toString());
    	Database = database.initDatabase(ThemGiaoDichActivity.this, DATABASE_NAME);
    	Database.insert("GiaoDich", null, contentValues);
    	
    	Toast.makeText(this, "Thêm giao dịch thành công!!", Toast.LENGTH_SHORT).show();
    }
}
