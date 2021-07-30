package misi;

import tempat.Wilayah;
import karakter.Karakter;
import items.Item;
import items.ItemKhusus;
import engine.GameInfo;
import engine.Aksi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Misi {
    protected String nama;
    protected String deskripsi;
    protected int koinYangDidapat;
    protected boolean statusMisi = false;
    protected boolean ismenuMisi = false;

    protected ArrayList<Wilayah> arrWilayah = new ArrayList<>();
    protected ArrayList<Karakter> arrMusuh = new ArrayList<>();
    protected ArrayList<Karakter> arrNPC = new ArrayList<>();
    protected HashMap<Integer, ItemKhusus> arrItem = new HashMap<>();
    protected ArrayList<Integer> arrTask = new ArrayList<>();
    protected ArrayList<String> arrMenuMisi = new ArrayList<>();

    Arena objArena = new Arena();

    Scanner sc = new Scanner(System.in);

    public Misi() {
        arrMenuMisi.add("Lanjutkan Misi");
        arrMenuMisi.add("Temui NPC");
    }

    public void setStatusMisi(boolean statusMisi) {
        this.statusMisi = statusMisi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getKoinYangDidapat() {
        return koinYangDidapat;
    }

    public void setKoinYangDidapat(int koinYangDidapat) {
        this.koinYangDidapat = koinYangDidapat;
    }

    public ArrayList<Wilayah> getArrWilayah() {
        return arrWilayah;
    }

    public void setArrWilayah(ArrayList<Wilayah> arrWilayah) {
        this.arrWilayah = arrWilayah;
    }

    public HashMap<Integer, ItemKhusus> getArrItem() {
        return arrItem;
    }

    public void setArrItem(HashMap<Integer, ItemKhusus> arrItem) {
        this.arrItem = arrItem;
    }

    public ArrayList<Integer> getArrTask() {
        return arrTask;
    }

    public ArrayList<Karakter> getArrMusuh() {
        return arrMusuh;
    }

    public void setArrMusuh(ArrayList<Karakter> arrMusuh) {
        this.arrMusuh = arrMusuh;
    }

    public ArrayList<Karakter> getArrNPC() {
        return arrNPC;
    }

    public void setIsmenuMisi(boolean ismenuMisi) {
        this.ismenuMisi = ismenuMisi;
    }

    public void setArrNPC(ArrayList<Karakter> arrNPC) {
        this.arrNPC = arrNPC;
    }

    public void deskripsiMisi() {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("                                      Deskripsi  "+this.getNama()+"               ");
        System.out.println("                                             -------                               ");
        System.out.println(this.getDeskripsi());
        System.out.println("Wilayah yang akan dilalui : ");
        for (Wilayah wilayah: this.getArrWilayah()) {
            if (wilayah.getMisi() == GameInfo.getMisiAktifIn()) {
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                wilayah.printData();
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            }
        }
    }

    public void jalankanMisi(){}

    public void temuiNPC() {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("            ");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public void menuMisi() {
        GameInfo.getMisiAktif().deskripsiMisi();
        do {
            Aksi.printAksi(arrMenuMisi);
            int pilihan = Aksi.pilihAksi();
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            if (pilihan == 1) {
                this.jalankanMisi();
            } else if (pilihan == 2){
                this.temuiNPC();
            } else if (pilihan == 0){
                break;
            }
        } while (!ismenuMisi);
    }

    public void tambahLevelSetelahMisi() {
        GameInfo.getKsatriaPlayer().setLevel(GameInfo.getKsatriaPlayer().getLevel()+1);
        GameInfo.getKsatriaPlayer().setHp(GameInfo.getKsatriaPlayer().getHp()*GameInfo.getKsatriaPlayer().getLevel());
        if (GameInfo.getKsatriaPlayer().getSenjataYangdigunakan() != null) {
            Item senjata = GameInfo.getKsatriaPlayer().getSenjataYangdigunakan();
            senjata.tambahLevel();
            senjata.setPower(senjata.hitungAtk());
        }
        if (GameInfo.getKsatriaPlayer().getPerisaiYangdigunakan() != null) {
            Item perisai = GameInfo.getKsatriaPlayer().getPerisaiYangdigunakan();
            perisai.tambahLevel();
            perisai.setDefense(perisai.hitungDefense());
        }
    }

    public void ambilItem(Item item, int index) {
        System.out.println("Selamat anda berhasil menebak angka yang benar");
        System.out.println("Ambil "+item.getNama());
        while(true) {
            System.out.println(" 1. Ambil ");
            System.out.println(" 2. Biarkan");
            System.out.print("Masukan : ");
            int pilih = sc.nextInt();
            if (pilih == 1) {
                System.out.println("Selamat anda berhasil mendapatkan " +item.getNama());
                GameInfo.getKsatriaPlayer().getArrItem().add(item);
                getArrTask().add(index, 1);
                break;
            } else if (pilih == 2){
                System.out.println("Anda tidak akan bisa lanjut kemisi selanjutnya, untuk melanjutkan misi silahkan ulangi misi ini dan ambil "+item.getNama());
                break;
            } else {
                System.out.println("Masukan pilihan yang tersedia");
            }
        }
    }
}
