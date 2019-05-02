package com.nitandhra.root.vulcanzy;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Splash extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;
    String userid;
    static String deviceid;
    boolean val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        userid=Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        Splash.deviceid=userid;
        setContentView(R.layout.activity_splash);
        try {
            //GifImageView gf=(GifImageView)findViewById(R.id.gif);
            //gf.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),"App requires Internet Connection",Toast.LENGTH_LONG).show();
        }

        catch (Exception e){}
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent spla = new Intent(Splash.this, HomeActivity.class);
                startActivity(spla);
                finish();
                //notRegistered();
            }
        },500);
    }
   public void notRegistered()
   {
       database = FirebaseDatabase.getInstance();
       ref = database.getReference("register");
       userid=Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
       Splash.deviceid=userid;
       ref.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {]
               if(dataSnapshot.getValue(User.class)!=null)
               {
                   Intent spla = new Intent(Splash.this, HomeActivity.class);
                   startActivity(spla);
                   finish();
                   val=false;
               }
               else
               {
                   Intent spla = new Intent(Splash.this, Signup.class);
                   startActivity(spla);
                   finish();
                   val=true;
               }
           }
           @Override
           public void onCancelled(DatabaseError error) {

           }
       });
   }
}

