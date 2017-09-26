package com.bignerdranch.android.personaltrainercustomermanagement2.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.personaltrainercustomermanagement2.Database.CustomerDbSchema.CustomerTable;

/**
 * Created by mmedina4 on 9/25/2017.
 */

public class CustomerBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "customerBase.db";

    public CustomerBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + CustomerTable.NAME + "(" +
            CustomerTable.Cols.UUID + ", " +
            CustomerTable.Cols.FNAME + ", " +
            CustomerTable.Cols.LNAME +
            ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
