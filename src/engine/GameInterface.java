package engine;

import java.util.ArrayList;

public class GameInterface {
    boolean isStop = false;
    GameEngine objGameEngine = new GameEngine();
    private ArrayList<String> arrMenuAwal = new ArrayList<>();

    public GameInterface() {
        arrMenuAwal.add("Mulai Permainan");
        loopMenuAwal();
    }

    public void setStory1(){
        judul();
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("\n\n\n" +
                "|---------------------------------------------------------------------------------------------------------|\n" +
                "|     Pada suatu ketika munculah seorang kesatria Langit, di Kota Moskawa dengan membawa cincin keabadian |\n" +
                "| yang telah jatuh sejak masa kegelapan. Cincin itu bisa membawa sang  kesatria menuju Kerajaan Andromeda |\n" +
                "| yang telah mengalami keruntuhan Sang kesatria kemudian bertemu dengan Raja Nyctophillic, lalu sang raja |\n" +
                "| memberikan misi penyelamatan pada Ksatria Langit untuk menemukan Sang Putri Ranivary yang telah diculik |\n" +
                "| oleh Pangeran Kegelapan Lord Hellsprint di Quil keabadian melalui cincin itu.                           |\n" +
                "|     Namun, untuk bisa  mendapatkan akses menuju Quil keabadian itu, Sang  Kesatria harus mencari 5 bola |\n" +
                "| elemental  yang  sudah  menghilang  sejak masa kegelapan. Menurut desas - desus ke-5 bola elemental itu |\n" +
                "| dijaga oleh  masing - masing  iblis  di muka  bumi  ini Iblis tanah, Iblis api, Iblis air, Iblis angin. |\n" +
                "| Dan yang satunya berada di sebuah perairan.                                                             |\n" +
                "|---------------------------------------------------------------------------------------------------------|\n\n");

        Aksi.pause();
    }

    public void loopMenuAwal() {
        while (!isStop) {
            menuAwal();
        }
    }

    public void pilihKarakter() {
        objGameEngine.mulaiPilihKarakter();
        Aksi.pause();
    }

    public void detailKarakter() {
        judul();
        System.out.println("___________________________________________________________________________________________________________");
        System.out.println(".... : Haii, Selamat Datang");Aksi.pause();
        System.out.println(".... : Terdapat 4 kesatria langit yang dapat anda pilih, untuk menyelamatkan sang putri");Aksi.pause();
        System.out.println(".... : Untuk memulai permainan, langkah pertama adalah pilihlah salah satu dari ke 4 kesatria tersebut");Aksi.pause();
        System.out.println(".... : Berikut akan saya, tampilkan terlebih dahulu setiap karakteristik para kesatria");Aksi.pause();
        System.out.println("___________________________________________________________________________________________________________");
        objGameEngine.getObjKsatria().printArray();
    }

    public void prosesMenuAwal(int pilihan) {
        switch (pilihan) {
            case 1:
                objGameEngine.setStopPilihanKsatria(false);
                objGameEngine.setLoopAksiUtama(false);

                if (GameInfo.getKsatriaPlayer() == null) {
                    setStory1();
                    detailKarakter();
                    judul();
                    pilihKarakter();
                }
                judul();
                objGameEngine.loopAksiUtama();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan yang anda masukan tidak tersedia");
                Aksi.pause();
                break;
        }
    }

    public void judul() {

        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.print("" +
                "\n" +
                "   _       _                 _                         _          ___      _           _ _                \n" +
                "  /_\\   __| |_   _____ _ __ | |_ _   _ _ __ __ _    __| | ___    / __\\__ _| |__   __ _| | | ___ _ __ ___  \n" +
                " //_\\\\ / _` \\ \\ / / _ \\ '_ \\| __| | | | '__/ _` |  / _` |/ _ \\  / /  / _` | '_ \\ / _` | | |/ _ \\ '__/ _ \\ \n" +
                "/  _  \\ (_| |\\ V /  __/ | | | |_| |_| | | | (_| | | (_| |  __/ / /__| (_| | |_) | (_| | | |  __/ | | (_) |\n" +
                "\\_/ \\_/\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\__,_|  \\__,_|\\___| \\____/\\__,_|_.__/ \\__,_|_|_|\\___|_|  \\___/ \n" +
                "                                                                                                          \n");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public void menuAwal() {
        judul();
        Aksi.printAksi(arrMenuAwal);
        System.out.println("-----------------------------------------------------------------------------------------------------------");


        int pilihan = Aksi.pilihAksi();
        prosesMenuAwal(pilihan);
    }
}
