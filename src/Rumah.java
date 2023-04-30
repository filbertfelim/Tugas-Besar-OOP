import java.util.ArrayList;

public class Rumah {

    private String nama;
    private Point lokasi; // lokasi di world
    private ArrayList<Ruangan> listofRuangan;

    public Rumah(String namaSim, Point lokasi) {
        this.nama = "Rumah " + namaSim;
        this.lokasi = lokasi;
        listofRuangan = new ArrayList<Ruangan>(0);
        listofRuangan.add(new Ruangan("Ruangan pertama", 1));

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Point getLokasi() {
        return lokasi;
    }

    public void setLokasi(Point lokasi) {
        this.lokasi = lokasi;
    }

    public void addRuangan(String namaRuang, Ruangan ruangTerhubung, int sisi) // 0 atas, 1 bawah, 2 kanan, 3 kiri
    {
        if (ruangTerhubung.getRuangTerhubung(sisi) == 0) {
            int ruangke = listofRuangan.size() + 1;
            Ruangan ruangBaru = new Ruangan(namaRuang, ruangke);
            listofRuangan.add(ruangBaru);
            ruangTerhubung.setRuangTerhubung(sisi, ruangke);

        } else {
            switch (sisi) {
                case 0:
                    System.out.println("Sisi atas ruangan ini sudah ada");

            }
        }
    }

    public ArrayList<Ruangan> getListofRuangan() {
        return listofRuangan;
    }

    public void printListOfRuangan() {
        for (Ruangan ruangan : listofRuangan) {
            System.out.println(ruangan.getNamaRuangan());
        }
    }
}
