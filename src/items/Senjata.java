package items;

import java.util.ArrayList;

public class Senjata extends Item{
    private int atk;
    private int maksAtk;

    @Override
    public int getPower() {
        return atk;
    }

    @Override
    public void setPower(int atk) {
        this.atk = atk;
    }

    public int getMaksAtk() {
        return maksAtk;
    }

    public void setMaksAtk(int maksAtk) {
        this.maksAtk = maksAtk;
    }

    public Senjata(int id, String nama, int level, int hargaJual, int hargaBeli, int hargaPenaikanLevel, int atk, int maksAtk) {
        super(id, nama, level, hargaJual, hargaBeli, hargaPenaikanLevel);
        this.atk = atk;
        this.maksAtk = maksAtk;
    }

    @Override
    public ArrayList<Item> getArrItem() {
        return arrItem;
    }

    @Override
    public void printData() {
        super.printData();
        System.out.println("Atk                   : "+this.getPower());
        System.out.println("----------------------------------------------------");
    }

    @Override
    public int hitungAtk() {
        int atk = 0;
        switch (this.getLevel()) {
            case 1:
                atk = 20 * this.getMaksAtk() / 100;
                break;
            case 2:
                atk = 40 * this.getMaksAtk() / 100;
                break;
            case 3:
                atk = 60 * this.getMaksAtk() / 100;
                break;
            case 4:
                atk = 80 * this.getMaksAtk() / 100;
                break;
            case 5:
                atk = 100 * this.getMaksAtk() / 100;
                break;
        }
        return atk;
    }
}
