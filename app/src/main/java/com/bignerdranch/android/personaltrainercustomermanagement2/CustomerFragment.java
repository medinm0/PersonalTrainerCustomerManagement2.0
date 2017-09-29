package com.bignerdranch.android.personaltrainercustomermanagement2;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.util.UUID;


public class CustomerFragment extends Fragment {
    private Button mSubmitNewCustomerButton;
    private File mPhotoFile;
    private EditText mFirstName, mLastName;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private Customer mCustomer;

    private static final int REQUEST_PHOTO = 2;
    private static final String ARGS_CUSTOMER_ID = "customer_id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mCustomer = new Customer("Yami", "Medina");


        View v = inflater.inflate(R.layout.fragment_customer, container, false);
        //init text views
        mFirstName = (EditText) v.findViewById(R.id.customer_first_name);
        mLastName = (EditText) v.findViewById(R.id.customer_last_name);

        // PackageManager knows about all the components on the device.
        PackageManager packageManager = getActivity().getPackageManager();


        //init customer and photo
        //UUID customerId = (UUID) getArguments().getSerializable(ARGS_CUSTOMER_ID);
        //mCustomer = CustomerLab.get(getActivity()).getCustomer(customerId);
        mPhotoFile = CustomerLab.get(getActivity()).getPhotoFile(mCustomer);

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

        mPhotoButton = (ImageButton) v.findViewById(R.id.customer_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // mPhotoFile: Intended file location for photo.
        boolean canTakePhoto = mPhotoFile != null &&
                // Determine whether there's camera app available.
                captureImage.resolveActivity(packageManager) != null;

        mPhotoButton.setEnabled(canTakePhoto);

        if (canTakePhoto) {
            Uri uri = Uri.fromFile(mPhotoFile);
            // Send the photo file destination.
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        // Start camera app on click.
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });
        mPhotoView = (ImageView) v.findViewById(R.id.customer_photo);
        updatePhotoView();

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        } else if (requestCode == REQUEST_PHOTO){
            Uri uri = FileProvider.getUriForFile(getActivity(),
                    "com.bignerdranch.android.personaltrainercustomermanagement2.fileprovider",
                    mPhotoFile);
            getActivity().revokeUriPermission(uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            updatePhotoView();
        }
    }

    private void updatePhotoView(){
        if(mPhotoFile ==null || !mPhotoFile.exists()){
            mPhotoView.setImageDrawable(null);
        } else{
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }


}