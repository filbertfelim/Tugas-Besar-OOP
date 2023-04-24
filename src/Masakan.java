import java.util.ArrayList;

public class Masakan extends Item implements Edible {

    private ArrayList<String> bahan;
    private int kekenyangan;

    public Masakan(String namaItem, ArrayList<String> bahan, int kekenyangan) {

        super(namaItem);
        this.bahan = bahan;
        this.kekenyangan = kekenyangan;

    }

    public Masakan(String namaItem) throws Exception {
        super(namaItem);
        if ((namaItem.toLowerCase()).equals("nasi ayam")) {
            kekenyangan = 5;
            bahan = new ArrayList<String>();
            bahan.add("nasi");
            bahan.add("ayam");
        } else if ((namaItem.toLowerCase()).equals("nasi kari")) {
            kekenyangan = 4;
            bahan = new ArrayList<String>();
            bahan.add("nasi");
            bahan.add("kentang");
            bahan.add("wortel");
            bahan.add("sapi");
        } else if ((namaItem.toLowerCase()).equals("susu kacang")) {
            kekenyangan = 8;
            bahan = new ArrayList<String>();
            bahan.add("susu");
            bahan.add("kacang");
        } else if ((namaItem.toLowerCase()).equals("tumis sayur")) {
            kekenyangan = 15;
            bahan = new ArrayList<String>();
            bahan.add("wortel");
            bahan.add("bayam");
        } else if ((namaItem.toLowerCase()).equals("bistik")) {
            kekenyangan = 2;
            bahan = new ArrayList<String>();
            bahan.add("kentang");
            bahan.add("sapi");
        } else {
            throw new Exception("Input tidak ada di daftar masakan!");
        }
    }

    public ArrayList<String> getBahan() {
        return bahan;
    }

    public void setBahan(ArrayList<String> bahan) {
        this.bahan = bahan;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public void setKekenyangan(int kekenyangan) {
        this.kekenyangan = kekenyangan;
    }

}
