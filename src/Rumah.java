import java.util.ArrayList;

public class Rumah {
    private String simPemilik;
    private Point lokasi;
    private final int panjang = 1;
    private final int lebar = 1;
    private ArrayList<Ruangan> listOfRuangan = new ArrayList<Ruangan>();
   
    // Konstruktor

    /**
     * @param namaRumah
     * @param lokasi
     */
    Rumah (String namaRumah, Point lokasi) {
        this.namaRumah = namaRumah;
        this.lokasi = lokasi;
        Ruangan ruang = new Ruangan("kamar");
        listOfRuangan.add(ruang);
    }

    // Getter dan Setter

    public String getSimPemilik() {
        return simPemilik;
    }
    public void setSimPemilik(String ) {
        this.simPemilik = simPemilik;
    }
    public Point getLokasi() {
        return lokasi;
    }
    public void setLokasi(Point lokasi) {
        this.lokasi = lokasi;
    }
    
    public ArrayList<Ruangan> getListOfRuangan() {
        return listOfRuangan;
    }
    
    public void setListOfRuangan(ArrayList<Ruangan> listOfRuangan) {
        this.listOfRuangan = listOfRuangan;
    }
    
    public void tambahRuangan(Ruangan ruang) {
        listOfRuangan.add(ruang);
    }
    
}
