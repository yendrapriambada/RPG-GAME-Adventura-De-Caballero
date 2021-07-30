package misi;

import engine.GameInfo;
import engine.Aksi;
import tempat.Wilayah;
import karakter.Karakter;

public class Misi1 extends Misi{

    public Misi1() {
        this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);
        this.setStatusMisi(false);
        this.setNama("Misi Satu");
        this.setKoinYangDidapat(50);
        this.setDeskripsi("Pertama, anda harus menemukan amplop peta Quil Keabadian yang telah \n" +
                "disembunyikan oleh Elf (anak Pangeran Kegelapan). \n" +
                "Lalu anda memulai petualangannya dari Kota Kiev. Di sana anda harus \n" +
                "mengalahkan  Elf dan Zack Barks (Vampir) dan menyelamatkan Kota yang telah diserang.\n"+
                "Anda harus berlayar menyusuri Sungai Bucharest dan di tepi Sungai inilah \n" +
                "Ksatria memulai rangkaian misinya. \n" +
                "Anda akan menemukan sorcerer stone yang akan digunakan untuk \n" +
                "membuka square magic di dalam Goa Praha sebrang sungai Bucharest.\n" +
                "Setelah anda berhasil membuka square magic, \n" +
                "anda akan mendapatkan bola elemental yang pertama.\n");
    }

    public void findAmplopPeta() {
        Wilayah wilayah = Aksi.findWilayah("Kota Kiev", getArrWilayah());
        if (wilayah == null) {
            System.out.println("Wilayah tidak diketahui");
        } else {
            System.out.println("Selamat datang di "+wilayah.getNama());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println(" Temukan Amplop Peta");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Temukan Amplop Peta untuk memulai perjalanan anda, \ndengan menebak angka 0-10 kemungkinan yang muncul dalam 3 kali kesempatan");
        int angkaTebak = (int) (Math.random() * 10);
        int tebakan, langkah = 0;

        boolean tertebak = false;

        do {
            langkah++;
            System.out.print("Masukan tebakan angka anda (0 - 10) : ");
            tebakan = sc.nextInt();
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            if (tebakan > angkaTebak) {
                System.out.println("Tebakan anda terlalu besar");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            } else if (tebakan < angkaTebak){
                System.out.println("Tebakan anda terlalu kecil");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            } else {
                tertebak = true;
                assert wilayah != null;
                if (wilayah.getItem() == null) {
                    System.out.println("Item tidak ditemukan");
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                } else {
                    ambilItem(wilayah.getItem(), 0);
                }
            }
        } while (!tertebak && langkah < 3);
    }

    public void findSorcerer() {
        String[] huruf = {"a", "i", "u", "e", "o"};
        Wilayah wilayah = Aksi.findWilayah("Goa Praha", getArrWilayah());
        if (wilayah == null) {
            System.out.println("Wilayah tidak diketahui");
        } else {
            System.out.println("Selamat datang di "+wilayah.getNama());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Temukan sorcerer stone ");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Temukan sorcerer stone untuk membuka square magic, \ndengan menebak huruf vokal kemungkinan yang muncul dalam 3 kali kesempatan");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        int angkaTebak = (int) (Math.random() * huruf.length);
        int nilaiTeb = -1,langkah = 0;

        boolean tertebak = false;

        do {
            langkah++;
            System.out.print("Masukan tebakan huruf vokal anda : ");
            String tebakan = sc.next();
            String tebakanLow = tebakan.toLowerCase();
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < huruf.length; i++) {
                if (tebakanLow.equals(huruf[i])) {
                    nilaiTeb = i;
                }
            }
            if (nilaiTeb > angkaTebak) {
                System.out.println("Tebakan anda terlalu besar");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            } else if (nilaiTeb < angkaTebak){
                System.out.println("Tebakan anda terlalu kecil");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            } else {
                tertebak = true;
                assert wilayah != null;
                ambilItem(wilayah.getItem(), 3);
            }
        } while (!tertebak && langkah < 3);
    }

    @Override
    public void jalankanMisi() {
        boolean statusMisi;
        int hpKsatriaAwal = GameInfo.getKsatriaPlayer().getHp();
        objArena.setGameOver(false);
        if (getArrTask().get(0) == 0) {
            findAmplopPeta();
        } else if (getArrTask().get(1) == 0) {
            Karakter elf = Aksi.findKarakter("Elf", getArrMusuh());
            if (elf != null) {
                objArena.perang(elf);
                statusMisi = objArena.isGameOver(hpKsatriaAwal, Aksi.findKarakter("Elf", getArrMusuh()));
                if (statusMisi) getArrTask().add(1,1);
            } else {
                System.out.println("elf tidak ditemukan");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            }
        } else if (getArrTask().get(2) == 0) {
            Karakter zack = Aksi.findKarakter("Zack Barks(Vampir) Lv.1", getArrMusuh());
            if (zack != null) {
                objArena.perang(zack);
                statusMisi = objArena.isGameOver(hpKsatriaAwal, Aksi.findKarakter("Zack Barks(Vampir) Lv.1", getArrMusuh()));
                if (statusMisi) getArrTask().add(2,1);
            } else {
                System.out.println("Zack Barks(Vampir) Lv.1 tidak ditemukan");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            }
        } else if (getArrTask().get(3) == 0) {
            findSorcerer();
        } else if (getArrTask().get(4) == 0) {
            if(Aksi.findItem("Bola Elemental 1",GameInfo.getKsatriaPlayer().getArrItem()) == null) {
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("Temui NPC untuk mengambil bola elemental pertama anda");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            } else {
                tambahLevelSetelahMisi();
                System.out.println("Misi satu selesai");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("Level anda naik menjadi level "+GameInfo.getKsatriaPlayer().getLevel());
                System.out.println("-----------------------------------------------------------------------------------------------------------");

                //init Misi yang Aktif
                Misi2 objMisi2 = new Misi2();
                objMisi2.setArrWilayah(GameInfo.getMisiAktif().getArrWilayah());
                objMisi2.setArrMusuh(GameInfo.getMisiAktif().getArrMusuh());
                objMisi2.setArrNPC(GameInfo.getMisiAktif().getArrNPC());
                objMisi2.setArrItem(GameInfo.getMisiAktif().getArrItem());
                GameInfo.setMisiAktif(objMisi2);
                GameInfo.setMisiAktifIn(2);
                setIsmenuMisi(true);
            }
        }
    }

    public void prosesTemuiNPC(int pilihan) {
        if (pilihan == 1) {
            if (getArrTask().get(3) == 0) {
                System.out.println("Selesaikan seluruh misi untuk mengambil bola elemental");
            } else {
                if (getArrItem().get(1) == null) {
                    System.out.println("Bola Elemental pertama telah anda ambil");
                } else {
                    GameInfo.getKsatriaPlayer().getArrItem().add(getArrItem().get(1));
                    getArrItem().remove(1);
                    System.out.println("Bola Elemental pertama berhasil disimpan di penyimpanan anda");
                }
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public void temuiNPC() {
        Aksi.printAksiNPC(getArrNPC(),1);
        int pil = Aksi.pilihAksi();
        prosesTemuiNPC(pil);
    }

}
