public class NonMakanan extends Item implements Buyable {

    private int harga;
    private int panjang;
    private int lebar;
    private Point titikAwal;
    private Point titikAkhir;

    public NonMakanan(String namaItem, int harga, int panjang, int lebar, Point titikAwal, Point titikAkhir) {
        super(namaItem);
        this.harga = harga;
        this.panjang = panjang;
        this.lebar = lebar;
        this.titikAwal = titikAwal;
        this.titikAkhir = titikAkhir;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getPanjang() {
        return panjang;
    }

    public void setPanjang(int panjang) {
        this.panjang = panjang;
    }

    public int getLebar() {
        return lebar;
    }

    public void setLebar(int lebar) {
        this.lebar = lebar;
    }

    public Point getTitikAwal() {
        return titikAwal;
    }

    public void setTitikAwal(Point titikAwal) {
        this.titikAwal = titikAwal;
    }

    public Point getTitikAkhir() {
        return titikAkhir;
    }

    public void setTitikAkhir(Point titikAkhir) {
        this.titikAkhir = titikAkhir;
    }
}
