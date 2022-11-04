package eshwar.user.loginregister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class postlostmain extends AppCompatActivity {

    private Button addimagelost,submitlost;
    private EditText  namepostlost,phonenumberlost,locationlost,messagelost;
    private static final int  Gallery_Pick =0 ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlostmain);
        getSupportActionBar().setTitle("POST FOR LOST");



         namepostlost = findViewById(R.id.edit_text_name_postforlost);
         phonenumberlost = findViewById(R.id.edit_text_phone_post_for_lost);
         locationlost = findViewById(R.id.edit_location_post_for_lost);
         messagelost = findViewById(R.id.edit_message_post_for_lost);


         submitlost = findViewById(R.id.submitlost);
         Button buttonaddimagelost = findViewById(R.id.addimagebuttonlost);


        /* buttonaddimagelost.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(postlostmain.this,addimagepostlost.class);
                 startActivity(intent);
             }
         });*/













    }





}