package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtLoginEmail,edtLoginPassword;
    private Button btnLogInActivity,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");
        edtLoginEmail=findViewById(R.id.edtLoginEmail);
        edtLoginPassword=findViewById(R.id.edtLoginPassword);
        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i==keyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN)
                    onClick(btnLogInActivity);
                return false;
            }
        });

        btnLogInActivity=findViewById(R.id.btnLogInActivity);
        button2=findViewById(R.id.button2);

        btnLogInActivity.setOnClickListener(this);

        if(ParseUser.getCurrentUser()!=null)
        //    ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnLogInActivity:
                if(edtLoginEmail.getText().toString().equals("")||edtLoginPassword.getText().toString().equals(""))
                {
                    FancyToast.makeText(LoginActivity.this,"Email,Password is required",Toast.LENGTH_SHORT,FancyToast.INFO,true).show();

                }else {
                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                FancyToast.makeText(LoginActivity.this, user.getUsername() + " is logged in successfully", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                               transitionToSocialMediaActivity();
                            }
                        }
                    });
                }
                    break;

            case R.id.button2:
                Intent intent=new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
                break;


                    }
                }
    public void LoginLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    private void transitionToSocialMediaActivity(){
        Intent intent=new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
        }
