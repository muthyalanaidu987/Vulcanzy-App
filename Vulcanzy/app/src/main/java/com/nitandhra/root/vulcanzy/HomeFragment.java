package com.nitandhra.root.vulcanzy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class HomeFragment extends Fragment {
 private ImageButton biotechib,civilib,cseib,eceib,eeeib,mechib,mmeib,musicib,magzib,dshib,ddib,pnpib,chemib;
 static String pressed_branch;
 @Nullable
 @Override
 public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {


  View view=inflater.inflate(R.layout.fragment_home,container,false );
  AboutVulcanzy.state="closed";
  biotechib=(ImageButton)view.findViewById(R.id.biotechib);
  civilib=(ImageButton)view.findViewById(R.id.civilib);
  cseib=(ImageButton)view.findViewById(R.id.cseib);
  eceib=(ImageButton)view.findViewById(R.id.eceib);
  eeeib=(ImageButton)view.findViewById(R.id.eeeib);
  mechib=(ImageButton)view.findViewById(R.id.mechib);
  mmeib=(ImageButton)view.findViewById(R.id.mmeib);
  musicib=(ImageButton)view.findViewById(R.id.musicib);
  magzib=(ImageButton)view.findViewById(R.id.magzib);
  dshib=(ImageButton)view.findViewById(R.id.dshib);
  ddib=(ImageButton)view.findViewById(R.id.ddib);
  pnpib=(ImageButton)view.findViewById(R.id.pnpib);
  chemib=(ImageButton)view.findViewById(R.id.chemib);


  biotechib.setOnClickListener( new View.OnClickListener() {
   @Override
   public void onClick ( View v ) {
    gotoEvents( "biotech" ); }
  } );
  chemib.setOnClickListener( new View.OnClickListener() {
   @Override
   public void onClick ( View v ) {
    gotoEvents( "chem" ); }
  } );

  civilib.setOnClickListener( new View.OnClickListener() {
   @Override
   public void onClick ( View v ) {
    gotoEvents( "civil" );
   }
  } );

  cseib.setOnClickListener( new View.OnClickListener() {
   @Override
   public void onClick ( View v ) {
    gotoEvents( "cse" );
   }
  } );

  eceib.setOnClickListener( new View.OnClickListener() {
   @Override
   public void onClick ( View v ) {
    gotoEvents( "ece" );
   }
  } );


  eeeib.setOnClickListener( new View.OnClickListener() {
   @Override
   public void onClick ( View v ) {
    gotoEvents( "eee" );
   }
  } );


  mechib.setOnClickListener( new View.OnClickListener() {
   @Override
   public void onClick ( View v ) {
    gotoEvents( "mech" );
   }
  } );


  mmeib.setOnClickListener( new View.OnClickListener() {
   @Override
   public void onClick ( View v ) {
    gotoEvents( "mme" );
   }
  } );


  musicib.setOnClickListener( new View.OnClickListener() {
   @Override
   public void onClick ( View v ) {
    gotoEvents( "music" );
   }
  } );


  magzib.setOnClickListener( new View.OnClickListener() {
   @Override
   public void onClick ( View v ) {
    gotoEvents( "magazine" );
   }
  } );


  dshib.setOnClickListener( new View.OnClickListener() {
   @Override
   public void onClick ( View v ) {
    gotoEvents( "dsh" );
   }
  } );


  ddib.setOnClickListener( new View.OnClickListener() {
   @Override
   public void onClick ( View v ) {
    gotoEvents( "dnd" );
   }
  } );

  pnpib.setOnClickListener( new View.OnClickListener() {
   @Override
   public void onClick ( View v ) {
    gotoEvents( "pnp" );
   }
  } );

  return view;
 }

 public void gotoEvents(String s)
 {
  HomeFragment.pressed_branch=s;
  Intent intent=new Intent(getActivity(),Csea_events.class);
  intent.putExtra( "Club",s);
  startActivity( intent );
 }

}
