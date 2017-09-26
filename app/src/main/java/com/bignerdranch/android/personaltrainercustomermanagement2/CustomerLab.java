package com.bignerdranch.android.personaltrainercustomermanagement2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bignerdranch.android.personaltrainercustomermanagement2.Database.CustomerBaseHelper;
import com.bignerdranch.android.personaltrainercustomermanagement2.Database.CustomerCursorWrapper;
import com.bignerdranch.android.personaltrainercustomermanagement2.Database.CustomerDbSchema.CustomerTable;


/**
 * Created by mmedina4 on 9/25/2017.
 */

public class CustomerLab {
    private static CustomerLab sCustomerLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CustomerLab get(Context context) {
        if (sCustomerLab == null) {
            sCustomerLab = new CustomerLab(context);
        }
        return sCustomerLab;
    }

    private CustomerLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new CustomerBaseHelper(mContext).getWritableDatabase();
    }

    public void addCustomer(Customer c){
        ContentValues values = getContentValues(c);

        mDatabase.insert(CustomerTable.NAME, null, values);
    }

    public List<Customer> getCustomer(){
        List<Customer> customers = new ArrayList<>();

        CustomerCursorWrapper cursor = queryCustomers(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                customers.add(cursor.getCustomer());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return customers;
    }

    public Customer getCustomer(UUID id){
        CustomerCursorWrapper cursor =    queryCustomers(
                CustomerTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try{
            if(cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCustomer();
        } finally{
            cursor.close();
        }
    }


    public void updateCustomer(Customer customer){
        String uuidString = customer.getId().toString();
        ContentValues values = getContentValues(customer);

        mDatabase.update(CustomerTable.NAME, values,
                CustomerTable.Cols.UUID + " = ?",
                new String[] { uuidString});
    }

    private CustomerCursorWrapper queryCustomers(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CustomerTable.NAME,
                null, //columns
                whereClause,
                whereArgs,
                null,  //group by
                null, //having
                null //order by
        );
        return new CustomerCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Customer customer){
        ContentValues values = new ContentValues();
        values.put(CustomerTable.Cols.UUID, customer.getId().toString());
        values.put(CustomerTable.Cols.FNAME, customer.getFirstName());
        values.put(CustomerTable.Cols.LNAME, customer.getLastName());

        return values;


    }
}
