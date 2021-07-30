package misi;

import items.Item;
import items.ItemKhusus;
import engine.GameInfo;
import engine.Aksi;
import tempat.Wilayah;
import karakter.Karakter;

public class Misi2 extends Misi{
    private boolean isActive = false;
    private boolean isBaca = false;

    public Misi2() {
        this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);
        this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);this.getArrTask().add(0);
        this.setStatusMisi(false);
        this.setNama("Misi Dua");
        this.setKoinYangDidapat(50);
        this.setDeskripsi("Hallo ksatria! Selamat atas keberhasilanmu menyelesaikan misi 1!\n" +
                "Namun, perjalananmu belum usai! siapkan tenagamu untuk melanjutkan misi\n" +
                "Setelah mendapatkan bola elemental pertama, kamu harus mencari 4 bola elemental lagi\n" +
                "Jika kamu sudah mempunyai Sorcerer Stone, silahkan tukarkan item tersebut dengan ramuan\n" +
                "Ramuan tersebut kamu gunakan untuk membangunkan kurcaci yang dikutuk oleh Aron Sang Tentara Iblis\n" +
                "Nanti kurcaci tersebut akan memberikanmu Mystic Key untuk membuka pintu apa saja\n" +
                "Hint: Gunakan kunci tersebut untuk membuka ruang penyimpanan di Quil Kegelapan\n" +
                "Temukan item Sorcrux yang akan berguna untuk membunuh Iblis Tanah, Elder dan Zack Barks (Vampir)\n"+
                "Setelah berhasil membunuhnya, temukanlah Kano yang akan berguna untuk akses masuk ke ruang tersembunyi\n" +
                "Masuklah kesana dan dapatkan Bola Elemental keduamu\n");
    }

    public void ambilItem(Item item, int index) {
        System.out.println("Apakah Anda ingin mengambil " +item.getNama()+"?");
        while(true){
            System.out.println(" 1. Ambil ");
            System.out.println(" 2. Biarkan");
            System.out.print("Masukkan : ");
            int pilih = sc.nextInt();
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            if (pilih == 1) {
                System.out.println("Selamat anda berhasil mendapatkan " +item.getNama());
                GameInfo.getKsatriaPlayer().getArrItem().add(item);
                getArrTask().add(index, 1);
                break;
            } else if (pilih == 2){
                System.out.println("Anda tidak akan bisa lanjut kemisi selanjutnya, untuk melanjutkan misi silahkan ulangi misi ini dan ambil "+item.getNama());
                break;
            } else {
                System.out.println("Masukkan pilihan yang tersedia");
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }
    }

    public void tukarSorcererStone(){
        ItemKhusus sorcererStone = (ItemKhusus) Aksi.findItem("Sorcerer Stone", GameInfo.getKsatriaPlayer().getArrItem());
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------- Dapatkan Ramuan dari Grumpy ---------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Tukarkan Sorcerer Stone mu!\n");
        System.out.println("Apakah ingin menukar Sorcerer Stone dengan hadiah dari Grumpy yaitu Ramuan?");
        System.out.println("1) Ya \n2) Tidak");
        int pil = Aksi.pilihAksi();
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        if(pil==1){
            GameInfo.getKsatriaPlayer().getArrItem().remove(sorcererStone);
            prosesTukarSorcererStone();
        }
        else{
            System.out.println("Sorcerer Stone harus ditukar untuk melanjutkan misi");
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public void prosesTukarSorcererStone(){
        ItemKhusus ramuan  = new ItemKhusus(5, "Ramuan", "Terdapat di Misi 2 berfungsi untuk membangunkan kurcaci");
        System.out.println("Ups! Sebelum menukarnya, kamu harus jawab pertanyaan ini dulu\n");
        System.out.println("Anda punya kesempatan menjawab 3 kali");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for(int i=1;i<4;i++){
            System.out.println("Kesempatan ke-"+i);
            System.out.println(" 20 : 5 (3 + 1) = ?");
            System.out.println("Berapa jumlahnya?");
            int jawab = sc.nextInt();
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            if (jawab == 16){
                GameInfo.getKsatriaPlayer().getArrItem().add(ramuan);
                getArrTask().add(0, 1);
                System.out.println("Pintar! Jawabanmu benar");
                System.out.println("Kamu mendapatkan Ramuan!");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                Aksi.pause();
                System.out.println("Anda telah menukar Sorcerer Stone dengan Ramuan!");
                break;
            }
            else{
                System.out.println("Jawabanmu masih salah, Ayo coba lagi");
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }
    }

    public void bangunkanKurcaci(){
        ItemKhusus mysticKey = new ItemKhusus(5, "Mystic Key", "Terdapat di Misi 2 berfungsi sebagai kunci kemana saja");
        ItemKhusus ramuan = (ItemKhusus) Aksi.findItem("Ramuan", GameInfo.getKsatriaPlayer().getArrItem());
        System.out.println("Ayo bangunkan Kurcaci!");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("[1] Gunakan Ramuan");
        System.out.println("[2] Biarkan");
        System.out.print("Masukkan : ");
        int pilih = sc.nextInt();
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        if (pilih == 1) {
            GameInfo.getKsatriaPlayer().getArrItem().remove(ramuan);
            System.out.println("Kurcaci : 'Terima kasih Telah Membangunkanku'\n'Ku berikan Mystic Key sebagai imbalannya' ");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            Aksi.pause();
            System.out.println("Selamat anda berhasil mendapatkan " + mysticKey.getNama());
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            GameInfo.getKsatriaPlayer().getArrItem().add(mysticKey);
            getArrTask().add(1, 1);
        } else if (pilih == 2) {
            System.out.println("Anda tidak akan bisa lanjut ke misi selanjutnya, \nuntuk melanjutkan misi silahkan bangunkan Kurcaci");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("Masukkan pilihan yang tersedia");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }
    }

    public void findSorcrux(){
        Wilayah wilayah = Aksi.findWilayah("Quil Kegelapan", getArrWilayah());
        if (wilayah == null) {
            System.out.println("Wilayah tidak diketahui");
        } else {
            System.out.println("Selamat datang di "+wilayah.getNama());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println(" Temukan Sorcrux di Ruang Penyimpanan Quil Kegelapan ");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Ayo masuk ke Ruang Penyimpanan");
        System.out.println("*Hint: Gunakan Mystic Key untuk masuk ke Ruang Penyimpanan");
        System.out.println("[1] Masuk dan Gunakan Mystic Key");
        System.out.println("[0] Tidak");
        int pil = Aksi.pilihAksi();
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        if (pil==1){
            System.out.println("Mystic Key berhasil digunakan!");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println("--------------------------- Selamat Datang di Ruang Penyimpanan Quil Kegelapan ----------------------------");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            Aksi.pause();
            System.out.println("Sorcrux Ditemukan!");
            assert wilayah != null;
            ambilItem(wilayah.getItem(), 2);
            System.out.println("Sorcrux digunakan untuk membantu mengalahkan Iblis Tanah Elder dan Zack Barks (Vampir).");
        }
        else {
            System.out.println("Anda tidak akan bisa lanjut ke misi selanjutnya, \nuntuk melanjutkan misi silahkan bangunkan Kurcaci");
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public void aktifkanSorcrux(){
        System.out.println("Perjalananmu untuk mendapatkan Bola Elemental 2 sudah semakin dekat!");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------- Temukan Kano -----------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Tapi..... Quil Kegelapan terlalu luas dan gelap...");
        System.out.println("Aktifkan sinar pada Sorcrux untuk memberikan penerangan di Quil Kegelapan");
        System.out.println("1. Aktifkan sinar Sorcrux");
        System.out.println("2. Tidak");
        int pil = Aksi.pilihAksi();
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        if(pil==1){
            System.out.println("Ups! Sorcrux meminta kamu untuk memasukkan kode yang didapatkan dari petunjuk ini");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println("Anda punya kesempatan menjawab 3 kali");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            for(int i=1;i<4;i++){
                System.out.println("Kesempatan ke-"+i);
                System.out.println("Hasil dari 64 x 826 : 28 adalah ....");
                System.out.println("Berapa jumlahnya?");
                int jawab = sc.nextInt();
                if (jawab == 1888){
                    getArrTask().add(5, 1);
                    isActive = true;
                    System.out.println("Pintar! Jawabanmu benar");
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    Aksi.pause();
                    System.out.println("Sinar Sorcrux aktif!");
                    System.out.println("Kini perjalananmu mencari Kano di Quil Kegelapan menjadi semakin mudah");
                    break;
                }
                else{
                    System.out.println("Jawabanmu masih salah, Ayo coba lagi");
                }
            }
        }
        else {
            System.out.println("Anda akan kesulitan mencari Kano jika tidak menyalakan sinar Sorcrux!");
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public void findKano(){
        ItemKhusus kano  = new ItemKhusus(5, "Kano", "Terdapat di Misi 2 berfungsi untuk Akses masuk ke ruang tersembunyi");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Perjalananmu untuk mendapatkan Bola Elemental 2 sudah semakin dekat!");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------- Temukan Kano -----------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Pastikan sinar Sorcrux-mu menyala!");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        Aksi.pause();
        if(isActive){
            System.out.println("Yeayy!! Kano berhasil ditemukan dan didapatkan!");
            GameInfo.getKsatriaPlayer().getArrItem().add(kano);
            getArrTask().add(6, 1);
        }
        else{
            System.out.println("Ups! Sinar Sorccrux mu belum menyala\nAyo nyalakan dulu!");
            aktifkanSorcrux();
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public void useKano(){
//        ItemKhusus kano = (ItemKhusus) Aksi.findItem("Kano", GameInfo.getKsatriaPlayer().getArrItem());
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------- Masuk ke Ruang Tersembunyi ----------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Gunakan Kano untuk masuk ke Ruang Tersembunyi");
        System.out.println("1. Masuk dan Gunakan Kano");
        System.out.println("2. Tidak");
        int pil = Aksi.pilihAksi();
        if(pil==1){
            System.out.println("Ups! Untuk bisa memasukinya, Anda harus memiliki Kano dan memecahkan kode yang didapatkan dari petunjuk ini");
            System.out.println("Anda punya kesempatan menjawab 3 kali");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            for(int i=1;i<4;i++){
                System.out.println("Kesempatan ke-"+i);
                System.out.println("Hasil dari 4500 : (870 - 370) x 9 adalah ....");
                System.out.print("Berapa hasilnya? ");
                int jawab = sc.nextInt();
                if (jawab == 1){
                    getArrTask().add(7, 1);
                    System.out.println("Pintar! Jawabanmu benar");
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    Aksi.pause();
                    System.out.println("Pintu Ruang tersembunyi terbuka!");
                    System.out.println("Kini anda telah memasuki Ruang Tersembunyi");
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    while(!isBaca){
                        System.out.println("Ada sebuah petunjuk!");
                        System.out.println("1. Baca");
                        int pil2 = Aksi.pilihAksi();
                        if(pil2==1){
                            System.out.println("Selamat!!!!! Anda telah berhasil menyelesaikan Misi 2");
                            System.out.println("Temui Aron untuk memberikan kabar gembira ini dan dapatkan Bola Elemental!");
                            isBaca = true;
                        }
                        else{
                            System.out.println("Pilihan tidak tersedia");
                        }
                    }
                    break;
                }
                else{
                    System.out.println("Jawabanmu masih salah, Ayo coba lagi");
                }
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            }
        }
        else {
            System.out.println("Anda tidak akan mendapatkan Bola Elemental jika tidak masuk kedalam Ruang Tersembunyi!");
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void jalankanMisi() {
        boolean statusMisi;
        int hpKsatriaAwal = GameInfo.getKsatriaPlayer().getHp();
        objArena.setGameOver(false);
        if (getArrTask().get(0) == 0) {
            tukarSorcererStone();
        }
        else if (getArrTask().get(1) == 0){
            bangunkanKurcaci();
        }
        else if (getArrTask().get(2) == 0){
            findSorcrux();
        }
        else if (getArrTask().get(3) == 0) {
            Karakter iblis =  Aksi.findKarakter("Iblis Tanah Elder", getArrMusuh());
            if (iblis == null) {
                System.out.println("Iblis tanah Elder tidak ditemukan");
            } else {
                objArena.perang(iblis);
                statusMisi = objArena.isGameOver(hpKsatriaAwal, Aksi.findKarakter("Iblis Tanah Elder", getArrMusuh()));
                if (statusMisi) getArrTask().add(3,1);
            }
        } else if (getArrTask().get(4) == 0) {
            Karakter zack = Aksi.findKarakter("Zack Barks(Vampir) Lv.2", getArrMusuh());
            if (zack == null) {
                System.out.println("Zack Barks tidak ditemukan");
            } else {
                objArena.perang(zack);
                statusMisi = objArena.isGameOver(hpKsatriaAwal, Aksi.findKarakter("Zack Barks(Vampir) Lv.2", getArrMusuh()));
                if (statusMisi) getArrTask().add(4,1);
            }
        }
        else if (getArrTask().get(5) == 0){
            aktifkanSorcrux();
        }
        else if (getArrTask().get(6) == 0){
            findKano();
        }
        else if (getArrTask().get(7) == 0){
            useKano();
        }
        else if (getArrTask().get(8) == 0) {
            if(Aksi.findItem("Bola Elemental 2",GameInfo.getKsatriaPlayer().getArrItem()) == null) {
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("Temui Aron untuk mengambil bola elemental kedua anda");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            } else {
                tambahLevelSetelahMisi();
                System.out.println("Misi dua selesai");
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("Level anda naik menjadi level "+GameInfo.getKsatriaPlayer().getLevel());
                System.out.println("-----------------------------------------------------------------------------------------------------------");

                //init Misi yang Aktif
                Misi3 objMisi3 = new Misi3();
                objMisi3.setArrWilayah(GameInfo.getMisiAktif().getArrWilayah());
                objMisi3.setArrMusuh(GameInfo.getMisiAktif().getArrMusuh());
                objMisi3.setArrNPC(GameInfo.getMisiAktif().getArrNPC());
                objMisi3.setArrItem(GameInfo.getMisiAktif().getArrItem());
                GameInfo.setMisiAktif(objMisi3);
                GameInfo.setMisiAktifIn(3);
                setIsmenuMisi(true);
            }
        }
    }

    public void prosesTemuiNPC(int pilihan) {
        switch (pilihan) {
            case 1:
                if (getArrTask().get(0) == 0) {
                    System.out.println("Halo! Namaku Grumpy, aku akan membantumu!");
                    System.out.println("Silahkan tukarkan Sorcerer Stone mu denganku! Akan kuberikan Ramuan untuk membangunkan Kurcaci!");
                } else if (getArrTask().get(1) == 0){
                    System.out.println("Bangunkanlah Kurcaci dengan menggunakan Ramuan");
                } else{
                    System.out.println("Bicaralah dengan Aron!");
                }
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                break;
            case 2:
                if (getArrTask().get(0) == 0) {
                    System.out.println("Bicaralah dengan Grumpy!");
                } else if (getArrTask().get(1) == 0){
                    System.out.println("Bicaralah dengan Grumpy!");
                } else if (getArrTask().get(2) == 0){
                    System.out.println("Halo! Namaku Aron, aku akan membantumu!");
                    System.out.println("Masuk ke Ruang Penyimpanan dengan menggunakan Mystic key");
                } else if (getArrTask().get(3) == 0){
                    System.out.println("Bunuh Iblis Tanah Elder!");
                } else if (getArrTask().get(4) == 0){
                    System.out.println("Bunuh Zack Barks (Vampir)!");
                } else if (getArrTask().get(5) == 0){
                    System.out.println("Aktifkan sinar Sorcrux untuk menerangi Quil Kegelapan");
                } else if (getArrTask().get(6) == 0){
                    System.out.println("Temukan Kano untuk akses masuk ke ruang tersembunyi, disana kamu akan mendapatkan Bola Elemental");
                } else if (getArrTask().get(7) == 0){
                    System.out.println("Masuklah ke Ruang Tersembunyi dengan menggunakan Kano!");
                } else {
                    if(getArrItem().get(2) == null) {
                        System.out.println("Bola Elemental kedua telah anda ambil");
                    } else {
                        GameInfo.getKsatriaPlayer().getArrItem().add(getArrItem().get(2));
                        getArrItem().remove(2);
                        System.out.println("Bola Elemental kedua berhasil disimpan di penyimpanan anda");
                    }
                }
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                break;
        }
    }

    @Override
    public void temuiNPC() {
        Aksi.printAksiNPC(getArrNPC(),2);
        int pil = Aksi.pilihAksi();
        prosesTemuiNPC(pil);
    }
}
