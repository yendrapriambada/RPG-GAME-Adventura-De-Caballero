package items;

import java.util.ArrayList;

public class ItemKhusus extends Item{
    private String deskripsi;

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public ItemKhusus(int id, String nama, String deskripsi) {
        super(id, nama);
        this.setDeskripsi(deskripsi);
    }

    @Override
    public void setArrAksi() {}

    @Override
    public ArrayList<String> getArrAksi() {
        return this.arrAksi;
    }

    @Override
    public ArrayList<Item> getArrItem() {
        return arrItem;
    }

    @Override
    public void printData() {
        System.out.println("Nama                  : "+this.getNama());
        System.out.println("Deskripsi             : "+this.getDeskripsi());
        System.out.println("----------------------------------------------------");
    }

}
