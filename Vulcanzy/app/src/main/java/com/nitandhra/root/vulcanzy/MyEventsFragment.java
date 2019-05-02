package com.nitandhra.root.vulcanzy;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyEventsFragment extends Fragment
{
    ExpandableListView lv;
    ExpandableListAdapter expandableListAdapter;
    HashMap<String, List<String>> expandableListDetail;
    List<String> expandableListTitle;
    FirebaseDatabase database;
    DatabaseReference ref;
    DatabaseReference eref;
    List<List<String>>
    int i=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_my_events, container,false);
        AboutVulcanzy.state="closed";
        lv=(ExpandableListView) view.findViewById(R.id.exp_list_view);
        expandableListDetail = new HashMap<String, List<String>>();

        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter=new CustomExpandableListAdapter(this.getContext(),expandableListTitle,expandableListDetail);
        lv.setAdapter(expandableListAdapter);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("register");
        add();
        return view;
    }
    public void add()
    {
        cse=new ArrayList<String>();
        ece=new ArrayList<String>();
        eee=new ArrayList<String>();
        mech=new ArrayList<String>();
        l=new ArrayList<>();
        l.add(cse);
        l.add(ece);
        l.add(eee);
        l.add(mech);
        ref.child(Splash.deviceid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User u=dataSnapshot.getValue(User.class);
                eref = database.getReference("cse");
                eref.child(u.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()){
                            cse.add(uniqueKeySnapshot.getKey());
                            Log.e("ch",uniqueKeySnapshot.getKey());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        expandableListDetail.put("CSE",cse);
        expandableListDetail.put("ECE",ece);
        expandableListDetail.put("EEE",eee);
        expandableListDetail.put("MECH",mech);
    }
    public void addall(String n)
    {

       Log.e("bra",n);
        String branch[]=new String[]{"cse","ece","eee","mech"};
    }
}
