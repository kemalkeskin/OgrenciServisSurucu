package com.ogrenci.projetakip.ogrenciservissurucu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class ActivityLogin extends AppCompatActivity {

    Button btnLogin, btnExit;
    TextInputEditText txtUserName, txtPassword, txtDealerCode;
    CheckBox cbRememberMe;
    Toolbar mActionBarToolbar;

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Öğrenci Servis Sürücü");

        GetVariableFromObject();

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        Boolean remember = loginPreferences.getBoolean("remember", false);

        if (remember == true) {
            txtDealerCode.setText(loginPreferences.getString("dealerCode", ""));
            txtUserName.setText(loginPreferences.getString("userName", ""));
            txtPassword.setText(loginPreferences.getString("password", ""));
            cbRememberMe.setChecked(true);
        }


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Business.internetCheck(getApplicationContext())) {
                    Business.giveDialog(ActivityLogin.this, "İnternet Bağlantısı", "İnternetinizi kontrol ediniz!");
                    return;
                }
                String dealerCode = txtDealerCode.getText().toString().trim();
                String userName = txtUserName.getText().toString().trim();
                String password = txtPassword.getText().toString();

                if (dealerCode.length() == 0) {
                    Messages.enterDealerCode(getApplicationContext());
                    return;
                }

                if (userName.length() == 0) {
                    Messages.enterUserName(getApplicationContext());
                    return;
                }

                if (password.length() == 0) {
                    Messages.enterPassword(getApplicationContext());
                    return;
                }

                RememberOrForgetMe(dealerCode, userName, password);
                Business.openScreen(getApplicationContext(), ActivityMain.class);
            }
        });

    }

    private void RememberOrForgetMe(String dealerCode, String userName, String password) {
        if (cbRememberMe.isChecked()) {
            loginPrefsEditor.putBoolean("remember", true);
            loginPrefsEditor.putString("dealerCode", dealerCode);
            loginPrefsEditor.putString("userName", userName);
            loginPrefsEditor.putString("password", password);
            loginPrefsEditor.commit();
        } else {
            loginPrefsEditor.clear();
            loginPrefsEditor.commit();
        }
    }

    private void GetVariableFromObject() {
        txtDealerCode = (TextInputEditText) findViewById(R.id.txtDealerCode);
        txtUserName = (TextInputEditText) findViewById(R.id.txtUserName);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        cbRememberMe = (CheckBox) findViewById(R.id.cbRememberMe);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }
}
