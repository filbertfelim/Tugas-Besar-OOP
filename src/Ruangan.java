import java.util.ArrayList;

public class Ruangan {
    private String nama;
    private int ruanganKe;
    private final int panjang = 6;
    private final int lebar = 6;
    private int[][] matrixRuangan;
    private ArrayList<NonMakanan> listofObjek;
    private int[] ruangTerhubung = int[4] // [arah (0 atas, 1 bawah, 2 kanan, 3 kiri)]

    public Ruangan(String nama,int ruanganke)
    {
        this.nama = nama;
        this.ruanganke = ruanganke;
        matrixRuangan = new int[panjang][lebar];
        listofObjek = new ArrayList<NonMakanan>();
    }

    public void setNamaRuangan(String nama)
    {
        this.nama = nama;
    }

    public String getNamaRuangan()
    {
        return nama;
    }

    public void setRuanganKe(int ruanganke)
    {
        this.ruanganke = ruanganke;
    }

    public int getRuanganKe()
    {
        return ruanganke;
    }

    public void listobject()
    {
        System.out.println("Daftar objek yang ada di ruangan: ");
        for (int i = 0 ; i < listofObjek.size() ; i++)
        {
            System.out.println(String.valueOf(i + 1) + ". " + listofObjek.get(i).getNamaItem());
        }
    }

    public ArrayList<NonMakanan> getListofObjek()
    {
        return listofObjek;
    }
    
    public void printMatriksRuangan()
    {
        System.out.print(" ");
        for (int j = 0; j < 6; j++)
            System.out.print("  " + j);
        System.out.print("\n");
        int i = 0;
        // Loop through all rows
        for (int[] row : matrixRuangan) {
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(i + " "  + Arrays.toString(row));
            i++;
        }
    }
    
    public boolean memasangBarang(NonMakanan barang, int x, int y) throws areaNotAvaibleException
    {
        p = barang.panjang;
        l = barang.lebar;
        for (int i = y; i < y + p; y++) {
            for (int j = x; j <= x + l; x++) {
                if (matriksRuangan[i][j] != 0) {
                    return false;
                }
            }
        }
        for (int y = y1; i <= y2; y--) {
            for (int x = x1; x <= x2; x++) {
                matriksRuangan[i][j] = barang.kode;
            }
        }
        listofObjek.add(barang);
        return true;
    }
    
    
    
    public int getRuangTerhubung(int arah) 
    {
        return ruangTerhubung[arah]; 
    }
    
    public void setRuangTerhubung(int arah, int ruanganKe)
    {
        ruangTerhubung[arah] = ruanganKe;
    }
    
}

