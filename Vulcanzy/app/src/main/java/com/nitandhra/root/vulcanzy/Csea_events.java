package com.nitandhra.root.vulcanzy;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Csea_events extends AppCompatActivity {
    ExpandableListAdapter listadapter;
    ExpandableListView explisview;
    List<String> events;
    HashMap<String ,List<String>> lists;
    String branch;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        ActionBar actionBar = getSupportActionBar();
        branch =getIntent().getStringExtra( "Club" );
        s=branch.toUpperCase()+" CLUB EVENTS";
        actionBar.setTitle(s);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00BFFF")));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csea_events);
        explisview=(ExpandableListView)findViewById(R.id.explistview);
        explisview.setGroupIndicator(null);
        LinearLayout ll=(LinearLayout)findViewById(R.id.deslayout);
        try {
            setItems(branch);
        }
        catch (Exception e){}
        setListener();
    }
    void setItems(String branch) throws IOException
    {

        ArrayList<String> header=new ArrayList<>();
        HashMap<String,List<String>> hashMap=new HashMap<>();
        if(branch.equals("cse"))
        {
            header.add("COLLOQIUM");
            header.add("CRYPTOTACTEON");
            header.add("CODE SPRINT");
            header.add("VIRTUALLY TRUE");
            header.add("WORKSHOP ON CRYPTOGRAPHY");
            String[] des=getResources().getStringArray(R.array.cse);
            for(int i=0;i<header.size();i++)
            {
                List<String> cse=new ArrayList<>();
                cse.add(des[i]);
                hashMap.put(header.get(i),cse);
            }
        }
        else if(branch.equals("ece"))
        {
            header.add("FORESEE-the 4C's");
            header.add("REAL TIME IMAGE PROCESSING WORKSHOP");
            header.add("ELECTRO WIZARD");
            header.add("QUIZ");
            header.add("CAZZLE");
            header.add("MURKY MAZE");
            header.add("PAPER PRESENTATION");
            String[] des=getResources().getStringArray(R.array.ece);
            for(int i=0;i<header.size();i++)
            {
                List<String> ece=new ArrayList<>();
                ece.add(des[i]);
                hashMap.put(header.get(i),ece);
            }

        }

        else if(branch.equals("eee"))
        {
            header.add("ALL ABOUT CIRCUITS");
            header.add("ARCHIPELAGO");
            header.add("BACK TO THE ORIGINS");
            header.add("AMALGAMATE");
            header.add("DECEPTION");
            header.add("PAPER  PRESENTATION");
            header.add("PROJECT EXPO");
            String[] des=getResources().getStringArray(R.array.eee);
            for(int i=0;i<header.size();i++)
            {
                List<String> eee=new ArrayList<>();
                eee.add(des[i]);
                hashMap.put(header.get(i),eee);
            }

        }


        else if(branch.equals("civil"))
        {
            header.add("POPTICLES");
            header.add("CRACK THE STRUCTURE");
            header.add("CON-CREATE");
            header.add("DE PRESENTA");
            header.add("ASK ME ANYTHING");
            header.add("VILLE INTELLIGENCE");
            header.add("LA TECQUILA");
            String[] des=getResources().getStringArray(R.array.civil);
            for(int i=0;i<header.size();i++)
            {
                List<String> civil=new ArrayList<>();
                civil.add(des[i]);
                hashMap.put(header.get(i),civil);
            }

        }


        else if(branch.equals("mme"))
        {
            header.add("ONE THRUST 2.0");
            header.add("RIDDLE HURDLES");
            header.add("WAX MOCK-UP");
            header.add("WHATS'S BEYOND");
            String[] des=getResources().getStringArray(R.array.mme);
            for(int i=0;i<header.size();i++)
            {
                List<String> mme=new ArrayList<>();
                mme.add(des[i]);
                hashMap.put(header.get(i),mme);
            }

        }



        else if(branch.equals("biotech"))
        {
            header.add("FORENSICS V2");
            header.add("GARDEN SCAVENGERS");
            header.add("LUMIERE");
            String[] des=getResources().getStringArray(R.array.biotech);
            for(int i=0;i<header.size();i++)
            {
                List<String> bio=new ArrayList<>();
                bio.add(des[i]);
                hashMap.put(header.get(i),bio);
            }

        }

        else if(branch.equals("mech"))
        {
            header.add("AMMC(Aircraft Modelling and Maneuvering Challange)");
            header.add("GISS(Godavari Innovation for Society Summit)");
            header.add("MARC(Mechanism and Robotics Championship)");
            header.add("ROBO-WAR");
            header.add("WORKSHOP AND QUIZZES");
            String[] des=getResources().getStringArray(R.array.mech);
            for(int i=0;i<header.size();i++)
            {
                List<String> mech=new ArrayList<>();
                mech.add(des[i]);
                hashMap.put(header.get(i),mech);
            }

        }




        else if(branch.equals("music"))
        {
            header.add("VULCANZY IDOL");
            header.add("LYRICAL MAESTRO");
            header.add("GUESS IT WIN IT");
            header.add("SONG SLAM");
            String[] des=getResources().getStringArray(R.array.music);
            for(int i=0;i<header.size();i++)
            {
                List<String> music=new ArrayList<>();
                music.add(des[i]);
                hashMap.put(header.get(i),music);
            }

        }


        else if(branch.equals("pnp"))
        {
            header.add("HAND PAINTING");
            header.add("ART IN LINE");
            header.add("ART-A-THON");
            header.add("PAINT WITHOUT BRUSH");
            header.add("Ad MAKING COMPETETION");
            header.add("PHOTO CONTEST");
            header.add("MANNEQUIN_CHALLENGE");
            String[] des=getResources().getStringArray(R.array.pnp);
            for(int i=0;i<header.size();i++)
            {
                List<String> pnp=new ArrayList<>();
                pnp.add(des[i]);
                hashMap.put(header.get(i),pnp);
            }

        }


        else if(branch.equals("magazine"))
        {
            header.add("BOOK FIE");
            header.add("ILLUSION");
            //   header.add("ART-A-THON");
//    header.add("PAINT WITHOUT BRUSH");
            String[] des=getResources().getStringArray(R.array.magazine);
            for(int i=0;i<header.size();i++)
            {
                List<String> magazine=new ArrayList<>();
                magazine.add(des[i]);
                hashMap.put(header.get(i),magazine);
            }

        }

        else if(branch.equals("dsh"))
        {
            header.add("BEST OUT OF WASTE");
            header.add("LAZY HOVER V1.0");
            header.add("VAN DE GRAFF GENERATOR");
            header.add("LANTERN MAKING");
            header.add("RUN TO WIN");
            String[] des=getResources().getStringArray(R.array.dsh);
            for(int i=0;i<header.size();i++)
            {
                List<String> snh=new ArrayList<>();
                snh.add(des[i]);
                hashMap.put(header.get(i),snh);
            }

        }


        else if(branch.equals("dnd"))
        {
            header.add("MEME-FINITY WAR");
            header.add("BOOMERANG");
            header.add("TELL A TALE");
            header.add("SHORT FILM");
            header.add("DANCE COMPETETION");
            header.add("DRAMA COMPETETION");
            String[] des=getResources().getStringArray(R.array.dnd);
            for(int i=0;i<header.size();i++)
            {
                List<String> DND=new ArrayList<>();
                DND.add(des[i]);
                hashMap.put(header.get(i),DND);
            }

        }
        else if(branch.equals("chem"))
        {
            header.add("LECTURES");
            header.add("AlCHEMY");
            header.add("ExQuizite");
            header.add("Pheonix");
            header.add("BlastDarts");
            header.add("QuickG");
            String[] des=getResources().getStringArray(R.array.chem);
            for(int i=0;i<header.size();i++)
            {
                List<String> chem=new ArrayList<>();
                 chem.add(des[i]);
                hashMap.put(header.get(i),chem);
            }
        }

        listadapter=new ExpandableEvents(Csea_events.this,header,hashMap);
        explisview.setAdapter(listadapter);
           }
    void setListener() {
        // This listener will show toast on group click
        explisview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView listview, View view,
                                        int group_pos, long id) {

                return false;
            }
        });

        // This listener will expand one group at one time
        // You can remove this listener for expanding all groups
        explisview
                .setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                    // Default position
                    int previousGroup = -1;

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if (groupPosition != previousGroup)

                            // Collapse the expanded group
                            explisview.collapseGroup(previousGroup);
                        previousGroup = groupPosition;
                    }

                });

        // This listener will show toast on child click
        explisview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {
                return false;
            }
        });
    }

}
