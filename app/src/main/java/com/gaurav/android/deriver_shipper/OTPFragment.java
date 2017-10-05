package com.gaurav.android.deriver_shipper;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Gaurav on 9/28/2017.
 */

public class OTPFragment extends Fragment {

    private String MobileNo;
    private TextView OTPCommand;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.otp_fragment,container,false);

        OTPCommand = (TextView) view.findViewById(R.id.OTPEnterCommand);
        OTPCommand.setText("Enter OTP sent to +91 " +  MobileNo);

        return view;
    }

    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }
}
