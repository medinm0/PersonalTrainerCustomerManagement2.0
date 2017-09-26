package com.bignerdranch.android.personaltrainercustomermanagement2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mmedina4 on 9/25/2017.
 */

public class CustomerListFragment extends Fragment {
    private RecyclerView mCustomerRecyclerView;
    private CustomerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
              Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);

        mCustomerRecyclerView = (RecyclerView) view
                .findViewById(R.id.customer_recycler_view);
        mCustomerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        CustomerLab customerLab = CustomerLab.get(getActivity());
        List<Customer> customers = customerLab.getCustomer();

        if(mAdapter == null) {
            mAdapter = new CustomerAdapter(customers);
            mCustomerRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCustomers(customers);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class CustomerHolder extends RecyclerView.ViewHolder{
        private TextView mFirstNameView;
        private TextView mLastNameView;
        private Customer mCustomer;

        public CustomerHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_customer,parent, false));

            mFirstNameView = (TextView) itemView.findViewById(R.id.customer_first_name);
            mLastNameView  = (TextView) itemView.findViewById(R.id.customer_last_name);

        }
        public void bind(Customer customer){
            mCustomer = customer;
            mFirstNameView.setText(mCustomer.getFirstName());
            mLastNameView.setText(mCustomer.getLastName());
        }

    }
    
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
    private class CustomerAdapter extends RecyclerView.Adapter<CustomerHolder>{
        private List<Customer> mCustomers;

        public CustomerAdapter(List<Customer> customers){
            mCustomers = customers;
        }
        @Override
        public CustomerHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CustomerHolder(layoutInflater ,parent);
        }

        @Override
        public void onBindViewHolder(CustomerHolder holder, int position){
            Customer customer = mCustomers.get(position);
            holder.bind(customer);
        }

        @Override
        public int getItemCount(){
            return mCustomers.size();
        }

        public void setCustomers(List<Customer> customers){
            mCustomers = customers;
        }
    }

}
