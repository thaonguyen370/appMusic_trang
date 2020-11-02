package com.thao.appmusic.ui.main;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.thao.appmusic.MusicActivity;
import com.thao.appmusic.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentDetail extends Fragment implements View.OnClickListener {
    ImageView detail;


    ImageButton quayLai, back, stop, play;
    ImageButton next;

    TextView thoiGianDangChay, thoiGianTong;
    SeekBar seekBar;

    TextView nameSong;
    TextView author;

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static FragmentDetail newInstance(int index) {
        FragmentDetail fragment = new FragmentDetail();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);


    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        // final TextView textView = root.findViewById(R.id.frag1);
        quayLai = (ImageButton) root.findViewById(R.id.quayLai);
        next = (ImageButton) root.findViewById(R.id.next);
        back = (ImageButton) root.findViewById(R.id.back);
        stop = (ImageButton) root.findViewById(R.id.stop);
        play = (ImageButton) root.findViewById(R.id.play);
        detail= root.findViewById(R.id.imageDetail);
        nameSong = (TextView) root.findViewById(R.id.songName);
        author = (TextView) root.findViewById(R.id.author);

        nameSong.setText(MusicActivity.songName);
        author.setText(MusicActivity.author);

        thoiGianDangChay = (TextView) root.findViewById(R.id.thoiGianDangChay);
        thoiGianTong = (TextView) root.findViewById(R.id.thoiGianTong);
        seekBar = (SeekBar) root.findViewById(R.id.seeBar);

        quayLai.setOnClickListener(this);
        next.setOnClickListener(this);
        back.setOnClickListener(this);
        stop.setOnClickListener(this);

        pageViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //      textView.setText("sadsadsadsa1233333333333333333333333333");
            }
        });

        upDateFirst();
        upDateBg();
        return root;
    }

    private void setTime() {
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        thoiGianTong.setText(dinhDangGio.format(MusicActivity.song.getDuration()));
        //cho thanh seekbar bang thoi gian tong
        seekBar.setMax(MusicActivity.song.getDuration());
    }

    private void upDate() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                //cap nhat cho thoi gian va seebar
                seekBar.setProgress(MusicActivity.song.getCurrentPosition());
                thoiGianDangChay.setText(dinhDangGio.format(MusicActivity.song.getCurrentPosition()));
                MusicActivity.song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if (MusicActivity.viTri >= 0 && MusicActivity.viTri < MusicActivity.al.size() - 1) {
//                    song.reset();
                            MusicActivity.song.stop();
                            MusicActivity.viTri++;
                            MusicActivity.song = MediaPlayer.create(getActivity(), MusicActivity.al.get(MusicActivity.viTri).getMp3());
                            setTime();
                            MusicActivity.song.start();
                            play.setImageResource(R.drawable.ic_pause_black_24dp);
    //                        tinNhan.setText(MusicActivity.al.get(MusicActivity.viTri).getTenBai());
                     //       chuDong();
                            upDate();
                        }

                    }
                });
                handler.postDelayed(this, 500);
            }
        }, 100);
    }
    private void upDateFirst() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                //cap nhat cho thoi gian va seebar
                seekBar.setProgress(MusicActivity.song.getCurrentPosition());
                thoiGianDangChay.setText(dinhDangGio.format(MusicActivity.song.getCurrentPosition()));
                handler.postDelayed(this, 500);
            }
        }, 100);
        setTime();
    }

    private void upDateBg() {
        final Handler handler = new Handler();
        final List<Integer> images = new ArrayList<>();
        images.add(R.drawable.camtay1);
        images.add(R.drawable.camtay2);
        images.add(R.drawable.camtay3);
        images.add(R.drawable.camtay4);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                int ra = r.nextInt((3 - 0) + 1) + 0;
                detail.setBackgroundResource(images.get(ra));
                handler.postDelayed(this, 5000);
            }
        }, 5000);
    }

//    void chuDong() {
//        Animation f = AnimationUtils.loadAnimation(getActivity(), R.anim.dong);
//        f.reset();
//        tinNhan.clearAnimation();
//        tinNhan.startAnimation(f);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if (MusicActivity.viTri > 0 && MusicActivity.viTri < MusicActivity.al.size() - 1) {
//                    song.reset();
                    MusicActivity.song.stop();
                    MusicActivity.viTri--;
                    MusicActivity.song = MediaPlayer.create(getActivity(), MusicActivity.al.get(MusicActivity.viTri).getMp3());

                    MusicActivity.song.start();
                    play.setImageResource(R.drawable.ic_pause_black_24dp);
                    setTime();
               //     chuDong();
                    upDate();
                } else {
                    Toast t = Toast.makeText(getActivity(), "het rui", Toast.LENGTH_LONG);
                    t.show();
                }
                break;
            case R.id.next:
                if (MusicActivity.viTri >= 0 && MusicActivity.viTri < MusicActivity.al.size() - 1) {
//                    song.reset();
                    MusicActivity.song.stop();
                    MusicActivity.viTri++;
                    MusicActivity.song = MediaPlayer.create(getActivity(), MusicActivity.al.get(MusicActivity.viTri).getMp3());
                    setTime();
                    MusicActivity.song.start();
//                    tinNhan.setText(MusicActivity.al.get(MusicActivity.viTri).getTenBai());
                    play.setImageResource(R.drawable.ic_pause_black_24dp);
               //     chuDong();
                    upDate();
                } else {
                    Toast t = Toast.makeText(getActivity(), "het rui", Toast.LENGTH_LONG);
                    t.show();
                }
                break;
//            ImageButton quayLai, back, stop, play;
//            ImageButton next;
//            TextView tinNhan;
//            TextView thoiGianDangChay, thoiGianTong;
//            SeekBar seekBar;
            case R.id.quayLai:
                getActivity().onBackPressed();
//                Intent intent = new Intent(getActivity(), MusicActivity.class);
//                startActivity(intent);
                break;
            case R.id.stop:
                if (MusicActivity.song.isPlaying()) {
                    MusicActivity.song.stop();
                    MusicActivity.song.release();
                    MusicActivity.song = MediaPlayer.create(getActivity(), MusicActivity.al.get(MusicActivity.viTri).getMp3());
                    play.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                } else {
                    MusicActivity.song = MediaPlayer.create(getActivity(), MusicActivity.al.get(MusicActivity.viTri).getMp3());
                    setTime();
                    MusicActivity.song.start();
                    play.setImageResource(R.drawable.ic_pause_black_24dp);

                }
           //     chuDong();
                upDate();

                break;
            case R.id.play:
                if (MusicActivity.song.isPlaying()) {

                    MusicActivity.song.pause();
                    play.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                } else {
                    MusicActivity.song.start();
                    play.setImageResource(R.drawable.ic_pause_black_24dp);
                }
                setTime();
                upDate();
                break;

        }
    }
}