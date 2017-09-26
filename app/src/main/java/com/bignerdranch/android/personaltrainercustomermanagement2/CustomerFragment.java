package com.bignerdranch.android.personaltrainercustomermanagement2;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class CustomerFragment extends Fragment {
    private Button mSubmitNewCustomerButton;
    private EditText mFirstName, mLastName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer, container, false);
        //init text views
        mFirstName = (EditText) v.findViewById(R.id.customer_first_name);
        mLastName = (EditText) v.findViewById(R.id.customer_last_name);

        // Submit Button
        mSubmitNewCustomerButton = (Button) v.findViewById(R.id.submit_new_customer);
        mSubmitNewCustomerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String mFirstNameValue = mFirstName.getText().toString();
                String mLastNameValue = mLastName.getText().toString();

                Customer customer = new Customer(mFirstNameValue,mLastNameValue);
                CustomerLab.get(getActivity()).addCustomer(customer);
                Log.d("mFirstNameValue",mFirstNameValue);
                Log.d("mLastName",mLastNameValue);

            }
        });

        return v;
    }


}