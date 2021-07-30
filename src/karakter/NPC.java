package karakter;

public class NPC extends Karakter{
    private int misi;
    private String informasi;

    public int getMisi() {
        return misi;
    }

    public void setMisi(int misi) {
        this.misi = misi;
    }

    public String getInformasi() {
        return informasi;
    }

    public void setInformasi(String informasi) {
        this.informasi = informasi;
    }

    public NPC(int code, String nama, int misi, String informasi) {
        super(code, nama);
        this.misi = misi;
        this.informasi = informasi;
    }

    public NPC(){}

    @Override
    public void printData() {
        super.printData();
        System.out.println("Misi       : "+this.getMisi());
        System.out.println("Informasi  : "+this.getInformasi());
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }
}
