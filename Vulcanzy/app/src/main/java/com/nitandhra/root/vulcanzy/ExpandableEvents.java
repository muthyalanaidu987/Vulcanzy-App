package com.nitandhra.root.vulcanzy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Html;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class ExpandableEvents extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> header;
    private HashMap<String,List<String>> child;
    private String pressed_str;
    private LinearLayout l;
    public ExpandableEvents(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this.header = listDataHeader;
        this.pressed_str=new String();
        this.child = listChildData;

    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        // This will return the child
        return this.child.get(this.header.get(groupPosition)).get(
                childPosititon);
    }@Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, final ViewGroup parent ) {

        // Getting child text
        final String childText = (String) getChild(groupPosition, childPosition);

        // Inflating child layout and setting textview
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.events_child,null);
        }
        EditText child_text = (EditText) convertView.findViewById(R.id.childe);
        Button bc=(Button)convertView.findViewById(R.id.moreb);
        bc.setText("More Details...");
        Button reg=(Button)convertView.findViewById(R.id.childb);
        reg.setText("Register");
        TextView tv=(TextView)convertView.findViewById(R.id.childtv);
        tv.setText("Description:");
        LinearLayout ll=(LinearLayout)convertView.findViewById(R.id.deslayout);
        ll.setVisibility(View.GONE);
        this.l=ll;
        TextView eml=(TextView)this.l.findViewById(R.id.email);
        eml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Linkify.addLinks(eml,Linkify.EMAIL_ADDRESSES);
            }
        });

        Button call=(Button)this.l.findViewById(R.id.con);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cal=call.getText().toString().substring(8,19);
                Intent i=new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+cal));
                _context.startActivity(i);
            }
        });
        Context con=this._context;
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    register();
                }
                catch (Exception e){}
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setll(pressed_str);
                if(ll.getVisibility()==View.VISIBLE) {
                    ll.setVisibility(View.GONE);
                    bc.setText("More Details...");
                }
                else {

                    ll.setVisibility(View.VISIBLE);
                    bc.setText("Less Details... ");
                }
            }
        });
        child_text.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {

                if (view.getId() == R.id.childe) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction()&MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }

            });
        Typeface myTypeface = Typeface.createFromAsset(convertView.getContext().getAssets(), "myfont.otf");
        child_text.setTypeface(myTypeface);
        child_text.setText(childText);
        return convertView;
    }
    @Override
    public int getChildrenCount(int groupPosition) {

        // return children count
        return this.child.get(this.header.get(groupPosition)).size();
    }
    @Override
    public Object getGroup(int groupPosition) {

        // Get header position
        return this.header.get(groupPosition);
    }
    @Override
    public int getGroupCount() {

        // Get header size
        return this.header.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        // Getting header title
        String headerTitle = (String) getGroup(groupPosition);


        // Inflating header layout and setting text
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.event_header, parent, false);
        }
        TextView header_text = (TextView) convertView.findViewById(R.id.headere);
        Typeface myTypeface = Typeface.createFromAsset(convertView.getContext().getAssets(), "myfonts.otf");
        header_text.setText(headerTitle);
        header_text.setTypeface(myTypeface);
        if (isExpanded) {

            header_text.setTypeface(myTypeface);
            header_text.setTextColor(Color.BLUE);
            header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.upex, 0);
            this.pressed_str = headerTitle;
        }

        else {
            // If group is not expanded then change the text back into normal
            // and change the icon
            header_text.setTextColor(Color.BLACK);
            header_text.setTypeface(myTypeface);
           header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.downex, 0);

        }
        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void register() throws Exception
    {
        try {
            Intent spla = new Intent(this._context,Webevent.class);
            spla.putExtra("name",this.pressed_str);
            this._context.startActivity(spla);

        }
        catch (Exception e){Toast.makeText(this._context,e.toString(),Toast.LENGTH_LONG).show();}


    }

    public void setll(String str)
    {
        String det[];
        switch (str) {

            case "COLLOQIUM":
            det = this.l.getResources().getStringArray(R.array.COLLOQUIUM);
            setviews(det);
            break;
            case "CRYPTOTACTEON":
                det = this.l.getResources().getStringArray(R.array.CRYPTACTAEON);
                setviews(det);
                break;
            case "CODE SPRINT":
                det = this.l.getResources().getStringArray(R.array.CODESPRINT);
                setviews(det);
                break;
            case "VIRTUALLY TRUE":
                det = this.l.getResources().getStringArray(R.array.VIRTUALLYTRUE);
                setviews(det);
                break;
            case "WORKSHOP ON CRYPTOGRAPHY":
                det = this.l.getResources().getStringArray(R.array.WCRYPTOGRAPHY);
                setviews(det);
                break;


                //////////// ece
            case "FORESEE-the 4C's":
                det = this.l.getResources().getStringArray(R.array.FORESEEthe4CS);
                setviews(det);
                break;
            case "WORKSHOP":
                det = this.l.getResources().getStringArray(R.array.WORKSHOP);
                setviews(det);
                break;
            case "ELECTRO WIZARD":
                det = this.l.getResources().getStringArray(R.array.ELECTROWIZARD);
                setviews(det);
                break;
            case "PAPER PRESENTATION":
                det = this.l.getResources().getStringArray(R.array.PAPERPRESENTATION);
                setviews(det);
                break;

            case "CAZZLE":
                det = this.l.getResources().getStringArray(R.array.CAZZLE);
                setviews(det);
                break;
            case "QUIZ":
                det = this.l.getResources().getStringArray(R.array.QUIZ);
                setviews(det);
                break;
            case "MURKY MAZE":
                det = this.l.getResources().getStringArray(R.array.MURKYMAZE);
                setviews(det);
                break;

                ////////// eee
            case "ALL ABOUT CIRCUITS":
                det = this.l.getResources().getStringArray(R.array.ALLABOUTCIRCUITS);
                setviews(det);
                break;
            case "ARCHIPELAGO":
                det = this.l.getResources().getStringArray(R.array.ARCHIPELAGO);
                setviews(det);
                break;
            case "BACK TO THE ORIGINS":
                det = this.l.getResources().getStringArray(R.array.BACKTOTHEORIGINS);
                setviews(det);
                break;
            case "AMALGAMATE":
                det = this.l.getResources().getStringArray(R.array.AMALGAMATE);
                setviews(det);
                break;

            case "DECEPTION":
                det = this.l.getResources().getStringArray(R.array.DECEPTION);
                setviews(det);
                break;

            case "PAPER  PRESENTATION":
                det = this.l.getResources().getStringArray(R.array.EPAPERPRESENTATION);
                setviews(det);
                break;

            case "PROJECT EXPO":
                det = this.l.getResources().getStringArray(R.array.PROJECTEXPO);
                setviews(det);
                break;
                //////civil

            case "POPTICLES":
                det = this.l.getResources().getStringArray(R.array.CRACKTHESTRUCTURE);
                setviews(det);
                break;
            case "CRACK THE STRUCTURE":
                det = this.l.getResources().getStringArray(R.array.CIVILOPEDIA);
                setviews(det);
                break;
            case "CON-CREATE":
                det = this.l.getResources().getStringArray(R.array.CONCREATE);
                setviews(det);
                break;
            case "DE PRESENTA":
                det = this.l.getResources().getStringArray(R.array.PAPERPRESENTO);
                setviews(det);
                break;
            case "LA TECQUILA":
                det = this.l.getResources().getStringArray(R.array.LA);
                setviews(det);
                break;



            case "ASK ME ANYTHING":
                det = this.l.getResources().getStringArray(R.array.askme);
                setviews(det);
                break;
            case "VILLE INTELLIGENCE":
                det = this.l.getResources().getStringArray(R.array.ville);
                setviews(det);
                break;
            ////////// mme


            case "ONE THRUST 2.0":
                det = this.l.getResources().getStringArray(R.array.OneThrust);
                setviews(det);
                break;
            case "RIDDLE HURDLES":
                det = this.l.getResources().getStringArray(R.array.Riddlehurdles);
                setviews(det);
                break;
            case "WAX MOCK-UP":
                det = this.l.getResources().getStringArray(R.array.Waxmockup);
                setviews(det);
                break;
            case "WHATS'S BEYOND":
                det = this.l.getResources().getStringArray(R.array.Whatsbeyond);
                setviews(det);
                break;

                /////// bio

            case "FORENSICS V2":
                det = this.l.getResources().getStringArray(R.array.Forensicsv2);
                setviews(det);
                break;
            case "GARDEN SCAVENGERS":
                det = this.l.getResources().getStringArray(R.array.Gardenscavengers);
                setviews(det);
                break;
            case "LUMIERE":
                det = this.l.getResources().getStringArray(R.array.Lumiere);
                setviews(det);
                break;
            ////// mech


            case "AMMC(Aircraft Modelling and Maneuvering Challange)":
                det = this.l.getResources().getStringArray(R.array.AMMC);
                setviews(det);
                break;
            case "GISS(Godavari Innovation for Society Summit)":
                det = this.l.getResources().getStringArray(R.array.GISS);
                setviews(det);
                break;
            case "MARC(Mechanism and Robotics Championship)":
                det = this.l.getResources().getStringArray(R.array.MARC);
                setviews(det);
                break;
            case "ROBO-WAR":
                det = this.l.getResources().getStringArray(R.array.RoboWar);
                setviews(det);
                break;
            case "WORKSHOP AND QUIZZES":
                det = this.l.getResources().getStringArray(R.array.work);
                setviews(det);
                break;



            /////// music

            case "VULCANZY IDOL":
                det = this.l.getResources().getStringArray(R.array.Talenthunt);
                setviews(det);
                break;
            case "LYRICAL MAESTRO":
                det = this.l.getResources().getStringArray(R.array.lyricalmaestro);
                setviews(det);
                break;
            case "GUESS IT WIN IT":
                det = this.l.getResources().getStringArray(R.array.GuessitWinit);
                setviews(det);
                break;
            case "SONG SLAM":
                det = this.l.getResources().getStringArray(R.array.Instagramvideos);
                setviews(det);
                break;

                ///////pnp

            case "HAND PAINTING":
                det = this.l.getResources().getStringArray(R.array.HandPainting);
                setviews(det);
                break;
            case "ART IN LINE":
                det = this.l.getResources().getStringArray(R.array.ArtinLine);
                setviews(det);
                break;
            case "ART-A-THON":
                det = this.l.getResources().getStringArray(R.array.ARTATHON);
                setviews(det);
                break;
            case "PAINT WITHOUT BRUSH":
                det = this.l.getResources().getStringArray(R.array.PaintWithoutBrush);
                setviews(det);
                break;
            case "Ad MAKING COMPETETION":
                det = this.l.getResources().getStringArray(R.array.ADMAKING);
                setviews(det);
                break;
            case "PHOTO CONTEST":
                det = this.l.getResources().getStringArray(R.array.PHOTOCONTEST);
                setviews(det);
                break;
            case "MANNEQUIN_CHALLENGE":
                det = this.l.getResources().getStringArray(R.array.MANNEQUIN);
                setviews(det);
                break;


            //////// dsh

            case "VAN DE GRAFF GENERATOR":
                det = this.l.getResources().getStringArray(R.array.VanDeGraffGenerator);
                setviews(det);
                break;
            case "LANTERN MAKING":
                det = this.l.getResources().getStringArray(R.array.LANTERNMAKING);
                setviews(det);
                break;
            case "LAZY HOVER V1.0":
                det = this.l.getResources().getStringArray(R.array.LAZYHOVERV);
                setviews(det);
                break;
            case "BEST OUT OF WASTE":
                det = this.l.getResources().getStringArray(R.array.BESTOUTOFWASTE);
                setviews(det);
                break;

            case "RUN TO WIN":
                det = this.l.getResources().getStringArray(R.array.RUNTOWIN);
                setviews(det);
                break;



            ///////// dnd


            case "MEME-FINITY WAR":
                det = this.l.getResources().getStringArray(R.array.MEMEfinitywar);
                setviews(det);
                break;
            case "BOOMERANG":
                det = this.l.getResources().getStringArray(R.array.Boomerang);
                setviews(det);
                break;
            case "TELL A TALE":
                det = this.l.getResources().getStringArray(R.array.TellaTale);
                setviews(det);
                break;
            case "SHORT FILM":
                det = this.l.getResources().getStringArray(R.array.Shortfilm);
                setviews(det);
                break;
            case "DANCE COMPETETION":
                det = this.l.getResources().getStringArray(R.array.dancecomp);
                setviews(det);
                break;
            case "DRAMA COMPETETION":
                det = this.l.getResources().getStringArray(R.array.dramacomp);
                setviews(det);
                break;




                //////// magazine
            case "BOOK FIE":
                det = this.l.getResources().getStringArray(R.array.Bookfie);
                setviews(det);
                break;
            case "ILLUSION":
                det = this.l.getResources().getStringArray(R.array.Illusion);
                setviews(det);
                break;



                ////chem
            case "LECTURES":
                det = this.l.getResources().getStringArray(R.array.LECTURES);
                setviews(det);
                break;
            case "AlCHEMY":
                det = this.l.getResources().getStringArray(R.array.AlChemy);
                setviews(det);
                break;
            case "ExQuizite":
                det = this.l.getResources().getStringArray(R.array.ExQuizite);
                setviews(det);
                break;
            case "Pheonix":
                det = this.l.getResources().getStringArray(R.array.Pheonix);
                setviews(det);
                break;
            case "BlastDarts":
                det = this.l.getResources().getStringArray(R.array.BlastDarts);
                setviews(det);
                break;
            case "QuickG":
                det = this.l.getResources().getStringArray(R.array.QuickG);
                setviews(det);
                break;

        }

    }
    public void setviews(String det[])
    {
        TextView fee=(TextView)this.l.findViewById(R.id.fee);
        TextView org=(TextView)this.l.findViewById(R.id.org);
        TextView email=(TextView)this.l.findViewById(R.id.email);
        Button con=(Button) this.l.findViewById(R.id.con);
        fee.setText(Html.fromHtml("<b>Fee:  </b>"+"<i>"+det[1]+"</i>"));
        org.setText(Html.fromHtml("<b>Organisers:  </b>"+"<i>"+det[3]+"</i>"));
        con.setText(Html.fromHtml("<b>Contact:  </b>"+"<i>"+det[4]+"</i>"));
        email.setText(Html.fromHtml("<b>Email:  </b>"+"<i>"+det[5]+"</i>"));
    }
    }

