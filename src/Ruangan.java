import java.util.ArrayList;

public class Ruangan {
    private String namaRuangan;
    private int ruanganKe;
    private Point lokasi;
    private int panjang;
    private int lebar;
    private int[][] matriksRuangan;
    private NonMakanan[] listOfObjek;

    // Konstruktor
    public Ruangan(String namaRuangan, int ruanganKe, Point lokasi, int panjang, int lebar) {
        this.namaRuangan = namaRuangan;
        this.ruanganKe = ruanganKe;
        this.lokasi = lokasi;
        this.panjang = panjang;
        this.lebar = lebar;
        matriksRuangan = new int[panjang][lebar];
    }

    // Getter
    public String getNamaRuangan() {
        return namaRuangan;
    }
    
    public int getRuanganKe() {
        return ruanganKe;
    }

    public Point getLokasi() {
        return lokasi;
    }

    public int getPanjang() {
        return panjang; 
    }

    public int getLebar() {
        return lebar;
    }

    public int[][] getMatriksRuangan() {
        return matriksRuangan;
    }

    public NonMakanan[] getListOfObjek() {
        return listOfObjek;
    }

    public void memasangBarang(Item item, Point point) {
            private int panjang;
    private int lebar;
    private Point lokasi;
    private static ArrayList<Item> listofItems;

    public Ruangan(Rumah rumah, Point lokasi)
    {
        panjang = 6;
        lebar = 6;
        this.lokasi = lokasi;
        listofItems = new ArrayList<Item>();
    }

    public Point getLokasiRuangan()
    {
        return lokasi;
    }

    public void setLokasiRuangan(Point lokasi)
    {
        this.lokasi = lokasi;
    }

    public int getPanjang()
    {
        return panjang;
    }

    public int getLebar(Point lokasi)
    {
        return lebar;
    }

        public static ArrayList<Item> getListofItems()
    {
        return listofItems;
    }
}

    public void memindahBarang() {
        
    }
}
