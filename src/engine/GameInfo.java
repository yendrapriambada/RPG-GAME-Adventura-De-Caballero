package engine;

import karakter.Ksatria;
import misi.Misi;

public class GameInfo {
    public static Ksatria ksatriaPlayer;
    public static Misi misiAktif;
    public static Integer misiAktifIn;

    public static Ksatria getKsatriaPlayer() {
        return ksatriaPlayer;
    }

    public static void setKsatriaPlayer(Ksatria ksatriaPlayer) {
        GameInfo.ksatriaPlayer = ksatriaPlayer;
    }

    public static Misi getMisiAktif() {
        return misiAktif;
    }

    public static void setMisiAktif(Misi misiAktif) {
        GameInfo.misiAktif = misiAktif;
    }

    public static Integer getMisiAktifIn() {
        return misiAktifIn;
    }

    public static void setMisiAktifIn(Integer misiAktifIn) {
        GameInfo.misiAktifIn = misiAktifIn;
    }
}
