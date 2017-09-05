package com.bignerdranch.android.personaltrainercustomermanagement2;

import java.util.Date;
import java.util.UUID;

/**
 * Created by mmedina4 on 9/4/2017.
 */

public class Session {
    private UUID mId;
    private Date mDate;
    private boolean mCompleted;
    private Customer mCustomer;

    public Session(Customer customer){
        mId = UUID.randomUUID();
        mDate = new Date();
        mCustomer = customer;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public Customer getCustomer() {
        return mCustomer;
    }

    public void setCustomer(Customer customer) {
        mCustomer = customer;
    }
}
