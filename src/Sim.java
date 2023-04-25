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

    public void checkKondisiSim() // ditaruh didalam akhir semua aksi
    {
        if (kesehatan > 100)
        {
            kesehatan = 100;
        }
        if (mood > 100)
        {
            mood = 100;
        }
        if (kekenyangan > 100)
        {
            kekenyangan = 100;
        }
    }

    public void checkisFull(Scanner scan)
    {
        if (kekenyangan > 100)
        {
            kekenyangan = 100;
            System.out.println("Terlalu kenyang! Muntah dulu.");
            muntah(scan);
        }
    }

    public boolean isDead()
    {
        if (kesehatan <= 0 || kekenyangan <= 0 || mood <= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // Aksi
    public void olahraga(Scanner scan)
    {
        boolean isValid = false;
        int duration = 1;
        while (!isValid)
        {
            try {
                System.out.print("Durasi ( detik kelipatan 20 ) : ");
                duration = scan.nextInt();
                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 20 != 0)
        {
            System.out.println("Input invalid ( harus kelipatan 20 ), silahkan diulangi!");
            isValid = false;
            while (!isValid)
            {
                try {
                    System.out.print("Durasi ( detik kelipatan 20 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                }
                catch (Exception e) {
                    System.out.println("Input invalid, silahkan input angka!");
                    scan.nextLine();
                }
            }
        }
        int finalduration = duration;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(finalduration * 1000);
                    System.out.println("Olahraga selesai!");
                    kesehatan = kesehatan + (5 * (finalduration / 20));
                    kekenyangan = kekenyangan - (5 * (finalduration / 20));
                    mood = mood + (10 * (finalduration / 20));
                    World.addWaktu(finalduration);
                    checkKondisiSim();
                    if (isDead())
                    {
                        World.removeSim();
                        World.changeSim(scan);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang olahraga...");
        thread.start();
    }
    
    //joget ( nambah mood nambah kesehatan turunin kekenyangan )
    public void joget(Scanner scan)
    {
        boolean isValid = false;
        int duration = 1;
        while (!isValid)
        {
            try {
                System.out.print("Durasi ( detik kelipatan 10 ) : ");
                duration = scan.nextInt();
                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 10 != 0)
        {
            System.out.println("Input invalid ( harus kelipatan 10 ), silahkan diulangi!");
            isValid = false;
            while (!isValid)
            {
                try {
                    System.out.print("Durasi ( detik kelipatan 10 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                }
                catch (Exception e) {
                    System.out.println("Input invalid, silahkan input angka!");
                    scan.nextLine();
                }
            }
        }
        int finalduration = duration;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(finalduration * 1000);
                    System.out.println("Joget selesai!");
                    kesehatan = kesehatan + (5 * (finalduration / 10));
                    kekenyangan = kekenyangan - (5 * (finalduration / 10));
                    mood = mood + (5 * (finalduration / 10));
                    World.addWaktu(finalduration);
                    checkKondisiSim();
                    if (isDead())
                    {
                        World.removeSim();
                        World.changeSim(scan);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang joget...");
        thread.start();
    }
    //mati di tempat ( mood, kesehatan, kekenyangan jadi 0 )
    public void matiditempat(Scanner scan)
    {
        System.out.println(nama + " mati di tempat!");
        kekenyangan = 0;
        mood = 0;
        kesehatan = 0;
        World.removeSim();
        World.changeSim(scan);
    }

    //nyanyi ( nambah mood )
    public void nyanyi(Scanner scan)
    {
        boolean isValid = false;
        int duration = 1;
        while (!isValid)
        {
            try {
                System.out.print("Durasi ( detik kelipatan 10 ) : ");
                duration = scan.nextInt();
                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 10 != 0)
        {
            System.out.println("Input invalid ( harus kelipatan 10 ), silahkan diulangi!");
            isValid = false;
            while (!isValid)
            {
                try {
                    System.out.print("Durasi ( detik kelipatan 10 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                }
                catch (Exception e) {
                    System.out.println("Input invalid, silahkan input angka!");
                    scan.nextLine();
                }
            }
        }
        int finalduration = duration;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(finalduration * 1000);
                    System.out.println("Nyanyi selesai!");
                    mood = mood + (5 * (finalduration / 10));
                    World.addWaktu(finalduration);
                    checkKondisiSim();
                    if (isDead())
                    {
                        World.removeSim();
                        World.changeSim(scan);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang nyanyi...");
        thread.start();
    }
    //stretching ( nambah kesehatan )
    public void stretching(Scanner scan)
    {
        boolean isValid = false;
        int duration = 1;
        while (!isValid)
        {
            try {
                System.out.print("Durasi ( detik kelipatan 10 ) : ");
                duration = scan.nextInt();
                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 10 != 0)
        {
            System.out.println("Input invalid ( harus kelipatan 10 ), silahkan diulangi!");
            isValid = false;
            while (!isValid)
            {
                try {
                    System.out.print("Durasi ( detik kelipatan 10 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                }
                catch (Exception e) {
                    System.out.println("Input invalid, silahkan input angka!");
                    scan.nextLine();
                }
            }
        }
        int finalduration = duration;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(finalduration * 1000);
                    System.out.println("Stretching selesai!");
                    kesehatan = kesehatan + (5 * (finalduration / 10));
                    World.addWaktu(finalduration);
                    checkKondisiSim();
                    if (isDead())
                    {
                        World.removeSim();
                        World.changeSim(scan);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang stretching...");
        thread.start();
    }
    //cuci wc ( nambah mood , turunin kekenyangan )
    public void cuciwc(Scanner scan)
    {
        boolean isValid = false;
        int duration = 1;
        while (!isValid)
        {
            try {
                System.out.print("Durasi ( detik kelipatan 30 ) : ");
                duration = scan.nextInt();
                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 30 != 0)
        {
            System.out.println("Input invalid ( harus kelipatan 30 ), silahkan diulangi!");
            isValid = false;
            while (!isValid)
            {
                try {
                    System.out.print("Durasi ( detik kelipatan 30 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                }
                catch (Exception e) {
                    System.out.println("Input invalid, silahkan input angka!");
                    scan.nextLine();
                }
            }
        }
        int finalduration = duration;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(finalduration * 1000);
                    System.out.println("Cuci WC selesai!");
                    kekenyangan = kekenyangan - (10 * (finalduration / 30));
                    mood = mood + (10 * (finalduration / 30));
                    World.addWaktu(finalduration);
                    checkKondisiSim();
                    if (isDead())
                    {
                        World.removeSim();
                        World.changeSim(scan);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang cuci WC...");
        thread.start();
    }

    //cuci piring ( nambah mood )
    public void cucipiring(Scanner scan)
    {
        boolean isValid = false;
        int duration = 1;
        while (!isValid)
        {
            try {
                System.out.print("Durasi ( detik kelipatan 30 ) : ");
                duration = scan.nextInt();
                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 30 != 0)
        {
            System.out.println("Input invalid ( harus kelipatan 30 ), silahkan diulangi!");
            isValid = false;
            while (!isValid)
            {
                try {
                    System.out.print("Durasi ( detik kelipatan 30 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                }
                catch (Exception e) {
                    System.out.println("Input invalid, silahkan input angka!");
                    scan.nextLine();
                }
            }
        }
        int finalduration = duration;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(finalduration * 1000);
                    System.out.println("Cuci piring selesai!");
                    mood = mood + (10 * (finalduration / 30));
                    World.addWaktu(finalduration);
                    checkKondisiSim();
                    if (isDead())
                    {
                        World.removeSim();
                        World.changeSim(scan);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang cuci piring...");
        thread.start();
    }

    //muntah ( nurunin kesehatan, nurunin kekenyangan ) (kalo kekenyangan > 100, diset jadi 100 dan muntah)
    public void muntah(Scanner scan)
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    System.out.println("Muntah selesai!");
                    kekenyangan = kekenyangan - 20;
                    World.addWaktu(10);
                    checkKondisiSim();
                    if (isDead())
                    {
                        World.removeSim();
                        World.changeSim(scan);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang muntah...");
        thread.start();
    }

    // makan
    public void makan(Scanner scan)
    {
        // algoritma makan apa ( perlu inventory )
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30 * 1000);
                    System.out.println("Makan selesai!");
                    kekenyangan = kekenyangan - 20; // belum diubah, sesuai makanan ( perlu inventory )
                    World.addWaktu(30);
                    checkisFull(scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang makan...");
        thread.start();
    }

    // tidur

    // masak
    public void masak(Scanner scan)
    {
        // algoritma masak apa ( perlu inventory )
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);// diubah menjadi 1.5 kekenyangan makanan yg dimasak
                    System.out.println("Makan selesai!");
                    mood = mood + 10; //
                    World.addWaktu(10); // diubah menjadi 1.5 kekenyangan makanan yg dimasak
                    checkisFull(scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang makan...");
        thread.start();
    }
    // berkunjung
    // buang air
    // upgrade rumah
    // beli barang
    // berpindah ruangan
    // melihat inventory
    // memasang barang
    // melihat waktu

    public void gotoObject(Scanner scan)
    {
        // nanti dilanjutin untuk terima input berupa int x dan pergi ke objek ke-x di list
        // udah sampai objek, dicek objek apa yg dihampirin, lalu ditanyain mau aksi apa sesuai dengan objeknya, kalo ga mau lakukan aksi, gak jalanin aksi, kalo mau, jalanin aksi
        int choiceobjek = 1;
        boolean isValid = false;
        while (!isValid)
        {
            try {
                posisiRuangan.listobject();
                System.out.println("0. Batal");
                System.out.print("Pilihan : ");
                choiceobjek = scan.nextInt();
                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (choiceobjek > posisiRuangan.getListofObjek().size() || choiceobjek < 0)
        {
            System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
            isValid = false;
            while (!isValid)
            {
                try {
                    posisiRuangan.listobject();
                    System.out.println("0. Batal");
                    System.out.print("Pilihan : ");
                    choiceobjek = scan.nextInt();
                    isValid = true;
                }
                catch (Exception e) {
                    System.out.println("Input invalid, silahkan input angka!");
                    scan.nextLine();
                }
            }
        }
        if (choiceobjek == 0)
        {
            System.out.println("Berhasil dibatalkan!");
        }
        else
        {
            NonMakanan accessed = posisiRuangan.getListofObjek().get(choiceobjek - 1);
            posisi = accessed.getTitikAwal();
            if (accessed.getNamaItem().equals("kasur single") || accessed.getNamaItem().equals("kasur queen size") || accessed.getNamaItem().equals("kasur king size"))
            {
                
                int choiceaksi = 1;
                isValid = false;
                while (!isValid)
                {
                    try {
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Tidur");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    }
                    catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 1 || choiceaksi < 0)
                {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid)
                    {
                        try {
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Tidur");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        }
                        catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
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
                int choiceaksi = 1;
                isValid = false;
                while (!isValid)
                {
                    try {
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Buang air");
                        System.out.println("2. Cuci WC");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    }
                    catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 2 || choiceaksi < 0)
                {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid)
                    {
                        try {
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Buang air");
                            System.out.println("2. Cuci WC");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        }
                        catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
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
                    cuciwc(scan);
                }
            }
            else if (accessed.getNamaItem().equals("kompor gas") || accessed.getNamaItem().equals("kompor listrik"))
            {
                int choiceaksi = 1;
                isValid = false;
                while (!isValid)
                {
                    try {
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Memasak");
                        System.out.println("2. Cuci piring");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    }
                    catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 2 || choiceaksi < 0)
                {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid)
                    {
                        try {
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Memasak");
                            System.out.println("2. Cuci piring");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        }
                        catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
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
                    cucipiring(scan);
                }
            }
            else if (accessed.getNamaItem().equals("meja dan kursi"))
            {
                int choiceaksi = 1;
                isValid = false;
                while (!isValid)
                {
                    try {
                        System.out.println("Input tidak valid!");
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Makan");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    }
                    catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 1 || choiceaksi < 0)
                {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid)
                    {
                        try {
                            System.out.println("Input tidak valid!");
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Makan");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        }
                        catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
                }
                if (choiceaksi == 0)
                {
                    System.out.println("Aksi tidak dilakukan!");
                }
                else if (choiceaksi == 1)
                {
                    makan(scan);
                }
            }
            else if (accessed.getNamaItem().equals("jam"))
            {
                int choiceaksi = 1;
                isValid = false;
                while (!isValid)
                {
                    try {
                        System.out.println("Input tidak valid!");
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Melihat waktu");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    }
                    catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 1 || choiceaksi < 0)
                {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid)
                    {
                        try {
                            System.out.println("Input tidak valid!");
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Melihat waktu");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        }
                        catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
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
