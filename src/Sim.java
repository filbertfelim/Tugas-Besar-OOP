
public class Sim {

    // Atribut
    private String nama;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory inventory;
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private String status;
    private Point posisi; // di dalam rumah
    private Rumah rumah;
    private Rumah posisiRumah;
    private Ruangan posisiRuangan;

    // Konstruktor
    public Sim(String nama, Point alamatRumah) {
        this.nama = nama;
        kekenyangan = 80;
        mood = 80;
        kesehatan = 80;
        uang = 100;
        pekerjaan = new Pekerjaan();
        inventory = new Inventory();
        rumah = World.getListofRumah().get(alamatRumah.getX() + alamatRumah.getY() * 64);
        posisiRumah = World.getListofRumah().get(alamatRumah.getX() + alamatRumah.getY() * 64);
        // posisiRuangan = ...
        // posisi = ....
    }

    // Method
    public String getNama() {
        return nama;
    }

    public Pekerjaan getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(Pekerjaan pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public int getUang() {
        return uang;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }
    
    public Inventory getInventory() {
        return inventory;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public void setKekenyangan(int kekenyangan) {
        this.kekenyangan = kekenyangan;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Point getPosisi() {
        return posisi;
    }

    public void setPosisi(Point posisi) {
        this.posisi = posisi;
    }

    public Rumah getRumah() {
        return rumah;
    }

    public Rumah getPosisiRumah() {
        return posisiRumah;
    }

    public void setPosisiRumah(Rumah posisiRumah) {
        this.posisiRumah = posisiRumah;
    }

    public Ruangan getPosisiRuangan() {
        return posisiRuangan;
    }

    public void setPosisiRuangan(Ruangan posisiRuangan) {
        this.posisiRuangan = posisiRuangan;
    }

    // Aksi
    
}
