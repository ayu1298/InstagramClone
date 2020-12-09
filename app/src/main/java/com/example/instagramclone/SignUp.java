package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    private TextView txtGetData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtGetData=findViewById(R.id.txtGetdata);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> parseQuery=ParseQuery.getQuery("Boxer");
                parseQuery.getInBackground("QdhkSIr7td", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object!=null && e==null)
                        {
                            txtGetData.setText(object.get("punch_speed") + "");
                        }
                    }
                });
            }
        });

    }
    public void hello(View view)
    {
        ParseObject boxer=new ParseObject("Boxer");
        boxer.put("punch_speed",200);
        boxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null)
                    FancyToast.makeText(SignUp.this,"Boxer saved",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
            }
        });
    }
}