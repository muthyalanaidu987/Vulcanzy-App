package com.nitandhra.root.vulcanzy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class Webevent extends AppCompatActivity {

    TextView ename;
    WebView web;
    ProgressBar pb;
    FirebaseDatabase database;
    DatabaseReference ref;
    String userid;
    String user;
    String title;
    Document document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar ab = getSupportActionBar();
        TextView tv = new TextView(getApplicationContext());
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00BFFF")));
        Intent i = getIntent();
        title = i.getStringExtra("name");
        // Create a LayoutParams for TextView
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        tv.setLayoutParams(lp);
        // Set text to display in TextView
        tv.setText(title);
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundColor(Color.parseColor("#00BFFF"));
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD_ITALIC);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(20);
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ab.setTitle(title);
        ab.setCustomView(tv);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webevent);
        Button back = (Button) findViewById(R.id.backreg);
        pb=(ProgressBar)findViewById(R.id.pbars);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////////////////////////////s
                finish();
            }
        });
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("register");
        userid=Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        ref.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(User.class) != null) {

                    User u = dataSnapshot.getValue(User.class);
                    Log.e("names", u.getUsername());
                    user=u.getUsername();
                    //Toast.makeText(getApplicationContext(), u.getUsername(), Toast.LENGTH_LONG).show();
                    switch (HomeFragment.pressed_branch) {
                        case "biotech":
                            chromes("biotech");
                            break;
                        case "civil":
                            chromes("civil");
                            break;
                        case "cse":
                            chromes("cse");
                            break;
                        case "eee":
                            chromes("eee");
                            break;
                        case "ece":
                            chromes("ece");
                            break;
                        case "mech":
                            chromes("mech");
                            break;
                        case "mme":
                            chromes("mme");
                            break;
                        case "music":
                            chromes("music");
                            break;
                        case "magazine":
                            chromes("magazine");
                            break;
                        case "dsh":
                            chromes("dsh");
                            break;
                        case "dnd":
                            chromes("dd");
                            break;
                        case "pnp":
                            chromes("pnp");
                            break;
                        case "chem":
                            chromes("chem");
                            break;
                    }

                } else {
                    //Toast.makeText(getApplicationContext(),"hj",Toast.LENGTH_LONG).show();
                    user = null;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
    public void chromes(String branch)
    {
        TextView tv=(TextView)findViewById(R.id.ack);
        DatabaseReference eref=database.getReference(branch);
        String event=title.toLowerCase().replace(" ","_");
        eref.child(user).child(event).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.getValue()!=null)
            {

                tv.setText("Already Registered for "+title);
                pb.setVisibility(View.GONE);

            }
            else
            {
                eref.child(user).child(event).setValue("true")
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                tv.setText("Registered Successfully for "+title);
                                Toasty.success(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                                pb.setVisibility(View.GONE);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_LONG).show();
                            }
                        });

                eref.child(user).child("username").setValue(user);

            }
        }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });




    }
}

    class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }



