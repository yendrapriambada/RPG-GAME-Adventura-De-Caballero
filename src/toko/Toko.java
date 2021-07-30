package toko;

import items.Item;
import engine.Aksi;
import java.util.ArrayList;

public class Toko {
    Item objItem = new Item();
    ArrayList<String> arrMenuToko = new ArrayList<>();
    private boolean isAksiToko = false;

    // CONSTRUCTOR
    public Toko() {
        arrMenuToko.add("Transaksi Senjata");
        arrMenuToko.add("Transaksi Perisai");
        arrMenuToko.add("Transaksi Kostum");
        arrMenuToko.add("Transaksi Obat");
    }

    // METHOD
    public void transaksiItem(Item item, int subPil){
        if (subPil == 1){
            item.beliItem();
        }
        else if (subPil == 2){
            item.jualItem();
        }
        else {
            System.out.println("Tidak Tersedia");
        }
    }

    public void prosesMenu(ArrayList<Item> arrItem, int kode){
        Aksi.printAksi(objItem.getArrTransaksi());
        int subPil = Aksi.pilihAksi();
        if (subPil == 0){
            isAksiToko = true;
        }
        else if (subPil > objItem.getArrTransaksi().size()){
            System.out.println("Pilihan yang anda masukan tidak tersedia");
        }
        else{
            ArrayList<Item> itemPilihan;
            itemPilihan = Aksi.printListItem(arrItem, kode, 1, subPil);
            int pilihanItem = Aksi.pilihAksi();
            if (pilihanItem != 0){
                Item pil = itemPilihan.get(pilihanItem-1);
                transaksiItem(pil, subPil);
            }
        }
    }

    public void menu(ArrayList<Item> arrItem) {
        System.out.println("----------------------------------------------------");
        System.out.println("    Selamat Datang di Toko Adventura de Caballero   ");
        System.out.println("----------------------------------------------------");

        Aksi.printAksi(this.arrMenuToko);       // pilih transaksi, kostum, senjata, perisai, dll
        int pilihan = Aksi.pilihAksi();
        if (pilihan == 0){
            isAksiToko = true;
        }
        else if (pilihan > this.arrMenuToko.size()){
            System.out.println("Pilihan yang anda masukan tidak tersedia");
        }
        else{
            prosesMenu(arrItem, pilihan);
        }
    }

    public void loopAksiToko(ArrayList<Item> arrItem) {
        while (!isAksiToko) {
            menu(arrItem);
        }
    }

    // SETTER AND GETTER
    public Item getObjItem() {
        return objItem;
    }

    public void setObjItem(Item objItem) {
        this.objItem = objItem;
    }

    public ArrayList<String> getArrMenuToko() {
        return arrMenuToko;
    }

    public void setArrMenuToko(ArrayList<String> arrMenuToko) {
        this.arrMenuToko = arrMenuToko;
    }

    public boolean isAksiToko() {
        return isAksiToko;
    }

    public void setAksiToko(boolean aksiToko) {
        isAksiToko = aksiToko;
    }
}
