package misi;

import items.Item;
import engine.GameInfo;
import engine.Aksi;
import tempat.Wilayah;
import karakter.Karakter;
import java.util.ArrayList;

public class Misi5 extends Misi {
    private ArrayList<String> arrAksiItem = new ArrayList<>();

    public ArrayList<String> getArrAksiItem() {
        return arrAksiItem;
    }

    public Misi5() {
        this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);
        this.setStatusMisi(false);
        this.setNama("Misi Lima");
        this.setKoinYangDidapat(250);
        this.setDeskripsi("Hallo Ksatria! karena Anda masih memiliki item khusus mystic key yang telah diberikan kurcaci\n " +
                "Anda harus menggunakannya untuk mendapatkan akses masuk menuju Benua Utara.\n" +
                "Di sana Anda akan menemukan Tantra seorang Iblis Angin dan Zack Barks.\n" +
                "Untuk bisa membunuh keduanya Anda harus menemukan crystal eye.\n" +
                "Carilah crystal eye di Benua Utara lalu bunuh mereka dan temukan\n" +
                "bola elemental ke-5 Anda\n");

        getArrAksiItem().add("Iya");
        getArrAksiItem().add("Tidak");
    }

    public void findCrystalEye() {

        Wilayah wilayah = Aksi.findWilayah("Pulau Terapung", getArrWilayah());
        if (wilayah == null) {
            System.out.println("Wilayah tidak diketahui");
        } else {
            System.out.println("Selamat datang di Benua Utara bagian "+wilayah.getNama());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------Temukan Crystal Eye--------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Temukan Crystal Eye untuk memulai perjalanan anda, dengan menebak angka 0-10 kemungkinan yang muncul dalam 3 kali kesempatan");
        int angkaTebak = (int) (Math.random() * 10);
        int tebakan, langkah = 0;

        boolean tertebak = false;

        do {
            langkah++;
            System.out.print("Masukan tebakan angka anda (0 - 10) : ");
            tebakan = sc.nextInt();
            if (tebakan > angkaTebak) {
                System.out.println("Tebakan anda terlalu besar");
            } else if (tebakan < angkaTebak){
                System.out.println("Tebakan anda terlalu kecil");
            } else {
                tertebak = true;
                assert wilayah != null;
                ambilItem(wilayah.getItem(), 1);
            }
        } while (!tertebak && langkah < 3);
    }

    public void gunakanMysticKey() {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------- Gunakan Mystic Key untuk mendapatkan akses masuk Benua Utara -----------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        Aksi.printAksi(getArrAksiItem());
        int pil = Aksi.pilihAksi();
        if(pil==1){
            Item mysticKey = Aksi.findItem("Mystic Key",GameInfo.getKsatriaPlayer().getArrItem());
            GameInfo.getKsatriaPlayer().getArrItem().remove(mysticKey);
            System.out.println("Mystic Key berhasil berhasil digunakan, Lanjutkan misi perjalananmu untuk menemukan " +
                    "Crystal Eye dan membunuh musuhmu\n");
            getArrTask().add(0, 1);
        }
        else{
            System.out.println("Mystic Key tidak digunakan, Coba lagi untuk menjalankan misi\n");
        }
    }

    public void gunakanCrystalEye(int index) {
        System.out.println("Gunakan Crsytal Eye untuk mengalahkan musuh");
        System.out.println("Apakah Anda ingin menggunakan Crsytal Eye?");
        Aksi.printAksi(getArrAksiItem());
        int pil = Aksi.pilihAksi();
        if(pil==1){
            Item crystalEye = Aksi.findItem("Crystal Eye",GameInfo.getKsatriaPlayer().getArrItem());
            if (crystalEye != null){
                GameInfo.getKsatriaPlayer().getArrItem().remove(crystalEye);
                System.out.println("Crstal Eye berhasil digunakan!, Musuh Anda telah kalah!");
                getArrTask().add(index, 1);
            }
            else{
                System.out.println("Crsytal Eye Sudah habis digunakan.");
            }

        }
        else{
            System.out.println("Crsytal Eye tidak digunakan, Coba lagi untuk melawan musuh!");
        }
    }

    @Override
    public void jalankanMisi() {
        boolean statusMisi;
        int hpKsatriaAwal = GameInfo.getKsatriaPlayer().getHp();
        objArena.setGameOver(false);
        if (getArrTask().get(0) == 0) {
            gunakanMysticKey();
        } else if (getArrTask().get(1) == 0) {
            findCrystalEye();
        } else if (getArrTask().get(2) == 0){
            Karakter iblis = Aksi.findKarakter("Iblis Angin Tantra", getArrMusuh());
            if (iblis == null) {
                System.out.println("Iblis angin tantra tidak ditemukan");
            } else {
                objArena.perang(iblis);
                statusMisi = objArena.isGameOver(hpKsatriaAwal, Aksi.findKarakter("Iblis Angin Tantra", getArrMusuh()));
                if (statusMisi) {
                    getArrTask().add(2,1);
                } else{
                    gunakanCrystalEye(2);
                }
            }
        } else if (getArrTask().get(3) == 0) {
            Karakter zack = Aksi.findKarakter("Zack Barks(Vampir) Lv.5", getArrMusuh());
            if (zack == null) {
                System.out.println("Zack vampir tidak ditemukan");
            } else {
                objArena.perang(zack);
                statusMisi = objArena.isGameOver(hpKsatriaAwal, Aksi.findKarakter("Zack Barks(Vampir) Lv.5", getArrMusuh()));
                if (statusMisi) {
                    getArrTask().add(3,1);
                } else{
                    gunakanCrystalEye(3);
                }
            }
        }else {
            if(Aksi.findItem("Bola Elemental 5",GameInfo.getKsatriaPlayer().getArrItem()) == null) {
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("Temui NPC untuk mengambil bola elemental kelima anda");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            } else {
                tambahLevelSetelahMisi();
                System.out.println("Misi Lima selesai");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("Level anda naik menjadi level "+GameInfo.getKsatriaPlayer().getLevel());
                System.out.println("-----------------------------------------------------------------------------------------------------------");

                //init Misi yang Aktif
                Misi6 objMisi6 = new Misi6();
                objMisi6.setArrWilayah(GameInfo.getMisiAktif().getArrWilayah());
                objMisi6.setArrMusuh(GameInfo.getMisiAktif().getArrMusuh());
                objMisi6.setArrNPC(GameInfo.getMisiAktif().getArrNPC());
                objMisi6.setArrItem(GameInfo.getMisiAktif().getArrItem());
                GameInfo.setMisiAktif(objMisi6);
                GameInfo.setMisiAktifIn(6);
                setIsmenuMisi(true);
            }
        }
    }

    public void prosesTemuiNPC(int pilihan) {
        if (pilihan == 1) {
            if (getArrTask().get(0) == 0) {
                System.out.println("Gunakan Mystic Key untuk melanjutkan perjalanan Anda Ksatria!");
            } else if (getArrTask().get(1) == 0) {
                System.out.println("Temukan Crstal Eye untuk memulai perjalanan dan membunuh musuh Anda Ksatria!");
            } else if (getArrTask().get(2) == 0) {
                System.out.println("Bunuh musuh Anda Ksatria, Iblis Angin Tantra!");
            } else if (getArrTask().get(3) == 0) {
                System.out.println("Bunuh musuh Anda Ksatria, Zack Barcks!");
            } else {
                if (getArrItem().get(5) == null) {
                    System.out.println("Bola Elemental kelima telah anda ambil");
                } else {
                    GameInfo.getKsatriaPlayer().getArrItem().add(getArrItem().get(5));
                    getArrItem().remove(5);
                    System.out.println("Bola Elemental lima berhasil disimpan di penyimpanan anda");
                }
            }
        }
    }

    @Override
    public void temuiNPC() {
        Aksi.printAksiNPC(getArrNPC(),5);
        int pil = Aksi.pilihAksi();
        prosesTemuiNPC(pil);
    }
}
