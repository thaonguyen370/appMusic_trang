package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thao.appmusic.MusicActivity;
import com.thao.appmusic.R;

import java.util.ArrayList;

import Models.BaiHat;

/**
 * Created by NguyenVanThao on 2/4/2018.
 */

public class ListAdapterBaiHat extends BaseAdapter {
    private MusicActivity context;
    private int layout;
    private ArrayList<BaiHat> al;

    public ListAdapterBaiHat(MusicActivity context, int layout, ArrayList<BaiHat> al) {
        this.context = context;
        this.layout = layout;
        this.al = al;
    }

    @Override
    public int getCount() {//tra ve so dong, vd tra ve 5 dong thi listView co 5 dong, còn muốn lấy hết thi return về kích thước cái mạng
        return al.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);// LayoutInflater để lấy context nào, để nhúng layout vao
        view = inflater.inflate(layout, null);
        //anh xa:
        TextView tenBaiHat = (TextView) view.findViewById(R.id.tenBaiHat);
        TextView caSi = (TextView) view.findViewById(R.id.caSi);
        ImageView anh = (ImageView) view.findViewById(R.id.anh);

        BaiHat baiHat = al.get(i);
        tenBaiHat.setText("Song: " + baiHat.getTenBai());
        caSi.setText("singer: " + baiHat.getCaSi());
        anh.setImageResource(baiHat.getAnh());
        return view;
    }

}