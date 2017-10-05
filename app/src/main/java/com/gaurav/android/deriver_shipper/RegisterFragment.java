package com.gaurav.android.deriver_shipper;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gaurav on 9/25/2017.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener,TextWatcher {

    private Spinner mSpinner;
    private EditText etMobileNo;
    private Button btRegister, btOrLogin;
    private CommBrigeB mCommBrigeB;
    private TextInputLayout mLayout3,mLayout4,mLayout5,mLayout6;
    private boolean SpinnerChoosed=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_register_fragment,container,false);

        mSpinner = (Spinner) view.findViewById(R.id.spCompanyType);
        etMobileNo = (EditText) view.findViewById(R.id.regMobileNo);
        btRegister = (Button) view.findViewById(R.id.btRegister);
        btOrLogin = (Button) view.findViewById(R.id.btORLogin);
        mLayout3 = (TextInputLayout)view.findViewById(R.id.textInputLayout3);
        mLayout4 = (TextInputLayout)view.findViewById(R.id.textInputLayout4);
        mLayout5 = (TextInputLayout)view.findViewById(R.id.textInputLayout5);
        mLayout6 = (TextInputLayout)view.findViewById(R.id.textInputLayout6);

        mLayout3.setErrorEnabled(true);
        mLayout4.setErrorEnabled(true);
        mLayout5.setErrorEnabled(true);
        mLayout6.setErrorEnabled(true);

        mLayout3.getEditText().addTextChangedListener(this);
        mLayout4.getEditText().addTextChangedListener(this);
        mLayout5.getEditText().addTextChangedListener(this);
        mLayout6.getEditText().addTextChangedListener(this);
        btOrLogin.setOnClickListener(this);


        btRegister.setOnClickListener(this);

        ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.CompanyType,android.R.layout.simple_spinner_item);

        spAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        mSpinner.setAdapter(spAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0 ){
                    SpinnerChoosed=false;
                }else {
                    SpinnerChoosed=true;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;

    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btRegister){
            if (validateForm()){
                mCommBrigeB.enterOTP(etMobileNo.getText().toString());
            }
        }

        if (view.getId() == R.id.btORLogin){
                mCommBrigeB.Login();
        }

    }

    private boolean validateForm() {

      if (!verifyEmailText(mLayout5.getEditText().getEditableText()) || mLayout4.getEditText().length() < 10 || mLayout3.getEditText().length() == 0  || mLayout6.getEditText().length() == 0 || !SpinnerChoosed){
          if (mLayout3.getEditText().length() == 0){
              mLayout3.setError("Enter Name");
          }
          if (mLayout4.getEditText().length() < 10){
              mLayout4.setError("Invalid Mobile Number");
          }
          if (mLayout6.getEditText().length() == 0){
              mLayout6.setError("Enter Complete Company Name");
          }
          if (!SpinnerChoosed){
              TextView error = (TextView) mSpinner.getSelectedView();
              error.setError("Select Company Type");
          }
          return false;
      }else {
          return true;
      }
    }

    private boolean verifyEmailText(Editable email) {
        if (TextUtils.isEmpty(email)){
            mLayout5.setError("Enter Email");
            return false;
        }else{
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches() == false){
                mLayout5.setError("Enter valid Email");
                return false;
            }else{
                return true;
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable == mLayout3.getEditText().getEditableText()){
            mLayout3.setError(null);
        }
        if (editable == mLayout4.getEditText().getEditableText()){
            mLayout4.setError(null);
        }
        if (editable == mLayout5.getEditText().getEditableText()){
            mLayout5.setError(null);
        }
        if (editable == mLayout6.getEditText().getEditableText()){
            mLayout6.setError(null);
        }
        
    }

    interface CommBrigeB{
        void enterOTP(String MobileNo);
        void Login();
    }

    @Override
    public void onAttach(Context context){
            super.onAttach(context);
        if (context instanceof  CommBrigeB){
                mCommBrigeB= (CommBrigeB)context;
        }
    }
}
