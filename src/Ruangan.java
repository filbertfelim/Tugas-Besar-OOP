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
        
    }

    public void memindahBarang() {
        
    }
}
