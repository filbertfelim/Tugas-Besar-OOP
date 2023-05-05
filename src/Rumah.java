import java.util.ArrayList;
import java.util.Map;

public class Rumah {

    private String nama;
    private Point lokasi; // lokasi di world
    private ArrayList<Ruangan> listofRuangan;
    private final int ukuran = 5;
    private Matriks matriksRumah = new Matriks;
    
    
    public Rumah(String namaSim, Point lokasi) {
        this.nama = "Rumah " + namaSim;
        this.lokasi = lokasi;
        Point titikRuanganPertama = new Point(ukuran / 2, 0);
        listofRuangan = new ArrayList<Ruangan>(0);
        listofRuangan.add(new Ruangan("Ruangan pertama", 1, titikRuanganPertama));
        // listofRuangan.get(0).memasangBarang(new NonMakanan("meja dan kursi"), 0, 0);
        // listofRuangan.get(0).memasangBarang(new NonMakanan("kasur single"), 5, 0);
        // listofRuangan.get(0).memasangBarang(new NonMakanan("kompor gas"), 0, 3);
        // listofRuangan.get(0).memasangBarang(new NonMakanan("toilet"), 5, 5);

    }

    // untuk fitur load
    public Rumah(String nama, Point lokasi, ArrayList<Ruangan> listofRuangan, int ukuran) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.listofRuangan = listofRuangan;
        this.matriksRumah = new int[ukuran][ukuran];
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
                    titikRuang.setX(ruangTerhubung.getTitikRuang().getX());
                    titikRuang.setY(ruangTerhubung.getTitikRuang().getY() + 1);
                    break;
                case 1:
                    titikRuang.setX(ruangTerhubung.getTitikRuang().getX());
                    titikRuang.setY(ruangTerhubung.getTitikRuang().getY() - 1);
                    break;
                case 2:
                    titikRuang.setX(ruangTerhubung.getTitikRuang().getX() + 1);
                    titikRuang.setY(ruangTerhubung.getTitikRuang().getY());     
                    break;
                case 3:
                    titikRuang.setX(ruangTerhubung.getTitikRuang().getX() - 1);
                    titikRuang.setY(ruangTerhubung.getTitikRuang().getY());
                    break;
            }
            Ruangan ruangBaru = new Ruangan(namaRuang, ruangke, titikRuang);
            listofRuangan.add(ruangBaru);
            ruangTerhubung.setRuangTerhubung(sisi, ruangke);
            matriksRumah[titikRuang.getX()][titikRuang.getY()] = ruangke;
            if (Sumbu.cekAda(x, y+1)) {
                ruangBaru.setRuangTerhubung(0, sumbuX.getNilai(x, y+1));
                listofRuangan.get(matriksRumah.getNilai(x, y+1)-1).setRuangTerhubung(1, ruangke);
            }
            if (matriksRumah.cekAda(x, y-1)) {
                ruangBaru.setRuangTerhubung(1, matriksRumah.getNilai(x, y-1)); // ruangBaru
                listofRuangan.get(matriksRumah.getNilai(x, y-1)-1).setRuangTerhubung(0, ruangke); // ruangTerhubung
                
            }
            if (matriksRumah.cekAda(x+1, y)) {
                ruangBaru.setRuangTerhubung(2, matriksRumah.getNilai(x+1, y));
                listofRuangan.get(matriksRumah.getNilai(x+1, y)-1).setRuangTerhubung(3, ruangke);
            }
            if (matriksRumah.cekAda(x-1, y)) {
                ruangBaru.setRuangTerhubung(3, matriksRumah.getNilai(x-1, y));
                listofRuangan.get(matriksRumah.getNilai(x-1, y)-1).setRuangTerhubung(2, ruangke);
            }
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
