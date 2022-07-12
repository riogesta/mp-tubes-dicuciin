package com.rgsvt.dicuciin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateDataSepatuActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnEdit;
    EditText text1, text2, text3, text4, text5;
    String edit;
    EditText nama_pelanggan, jenis_cuci, jenis_kategori, harga, berat, total, bayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_sepatu);

        dbHelper = new DataHelper(this);
        nama_pelanggan = (EditText) findViewById(R.id.etNamaPelanggan);
        jenis_cuci = (EditText) findViewById(R.id.etJenisCuci);
        jenis_kategori = (EditText) findViewById(R.id.etJenisKategori);
        harga = (EditText) findViewById(R.id.etHarga);
        berat = (EditText) findViewById(R.id.etBerat);
        total = (EditText) findViewById(R.id.etTotal);
        bayar = (EditText) findViewById(R.id.etBayar);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM cucisepatu WHERE no_cucisepatu='"+ getIntent().getStringExtra("no_cucisepatu") +"' LIMIT 1";
        cursor = db.rawQuery(sql, null);
        Log.d("data", "select cucisepatu = "+sql);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            nama_pelanggan.setText(cursor.getString(1).toString());
            jenis_cuci.setText(cursor.getString(2).toString());
            jenis_kategori.setText(cursor.getString(3).toString());
            harga.setText(cursor.getString(4).toString());
            berat.setText(cursor.getString(5).toString());
            total.setText(cursor.getString(6).toString());
            bayar.setText(cursor.getString(7).toString());

        }

        btnEdit = (Button) findViewById(R.id.btnSimpan);
        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                // TODO Auto-Generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                edit = nama_pelanggan.getText().toString();
                edit = jenis_cuci.getText().toString();
                edit = jenis_kategori.getText().toString();
                edit = harga.getText().toString();
                edit = berat.getText().toString();
                edit = total.getText().toString();
                edit = bayar.getText().toString();
                if (edit.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    String sql = "update cucisepatu set nama_pelanggan='" + nama_pelanggan.getText().toString() + "', jenis_cuci='" + jenis_cuci.getText().toString() + "', jenis_kategori='" + jenis_kategori.getText().toString() + "', harga_kg='" + harga.getText().toString() + "', berat='"+berat.getText().toString()+"', total_harga='"+total.getText().toString()+"', bayar='"+bayar.getText().toString()+"' where no_cucisepatu='"+getIntent().getStringExtra("no_cucisepatu")+"'";
                    db.execSQL(sql);
                    Toast.makeText(getApplicationContext(), "Data Cucian Terubah!", Toast.LENGTH_LONG).show();
                    finish();
                }
                DataLaundryActivity.dla.refreshList();
            }
        });

    }
}