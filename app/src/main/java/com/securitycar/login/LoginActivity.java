package com.securitycar.login;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.securitycar.R;
import com.securitycar.app.AppConfig;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnLogin;
    private TextInputLayout inputLayoutUser, inputLayoutPass;
    private EditText inputUser, inputPass;

    private MaterialDialog dialogProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();


        inputLayoutUser = (TextInputLayout) findViewById(R.id.inputLayoutUser);
        inputLayoutPass = (TextInputLayout) findViewById(R.id.inputLayoutPass);

        inputUser = (EditText) findViewById(R.id.inputUser);
        inputPass = (EditText) findViewById(R.id.inputPass);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);

    }

    private void submiLogin() {
        if (!validateUser()) {
            return;
        }
        if (!validatePass()) {
            return;
        }

        // simulacion de inicio de sesion
        dialogProgress = AppConfig.alertProgressCustom(this, getString(R.string.init_sesion), R.color.colorPrimary,  true);

    }

    private boolean validateUser() {
        if (inputUser.getText().toString().trim().isEmpty()) {
            inputLayoutUser.setError(getString(R.string.err_user));
            requestFocus(inputUser);
            return false;
        } else {
            inputLayoutUser.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePass() {
        if (inputPass.getText().toString().trim().isEmpty()) {
            inputLayoutPass.setError(getString(R.string.err_pass));
            requestFocus(inputPass);
            return false;
        } else {
            inputLayoutPass.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void hideFocus(View view) {
        if (view != null) {
            if (view.requestFocus()) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                submiLogin();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialogProgress != null){
            dialogProgress.dismiss();
        }

    }
}
