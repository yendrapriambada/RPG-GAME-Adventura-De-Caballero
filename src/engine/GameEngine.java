package engine;

import items.*;
import karakter.*;
import tempat.Wilayah;
//import misi.Misi;
import misi.Misi1;
import toko.Toko;
import java.util.ArrayList;
import java.util.HashMap;

public class GameEngine {
    private Item objItem = new Item();
    private Karakter objKarakter = new Karakter();
    private Ksatria objKsatria = new Ksatria();
    private Wilayah objWilayah = new Wilayah();
//    private Misi objMisi = new Misi();
//    private NPC objNPC = new NPC();
    private Toko objToko = new Toko();
    private boolean isStopPilihanKsatria = false;
    private boolean isLoopAksiUtama = false;


    ArrayList<String> arrAksiUtama = new ArrayList<>();

    //Objek Senjata
    Senjata pisau = new Senjata(1,"Pisau",1,10,20,3,0,100);
    Senjata panah = new Senjata(1,"Panah",1,20,40,3,0,300);
    Senjata tombak = new Senjata(1,"Tombak",1,60,30,3,0,500);
    Senjata trisula = new Senjata(1,"Trisula",1,10,20,3,0,600);
    Senjata pedang = new Senjata(1,"Pedang",1,10,20,3,0,100);

    //Objek Perisai
    Perisai perisaiKayu = new Perisai(2,"Perisai Kayu",1,10,20,3,0,50);
    Perisai perisaiStainlessSteel = new Perisai(2,"Perisai Stainless Steel",1,20,30,3,0,50);
    Perisai perisaiTembaga = new Perisai(2,"Perisai Tembaga",1,10,20,3,0,50);
    Perisai perisaiTrisulaMetal = new Perisai(2,"Perisai Trisula Metal",1,10,20,3,0,50);
    Perisai perisaiTimbal = new Perisai(2,"Perisai Timbal",1,10,20,3,0,50);

    //Objek Kostum
    Kostum legionnaire = new Kostum(3, "legionnaire", 30, 50, 20);
    Kostum hoplites = new Kostum(3, "Hoplites", 40, 60, 30);
    Kostum catapharact = new Kostum(3, "Catapharact", 50, 70, 40);
    Kostum derRitterBruder = new Kostum(3, "Der Ritter Bruder", 70, 90, 60);
    Kostum knightTemplar = new Kostum(3, "Knight Templar", 70, 90, 60);

    //Objek Obat
    Obat healingPotion = new Obat(4, "Healing Potion", 5, 10, 200, 0, 1);
    Obat stimulant = new Obat(4, "Stimulant", 20, 10, 500, 0, 1);
    Obat manaPotion = new Obat(4, "Mana Potion", 5, 10, 0, 20, 2);
    Obat elixir = new Obat(4, "Elixir", 10, 20, 0, 50, 2);
    Obat ginseng = new Obat(4, "Ginseng", 15, 25, 200, 50, 3);
    Obat polish = new Obat(4, "Polish", 15, 25, 500, 20, 3);

    //Objek Item Khusus
    ItemKhusus amplopPetaCastle = new ItemKhusus(5, "Amplop Peta Castle", "Untuk melihat peta quil keabadian pada misi 1");
    ItemKhusus bolaElemental1 = new ItemKhusus(5, "Bola Elemental 1", "Bola Elemental Pertama");
    ItemKhusus bolaElemental2 = new ItemKhusus(5, "Bola Elemental 2", "Bola Elemental Kedua");
    ItemKhusus bolaElemental3 = new ItemKhusus(5, "Bola Elemental 3", "Bola Elemental Ketiga");
    ItemKhusus bolaElemental4 = new ItemKhusus(5, "Bola Elemental 4", "Bola Elemental Keempat");
    ItemKhusus bolaElemental5 = new ItemKhusus(5, "Bola Elemental 5", "Bola Elemental Kelima");
    ItemKhusus cincinKeabadian = new ItemKhusus(5, "Cincin Keabadian", "Item Khusus Milik Ksatria");
    ItemKhusus mysticKey = new ItemKhusus(5, "Mystic Key", "Terdapat di Misi 2 berfungsi sebagai kunci kemana saja");
    ItemKhusus ramuan  = new ItemKhusus(5, "Ramuan", "Terdapat di Misi 2 berfungsi untuk membangunkan kurcaci");
    ItemKhusus kano  = new ItemKhusus(5, "Kano", "Terdapat di Misi 2 berfungsi untuk Akses masuk ke ruang tersembunyi");
    ItemKhusus sorcrux  = new ItemKhusus(5, "Sorcrux", "Menerangi jalan kuil kegelapan, dapat ditukar dengan Star Ruby");
    ItemKhusus starRuby   = new ItemKhusus(5, "Star Ruby", "Terdapat di Misi 3 berfungsi untuk mengalahkan vampir");
    ItemKhusus tongkatSarda  = new ItemKhusus(5, "Tongkat Sarda", "Terdapat di Misi 3 sebagai Hadiah mengalahkan vampire untuk membantu ksatria menuju gunung berapi");
    ItemKhusus levistone = new ItemKhusus(5, "Levistone", "Terdapat di Misi 4 berfungsi untuk menerbangkan balon udara");
    ItemKhusus ratsTail = new ItemKhusus(5, "Rats Tail", "Item khusus permintaan dari raja astor yang terdapat di kastil wizard");
    ItemKhusus cairanNitrogen = new ItemKhusus(5, "Cairan nitrogen", "Terdapat di Misi 4 berfungsi untuk mengalahkan iblis air");
    ItemKhusus crystalEye = new ItemKhusus(5, "Crystal Eye", "Terdapat di Misi 5 berfungsi untuk membunuh tantra");
    ItemKhusus sorcererStone = new ItemKhusus(5, "Sorcerer Stone", "Item khusus untuk ditukar dengan ramuan");

    //objek Wilayah
    Wilayah goaPraha = new Wilayah("Goa Praha", 1, sorcererStone);
    Wilayah kotaKiev = new Wilayah("Kota Kiev", 1, amplopPetaCastle);
    Wilayah quilKegelapan = new Wilayah("Quil Kegelapan", 2, sorcrux);
    Wilayah kotaBrussels = new Wilayah("Kota Brussels", 3,tongkatSarda);
    Wilayah pegununganEs = new Wilayah("Pegunungan Es", 4, levistone);
    Wilayah kastilWizard = new Wilayah("Kastil Wizard", 4, ratsTail);
    Wilayah benuaUtara = new Wilayah("Benua Utara", 5, cairanNitrogen);
    Wilayah pulauTerapung = new Wilayah("Pulau Terapung", 6, crystalEye);
//    Wilayah sungaiBucharest = new Wilayah("Sungai Bucharest", 1, null);

    //objek Ksatria
    Ksatria goldwinMiller = new Ksatria(1, "Goldwin Miller", 200, 0, 0, 100, 0, 1, 100);
    Ksatria marcelloParker = new Ksatria(1, "Marcello Parker", 200, 0, 0, 100, 0, 1, 100);
    Ksatria braceHarison = new Ksatria(1, "Brace Harison",200, 0, 0, 100, 0, 1, 100);
    Ksatria marvinJensen = new Ksatria(1, "Marvin Jensen", 200, 0, 0, 100, 0, 1, 100);

    //Objek Musuh
    Musuh elf = new Musuh(2, 1, "Elf", 150, 50, 5, kotaKiev);
    Musuh zackBarks = new Musuh(2, 1, "Zack Barks(Vampir) Lv.1", 150, 50, 5, kotaKiev);
    Musuh iblisTanahElder = new Musuh(2, 2, "Iblis Tanah Elder", 350, 70, 10, quilKegelapan);
    Musuh zackBarks2 = new Musuh(2, 2, "Zack Barks(Vampir) Lv.2", 350, 70, 10, quilKegelapan);
    Musuh iblisApiEither = new Musuh(2, 3, "Iblis Api Either", 600, 100, 15, kotaBrussels);
    Musuh zackBarks3 = new Musuh(2, 3, "Zack Barks(Vampir) Lv.3", 600, 100, 15, kotaBrussels);
    Musuh iblisAirRaken = new Musuh(2, 4, "Iblis Air Raken", 900, 130, 20, pegununganEs);
    Musuh zackBarks4 = new Musuh(2, 4, "Zack Barks(Vampir) Lv.4", 900, 130, 20, pegununganEs);
    Musuh iblisAnginTantra = new Musuh(2, 5, "Iblis Angin Tantra", 1100, 150, 25, benuaUtara);
    Musuh zackBarks5 = new Musuh(2, 5, "Zack Barks(Vampir) Lv.5", 1100, 150, 25, benuaUtara);
    Musuh lordHellsprint = new Musuh(2, 6, "Lord Hellsprint", 1500, 200, 50, pulauTerapung);
    Musuh zackBarks6 = new Musuh(2, 6, "Zack Barks(Vampir) Lv.6", 1500, 200, 50, pulauTerapung);

    //Objek NPC
    NPC rajaNyctophillic = new NPC(3, "Raja Nyctophillic", 1, "Raja Nyctophillic memberikan misi penyelamatan pada Ksatria Langit untuk menemukan Sang Putri Ranivary yang telah diculik oleh Pangeran Kegelapan Lord Hellsprint di Quil keabadian melalui cincin itu");
    NPC grumpy = new NPC(3, "Grumpy", 2, "Grummy sang Kurcaci memberikannya mystic key kepada ksatria langit");
    NPC aron = new NPC(3, "Aron", 2, "Memberitahu ksatria cara membangunkan kurcaci yang telah ia kutuk");
    NPC rajaNyctophillic2 = new NPC(3, "Raja Nyctophillic", 3, "Raja menawarkan pertukaran sorcux dengan Star Ruby");
    NPC astor = new NPC(3, "Astor", 4, "Sang raja naga Astor Meminta Ksatria untuk membuktikan keberaniannya dengan mengambil kembali Rats Tail dari Kastil Wizard. dan Raja menawarkan apapun yang Ksatria minta dan memberikan peri-peri penolong untuk bersama dirinya");
    NPC putriRanivary = new NPC (3, "Burung Putri", 5, "Putri mengirimkan pesan lewat burung, bahwa untuk mengakses masuk menuju Pulau Terapung ia harus menggunakan mystic key");
    NPC putriRanivary2 = new NPC (3, "Putri Ranivary", 6, "Putri berterimakasih kepada ksatria");
    NPC rajaNyctophillic3 = new NPC(3, "Raja Nyctophillic", 6, "Raja memberikan informasi mengenai hadiah untuk menikahi Sang Putri\n");

    GameEngine() {
        //init Misi yang Aktif
        Misi1 objMisi1 = new Misi1();
        GameInfo.setMisiAktif(objMisi1);
        GameInfo.setMisiAktifIn(1);


        initArrayClassObjek();
        addMusuhWilayah();
        initObjekMisi();
        getArrAksiUtama().add("Inventaris");
        getArrAksiUtama().add("Kunjungi Pasar");
        getArrAksiUtama().add("Maps Wilayah");
        getArrAksiUtama().add("Jalankan Misi");
    }

    public ArrayList<String> getArrAksiUtama() {
        return arrAksiUtama;
    }

    Item getObjItem() {
        return objItem;
    }

    public void setObjItem(Item objItem) {
        this.objItem = objItem;
    }

    Karakter getObjKarakter() {
        return objKarakter;
    }

    public void setObjKarakter(Karakter objKarakter) {
        this.objKarakter = objKarakter;
    }

    Ksatria getObjKsatria() {
        return objKsatria;
    }

    public void setObjKsatria(Ksatria objKsatria) {
        this.objKsatria = objKsatria;
    }

    Wilayah getObjWilayah() {
        return objWilayah;
    }

    public void setObjWilayah(Wilayah objWilayah) {
        this.objWilayah = objWilayah;
    }

    boolean isStopPilihanKsatria() {
        return isStopPilihanKsatria;
    }

    public void setStopPilihanKsatria(boolean stopPilihanKsatria) {
        isStopPilihanKsatria = stopPilihanKsatria;
    }

    boolean isLoopAksiUtama() {
        return isLoopAksiUtama;
    }

    public void setLoopAksiUtama(boolean loopAksiUtama) {
        isLoopAksiUtama = loopAksiUtama;
    }

    public void initArrayClassObjek() {
        //set atksenjata
        pisau.setPower(pisau.hitungAtk()); panah.setPower(panah.hitungAtk()); tombak.setPower(tombak.hitungAtk()); trisula.setPower(trisula.hitungAtk()); pedang.setPower(pedang.hitungAtk());

        //set defense perisai
        perisaiKayu.setDefense(perisaiKayu.hitungDefense()); perisaiStainlessSteel.setDefense(perisaiStainlessSteel.hitungDefense()); perisaiTembaga.setDefense(perisaiTembaga.hitungDefense()); perisaiTrisulaMetal.setDefense(perisaiTrisulaMetal.hitungDefense()); perisaiTimbal.setDefense(perisaiTimbal.hitungDefense());

        //set atk ksatria
        goldwinMiller.setAtk(goldwinMiller.getHitungPower()[0]);goldwinMiller.setDefense(goldwinMiller.getHitungPower()[1]);
        marcelloParker.setAtk(marcelloParker.getHitungPower()[0]);marcelloParker.setDefense(marcelloParker.getHitungPower()[1]);
        braceHarison.setAtk(braceHarison.getHitungPower()[0]);braceHarison.setDefense(braceHarison.getHitungPower()[1]);
        marvinJensen.setAtk(marvinJensen.getHitungPower()[0]);marvinJensen.setDefense(marvinJensen.getHitungPower()[1]);

        //add array item
        ArrayList<Item> item= new ArrayList<>();
        item.add(pisau);item.add(panah);item.add(tombak);item.add(trisula);item.add(pedang);
        item.add(perisaiKayu);item.add(perisaiStainlessSteel);item.add(perisaiTembaga);item.add(perisaiTrisulaMetal);item.add(perisaiTimbal);
        item.add(legionnaire);item.add(hoplites);item.add(catapharact);item.add(derRitterBruder);item.add(knightTemplar);
        item.add(healingPotion);item.add(stimulant);item.add(manaPotion);item.add(elixir);item.add(ginseng); item.add(polish);
        item.add(amplopPetaCastle);item.add(bolaElemental1);item.add(bolaElemental2);item.add(bolaElemental3);item.add(bolaElemental4); item.add(bolaElemental5);
        item.add(cincinKeabadian);item.add(mysticKey);item.add(ramuan);item.add(kano);item.add(sorcrux); item.add(starRuby);
        item.add(tongkatSarda);item.add(levistone);item.add(ratsTail);item.add(cairanNitrogen);item.add(crystalEye); item.add(sorcererStone);
        objItem.setArrItem(item);

        //add array karakter
        ArrayList<Karakter> karakter= new ArrayList<>();
        //ksatria
        karakter.add(goldwinMiller);karakter.add(marcelloParker);karakter.add(braceHarison);karakter.add(marvinJensen);
        objKsatria.arrKsatria.add(goldwinMiller);objKsatria.arrKsatria.add(marcelloParker);objKsatria.arrKsatria.add(braceHarison);objKsatria.arrKsatria.add(marvinJensen);
        //musuh
        karakter.add(elf);karakter.add(zackBarks);karakter.add(iblisTanahElder);karakter.add(zackBarks2);karakter.add(zackBarks3);karakter.add(iblisAirRaken);
        karakter.add(iblisApiEither);karakter.add(zackBarks);karakter.add(iblisApiEither);karakter.add(zackBarks4);
        karakter.add(iblisAnginTantra);karakter.add(zackBarks5);karakter.add(lordHellsprint);karakter.add(zackBarks6);
        //npc
        karakter.add(rajaNyctophillic);karakter.add(grumpy);karakter.add(aron);karakter.add(rajaNyctophillic2);karakter.add(astor);
        karakter.add(putriRanivary);karakter.add(putriRanivary2);karakter.add(rajaNyctophillic3);
        objKarakter.setArrKarakter(karakter);

        //add array wilayah
        ArrayList<Wilayah> wilayah= new ArrayList<>();
        wilayah.add(kotaKiev);wilayah.add(goaPraha);wilayah.add(quilKegelapan);wilayah.add(kotaBrussels);wilayah.add(pegununganEs);wilayah.add(kastilWizard);wilayah.add(benuaUtara);wilayah.add(pulauTerapung);
        objWilayah.setArrayWilayah(wilayah);
    }

    public void initObjekMisi() {
        ArrayList<Karakter> arrMusuh = new ArrayList<>();
        ArrayList<Karakter> arrNPC = new ArrayList<>();
        for(Karakter kar:objKarakter.getArrKarakter()) {
            if (kar.getCode() == 2) {
                arrMusuh.add(kar);
            } else if (kar.getCode() == 3) {
                arrNPC.add(kar);
            }
        }
        GameInfo.getMisiAktif().setArrWilayah(objWilayah.getArrayWilayah());
        GameInfo.getMisiAktif().setArrMusuh(arrMusuh);
        GameInfo.getMisiAktif().setArrNPC(arrNPC);
        HashMap <Integer, ItemKhusus> hSItem = new HashMap<>();
        hSItem.put(1,bolaElemental1);
        hSItem.put(2,bolaElemental2);
        hSItem.put(3,bolaElemental3);
        hSItem.put(4,bolaElemental4);
        hSItem.put(5,bolaElemental5);
        GameInfo.getMisiAktif().setArrItem(hSItem);
    }

    public void addMusuhWilayah() {
        kotaKiev.getMusuh().add(elf);
        kotaKiev.getMusuh().add(zackBarks);
        quilKegelapan.getMusuh().add(iblisTanahElder);
        quilKegelapan.getMusuh().add(zackBarks2);
        kotaBrussels.getMusuh().add(iblisApiEither);
        kotaBrussels.getMusuh().add(zackBarks3);
        pegununganEs.getMusuh().add(iblisAirRaken);
        pegununganEs.getMusuh().add(zackBarks4);
        benuaUtara.getMusuh().add(iblisAnginTantra);
        benuaUtara.getMusuh().add(zackBarks5);
        pulauTerapung.getMusuh().add(lordHellsprint);
        pulauTerapung.getMusuh().add(zackBarks6);
    }

    public void prosesPilihKarakter(int pilihan) {
        if (pilihan > 0 && pilihan <= objKsatria.getArrKsatria().size()) {
            Ksatria ksatriaPlayer = objKsatria.getArrKsatria().get(pilihan-1);
            GameInfo.setKsatriaPlayer(ksatriaPlayer);
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println(".... : Pilihan yang tepat, Selamat Datang "+GameInfo.getKsatriaPlayer().getNama());
            GameInfo.getKsatriaPlayer().printData();
            GameInfo.getKsatriaPlayer().getArrItem().add(cincinKeabadian);

            System.out.println("-----------------------------------------------------------------------------------------------------------");
            isStopPilihanKsatria = true;
        } else if (pilihan == 0) {
            System.out.println(".... : Sampai Jumpa, pilihlah Ksatria dilain kesempatan ");
            isStopPilihanKsatria = true;
        } else {
            System.out.println("Pilihan yang anda masukan tidak tersedia");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }
    }

    public void pilihKarakter() {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println(" Pilih Karakter yang akan anda mainkan ");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        Aksi.printAksiPilihKarakter(objKarakter.getArrKarakter(1));
        int pilihan = Aksi.pilihAksi();
        prosesPilihKarakter(pilihan);
    }

    public void mulaiPilihKarakter() {
        while (!isStopPilihanKsatria) {
            pilihKarakter();
        }
    }

    public void mapsWilayah(){
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("    Wilayah yang akan anda lalui dalam permainan");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        objWilayah.printArray();
        System.out.println("-----------------------------------------------------------------------------------------------------------");

    }

    public void prosesAksiUtama(int pilihan) {
        switch (pilihan) {
            case 1:
                GameInfo.getKsatriaPlayer().setStop(false);
                GameInfo.getKsatriaPlayer().loopMenu();
                Aksi.pause();
                break;
            case 2:
                objToko.setAksiToko(false);
                objToko.loopAksiToko(objItem.getArrItem());
                Aksi.pause();
                break;
            case 3:
                mapsWilayah();
                Aksi.pause();
                break;
            case 4:
                GameInfo.getMisiAktif().setIsmenuMisi(false);
                GameInfo.getMisiAktif().menuMisi();
                break;
            case 0:
                isLoopAksiUtama=true;
                break;
            default:
                System.out.println("Pilihan yang anda masukan tidak tersedia");
        }
    }

    public void aksiUtama() {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        GameInfo.getKsatriaPlayer().printData();
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        Aksi.printAksi(arrAksiUtama);
        int pilihan = Aksi.pilihAksi();
        prosesAksiUtama(pilihan);
    }

    public void loopAksiUtama() {
        while (!isLoopAksiUtama) {
            aksiUtama();
        }
    }
}