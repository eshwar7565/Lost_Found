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
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

public class postlostmain extends AppCompatActivity {
   private ImageSwitcher imageIS;
    private Button addimagelost,submitlost,nextinlost,previousinlost;
    private EditText  namepostlost,phonenumberlost,locationlost,messagelost;
    private static final int  PICK_IMAGES_CODE = 0 ;
    int position =0 ;
    private ArrayList <Uri> imageUris;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlostmain);
        getSupportActionBar().setTitle("POST FOR LOST");



         namepostlost = findViewById(R.id.edit_text_name_postforlost);
         phonenumberlost = findViewById(R.id.edit_text_phone_post_for_lost);
         locationlost = findViewById(R.id.edit_location_post_for_lost);
         messagelost = findViewById(R.id.edit_message_post_for_lost);

         imageIS = findViewById(R.id.imageswitcherpostlost);

         nextinlost = findViewById(R.id.nextinpostlost);
         previousinlost = findViewById(R.id.previousinppostlost);

         imageUris = new ArrayList<>();
         imageIS.setFactory(new ViewSwitcher.ViewFactory() {
             @Override
             public View makeView() {
                 ImageView imageView = new ImageView(getApplicationContext());
                 return imageView;
             }
         });




         submitlost = findViewById(R.id.submitlost);
         Button buttonaddimagelost = findViewById(R.id.addimagebuttonlost);


         previousinlost.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(position>0){
                     position--;
                     imageIS.setImageURI(imageUris.get(position));
                 }

             }
         });

         nextinlost.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (position<imageUris.size()-1){
                     position++;
                     imageIS.setImageURI(imageUris.get(position));
                 }

             }
         });


        buttonaddimagelost.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 pickImagesintent();


             }
         });

    }

    private void pickImagesintent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(Intent.createChooser(intent,"Select Image(s)"),PICK_IMAGES_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGES_CODE){
            if(data.getClipData()!=null){

                int count = data.getClipData().getItemCount();
                for(int i =0 ; i < count ; i++ ){
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    imageUris.add(imageUri);
                }
                imageIS.setImageURI(imageUris.get(0));
                position = 0;
            }
            else{
                Uri imageUri = data.getData();
                imageUris.add(imageUri);
                imageIS.setImageURI(imageUris.get(0));
                position = 0;
            }
        }
    }
}