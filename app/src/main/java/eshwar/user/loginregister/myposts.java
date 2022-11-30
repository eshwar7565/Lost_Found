package eshwar.user.loginregister;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class myposts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myposts);
        ActionBar actionBar;

        actionBar = getSupportActionBar();
        actionBar.setTitle("MY POSTS");

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0E0807"));
        actionBar.setBackgroundDrawable(colorDrawable);


        Button myfoundposts = findViewById(R.id.myfoundposts);
        myfoundposts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myposts.this , myfoundposts.class);
                startActivity(intent);
            }
        });

    Button mylostposts = findViewById(R.id.mylostposts);
        mylostposts.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(myposts.this , mylostposts.class);
            startActivity(intent);
        }
    });
}

}