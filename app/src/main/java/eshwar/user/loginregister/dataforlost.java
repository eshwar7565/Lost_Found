package eshwar.user.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class dataforlost extends AppCompatActivity implements dataforlost2 {

    FirebaseAuth firebaseAuth;
   private RecyclerView recyclerView;
    List<modellostposts> lostpostlist;
    Adapterforlostposts adapterforlostposts;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataforlost);
        getSupportActionBar().setTitle("LOST ITEMS DATA");





    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.activity_dataforlost,container,false);
        firebaseAuth = FirebaseAuth.getInstance();


        // recyler view and its properties

        recyclerView = findViewById(R.id.recyclerviewforlostposts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(dataforlost.this);

        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
        adapterforlostposts = new Adapterforlostposts();
        recyclerView.setAdapter(adapterforlostposts);

        lostpostlist = new ArrayList<>();


        loadlostposts();



        return view;
    }

    private void loadlostposts() {
   //path of all lostposts
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Lost Items");

        //get required data from this

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                lostpostlist.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    modellostposts modellostposts = ds.getValue(modellostposts.class);
                    lostpostlist = new ArrayList<>();


                    lostpostlist.add(modellostposts);

                    adapterforlostposts = new Adapterforlostposts(dataforlost.this,lostpostlist);

                    recyclerView.setAdapter(adapterforlostposts);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // incase of error


            }
        });
    }


}