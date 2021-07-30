package engine;

import items.Item;
import karakter.Karakter;
import karakter.NPC;
import tempat.Wilayah;
import java.util.ArrayList;
import java.util.Scanner;

public class Aksi {
    public static void printAksi(ArrayList<String> aksi) {
        int no=0;
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for (String dataAksi: aksi) {
            no++;
            System.out.println(no+". "+dataAksi);
        }
        System.out.println("0. Kembali");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public static void printAksiNkembali(ArrayList<String> aksi) {
        int no=0;
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for (String dataAksi: aksi) {
            no++;
            System.out.println(no+". "+dataAksi);
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public static ArrayList<Item> printListItem(ArrayList<Item> list, int kode, int no_urut, int subPil) {
        ArrayList<Item> itemPilihan = new ArrayList<>();
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        if (subPil == 1){
            for (Item data: list) {
                if (data.getId() == kode && data.isBolehDibeli()) {
                    System.out.println(no_urut + ". " + data.getNama());
                    no_urut++;
                    itemPilihan.add(data);
                }
            }
        }
        else {
            for (Item data: GameInfo.getKsatriaPlayer().getArrItem()) {
                if (data.getId() == kode && !data.isBolehDibeli() && !data.isStatusPemakaian()) {
                    System.out.println(no_urut + ". " + data.getNama());
                    no_urut++;
                    itemPilihan.add(data);
                }
            }
            if (itemPilihan.isEmpty()){
                System.out.println("Tidak ada item yang tersedia untuk dijual");
            }
        }
        System.out.println("0. Kembali");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        return itemPilihan;
    }

    public static void printAksiPilihKarakter(ArrayList<Karakter> aksi) {
        int no=0;
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for (Karakter dataAksi: aksi) {
            no++;
            System.out.println(no+". "+dataAksi.getNama());
        }
        System.out.println("0. Kembali");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public static void printAksiNPC(ArrayList<Karakter> aksi, int misi) {
        NPC npc;
        int no=0;
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for (Karakter data: aksi) {
            npc = (NPC) data;
            if (npc.getMisi() == misi) {
                no++;
                System.out.println(no+". "+npc.getNama());
            }
        }
        System.out.println("0. Kembali");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    public static int pilihAksi() {
        Scanner input = new Scanner(System.in);
        System.out.print("Pilih menu : ");
        return input.nextInt();
    }

    public static int[] pilihAksiSub(){
        Scanner input = new Scanner(System.in);
        int pil;
        int subPil;
        int[] ar = new int[2];
        System.out.print("Pilihan anda : ");
        String strPil = input.next();
        if (strPil.length() != 2) {
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println("Masukan tidak sesuai");
            System.out.println("Contoh Masukan terdiri dari kodeItem+Aksi (11, 12, 13)");
            pil = -1;
            subPil = -1;
        } else {
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            pil    =  Integer.parseInt(strPil.substring(0,1)); //ambil digit pertama, asumsikan jumlah tidak lebih dari 10
            subPil =  Integer.parseInt(strPil.substring(1,2)); //ambil digit kedua, asumsikan jumlah tidak lebih dari 10
        }

        ar[0] = pil;
        ar[1] = subPil;
        return ar;
    }

    public static Wilayah findWilayah(String cari, ArrayList<Wilayah> arr) {
        for (Wilayah data: arr) {
            if (data.getNama().equals(cari)) {
                return data;
            }
        }
        return null;
    }

    public static Karakter findKarakter(String cari, ArrayList<Karakter> arr) {
        for (Karakter data: arr) {
            if (data.getNama().equals(cari)) {
                return data;
            }
        }
        return null;
    }

    public static Item findItem(String cari, ArrayList<Item> arr) {
        for (Item data: arr) {
            if (data.getNama().equals(cari)) {
                return data;
            }
        }
        return null;
    }

    public static void pause() {
        System.out.print("\nPress Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
        System.out.println("\n");
    }
}
