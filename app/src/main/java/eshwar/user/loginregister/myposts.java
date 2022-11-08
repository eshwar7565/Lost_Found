package eshwar.user.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class myposts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myposts);
        getSupportActionBar().setTitle("MY POSTS");
    }

}