import java.util.ArrayList;

public class Ruangan {
    private String namaRuangan;
    private int ruanganKe;
    private final int panjang = 6;
    private final int lebar = 6;
    private int[][] matriksRuangan;
    private ArrayList<NonMakanan> listOfObjek;
    

    // Konstruktor
    public Ruangan(String namaRuangan, int ruanganKe) {
        this.namaRuangan = namaRuangan;
        this.ruanganKe = ruanganKe;
        matriksRuangan = [panjang][lebar];
        listOfObjek = new ArrayList<NonMakanan>();
    }

    // Getter
    public String getNamaRuangan() {
        return namaRuangan;
    }
    
    public int getRuanganKe() {
        return ruanganKe;   
    }
    
    public int setRuanganKe(int ruanganKe) {
        this.ruanganKe = ruanganKe;
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
    
    public void printListOfObjek() {
        
    }
    
    public void memasangBarang(Item item, Point point) {
        listOfObjek.add(item);    
    }

    public void memindahBarang() {
        
    }
}
