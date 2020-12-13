package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class UsersPost extends AppCompatActivity {
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_post);

        linearLayout=findViewById(R.id.linearLayout);







         // to  check that which user is to be seen on scroll view
        Intent receivedIntentObject=getIntent();
        String receivedUserName=receivedIntentObject.getStringExtra("username");

         setTitle(receivedUserName + "'s posts");

        ParseQuery<ParseObject> parseQuery=new ParseQuery<ParseObject>("Photo");
        // very important condition to know that right user  is recevied
        parseQuery.whereEqualTo("username",receivedUserName);
        // next line gives latest photo object created by the user
        parseQuery.orderByDescending("createdAt");

        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();


        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects.size()>0 && e==null)
                {
                    for(ParseObject post: objects)
                    {
                        TextView postDescription=new TextView(UsersPost.this);
                        postDescription.setText(post.get("image_des") + "");
                        ParseFile postPicture=(ParseFile) post.get("picture");

                        postPicture.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if(data!=null && e==null)
                                {         //to set images in feed
                                    Bitmap bitmap= BitmapFactory.decodeByteArray(data,0, data.length);
                                    ImageView postImageView=new ImageView(UsersPost.this);
                                    LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                                    params.setMargins(5,5,5,5);
                                    postImageView.setLayoutParams(params);
                                    postImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                    postImageView.setImageBitmap(bitmap);

                                    LinearLayout.LayoutParams dparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                                    params.setMargins(5,5,5,15);
                                    postDescription.setLayoutParams(dparams);
                                    postDescription.setGravity(Gravity.CENTER);
                                    postDescription.setBackgroundColor(Color.BLUE);
                                    postDescription.setTextColor(Color.WHITE);
                                    postDescription.setTextSize(30f);


                                    linearLayout.addView(postImageView);
                                    linearLayout.addView(postDescription);

                                }
                            }
                        });
                    }
                }
                else
                {
                    FancyToast.makeText(UsersPost.this,receivedUserName + " doesn't have any posts", Toast.LENGTH_SHORT,FancyToast.INFO,true).show();
                    finish();
                }
            dialog.dismiss();
            }

        });


    }
}