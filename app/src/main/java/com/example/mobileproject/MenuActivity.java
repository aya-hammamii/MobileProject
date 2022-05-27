package com.example.mobileproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.databinding.ActivityMenuBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuBinding binding;

    private FirebaseAuth mAuth;

    NavigationView mNavigationView;
    View mHeaderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMenu.toolbar);
//        binding.appBarMenu.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        binding.appBarMenu.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendEmail();


            }

        });


        DrawerLayout drawer = binding.drawerLayout;

        NavigationView navigationView = binding.navView;


        //Get the headerview first
        View headerView = navigationView.getHeaderView(0);
        // Email TextView
        TextView userEmail = headerView.findViewById(R.id.textView);
        // set email
        SharedPreferences preferencesGet = getSharedPreferences("Data_Session", Context.MODE_PRIVATE);
        String email = preferencesGet.getString("MailKey", "default");
        userEmail.setText(email);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        binding.navView.getMenu().findItem(R.id.students).setOnMenuItemClickListener(menuItem -> {
            OnStudentsList();
            return true;
        });


        binding.navView.getMenu().findItem(R.id.position).setOnMenuItemClickListener(menuItem -> {
            mapIntent();
            return true;
        });
//
        binding.navView.getMenu().findItem(R.id.settings).setOnMenuItemClickListener(menuItem -> {
            settingsIntent();
            return true;
        });

        binding.navView.getMenu().findItem(R.id.logout).setOnMenuItemClickListener(menuItem -> {
            logout();
            return true;
        });


//        binding.appBarMenu.toolbar.getMenu().findItem(R.id.action_logout).setOnMenuItemClickListener(menuItem -> {
//            settingsIntent();
//            return true;
//        });
//
//        binding.appBarMenu.toolbar.getMenu().findItem(R.id.action_logout).setOnMenuItemClickListener(menuItem -> {
//            logout();
//            return true;
//        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void logout() {
        FirebaseAuth.getInstance().signOut();
        SharedPreferences preferences = getSharedPreferences("Data_Session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        finish();
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
        Toast.makeText(this, "Logout Successfully!", Toast.LENGTH_SHORT).show();
    }


    private void OnStudentsList() {

        Intent i = new Intent(this, ShowList.class);
        startActivity(i);

    }


    private void SendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "contact.isamm@gmail.com", null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(intent, "Choose an Email client :"));
    }

    private void mapIntent() {
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }

    private void settingsIntent() {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }


}