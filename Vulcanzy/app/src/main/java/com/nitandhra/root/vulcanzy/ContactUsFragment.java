package com.nitandhra.root.vulcanzy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContactUsFragment extends Fragment {
 TextView tvEmail;//tvfb,tvinsta;
 ImageButton imbfacebook,imbinsta;
 @Nullable
 @Override
 public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
  View view=inflater.inflate(R.layout.fragment_contact_us,container,false );
  tvEmail=view.findViewById(R.id.tvEmailUs);
  AboutVulcanzy.state="closed";
  imbfacebook=(ImageButton)view.findViewById(R.id.imbfb);
  imbinsta=(ImageButton)view.findViewById(R.id.imbinsta);
  Linkify.addLinks(tvEmail,Linkify.EMAIL_ADDRESSES);
  imbfacebook.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View view) {
    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/vulcanzy"));
    startActivity(intent);
   }
  });

  imbinsta.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View view) {
    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/vulcanzy/"));
    startActivity(intent);
   }
  });
  return view;
 }
}
