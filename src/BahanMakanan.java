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

    public BahanMakanan(String namaItem) {
        super(namaItem);
        if ((namaItem.toLowerCase()).equals("nasi")) {
            harga = 5;
            kekenyangan = 5;
        } else if ((namaItem.toLowerCase()).equals("kentang")) {
            harga = 3;
            kekenyangan = 4;
        } else if ((namaItem.toLowerCase()).equals("ayam")) {
            harga = 10;
            kekenyangan = 8;
        } else if ((namaItem.toLowerCase()).equals("sapi")) {
            harga = 12;
            kekenyangan = 15;
        } else if ((namaItem.toLowerCase()).equals("wortel")) {
            harga = 3;
            kekenyangan = 2;
        } else if ((namaItem.toLowerCase()).equals("bayam")) {
            harga = 3;
            kekenyangan = 2;
        } else if ((namaItem.toLowerCase()).equals("kacang")) {
            harga = 2;
            kekenyangan = 2;
        } else if ((namaItem.toLowerCase()).equals("susu")) {
            harga = 2;
            kekenyangan = 1;
        } else {
            System.out.println("Input tidak ada di daftar item bahan makanan!");
        }
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
