import java.util.ArrayList;

public class Ruangan {
    private String nama;
    private int ruanganke;
    private final int panjang = 6;
    private final int lebar = 6;
    private int[][] matrixRuangan;
    private ArrayList<NonMakanan> listofObjek;

    public Ruangan(String nama,int ruanganke)
    {
        this.nama = nama;
        this.ruanganke = ruanganke;
        matrixRuangan = new int[6][6];
        listofObjek = new ArrayList<NonMakanan>();
    }

    public void setRuanganKe(int ruanganke)
    {
        this.ruanganke = ruanganke;
    }

    public int getRuanganKe()
    {
        return ruanganke;
    }
}
