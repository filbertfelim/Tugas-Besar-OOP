public class Masakan extends Item implements Edible {

    private String[] bahan;
    private int kekenyangan;

    public Masakan(String namaItem, BahanMakanan[] bahan, int kekenyangan) {

        super(namaItem);
        this.bahan = bahan;
        this.kekenyangan = kekenyangan;

    }

    public Masakan(String namaItem) throws Exception {
        super(namaItem);
        if ((namaItem.toLowerCase()).equals("nasi ayam")) {
            kekenyangan = 5;
            bahan = ["nasi", "ayam"];
        } else if ((namaItem.toLowerCase()).equals("nasi kari")) {
            kekenyangan = 4;
            bahan = ["nasi", "kentang", "wortel", "sapi"];
        } else if ((namaItem.toLowerCase()).equals("susu kacang")) {
            kekenyangan = 8;
            bahan = ["susu", "kacang"];
        } else if ((namaItem.toLowerCase()).equals("tumis sayur")) {
            kekenyangan = 15;
            bahan = ["wortel", "bayam"];
        } else if ((namaItem.toLowerCase()).equals("bistik")) {
            kekenyangan = 2;
            bahan = ["kentang", "sapi"];
        } else {
            throw new Exception("Input tidak ada di daftar masakan!");
        }
    }

    public String[] getBahan() {
        return bahan;
    }

    public void setBahan(String[] bahan) {
        this.bahan = bahan;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public void setKekenyangan(int kekenyangan) {
        this.kekenyangan = kekenyangan;
    }

}
