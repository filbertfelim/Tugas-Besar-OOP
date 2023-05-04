import java.util.ArrayList;
import java.util.Arrays;

public class Ruangan {
    private String nama;
    private int ruanganKe;
    private final int panjang = 6;
    private final int lebar = 6;
    private int[][] matrixRuangan;
    private ArrayList<NonMakanan> listofObjek;
    private int[] ruangTerhubung = new int[4]; // [arah (0 atas, 1 bawah, 2 kanan, 3 kiri)]
    private Point titikRuang;

    public Ruangan(String nama, int ruanganke, Point titikRuang) {
        this.nama = nama;
        this.ruanganKe = ruanganke;
        this.titikRuang = titikRuang;
        matrixRuangan = new int[panjang][lebar];
        listofObjek = new ArrayList<NonMakanan>();
        ruangTerhubung[0] = 0;
        ruangTerhubung[1] = 0;
        ruangTerhubung[2] = 0;
        ruangTerhubung[3] = 0;
    }

    public void setNamaRuangan(String nama) {
        this.nama = nama;
    }

    public String getNamaRuangan() {
        return nama;
    }

    public void setRuanganKe(int ruanganke) {
        this.ruanganKe = ruanganke;
    }

    public int getRuanganKe() {
        return ruanganKe;
    }

    public Point getTitikRuang() {
        return titikRuang;
    }

    public void setTitikRuang(Point titikRuang) {
        this.titikRuang = titikRuang;
    }

    public int[][] getMatrixRuangan() {
        return matrixRuangan;
    }

    public void printListOfObjek() {
        System.out.println("Daftar objek yang ada di ruangan: ");
        if (listofObjek.size() == 0) {
            System.out.println("Ruangan Kosong");
        }
        for (int i = 0; i < listofObjek.size(); i++) {
            System.out.println(String.valueOf(i + 1) + ". " + listofObjek.get(i).getNamaItem() + " dengan kode "
                    + listofObjek.get(i).getKodeJenisBarang());
        }
    }

    public ArrayList<NonMakanan> getListofObjek() {
        return listofObjek;
    }

    public void printMatriksRuangan() {
        System.out.print(" ");
        for (int j = 0; j < 6; j++)
            System.out.print("  " + j);
        System.out.print("\n");
        int i = 0;
        // Loop through all rows
        for (int[] row : matrixRuangan) {

            // converting each row as string
            // and then printing in a separate line
            System.out.println(i + " " + Arrays.toString(row));
            i++;
        }
    }

    public boolean memasangBarang(NonMakanan barang, int x, int y) {
        int p = barang.getPanjang();
        int l = barang.getLebar();

        if ((x + p + 1 > panjang) || (y + l + 1 > lebar)) {
            System.out.println(barang.getNamaItem() + " Tidak bisa diletakkan disitu");
            return false;
        }

        boolean kosong = true;
        for (int i = y; i < y + p; i++) {
            for (int j = x; j < x + l; j++) {
                if (matrixRuangan[i][j] != 0) { // cek koordinat tersedia atau tidak
                    kosong = false;
                }
            }
        }

        if (kosong) {
            for (int i = y; i < y + p; i++) {
                for (int j = x; j < x + l; j++) {
                    matrixRuangan[i][j] = barang.getKodeJenisBarang();
                }
            }
            listofObjek.add(barang);
            barang.setTitikAwal(new Point(x, y));
        } else {
            System.out.println("Maaf titik tersebut penuh untuk disimpan objek " + barang.getNamaItem());
        }

        return kosong;
    }

    public boolean memindahBarang(NonMakanan barang, int x_Current, int y_Current, int x_Baru, int y_Baru) {
        // x dan y adalah posisi barang yang ada di ruangan

        int p = barang.getPanjang();
        int l = barang.getLebar();
        boolean ada = true;

        if (matrixRuangan[x_Current][y_Current] != barang.getKodeJenisBarang()) {
            ada = false;
        }

        if (ada) {
            // menghapus letak awal barang
            int idx = 0;
            for (NonMakanan item : listofObjek) {
                if (item.getNamaItem().equals(barang.getNamaItem())) {
                    listofObjek.remove(idx);
                    break;
                } else {
                    idx++;
                }
            }
            for (int i = barang.getTitikAwal().getY(); i < barang.getTitikAwal().getY() + p; i++) {
                for (int j = barang.getTitikAwal().getX(); j < barang.getTitikAwal().getX() + l; j++) {
                    matrixRuangan[i][j] = 0;
                }
            }

            // memasang lokasi baru barang
            memasangBarang(barang, x_Baru, y_Baru);
        }
        return ada;
    }

    public int[] getArrayRuangTerhubung() {
        return ruangTerhubung;
    }

    public int getRuangTerhubung(int sisi) {
        return ruangTerhubung[sisi];
    }

    public void setRuangTerhubung(int sisi, int ruanganKe) {
        ruangTerhubung[sisi] = ruanganKe;
    }

}
