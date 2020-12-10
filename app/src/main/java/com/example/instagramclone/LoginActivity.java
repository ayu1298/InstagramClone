package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
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

        btnLogInActivity=findViewById(R.id.btnLogInActivity);
        button2=findViewById(R.id.button2);

        btnLogInActivity.setOnClickListener(this);

        if(ParseUser.getCurrentUser()!=null)
            ParseUser.getCurrentUser().logOut();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnLogInActivity:
                   ParseUser.logInInBackground(edtLoginEmail.getText().toString(),edtLoginPassword.getText().toString(), new LogInCallback() {
                       @Override
                       public void done(ParseUser user, ParseException e) {
                           if(user!=null && e==null)
                           {
                               FancyToast.makeText(LoginActivity.this,user.getUsername() + " is logged in successfully",Toast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

                           }
                       }
                   });
                   break;
            case R.id.button2:
                break;


                    }
                }
        }
