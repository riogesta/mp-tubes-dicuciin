package com.rgsvt.dicuciin;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dicuciindb.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table cucisepatu(no_cucisepatu integer primary key AUTOINCREMENT, nama_pelanggan text null, jenis_cuci text null, jenis_kategori text null, harga_kg text null, berat text null, total_harga text null, bayar text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO cucisepatu(nama_pelanggan, jenis_cuci, jenis_kategori, harga_kg, berat, total_harga, bayar) VALUES ('Narji', 'kain', 'basah','10000','1kg','10000','10000');";
        db.execSQL(sql);

        // add table cucibaju
        String sqlbaju = "create table cucibaju(no_cucibaju integer primary key AUTOINCREMENT, nama_pelanggan text null, jenis_cuci text null, jenis_kategori text null, harga_kg text null, berat text null, total_harga text null, bayar text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sqlbaju);
        sqlbaju = "INSERT INTO cucibaju(nama_pelanggan, jenis_cuci, jenis_kategori, harga_kg, berat, total_harga, bayar) VALUES ('NarNar', 'kain', 'basah','10000','1kg','10000','10000');";
        db.execSQL(sqlbaju);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}
