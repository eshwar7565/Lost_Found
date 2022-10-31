package eshwar.user.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LostFoundmainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_foundmain);
        getSupportActionBar().setTitle("LOST & FOUND");


        // Open postlost activity
        Button buttonpostforlost = findViewById(R.id.postforlost);
        buttonpostforlost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LostFoundmainActivity.this,postlostmain.class);
                startActivity(intent);
            }
        });

        //Open postfoud Activity

        Button buttonforfound = findViewById(R.id.postforfound);
        buttonforfound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LostFoundmainActivity.this,postforfound.class);
                startActivity(intent);
            }
        });
    }
}