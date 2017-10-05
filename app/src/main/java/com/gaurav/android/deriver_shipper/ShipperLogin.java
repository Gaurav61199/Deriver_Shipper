package com.gaurav.android.deriver_shipper;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShipperLogin extends AppCompatActivity implements LoginFragment.CommBridge, RegisterFragment.CommBrigeB{

    OTPFragment mOTPFragment = new OTPFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper_login);

        LoginFragment loginFragment = new LoginFragment();
        if (loginFragment != null){
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.login_layout_root,loginFragment,"loginFrag").commit();

        }
    }


    @Override
    public void register() {
        RegisterFragment registerFragment = new RegisterFragment();
        getFragmentManager().beginTransaction().replace(R.id.login_layout_root,registerFragment).addToBackStack(null).commit();
    }

    @Override
    public void enterOTP(String MobileNo) {
        mOTPFragment.setMobileNo(MobileNo);
        getFragmentManager().beginTransaction().replace(R.id.login_layout_root,mOTPFragment).addToBackStack(null).commit();
    }

    @Override
    public void Login() {
        LoginFragment loginFragment = new LoginFragment();
        getFragmentManager().beginTransaction().replace(R.id.login_layout_root,loginFragment).commit();
    }

}
