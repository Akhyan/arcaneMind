package com.akhyanvaidya.secusafe;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import controller.LoginBackground;
import navigateMenu.LoginHome;

public class LoginActivity extends AppCompatActivity {


    EditText lUname, lPassword;
    Button bLogin;
    TextView tRegister;
    CheckBox cbRemember;
    SharedPreferences shareLogin, shareRemember, shareCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        lUname=(EditText) findViewById(R.id.elUsername);
        lPassword= (EditText) findViewById(R.id.elPassword);
        bLogin=(Button) findViewById(R.id.bLogin);
        tRegister=(TextView) findViewById(R.id.tRegister);
        cbRemember=(CheckBox) findViewById(R.id.cbRemember);
        AutoLogin(); //check if there is shared pref has details


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveRemember("Remember_Me", cbRemember.isChecked());
                if (lUname.getText().toString().equals("") || lPassword.getText().toString().equals("")) {

                    lUname.setText("");
                    lPassword.setText("");
                    Toast.makeText(LoginActivity.this, "No Username or Password", Toast.LENGTH_SHORT).show();
                } else if (cbRemember.isChecked()) {

                    SaveLoginDetails(lUname.getText().toString(), lPassword.getText().toString());//save login information
                    LoadLoginBackground();

                } else {

                    LoadLoginBackground();

                    }
                }
            }

            );

            //when tRegsiter is clicked this method will run.
            tRegister.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);//Goes from Login page to Register page
                LoginActivity.this.startActivity(registerIntent);//starts the register.Intent function
            }
        });

    }


    private void SaveLoginDetails(String valueUser, String valuePass){//save username and password
        shareLogin = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor= shareLogin.edit();
        editor.putString("Username", valueUser);
        editor.putString("Password", valuePass);
        editor.commit();
    }

    private void SaveRemember(String key, Boolean value){
        shareRemember= PreferenceManager.getDefaultSharedPreferences(this);
        Editor editorRem= shareRemember.edit();
        editorRem.putBoolean(key, value);
        editorRem.commit();
    }

    private void LoadLoginBackground(){

        LoginBackground loginBackground=new LoginBackground(LoginActivity.this);
        loginBackground.execute("login", lUname.getText().toString(), lPassword.getText().toString());

    }
    //check if the user is logged in
    private void AutoLogin(){
        shareCheck= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());//changed to this if does not work
        String name= shareCheck.getString("Username", "");
        String pass= shareCheck.getString("Password", "");

        if (name != "" && pass != ""){

            lUname.setText(name);
            lPassword.setText(pass);
            LoadLoginBackground();

        }
    }
}
