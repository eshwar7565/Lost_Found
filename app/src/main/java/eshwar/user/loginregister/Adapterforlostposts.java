package eshwar.user.loginregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapterforlostposts extends RecyclerView.Adapter<Adapterforlostposts.MylostpostHolder> {

    Context context;
    List <modellostposts> lostpostlist;

    public Adapterforlostposts(Context context, List<modellostposts> lostpostlist) {
        this.context = context;
        this.lostpostlist = lostpostlist;
    }

    public Adapterforlostposts() {

    }

    @NonNull
    @Override
    public MylostpostHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //inflate layout lostitemspostviewstyle

        View view = LayoutInflater.from(context).inflate(R.layout.lostitemspostviewstyle,viewGroup ,false);

        return new MylostpostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MylostpostHolder holder, int i) {
        String FullName = lostpostlist.get(i).getFullName();
        String Imagelink = lostpostlist.get(i).getImagelink();
        String Location = lostpostlist.get(i).getLocation();
        String Message = lostpostlist.get(i).getMessage();
        String date = lostpostlist.get(i).getDate();
        String time = lostpostlist.get(i).getTime();
        String uid = lostpostlist.get(i).getUid();



        holder.FullNametv.setText(FullName);
        holder.Locationtv.setText(Location);
        holder.Messagetv.setText(Message);
        holder.datetv.setText(date);
        holder.timetv.setText(time);



        try {
            Picasso.get().load(Imagelink).into(holder.checklostimageIv);
        }
        catch (Exception e ) {

        }



    }

    @Override
    public int getItemCount() {
        return 7;
    }




        //view holder class


    class MylostpostHolder extends RecyclerView.ViewHolder{



        //views from xml file

        ImageView checklostimageIv;
        TextView FullNametv,Locationtv,datetv,timetv,Messagetv;
        Button FoundButton ;


        public MylostpostHolder(@NonNull View itemView) {
            super(itemView);
            //init views


            checklostimageIv = itemView.findViewById(R.id.checklostimageviewIv);
            FullNametv = itemView.findViewById(R.id.checklostusername);
            Locationtv = itemView.findViewById(R.id.locationlost);
            datetv = itemView.findViewById(R.id.checklostdate);
            timetv = itemView.findViewById(R.id.checklosttime);
            Messagetv = itemView.findViewById(R.id.check_lost_message);
            FoundButton  = itemView.findViewById(R.id.Ifounditbutton);




        }
    }
}
