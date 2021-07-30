package karakter;

import tempat.Wilayah;

public class Musuh extends Karakter{
    private int misi;
    private Wilayah wilayah;

    public int getMisi() {
        return misi;
    }

    public void setMisi(int misi) {
        this.misi = misi;
    }

    public Wilayah getWilayah() {
        return wilayah;
    }

    public void setWilayah(Wilayah wilayah) {
        this.wilayah = wilayah;
    }

    public Musuh(int code, int misi, String nama, int hp, int atk, int defense, Wilayah wilayah) {
        super(code, nama,hp,atk,defense);
        this.misi = misi;
        this.wilayah = wilayah;
    }

    @Override
    public void printData() {
        super.printData();
        System.out.println("Misi     : "+this.getMisi());
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }
}
