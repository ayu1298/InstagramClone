package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity {
       private Button btnLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnLogOut=findViewById(R.id.btnLogOut);
        TextView txtWeclome=findViewById(R.id.txtWelcome);
        txtWeclome.setText("Welcome!!" + ParseUser.getCurrentUser().get("username"));
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WelcomeActivity.this,SignUpLoginActivity.class);
                startActivity(intent);
            }
        });

    }
}