package items;
import engine.GameInfo;
import java.util.ArrayList;

public class Item {
    /*
    * Kode :
    * 1 = Senjata
    * 2 = Perisai
    * 3 = Kostum
    * 4 = Obat
    * 5 = ItemKhusus
    */
    protected int id;
    protected String nama;
    protected int level;
    protected int hargaJual;
    protected int hargaBeli;
    protected int hargaPenaikanLevel;
    protected boolean isLoopTransaksi;
    protected boolean isStatusPemakaian = false;
    protected boolean isBolehDibeli = true;
    protected ArrayList<Item> arrItem = new ArrayList<>();
    protected ArrayList<String> arrAksi= new ArrayList<>();
    protected ArrayList<String> arrTransaksi = new ArrayList<>();

    // CONSTRUCTOR
    public Item(int id, String nama) {
        this.setId(id);
        this.setNama(nama);

        setArrAksi();
    }

    public Item(int id, String nama, int level, int hargaJual, int hargaBeli, int hargaPenaikanLevel) {
        this.setId(id);
        this.setNama(nama);
        this.setLevel(level);
        this.setHargaJual(hargaJual);
        this.setHargaBeli(hargaBeli);
        this.setHargaPenaikanLevel(hargaPenaikanLevel);
        setArrAksi();
        arrTransaksi.add("Beli Item");
        arrTransaksi.add("Jual Item");
    }

    public Item(int id, String nama,  int hargaJual, int hargaBeli) {
        this.setId(id);
        this.setNama(nama);
        this.setHargaJual(hargaJual);
        this.setHargaBeli(hargaBeli);

        setArrAksi();
        arrTransaksi.add("Beli Item");
        arrTransaksi.add("Jual Item");
    }

    public Item() {
        setArrAksi();
        arrTransaksi.add("Beli Item");
        arrTransaksi.add("Jual Item");
    }

    // SETTER AND GETTER
    public boolean isBolehDibeli() {
        return isBolehDibeli;
    }

    public void setBolehDibeli(boolean bolehDibeli) {
        isBolehDibeli = bolehDibeli;
    }

    public ArrayList<String> getArrAksi() {
        return this.arrAksi;
    }

    public void setArrAksi() {
        getArrAksi().add("Lepas");
        getArrAksi().add("Pakai");
        getArrAksi().add("Status Penggunaan");
        getArrAksi().add("Tambah Level");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(int hargaJual) {
        this.hargaJual = hargaJual;
    }

    public int getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(int hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public int getHargaPenaikanLevel() {
        return hargaPenaikanLevel;
    }

    public void setHargaPenaikanLevel(int hargaPenaikanLevel) {
        this.hargaPenaikanLevel = hargaPenaikanLevel;
    }

    public boolean isLoopTransaksi() {
        return isLoopTransaksi;
    }

    public void setLoopTransaksi(boolean loopTransaksi) {
        isLoopTransaksi = loopTransaksi;
    }

    public boolean isStatusPemakaian() {
        return isStatusPemakaian;
    }

    public void setStatusPemakaian(boolean statusPemakaian) {
        isStatusPemakaian = statusPemakaian;
    }

    public int getPower() {
        return 0;
    }

    public ArrayList<Item> getArrItem() {
        return arrItem;
    }

    public void setArrItem(ArrayList<Item> arrItem) {
        this.arrItem = arrItem;
    }

    public ArrayList<String> getArrTransaksi() {
        return arrTransaksi;
    }

    public void setArrTransaksi(ArrayList<String> arrTransaksi) {
        this.arrTransaksi = arrTransaksi;
    }


    // METHOD
    public void printData() {
        System.out.println("Nama                  : "+this.getNama());
        System.out.println("Level                 : "+this.getLevel());
        System.out.println("Harga Jual            : "+this.getHargaJual());
        System.out.println("Harga Beli            : "+this.getHargaBeli());
        System.out.println("Harga Penaikan Level  : "+this.getHargaPenaikanLevel());
    }

    public void itemAktif() {
        if (this.isStatusPemakaian) {
            this.printData();
            System.out.println("----------------------------------------------------------------------");
        } else {
            System.out.println(this.getNama()+" Tidak digunakan");
            System.out.println("----------------------------------------------------------------------");
        }
    }

    public Item cekItemAktif(int kode, ArrayList<Item> arr) {
        for (Item data: arr) {
            if (data.getId() == kode) {
                if (data.isStatusPemakaian()) {
                    return data;
                }
            }
        }
        return null;
    }

    public void prosesTambahLevel() {
        if (this.getId() == 1) {
            this.setPower(this.hitungAtk());
        } else if (this.getId() == 2) {
            this.setDefense(this.hitungDefense());
        }

        if (this.isStatusPemakaian) {
            //set power ksatria
            int[] power = GameInfo.getKsatriaPlayer().getHitungPower();
            GameInfo.getKsatriaPlayer().setAtk(power[0]);
            GameInfo.getKsatriaPlayer().setDefense(power[1]);
        }
    }

    public void tambahLevelBeli() {
        if (this.getLevel() < 5) {
            if (GameInfo.getKsatriaPlayer().getKoin() >= this.getHargaPenaikanLevel()) {
                this.setLevel(this.getLevel()+1);
                GameInfo.getKsatriaPlayer().setKoin(GameInfo.getKsatriaPlayer().getKoin() - this.getHargaPenaikanLevel());
                System.out.println(this.getNama()+" sudah naik 1 level menjadi level "+this.getLevel());
                prosesTambahLevel();
                System.out.println("----------------------------------------------------");
            }
        } else {
            System.out.println(this.getNama()+" sudah mencapai level maksimal");
            System.out.println("----------------------------------------------------");
        }
    }

    public void tambahLevel() {
        if (this.getLevel() < 5) {
            this.setLevel(this.getLevel()+1);
            System.out.println(this.getNama()+" sudah naik 1 level menjadi level "+this.getLevel());
            prosesTambahLevel();
            System.out.println("----------------------------------------------------");
        } else {
            System.out.println(this.getNama()+" sudah mencapai level maksimal");
            System.out.println("----------------------------------------------------");
        }
    }

    public void prosesAksi(int pilihan) {
        if (pilihan == 1) {
            this.lepas();
        } else if (pilihan == 2){
            this.pakai();
        } else if (pilihan == 3){
            this.itemAktif();
        } else if (pilihan == 4) {
            this.tambahLevelBeli();
        }
    }

    public void pakai() {
        Item objItem = cekItemAktif(this.getId(), GameInfo.getKsatriaPlayer().getArrItem());
        if (this.isStatusPemakaian()) {
            System.out.println(this.getNama()+" sudah anda pakai");
        } else if (objItem == null) {
            this.setStatusPemakaian(true);
            //set power ksatria
            int[] power = GameInfo.getKsatriaPlayer().getHitungPower();
            GameInfo.getKsatriaPlayer().setAtk(power[0]);
            GameInfo.getKsatriaPlayer().setDefense(power[1]);
            GameInfo.getKsatriaPlayer().printData();
            System.out.println(this.getNama()+" berhasil digunakan");
            System.out.println("----------------------------------------------------------------------");
        } else {
            System.out.println("Anda sedang memakai "+objItem.getNama()+", lepaskan "+objItem.getNama()+ " untuk menggunakan"+this.getNama());
        }
        GameInfo.getKsatriaPlayer().itemAktif();
    }

    public void lepas() {
        if (this.isStatusPemakaian()) {
            System.out.println("Item yang akan dilepas : ");
            this.printData();
            GameInfo.getKsatriaPlayer().setAtk(10);
            this.setStatusPemakaian(false);
            System.out.println(this.getNama()+" berhasil dilepas");
            System.out.println("----------------------------------------------------------------------");
        } else if (!this.isStatusPemakaian){
            System.out.println(this.getNama()+" tidak sedang anda pakai");
        }
        GameInfo.getKsatriaPlayer().itemAktif();
    }

    public void beliItem(){
        // cek koin user
        // item yang udah dibeli pindahin ke inventaris ke ksatria
        System.out.println("Koin Anda : " + GameInfo.getKsatriaPlayer().getKoin());
        System.out.println("Harga Beli Item : " + this.getHargaBeli());
        if (GameInfo.getKsatriaPlayer().getKoin() >= this.getHargaBeli()) {
            GameInfo.getKsatriaPlayer().getArrItem().add(this);
            GameInfo.getKsatriaPlayer().setKoin(GameInfo.getKsatriaPlayer().getKoin() - this.getHargaBeli());
            System.out.println("Anda berhasil membeli item " + this.getNama());
            System.out.println("Koin anda sekarang : " + GameInfo.getKsatriaPlayer().getKoin());
            this.setBolehDibeli(false);
        }
        else {
            System.out.println("Koin anda belum mencukupi untuk membeli " + this.getNama());
        }
    }

    public void jualItem(){
        // cek isBolehDibeli
        // item yang udah dibeli pindahin ke inventaris ke ksatria
        System.out.println("Koin Anda : " + GameInfo.getKsatriaPlayer().getKoin());
        System.out.println("Harga Jual Item : " + this.getHargaJual());
        if (!this.isBolehDibeli()) {
            GameInfo.getKsatriaPlayer().getArrItem().remove(this);
            GameInfo.getKsatriaPlayer().setKoin(GameInfo.getKsatriaPlayer().getKoin() + this.getHargaJual());
            System.out.println("Anda berhasil menjual item " + this.getNama());
            System.out.println("Anda mendapatkan harga jual sebesar : " + this.getHargaJual());
            System.out.println("Koin anda sekarang : " + GameInfo.getKsatriaPlayer().getKoin());
            this.setBolehDibeli(true);
        }
        else {
            System.out.println("Anda tidak memiliki" +this.getNama());
        }
    }

    public void setPower(int power) {}

    public int hitungAtk() {
        return 0;
    }

    public void setDefense(int defense) {}

    public int hitungDefense() {
        return 0;
    }

}
