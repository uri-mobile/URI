package com.example.penjadwalan.Activities.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.penjadwalan.R;

public class home extends AppCompatActivity implements OnClickListener{

    ImageView background, clover;
    LinearLayout splashscreen, texthome, menus;
    Animation frombottom;
    private CardView aggCard, jadwalCard, medaliCard, organisasiCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_nav_draw);

        //define card
        aggCard = (CardView) findViewById(R.id.agg_card);
        jadwalCard = (CardView) findViewById(R.id.jadwal_card);
        medaliCard = (CardView) findViewById(R.id.medali_card);
        organisasiCard = (CardView) findViewById(R.id.organisasi_card);

        //Add Click Listener to the cards
        aggCard.setOnClickListener(this);
        jadwalCard.setOnClickListener(this);
        medaliCard.setOnClickListener(this);
        organisasiCard.setOnClickListener(this);

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
        clover.animate().translationY(-1300).setDuration(3000).setStartDelay(1500);
        splashscreen.animate().translationY(140).alpha(0).setDuration(3000).setStartDelay(1500);

        //memanggil tampilan setelah splash yaitu text 'home' dan menu utama
        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);
    }


    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.agg_card : i = new Intent(this,Anggota.class);startActivity(i); break;
            case R.id.jadwal_card : i = new Intent(this, Jadwal.class);startActivity(i); break;
            case R.id.medali_card : i = new Intent(this, Prestasi.class);startActivity(i); break;
            case R.id.organisasi_card : i = new Intent(this, Organigram.class);startActivity(i); break;
            default:break;
        }
    }
}
