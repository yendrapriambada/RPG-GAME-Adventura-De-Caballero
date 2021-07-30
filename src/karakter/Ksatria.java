package karakter;

import items.Item;
import engine.Aksi;
import engine.GameInfo;
import java.util.ArrayList;

public class Ksatria extends Karakter{

    private int mp;
    private int exp;
    private int level;
    private int koin;
    private boolean isStop = false;
    private Item senjataYangdigunakan;
    private Item perisaiYangdigunakan;
    private final ArrayList<String> arrAksiUtama = new ArrayList<>(); //array menu
    public ArrayList<Ksatria> arrKsatria = new ArrayList<>();

    public Ksatria(int code, String nama, int hp, int atk, int defense, int mp, int exp, int level, int koin) {
        super(code, nama, hp, atk, defense);
        this.mp = mp;
        this.exp = exp;
        this.level = level;
        this.koin = koin;

        itemAktif();

        this.arrAksiUtama.add("Penyimpanan Senjata");
        this.arrAksiUtama.add("Penyimpanan Perisai");
        this.arrAksiUtama.add("Penyimpanan Kostum");
        this.arrAksiUtama.add("Penyimpanan Obat");
        this.arrAksiUtama.add("Penyimpanan Spesial Item");
    }

    public Ksatria() {
        itemAktif();

        this.arrAksiUtama.add("Penyimpanan Senjata");
        this.arrAksiUtama.add("Penyimpanan Perisai");
        this.arrAksiUtama.add("Penyimpanan Kostum");
        this.arrAksiUtama.add("Penyimpanan Obat");
        this.arrAksiUtama.add("Penyimpanan Spesial Item");
    }

    public Item getSenjataYangdigunakan() {
        return senjataYangdigunakan;
    }

    public void setSenjataYangdigunakan(Item senjataYangdigunakan) {
        this.senjataYangdigunakan = senjataYangdigunakan;
    }

    public Item getPerisaiYangdigunakan() {
        return perisaiYangdigunakan;
    }

    public void setPerisaiYangdigunakan(Item perisaiYangdigunakan) {
        this.perisaiYangdigunakan = perisaiYangdigunakan;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getKoin() {
        return koin;
    }

    public void setKoin(int koin) {
        this.koin = koin;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public ArrayList<String> getArrAksiUtama() {
        return arrAksiUtama;
    }

    public ArrayList<Ksatria> getArrKsatria() {
        return arrKsatria;
    }

    @Override
    public void printData() {
        super.printData();
        System.out.println("Mp          : "+this.getMp());
        System.out.println("Exp         : "+this.getExp());
        System.out.println("Level       : "+this.getLevel());
        System.out.println("Koin        : "+this.getKoin());
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }
    @Override
    public void printArray() {
        for (Ksatria data: getArrKsatria()) {
            data.printData();
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }
    }

    public int[] getHitungPower() {
        int[] power = new int[2];
        int atkThis = this.getAtk(), defenseThis = this.getDefense();

        if (this.getArrItem().isEmpty()) {
            atkThis = 10;
        } else {
            for (Item data: this.getArrItem()) {
                if (data.isStatusPemakaian() && data.getId() == 1) {
                    atkThis += data.getPower();
                }
                if (data.isStatusPemakaian() && data.getId() == 2) {
                    defenseThis += data.getPower();
                }
                if (data.isStatusPemakaian() && data.getId() == 3) {
                    defenseThis += data.getPower();
                }
            }
        }
        power[0] = atkThis;
        power[1] = defenseThis;
        return power;
    }

    public void prosesMenu(int kode) {
        int cc=0;
        if (getArrItem(kode).isEmpty()) {
            System.out.println("Kotak penyimpanan Item kosong");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        } else {
            for (Item aksi: this.getArrItem(kode)) {
                cc++;
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                System.out.println("            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~[ "+cc+" ]~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~             ");
                aksi.printData();
                Aksi.printAksi(aksi.getArrAksi());
            }
            System.out.println("Contoh Masukan terdiri dari kodeItem+Aksi (11, 12, 13)");
            int[] pilihan = Aksi.pilihAksiSub();
            if (pilihan[0] != -1) {
                GameInfo.getKsatriaPlayer().getArrItem(kode).get(pilihan[0]-1).prosesAksi(pilihan[1]);
            }
        }
    }

    public void menu() {
        Aksi.printAksi(this.getArrAksiUtama());
        int pilihan = Aksi.pilihAksi();

        if (pilihan == 0) {
            isStop = true;
        } else if (pilihan > this.getArrAksiUtama().size()) {
            System.out.println("Pilihan yang anda masukan tidak tersedia");
        } else {
            prosesMenu(pilihan);
        }
    }

    public void loopMenu() {
        while (!isStop) {
            menu();
        }
    }

    public void serangKurangiHpSenjata(Karakter musuh) {
        boolean status = false;
        //mengurangi kesehatan lawan berdasarkan senjata yang dimiliki
        if (getArrItem(1).isEmpty()) {
            System.out.println("Anda tidak memiliki senjata - Serangan dialihkan menggunakan tangan kosong");
            serangKurangiHpFree(musuh);
        } else {
            for (Item n: getArrItem(1)) {
                if (n.isStatusPemakaian()) {
                    int kurangi = n.getPower() - musuh.getDefense();
                    if (kurangi <= 0) {
                        musuh.setHp(musuh.getHp());
                        System.out.println("Defence musuh lebih tinggi dari atk ksatria\n, tidak ada pengurangan Hp");
                    } else {
                        musuh.setHp(musuh.getHp() - kurangi);
                    }
                    status = true;
                } else {
                    status = false;
                }
            }
            if (!status) {
                System.out.println("Tidak ada senjata yang sedang anda pakai \n" +
                        "- Serangan dialihkan menggunakan tangan kosong");
            }
        }
    }

    public void serangKurangiHpFree(Karakter musuh) {
        //smentara nanti ubah 40 lagi
        int kurangi = 40 - musuh.getDefense();
        if (kurangi <= 0) {
            musuh.setHp(musuh.getHp());
            System.out.println("Defence musuh lebih tinggi dari atk ksatria\n, tidak ada pengurangan Hp");
        } else {
            musuh.setHp(musuh.getHp() - kurangi);
        }
    }

    public void itemAktif() {
        if (!getArrItem().isEmpty()) {
            for (Item n: getArrItem(1)) {
                if (n.isStatusPemakaian()) {
                    GameInfo.getKsatriaPlayer().setSenjataYangdigunakan(n);
                }
            }

            for (Item n: getArrItem(2)) {
                if (n.isStatusPemakaian()) {
                    GameInfo.getKsatriaPlayer().setPerisaiYangdigunakan(n);
                }
            }
        }
    }
}
