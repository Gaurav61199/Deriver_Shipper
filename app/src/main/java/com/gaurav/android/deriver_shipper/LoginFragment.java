package com.gaurav.android.deriver_shipper;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by Gaurav on 9/24/2017.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    private Button btOrRegister,btLogin;
    private EditText MobileEditText;
    private CommBridge mCommBridge;
    private TextInputLayout mInputLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_login_fragment,container,false);


        btLogin = (Button) view.findViewById(R.id.btLogin);
        btOrRegister = (Button) view.findViewById(R.id.btOrRegister);
        MobileEditText = (EditText) view.findViewById(R.id.etLoginPhone);
        mInputLayout= (TextInputLayout) view.findViewById(R.id.loginTextInputLayout);
        mInputLayout.setErrorEnabled(true);


        btOrRegister.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        MobileEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() < 10){
                    mInputLayout.setError(null);
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btOrRegister){
            mCommBridge.register();
        }else{
            if (mInputLayout.getEditText().length()< 10){
                mInputLayout.setError("Invalid Mobile Number");
            }else{
                mCommBridge.enterOTP(MobileEditText.getText().toString());
            }

        }

    }

    interface CommBridge{
        void register();
        void enterOTP(String MobileNo);

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof CommBridge){
            mCommBridge = (CommBridge) context;
        }
    }

}
