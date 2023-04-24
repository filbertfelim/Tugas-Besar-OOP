import java.util.ArrayList;

public class Ruangan {
    private String namaRuangan;

    private final int panjang = 6;
    private final int lebar = 6;
    private int[][] matriksRuangan;
    private ArrayList<NonMakanan> listOfObjek;
    

    // Konstruktor
    public Ruangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    // Getter
    public String getNamaRuangan() {
        return namaRuangan;
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

    public ArrayList<NonMakanan> getListOfObjek() {
        return listOfObjek;
    }


    public void memasangBarang(Item item, Point point) {
        
    }

    public void memindahBarang() {
        
    }
}
