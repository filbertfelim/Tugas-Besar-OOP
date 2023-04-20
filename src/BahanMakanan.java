public class BahanMakanan extends Item implements Edible, Buyable {

    // Tambahan atribut
    private int kekenyangan;
    private int harga;

    // Konstruktor
    public BahanMakanan(String namaItem, int kekenyangan, int harga) {
        super(namaItem);
        this.kekenyangan = kekenyangan;
        this.harga = harga;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public void setKekenyangan(int kekenyangan) {
        this.kekenyangan = kekenyangan;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

}
