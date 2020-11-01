package Models;

/**
 * Created by NguyenVanThao on 2/4/2018.
 */
public class BaiHat {
    private int anh;
    private String tenBai;
    String caSi;
    private int mp3;
    private String loiBaiHat;
    public BaiHat(int anh, String tenBai, String caSi, int mp3) {
        this.anh = anh;
        this.tenBai = tenBai;
        this.caSi = caSi;
        this.mp3=mp3;
    }

    public void setMp3(int mp3) {
        this.mp3 = mp3;
    }

    public int getMp3() {
        return mp3;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public void setTenBai(String tenBai) {
        this.tenBai = tenBai;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }

    public int getAnh() {
        return anh;
    }

    public String getTenBai() {
        return tenBai;
    }

    public String getCaSi() {
        return caSi;
    }

    public String getLoiBaiHat() {
        return loiBaiHat;
    }

    public void setLoiBaiHat(String loiBaiHat) {
        this.loiBaiHat = loiBaiHat;
    }
}
