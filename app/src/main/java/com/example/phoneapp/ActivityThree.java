package com.example.phoneapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityThree extends Activity implements View.OnClickListener {
    final String LOG_TAG = "myLogs";
    TextView text_view_email;
    TextView text_view_name;
    TextView text_read_row;

    EditText edit_text_email;
    EditText edit_text_name;

    Button button_read;
    Button button_add;
    Button button_clear;

    DBHelper dbHelper;

    StringBuffer inputText = new StringBuffer();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        text_view_email = findViewById(R.id.textViewEmail);
        text_view_name = findViewById(R.id.textViewName);
        text_read_row = findViewById(R.id.ShowRows);

        edit_text_email = findViewById(R.id.editTextEmail);
        edit_text_name = findViewById(R.id.editTextName);

        button_read = findViewById(R.id.buttonRead);
        button_read.setOnClickListener(this);

        button_add = findViewById(R.id.buttonAdd);
        button_add.setOnClickListener(this);

        button_clear = findViewById(R.id.buttonClear);
        button_clear.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues content_values = new ContentValues();

        String name = edit_text_name.getText().toString();
        String email = edit_text_email.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (v.getId()){
            case R.id.buttonAdd:
                Log.d(LOG_TAG,"---Insert to table mytable ---");
            content_values.put("name",name);
            content_values.put("email",email);

            long rowID = db.insert("mytable",null,content_values);
                inputText.append("ID = " + rowID + " " + content_values + System.getProperty ("line.separator"));
                text_read_row.setText(inputText);
            Log.d(LOG_TAG,"row inserted, ID =" + rowID);
            break;
            case R.id.buttonRead:
            Log.d(LOG_TAG,"--- Rows in table ---");
                Cursor cursor = db.query("mytable",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    int idColIndex = cursor.getColumnIndex("id");
                    int nameColIndex = cursor.getColumnIndex("name");
                    int emailColIndex = cursor.getColumnIndex("email");
                    do{
                        Log.d(LOG_TAG,"ID = " + cursor.getInt(idColIndex) +
                                ",Name = " + cursor.getString(nameColIndex) +
                                ",Email = " + cursor.getString(emailColIndex));
                    }while (cursor.moveToNext());
                }else Log.d(LOG_TAG,"0 rows");
                cursor.close();
            break;
            case R.id.buttonClear:
                Log.d(LOG_TAG,"--- Clear all table ---");
                int clearCount = db.delete("mytable",null,null);
                break;
        }
    dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "MyDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate DataBase ---");
            db.execSQL("create table mytable (" +
                    "id integer primary key autoincrement," +
                    "name text," +
                    "email text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}