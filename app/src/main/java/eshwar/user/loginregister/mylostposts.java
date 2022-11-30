package eshwar.user.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class mylostposts extends AppCompatActivity {
    private RecyclerView postlist ;
    private DatabaseReference postref, UserRef;
    private FirebaseAuth mAuth;
    private FirebaseRecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    String current_user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylostposts);
        getSupportActionBar().setTitle("MY LOST POSTS");
        postlist = (RecyclerView) findViewById(R.id.recyclerviewformylostposts);
        postlist.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        postlist.setLayoutManager(linearLayoutManager);
        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Registered Users");




            fetch();


    }

    private void fetch() {
        postref = FirebaseDatabase.getInstance().getReference().child("LostItems");


        Query query = postref.orderByChild("uid").equalTo(current_user_id);



        FirebaseRecyclerOptions<modelmylostposts> options =
                new FirebaseRecyclerOptions.Builder<modelmylostposts>().setQuery(query, new SnapshotParser<modelmylostposts>() {
                    @NonNull
                    @Override
                    public modelmylostposts parseSnapshot(@NonNull DataSnapshot snapshot) {

                        return new modelmylostposts(snapshot.child("FullName").getValue().toString(),
                                snapshot.child("Message").getValue().toString(),



                                snapshot.child("date").getValue().toString(),
                                snapshot.child("time").getValue().toString(),
                                snapshot.child("PhoneNumber").getValue().toString(),

                                snapshot.child("Imagelink").getValue().toString());


                    }
                }).build();

        adapter = new FirebaseRecyclerAdapter<modelmylostposts, viewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull modelmylostposts model) {
                final DatabaseReference itemRef = getRef(position);
                final String mykey = itemRef.getKey();
                holder.setDatetv(modelmylostposts.getDate());

                holder.setFullNametv(modelmylostposts.getFullName());
                holder.setMessagetv(modelmylostposts.getMessage());
                holder.setTimetv(modelmylostposts.getTime());
                holder.setPhonetv(modelmylostposts.getPhoneNumber());
                Glide.with(holder.imageIv.getContext()).load(modelmylostposts.getImagelink()).into(holder.imageIv);
                holder.Deletebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        assert mykey != null;
                        postref.child(mykey).removeValue();

                    }
                });
            }
            @NonNull
            @Override
            public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylostpostsviewstyle, parent, false);
                return new viewHolder(view) {

                };
            }
        };
        postlist.setAdapter(adapter);
    }



    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root ;

        //views from xml file
        ImageView imageIv;
        TextView FullNametv, datetv, timetv, Messagetv,Phonetv;
        Button Deletebutton;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            //init views
            root = itemView.findViewById(R.id.list_root);
            imageIv = itemView.findViewById(R.id.imageviewIv);
            FullNametv = itemView.findViewById(R.id.username);
            datetv = itemView.findViewById(R.id.date);
            timetv = itemView.findViewById(R.id.time);
            Phonetv = itemView.findViewById(R.id.mylostpostphonetv);
            Messagetv = itemView.findViewById(R.id.message);
            Deletebutton = itemView.findViewById(R.id.deletebutton);

        }
        public void setFullNametv(String string){
            FullNametv.setText(string);
        }
        public void setMessagetv(String string){
            Messagetv.setText(string);
        }
        public void setDatetv(String string){
            datetv.setText(string);
        }
        public void setTimetv(String string){
            timetv.setText(string);
        }
        public void setPhonetv(String string){ Phonetv.setText(string);}
    }


}