package eshwar.user.loginregister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class postlostmain extends AppCompatActivity {
   private ImageSwitcher imageIS;
    private Button addimagelost,submitlost,nextinlost,previousinlost;
    private EditText  namepostlost,phonenumberlost,locationpostlost,messagepostlost;
    private static final int  PICK_IMAGES_CODE = 0 ;
    int position =0 ;
    private ArrayList <Uri> imageUris;
    private String phonelost;
    private String locationlost;
    private String namelost,current_user_id;
    private String messagelost;
    private String saveCurrentDate,saveCurrentTime ,Postlostrandomname;
    private StorageReference PostlostReference;
    private  DatabaseReference reference;
    private int upload_count =0 ;
    private FirebaseAuth authProfile;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlostmain);
        getSupportActionBar().setTitle("POST FOR LOST");

        PostlostReference = FirebaseStorage.getInstance().getReference();
        reference = FirebaseDatabase.getInstance().getReference().child("Registered Users");
        authProfile = FirebaseAuth.getInstance();
        current_user_id =  authProfile.getCurrentUser().getUid();



         namepostlost = findViewById(R.id.edit_text_name_postforlost);
         phonenumberlost = findViewById(R.id.edit_text_phone_post_for_lost);
         locationpostlost = findViewById(R.id.edit_location_post_for_lost);
         messagepostlost = findViewById(R.id.edit_message_post_for_lost);

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

         submitlost.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 ValidatePostLost();
             }
         });


        buttonaddimagelost.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 pickImagesintent();


             }
         });

    }

    private void ValidatePostLost() {
        String phonelost = phonenumberlost.getText().toString();
        String namelost = namepostlost.getText().toString();
        String locationlost = locationpostlost.getText().toString();
        String  messagelost =  messagepostlost.getText().toString();
        if(imageUris == null ){
            Toast.makeText(postlostmain.this, "Please select an image ", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(messagelost)){
            Toast.makeText(postlostmain.this, "Please add a message", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(namelost)){
            Toast.makeText(postlostmain.this, "Please enter your name", Toast.LENGTH_SHORT).show();
        }
         else if(TextUtils.isEmpty(phonelost)){
            Toast.makeText(postlostmain.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
        }
       else  if(TextUtils.isEmpty(locationlost)){
            Toast.makeText(postlostmain.this, "Please enter location ", Toast.LENGTH_SHORT).show();
        }
       else{
           StoringImagetoFirebaseStorage();
        }

    }

    private void StoringImagetoFirebaseStorage() {

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());


        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(calForTime.getTime());

        Postlostrandomname = saveCurrentDate+saveCurrentTime;

        StorageReference ImagelostFolder = FirebaseStorage.getInstance().getReference().child("Lost_Items");

       for(int j =0 ; j < imageUris.size() ; j++){
           Uri IndividualImage = imageUris.get(j);
         StorageReference ImageName  = ImagelostFolder.child("Image" + IndividualImage.getLastPathSegment());
          ImageName.putFile(IndividualImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
              @Override
              public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                  ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                      @Override
                      public void onSuccess(Uri uri) {
                          String url =  String.valueOf(uri);
                          StoreLink(url);

                      }
                  });
              }
          });
       }





    }

    private void StoreLink(String url) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Lost Items");
        reference.child(current_user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("Imagelink",url);

        databaseReference.push().setValue(hashMap);


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