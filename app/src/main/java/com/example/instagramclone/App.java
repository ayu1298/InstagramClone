package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;



    public class App extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            Parse.initialize(new Parse.Configuration.Builder(this)
                    .applicationId("JxxgYSAKfUHlxSKWIyZNpy7SGAqhTcOx5fbybozY")
                    // if defined
                    .clientKey("OcS8lxzWMxVxD9xvswEQVQG4nbnq13o2o5FK1aHk")
                    .server("https://parseapi.back4app.com/")
                    .build()
            );
        }
    }


