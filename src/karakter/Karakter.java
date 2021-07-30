package karakter;

import items.Item;
import java.util.ArrayList;

public class Karakter {
    protected String nama;
    protected int hp;
    protected int atk;
    protected int defense;
    protected boolean isLife = true;
    protected int code;
    /*keterangan kode
    1. ksatria
    2. musuh
    3. npc
    */
    protected ArrayList<Karakter> arrKarakter = new ArrayList<>();
    protected ArrayList<Item> arrItem = new ArrayList<>();

    public Karakter(int code, String nama, int hp, int atk, int defense) {
        this.code = code;
        this.nama = nama;
        this.hp = hp;
        this.atk = atk;
        this.defense = defense;
    }

    public Karakter(int code, String nama) {
        this.code = code;
        this.nama = nama;
    }

    public Karakter(){}

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isLife() {
        return isLife;
    }

    public void setLife(boolean life) {
        isLife = life;
    }

    public int getCode() {
        return code;
    }

    public ArrayList<Karakter> getArrKarakter() {
        return arrKarakter;
    }

    public ArrayList<Karakter> getArrKarakter(int kode) {
        ArrayList<Karakter> karPil = new ArrayList<>();
        for(Karakter data: this.getArrKarakter()) {
            if (data.getCode() == kode) {
                karPil.add(data);
            }
        }
        return karPil;
    }

    public void setArrKarakter(ArrayList<Karakter> arrKarakter) {
        this.arrKarakter = arrKarakter;
    }

    public ArrayList<Item> getArrItem() {
        return arrItem;
    }

    public ArrayList<Item> getArrItem(int kode) {
        ArrayList<Item> itemPil = new ArrayList<>();
        for(Item data: this.getArrItem()) {
            if (data.getId() == kode) {
                itemPil.add(data);
            }
        }
        return itemPil;
    }

    public void printData() {
        System.out.println("Nama        : "+this.getNama());
        System.out.println("Hp          : "+this.getHp());
        System.out.println("Atk         : "+this.getAtk());
        System.out.println("Defense     : "+this.getDefense());
    }

    public void printArray() {
        for (Karakter data: getArrKarakter()) {
            data.printData();
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }
    }
}
