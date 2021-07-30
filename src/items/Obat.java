package items;

import engine.GameInfo;
import java.util.ArrayList;

public class Obat extends Item{
    private int hp;
    private int mp;
    private int jenisObat;
    //    Jenis Obat Keterangan:
    //        1 = Penambah Hp
    //        2 = Penambah Mp
    //        3 = Penambah Hp dan Mp


    public Obat(int id, String nama, int hargaJual, int hargaBeli, int hp, int mp, int jenisObat) {
        super(id, nama, hargaJual, hargaBeli);
        this.hp = hp;
        this.mp = mp;
        this.jenisObat = jenisObat;
    }

    @Override
    public void setArrAksi() {
        getArrAksi().add("Minum Obat");
    }

    @Override
    public ArrayList<String> getArrAksi() {
        return this.arrAksi;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getJenisObat() {
        return jenisObat;
    }

    public void setJenisObat(int jenisObat) {
        this.jenisObat = jenisObat;
    }

    @Override
    public ArrayList<Item> getArrItem() {
        return arrItem;
    }

    @Override
    public void printData() {
        System.out.println("Nama                  : "+this.getNama());
        System.out.println("Harga Jual            : "+this.getHargaJual());
        System.out.println("Harga Beli            : "+this.getHargaBeli());
        if(this.getJenisObat()==1){
            System.out.println("HP                    : "+this.getHp());
        }
        else if(this.getJenisObat()==2){
            System.out.println("MP                    : "+this.getMp());
        }
        else{
            System.out.println("HP                    : "+this.getHp());
            System.out.println("MP                    : "+this.getMp());
        }
        System.out.println("----------------------------------------------------");
    }

    @Override
    public void prosesAksi(int pilihan) {
        if (pilihan == 1) {
            this.pakai();
        }
    }

    @Override
    public void pakai() {
        if (this!=null){
            if(this.getJenisObat()==1){
                int hp = GameInfo.getKsatriaPlayer().getHp()+this.getHp();
                GameInfo.getKsatriaPlayer().setHp(hp);
            }
            else if(this.getJenisObat()==2){
                int mp = GameInfo.getKsatriaPlayer().getMp()+this.getMp();
                GameInfo.getKsatriaPlayer().setMp(mp);
            }
            else{
                int hp = GameInfo.getKsatriaPlayer().getHp()+this.getHp();
                GameInfo.getKsatriaPlayer().setHp(hp);
                int mp = GameInfo.getKsatriaPlayer().getMp()+this.getMp();
                GameInfo.getKsatriaPlayer().setMp(mp);
            }
            GameInfo.getKsatriaPlayer().getArrItem().remove(this);
            System.out.println("----------------------------------------------------");
            System.out.println("Obat berhasil di minum");
            System.out.println("----------------------------------------------------");
            GameInfo.getKsatriaPlayer().printData();
            System.out.println("----------------------------------------------------");
        }
        else{
            System.out.println("Data Obat kosong!");
        }
    }
}
