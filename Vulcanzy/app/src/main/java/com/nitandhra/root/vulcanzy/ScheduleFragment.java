package com.nitandhra.root.vulcanzy;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScheduleFragment extends Fragment {

 @Nullable
 @Override
 public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
  View view=inflater.inflate(R.layout.fragment_schedule,container,false );
  TextView tv1=(TextView)view.findViewById(R.id.date1);
     AboutVulcanzy.state="closed";
     TextView tv2=(TextView)view.findViewById(R.id.date2);
     TextView tv3=(TextView)view.findViewById(R.id.date3);
  Typeface myTypeface = Typeface.createFromAsset(view.getContext().getAssets(), "CaviarDreams.ttf");
  tv1.setTypeface(myTypeface);
  tv1.setText(Html.fromHtml(      "<pre><b>22-March-2019</b>   <i>Friday</i>"));
     tv2.setTypeface(myTypeface);
     tv2.setText(Html.fromHtml(      "<pre><b>23-March-2019</b>   <i>Saturday</i>"));
     tv3.setTypeface(myTypeface);
     tv3.setText(Html.fromHtml(      "<pre><b>24-March-2019</b>   <i>Sunday</i>"));
  return view;
 }

}
