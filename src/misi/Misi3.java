package misi;

import items.ItemKhusus;
import items.Item;
import engine.Aksi;
import engine.GameInfo;
import minigame.Coin;
import karakter.Karakter;

public class Misi3 extends Misi{

    public Misi3 (){
        this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);
        this.setStatusMisi(false);
        this.setNama("Misi Tiga");
        this.setKoinYangDidapat(50);
        this.setDeskripsi("Hallo, pasti Anda telah memiliki Sorcrux. Silahkan tukar Sorcrux Anda \n" +
                "dengan Star Ruby. Star Ruby akan membantu mengalahkan vampir di dekat Kota Brussels\n" +
                "Taklukanlah vampir tersebut dan Anda akan mendapatkan Tongkat Sarda \n" +
                "Tongkat sarda itu akan menuntun Anda untuk pergi ke Gunung Berapi Gurugun, lalu \n" +
                "Anda harus mengalahkan Iblis Api, Either dan Zack Barks (Vampir).\n"+
                "Setelah mengalahkan semua musuh, Anda akan mendapatkan Bola Elemental ke tiga.\n");
    }

    public void tukarStarRuby(){
        ItemKhusus sorcrux1 = (ItemKhusus) Aksi.findItem("Sorcrux", GameInfo.getKsatriaPlayer().getArrItem());
        if (sorcrux1 == null) {
            System.out.println("Anda belum memiliki Sorcrux \nAmbil Sorcrux dulu!");
            System.out.println("1). Ambil Sorcrux");
            System.out.println("2). Abaikan");
            int pil = Aksi.pilihAksi();
            ambilSorcrux(pil);
        }
        else {
            System.out.println("Apakah ingin menukar Sorcrux dengan Star Ruby?");
            System.out.println("1) Ya \n2) Tidak");
            int pil = Aksi.pilihAksi();
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            if(pil==1){
                GameInfo.getKsatriaPlayer().getArrItem().remove(sorcrux1);
                prosesTukarStarRuby();
                System.out.println("Anda telah menukar Sorcrux dengan Star Ruby");
            }
            else{
                System.out.println("Sorcrux harus ditukar dengan Star Ruby untuk melanjutkan misi");
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public void prosesTukarStarRuby(){
        ItemKhusus starRuby   = new ItemKhusus(5, "Star Ruby", "Terdapat di Misi 3 berfungsi untuk mengalahkan vampir");
        int i;
        for( i=1; i<=3; i++){
            System.out.println("\nKesempatan ke "+i);
            System.out.println("Lempar Koin?");
            System.out.println("1) Ya\n2) Tidak");
            int pil = Aksi.pilihAksi();
            if(pil==1){
                Coin objCoin = new Coin();
                if(objCoin.playCoin().equals("head")){
                    GameInfo.getKsatriaPlayer().getArrItem().add(starRuby);
                    System.out.println("Anda melempar coin dengan tepat");
                    System.out.println("Anda Berhasil menukar Star Ruby\n");
                    getArrTask().add(0, 1);
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    break;
                }
                else{
                    System.out.println("Lemparan Anda belum tepat Coba Lempar lagi!");
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                }
            }
        }
    }

    public void ambilSorcrux(int pil){
        if(pil==1){
            ItemKhusus sorcrux  = new ItemKhusus(5, "Sorcrux", "Menerangi jalan kuil kegelapan, dapat ditukar dengan Star Ruby");
            GameInfo.getKsatriaPlayer().getArrItem().add(sorcrux);
            System.out.println("Sorcrux diambil\n");
            tukarStarRuby();
        }
        else{
            System.out.println("Sorcrux tidak diambil, Anda tidak bisa melanjutkan misi");
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public void ambilTongkatSarda(){
        System.out.println("Apakah Anda ingin mengambil Tongkat Sarda?");
        System.out.println("1). Ya");
        System.out.println("2). Tidak");
        int pil = Aksi.pilihAksi();
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        if(pil==1){
            ItemKhusus tongkatSarda  = new ItemKhusus(5, "Tongkat Sarda", "Terdapat di Misi 3 sebagai Hadiah mengalahkan vampire untuk membantu ksatria menuju gunung berapi");
            GameInfo.getKsatriaPlayer().getArrItem().add(tongkatSarda);
            System.out.println("Tongkat Sarda diambil\n");
            getArrTask().add(2, 1);
        }
        else{
            System.out.println("Tongkat Sarda tidak diambil, Anda tidak bisa melanjutkan misi");
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public void gunakanStarRuby() {
        System.out.println("Gunakan Star Ruby untuk membunuh Vampir");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Apakah Anda ingin menggunakan Star Ruby?");
        System.out.println("1). Ya");
        System.out.println("2). Tidak");
        int pil = Aksi.pilihAksi();
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        if(pil==1){
            Item starRuby = Aksi.findItem("Star Ruby",GameInfo.getKsatriaPlayer().getArrItem());
            GameInfo.getKsatriaPlayer().getArrItem().remove(starRuby);
            System.out.println("Star Ruby berhasil digunakan, Vampir berhasil dikalahkan\n");
            getArrTask().add(1, 1);
        }
        else{
            System.out.println("Star Ruby tidak digunakan, Coba lagi untuk melawan Vampir");
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void jalankanMisi() {
        boolean statusMisi;
        int hpKsatriaAwal = GameInfo.getKsatriaPlayer().getHp();
        objArena.setGameOver(false);
        if (getArrTask().get(0) == 0) {
            tukarStarRuby();
        }
        else if (getArrTask().get(1) == 0) {
            Karakter zack = Aksi.findKarakter("Zack Barks(Vampir) Lv.3", getArrMusuh());
            if (zack == null) {
                System.out.println("Zack vampir tidak ditemukan");
            } else {
                objArena.perang(zack);
                statusMisi = objArena.isGameOver(hpKsatriaAwal, Aksi.findKarakter("Zack Barks(Vampir) Lv.3", getArrMusuh()));
                if (statusMisi) {
                    getArrTask().add(1,1);
                } else {
                    gunakanStarRuby();
                }
            }
        }
        else if (getArrTask().get(2) == 0) {
            System.out.println("Selamat Anda telah mengalahkan Vampir");
            ambilTongkatSarda();
        }
        else if (getArrTask().get(3) == 0) {
            Karakter iblis = Aksi.findKarakter("Iblis Api Either", getArrMusuh());
            if (iblis == null) {
                System.out.println("iblis api either tidak ditemukan");
            } else {
                objArena.perang(iblis);
                statusMisi = objArena.isGameOver(hpKsatriaAwal, Aksi.findKarakter("Iblis Api Either", getArrMusuh()));
                if (statusMisi) getArrTask().add(3,1);
            }
        }
        else {
            if(Aksi.findItem("Bola Elemental 3",GameInfo.getKsatriaPlayer().getArrItem()) == null) {
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("Temui NPC untuk mengambil bola elemental ketiga");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            } else {
                tambahLevelSetelahMisi();
                System.out.println("Misi Tiga selesai");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("Level anda naik menjadi level "+GameInfo.getKsatriaPlayer().getLevel());
                System.out.println("-----------------------------------------------------------------------------------------------------------");

                //init Misi yang Aktif
                Misi4 objMisi4 = new Misi4();
                objMisi4.setArrWilayah(GameInfo.getMisiAktif().getArrWilayah());
                objMisi4.setArrMusuh(GameInfo.getMisiAktif().getArrMusuh());
                objMisi4.setArrNPC(GameInfo.getMisiAktif().getArrNPC());
                objMisi4.setArrItem(GameInfo.getMisiAktif().getArrItem());
                GameInfo.setMisiAktif(objMisi4);
                GameInfo.setMisiAktifIn(4);
                setIsmenuMisi(true);
            }
        }
    }

    public void prosesTemuiNPC(int pilihan) {
        if (pilihan == 1) {
            if (getArrTask().get(3) == 0) {
                System.out.println("Selesaikan seluruh misi untuk mengambil bola elemental");
            } else {
                if (getArrItem().get(3) == null) {
                    System.out.println("Bola Elemental ketiga telah anda ambil");
                } else {
                    GameInfo.getKsatriaPlayer().getArrItem().add(getArrItem().get(3));
                    getArrItem().remove(3);
                    System.out.println("Bola Elemental ketiga berhasil disimpan di penyimpanan anda");
                }
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public void temuiNPC() {
        Aksi.printAksiNPC(getArrNPC(),3);
        int pil = Aksi.pilihAksi();
        prosesTemuiNPC(pil);
    }
}



