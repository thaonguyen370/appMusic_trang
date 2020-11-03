package com.thao.appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Adapter.ListAdapterBaiHat;
import Models.BaiHat;

public class MusicActivity extends AppCompatActivity {

    LinearLayout manHinhDS;
    ListView listView;
    public static ArrayList<BaiHat> al;

    ListAdapterBaiHat adapter;
    public static MediaPlayer song;
    public static int viTri = 0;

    ImageButton quayLai, back, stop, play;
    ImageButton next;
    TextView tinNhan;
    TextView thoiGianDangChay, thoiGianTong;
    SeekBar seekBar;
    private ArrayList<String> DSBaiHat = new ArrayList<>();
    private List<String> lois = new ArrayList<>();
    public static String timeRunning;
    public static String timeTotal;
    public static String songName;
    public static String author;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        getBaihat();
        manHinhDS = (LinearLayout) findViewById(R.id.manHinhDS);
        listView = (ListView) findViewById(R.id.list);
        quayLai = (ImageButton) findViewById(R.id.quayLai);
        next = (ImageButton) findViewById(R.id.next);
        back = (ImageButton) findViewById(R.id.back);
        stop = (ImageButton) findViewById(R.id.stop);
        play = (ImageButton) findViewById(R.id.play);
        tinNhan = (TextView) findViewById(R.id.txt_trang);
        thoiGianDangChay = (TextView) findViewById(R.id.thoiGianDangChay);
        thoiGianTong = (TextView) findViewById(R.id.thoiGianTong);
        seekBar = (SeekBar) findViewById(R.id.seeBar);

        //manHinhDS.setBackgroundResource(R.drawable.manhinh2);

        // song.start();raw
        al = new ArrayList<BaiHat>();
        addSongs();
        adapter = new ListAdapterBaiHat(this, R.layout.bai_hat, al);
        listView.setAdapter(adapter);
        song = MediaPlayer.create(getApplicationContext(), al.get(viTri).getMp3());

        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                song.stop();
                Intent intent = new Intent(MusicActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (int j = 0; j < listView.getChildCount(); j++) {
                    if (i == j) {
                        listView.getChildAt(j).setBackgroundColor(Color.parseColor("#FF1493"));
                    } else {
                        listView.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);
                    }
                }

                song.reset();
                song = MediaPlayer.create(getApplicationContext(), al.get(i).getMp3());
                setTime();
                song.start();
                viTri = i;
                songName = al.get(i).getTenBai();
                author = al.get(i).getCaSi();
                tinNhan.setText(al.get(i).getTenBai());
                play.setImageResource(R.drawable.ic_pause_black_24dp);
                chuDong();
                upDate();
                Intent intent = new Intent(MusicActivity.this, DetailActivity.class);
                startActivity(intent);


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viTri > 0 && viTri < al.size() - 1) {
//                    song.reset();
                    song.stop();
                    viTri--;
                    song = MediaPlayer.create(getApplicationContext(), al.get(viTri).getMp3());

                    song.start();

                    songName = al.get(viTri).getTenBai();
                    author = al.get(viTri).getCaSi();

                    tinNhan.setText(al.get(viTri).getTenBai());
                    play.setImageResource(R.drawable.ic_pause_black_24dp);
                    setTime();
                    chuDong();
                    upDate();
                } else {
                    Toast t = Toast.makeText(MusicActivity.this, "het rui", Toast.LENGTH_LONG);
                    t.show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viTri >= 0 && viTri < al.size() - 1) {
//                    song.reset();
                    song.stop();
                    viTri++;
                    song = MediaPlayer.create(getApplicationContext(), al.get(viTri).getMp3());
                    setTime();
                    song.start();
                    songName = al.get(viTri).getTenBai();
                    author = al.get(viTri).getCaSi();
                    tinNhan.setText(al.get(viTri).getTenBai());
                    play.setImageResource(R.drawable.ic_pause_black_24dp);
                    chuDong();
                    upDate();
                } else {
                    Toast t = Toast.makeText(MusicActivity.this, "het rui", Toast.LENGTH_LONG);
                    t.show();
                }

            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (song.isPlaying()) {

                    song.pause();
                    play.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                } else {
                    song.start();
                    play.setImageResource(R.drawable.ic_pause_black_24dp);
                }
                setTime();
                upDate();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (song.isPlaying()) {
                    song.stop();
                    song.release();
                    song = MediaPlayer.create(getApplicationContext(), al.get(viTri).getMp3());
                    play.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                } else {
                    song = MediaPlayer.create(getApplicationContext(), al.get(viTri).getMp3());
                    setTime();
                    song.start();
                    play.setImageResource(R.drawable.ic_pause_black_24dp);
                    tinNhan.setText(al.get(viTri).getTenBai());
                    songName = al.get(viTri).getTenBai();
                    author = al.get(viTri).getCaSi();
                }
                chuDong();
                upDate();

            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                song.seekTo(seekBar.getProgress());
            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adpterView, View view, int position,
//                                    long id) {
//                for (int i = 0; i < listView.getChildCount(); i++) {
//                    if (position == i) {
//                                 listView.getChildAt(i).setBackgroundColor(Color.BLUE);
//                    } else {
//                        listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
//                    }
//                }
//
//            }
//        });
    }

    private void getBaihat() {
        DSBaiHat.add("Every time");
        lois.add("Notice me\n" +
                "Take my hand\n" +
                "Why are we\n" +
                "Strangers when\n" +
                "Our love is strong?\n" +
                "Why carry on without me?\n" +
                "Every time I try to fly I fall\n" +
                "Without my wings\n" +
                "I feel so small\n" +
                "I guess I need you, baby\n" +
                "And every time I see you in my dreams\n" +
                "I see your face\n" +
                "It's haunting me\n" +
                "I guess I need you, baby\n" +
                "I make-believe\n" +
                "That you are here\n" +
                "It's the only way\n" +
                "I see clear\n" +
                "What have I done?\n" +
                "You seem to move uneasy\n" +
                "And every time I try to fly I fall\n" +
                "Without my wings\n" +
                "I feel so small\n" +
                "I guess I need you, baby\n" +
                "And every time I see you in my dreams\n" +
                "I see your face\n" +
                "You're haunting me\n" +
                "I guess I need you, baby\n" +
                "I may have made it rain\n" +
                "Please, forgive me\n" +
                "My weakness caused…");
        DSBaiHat.add("All out of love");
        lois.add("I'm lying alone with my head on the phone\n" +
                "Thinking of you till it hurts\n" +
                "I know you hurt too but what else can we do\n" +
                "Tormented and torn apart\n" +
                "I wish I could carry your smile and my heart\n" +
                "For times when my life seems so low\n" +
                "It would make me believe what tomorrow could bring\n" +
                "When today doesn't really know, doesn't really know\n" +
                "I'm all out of love, I'm so lost without you\n" +
                "I know you were right believing for so long\n" +
                "I'm all out of love, what am I without you\n" +
                "I can't be too late to say that I was so wrong\n" +
                "I want you to come back and carry me home\n" +
                "Away from this long lonely nights\n" +
                "I'm reaching for you, are you feeling it too\n" +
                "Does the feeling seem oh so right\n" +
                "And what would you say if I called on you now\n" +
                "And said that I can't hold on\n" +
                "There's no easy way, it gets…");
        DSBaiHat.add("Show me the meaning of being lonely");
        lois.add("Show me the meaning of being lonely\n" +
                "So many words for the broken heart\n" +
                "It's hard to see in a crimson love\n" +
                "So hard to breathe\n" +
                "Walk with me, and maybe\n" +
                "Nights of light so soon become\n" +
                "Wild and free I could feel the sun\n" +
                "Your every wish will be done\n" +
                "They tell me\n" +
                "Show me the meaning of being lonely\n" +
                "Is this the feeling I need to walk with?\n" +
                "Tell me why I can't be there where you are\n" +
                "There's something missing in my heart\n" +
                "Life goes on as it never ends\n" +
                "Eyes of stone observe the trends\n" +
                "They never say forever gaze upon me\n" +
                "Guilty roads to an endless love (endless love)\n" +
                "There's no control\n" +
                "Are you with me now?\n" +
                "Your every wish will be done\n" +
                "They tell me\n" +
                "Show me the meaning of being lonely\n" +
                "Is this the feeling I need to walk with?\n" +
                "(Tell me why) tell me why I can't be there where you…");
        DSBaiHat.add("Background");
        lois.add("Background1");
        DSBaiHat.add("Woman in love");
        lois.add("Life is a moment in space\n" +
                "When the dream is gone\n" +
                "It's a lonelier place\n" +
                "I kiss the morning goodbye\n" +
                "But down inside you know\n" +
                "We never know why\n" +
                "The road is narrow and long\n" +
                "When eyes meet eyes\n" +
                "And the feeling is strong\n" +
                "I turn away from the wall\n" +
                "I stumble and fall\n" +
                "But I give you it all\n" +
                "I am a woman in love\n" +
                "And I do anything\n" +
                "To get you into my world\n" +
                "And hold you within\n" +
                "It's a right I defend\n" +
                "Over and over again\n" +
                "What do I do?\n" +
                "With you eternally mine\n" +
                "In love there is\n" +
                "No measure of time\n" +
                "We planned it all at the start\n" +
                "That you and I\n" +
                "Live in each other's hearts\n" +
                "We may be oceans away\n" +
                "You feel my love\n" +
                "I hear what you say\n" +
                "No truth is ever a lie\n" +
                "I stumble and fall\n" +
                "But I give you it all\n" +
                "I am a woman in love\n" +
                "And I do anything\n" +
                "To get you into my world\n" +
                "And hold you…");
        DSBaiHat.add("Can you feel the love tonight");
        lois.add("There's a calm surrender\n" +
                "To the rush of day\n" +
                "When the heat of a rolling wave\n" +
                "Can be turned away\n" +
                "An enchanted moment\n" +
                "And it sees me through\n" +
                "It's enough for this restless warrior\n" +
                "Just to be with you\n" +
                "And can you feel the love tonight?\n" +
                "It is where we are\n" +
                "It's enough for this wide-eyed wanderer\n" +
                "That we've got this far\n" +
                "And can you feel the love tonight? (Tonight)\n" +
                "How it's laid to rest?\n" +
                "It's enough to make kings and vagabonds\n" +
                "Believe the very best\n" +
                "There's a time for everyone\n" +
                "If they only learn\n" +
                "That the twisting kaleidoscope\n" +
                "Moves us all in turn\n" +
                "There's a rhyme and reason\n" +
                "To the wild outdoors\n" +
                "When the heart of this star-crossed voyager\n" +
                "Beats in time with yours\n" +
                "And can you feel the love…");
        DSBaiHat.add("Sacrifice");
        lois.add("It's a human sign\n" +
                "When things go wrong\n" +
                "When the scent of her lingers\n" +
                "And temptation's strong\n" +
                "Into the boundary\n" +
                "Of each married man\n" +
                "Sweet deceit comes calling\n" +
                "And negativity lands\n" +
                "Cold, cold heart\n" +
                "Hard done by you\n" +
                "Some things look better, baby\n" +
                "Just passing through\n" +
                "And it's no sacrifice\n" +
                "Just a simple word\n" +
                "It's two hearts living\n" +
                "In two separate worlds\n" +
                "But it's no sacrifice\n" +
                "No sacrifice\n" +
                "It's no sacrifice at all\n" +
                "Mutual misunderstanding\n" +
                "After the fact\n" +
                "Sensitivity builds a prison\n" +
                "In the final act\n" +
                "We lose direction\n" +
                "No stone unturned\n" +
                "No tears to damn you\n" +
                "When jealousy burns\n" +
                "Cold, cold heart\n" +
                "Hard done by you\n" +
                "Some things look better, baby\n" +
                "Just passing through\n" +
                "And it's no sacrifice\n" +
                "Just a simple word\n" +
                "It's two hearts living\n" +
                "In two separate worlds\n" +
                "But it's no sacrifice\n" +
                "No sacrifice…");
        DSBaiHat.add("Nothing gonna change my love for you");
        lois.add("If I had to live my life without you near me\n" +
                "The days would all be empty\n" +
                "The nights would seem so long\n" +
                "With you I see forever, oh, so clearly\n" +
                "I might have been in love before\n" +
                "But it never felt this strong\n" +
                "Our dreams are young and we both know\n" +
                "They'll take us where we want to go\n" +
                "Hold me now, touch me now\n" +
                "I don't want to live without you\n" +
                "Nothing's gonna change my love for you\n" +
                "You oughta know by now how much I love you\n" +
                "One thing you can be sure of\n" +
                "I'll never ask for more than your love\n" +
                "Nothing's gonna change my love for you\n" +
                "You oughta know by now how much I love you\n" +
                "The world may change my whole life through\n" +
                "But nothing's gonna change my love for you\n" +
                "If the road ahead is not so easy\n" +
                "Our love will lead the way for us\n" +
                "Like a guiding star\n" +
                "I'll be there for you if you…");
        DSBaiHat.add("Pretty boy");
        lois.add("I lie awake at night\n" +
                "See things in black and white\n" +
                "I've only got you inside my mind\n" +
                "You know you have made me blind\n" +
                "I lie awake and pray\n" +
                "That you will look my way\n" +
                "I have all this longing in my heart\n" +
                "I knew it right from the start\n" +
                "Oh my pretty, pretty boy I love you\n" +
                "Like I never ever loved no one before you\n" +
                "Pretty, pretty boy of mine\n" +
                "Just tell me you love me too\n" +
                "Oh my pretty, pretty boy I need you\n" +
                "Oh my pretty, pretty boy I do\n" +
                "Let me inside\n" +
                "Make me stay right beside you\n" +
                "I used to write your name\n" +
                "And put it in a frame\n" +
                "And sometimes I think I hear you call\n" +
                "Right from my bedroom wall\n" +
                "You stay a little while\n" +
                "And touch me with your smile (touch me with your smile)\n" +
                "And what can I say to make you mine\n" +
                "To reach out for you in time\n" +
                "Oh my pretty, pretty boy I love you\n" +
                "Like I…");
        DSBaiHat.add("Without you");
        lois.add("No I can't forget this evening or your face as you were leaving\n" +
                "But I guess that's just the way the story goes\n" +
                "You always smile, but in your eyes\n" +
                "Your sorrow shows\n" +
                "Yes, it shows\n" +
                "No I can't forget tomorrow\n" +
                "When I think of all my sorrow\n" +
                "When I had you there but then I let you go\n" +
                "And now it's only fair that I should let you know\n" +
                "What you should know\n" +
                "I can't live\n" +
                "If living is without you\n" +
                "I can't live\n" +
                "I can't give anymore\n" +
                "I can't live\n" +
                "If living is without you\n" +
                "I can't give\n" +
                "I can't give anymore\n" +
                "Well, I can't forget this evening or your face as you were leaving\n" +
                "But I guess that's just the way the story goes\n" +
                "You always smile, but in…");
        DSBaiHat.add("Background");
        lois.add("Background2");
        DSBaiHat.add("Background ");
        lois.add("Background3");
        DSBaiHat.add("From sarah with love");
        lois.add("For so many years we were friends\n" +
                "And yes, I always knew what we could do\n" +
                "But so many tears in the rain\n" +
                "Felt the night, you said\n" +
                "That love had come to you\n" +
                "I thought you were not my kind\n" +
                "I thought that I could never feel for you (feel for you)\n" +
                "The passion and love you were feeling\n" +
                "And so you left\n" +
                "For someone new (someone new)\n" +
                "And now that you're far and away\n" +
                "I'm sending a letter today\n" +
                "From Sarah with love\n" +
                "She'd got the lover she is dreaming of\n" +
                "She never found the words to say\n" +
                "But I know that today\n" +
                "She's gonna send her letter to you\n" +
                "From Sarah with love\n" +
                "She took your picture to the stars above\n" +
                "And they told her it is true\n" +
                "She could dare to fall in love with you\n" +
                "So don't make her blue when she writes to you\n" +
                "From Sarah with love\n" +
                "So maybe the…");
        DSBaiHat.add("Cry on my shoulder");
        lois.add("If the hero never comes to you\n" +
                "If you need someone, you're feeling blue\n" +
                "If you wait for love and you're alone\n" +
                "If you call your friends nobody's home\n" +
                "You can run away but you can't hide\n" +
                "Through a storm and through a lonely night\n" +
                "Then I'll show you there's a destiny\n" +
                "The best things in life they are free\n" +
                "But if you wanna cry\n" +
                "Cry on my shoulder\n" +
                "If you need someone\n" +
                "Who cares for you\n" +
                "If you're feeling sad\n" +
                "Your heart gets colder\n" +
                "Yes I show you what real love can do\n" +
                "If your sky is grey oh, let me know\n" +
                "There's a place in heaven where we'll go\n" +
                "If heaven is a million years away\n" +
                "Oh, just call me and I'll make your day\n" +
                "When the nights are gettin' cold and blue\n" +
                "When the days are gettin' hard for you\n" +
                "I will always stay here by your side\n" +
                "I promise you I'll never hide\n" +
                "But if you…");
        DSBaiHat.add("Back for good");
        lois.add("I guess now it's time for me to give up\n" +
                "I feel it's time\n" +
                "Got a picture of you beside me\n" +
                "Got your lipstick mark still on your coffee cup\n" +
                "Oh yeah\n" +
                "Got a fist of pure emotion\n" +
                "Got a head of shattered dreams\n" +
                "Gotta leave it, gotta leave it all behind now\n" +
                "Whatever I said, whatever I did\n" +
                "I didn't mean it\n" +
                "I just want you back for good\n" +
                "(Want you back, want you back, want you back for good)\n" +
                "Whenever I'm wrong\n" +
                "Just tell me the song and I'll sing it\n" +
                "You'll be right and understood\n" +
                "(Want you back, want you back, want you back for good)\n" +
                "I want you back for good\n" +
                "Unaware but underlined\n" +
                "I figured out this story (no no)\n" +
                "It wasn't good (no no)\n" +
                "But in a corner of my mind (corner of my mind)\n" +
                "I celebrated glory\n" +
                "But that was not to be\n" +
                "In the twist of separation\n" +
                "You excelled at being free…");
        DSBaiHat.add("Unchained melody");
        lois.add("Woah, my love, my darling\n" +
                "I've hungered for your touch\n" +
                "A long, lonely time\n" +
                "And time goes by so slowly\n" +
                "And time can do so much\n" +
                "Are you still mine?\n" +
                "I need your love\n" +
                "I need your love\n" +
                "God speed your love to me\n" +
                "Lonely rivers flow\n" +
                "To the sea, to the sea\n" +
                "To the open arms of the sea, yeah\n" +
                "Lonely rivers sigh\n" +
                "\"Wait for me, wait for me\"\n" +
                "I'll be coming home, wait for me\n" +
                "Woah, my love, my darling\n" +
                "I've hungered,…");
        DSBaiHat.add("Say you will");
        lois.add("Say you will, say you won't make up your mind tonight\n" +
                "Say you do, say you don't want to be mine\n" +
                "Say you will, say you won't make up your mind this time\n" +
                "Say you will, say you will be mine tonight\n" +
                "I can't sleep, I keep dreaming of losing you\n" +
                "I feel so alone in the night, scared to open my eyes\n" +
                "I'm in too deep, I'm in over my head this time\n" +
                "Can't get you out of my mind, no matter how hard I try\n" +
                "So won't you say you will, say you won't make up your mind tonight\n" +
                "Say you will, say you won't be my guide light\n" +
                "Say you will, say you won't make up your mind this time\n" +
                "Say you do, say you do, you want to be mine\n" +
                "I get the feeling I've never been here before\n" +
                "'Cause no one I've known's ever moved me the way that you do\n" +
                "And I know this is the real thing, it's all I've…");
        DSBaiHat.add("Unbreak my heart");
        lois.add("Don't leave me in all this pain\n" +
                "Don't leave me out in the rain\n" +
                "Come back and bring back my smile\n" +
                "Come and take these tears away\n" +
                "I need your arms to hold me now\n" +
                "The nights are so unkind\n" +
                "Bring back those nights when I held you beside me\n" +
                "Un-break my heart\n" +
                "Say you'll love me again\n" +
                "Undo this hurt you caused\n" +
                "When you walked out the door\n" +
                "And walked out of my life\n" +
                "Un-cry these tears\n" +
                "I cried so many nights\n" +
                "Un-break my heart\n" +
                "My heart\n" +
                "Take back that sad word goodbye\n" +
                "Bring back the joy to my life\n" +
                "Don't leave me here with these tears\n" +
                "Come and kiss this pain away\n" +
                "I can't forget the day you left\n" +
                "Time is so unkind\n" +
                "And life is so cruel without you here beside me\n" +
                "Un-break my heart\n" +
                "Say you'll love me again\n" +
                "Undo this hurt you caused\n" +
                "When you walked out the door\n" +
                "And walked out of my life…");
        DSBaiHat.add("Careless whisper");
        lois.add("Time can never mend\n" +
                "The careless whisper of a good friend\n" +
                "To the heart and mind\n" +
                "If your answer's kind\n" +
                "There's no comfort in the truth\n" +
                "Pain is all you'll find\n" +
                "I should have known better, yeah\n" +
                "I feel so unsure\n" +
                "As I take your hand and lead you to the dance floor\n" +
                "As the music dies\n" +
                "Something in your eyes\n" +
                "Calls to mind a silver screen\n" +
                "And all its sad goodbyes\n" +
                "I'm never gonna dance again\n" +
                "Guilty feet have got no rhythm\n" +
                "Though it's easy to pretend\n" +
                "I know you're not a fool\n" +
                "I should have known better than to cheat a friend\n" +
                "And waste a chance that I'd been given\n" +
                "So I'm never gonna dance again\n" +
                "The way I danced with you\n" +
                "Time can never mend\n" +
                "The careless whisper of a good friend\n" +
                "To the heart and mind\n" +
                "If your answer's kind\n" +
                "There's no comfort in the truth\n" +
                "Pain is all you'll find\n" +
                "I'm…");
        DSBaiHat.add("I will always love you");
        lois.add("If I should stay, I would only be in your way\n" +
                "So I'll go, but I know\n" +
                "I'll think of you every step of the way\n" +
                "And I will always love you\n" +
                "I will always love you\n" +
                "You, my darling you, hm\n" +
                "Bittersweet memories\n" +
                "That is all I'm taking with me\n" +
                "So, goodbye\n" +
                "Please, don't cry\n" +
                "We both know I'm not what you, you need\n" +
                "And I will always love you\n" +
                "I will always love you, you\n" +
                "I hope life treats you kind\n" +
                "And I hope you have all you've dreamed of\n" +
                "And I wish to you joy and happiness\n" +
                "But above all this, I wish you…");
    }

    private void addSongs() {
        al.add(getSong(R.drawable.doi40x50, 0, "no author", R.raw.a10everytime,0));
        al.add(getSong(R.drawable.doi40x50, 1, "air supply", R.raw.air_supply0all_out_of_love,1));
        al.add(getSong(R.drawable.doi40x50, 2, "backstreet_boys", R.raw.backstreet_boys0show_me_the_meaning_of_being_lonely,2));
        al.add(getSong(R.drawable.doi40x50, 3, "no author", R.raw.bainen0thao,3));
        al.add(getSong(R.drawable.doi40x50, 4, "barbra streisand", R.raw.barbra_streisand0woman_in_love,4));
        al.add(getSong(R.drawable.doi40x50, 5, "elton john", R.raw.elton_john0can_you_feel_the_love_tonight,5));
        al.add(getSong(R.drawable.doi40x50, 6, "elton john", R.raw.elton_john0sacrifice,6));
        al.add(getSong(R.drawable.doi40x50, 7, "george_benson", R.raw.george_benson0nothing_gonna_change_my_love_for_you,7));
        al.add(getSong(R.drawable.doi40x50, 8, "m2m", R.raw.m2m0pretty_boy,8));
        al.add(getSong(R.drawable.doi40x50, 9, "mariah carey", R.raw.mariah_carey0without_you,9));
        al.add(getSong(R.drawable.doi40x50, 10, "no author", R.raw.nen20trang,10));
        al.add(getSong(R.drawable.doi40x50, 11, "no author", R.raw.nen30trang,11));
        al.add(getSong(R.drawable.doi40x50, 12, "sarah connor", R.raw.sarah_connor0from_sarah_with_love,12));
        al.add(getSong(R.drawable.doi40x50, 13, "super stars", R.raw.super_stars0cry_on_my_houlder,13));
        al.add(getSong(R.drawable.doi40x50, 14, "take that", R.raw.take_that0back_for_good,14));
        al.add(getSong(R.drawable.doi40x50, 15, "the righteous brothers", R.raw.the_righteous_brothers0unchained_melody,15));
        al.add(getSong(R.drawable.doi40x50, 16, "tokyo Square", R.raw.tokyo_square0say_you_will,16));
        al.add(getSong(R.drawable.doi40x50, 17, "toni braxton", R.raw.toni_braxton0unbreak_my_heart,17));
        al.add(getSong(R.drawable.doi40x50, 18, "wham", R.raw.wham0careless_whisper,18));
        al.add(getSong(R.drawable.soy50x50, 19, "whitney houston", R.raw.whitney_houston0i_will_always_love_you,19));
    }

    private BaiHat getSong(int p, int indexBaiHat, String s, int p2,int indexLoi) {

        BaiHat baiHat=new BaiHat((p), DSBaiHat.get(indexBaiHat), s, p2);;
        baiHat.setLoiBaiHat(lois.get(indexLoi));
        return baiHat;
    }

    private void setLoibaiHat(BaiHat b, String loi) {
        b.setLoiBaiHat(loi);
    }

    private void setTime() {
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        timeTotal = dinhDangGio.format(song.getDuration());
        thoiGianTong.setText(timeTotal);
        //cho thanh seekbar bang thoi gian tong
        seekBar.setMax(song.getDuration());
    }


    private void upDate() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                //cap nhat cho thoi gian va seebar
                timeRunning = dinhDangGio.format(song.getCurrentPosition());
                seekBar.setProgress(song.getCurrentPosition());
                thoiGianDangChay.setText(timeRunning);
                song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if (viTri >= 0 && viTri < al.size() - 1) {
//                    song.reset();
                            song.stop();
                            viTri++;
                            song = MediaPlayer.create(getApplicationContext(), al.get(viTri).getMp3());
                            setTime();
                            song.start();
                            play.setImageResource(R.drawable.ic_pause_black_24dp);
                            tinNhan.setText(al.get(viTri).getTenBai());
                            chuDong();
                            upDate();
                        }

                    }
                });
                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    void chuDong() {
        Animation f = AnimationUtils.loadAnimation(this, R.anim.dong);
        f.reset();
        tinNhan.clearAnimation();
        tinNhan.startAnimation(f);
    }


}