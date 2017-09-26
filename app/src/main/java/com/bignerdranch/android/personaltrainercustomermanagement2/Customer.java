package com.bignerdranch.android.personaltrainercustomermanagement2;

import java.util.UUID;

/**
 * Created by mmedina4 on 9/4/2017.
 */

public class Customer {
    private UUID mId;
    private String mFirstName;
    private String mLastName;

    public Customer(){
        this(UUID.randomUUID());
    }

    public Customer(UUID id){
        mId = id;
    }

    public Customer(String firstName, String lastName){
        mId = UUID.randomUUID();
        mFirstName = firstName;
        mLastName = lastName;
    }

    public UUID getId() {
        return mId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }
}

