import java.util.ArrayList;

public class Ruangan {
    private String namaRuangan;
    private int ruanganKe;
    private final int panjang = 6; 
    private final int lebar = 6;
    private int[][] matriksRuangan = new int[panjang][lebar];
    private ArrayList<NonMakanan> listOfObjek;

    // Konstruktor
    public Ruangan(String namaRuangan, int ruanganKe) {
        this.namaRuangan = namaRuangan;
        this.ruanganKe = ruanganKe;
    }

    // Getter
    public String getNamaRuangan() {
        return namaRuangan;
    }

    public ArrayList<NonMakanan> getListOfObjek() {
        return listOfObjek;
    }

    public int getPanjang() {
        return panjang; 
    }

    public int getLebar() {
        return lebar;
    }

    public void memasangBarang(Item item, Point point) {

    }

    public void memindahBarang() {
        
    }
}
