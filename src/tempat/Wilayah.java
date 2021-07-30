package tempat;

import items.Item;
//import karakter.NPC;
import karakter.Musuh;
import java.util.ArrayList;

public class Wilayah {
    private String nama;
    private int misi;
    private Item item;
//    private NPC npc;
    private ArrayList<Musuh> musuh = new ArrayList<>();
    private ArrayList<Wilayah> arrayWilayah = new ArrayList<>();

    public Wilayah(String nama, int misi, Item item) {
        this.nama = nama;
        this.misi = misi;
        this.item = item;
    }

    public Wilayah() {}

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getMisi() {
        return misi;
    }

    public void setMisi(int misi) {
        this.misi = misi;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ArrayList<Musuh> getMusuh() {
        return musuh;
    }

    public void setMusuh(ArrayList<Musuh> musuh) {
        this.musuh = musuh;
    }

    public ArrayList<Wilayah> getArrayWilayah() {
        return arrayWilayah;
    }

    public void setArrayWilayah(ArrayList<Wilayah> arrayWilayah) {
        this.arrayWilayah = arrayWilayah;
    }

    public void printData() {
        System.out.println("Nama                  : "+this.getNama());
        System.out.println("Misi                  : "+this.getMisi());
        System.out.println("Item                  : "+this.getItem().getNama());
        System.out.println("----------------------------------------------------");
        if (this.getMusuh().isEmpty()) {
            System.out.println("Tidak ada musuh yang di lawan di "+this.getNama());
            System.out.println("----------------------------------------------------");
        } else {
            System.out.println("Musuh yang terdapat di "+this.getNama());
            System.out.println("----------------------------------------------------");
            int no = 1;
            for (Musuh musuh:this.getMusuh()) {
                System.out.println(" ---  : "+no+" :  --- ");
                musuh.printData();
                no++;
            }
        }
    }

    public void printArray() {
        for (Wilayah data: this.getArrayWilayah()) {
            data.printData();
        }
    }
}
