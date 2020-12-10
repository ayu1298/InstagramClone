package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail,edtUsername,edtPassword;
    private Button btnSignUp,btnLogin;
   // private String allBoxers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        edtEmail=findViewById(R.id.edtEnterEmail);
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);

        btnSignUp=findViewById(R.id.button);
        btnLogin=findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
                if(ParseUser.getCurrentUser()!=null)
                    ParseUser.getCurrentUser().logOut();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button:
                 final ParseUser appuser=new ParseUser();
                 appuser.setEmail(edtEmail.getText().toString());
                 appuser.setUsername(edtUsername.getText().toString());
                 appuser.setPassword(edtPassword.getText().toString());
               // to show user to wait while u r signed in
                ProgressDialog progressDialog=new ProgressDialog(this);
                progressDialog.setMessage("Signing up " + edtUsername.getText().toString());
                progressDialog.show();
                 appuser.signUpInBackground(new SignUpCallback() {
                                                @Override
                                                public void done(ParseException e) {
                                                    if(e==null)
                                                    {
                                                        FancyToast.makeText(SignUp.this,appuser.getUsername() + " is signed up successfully",Toast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                                                    }
                                                    else
                                                    {
                                                        FancyToast.makeText(SignUp.this,e.getMessage(),Toast.LENGTH_LONG,FancyToast.ERROR,true).show();
                                                    }
                                                    progressDialog.dismiss();
                                                }
                                            }
                 );
                break;
            case R.id.btnLogIn:
                Intent intent=new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);

                break;
        }

    }
}