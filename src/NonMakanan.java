public class NonMakanan extends Item implements Buyable {

    private int harga;
    private int panjang;
    private int lebar;
    private Point titikAwal;
    private Point titikAkhir;
    private int kodeJenisBarang;

    public NonMakanan(String namaItem, int harga, int panjang, int lebar, Point titikAwal, Point titikAkhir) {
        super(namaItem);
        this.harga = harga;
        this.panjang = panjang;
        this.lebar = lebar;
        this.titikAwal = titikAwal;
        this.titikAkhir = titikAkhir;
    }

    public NonMakanan(String namaItem) {
        super(namaItem);
        titikAwal = null;
        titikAkhir = null;
        if ((namaItem.toLowerCase()).equals("kasur single")) {
            panjang = 4;
            lebar = 1;
            harga = 50;
            kodeJenisBarang = 1;
        } else if ((namaItem.toLowerCase()).equals("kasur queen size")) {
            panjang = 4;
            lebar = 2;
            harga = 100;
            kodeJenisBarang = 2;
        } else if ((namaItem.toLowerCase()).equals("kasur king size")) {
            panjang = 5;
            lebar = 2;
            harga = 150;
            kodeJenisBarang = 3;
        } else if ((namaItem.toLowerCase()).equals("toilet")) {
            panjang = 1;
            lebar = 1;
            harga = 50;
            kodeJenisBarang = 4;
        } else if ((namaItem.toLowerCase()).equals("kompor gas")) {
            panjang = 2;
            lebar = 1;
            harga = 100;
            kodeJenisBarang = 5;
        } else if ((namaItem.toLowerCase()).equals("kompor listrik")) {
            panjang = 1;
            lebar = 1;
            harga = 200;
            kodeJenisBarang = 6;
        } else if ((namaItem.toLowerCase()).equals("meja dan kursi")) {
            panjang = 3;
            lebar = 3;
            harga = 50;
            kodeJenisBarang = 7;
        } else if ((namaItem.toLowerCase()).equals("jam")) {
            panjang = 1;
            lebar = 1;
            harga = 50;
            kodeJenisBarang = 8;
        } else {
            System.out.println("Input tidak ada di daftar item nonmakanan!");
        }
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

    public int getKodeJenisBarang() {
        return kodeJenisBarang;
    }

    public void setKodeJenisBarang(int kodeJenisBarang) {
        this.kodeJenisBarang = kodeJenisBarang;
    }
}
