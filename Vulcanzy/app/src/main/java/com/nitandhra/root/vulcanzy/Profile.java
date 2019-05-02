package com.nitandhra.root.vulcanzy;

import android.graphics.Typeface;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Fragment {
    TextView p0,p1,p2,p3,p4,p5,uname;
    FirebaseDatabase database;
    TextView name,colg,eml,mob,gend;
    DatabaseReference ref;
    ProgressBar pb;
    User u;
    public View onCreateView (@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        p0=(TextView)view.findViewById(R.id.p0);
        p1=(TextView)view.findViewById(R.id.p1);
        AboutVulcanzy.state="closed";
        pb=(ProgressBar)view.findViewById(R.id.pbar);
        p2=(TextView)view.findViewById(R.id.p2);
        p3=(TextView)view.findViewById(R.id.p3);
        p4=(TextView)view.findViewById(R.id.p4);
        p5=(TextView)view.findViewById(R.id.p5);
        name=(TextView) view.findViewById(R.id.name);
        colg=(TextView) view.findViewById(R.id.college);
        eml=(TextView) view.findViewById(R.id.mail);
        mob=(TextView) view.findViewById(R.id.mobile);
        gend=(TextView) view.findViewById(R.id.gender);
        uname=(TextView)view.findViewById(R.id.username);
        TextView p[]=new TextView[]{p0,p1,p2,p3,p4,p5,uname,name,colg,eml,mob,gend};
        for(int i=0;i<p.length;i++)
        {
            Typeface myTypeface = Typeface.createFromAsset(view.getContext().getAssets(), "CaviarDreams.ttf");
            p[i].setTypeface(myTypeface);
            p[i].setVisibility(View.GONE);
        }
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("register");
        ref.child(Splash.deviceid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    u=dataSnapshot.getValue(User.class);
                    uname.setText(u.getUsername());
                    name.setText(u.getName());
                    colg.setText(u.getClg_name());
                    eml.setText(u.getEmail());
                    mob.setText(u.getPhone_number());
                    gend.setText(u.getGender());
                for(int i=0;i<p.length;i++)
                {
                    p[i].setVisibility(View.VISIBLE);
                }
                pb.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}
