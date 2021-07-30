package misi;

import karakter.Karakter;
import engine.GameInfo;
import engine.Aksi;
import java.util.ArrayList;

public class Arena {
    private int jumlahPenghindaranMusuh;
    private int jumlahPenghindaranKsatria;
    private boolean isGameOver = false;
    private ArrayList<String> arrArahSerang = new ArrayList<>();
    private ArrayList<String> arrPilihanAksiSerangKsatria = new ArrayList<>();
    private ArrayList<String> arrModePenyerangan = new ArrayList<>();

    public Arena() {
        getArrArahSerang().add("Kanan");
        getArrArahSerang().add("Bawah");
        getArrArahSerang().add("Kiri");
        getArrArahSerang().add("Atas");

        getArrPilihanAksiSerangKsatria().add("Menyerang");
        getArrPilihanAksiSerangKsatria().add("Kabur");

        getArrModePenyerangan().add("Tangan Kosong");
        getArrModePenyerangan().add("Senjata");
    }

    public int getJumlahPenghindaranMusuh() {
        return jumlahPenghindaranMusuh;
    }

    public void setJumlahPenghindaranMusuh(int jumlahPenghindaranMusuh) {
        this.jumlahPenghindaranMusuh = jumlahPenghindaranMusuh;
    }

    public int getJumlahPenghindaranKsatria() {
        return jumlahPenghindaranKsatria;
    }

    public void setJumlahPenghindaranKsatria(int jumlahPenghindaranKsatria) {
        this.jumlahPenghindaranKsatria = jumlahPenghindaranKsatria;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public ArrayList<String> getArrArahSerang() {
        return arrArahSerang;
    }

    public void setArrArahSerang(ArrayList<String> arrArahSerang) {
        this.arrArahSerang = arrArahSerang;
    }

    public ArrayList<String> getArrPilihanAksiSerangKsatria() {
        return arrPilihanAksiSerangKsatria;
    }

    public void setArrPilihanAksiSerangKsatria(ArrayList<String> arrPilihanAksiSerangKsatria) {
        this.arrPilihanAksiSerangKsatria = arrPilihanAksiSerangKsatria;
    }

    public ArrayList<String> getArrModePenyerangan() {
        return arrModePenyerangan;
    }

    public void setArrModePenyerangan(ArrayList<String> arrModePenyerangan) {
        this.arrModePenyerangan = arrModePenyerangan;
    }

    public void perang(Karakter musuh) {
        int jumlahSerang = 0;
        setJumlahPenghindaranKsatria(3);
        setJumlahPenghindaranMusuh(3);
        System.out.println("-----------------------------------------------------");
        System.out.println(" Misi selanjutnya serang dan kalahkan "+musuh.getNama());
        System.out.println("-----------------------------------------------------");
        System.out.println(" Mulai Bertanding ");
        System.out.println("-----------------------------------------------------");

        while (true) {
            if (!isGameOver()) {
                if (jumlahSerang % 2 == 0) {
                    if (GameInfo.getKsatriaPlayer().getHp() > 0) {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("                Giliran Anda Menyerang");
                        System.out.println("-----------------------------------------------------");
                        menuSerangKsatria(musuh);
                        jumlahSerang++;
                        if (musuh.getHp() <= 0) {
                            break;
                        }
                    }
                } else {
                    if (musuh.getHp() > 0) {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("                Giliran Musuh Menyerang");
                        System.out.println("-----------------------------------------------------");
                        penyeranganMusuh(musuh);
                        jumlahSerang++;
                        if (GameInfo.getKsatriaPlayer().getHp() <= 0) {
                            break;
                        }
                    }
                }
            } else {
                GameInfo.getKsatriaPlayer().setHp(0);
                System.out.println("-----------------------------------------------------");
                System.out.println("Anda melarihan diri");
                System.out.println("-----------------------------------------------------");
                break;
            }
        }
    }

    public int menuPilihArah(){
        Aksi.printAksiNkembali(getArrArahSerang());
        return Aksi.pilihAksi();
    }

    boolean cekHasilPilihArah() {
        int arahMusuh = (int) (Math.random() * 3);
        int arahserang = menuPilihArah();
        boolean hasilSerangan;
        if (arahserang > 0 && arahserang <= getArrArahSerang().size()) {
            hasilSerangan = cekSerangan(arahMusuh, arahserang);
        } else {
            hasilSerangan = false;
            System.out.println("Arah tidak tersedia");
        }
        return hasilSerangan;
    }

    public void menuSerangKsatria(Karakter musuh) {
        boolean status = true;
        while (status) {
            Aksi.printAksiNkembali(getArrPilihanAksiSerangKsatria());
            int pilihan = Aksi.pilihAksi();
            switch (pilihan) {
                case 1:
                    while (true) {
                        Aksi.printAksiNkembali(getArrModePenyerangan());
                        int pilihan2 = Aksi.pilihAksi();
                        if (pilihan2 > 0 && pilihan2 <= 2) {
                            boolean hasilSerangan = cekHasilPilihArah();
                            if (getJumlahPenghindaranKsatria() > 0) {
                                if (pilihan2 == 1) {
                                    if (hasilSerangan) {
                                        GameInfo.getKsatriaPlayer().serangKurangiHpFree(musuh);
                                        System.out.println("Serangan berhasil");
                                    } else {
                                        System.out.println("Serangan gagal, musuh menghindar");
                                        setJumlahPenghindaranKsatria(getJumlahPenghindaranKsatria()-1);
                                    }
                                    cekKondisi(musuh);
                                    break;
                                } else if (pilihan2 == 2) {
                                    if (hasilSerangan) {
                                        GameInfo.getKsatriaPlayer().serangKurangiHpSenjata(musuh);
                                        System.out.println("Serangan berhasil!");
                                    } else {
                                        System.out.println("Serangan gagal, musuh menghindar!");
                                        setJumlahPenghindaranKsatria(getJumlahPenghindaranKsatria()-1);
                                    }
                                    cekKondisi(musuh);
                                    break;
                                }
                            } else {
                                if (pilihan2 == 1) {
                                    GameInfo.getKsatriaPlayer().serangKurangiHpFree(musuh);
                                } else if (pilihan2 == 2) {
                                    GameInfo.getKsatriaPlayer().serangKurangiHpSenjata(musuh);
                                }
                                System.out.println("Serangan berhasil");
                                cekKondisi(musuh);
                                break;
                            }
                        } else {
                            System.out.println("Masukan anda tidak tersedia");
                        }
                    }
                    status = false;
                    break;
                case 2:
                    status = false;
                    setGameOver(true);
                    break;
                default:
                    System.out.println("Masukan anda tidak tersedia");
                    break;
            }
        }
    }

    public void penyeranganMusuh(Karakter musuh){
        System.out.println("Pilih arah penghindaran");
        boolean hasilSerangan = cekHasilPilihArah();
        if (getJumlahPenghindaranMusuh() > 0) {
            if (hasilSerangan){
                serangKurangiHp(musuh);
                System.out.println("Anda terkena Serangan!");
            }
            else{
                System.out.println("Serangan gagal, anda berhasil menghindar!");
                setJumlahPenghindaranMusuh(getJumlahPenghindaranMusuh()-1);
            }
        } else {
            serangKurangiHp(musuh);
            System.out.println("Anda terkena Serangan!");
        }
        cekKondisi(musuh);
    }

    public boolean cekSerangan(int arahM, int arahS){
        boolean hasilSerangan;
        if (arahM==arahS){
            hasilSerangan = true;
        }
        else{
            hasilSerangan = false;
        }
        return hasilSerangan;
    }

    public void cekKondisi (Karakter musuh){
        System.out.println("----------------------------------------------");
        if (GameInfo.getKsatriaPlayer().getHp() < 0) {
            GameInfo.getKsatriaPlayer().setHp(0);
        }
        if (musuh.getHp() < 0) {
            musuh.setHp(0);
        }
        System.out.println("Sisa Hp Anda : "+GameInfo.getKsatriaPlayer().getHp());
        System.out.println("Sisa Hp Musuh : "+musuh.getHp());
        System.out.println("----------------------------------------------");
    }

    public void serangKurangiHp(Karakter musuh) {
        int musuhAtk = musuh.getAtk() - GameInfo.getKsatriaPlayer().getDefense();
        if (musuhAtk <= 0) {
            GameInfo.getKsatriaPlayer().setHp(GameInfo.getKsatriaPlayer().getHp());
            System.out.println("Defence ksatria lebih tinggi dari atk musuh\n, tidak ada pengurangan Hp");
        } else {
            GameInfo.getKsatriaPlayer().setHp(GameInfo.getKsatriaPlayer().getHp() - musuhAtk);
        }
    }

    public boolean isGameOver(int hpKsatriaAwal, Karakter musuh) {
        if(GameInfo.getKsatriaPlayer().getHp() <= 0) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Anda kalah, silahkan coba lagi dilain kesempatan");
            System.out.println("-----------------------------------------------------");
            GameInfo.getKsatriaPlayer().setHp(hpKsatriaAwal);
            GameInfo.getKsatriaPlayer().setKoin(GameInfo.getKsatriaPlayer().getKoin()+5);
            System.out.println("-----------------------------------------------------");
            System.out.println("Selamat anda mendapatkan 5 koin");
            System.out.println("-----------------------------------------------------");

            return false;
        } else {
            System.out.println("---------------------------------------------------------------");
            System.out.println("Selamat anda telah memenangkan pertarungan");
            System.out.println("---------------------------------------------------------------");
            musuh.setLife(false);
            GameInfo.getKsatriaPlayer().setHp(hpKsatriaAwal);
            GameInfo.getKsatriaPlayer().setKoin(GameInfo.getKsatriaPlayer().getKoin()+GameInfo.getMisiAktif().getKoinYangDidapat());
            System.out.println("-----------------------------------------------------");
            System.out.println("Selamat anda mendapatkan "+GameInfo.getMisiAktif().getKoinYangDidapat()+" koin");
            System.out.println("-----------------------------------------------------");

            return true;
        }
    }

    public boolean isGameOver (int hpKsatriaAwal) {
        if (GameInfo.getKsatriaPlayer().getHp() <= 0) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Anda kalah, silahkan coba lagi dilain kesempatan");
            System.out.println("-----------------------------------------------------");
            GameInfo.getKsatriaPlayer().setHp(hpKsatriaAwal);
            GameInfo.getKsatriaPlayer().setKoin(GameInfo.getKsatriaPlayer().getKoin() + 5);
            System.out.println("-----------------------------------------------------");
            System.out.println("Selamat anda mendapatkan 5 koin");
            System.out.println("-----------------------------------------------------");

            return false;
        } else {
            System.out.println("---------------------------------------------------------------");
            System.out.println("Selamat anda telah memenangkan pertarungan");
            System.out.println("---------------------------------------------------------------");
            GameInfo.getKsatriaPlayer().setHp(hpKsatriaAwal);
            GameInfo.getKsatriaPlayer().setKoin(GameInfo.getKsatriaPlayer().getKoin() + GameInfo.getMisiAktif().getKoinYangDidapat());
            System.out.println("-----------------------------------------------------");
            System.out.println("Selamat anda mendapatkan " + GameInfo.getMisiAktif().getKoinYangDidapat() + " koin");
            System.out.println("-----------------------------------------------------");

            return true;
        }
    }
}
