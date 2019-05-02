package com.nitandhra.root.vulcanzy;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

public class GalleryFragment extends Fragment {

 @Nullable
 @Override
 public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState )
 {
  AboutVulcanzy.state="closed";
  View v=inflater.inflate(R.layout.fragment_gallery,container,false );
  WebView web=(WebView)v.findViewById(R.id.gallery);
  web.getSettings().setJavaScriptEnabled(true);
  Button btn=(Button)v.findViewById(R.id.backbtn);
  web.loadUrl("https://amitsai28.github.io/Vulcanzy-2k19/gallerymain.html");
  ProgressBar pbar=(ProgressBar)v.findViewById(R.id.gpbar);
  web.getSettings().setLoadsImagesAutomatically(true);
  web.setWebViewClient(new WebViewClient() {

   @Override
   public void onPageStarted(WebView view, String url, Bitmap favicon) {


    super.onPageStarted(view, url, favicon);
    pbar.setVisibility(View.VISIBLE);


   }

   @Override
   public void onPageFinished(WebView view, String url) {
    super.onPageFinished(view, url);
    pbar.setVisibility(View.GONE);
   }
  });

  btn.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {
    FragmentManager fm=getActivity().getSupportFragmentManager();
    fm.beginTransaction().replace(R.id.frame_container,new AboutVulcanzyFragment()).commit();
   }
  });
  return v;
 }

}
