package com.nitandhra.root.vulcanzy;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class AboutVulcanzyFragment extends Fragment {
 TextView tvaboutvul;
 WebView wb;
    private float[] lastTouchDownXY = new float[2];
    String str="Vulcanzy, the annual techno-cultural fest of NIT-AP is the most awaited event of the year . " +
         "Guided by Vulcan, the Roman God of Fire, the idea of Vulcanzy unravels the big aspirations hidden inside a" +
         " creative mind and promises the ultimate platform to showcase everyones hidden talents. This is the perfect d" +
         "estination to challenge one’s technical ability and groove to trending tunes. It’s time to relax , " +
         "take a break from hectic schedules and participate in the various activities that are in store.";

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getActionMasked() == 0) {
                AboutVulcanzyFragment.this.lastTouchDownXY[0] = motionEvent.getX();
                AboutVulcanzyFragment.this.lastTouchDownXY[1] = motionEvent.getY();
                float f = AboutVulcanzyFragment.this.lastTouchDownXY[0];
                f = AboutVulcanzyFragment.this.lastTouchDownXY[1];
            }
            return false;
        }
    };
 @Nullable
 @Override
 public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
  View view=inflater.inflate(R.layout.fragment_about_vulcanzy,container,false );
  Typeface myTypeface = Typeface.createFromAsset(view.getContext().getAssets(), "CaviarDreams.ttf");
  AboutVulcanzy.state="closed";
  tvaboutvul=(TextView)view.findViewById(R.id.tvabout_vulcanzy);
  tvaboutvul.setText(str);
  tvaboutvul.setTypeface(myTypeface);
  Button gov=(Button)view.findViewById(R.id.events);
     ObjectAnimator anim = ObjectAnimator.ofInt(gov, "backgroundColor",
             Color.parseColor("#7FFFD4"),Color.parseColor("#EE82EE"));
  anim.setDuration(1500);
  anim.setEvaluator(new ArgbEvaluator());
  anim.setRepeatCount(Animation.INFINITE);
  anim.start();
  wb=(WebView)view.findViewById(R.id.part);
  wb.loadUrl("file:///android_asset/www/index.html");
  wb.getSettings().setJavaScriptEnabled(true);
  wb.setOnTouchListener(this.touchListener);







     gov.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {
    FragmentManager fm=getActivity().getSupportFragmentManager();
    fm.beginTransaction().replace(R.id.frame_container,new HomeFragment()).commit();
   }
  });
  return view;
 }

}
