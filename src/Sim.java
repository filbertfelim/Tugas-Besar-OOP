import java.util.Scanner;

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
        posisiRuangan = World.getListofRumah().get(alamatRumah.getX() + alamatRumah.getY() * 64).getListofRuangan().get(0);
        // posisi = .... posisi di rumah sendiri ( nentuin titik spawn default di mana)
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
    public void olahraga(Scanner scan)
    {
        System.out.print("Durasi ( detik ) : ");
        int duration = scan.nextInt();
        if (duration % 20 == 0)
        {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(duration * 1000);
                        kesehatan = kesehatan + (5 * (duration / 20));
                        kekenyangan = kekenyangan - (5 * (duration / 20));
                        mood = mood + (10 * (duration / 20));
                        World.addWaktu(duration);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            thread.start();
        }
    }

    //joget ( nambah mood nambah kesehatan turunin kekenyangan )
    //mati di tempat ( mood, kesehatan, kekenyangan jadi 0 )
    //nyanyi ( nambah mood )
    //stretching ( nambah kesehatan )
    //cuci wc ( nambah mood , turunin kekenyangan )
    //cuci piring ( nambah mood )
    //muntah ( nurunin kesehatan, nurunin kekenyangan )

    public void gotoObject(Scanner scan)
    {
        posisiRuangan.listobject();
        // nanti dilanjutin untuk terima input berupa int x dan pergi ke objek ke-x di list
        // udah sampai objek, dicek objek apa yg dihampirin, lalu ditanyain mau aksi apa sesuai dengan objeknya, kalo ga mau lakukan aksi, gak jalanin aksi, kalo mau, jalanin aksi
        System.out.println("0. Batal");
        System.out.print("Pilihan : ");
        int choiceobjek = scan.nextInt();
        if (choiceobjek > posisiRuangan.getListofObjek().size() || choiceobjek < 0)
        {
            System.out.println("Input tidak valid!");
        }
        else
        {
            if (choiceobjek == 0)
            {
                System.out.println("Berhasil dibatalkan!");
            }
            else
            {
                NonMakanan accessed = posisiRuangan.getListofObjek().get(choiceobjek - 1);
                // code posisi berubah
                if (accessed.getNamaItem().equals("kasur single") || accessed.getNamaItem().equals("kasur queen size") || accessed.getNamaItem().equals("kasur king size"))
                {
                    System.out.println("Aksi yang bisa dilakukan : ");
                    System.out.println("1. Tidur");
                    System.out.println("0. Batal");
                    System.out.print("Pilihan : ");
                    int choiceaksi = scan.nextInt();
                    while (choiceaksi > 1 || choiceaksi < 0)
                    {
                        System.out.println("Input tidak valid!");
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Tidur");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                    }
                    if (choiceaksi == 0)
                    {
                        System.out.println("Aksi tidak dilakukan!");
                    }
                    else
                    {
                        // method tidur;
                    }
                }
                else if (accessed.getNamaItem().equals("toilet"))
                {
                    System.out.println("Aksi yang bisa dilakukan : ");
                    System.out.println("1. Buang air");
                    System.out.println("2. Cuci WC");
                    System.out.println("0. Batal");
                    System.out.print("Pilihan : ");
                    int choiceaksi = scan.nextInt();
                    while (choiceaksi > 2 || choiceaksi < 0)
                    {
                        System.out.println("Input tidak valid!");
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Buang air");
                        System.out.println("2. Cuci WC");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                    }
                    if (choiceaksi == 0)
                    {
                        System.out.println("Aksi tidak dilakukan!");
                    }
                    else if (choiceaksi == 1)
                    {
                        // method buangair;
                    }
                    else if (choiceaksi == 2)
                    {
                        // method cuci wc;
                    }
                }
                else if (accessed.getNamaItem().equals("kompor gas") || accessed.getNamaItem().equals("kompor listrik"))
                {
                    System.out.println("Aksi yang bisa dilakukan : ");
                    System.out.println("1. Memasak");
                    System.out.println("2. Cuci piring");
                    System.out.println("0. Batal");
                    System.out.print("Pilihan : ");
                    int choiceaksi = scan.nextInt();
                    while (choiceaksi > 2 || choiceaksi < 0)
                    {
                        System.out.println("Input tidak valid!");
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Memasak");
                        System.out.println("2. Cuci piring");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                    }
                    if (choiceaksi == 0)
                    {
                        System.out.println("Aksi tidak dilakukan!");
                    }
                    else if (choiceaksi == 1)
                    {
                        // method masak;
                    }
                    else if (choiceaksi == 2)
                    {
                        // method cuci piring;
                    }
                }
                else if (accessed.getNamaItem().equals("meja dan kursi"))
                {
                    System.out.println("Aksi yang bisa dilakukan : ");
                    System.out.println("1. Makan");
                    System.out.println("0. Batal");
                    System.out.print("Pilihan : ");
                    int choiceaksi = scan.nextInt();
                    while (choiceaksi > 1 || choiceaksi < 0)
                    {
                        System.out.println("Input tidak valid!");
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Makan");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                    }
                    if (choiceaksi == 0)
                    {
                        System.out.println("Aksi tidak dilakukan!");
                    }
                    else if (choiceaksi == 1)
                    {
                        // method makan;
                    }
                }
                else if (accessed.getNamaItem().equals("jam"))
                {
                    System.out.println("Aksi yang bisa dilakukan : ");
                    System.out.println("1. Melihat waktu");
                    System.out.println("0. Batal");
                    System.out.print("Pilihan : ");
                    int choiceaksi = scan.nextInt();
                    while (choiceaksi > 1 || choiceaksi < 0)
                    {
                        System.out.println("Input tidak valid!");
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Melihat waktu");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                    }
                    if (choiceaksi == 0)
                    {
                        System.out.println("Aksi tidak dilakukan!");
                    }
                    else if (choiceaksi == 1)
                    {
                        // method melihat waktu;
                    }
                }
            } 
        }
    }



}
