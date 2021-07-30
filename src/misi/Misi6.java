package misi;

import items.ItemKhusus;
import engine.Aksi;
import engine.GameInfo;
import karakter.Karakter;
import java.util.ArrayList;

public class Misi6 extends Misi{
    private ArrayList<String> arrAksiItem = new ArrayList<>();
    ItemKhusus cincin;
    ItemKhusus bola1;
    ItemKhusus bola2;
    ItemKhusus bola3;
    ItemKhusus bola4;
    ItemKhusus bola5;

    public Misi6() {
        this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);
        this.setStatusMisi(false);
        this.setNama("Misi Enam");
        this.setKoinYangDidapat(500);
        this.setDeskripsi("EXCELENT! GOOD JOB KSATRIA \n" +
                "Kini anda berada di Chapter terakhir perjalanan anda menyelesaikan misi ini \n" +
                "yang perlu anda lakukan pertama kali adalah menggunakan Peta Quil Keabadian \n" +
                "untuk menemukan Putri Raja dan Pangerangan Kegelapan.\n" +
                "Pakailah cincin keabadian itu untuk menyatukan ke 5 bola elemental yang dibantu\n" +
                "oleh peri penolong untuk mengalahkan Pangeran Kegelapan Lord Helisprints.\n"+
                "Anda harus menghancurkan cincin keabadian dengan menggunakan kekuatan dari ke 5 bola elemental\n" +
                "agar tidak ada lagi masa terlahir kembali pangeran kegelapan\n" +
                "Setelah selesai melalui semua rintangan dan misi, anda akan menerima hadiah\n" +
                "dari sang raja dengan menikahi putri raja kerajaan Andromeda\n");

        getArrAksiItem().add("Iya");
        getArrAksiItem().add("Tidak");
    }

    public ArrayList<String> getArrAksiItem() {
        return arrAksiItem;
    }

    public void findPutriRaja() {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("------------------------------- Temukan Putri Raja dan Pangeran Kegelapan ---------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Gunakan Amplop Peta Castle untuk menemukan\ntempat putri raja dan pangeran kegelapan");
        Aksi.printAksi(getArrAksiItem());
        int pilihan = Aksi.pilihAksi();
        switch (pilihan) {
            case 1:
                ItemKhusus peta = (ItemKhusus) Aksi.findItem("Amplop Peta Castle",GameInfo.getKsatriaPlayer().getArrItem());
                if (peta == null) {
                    System.out.println("Amplop Peta Castle belum anda dapatkan");
                } else {
                    getArrItem().put(12,peta);
                    GameInfo.getKsatriaPlayer().getArrItem().remove(peta);
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------- Anda berhasil menemukan tempat pangeran kegelapan ----------------------------");
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    getArrTask().add(0, 1);
                }
                break;
            case 2:
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("--------------------- Misi gagal, pangeran kegelapan dan putri raja gagal ditemukan -----------------------");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                break;
        }
    }

    boolean useCincinBola() {
        cincin = (ItemKhusus) Aksi.findItem("Cincin Keabadian",GameInfo.getKsatriaPlayer().getArrItem());
        bola1 = (ItemKhusus) Aksi.findItem("Bola Elemental 1",GameInfo.getKsatriaPlayer().getArrItem());
        bola2 = (ItemKhusus) Aksi.findItem("Bola Elemental 2",GameInfo.getKsatriaPlayer().getArrItem());
        bola3 = (ItemKhusus) Aksi.findItem("Bola Elemental 3",GameInfo.getKsatriaPlayer().getArrItem());
        bola4 = (ItemKhusus) Aksi.findItem("Bola Elemental 4",GameInfo.getKsatriaPlayer().getArrItem());
        bola5 = (ItemKhusus) Aksi.findItem("Bola Elemental 5",GameInfo.getKsatriaPlayer().getArrItem());

        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------- Musnahkan keabadian Lord Hellsprint ------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Gunakan dan satukan cicin keabadian dan 5 bola elemental");
        Aksi.printAksi(getArrAksiItem());
        int pilihan = Aksi.pilihAksi();
        switch (pilihan) {
            case 1:
                if (cincin == null) {
                    System.out.println("Cincin Keabadian belum anda dapatkan");
                } else {
                    if (bola1 == null || bola2 == null || bola3 == null || bola4 == null || bola5 == null) {
                        System.out.println("Bola Elemental belum lengkap");
                        return false;
                    } else {
                        System.out.println("-----------------------------------------------------------------------------------------------------------");
                        System.out.println("--------------------------------------------- MISSION COMPLETE! -------------------------------------------");
                        System.out.println("-----------------------------------------------------------------------------------------------------------");
                        System.out.println("SELAMAT ANDA BERHASIL MENGALAHKAN Pangeran Kegelapan Lord Hellsprint.");
                        return true;
                    }
                }
                break;
            case 2:
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("Misi gagal, Pangeran Kegelapan Lord Hellsprint masih hidup");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                return false;
        }
        return false;
    }

    public void hancurKanCincin() {
        if (cincin == null) {
            System.out.println("Cincin telah di hancurkan");
            System.out.println("Langkah terakhir adalah Temui Putri Raja, setelah itu Temui Raja");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("Ada apa Ksatria?.......");
            System.out.println("Apakah Anda memikirkan sesuatu?");
            System.out.println(".. Ya...");
            System.out.println("Anda harus menghancurkan cincin keabadian itu dengan\nmenggunakan kekuatan dari ke 5" +
                    "bola elemental. \nagar pangeran kegelapan tidak terlahir kembali .");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------- Hancurkan Cincin Keabadian ---------------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println("Apakah anda akan menghancurkan cincin keabadian ? ");
            Aksi.printAksi(getArrAksiItem());
            int pilihan = Aksi.pilihAksi();
            if (pilihan == 1) {
                GameInfo.getKsatriaPlayer().getArrItem().remove(cincin);
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("Good Job!!");
                System.out.println("MAXIMAL MISSION COMPLETE\n" +
                        "ANDA TELAH BERHASIL MENGHANCURKAN CINCIN KEABADIAN. \nSEKARANG BAWA KEMBALI PUTRI RAJA KE KERAJAAN ANDROMEDA.." +
                        "Langkah terakhir adalah Temui Putri Raja, setelah itu Temui Raja");
                getArrTask().add(3,1);
            } else {
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("Hancurkan cincin, untuk menyelesaikan misi");
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public void jalankanMisi() {
        boolean statusMisi;
        int hpKsatriaAwal = GameInfo.getKsatriaPlayer().getHp();
        objArena.setGameOver(false);
        if (getArrTask().get(0) == 0) {
            findPutriRaja();
        } else if (getArrTask().get(1) == 0) {
            Karakter zack = Aksi.findKarakter("Zack Barks(Vampir) Lv.6", getArrMusuh());
            if (zack == null) {
                System.out.println("Zack vampir tidak ditemukan");
            } else {
                objArena.perang(zack);
                statusMisi = objArena.isGameOver(hpKsatriaAwal, Aksi.findKarakter("Zack Barks(Vampir) Lv.6", getArrMusuh()));
                if (statusMisi) getArrTask().add(1,1);
            }
        } else if (getArrTask().get(2) == 0) {
            Karakter musuh = Aksi.findKarakter("Lord Hellsprint", getArrMusuh());
            if (musuh == null) {
                System.out.println("Lord Hellsprint tidak ditemukan");
            } else {
                objArena.perang(musuh);
                if (useCincinBola()) {
                    statusMisi = objArena.isGameOver(hpKsatriaAwal);
                    if (musuh.isLife()) {
                        if (statusMisi) getArrTask().add(2,1);
                    } else {
                        useCincinBola();
                    }
                }
            }
        } else if (getArrTask().get(3) == 0) {
            hancurKanCincin();
        } else if (getArrTask().get(4) == 0) {
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println("--------------------------------------------- Temui Putri Raja --------------------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        } else if (getArrTask().get(5) == 0) {
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------- Raja Nyctophillic --------------------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        } else {
            if (getArrTask().get(5) == 1) {
                System.out.println("Ksatria menerima imbalan Sang Raja.. Dan menikah dengan Sang Putri.." +
                        "Thank you for your coming to my game!\n" +
                        "Hope you enjoyed! And thank you for complete our mission Ksatria!...^^\n");

                System.exit(0);
            }
        }
    }

    public void bangunKerajaan() {
        GameInfo.getKsatriaPlayer().getArrItem().remove(bola1);
        GameInfo.getKsatriaPlayer().getArrItem().remove(bola2);
        GameInfo.getKsatriaPlayer().getArrItem().remove(bola3);
        GameInfo.getKsatriaPlayer().getArrItem().remove(bola4);
        GameInfo.getKsatriaPlayer().getArrItem().remove(bola5);
        System.out.println("Selamat ksatria anda telah berhasil membangun kembali kerajaan yang sudah runtuh");
        System.out.println("Temui Raja untuk menerima imbalan");
        getArrTask().add(4, 1);
    }

    public void prosesTemuiNPC(int pilihan) {
        switch (pilihan) {
            case 1:
                if (getArrTask().get(3) == 0) {
                    System.out.println("Selesaikan seluruh misi untuk membawa sang Putri ke kerajaan");
                } else {
                    if (Aksi.findItem("Bola Elemental 1",GameInfo.getKsatriaPlayer().getArrItem()) == null && Aksi.findItem("Bola Elemental 2",GameInfo.getKsatriaPlayer().getArrItem()) == null && Aksi.findItem("Bola Elemental 3",GameInfo.getKsatriaPlayer().getArrItem()) == null && Aksi.findItem("Bola Elemental 4",GameInfo.getKsatriaPlayer().getArrItem()) == null && Aksi.findItem("Bola Elemental 5",GameInfo.getKsatriaPlayer().getArrItem()) == null) {
                        System.out.println("Tuan Putri berhasil diselamatkan dan kerajaan berhasil dibangun kembali");
                    } else {
                        System.out.println("Tuan Putri berhasil diselamatkan dan sudah siap untuk kembali");
                        System.out.println("Sebelum itu gunakanlah Bola Elemental untuk membangun kembali kerajaan yang telah runtuh");
                        System.out.println("1. Ya");
                        System.out.println("2. Tidak");
                        int pil = Aksi.pilihAksi();
                        if(pil == 1) {
                            bangunKerajaan();
                        } else {
                            System.out.println("Anda harus membangun kembali kerajaan dan hidup bahagia bersama Putri Ranivary");
                        }
                    }
                }
                break;
            case 2 :
                if (getArrTask().get(4) == 0) {
                    System.out.println("Temui putri terlebih dahulu");
                } else {
                    System.out.println("HALLO KSATRIA terima kasih telah mengubah kehidupan\n" +
                            "di Kerajaan Andromeda menjadi makmur kembali\n" +
                            "Saya Raja Nyctophillic memberikan anugerah dan imbalan kepada Anda..\n" +
                            "Saya akan menikahkan Putri Saya Ranivary dengan Anda Ksatria..");
                            getArrTask().add(5, 1);
                }
                break;
        }
    }

    public void temuiNPC() {
        Aksi.printAksiNPC(getArrNPC(),6);
        int pil = Aksi.pilihAksi();
        prosesTemuiNPC(pil);
    }
}
