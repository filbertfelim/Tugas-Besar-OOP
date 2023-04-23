import java.util.ArrayList;

public class Rumah {
    private Point lokasi;
    private static ArrayList<Ruangan> listofRuangan;
    private static int[][] matrixRumah;
    private static Sim pemilik;

    public Rumah(Sim namaSim, Point lokasi)
    {
        matrixRumah = new int[64][64]; //inisialisasi ukuran rumah pada matrix, ntar pas upgrade rumah sekalian tambah ukuran
        matrixRumah[0][0] = 0;
        // aku bingung bikin lokasi ruangan itu pakai point atau matriks 
        this.lokasi = lokasi;
        listofRuangan = new ArrayList<Ruangan>();
        listofRuangan.add(new Ruangan(this, new Point(0,0)));
        pemilik = namaSim;
    }

    public Point getLokasiRumah()
    {
        return lokasi;
    }

    public Sim getPemilikRumah()
    {
        return pemilik;
    }

    public void setLokasiRumah(Point lokasi)
    {
        this.lokasi = lokasi;
    }

    public void upgradeRumah()
    {

    }


}
