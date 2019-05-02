package com.nitandhra.root.vulcanzy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class Signup extends AppCompatActivity {

    Button reg;
    LinearLayout lay;
    EditText uname,fname,eml,mob,pass,cpass,coll;
    RadioGroup rg;
    RadioButton male,female;
    FirebaseDatabase database;
    DatabaseReference ref;
    String userid;

    static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        lay=(LinearLayout)findViewById(R.id.signups);
        reg=(Button)findViewById(R.id.register);
        uname=(EditText)findViewById(R.id.username);
        fname=(EditText)findViewById(R.id.fullname);
        eml=(EditText)findViewById(R.id.email);
        mob=(EditText)findViewById(R.id.mobile);
        pass=(EditText)findViewById(R.id.password);
        cpass=(EditText)findViewById(R.id.cpassword);
        coll=(EditText)findViewById(R.id.college);
        rg=(RadioGroup)findViewById(R.id.gender);
        male=(RadioButton)findViewById(R.id.male);
        female=(RadioButton)findViewById(R.id.female);
        EditText e[]=new EditText[]{uname,fname,eml,mob,pass,cpass,coll};
        uname.setHintTextColor(Color.WHITE);
        userid=Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        for(int i=0;i<e.length;i++)
        {
            e[i].setHintTextColor(Color.WHITE);
        }
        lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                // Hide the soft keyboard
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                ref = database.getReference("register");
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean alr=true;
                        for(DataSnapshot snapshot:dataSnapshot.getChildren())
                        {
                            User u=snapshot.getValue(User.class);
                            if(u.getUsername().equals(uname.getText().toString()))
                            {

                                Toast.makeText(getApplicationContext(),"Username already Taken",Toast.LENGTH_LONG).show();
                                alr=false;
                                break;
                            }
                        }
                        if(alr)
                            registeruser();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
    }
    public void registeruser()
    {
        String s1=pass.getText().toString();
        String s2=cpass.getText().toString();
        Boolean val=s1.equals(s2);
        Boolean alright=true;
        String gend=new String();
        if(!isValid(eml.getText().toString()))
        {
            eml.setError("Invalid Email");
            Toasty.error(this,"Invalid Email Format",Toast.LENGTH_LONG).show();
            alright=false;
        }
        if(!isValidMob(mob.getText().toString()))
        {
            mob.setError("Invalid Mobile Number");
            Toasty.error(this,"Invalid Mobile Format",Toast.LENGTH_LONG).show();
            alright=false;
        }
        if(pass.length()<8)
        {
            Toasty.error(this,"Password should be atleast 8 characters long",Toast.LENGTH_LONG).show();
            alright=false;
        }
        if (!val)
        {
            Toast.makeText(this,"Passwords Doesn't Match",Toast.LENGTH_LONG).show();
            cpass.setText("");
            cpass.setError("no match");
            pass.setError("no match");
            pass.setText("");
            alright=false;
        }

        if(rg.getCheckedRadioButtonId()==male.getId())
        {
            gend="M";
        }
        else if(rg.getCheckedRadioButtonId()==female.getId())
        {
            gend="F";
        }
        else
        {
            alright=false;
            Toast.makeText(this,"Select Gender",Toast.LENGTH_LONG).show();
        }

        if(alright)
        {

            User user=new User(uname.getText().toString(),fname.getText().toString(),eml.getText().toString(),
                    mob.getText().toString(),pass.getText().toString(),coll.getText().toString(),gend);
            userid=Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
            ref.child(userid).setValue(user).
                    addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toasty.success(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_LONG).show();
                        }
                    });;
            addUserChangeListener();
        }
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public static boolean isValidMob(String s)
    {
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }
    public void addUserChangeListener() {
        // User data change listener
        ref.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user == null) {
                    Toast.makeText(getApplicationContext(),"some error",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent spla = new Intent(Signup.this,HomeActivity.class);
                startActivity(spla);
                finish();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Connection Error Try Again",Toast.LENGTH_LONG).show();
            }
        });
    }

}
