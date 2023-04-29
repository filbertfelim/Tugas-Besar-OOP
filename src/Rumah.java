import java.util.ArrayList;

public class Rumah {

    private String nama;
    private Point lokasi; // lokasi di world
    private ArrayList<Ruangan> listofRuangan;


    public Rumah(String namaSim, Point lokasi)
    {
        this.nama = "Rumah " + namaSim;
        this.lokasi = lokasi;
        listofRuangan = new ArrayList<Ruangan>(0);
        listofRuangan.add(new Ruangan("Ruangan pertama",1));
        matrixRumah = new int[6][6]; // awal buat rumah
        
    }

    public String getNama()
    {
        return nama;
    }
    
    public void setNama(String nama)
    {
        this.nama = nama;
    }
    
    public Point getLokasi()
    {
        return lokasi;
    }

    public void setLokasi(Point lokasi)
    {
        this.lokasi = lokasi;
    }

    public boolean addRuangan(String namaRuang, Ruangan ruangTerhubung, int sisi) //  0 atas, 1 bawah, 2 kanan, 3 kiri
    {
        if (ruangTerhubung.getRuangTerhubung(sisi) == 0) {
            int ruangke = listofRuangan.size() + 1;
            ruangBaru = new Ruangan(namaRuang, ruangke);
            listofRuangan.add(ruangBaru);
            ruang.setRuangTerhubung(sisi, ruangke);
            return true;
        } else {
            return false;
        } 
    }

    public ArrayList<Ruangan> getListofRuangan()
    {
        return listofRuangan;
    }
}
