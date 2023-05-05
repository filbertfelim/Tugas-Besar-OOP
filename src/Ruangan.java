import java.util.*;

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

    public Ruangan(String nama, int ruanganKe, int[][] matrixRuangan, ArrayList<NonMakanan> listofObjek,
            int[] ruangTerhubung, Point titikRuang) {
        this.nama = nama;
        this.ruanganKe = ruanganKe;
        this.matrixRuangan = matrixRuangan;
        this.listofObjek = listofObjek;
        this.ruangTerhubung = ruangTerhubung;
        this.titikRuang = titikRuang;
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

        if ((x + p > panjang) || (y + l > lebar)) {
            System.out.println(barang.getNamaItem() + " Tidak bisa diletakkan disitu");
            return false;
        }

        boolean kosong = true;
        for (int i = y; i < y + l; i++) {
            for (int j = x; j < x + p; j++) {
                if (matrixRuangan[i][j] != 0) { // cek koordinat tersedia atau tidak
                    kosong = false;
                }
            }
        }

        if (kosong) {
            for (int i = y; i < y + l; i++) {
                for (int j = x; j < x + p; j++) {
                    matrixRuangan[i][j] = barang.getKodeJenisBarang();
                }
            }
            listofObjek.add(barang);
            barang.setTitikAwal(new Point(x, y));
            barang.setTitikAkhir(new Point((x + p - 1), (y + l - 1)));
        } else {
            kosong = false;
            System.out.println("Maaf titik tersebut penuh untuk disimpan objek " + barang.getNamaItem());
        }

        return kosong;
    }

    public boolean memindahBarang(NonMakanan barang, int x_Current, int y_Current, int x_Baru, int y_Baru) {
        // x dan y adalah posisi barang yang ada di ruangan
        boolean ada = true;

        if (matrixRuangan[y_Current][x_Current] != barang.getKodeJenisBarang()) {
            ada = false;
            System.out.println("Tidak ada barang tersebut di titik ini");
        }

        if (ada) {
            Point titikAwalBarang = new Point();
            int idx = 0;
            int indexObjek = 0;
            for (NonMakanan objek : listofObjek) {
                if (objek.getNamaItem().equals(barang.getNamaItem())) {
                    for (int i = objek.getTitikAwal().getY(); i <= objek.getTitikAkhir().getY(); i++) {
                        if (i == barang.getTitikAwal().getY()) {
                            titikAwalBarang.setY(objek.getTitikAwal().getY());
                            for (int j = objek.getTitikAwal().getX(); j <= objek.getTitikAkhir().getX(); j++) {
                                if (j == barang.getTitikAwal().getX()) {
                                    titikAwalBarang.setX(objek.getTitikAwal().getX());
                                    barang.setTitikAwal(objek.getTitikAwal());
                                    barang.setTitikAkhir(objek.getTitikAkhir());
                                    if (objek.getIsHorizontal()) {
                                        barang.setHorizontal();
                                    } else {
                                        barang.setVertikal();
                                    }
                                    for (int y = barang.getTitikAwal().getY(); y <= barang.getTitikAkhir()
                                            .getY(); y++) {
                                        for (int x = barang.getTitikAwal().getX(); x <= barang.getTitikAkhir()
                                                .getX(); x++) {
                                            matrixRuangan[y][x] = 0;
                                        }
                                    }
                                    indexObjek = idx;
                                }
                            }
                        }
                    }
                }
                idx++;
            }
            // menghapus letak awal barang
            listofObjek.remove(indexObjek);
            System.out.println("Ubah orientasi objek? (y/n)");
            Scanner scan = new Scanner(System.in);
            String orientasi = scan.nextLine().toLowerCase();
            boolean ubahOrientasi = false;
            if (orientasi.equals("y")) {
                ubahOrientasi = true;
                if (barang.getIsHorizontal()) {
                    barang.setVertikal();
                } else {
                    barang.setHorizontal();
                }
            }

            // memasang lokasi baru barang
            ada = memasangBarang(barang, x_Baru, y_Baru);
            if (!ada) {
                if (ubahOrientasi) {
                    if (barang.getIsHorizontal()) {
                        barang.setVertikal();
                    } else {
                        barang.setHorizontal();
                    }
                }
                int x = titikAwalBarang.getX();
                int y = titikAwalBarang.getY();
                for (int i = y; i < y + barang.getLebar(); i++) {
                    for (int j = x; j < x + barang.getPanjang(); j++) {
                        matrixRuangan[i][j] = barang.getKodeJenisBarang();
                    }
                }
                listofObjek.add(barang);
            }
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

    public void printRuangTerhubung() {
        System.out.println("Ruang Atas: " + ruangTerhubung[0]);
        System.out.println("Ruang Bawah: " + ruangTerhubung[1]);
        System.out.println("Ruang Kanan: " + ruangTerhubung[2]);
        System.out.println("Ruang Kiri: " + ruangTerhubung[3]);

    }

}
