package eshwar.user.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LostFoundmainActivity extends AppCompatActivity {
    private FirebaseAuth authProfile;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu
        getMenuInflater().inflate(R.menu.common_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menulogout){
            authProfile.signOut();
            Toast.makeText(LostFoundmainActivity.this, "Logged Out ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LostFoundmainActivity.this,MainActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        finish();
        }
        return super.onOptionsItemSelected(item);
    }
}