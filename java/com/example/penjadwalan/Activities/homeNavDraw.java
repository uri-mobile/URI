package com.example.penjadwalan.Activities.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.penjadwalan.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homeNavDraw extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ImageView background, clover;
    LinearLayout splashscreen, texthome, menus;
    Animation frombottom;
    private CardView aggCard, jadwalCard, medaliCard, organisasiCard;

    private DrawerLayout drawerLayout;
    FirebaseUser currentUser;
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_nav_draw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mauth = FirebaseAuth.getInstance();
        currentUser = mauth.getCurrentUser();

        //define card
        aggCard = (CardView) findViewById(R.id.agg_card);
        jadwalCard = (CardView) findViewById(R.id.jadwal_card);
        medaliCard = (CardView) findViewById(R.id.medali_card);
        organisasiCard = (CardView) findViewById(R.id.organisasi_card);

        //Add Click Listener to the cards

        aggCard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                aggCard.getContext().startActivity(new Intent(aggCard.getContext(), Anggota.class));
            }
        });
        jadwalCard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                jadwalCard.getContext().startActivity(new Intent(jadwalCard.getContext(), Jadwal.class));
            }
        });
        medaliCard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                medaliCard.getContext().startActivity(new Intent(medaliCard.getContext(), Prestasi.class));
            }
        });
        organisasiCard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                organisasiCard.getContext().startActivity(new Intent(organisasiCard.getContext(), Organigram.class));
            }
        });

//        aggCard.setOnClickListener((OnClickListener) this);
//        jadwalCard.setOnClickListener((OnClickListener) this);
//        medaliCard.setOnClickListener((OnClickListener) this);
//        organisasiCard.setOnClickListener((OnClickListener) this);

        //load Animation
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);

        //define atribut
        background =(ImageView) findViewById(R.id.background);
        clover = (ImageView) findViewById(R.id.clover);
        splashscreen = (LinearLayout) findViewById(R.id.splashscreen);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);

        //set duration n size of splashcreen, background n clover
        background.animate().translationY(-1300).setDuration(3000).setStartDelay(2500);
        clover.animate().translationY(-1300).setDuration(3000).setStartDelay(2500);
        splashscreen.animate().translationY(140).alpha(0).setDuration(3000).setStartDelay(2500);

        //memanggil tampilan setelah splash yaitu text 'home' dan menu utama
        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        updateNavHeader();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.homee){
            getSupportFragmentManager().beginTransaction().replace(R.id.containerrr, new HomeFragment()).commit();

        } else if (id == R.id.profile) {
           getSupportFragmentManager().beginTransaction().replace(R.id.containerrr, new ProfilFragment()).commit();

        } else if(id == R.id.settings){

            getSupportFragmentManager().beginTransaction().replace(R.id.containerrr, new SettingsFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateNavHeader(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView nama = headerView.findViewById(R.id.namapengguna);
        TextView email = headerView.findViewById(R.id.email);
        ImageView img = headerView.findViewById(R.id.imageViewnav);


        nama.setText(currentUser.getDisplayName());
        email.setText(currentUser.getEmail());

        //Glide for img
        Glide.with(this).load(currentUser.getPhotoUrl()).into(img);
    }

}
