package com.example.atm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class deleteFunctions extends AppCompatActivity {
    private System.LocalDatabaseHelper localDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_functions);
    }
    public deleteFunctions(){
        //localDatabaseHelper = new LocalDatabaseHelper(this);
    }
    public int delete(String uname) {
        SQLiteDatabase db = localDatabaseHelper.getWritableDatabase();
        String[] whereArgs = {uname};

       // int count = db.delete(localDatabaseHelper.TABLE_NAME, localDatabaseHelper.Balance + " = ?", whereArgs);
        return Integer.parseInt(null);
    }


    public void deleteAllRows() {
        SQLiteDatabase db = localDatabaseHelper.getWritableDatabase();
        //db.execSQL("delete from " + localDatabaseHelper.TABLE_NAME);

    }
}
