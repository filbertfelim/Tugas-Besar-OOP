import java.util.ArrayList;

public class Rumah {
    private Point lokasi; // lokasi di world
    private ArrayList<Ruangan> listofRuangan;
    private int[][] matrixRumah;

    public Rumah(Point lokasi)
    {
        this.lokasi = lokasi;
        listofRuangan = new ArrayList<Ruangan>();
        listofRuangan.add(new Ruangan("Ruangan pertama",1));
        matrixRumah = new int[6][6]; // awal buat rumah
        for (int i = 0; i < matrixRumah.length ; i++)
        {
            for (int j = 0; i < matrixRumah[i].length;i++)
            {
                matrixRumah[i][j] = 1;
            }
        }
    }

    public Point getLokasi()
    {
        return lokasi;
    }

    public void setLokasi(Point lokasi)
    {
        this.lokasi = lokasi;
    }

    public void addRuangan(int sisi) // 1 kanan, 2 bawah, 3 kiri ,4 atas
    {
        
    }
}
