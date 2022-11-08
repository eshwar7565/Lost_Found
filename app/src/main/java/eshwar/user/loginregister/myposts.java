package eshwar.user.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class myposts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myposts);
        getSupportActionBar().setTitle("MY POSTS");


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