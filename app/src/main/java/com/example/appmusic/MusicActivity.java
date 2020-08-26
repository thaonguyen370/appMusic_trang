package com.example.appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import Adapter.ListAdapterBaiHat;
import Models.BaiHat;

public class MusicActivity extends AppCompatActivity {

    LinearLayout manHinhDS;
    ListView listView;
    ArrayList<BaiHat> al;
    ImageButton quayLai,back, stop, play;
    ImageButton next;
    ListAdapterBaiHat adapter;
    MediaPlayer song;
    public static int viTri=0;
    TextView tinNhan;
    TextView thoiGianDangChay, thoiGianTong;
    SeekBar seekBar;
    private ArrayList<String> DSBaiHat=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        DSBaiHat.add( "everytime" );
        DSBaiHat.add( "all out of love" );
        DSBaiHat.add( "show me the meaning of being lonely" );
        DSBaiHat.add( "bai nen" );
        DSBaiHat.add( "woman in love" );
        DSBaiHat.add( "can you feel the love tonight" );
        DSBaiHat.add( "sacrifice" );
        DSBaiHat.add( "nothing gonna change my love for you" );
        DSBaiHat.add( "pretty boy9" );
        DSBaiHat.add( "without you" );
        DSBaiHat.add( "nen2" );
        DSBaiHat.add( "nen3" );
        DSBaiHat.add( "from sarah with love" );
        DSBaiHat.add( "cry on my houlder" );
        DSBaiHat.add( "back for good" );
        DSBaiHat.add( "unchained melody" );
        DSBaiHat.add( "say you will" );
        DSBaiHat.add( "unbreak my heart" );
        DSBaiHat.add( "careless whisper" );
        DSBaiHat.add( "i will always love you" );


        manHinhDS=(LinearLayout) findViewById(R.id.manHinhDS);
        listView=(ListView) findViewById(R.id.list);
        quayLai=(ImageButton) findViewById(R.id.quayLai);
        next= (ImageButton) findViewById(R.id.next);
        back=(ImageButton) findViewById( R.id.back );
        stop=(ImageButton) findViewById( R.id.stop );
        play=(ImageButton) findViewById( R.id.play );
        tinNhan=(TextView) findViewById(R.id.txt_trang);
        thoiGianDangChay=(TextView) findViewById( R.id.thoiGianDangChay );
        thoiGianTong=(TextView) findViewById( R.id.thoiGianTong );
        seekBar=(SeekBar) findViewById( R.id.seeBar );

        //manHinhDS.setBackgroundResource(R.drawable.manhinh2);

        // song.start();raw
        al=new ArrayList<BaiHat>();
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get( 0 ),"a1",R.raw.a10everytime));
        al.add(new BaiHat((R.drawable.picture3),DSBaiHat.get( 1 ),"air supply",R.raw.air_supply0all_out_of_love));
        al.add(new BaiHat((R.drawable.picture3),DSBaiHat.get( 2 ),"backstreet_boys",R.raw.backstreet_boys0show_me_the_meaning_of_being_lonely));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get( 3 ),"thao",R.raw.bainen0thao));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get( 4 ),"barbra streisand",R.raw.barbra_streisand0woman_in_love));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get(5 ),"elton john",R.raw.elton_john0can_you_feel_the_love_tonight));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get( 6 ),"elton john",R.raw.elton_john0sacrifice));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get( 7),"george_benson",R.raw.george_benson0nothing_gonna_change_my_love_for_you));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get(8),"m2m",R.raw.m2m0pretty_boy));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get(9),"mariah carey",R.raw.mariah_carey0without_you));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get(10),"trang2",R.raw.nen20trang));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get( 11 ),"trang3",R.raw.nen30trang));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get( 12 ),"sarah connor",R.raw.sarah_connor0from_sarah_with_love));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get(13 ),"super stars",R.raw.super_stars0cry_on_my_houlder));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get( 14 ),"take that",R.raw.take_that0back_for_good));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get( 15),"the righteous brothers",R.raw.the_righteous_brothers0unchained_melody));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get(16 ),"tokyo Square",R.raw.tokyo_square0say_you_will));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get( 17 ),"toni braxton",R.raw.toni_braxton0unbreak_my_heart));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get( 18 ),"wham",R.raw.wham0careless_whisper));
        al.add(new BaiHat((R.drawable.image),DSBaiHat.get( 19),"whitney houston",R.raw.whitney_houston0i_will_always_love_you));
        adapter=new ListAdapterBaiHat(this,R.layout.bai_hat,al);
        listView.setAdapter(adapter);
        song=MediaPlayer.create(getApplicationContext(),al.get(viTri).getMp3());

        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                song.stop();
                Intent intent = new Intent(MusicActivity.this, LoginActivity.class);
                startActivity(intent);

            } });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                song.reset();
                song=MediaPlayer.create(getApplicationContext(),al.get(i).getMp3());
                setTime();
                song.start();
                viTri=i;
                tinNhan.setText( al.get( i ).getTenBai() );
                play.setImageResource( R.drawable.ic_pause_black_24dp );
                chuDong();
                upDate();


            }
        });
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTri>0&&viTri<al.size()-1){
//                    song.reset();
                    song.stop();
                    viTri--;
                    song=MediaPlayer.create(getApplicationContext(),al.get(viTri).getMp3());

                    song.start();
                    tinNhan.setText( al.get( viTri ).getTenBai() );
                    play.setImageResource( R.drawable.ic_pause_black_24dp );
                    setTime();
                    chuDong();
                    upDate();
                }else {
                    Toast t=Toast.makeText(MusicActivity.this,"het rui",Toast.LENGTH_LONG);
                    t.show();
                }
            }
        } );

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTri>=0&&viTri<al.size()-1){
//                    song.reset();
                    song.stop();
                    viTri++;
                    song=MediaPlayer.create(getApplicationContext(),al.get(viTri).getMp3());
                    setTime();
                    song.start();
                    tinNhan.setText( al.get( viTri ).getTenBai() );
                    play.setImageResource( R.drawable.ic_pause_black_24dp );
                    chuDong();
                    upDate();
                }else {
                    Toast t=Toast.makeText(MusicActivity.this,"het rui",Toast.LENGTH_LONG);
                    t.show();
                }

            }
        });
        play.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (song.isPlaying()){

                    song.pause();
                    play.setImageResource( R.drawable.ic_play_arrow_black_24dp );
                }else {
                    song.start();
                    play.setImageResource( R.drawable.ic_pause_black_24dp );
                }
                setTime();
                upDate();
            }
        } );
        stop.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (song.isPlaying()){
                    song.stop();
                    song.release();
                    song=MediaPlayer.create(getApplicationContext(),al.get(viTri).getMp3());
                    play.setImageResource( R.drawable.ic_play_arrow_black_24dp );
                }else {
                    song=MediaPlayer.create(getApplicationContext(),al.get(viTri).getMp3());
                    setTime();
                    song.start();
                    play.setImageResource( R.drawable.ic_pause_black_24dp );
                    tinNhan.setText( al.get( viTri ).getTenBai() );

                }
                chuDong();
                upDate();

            }
        } );
        seekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                song.seekTo( seekBar.getProgress() );
            }
        } );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adpterView, View view, int position,
                                    long id) {

                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.dia_xoay_am_nhac );

                for (int i = 0; i < listView.getChildCount(); i++) {
                    if(position == i ){
                        listView.getChildAt(i).setBackgroundColor(Color.BLUE);
                        listView.getChildAt(i).findViewById(R.id.anh).findViewById(R.id.anh).startAnimation(anim);
                    }else{
                       listView.getChildAt(i).findViewById(R.id.anh).findViewById(R.id.anh).clearAnimation();

                        listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
    }
    private void setTime(){
        SimpleDateFormat dinhDangGio=new SimpleDateFormat( "mm:ss" );
        thoiGianTong.setText( dinhDangGio.format( song.getDuration() ) );
        //cho thanh seekbar bang thoi gian tong
        seekBar.setMax( song.getDuration()  );
    }
    private void upDate(){
        final Handler handler=new Handler(  );
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio=new SimpleDateFormat( "mm:ss" );
                //cap nhat cho thoi gian va seebar
                seekBar.setProgress( song.getCurrentPosition() );
                thoiGianDangChay.setText( dinhDangGio.format( song.getCurrentPosition() ) );
                song.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if(viTri>=0&&viTri<al.size()-1){
//                    song.reset();
                            song.stop();
                            viTri++;
                            song=MediaPlayer.create(getApplicationContext(),al.get(viTri).getMp3());
                            setTime();
                            song.start();
                            play.setImageResource( R.drawable.ic_pause_black_24dp );
                            tinNhan.setText( al.get( viTri ).getTenBai() );
                            chuDong();
                            upDate();
                        }

                    }
                } );
                handler.postDelayed( this,500 );
            }
        },100 );
    }

    void chuDong(){
        Animation f= AnimationUtils.loadAnimation(this,R.anim.dong);
        f.reset();
        tinNhan.clearAnimation();
        tinNhan.startAnimation(f);
    }


}