package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

        Animation anim;
        ImageView imageView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);
            imageView=(ImageView)findViewById(R.id.imageView2); // Declare an imageView to show the animation.
            anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in); // Create the animation.
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    // MainActivity.class is the activity to go after showing the splash screen.
//                    Intent intent = new Intent(this, MainActivity.class);
//                    startActivity(intent);
//                    finish();



                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        //User is Logged in
                        Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(i);
                    }else{
                        //No user is Logged in
                        Intent intent = new Intent(getApplicationContext(), LogIn.class);
                        startActivity(intent);
                    }
                }





                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            imageView.startAnimation(anim);
        }
    }