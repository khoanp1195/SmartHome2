package com.example.firebasetest.Login1;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.firebasetest.R;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class Mainactivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

/*------------------------------------Drawer Menu-------------------------- */
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView tv_name, textView;

    CircleImageView profileImage;
/*------------------Login----------------------------------------*/
    private static final int GALLERY_INTENT_CODE = 1023;
    TextView fullName, email, phone, verifyMsg,job,adress;

    FirebaseFirestore fStore;

    Button resendCode;

    FirebaseUser user;
    FirebaseAuth fAuth;
    String userId;

    StorageReference storageReference;



    /*------------------Bottom NavigationView----------------------------------------*/


    TextView humid,temp,txtfire,day1,hour1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);
        phone = findViewById(R.id.profilePhone);
        fullName = findViewById(R.id.profileName);
        email = findViewById(R.id.profileEmail);
    //    resetPassLocal = findViewById(R.id.resetPasswordLocal);

        profileImage = findViewById(R.id.profileImage);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainactivity.this, ProfileInfo.class);
                startActivity(intent);
            }
        });
       // changeProfileImage = findViewById(R.id.changeProfile);
        job = findViewById(R.id.job);
        adress = findViewById(R.id.adress);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
  //      final DatabaseReference nhiet = database.getReference("DataUpdate/Humidity");
   //     final DatabaseReference doam = database.getReference("DataUpdate/Humidity");
      //  final DatabaseReference fire = database.getReference("MQ1/TinHieu1");

     //   Firebase nhiet = new Firebase("https://trac-nghiem-2-20ffc.firebaseio.com/DataUpdate1/Humidity");
        /*
        nhiet.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value =snapshot.getValue(String.class);
                value = value +"kkkk";
                temp.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */

/*
        doam.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value =snapshot.getValue(String.class);
                value = value +"";
                humid.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

 */



/*


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("users/" + fAuth.getCurrentUser().getUid() + "/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });

        resendCode = findViewById(R.id.resendCode);
        verifyMsg = findViewById(R.id.verifyMsg);


        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();
/*
        if (!user.isEmailVerified()) {
            verifyMsg.setVisibility(View.VISIBLE);
            resendCode.setVisibility(View.VISIBLE);

            resendCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(v.getContext(), "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("tag", "onFailure: Email not sent " + e.getMessage());
                        }
                    });
                }
            });
        }
/*
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    phone.setText(documentSnapshot.getString("phone"));
                    fullName.setText(documentSnapshot.getString("fName"));
                    email.setText(documentSnapshot.getString("email"));
                    job.setText(documentSnapshot.getString("job"));
                    adress.setText(documentSnapshot.getString("adress"));

                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });

 */





        /*------------------------------------------Bottom Navigation View----------------------------------------------------------------------*/
    /*
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.Profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),com.example.firebasetest.hienthi3.class));
                       overridePendingTransition(0,0);
                        return true;
                    case  R.id.feedback:
                        startActivity(new Intent(getApplicationContext(),com.example.firebasetest.DashboardActivity.class));
                       overridePendingTransition(0,0);
                }
                return false;
            }
        });
        /*------------------------------------------Bottom Navigation View----------------------------------------------------------------------*/






/*-----------------------drawer menu-----------------------------------------------------------------/*

        /*---------------Hooks------------*/
        drawerLayout = findViewById(R.id.drawerlayout);

        navigationView =findViewById(R.id.nav_view);

        toolbar = findViewById(R.id.toolbar);

        /*---------------Tool bar------------*/
        setSupportActionBar(toolbar);

        /*---------------Hide or show items------------*/
        Menu menu = navigationView.getMenu();
        // menu.findItem(R.id.nav_logout).setVisible(false);
        // menu.findItem(R.id.nav_profile).setVisible(false);
        //  menu.findItem(R.id.tv_name).setTitle("Hello \t" + CurrentUser.currentUser.getName());

        /*---------------Navigation Drawer Menu------------*/
        //  ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        // drawerLayout.addDrawerListener(toggle);
        // toggle.syncState();
        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(this);
        //  navigationView.setCheckedItem(R.id.nav_home);

/*-----------------------drawer menu-----------------------------------------------------------------*/


    }
/*
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

 */

    @Override
    public  void onBackPressed(){

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){

            case R.id.nav_home:

                break;

            case R.id.nav_direct:
                Intent intent2 = new Intent(Mainactivity.this,com.example.firebasetest.hienthi3.class);
                startActivity(intent2);
                break;

            case R.id.nav_video:
                Intent video = new Intent(Mainactivity.this, com.example.firebasetest.DashboardActivity.class);
                startActivity(video);
                break;

            case R.id.nav_control:
                Intent control = new Intent(Mainactivity.this,  Login.class);
                startActivity(control);
                break;

            case R.id.nav_logout:
                Intent intent1 = new Intent(Mainactivity.this, com.example.firebasetest.Sanh.class);
                final AlertDialog.Builder builder=new AlertDialog.Builder(Mainactivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc muốn thoát hay không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent =  new Intent(Mainactivity.this,com.example.firebasetest.Sanh.class);

                        startActivity(intent);
                        fAuth.signOut();
                        finish();

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.show();
             //   startActivity(intent1);

        }

        // drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
}
