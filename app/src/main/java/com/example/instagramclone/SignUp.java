package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i==keyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN)
                    onClick(btnSignUp);
                return false;
            }
        });

        btnSignUp=findViewById(R.id.button);
        btnLogin=findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
                if(ParseUser.getCurrentUser()!=null)
                   // ParseUser.getCurrentUser().logOut();
                    transitionToSocialMediaActivity();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button:
                if(edtEmail.getText().toString().equals("")||edtUsername.getText().toString().equals("")||edtPassword.getText().toString().equals(""))
                {
                    FancyToast.makeText(SignUp.this,"Email,Username,Password is required",Toast.LENGTH_SHORT,FancyToast.INFO,true).show();

                }
                else {
                    final ParseUser appuser = new ParseUser();
                    appuser.setEmail(edtEmail.getText().toString());
                    appuser.setUsername(edtUsername.getText().toString());
                    appuser.setPassword(edtPassword.getText().toString());
                    // to show user to wait while u r signed in
                    ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up " + edtUsername.getText().toString());
                    progressDialog.show();
                    appuser.signUpInBackground(new SignUpCallback() {
                                                   @Override
                                                   public void done(ParseException e) {
                                                       if (e == null) {
                                                           FancyToast.makeText(SignUp.this, appuser.getUsername() + " is signed up successfully", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                                           transitionToSocialMediaActivity();
                                                       } else {
                                                           FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                                                       }
                                                       progressDialog.dismiss();
                                                   }
                                               }
                    );
                }
                break;
            case R.id.btnLogIn:
                Intent intent=new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);

                break;
        }

    } // to disable keyboard when touched in layout
    public void rootLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    private void transitionToSocialMediaActivity(){
        Intent intent=new Intent(SignUp.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}