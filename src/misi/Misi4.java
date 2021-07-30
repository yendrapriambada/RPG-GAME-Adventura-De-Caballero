package misi;

import items.Item;
import items.ItemKhusus;
import engine.GameInfo;
import engine.Aksi;
import minigame.*;
import tempat.Wilayah;
import karakter.Karakter;

public class Misi4 extends Misi{

    private boolean penandaAlurNPC;

    public Misi4() {
        this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);
        this.setStatusMisi(false);
        this.setNama("Misi Empat");
        this.setKoinYangDidapat(50);
        this.setDeskripsi("Hallo Ksatria! Perjalananmu belum usai! Siapkan tenaga anda untuk melanjutkan misi\n" +
                "Setelah keluar dari gunung berapi, anda harus menyusuri pegunungan es\n" +
                "Disana anda harus menemukan Levistone yang akan membantumu menerbangkan balon udara.\n" +
                "Pergilah ke Benua Utara! temui Raja Naga disana yang bernama Astor.\n"+
                "Dia Akan memberikan sesuatu jika kamu mampu memenuhi permintaannya.\n" +
                "Tidak lupa, kalahkan musuh-musuh yang ada! Siapkan senjata, defense dan energi kamu!\n" +
                "Setelah semua task misi selesai, anda akan mendapatkan Bola Elemental yang Keempat.\n");
    }

    public void findLevistone() {
        Wilayah wilayah = Aksi.findWilayah("Pegunungan Es", getArrWilayah());
        if (wilayah == null) {
            System.out.println("Wilayah tidak diketahui");
        } else {
            System.out.println("Selamat datang di "+wilayah.getNama());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------Temukan Levistone---------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Temukan Levistone untuk membantu anda menerbangkan balon udara, dengan melemparkan dadu dalam 3 kali kesempatan dengan salah satu syarat dibawah ini:");
        System.out.println(" + Dadu yang muncul harus berangka 6");
        System.out.println(" + Dadu yang muncul harus berjumlah 6 dalam 3 kali kesempatan");
        assert wilayah != null;
        prosesFindLevistone(wilayah.getItem());
    }

    public void prosesFindLevistone(Item item){
        int i, temp;
        int total = 0;
        for( i=1; i<=3; i++){
            System.out.println("\nJumlah angka dadu: " + total);
            System.out.println("Kesempatan ke "+i);
            System.out.println("Lempar Dadu?");
            System.out.println("1) Ya\n2) Tidak");
            int pil = Aksi.pilihAksi();
            if(pil==1){
                Dice objDice = new Dice();
                temp = objDice.playDice();
                System.out.println("Angka dadu yang muncul : " + temp);
                if(temp == 6){
                    System.out.println("Anda melempar Dadu dengan sangat tepat!");
                    ambilItem(item, 0);
                    break;
                }
                else {

                    total = total + temp;
                    if (total == 6){
                        System.out.println("Anda melempar Dadu dengan sangat tepat!");
                        ambilItem(item, 0);
                    }
                    else{
                        System.out.println("Lemparan Anda belum tepat Coba Lempar lagi!");
                    }
                }
            }
        }
    }

    public void gunakanLevistone() {
        System.out.println("Gunakan Levistone sebagai bahan bakar api balon udara");
        System.out.println("Apakah Anda ingin menggunakan Levistone?");
        System.out.println("1). Ya");
        System.out.println("2). Tidak");
        int pil = Aksi.pilihAksi();
        if(pil==1){
            Item levistone = Aksi.findItem("Levistone",GameInfo.getKsatriaPlayer().getArrItem());
            GameInfo.getKsatriaPlayer().getArrItem().remove(levistone);
            System.out.println("Levistone berhasil digunakan, Balon udara siap digunakan untuk perjalanan ke Benua Utara\n");
            getArrTask().add(1, 1);
        }
        else{
            System.out.println("Levistone tidak digunakan, Coba lagi untuk menerbangkan balon udara");
        }
    }

    public void findRatsTail() {
        Wilayah wilayah = Aksi.findWilayah("Kastil Wizard", getArrWilayah());
        if (wilayah == null) {
            System.out.println("Wilayah tidak diketahui");
        } else {
            System.out.println("Selamat datang di "+wilayah.getNama());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("-------------------------------------Temukan RatsTail di Kastil Wizard-------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Temukan RatsTail untuk memenuhi permintaan Raja Astor,\n" +
                           "dengan melemparkan coin yang harus menampilkan permukaan\n" +
                            "'Head' dengan percobaan 3 kali kesempatan");
        int i;
        for( i=1; i<=3; i++){
            System.out.println("\nKesempatan ke "+i);
            System.out.println("Lempar Koin?");
            System.out.println("1) Ya\n2) Tidak");
            int pil = Aksi.pilihAksi();
            if(pil==1){
                Coin objCoin = new Coin();
                if(objCoin.playCoin().equals("head")){
                    System.out.println("Anda melempar coin dengan tepat");
                    assert wilayah != null;
                    ambilItem(wilayah.getItem(), 3);
                    break;
                }
                else{
                    System.out.println("Lemparan Anda belum tepat Coba Lempar lagi!");
                }
            }
        }
    }

    public void tukarRatsTail(){
        ItemKhusus ratsTail = (ItemKhusus) Aksi.findItem("Rats Tail", GameInfo.getKsatriaPlayer().getArrItem());
        if (ratsTail == null) {
            System.out.println("Anda belum memiliki Rats Tail \nAmbil Rats Tail dulu!");
            findRatsTail();
        }
        else {
            System.out.println("Apakah ingin menukar Rats Tail dengan hadiah dari NPC?");
            System.out.println("1) Ya \n2) Tidak");
            int pil = Aksi.pilihAksi();
            if(pil==1){
                GameInfo.getKsatriaPlayer().getArrItem().remove(ratsTail);
                prosesTukarRatsTail();
                System.out.println("Anda telah menukar Rats Tail dengan Peri Penolong dan Cairan Nitrogen");
            }
            else{
                System.out.println("Rats Tail harus ditukar untuk melanjutkan misi");
            }
        }
    }

    public void prosesTukarRatsTail(){
        ItemKhusus cairanNitrogen = new ItemKhusus(5, "Cairan nitrogen", "Terdapat di Misi 4 berfungsi untuk mengalahkan iblis air");
        int i;
        for( i=1; i<=3; i++){
            System.out.println("\nKesempatan ke "+i);
            System.out.println("Lempar Koin?");
            System.out.println("1) Ya\n2) Tidak");
            int pil = Aksi.pilihAksi();
            if(pil==1){
                Coin objCoin = new Coin();
                if(objCoin.playCoin().equals("head")){
                    GameInfo.getKsatriaPlayer().getArrItem().add(cairanNitrogen);
                    System.out.println("Anda melempar coin dengan tepat");
                    System.out.println("Anda Berhasil mendapatkan Cairan Nitrogen dan Peri Penolong\n");
                    getArrTask().add(4, 1);
                    break;
                }
                else{
                    System.out.println("Lemparan Anda belum tepat Coba Lempar lagi!");
                }
            }
        }
    }

    public void gunakanCairanNitrogen(int index) {
        System.out.println("Gunakan Cairan Nitrogen untuk mengalahkan musuh");
        System.out.println("*NOTE: Cairan Nitrogen ini hanya bisa di gunakan untuk satu kali pemakaian!");
        System.out.println("Apakah Anda ingin menggunakan Cairan Nitrogen?");
        System.out.println("1). Ya");
        System.out.println("2). Tidak");
        int pil = Aksi.pilihAksi();
        if(pil==1){
            Item cairanNitrogen = Aksi.findItem("Cairan nitrogen",GameInfo.getKsatriaPlayer().getArrItem());
            if (cairanNitrogen != null){
                GameInfo.getKsatriaPlayer().getArrItem().remove(cairanNitrogen);
                System.out.println("Cairan nitrogen berhasil digunakan!, Musuh Anda telah kalah!");
                getArrTask().add(index, 1);
            }
            else{
                System.out.println("Item Cairan Nitrogen Sudah habis digunakan.");
            }

        }
        else{
            System.out.println("Cairan nitrogen tidak digunakan, Coba lagi untuk melawan musuh!");
        }
    }

    @Override
    public void jalankanMisi() {
        boolean statusMisi;
        int hpKsatriaAwal = GameInfo.getKsatriaPlayer().getHp();
        objArena.setGameOver(false);
        if (getArrTask().get(0) == 0) {
            findLevistone();
        } else if (getArrTask().get(1) == 0) {
            gunakanLevistone();
        } else if (getArrTask().get(2) == 0) {
            System.out.println("Temui NPC Raja Naga yaitu Astor.");
            penandaAlurNPC = true;
        } else if (getArrTask().get(3) == 0) {
            findRatsTail();
        } else if (getArrTask().get(4) == 0) {
            tukarRatsTail();
        } else if (getArrTask().get(5) == 0) {
            Karakter iblis = Aksi.findKarakter("Iblis Air Raken", getArrMusuh());
            if (iblis == null) {
                System.out.println("Iblia air raken tidak ditemukan");
            } else {
                objArena.perang(iblis);
                statusMisi = objArena.isGameOver(hpKsatriaAwal, Aksi.findKarakter("Iblis Air Raken", getArrMusuh()));
                if (statusMisi) {
                    getArrTask().add(5,1);
                } else{
                    gunakanCairanNitrogen(5);
                }
            }
        } else if (getArrTask().get(6) == 0) {
            Karakter zack = Aksi.findKarakter("Zack Barks(Vampir) Lv.4", getArrMusuh());
            if (zack == null) {
                System.out.println("Zack vampir tidak ditemukan");
            } else {
                objArena.perang(zack);
                statusMisi = objArena.isGameOver(hpKsatriaAwal, Aksi.findKarakter("Zack Barks(Vampir) Lv.4", getArrMusuh()));
                if (statusMisi) {
                    getArrTask().add(6,1);
                } else {
                    gunakanCairanNitrogen(6);
                }
            }
        } else{
            if(Aksi.findItem("Bola Elemental 4",GameInfo.getKsatriaPlayer().getArrItem()) == null) {
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("Temui NPC untuk mengambil bola elemental keempat anda");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            } else {
                tambahLevelSetelahMisi();
                System.out.println("Misi empat selesai");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("Level anda naik menjadi level "+GameInfo.getKsatriaPlayer().getLevel());
                System.out.println("-----------------------------------------------------------------------------------------------------------");

                //init Misi yang Aktif
                Misi5 objMisi5 = new Misi5();
                objMisi5.setArrWilayah(GameInfo.getMisiAktif().getArrWilayah());
                objMisi5.setArrMusuh(GameInfo.getMisiAktif().getArrMusuh());
                objMisi5.setArrNPC(GameInfo.getMisiAktif().getArrNPC());
                objMisi5.setArrItem(GameInfo.getMisiAktif().getArrItem());
                GameInfo.setMisiAktif(objMisi5);
                GameInfo.setMisiAktifIn(5);
                setIsmenuMisi(true);
            }
        }
    }

    public void prosesTemuiNPC(int pilihan) {
        if (pilihan == 1) {
            if (getArrTask().get(0) == 0) {
                System.out.println("Silahkan lakukan perjalanan di pegunungan es dan temukanlah Levistone!");
            } else if (getArrTask().get(1) == 0) {
                System.out.println("Di sekitar anda, terdapat balon udara yang dapat membantu perjalanan anda ke Benua Utara. Gunakan levistone untuk menerbangkannya.");
            } else if (getArrTask().get(2) == 0) {
                if (penandaAlurNPC) {
                    System.out.println("Hai Ksatria! Jika anda merupakan ksatria pemberani, buktikanlah dengan mengambil Item Rats Tail dari Kastil Wizard!");
                    getArrTask().add(2, 1);
                } else {
                    System.out.println("Kerja Yang Bagus! Silahkan lanjutkan misi!");
                }
            } else if (getArrTask().get(3) == 0) {
                System.out.println("Ambil kembali Rats Tail milik-ku. Niscaya aku akan memberikan peri penolong dan Cairan Nitrogen untuk melanjutkan perjalananmu!");
            } else if (getArrTask().get(4) == 0) {
                System.out.println("Akhirnya Rats Tail ku kembali. Tukarlah dengan peri penolong dan cairan nitrogen milikku!");
            } else if (getArrTask().get(5) == 0) {
                System.out.println("Kalahkan Musuh!");
            } else if (getArrTask().get(6) == 0) {
                System.out.println("Kalahkan Musuh!");
            } else {
                if (getArrItem().get(4) == null) {
                    System.out.println("Bola Elemental keempat telah anda ambil");
                } else {
                    GameInfo.getKsatriaPlayer().getArrItem().add(getArrItem().get(4));
                    getArrItem().remove(4);
                    System.out.println("Bola Elemental keempat berhasil disimpan di penyimpanan anda");
                }
            }
        }
    }

    @Override
    public void temuiNPC() {
        Aksi.printAksiNPC(getArrNPC(),4);
        int pil = Aksi.pilihAksi();
        prosesTemuiNPC(pil);
    }
}
