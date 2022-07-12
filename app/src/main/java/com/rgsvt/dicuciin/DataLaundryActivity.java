package com.rgsvt.dicuciin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DataLaundryActivity extends AppCompatActivity {

    String[] daftar, id;
    ListView listView;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static DataLaundryActivity dla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_laundry);

        dla = this;
        dbcenter = new DataHelper(this);
        refreshList();
    }

    public void refreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("select * from cucisepatu", null);
        daftar = new String[cursor.getCount()];
        id = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i=0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(1).toString();
            id[i] = cursor.getString(0).toString();
        }
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = id[arg2];
                //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat & Update Data", "Hapus Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DataLaundryActivity.this);
                builder.setTitle("Pilihan : "+daftar[arg2]);
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                //akan memanggil activity update data
                                Intent i = new Intent(getApplicationContext(), UpdateDataSepatuActivity.class);
                                i.putExtra("no_cucisepatu", selection);
                                startActivity(i);
                                break;
                            case 1:
                                //akan menghapus data
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from cucisepatu where no_cucisepatu='"+selection+"'");
                                Toast.makeText(DataLaundryActivity.this, "Data Cucian Terhapus.", Toast.LENGTH_SHORT).show();
                                refreshList();
                                break;

                        }
                    }
                });
                builder.create().show();
            }
        });
    }

}