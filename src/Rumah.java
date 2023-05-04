import java.util.ArrayList;

public class Rumah {

    private String nama;
    private Point lokasi; // lokasi di world
    private ArrayList<Ruangan> listofRuangan;
    private final int ukuran = 5;
    private int[][] matriksRumah = new int[ukuran][ukuran];

    public Rumah(String namaSim, Point lokasi) {
        this.nama = "Rumah " + namaSim;
        this.lokasi = lokasi;
        Point titikRuanganPertama = new Point(ukuran / 2, 0);
        listofRuangan = new ArrayList<Ruangan>(0);
        listofRuangan.add(new Ruangan("Ruangan pertama", 1, titikRuanganPertama));

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

    public int getUkuran() {
        return ukuran;
    }

    public int[][] getMatrixRumah() {
        return matriksRumah;
    }

    public void addRuangan(String namaRuang, Ruangan ruangTerhubung, int sisi) // 0 atas, 1 bawah, 2 kanan, 3 kiri
    {
        if (ruangTerhubung.getRuangTerhubung(sisi) == 0) {
            int ruangke = listofRuangan.size() + 1;
            Point titikRuang = null;
            switch (sisi) {
                case 0:
                    titikRuang = new Point(ruangTerhubung.getTitikRuang().getX(),
                            ruangTerhubung.getTitikRuang().getY() + 1);
                case 1:
                    titikRuang = new Point(ruangTerhubung.getTitikRuang().getX(),
                            ruangTerhubung.getTitikRuang().getY() - 1);
                case 2:
                    titikRuang = new Point(ruangTerhubung.getTitikRuang().getX() + 1,
                            ruangTerhubung.getTitikRuang().getY());
                case 3:
                    titikRuang = new Point(ruangTerhubung.getTitikRuang().getX() - 1,
                            ruangTerhubung.getTitikRuang().getY());
            }
            Ruangan ruangBaru = new Ruangan(namaRuang, ruangke, titikRuang);
            listofRuangan.add(ruangBaru);
            ruangTerhubung.setRuangTerhubung(sisi, ruangke);
            matriksRumah[titikRuang.getX()][titikRuang.getY()] = ruangke;

        } else {
            switch (sisi) {
                case 0:
                    System.out.println("Sisi atas ruangan ini sudah ada");
                case 1:
                    System.out.println("Sisi bawah ruangan ini sudah ada");
                case 2:
                    System.out.println("Sisi kanan ruangan ini sudah ada");
                case 3:
                    System.out.println("Sisi kiri ruangan ini sudah ada");
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
