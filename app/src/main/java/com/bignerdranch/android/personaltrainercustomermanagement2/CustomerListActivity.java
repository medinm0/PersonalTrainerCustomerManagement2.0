package com.bignerdranch.android.personaltrainercustomermanagement2;

import android.support.v4.app.Fragment;

/**
 * Created by mmedina4 on 9/25/2017.
 */

public class CustomerListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment(){
        return new CustomerListFragment();
    }
}
