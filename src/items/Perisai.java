package items;

import java.util.ArrayList;

public class Perisai extends Item{
    private int defense;
    private int maksDefense;

    @Override
    public int getPower() {
        return defense;
    }

    @Override
    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMaksDefense() {
        return maksDefense;
    }

    public void setMaksDefense(int maksDefense) {
        this.maksDefense = maksDefense;
    }

    public Perisai(int id, String nama, int level, int hargaJual, int hargaBeli, int hargaPenaikanLevel, int defense, int maksDefense) {
        super(id, nama, level, hargaJual, hargaBeli, hargaPenaikanLevel);
        this.defense = defense;
        this.maksDefense = maksDefense;
    }

    @Override
    public ArrayList<Item> getArrItem() {
        return arrItem;
    }

    @Override
    public void printData() {
        super.printData();
        System.out.println("Defense               : "+this.getPower());
        System.out.println("----------------------------------------------------");
    }

    @Override
    public int hitungDefense() {
        int defense = 0;
        switch (this.getLevel()) {
            case 1:
                defense = 20 * this.getMaksDefense() / 100;
                break;
            case 2:
                defense = 40 * this.getMaksDefense() / 100;
                break;
            case 3:
                defense = 60 * this.getMaksDefense() / 100;
                break;
            case 4:
                defense = 80 * this.getMaksDefense() / 100;
                break;
            case 5:
                defense = 100 * this.getMaksDefense() / 100;
                break;
        }

        return defense;
    }
}
