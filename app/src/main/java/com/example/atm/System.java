package com.example.atm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

public class System extends AppCompatActivity {
    private LocalDatabaseHelper localDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button balanceBtn;
    private TextView textViewUser;
    private TextView balance;
    Boolean retrievData;


    public System() {
        localDatabaseHelper = new LocalDatabaseHelper(this);
    }

    //Used one time to insert Balance.
    public long insertData(String balance) {
        SQLiteDatabase dbb = localDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalDatabaseHelper.Balance, balance);
        long id = dbb.insert(localDatabaseHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = localDatabaseHelper.getWritableDatabase();
        String[] columns = {localDatabaseHelper.CARD_ID, localDatabaseHelper.Balance};
        Cursor cursor = db.query(localDatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int atm_id = cursor.getInt(cursor.getColumnIndex(localDatabaseHelper.CARD_ID));
            String balance = cursor.getString(cursor.getColumnIndex(localDatabaseHelper.Balance));

            buffer.append(atm_id + "   " + balance + " \n");
        }
        if (buffer.toString() != null) {


            retrievData = true;
            return buffer.toString();
        } else {
            return null;
        }
    }


    public class LocalDatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "atm_balance";
        private static final String TABLE_NAME = "balance";
        private static final int DATABASE_Version = 1;

        //Columns
        private static final String CARD_ID = "atm_id";
        private static final String Balance = "balance";

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + CARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Balance + " VARCHAR(255));";

        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public LocalDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                create_toast(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                create_toast(context, "" + e);
            }
        }
    }

    public void create_toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        textViewUser = findViewById(R.id.textViewUser);
        balance = findViewById(R.id.balance);
        balanceBtn = findViewById(R.id.balanceBtn);
        balanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewUser.setText("Loged In");
                balance.setText(getData());

            }
        });
    }
}