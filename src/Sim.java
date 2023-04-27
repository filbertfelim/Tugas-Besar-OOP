import java.util.Scanner;

public class Sim {

    // Atribut
    private String nama;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory<Item> inventory;
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private String status;
    private Point posisi; // di dalam rumah
    private Rumah rumah;
    private Rumah posisiRumah;
    private Ruangan posisiRuangan;
    private int timerBelumTidur;
    private int timerBelumBAB;
    private boolean perluBAB;
    private int jatahWaktuBerkunjung;
    private int timerWaktuKunjung;
    private boolean isBerkunjung;

    // Konstruktor
    public Sim(String nama, Point alamatRumah) {
        this.nama = nama;
        kekenyangan = 80;
        mood = 80;
        kesehatan = 80;
        uang = 100;
        pekerjaan = new Pekerjaan();
        inventory = new Inventory<Item>();
        rumah = World.getListofRumah().get(alamatRumah.getX() + alamatRumah.getY() * 64);
        posisiRumah = World.getListofRumah().get(alamatRumah.getX() + alamatRumah.getY() * 64);
        posisiRuangan = World.getListofRumah().get(alamatRumah.getX() + alamatRumah.getY() * 64).getListofRuangan().get(0);
        posisi = new Point(0,0);
        timerBelumTidur = 0;
        timerBelumBAB = 0;
        perluBAB = false;
        jatahWaktuBerkunjung = 0;
        timerWaktuKunjung = 0;
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
    
    public Inventory<Item> getInventory() {
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

    public void getInfo()
    {
        System.out.printf("+=========+\tPROFILE\t+=========\n");
        System.out.printf("Nama\t\t:"+getNama()+"\n");
        System.out.printf("Pekerjaan\t:"+getPekerjaan()+"\n");
        System.out.printf("Uang\t\t:"+getUang()+"\n\n");
        System.out.printf("+=========+\t STATS \t+=========\n");
        System.out.printf("Kekenyangan\t:"+getKekenyangan()+"\n");
        System.out.printf("Mood\t\t:"+getMood()+"\n");
        System.out.printf("Kesehatan\t\t:"+getKesehatan()+"\n\n");
    }

    public void addTimerBelumTidur(int duration)
    {
        timerBelumTidur += duration;
    }

    public void resetTimerBelumTidurAfterSleep()
    {
        timerBelumTidur = 0;
    }

    public void resetTimerBelumTidurAfterNoSleep()
    {
        if (timerBelumTidur >= 600)
        {
            System.out.println("Anda kurang tidur! kesehatan dan mood berkurang, segera tidur!");
            timerBelumTidur = 0;
            kesehatan = kesehatan - 5;
            mood = mood - 5;
        }
    }

    public void resetTimerBelumBAB()
    {
        if (timerBelumBAB > 240 && perluBAB)
        {
            System.out.println("Anda belum buang air setelah makan! kesehatan dan mood berkurang, segera buang air!");
            timerBelumBAB = 0;
            kesehatan = kesehatan - 5;
            mood = mood - 5;
        }
    }

    public void addTimerBelumBAB(int duration)
    {
        if (perluBAB)
        {
            timerBelumBAB += duration;
        }   
    }

    public void addTimerWaktuKunjung(int duration)
    {
        if (isBerkunjung)
        {
            timerWaktuKunjung += duration;
        }
    }

    public void balikdariBerkunjung(Scanner scan)
    {
        if (timerWaktuKunjung > jatahWaktuBerkunjung)
        {
            System.out.println("Waktu berkunjung sudah habis! Saatnya pulang.");
            jatahWaktuBerkunjung = 0;
            mood = mood + (10 * (timerWaktuKunjung / 30));
            kekenyangan = kekenyangan - (10 * (timerWaktuKunjung / 30));
            timerWaktuKunjung = 0;
            isBerkunjung = false;
            int waktubalik = rumah.getLokasi().distance(posisiRumah.getLokasi());
            posisiRumah = rumah;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(waktubalik * 1000);
                        System.out.println("Sudah sampai di rumah!");
                        World.addWaktu(waktubalik);
                        addTimerBelumTidur(waktubalik);
                        resetTimerBelumTidurAfterNoSleep();
                        addTimerBelumBAB(waktubalik);
                        resetTimerBelumBAB();
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
            System.out.println("Sedang balik ke rumah...");
            thread.start();
        }
    }

    // Aksi

    // buang air
    public void buangair(Scanner scan)
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    System.out.println("Buang air selesai!");
                    kekenyangan = kekenyangan - 20;
                    mood = mood + 10;
                    World.addWaktu(10);
                    addTimerBelumTidur(10);
                    resetTimerBelumTidurAfterNoSleep();
                    perluBAB = false;
                    timerBelumBAB = 0;
                    addTimerWaktuKunjung(10);
                    balikdariBerkunjung(scan);
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
        System.out.println("Sedang buang air...");
        thread.start();
    }

    // tidur
    public void tidur(Scanner scan)
    {
        boolean isValid = false;
        int duration = 1;
        while (!isValid)
        {
            try {
                System.out.print("Durasi ( detik kelipatan 240 ) : ");
                duration = scan.nextInt();
                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 240 != 0)
        {
            System.out.println("Input invalid ( harus kelipatan 240 ), silahkan diulangi!");
            isValid = false;
            while (!isValid)
            {
                try {
                    System.out.print("Durasi ( detik kelipatan 240 ) : ");
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
                    System.out.println("Tidur selesai!");
                    kesehatan = kesehatan + 30 * (finalduration / 240);
                    mood = mood + 20 * (finalduration / 240);
                    World.addWaktu(finalduration);
                    resetTimerBelumTidurAfterSleep();
                    addTimerBelumBAB(finalduration);
                    resetTimerBelumBAB();
                    addTimerWaktuKunjung(finalduration);
                    balikdariBerkunjung(scan);
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
        System.out.println("Sedang tidur...");
        thread.start();
    }

    //olahraga
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
                    addTimerBelumTidur(finalduration);
                    resetTimerBelumTidurAfterNoSleep();
                    addTimerBelumBAB(finalduration);
                    resetTimerBelumBAB();
                    addTimerWaktuKunjung(finalduration);
                    balikdariBerkunjung(scan);
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
                    addTimerBelumTidur(finalduration);
                    resetTimerBelumTidurAfterNoSleep();
                    addTimerBelumBAB(finalduration);
                    resetTimerBelumBAB();
                    addTimerWaktuKunjung(finalduration);
                    balikdariBerkunjung(scan);
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
                    addTimerBelumTidur(finalduration);
                    resetTimerBelumTidurAfterNoSleep();
                    addTimerBelumBAB(finalduration);
                    resetTimerBelumBAB();
                    addTimerWaktuKunjung(finalduration);
                    balikdariBerkunjung(scan);
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
                    addTimerBelumTidur(finalduration);
                    resetTimerBelumTidurAfterNoSleep();
                    addTimerBelumBAB(finalduration);
                    resetTimerBelumBAB();
                    addTimerWaktuKunjung(finalduration);
                    balikdariBerkunjung(scan);
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
                    addTimerBelumTidur(finalduration);
                    resetTimerBelumTidurAfterNoSleep();
                    addTimerBelumBAB(finalduration);
                    resetTimerBelumBAB();
                    addTimerWaktuKunjung(finalduration);
                    balikdariBerkunjung(scan);
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
                    addTimerBelumTidur(finalduration);
                    resetTimerBelumTidurAfterNoSleep();
                    addTimerBelumBAB(finalduration);
                    resetTimerBelumBAB();
                    addTimerWaktuKunjung(finalduration);
                    balikdariBerkunjung(scan);
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
                    addTimerBelumTidur(10);
                    resetTimerBelumTidurAfterNoSleep();
                    checkKondisiSim();
                    addTimerBelumBAB(10);
                    resetTimerBelumBAB();
                    addTimerWaktuKunjung(10);
                    balikdariBerkunjung(scan);
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
                    addTimerBelumTidur(30);
                    resetTimerBelumTidurAfterNoSleep();
                    checkisFull(scan);
                    addTimerBelumBAB(30);
                    resetTimerBelumBAB();
                    addTimerWaktuKunjung(30);
                    balikdariBerkunjung(scan);
                    perluBAB = true;
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang makan...");
        thread.start();
    }

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
                    addTimerBelumBAB(10);// diubah menjadi 1.5 kekenyangan makanan yg dimasak
                    addTimerBelumTidur(10);// diubah menjadi 1.5 kekenyangan makanan yg dimasak
                    resetTimerBelumBAB();
                    resetTimerBelumTidurAfterNoSleep();
                    addTimerWaktuKunjung(10);// diubah menjadi 1.5 kekenyangan makanan yg dimasak
                    balikdariBerkunjung(scan);
                    checkKondisiSim();
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang makan...");
        thread.start();
    }
    // berkunjung
    public void berkunjung(Scanner scan)
    {
        boolean isValid = false;
        int idx = 1;
        while (!isValid)
        {
            try {
                System.out.println("Daftar rumah yang ada di World : ");
                for (int i = 0 ; i < World.getListofRumah().size(); i++)
                {
                    System.out.println(String.valueOf(i + 1) + ". " + World.getListofRumah().get(i).getNama());
                }
                System.out.println("0. Batal");
                System.out.print("Pilihan : ");
                idx = scan.nextInt();
                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        if (idx == 0)
        {
            System.out.println("Tidak jadi berkunjung!");
        }
        else
        {
            while (idx < 0 || idx > World.getListofRumah().size() || World.getListofRumah().get(idx-1).getNama().equals(rumah.getNama()))
            {
                if (World.getListofRumah().get(idx-1).getNama().equals(rumah.getNama()))
                {
                    System.out.println("Tidak bisa berkunjung ke rumah sendiri!");
                }
                else
                {
                    System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
                }
                System.out.println("Daftar rumah yang ada di World : ");
                for (int i = 0 ; i < World.getListofRumah().size(); i++)
                {
                    System.out.println(String.valueOf(i + 1) + ". " + World.getListofRumah().get(i).getNama());
                }
                System.out.println("0. Batal");
                isValid = false;
                while (!isValid)
                {
                    try {
                        System.out.print("Pilihan : ");
                        idx = scan.nextInt();
                        isValid = true;
                    }
                    catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                if (idx == 0)
                {
                    System.out.println("Tidak jadi berkunjung!");
                }
            }
            int waktuberkunjung = rumah.getLokasi().distance(World.getListofRumah().get(idx-1).getLokasi());
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(waktuberkunjung * 1000);
                        System.out.println("Berkunjung selesai!");
                        World.addWaktu(waktuberkunjung);
                        addTimerBelumTidur(waktuberkunjung);
                        resetTimerBelumTidurAfterNoSleep();
                        addTimerBelumBAB(waktuberkunjung);
                        resetTimerBelumBAB();
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
            System.out.println("Sedang berkunjung...");
            thread.start();
            try {
                thread.join();
            }
            catch (InterruptedException err) {
                System.out.println(err.getMessage());
            }
            isBerkunjung = true;
            posisiRumah = World.getListofRumah().get(idx-1);
            isValid = false;
            int duration = 1;
            while (!isValid)
            {
                try {
                    System.out.print("Durasi berkunjung ( detik kelipatan 30 ) : ");
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
                        System.out.print("Durasi berkunjung ( detik kelipatan 30 ) : ");
                        duration = scan.nextInt();
                        isValid = true;
                    }
                    catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
            }
            jatahWaktuBerkunjung = duration;
        }
    }

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
                    tidur(scan);
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
                    buangair(scan);
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
                    masak(scan);
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
