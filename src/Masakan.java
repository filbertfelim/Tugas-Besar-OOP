public class Masakan extends Item implements Edible {

    private BahanMakanan[] bahan;
    private int kekenyangan;

    public Masakan(String namaItem, BahanMakanan[] bahan, int kekenyangan) {

        super(namaItem);
        this.bahan = bahan;
        this.kekenyangan = kekenyangan;

    }

    public BahanMakanan[] getBahan() {
        return bahan;
    }

    public void setBahan(BahanMakanan[] bahan) {
        this.bahan = bahan;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public void setKekenyangan(int kekenyangan) {
        this.kekenyangan = kekenyangan;
    }

}
