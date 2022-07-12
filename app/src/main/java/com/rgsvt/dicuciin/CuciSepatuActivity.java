package com.rgsvt.dicuciin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CuciSepatuActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button simpan;
    EditText nama_pelanggan, jenis_cuci, jenis_kategori, harga, berat, total, bayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuci_sepatu);

        dbHelper = new DataHelper(this);
        nama_pelanggan = (EditText) findViewById(R.id.etNamaPelanggan);
        jenis_cuci = (EditText) findViewById(R.id.etJenisCuci);
        jenis_kategori = (EditText) findViewById(R.id.etJenisKategori);
        harga = (EditText) findViewById(R.id.etHarga);
        berat = (EditText) findViewById(R.id.etBerat);
        total = (EditText) findViewById(R.id.etTotal);
        bayar = (EditText) findViewById(R.id.etBayar);

        simpan = (Button) findViewById(R.id.btnSimpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0){
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String sql = "insert into cucisepatu (nama_pelanggan, jenis_cuci, jenis_kategori, harga_kg, berat, total_harga, bayar) values ('"+nama_pelanggan.getText().toString()+"', '"+jenis_cuci.getText().toString()+"', '"+jenis_kategori.getText().toString()+"', '"+harga.getText().toString()+"', '"+berat.getText().toString()+"', '"+total.getText().toString()+"', '"+bayar.getText().toString()+"')";
                db.execSQL(sql);

                Log.d("insert data", "query: "+sql);

                Toast.makeText(CuciSepatuActivity.this, "Cucian sepatu tersimpan !", Toast.LENGTH_SHORT).show();

                new DataLaundryActivity().refreshList();
                finish();
            }
        });


    }
}