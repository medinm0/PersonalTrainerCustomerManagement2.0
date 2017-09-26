package com.bignerdranch.android.personaltrainercustomermanagement2.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.personaltrainercustomermanagement2.Customer;
import com.bignerdranch.android.personaltrainercustomermanagement2.Database.CustomerDbSchema.CustomerTable;

import java.util.UUID;

/**
 * Created by mmedina4 on 9/25/2017.
 */

public class CustomerCursorWrapper extends CursorWrapper{
    public CustomerCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Customer getCustomer(){
        String uuidString = getString(getColumnIndex(CustomerTable.Cols.UUID));
        String firstName = getString(getColumnIndex(CustomerTable.Cols.FNAME));
        String lastName = getString(getColumnIndex(CustomerTable.Cols.LNAME));

        Customer customer = new Customer(UUID.fromString(uuidString));
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        return customer;
    }
}
