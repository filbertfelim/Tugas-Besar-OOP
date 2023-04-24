import java.util.ArrayList;

public class Rumah {
    
    private String namaRumah;
    private Point lokasi;
    private int panjang;
    private int lebar;
    private ArrayList<Ruangan> listOfRuangan = new ArrayList<Ruangan>();
    private int[][] matriksRumah;

    // Konstruktor

    /**
     * @param namaRumah
     * @param lokasi
     * @param panjang
     * @param lebar
     */
    Rumah (String namaRumah, Point lokasi, int panjang, int lebar) {
        this.namaRumah = namaRumah;
        this.lokasi = lokasi;
        this.panjang = panjang;
        this.lebar = lebar;
        matriksRumah = new int[panjang][lebar];
    }

    // Getter dan Setter

    public String getNamaRumah() {
        return namaRumah;
    }
    public void setNamaRumah(String namaRumah) {
        this.namaRumah = namaRumah;
    }
    public Point getLokasi() {
        return lokasi;
    }
    public void setLokasi(Point lokasi) {
        this.lokasi = lokasi;
    }
    public int getPanjang() {
        return panjang;
    }
    public void setPanjang(int panjang) {
        this.panjang = panjang;
    }
    public int getLebar() {
        return lebar;
    }
    public void setLebar(int lebar) {
        this.lebar = lebar;
    }
    public ArrayList<Ruangan> getListOfRuangan() {
        return listOfRuangan;
    }
    public void setListOfRuangan(ArrayList<Ruangan> listOfRuangan) {
        this.listOfRuangan = listOfRuangan;
    }
    public void tambahRuangan()
    

    

}
