package items;

import java.util.ArrayList;

public class Kostum extends Item{

    private int defense;

    @Override
    public int getPower() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Kostum(int id, String nama, int hargaJual, int hargaBeli, int defense) {
        super(id, nama, hargaJual, hargaBeli);
        this.defense = defense;
    }

    @Override
    public ArrayList<Item> getArrItem() {
        return arrItem;
    }

    @Override
    public void printData() {
        System.out.println("Nama                  : "+this.getNama());
        System.out.println("Harga Jual            : "+this.getHargaJual());
        System.out.println("Harga Beli            : "+this.getHargaBeli());
        System.out.println("Defense               : "+this.getPower());
        System.out.println("----------------------------------------------------");
    }
}
