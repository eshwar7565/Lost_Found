package eshwar.user.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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


public class dataforfound extends AppCompatActivity {


    private RecyclerView postfoundlist ;
    private DatabaseReference postref, UserRef;
    private FirebaseAuth mAuth;
    private FirebaseRecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    String current_user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataforfound);
        getSupportActionBar().setTitle("FOUND ITEMS DATA");

        postfoundlist = (RecyclerView) findViewById(R.id.recyclerviewforfoundposts);
        postfoundlist.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        postfoundlist.setLayoutManager(linearLayoutManager);
        fetch();

        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Registered Users");


    }

    private void fetch() {
        Query query = FirebaseDatabase.getInstance().getReference().child("FoundItems");

        FirebaseRecyclerOptions<modelfoundposts> options =
                new FirebaseRecyclerOptions.Builder<modelfoundposts>().setQuery(query, new SnapshotParser<modelfoundposts>() {
                    @NonNull
                    @Override
                    public modelfoundposts parseSnapshot(@NonNull DataSnapshot snapshot) {

                        return new modelfoundposts(snapshot.child("FullName").getValue().toString(),
                                snapshot.child("Message").getValue().toString(),

                                snapshot.child("date").getValue().toString(),
                                snapshot.child("time").getValue().toString(),
                                snapshot.child("Imagelink").getValue().toString());


                    }
                }).build();

        adapter = new FirebaseRecyclerAdapter<modelfoundposts, viewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull modelfoundposts model) {
                holder.setDatetv(modelfoundposts.getDate());
                holder.setFullNametv(modelfoundposts.getFullName());
                holder.setMessagetv(modelfoundposts.getMessage());
                holder.setTimetv(modelfoundposts.getTime());
                Glide.with(holder.checkfoundimageIv.getContext()).load(modelfoundposts.getImagelink()).into(holder.checkfoundimageIv);


            }








            @NonNull
            @Override
            public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.founditemsviewstyle, parent, false);
                return new viewHolder(view) {
                };
            }
        };
        postfoundlist.setAdapter(adapter);


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

        ImageView checkfoundimageIv;
        TextView FullNametv, datetv, timetv, Messagetv;
        Button Claimbutton;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            //init views

            root = itemView.findViewById(R.id.list_root);
            checkfoundimageIv = itemView.findViewById(R.id.checkfoundimageviewIv);
            FullNametv = itemView.findViewById(R.id.checkfoundusername);

            datetv = itemView.findViewById(R.id.checkfounddate);
            timetv = itemView.findViewById(R.id.checkfoundtime);
            Messagetv = itemView.findViewById(R.id.check_found_message);
            Claimbutton = itemView.findViewById(R.id.Claimbutton);


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
    }
}