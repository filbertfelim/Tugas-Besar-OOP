import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

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
    private ArrayList<Item> barangdibeli;
    private ArrayList<Integer> timerbarangdibeli;

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
        posisiRuangan = World.getListofRumah().get(alamatRumah.getX() + alamatRumah.getY() * 64).getListofRuangan()
                .get(0);
        posisi = new Point(0, 0);
        timerBelumTidur = 0;
        timerBelumBAB = 0;
        perluBAB = false;
        jatahWaktuBerkunjung = 0;
        timerWaktuKunjung = 0;
        barangdibeli = new ArrayList<Item>();
        timerbarangdibeli = new ArrayList<Integer>();
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

    public int getTimerBelumTidur() {
        return timerBelumTidur;
    }

    public int getTimerBelumBab() {
        return timerBelumBAB;
    }

    public boolean getPerluBab() {
        return perluBAB;
    }

    public int getJatahWaktuBerkunjung() {
        return jatahWaktuBerkunjung;
    }

    public int getTimerWaktuKunjung() {
        return timerWaktuKunjung;
    }

    public boolean getIsBerkunjung() {
        return isBerkunjung;
    }

    public ArrayList<Item> getBarangDiBeli() {
        return barangdibeli;
    }

    public ArrayList<Integer> getTimerBarangDibeli() {
        return timerbarangdibeli;
    }

    public void getCurrentLocation() {
        System.out.println("Sim ini berada di rumah: " + getPosisiRumah().getNama() + " yang berposisi di "
                + getPosisiRumah().getLokasi().toString());
        System.out.println("Pada ruangan: " + getPosisiRuangan().getNamaRuangan());
    }

    public void checkKondisiSim() // ditaruh didalam akhir semua aksi
    {
        if (kesehatan > 100) {
            kesehatan = 100;
        }
        if (mood > 100) {
            mood = 100;
        }
        if (kekenyangan > 100) {
            kekenyangan = 100;
        }
    }

    public void checkisFull(Scanner scan) {
        if (kekenyangan > 100) {
            kekenyangan = 100;
            System.out.println("Terlalu kenyang! Muntah dulu.");
            muntah(scan);
        }
    }

    public boolean isDead() {
        if (kesehatan <= 0 || kekenyangan <= 0 || mood <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void getInfo() {
        System.out.printf("+=========+\tPROFILE\t+=========\n");
        System.out.printf("Nama\t\t:" + getNama() + "\n");
        System.out.printf("Pekerjaan\t:" + getPekerjaan().getNama() + "\n");
        System.out.printf("Uang\t\t:" + getUang() + "\n\n");
        System.out.printf("+=========+\t STATS \t+=========\n");
        System.out.printf("Kekenyangan\t:" + getKekenyangan() + "\n");
        System.out.printf("Mood\t\t:" + getMood() + "\n");
        System.out.printf("Kesehatan\t:" + getKesehatan() + "\n\n");
    }

    public void printEdibleList() {
        System.out.println("Makanan dan masakan yang tersedia :");
        System.out.printf("%-3s %-15s %15s %10s\n", "No", "Makanan", "Kekenyangan", "Jumlah");
        int idx = 1;
        for (Item item : inventory.getInventory()) {
            if (item instanceof BahanMakanan) {
                System.out.printf("%-3s %-15s %15s %10s\n", idx, item.getNamaItem(),
                        ((BahanMakanan) item).getKekenyangan(), inventory.getDetails().get(item.getNamaItem()));
                idx++;
            } else if (item instanceof Masakan) {
                System.out.printf("%-3s %-15s %15s %10s\n", idx, item.getNamaItem(), ((Masakan) item).getKekenyangan(),
                        inventory.getDetails().get(item.getNamaItem()));
                idx++;
            }
        }
    }

    public int countEdible() {
        int count = 0;
        for (Item item : inventory.getInventory()) {
            if (item instanceof BahanMakanan || item instanceof Masakan) {
                count++;
            }
        }
        return count;
    }

    public void printBuyableList() {
        System.out.printf("%-2s %-20s %-10s %10s\n", "No", "Barang", "Harga", "Dimensi");
        System.out.printf("%-2s %-20s %-10s %10s\n", 1, "Kasur Single", 50, "4 x 1");
        System.out.printf("%-2s %-20s %-10s %10s\n", 2, "Kasur Queen Size", 100, "4 x 2");
        System.out.printf("%-2s %-20s %-10s %10s\n", 3, "Kasur King Size", 150, "5 x 2");
        System.out.printf("%-2s %-20s %-10s %10s\n", 4, "Toilet", 50, "1 x 1");
        System.out.printf("%-2s %-20s %-10s %10s\n", 5, "Kompor Gas", 100, "2 x 1");
        System.out.printf("%-2s %-20s %-10s %10s\n", 6, "Kompor Listrik", 200, "1 x 1");
        System.out.printf("%-2s %-20s %-10s %10s\n", 7, "Meja dan Kursi", 50, "3 x 3");
        System.out.printf("%-2s %-20s %-10s %10s\n", 8, "Jam", 10, "1 x 1");
        System.out.printf("%-2s %-20s %-10s %10s\n", 9, "Play Station", 200, "2 x 1");
        System.out.printf("%-2s %-20s %-10s %10s\n", 10, "Lemari Buku", 100, "1 x 1");
        System.out.printf("%-2s %-20s %-10s %10s\n", 11, "Radio", 100, "1 x 1");
        System.out.printf("%-2s %-20s %-10s %10s\n", 12, "Piano", 200, "2 x 1");
        System.out.printf("\n%-2s %-20s %-10s %10s\n", "No", "Bahan Makanan", "Harga", "Kekenyangan");
        System.out.printf("%-2s %-20s %-10s %10s\n", 13, "Nasi", 5, 5);
        System.out.printf("%-2s %-20s %-10s %10s\n", 14, "Kentang", 3, 4);
        System.out.printf("%-2s %-20s %-10s %10s\n", 15, "Ayam", 10, 8);
        System.out.printf("%-2s %-20s %-10s %10s\n", 16, "Sapi", 12, 15);
        System.out.printf("%-2s %-20s %-10s %10s\n", 17, "Wortel", 3, 2);
        System.out.printf("%-2s %-20s %-10s %10s\n", 18, "Bayam", 3, 2);
        System.out.printf("%-2s %-20s %-10s %10s\n", 19, "Kacang", 2, 2);
        System.out.printf("%-2s %-20s %-10s %10s\n", 20, "Susu", 2, 1);
    }

    public Item getBarang(int idx) {
        if (idx == 1) {
            return new NonMakanan("kasur single");
        } else if (idx == 2) {
            return new NonMakanan("kasur queen size");
        } else if (idx == 3) {
            return new NonMakanan("kasur king size");
        } else if (idx == 4) {
            return new NonMakanan("toilet");
        } else if (idx == 5) {
            return new NonMakanan("kompor gas");
        } else if (idx == 6) {
            return new NonMakanan("kompor listrik");
        } else if (idx == 7) {
            return new NonMakanan("meja dan kursi");
        } else if (idx == 8) {
            return new NonMakanan("jam");
        } else if (idx == 9) {
            return new NonMakanan("play station");
        } else if (idx == 10) {
            return new NonMakanan("lemari buku");
        } else if (idx == 11) {
            return new NonMakanan("radio");
        } else if (idx == 12) {
            return new NonMakanan("piano");
        } else if (idx == 13) {
            return new BahanMakanan("susu");
        } else if (idx == 14) {
            return new BahanMakanan("kentang");
        } else if (idx == 15) {
            return new BahanMakanan("ayam");
        } else if (idx == 16) {
            return new BahanMakanan("sapi");
        } else if (idx == 17) {
            return new BahanMakanan("wortel");
        } else if (idx == 18) {
            return new BahanMakanan("bayam");
        } else if (idx == 19) {
            return new BahanMakanan("kacang");
        } else {
            return new BahanMakanan("susu");
        }
    }

    public int getHargaBarang(int idx) {
        if (idx == 1 || idx == 4 || idx == 7) {
            return 50;
        } else if (idx == 2 || idx == 5 || idx == 10 || idx == 11) {
            return 100;
        } else if (idx == 3) {
            return 150;
        } else if (idx == 6 || idx == 9 || idx == 12) {
            return 200;
        } else if (idx == 8 || idx == 11) {
            return 10;
        } else if (idx == 13) {
            return 5;
        } else if (idx == 14 || idx == 17 || idx == 18) {
            return 3;
        } else if (idx == 16) {
            return 12;
        } else if (idx == 19 || idx == 20) {
            return 2;
        } else {
            return 0;
        }
    }

    public void addTimerBeliBarang(int duration) {
        for (int time : timerbarangdibeli) {
            time = time - duration;
        }
    }

    public void checkkirimBarang() {
        for (int i = 0; i < timerbarangdibeli.size(); i++) {
            if (timerbarangdibeli.get(i) <= 0) {
                System.out.println(barangdibeli.get(i).getNamaItem() + " sudah sampai untuk " + nama + ", "
                        + barangdibeli.get(i).getNamaItem() + " dimasukkan ke inventory!");
                timerbarangdibeli.remove(i);
                barangdibeli.remove(i);
                inventory.addItem(barangdibeli.get(i));
            }
        }
    }

    public void addTimerBelumTidur(int duration) {
        timerBelumTidur += duration;
    }

    public void resetTimerBelumTidurAfterSleep() {
        timerBelumTidur = 0;
    }

    public void resetTimerBelumTidurAfterNoSleep() {
        if (timerBelumTidur >= 600) {
            System.out.println(getNama() + " kurang tidur! kesehatan dan mood berkurang, segera tidur!");
            timerBelumTidur = 0;
            kesehatan = kesehatan - 5;
            mood = mood - 5;
        }
    }

    public void resetTimerBelumBAB() {
        if (timerBelumBAB > 240 && perluBAB) {
            System.out.println(
                    getNama() + " belum buang air setelah makan! kesehatan dan mood berkurang, segera buang air!");
            timerBelumBAB = 0;
            kesehatan = kesehatan - 5;
            mood = mood - 5;
        }
    }

    public void addTimerBelumBAB(int duration) {
        if (perluBAB) {
            timerBelumBAB += duration;
        }
    }

    public void addTimerWaktuKunjung(int duration) {
        if (isBerkunjung) {
            timerWaktuKunjung += duration;
        }
    }

    public void balikdariBerkunjung(Scanner scan) {
        if (timerWaktuKunjung > jatahWaktuBerkunjung) {
            System.out.println("Waktu berkunjung " + getNama() + " sudah habis! Saatnya pulang.");
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
                        World.checkAllSimTimer(waktubalik, scan);
                        checkKondisiSim();
                        if (isDead()) {
                            World.removeActiveSim();
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
    public void buangair(Scanner scan) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    System.out.println("Buang air selesai!");
                    kekenyangan = kekenyangan - 20;
                    mood = mood + 10;
                    World.addWaktu(10);
                    perluBAB = false;
                    timerBelumBAB = 0;
                    World.checkAllSimTimer(10, scan);
                    checkKondisiSim();
                    if (isDead()) {
                        World.removeActiveSim();
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
    public void tidur(Scanner scan) {
        boolean isValid = false;
        int duration = 1;
        while (!isValid) {
            try {
                System.out.print("Durasi ( detik kelipatan 240 ) : ");
                duration = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 240 != 0) {
            System.out.println("Input invalid ( harus kelipatan 240 ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    System.out.print("Durasi ( detik kelipatan 240 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
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
                    World.checkAllSimTimer(finalduration, scan);
                    checkKondisiSim();
                    if (isDead()) {
                        World.removeActiveSim();
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

    // kerja
    public void doKerja(Scanner scan) {
        if (World.gethariKe() > pekerjaan.getChangeWorkAtHari()) {
            boolean isValid = false;
            int duration = 1;
            while (!isValid) {
                try {
                    System.out.print("Durasi ( detik kelipatan 120 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
                    System.out.println("Input invalid, silahkan input angka!");
                    scan.nextLine();
                }
            }
            while (duration % 120 != 0) {
                System.out.println("Input invalid ( harus kelipatan 120 ), silahkan diulangi!");
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.print("Durasi ( detik kelipatan 120 ) : ");
                        duration = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
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
                        pekerjaan.addLamaBekerja(finalduration);
                        setKekenyangan(getKekenyangan() - (10 * finalduration / 30));
                        setMood(getMood() - (10 * finalduration / 30));
                        setUang(getUang() + (pekerjaan.getGaji() * finalduration / 240));
                        World.addWaktu(finalduration);
                        World.checkAllSimTimer(finalduration, scan);
                        checkKondisiSim();
                        if (isDead()) {
                            World.removeActiveSim();
                            World.changeSim(scan);
                        }
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            thread.start();
        } else {
            System.out.println("Belum bisa bekerja dengan pekerjaan baru!");
        }
    }

    // mandi
    public void mandi(Scanner scan) {
        boolean isValid = false;
        int duration = 1;
        while (!isValid) {
            try {
                System.out.print("Durasi ( detik kelipatan 10 ) : ");
                duration = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 10 != 0) {
            System.out.println("Input invalid ( harus kelipatan 10 ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    System.out.print("Durasi ( detik kelipatan 10 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
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
                    System.out.println("Mandi selesai!");
                    kesehatan = kesehatan + (5 * (finalduration / 10));
                    kekenyangan = kekenyangan - (5 * (finalduration / 10));
                    mood = mood + (5 * (finalduration / 10));
                    World.addWaktu(finalduration);
                    World.checkAllSimTimer(finalduration, scan);
                    checkKondisiSim();
                    if (isDead()) {
                        World.removeActiveSim();
                        World.changeSim(scan);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang mandi...");
        thread.start();
    }

    // main game
    public void maingame(Scanner scan) {
        boolean isValid = false;
        int duration = 1;
        while (!isValid) {
            try {
                System.out.print("Durasi ( detik kelipatan 10 ) : ");
                duration = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 10 != 0) {
            System.out.println("Input invalid ( harus kelipatan 10 ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    System.out.print("Durasi ( detik kelipatan 10 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
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
                    System.out.println("Main game selesai!");
                    kekenyangan = kekenyangan - (5 * (finalduration / 10));
                    mood = mood + (5 * (finalduration / 10));
                    World.addWaktu(finalduration);
                    World.checkAllSimTimer(finalduration, scan);
                    checkKondisiSim();
                    if (isDead()) {
                        World.removeActiveSim();
                        World.changeSim(scan);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang main game...");
        thread.start();
    }

    // membaca
    public void bacabuku(Scanner scan) {
        boolean isValid = false;
        int duration = 1;
        while (!isValid) {
            try {
                System.out.print("Durasi ( detik kelipatan 10 ) : ");
                duration = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 10 != 0) {
            System.out.println("Input invalid ( harus kelipatan 10 ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    System.out.print("Durasi ( detik kelipatan 10 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
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
                    System.out.println("Baca buku selesai!");
                    kekenyangan = kekenyangan - (5 * (finalduration / 10));
                    mood = mood + (5 * (finalduration / 10));
                    World.addWaktu(finalduration);
                    World.checkAllSimTimer(finalduration, scan);
                    checkKondisiSim();
                    if (isDead()) {
                        World.removeActiveSim();
                        World.changeSim(scan);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang baca buku...");
        thread.start();
    }

    // main piano
    public void mainpiano(Scanner scan) {
        boolean isValid = false;
        int duration = 1;
        while (!isValid) {
            try {
                System.out.print("Durasi ( detik kelipatan 10 ) : ");
                duration = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 10 != 0) {
            System.out.println("Input invalid ( harus kelipatan 10 ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    System.out.print("Durasi ( detik kelipatan 10 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
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
                    System.out.println("Bermain piano selesai!");
                    kekenyangan = kekenyangan - (5 * (finalduration / 10));
                    mood = mood + (5 * (finalduration / 10));
                    World.addWaktu(finalduration);
                    World.checkAllSimTimer(finalduration, scan);
                    checkKondisiSim();
                    if (isDead()) {
                        World.removeActiveSim();
                        World.changeSim(scan);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang bermain piano...");
        thread.start();
    }

    // main piano
    public void dengarmusik(Scanner scan) {
        boolean isValid = false;
        int duration = 1;
        while (!isValid) {
            try {
                System.out.print("Durasi ( detik kelipatan 10 ) : ");
                duration = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 10 != 0) {
            System.out.println("Input invalid ( harus kelipatan 10 ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    System.out.print("Durasi ( detik kelipatan 10 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
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
                    System.out.println("Mendengarkan musik selesai!");
                    kekenyangan = kekenyangan - (5 * (finalduration / 10));
                    mood = mood + (5 * (finalduration / 10));
                    World.addWaktu(finalduration);
                    World.checkAllSimTimer(finalduration, scan);
                    checkKondisiSim();
                    if (isDead()) {
                        World.removeActiveSim();
                        World.changeSim(scan);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang mendengarkan musik...");
        thread.start();
    }

    // olahraga
    public void olahraga(Scanner scan) {
        boolean isValid = false;
        int duration = 1;
        while (!isValid) {
            try {
                System.out.print("Durasi ( detik kelipatan 20 ) : ");
                duration = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 20 != 0) {
            System.out.println("Input invalid ( harus kelipatan 20 ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    System.out.print("Durasi ( detik kelipatan 20 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
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
                    World.checkAllSimTimer(finalduration, scan);
                    checkKondisiSim();
                    if (isDead()) {
                        World.removeActiveSim();
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

    // joget ( nambah mood nambah kesehatan turunin kekenyangan )
    public void joget(Scanner scan) {
        boolean isValid = false;
        int duration = 1;
        while (!isValid) {
            try {
                System.out.print("Durasi ( detik kelipatan 10 ) : ");
                duration = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 10 != 0) {
            System.out.println("Input invalid ( harus kelipatan 10 ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    System.out.print("Durasi ( detik kelipatan 10 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
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
                    World.checkAllSimTimer(finalduration, scan);
                    checkKondisiSim();
                    if (isDead()) {
                        World.removeActiveSim();
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

    // mati di tempat ( mood, kesehatan, kekenyangan jadi 0 )
    public void matiditempat(Scanner scan) {
        System.out.println(nama + " mati di tempat!");
        kekenyangan = 0;
        mood = 0;
        kesehatan = 0;
        World.removeActiveSim();
        World.changeSim(scan);
    }

    // nyanyi ( nambah mood )
    public void nyanyi(Scanner scan) {
        boolean isValid = false;
        int duration = 1;
        while (!isValid) {
            try {
                System.out.print("Durasi ( detik kelipatan 10 ) : ");
                duration = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 10 != 0) {
            System.out.println("Input invalid ( harus kelipatan 10 ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    System.out.print("Durasi ( detik kelipatan 10 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
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
                    World.checkAllSimTimer(finalduration, scan);
                    checkKondisiSim();
                    if (isDead()) {
                        World.removeActiveSim();
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

    // stretching ( nambah kesehatan )
    public void stretching(Scanner scan) {
        boolean isValid = false;
        int duration = 1;
        while (!isValid) {
            try {
                System.out.print("Durasi ( detik kelipatan 10 ) : ");
                duration = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 10 != 0) {
            System.out.println("Input invalid ( harus kelipatan 10 ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    System.out.print("Durasi ( detik kelipatan 10 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
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
                    World.checkAllSimTimer(finalduration, scan);
                    checkKondisiSim();
                    if (isDead()) {
                        World.removeActiveSim();
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

    // cuci wc ( nambah mood , turunin kekenyangan )
    public void cuciwc(Scanner scan) {
        boolean isValid = false;
        int duration = 1;
        while (!isValid) {
            try {
                System.out.print("Durasi ( detik kelipatan 30 ) : ");
                duration = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 30 != 0) {
            System.out.println("Input invalid ( harus kelipatan 30 ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    System.out.print("Durasi ( detik kelipatan 30 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
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
                    World.checkAllSimTimer(finalduration, scan);
                    checkKondisiSim();
                    if (isDead()) {
                        World.removeActiveSim();
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

    // cuci piring ( nambah mood )
    public void cucipiring(Scanner scan) {
        boolean isValid = false;
        int duration = 1;
        while (!isValid) {
            try {
                System.out.print("Durasi ( detik kelipatan 30 ) : ");
                duration = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (duration % 30 != 0) {
            System.out.println("Input invalid ( harus kelipatan 30 ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    System.out.print("Durasi ( detik kelipatan 30 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
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
                    World.checkAllSimTimer(finalduration, scan);
                    checkKondisiSim();
                    if (isDead()) {
                        World.removeActiveSim();
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

    // muntah ( nurunin kesehatan, nurunin kekenyangan ) (kalo kekenyangan > 100,
    // diset jadi 100 dan muntah)
    public void muntah(Scanner scan) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    System.out.println("Muntah selesai!");
                    kekenyangan = kekenyangan - 20;
                    World.addWaktu(10);
                    World.checkAllSimTimer(10, scan);
                    if (isDead()) {
                        World.removeActiveSim();
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
    public void makan(Scanner scan) {
        boolean isValid = false;
        int idx = 1;
        while (!isValid) {
            try {
                printEdibleList();
                System.out.println("0  Batal");
                System.out.print("Pilihan : ");
                idx = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        if (idx == 0) {
            System.out.println("Tidak jadi makan!");
        } else {
            while (idx < 0 || idx > countEdible()) {
                System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
                printEdibleList();
                System.out.println("0  Batal");
                System.out.print("Pilihan : ");
                isValid = false;
                while (!isValid) {
                    try {
                        idx = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                if (idx == 0) {
                    System.out.println("Tidak jadi makan!");
                }
            }
            int makananke = 0;
            Item yangdimakan = new Masakan("nasi ayam");
            for (Item item : inventory.getInventory()) {
                if (item instanceof BahanMakanan) {
                    makananke++;
                    if (makananke == idx) {
                        yangdimakan = item;
                    }
                } else if (item instanceof Masakan) {
                    makananke++;
                    if (makananke == idx) {
                        yangdimakan = item;
                    }
                }
            }
            inventory.removeItem(yangdimakan);
            Item dimakan = yangdimakan;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(30 * 1000);
                        System.out.println("Makan selesai!");
                        if (dimakan instanceof BahanMakanan) {
                            kekenyangan = kekenyangan + ((BahanMakanan) dimakan).getKekenyangan();
                        } else {
                            kekenyangan = kekenyangan + ((Masakan) dimakan).getKekenyangan();
                        }
                        World.addWaktu(30);
                        checkisFull(scan);
                        World.checkAllSimTimer(30, scan);
                        perluBAB = true;
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            System.out.println("Sedang makan...");
            thread.start();
        }
    }

    // masak
    public void masak(Scanner scan) {
        // algoritma masak apa ( perlu inventory )
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);// diubah menjadi 1.5 kekenyangan makanan yg dimasak
                    System.out.println("Makan selesai!");
                    mood = mood + 10; //
                    World.addWaktu(10); // diubah menjadi 1.5 kekenyangan makanan yg dimasak
                    World.checkAllSimTimer(10, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang makan...");
        thread.start();
    }

    // berkunjung
    public void berkunjung(Scanner scan) {
        boolean isValid = false;
        int idx = 1;
        while (!isValid) {
            try {
                System.out.println("Daftar rumah yang ada di World : ");
                for (int i = 0; i < World.getListofRumah().size(); i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + World.getListofRumah().get(i).getNama());
                }
                System.out.println("0. Batal");
                System.out.print("Pilihan : ");
                idx = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        if (idx == 0) {
            System.out.println("Tidak jadi berkunjung!");
        } else {
            while (idx < 0 || idx > World.getListofRumah().size()
                    || World.getListofRumah().get(idx - 1).getNama().equals(rumah.getNama())) {
                if (World.getListofRumah().get(idx - 1).getNama().equals(rumah.getNama())) {
                    System.out.println("Tidak bisa berkunjung ke rumah sendiri!");
                } else {
                    System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
                }
                System.out.println("Daftar rumah yang ada di World : ");
                for (int i = 0; i < World.getListofRumah().size(); i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + World.getListofRumah().get(i).getNama());
                }
                System.out.println("0. Batal");
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.print("Pilihan : ");
                        idx = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                if (idx == 0) {
                    System.out.println("Tidak jadi berkunjung!");
                }
            }
            int waktuberkunjung = rumah.getLokasi().distance(World.getListofRumah().get(idx - 1).getLokasi());
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(waktuberkunjung * 1000);
                        System.out.println("Berkunjung selesai!");
                        World.addWaktu(waktuberkunjung);
                        World.checkAllSimTimer(waktuberkunjung, scan);
                        checkKondisiSim();
                        if (isDead()) {
                            World.removeActiveSim();
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
            } catch (InterruptedException err) {
                System.out.println(err.getMessage());
            }
            isValid = false;
            int duration = 1;
            while (!isValid) {
                try {
                    System.out.print("Durasi berkunjung ( detik kelipatan 30 ) : ");
                    duration = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
                    System.out.println("Input invalid, silahkan input angka!");
                    scan.nextLine();
                }
            }
            while (duration % 30 != 0) {
                System.out.println("Input invalid ( harus kelipatan 30 ), silahkan diulangi!");
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.print("Durasi berkunjung ( detik kelipatan 30 ) : ");
                        duration = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
            }
            jatahWaktuBerkunjung = duration;
            isBerkunjung = true;
            posisiRumah = World.getListofRumah().get(idx - 1);
        }
    }

    // berpindah ruangan
    public void pindahruangan(Scanner scan) {
        boolean isValid = false;
        int idx = 1;
        while (!isValid) {
            try {
                System.out.println("Ruangan yang ada : ");
                int i = 0;
                for (int ruangan : posisiRuangan.getArrayRuangTerhubung()) {
                    if (i == 0) {
                        System.out.print(String.valueOf(i + 1) + ". Ruangan atas = ");
                    } else if (i == 1) {
                        System.out.print(String.valueOf(i + 1) + ". Ruangan bawah = ");
                    } else if (i == 2) {
                        System.out.print(String.valueOf(i + 1) + ". Ruangan kanan = ");
                    } else if (i == 3) {
                        System.out.print(String.valueOf(i + 1) + ". Ruangan kiri = ");
                    }
                    i++;

                    for (Ruangan ruanganRumah : posisiRumah.getListofRuangan()) {
                        if (ruanganRumah.getRuanganKe() == ruangan) {
                            System.out.print(ruanganRumah.getNamaRuangan() + "\n");
                        }
                    }
                    if (ruangan == 0) {
                        System.out.print("Tidak ada\n");
                    }
                }
                System.out.println("0. Batal");
                System.out.print("Pilihan : ");
                idx = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        if (idx == 0) {
            System.out.println("Tidak jadi berpindah ruangan!");
        } else {
            while (idx < 0 || idx > 3) {
                System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
                System.out.println("Ruangan yang ada : ");
                int i = 0;
                for (int ruangan : posisiRuangan.getArrayRuangTerhubung()) {
                    if (i == 0) {
                        System.out.print(String.valueOf(i + 1) + ". Ruangan atas = ");
                    } else if (i == 1) {
                        System.out.print(String.valueOf(i + 1) + ". Ruangan bawah = ");
                    } else if (i == 2) {
                        System.out.print(String.valueOf(i + 1) + ". Ruangan kanan = ");
                    } else if (i == 3) {
                        System.out.print(String.valueOf(i + 1) + ". Ruangan kiri = ");
                    }
                    i++;

                    for (Ruangan ruanganRumah : posisiRumah.getListofRuangan()) {
                        if (ruanganRumah.getRuanganKe() == ruangan) {
                            System.out.print(ruanganRumah.getNamaRuangan() + "\n");
                        }
                    }
                    if (ruangan == 0) {
                        System.out.print("Tidak ada\n");
                    }
                }
                System.out.println("0. Batal");
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.print("Pilihan : ");
                        idx = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                if (idx == 0) {
                    System.out.println("Tidak jadi berpindah ruangan!");
                }
            }

            for (Ruangan ruang : posisiRumah.getListofRuangan()) {
                if (ruang.getRuanganKe() == posisiRuangan.getRuangTerhubung(idx - 1)) {
                    posisiRuangan = ruang;
                }
            }
        }
    }

    // melihat inventory
    public void seeinventory() {
        inventory.printInventory();
    }

    // upgrade rumah

    public void upgraderumah(Scanner scan) {
        if (rumah.equals(posisiRumah)) {
            System.out.println("Ruangan apa yang ingin ditambah ruang tetangganya?");
            rumah.printListOfRuangan();
            String namaRuangan = scan.nextLine().toLowerCase();
            for (int i = 0; i < rumah.getListofRuangan().size(); i++) {
                if (namaRuangan.equals((rumah.getListofRuangan().get(i)).getNamaRuangan().toLowerCase())) {
                    System.out.print("Nama ruangan baru: ");
                    String namaRuanganBaru = scan.nextLine();
                    System.out.println("Pilih sisi pada ruangan " + (rumah.getListofRuangan().get(i)).getNamaRuangan()
                            + " untuk ditambah ruangan " + namaRuanganBaru);
                    System.out.println("Ketik 0 untuk sisi atas, 1 untuk bawah, 2 untuk kanan, atau 3 untuk kiri");
                    int sisi = scan.nextInt();
                    if ((rumah.getListofRuangan().get(i)).getRuangTerhubung(sisi) == 0) {
                        rumah.addRuangan(namaRuanganBaru, (rumah.getListofRuangan().get(i)), sisi);
                    } else {
                        System.out.println("Maaf sudah ada ruangan di sisi tersebut");
                    }
                }
            }

        } else {
            System.out.println("Maaf, upgrade rumah hanya bisa dilakukan di rumah pribadi Sim ini");
        }
    }

    // beli barang
    public void belibarang(Scanner scan) {
        boolean isValid = false;
        int idx = 1;
        while (!isValid) {
            try {
                printBuyableList();
                System.out.println("0  Batal");
                System.out.print("Pilihan : ");
                idx = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        if (idx == 0) {
            System.out.println("Tidak jadi membeli barang!");
        } else {
            while (idx < 0 || idx > 20 || uang < getHargaBarang(idx)) {
                if (uang < getHargaBarang(idx)) {
                    System.out.println("Uang tidak cukup! Silakan pilih yang lain!");
                } else {
                    System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
                }
                printBuyableList();
                System.out.println("0  Batal");
                System.out.print("Pilihan : ");
                isValid = false;
                while (!isValid) {
                    try {
                        idx = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                if (idx == 0) {
                    System.out.println("Tidak jadi membeli barang!");
                }
            }
            barangdibeli.add(getBarang(idx));
            Random random = new Random();
            double randomNumber = 0.1 + (1.5 - 0.1) * random.nextDouble();
            int shippingtime = (int) randomNumber * 30;
            timerbarangdibeli.add(shippingtime);
            uang = uang - getHargaBarang(idx);
            System.out.println("Barang berhasil dibeli! Silakan ditunggu untuk pengantarannya!");
        }
    }

    // memindah barang
    public void memindahBarang() {
        if (rumah.equals(posisiRumah)) {
            posisiRuangan.printListOfObjek();
            posisiRuangan.printMatriksRuangan();

            System.out.println("Pilih barang yang ingin dipindah");
            Scanner scan = new Scanner(System.in);
            String namaBarang = scan.nextLine().toLowerCase();

            System.out.println("Ketik posisi sekarang barang yang ingin dipindah");
            System.out.print("x: ");
            int x_Current = scan.nextInt();
            System.out.print("y: ");
            int y_Current = scan.nextInt();

            System.out.println("Ketik posisi baru barang yang ingin dipindah");
            System.out.print("x: ");
            int x_Baru = scan.nextInt();
            System.out.print("y: ");
            int y_Baru = scan.nextInt();

            NonMakanan barang = new NonMakanan(namaBarang);
            boolean berhasil = posisiRuangan.memindahBarang(barang, x_Current, y_Current, x_Baru, y_Baru);
            if (berhasil) {
                System.out.println(barang.getNamaItem() + " berhasil dipindah");
            } else {
                System.out.println("Barang gagal dipindah!");
            }

        }
    }

    // memasang barang
    public void memasangbarang() {
        if (rumah.equals(posisiRumah)) {
            // Pilih barang di inventory
            seeinventory();
            System.out.println("Pilih barang yang ingin dipasang");
            Scanner scan = new Scanner(System.in);
            String namaBarang = scan.nextLine().toLowerCase();

            for (String nama : inventory.getDetails().keySet()) {
                if (namaBarang.equals(nama.toLowerCase())) {

                    NonMakanan barang = new NonMakanan(namaBarang);
                    int x;
                    int y;
                    boolean selesai = false;
                    int p = barang.getPanjang();
                    int l = barang.getLebar();
                    while (!selesai) {
                        try {
                            System.out.println(barang.getNamaItem() + ": ");
                            for (int i = 0; i < l; i++) {
                                for (int j = 0; j < p; j++) {
                                    System.out.print("1");
                                }
                                System.out.print("\n");
                            }
                            System.out.println(posisiRuangan.getNamaRuangan() + ": ");
                            posisiRuangan.printMatriksRuangan();
                            System.out.println(
                                    " Pilihan: \n 1. Pilih letak (area kosong 0)\n 2. Putar barang\n 3. Batal");
                            Scanner read = new Scanner(System.in);
                            int pilihan = read.nextInt();
                            if (pilihan == 1) {
                                while (!selesai) {
                                    try {
                                        System.out.print("Pilih titik horizontal awal: ");
                                        x = read.nextInt();
                                        if (x < 0 || x > 5) {
                                            throw new InputMismatchException();
                                        }

                                        System.out.print("Pilih titik vertikal awal: ");
                                        y = read.nextInt();
                                        if (y < 0 || y > 5) {
                                            throw new InputMismatchException();
                                        }

                                        boolean berhasil = posisiRuangan.memasangBarang(barang, x, y); // true kalau
                                                                                                       // area
                                                                                                       // kosong, false
                                                                                                       // kalau
                                                                                                       // udah ada
                                                                                                       // barang lain
                                        if (berhasil) {
                                            inventory.removeItem(barang);
                                            System.out.println(barang.getNamaItem() + " berhasil dipasang");
                                            selesai = true;
                                        } else {
                                            System.out.println("Area tidak kosong, Pilih titik lain!");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Pilih koordinat yang valid! (0-5)");
                                    }
                                }
                            } else if (pilihan == 2) { // Putar barang
                                p = p + l;
                                l = p - l;
                                p = p - l; // ditukar panjang sama lebar
                                barang.setPanjang(p);
                                barang.setLebar(l);
                            } else if (pilihan == 3) { // Batal
                                selesai = true;
                            } else {
                                throw new InputMismatchException();
                            }
                        } catch (InputMismatchException e) { // input tidak valid
                            System.out.println("Input invalid (masukan pilihan angka yang tersedia)");
                        }
                    }

                } else {
                    System.out.println("Anda tidak memiliki barang tersebut");
                }
            }

        } else {
            System.out.println("Maaf, anda hanya bisa memasang barang pada rumah pribadi Sim ini");
        }

    }

    // melihat waktu
    public void seetime() {
        int menit = (720 - World.getWaktu()) / 60;
        int detik = (720 - World.getWaktu()) % 60;
        System.out.println("Sisa waktu hari ini :");
        System.out.println(String.valueOf(menit) + " menit " + String.valueOf(detik) + " detik");

        // print waktu sisa untuk beli barang
        if (barangdibeli.size() > 0) {
            System.out.println("Sisa waktu pembelian barang :");
            System.out.printf("%-4s %-20s %-30s\n", "No", "Barang", "Sisa Waktu Pengiriman");
            for (int i = 0; i < barangdibeli.size(); i++) {
                menit = timerbarangdibeli.get(i) / 60;
                detik = timerbarangdibeli.get(i) % 60;
                System.out.printf("%-4s %-20s %-30s\n", i + 1, barangdibeli.get(i).getNamaItem(),
                        menit + " menit " + detik + " detik");
            }
        }

        // print waktu sisa untuk upgrade rumah
        // kode.....
    }

    public void gotoObject(Scanner scan) {
        // nanti dilanjutin untuk terima input berupa int x dan pergi ke objek ke-x di
        // list
        // udah sampai objek, dicek objek apa yg dihampirin, lalu ditanyain mau aksi apa
        // sesuai dengan objeknya, kalo ga mau lakukan aksi, gak jalanin aksi, kalo mau,
        // jalanin aksi
        int choiceobjek = 1;
        boolean isValid = false;
        while (!isValid) {
            try {
                posisiRuangan.printListOfObjek();
                System.out.println("0. Batal");
                System.out.print("Pilihan : ");
                choiceobjek = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (choiceobjek > posisiRuangan.getListofObjek().size() || choiceobjek < 0) {
            System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    posisiRuangan.printListOfObjek();
                    System.out.println("0. Batal");
                    System.out.print("Pilihan : ");
                    choiceobjek = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
                    System.out.println("Input invalid, silahkan input angka!");
                    scan.nextLine();
                }
            }
        }
        if (choiceobjek == 0) {
            System.out.println("Berhasil dibatalkan!");
        } else {
            NonMakanan accessed = posisiRuangan.getListofObjek().get(choiceobjek - 1);
            posisi = accessed.getTitikAwal();
            if (accessed.getNamaItem().equals("kasur single") || accessed.getNamaItem().equals("kasur queen size")
                    || accessed.getNamaItem().equals("kasur king size")) {

                int choiceaksi = 1;
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Tidur");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 1 || choiceaksi < 0) {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid) {
                        try {
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Tidur");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        } catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
                }
                if (choiceaksi == 0) {
                    System.out.println("Aksi tidak dilakukan!");
                } else {
                    tidur(scan);
                }
            } else if (accessed.getNamaItem().equals("toilet")) {
                int choiceaksi = 1;
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Buang air");
                        System.out.println("2. Cuci WC");
                        System.out.println("3. Mandi");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 2 || choiceaksi < 0) {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid) {
                        try {
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Buang air");
                            System.out.println("2. Cuci WC");
                            System.out.println("3. Mandi");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        } catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
                }
                if (choiceaksi == 0) {
                    System.out.println("Aksi tidak dilakukan!");
                } else if (choiceaksi == 1) {
                    buangair(scan);
                } else if (choiceaksi == 2) {
                    cuciwc(scan);
                } else if (choiceaksi == 3) {
                    mandi(scan);
                }
            } else if (accessed.getNamaItem().equals("kompor gas") || accessed.getNamaItem().equals("kompor listrik")) {
                int choiceaksi = 1;
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Memasak");
                        System.out.println("2. Cuci piring");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 2 || choiceaksi < 0) {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid) {
                        try {
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Memasak");
                            System.out.println("2. Cuci piring");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        } catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
                }
                if (choiceaksi == 0) {
                    System.out.println("Aksi tidak dilakukan!");
                } else if (choiceaksi == 1) {
                    masak(scan);
                } else if (choiceaksi == 2) {
                    cucipiring(scan);
                }
            } else if (accessed.getNamaItem().equals("meja dan kursi")) {
                int choiceaksi = 1;
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.println("Input tidak valid!");
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Makan");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 1 || choiceaksi < 0) {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid) {
                        try {
                            System.out.println("Input tidak valid!");
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Makan");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        } catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
                }
                if (choiceaksi == 0) {
                    System.out.println("Aksi tidak dilakukan!");
                } else if (choiceaksi == 1) {
                    makan(scan);
                }
            } else if (accessed.getNamaItem().equals("jam")) {
                int choiceaksi = 1;
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.println("Input tidak valid!");
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Melihat waktu");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 1 || choiceaksi < 0) {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid) {
                        try {
                            System.out.println("Input tidak valid!");
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Melihat waktu");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        } catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
                }
                if (choiceaksi == 0) {
                    System.out.println("Aksi tidak dilakukan!");
                } else if (choiceaksi == 1) {
                    seetime();
                }
            } else if (accessed.getNamaItem().equals("play station")) {
                int choiceaksi = 1;
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.println("Input tidak valid!");
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Main game");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 1 || choiceaksi < 0) {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid) {
                        try {
                            System.out.println("Input tidak valid!");
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Main game");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        } catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
                }
                if (choiceaksi == 0) {
                    System.out.println("Aksi tidak dilakukan!");
                } else if (choiceaksi == 1) {
                    maingame(scan);

                }
            } else if (accessed.getNamaItem().equals("lemari buku")) {
                int choiceaksi = 1;
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.println("Input tidak valid!");
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Baca buku");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 1 || choiceaksi < 0) {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid) {
                        try {
                            System.out.println("Input tidak valid!");
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Baca buku");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        } catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
                }
                if (choiceaksi == 0) {
                    System.out.println("Aksi tidak dilakukan!");
                } else if (choiceaksi == 1) {
                    bacabuku(scan);
                }
            } else if (accessed.getNamaItem().equals("radio")) {
                int choiceaksi = 1;
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.println("Input tidak valid!");
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Mendengarkan musik");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 1 || choiceaksi < 0) {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid) {
                        try {
                            System.out.println("Input tidak valid!");
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Mendengarkan musik");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        } catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
                }
                if (choiceaksi == 0) {
                    System.out.println("Aksi tidak dilakukan!");
                } else if (choiceaksi == 1) {
                    dengarmusik(scan);
                }
            } else if (accessed.getNamaItem().equals("piano")) {
                int choiceaksi = 1;
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.println("Input tidak valid!");
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Main piano");
                        System.out.println("0. Batal");
                        System.out.print("Pilihan : ");
                        choiceaksi = scan.nextInt();
                        isValid = true;
                    } catch (Exception e) {
                        System.out.println("Input invalid, silahkan input angka!");
                        scan.nextLine();
                    }
                }
                while (choiceaksi > 1 || choiceaksi < 0) {
                    System.out.println("Input tidak valid ( diluar index )!");
                    isValid = false;
                    while (!isValid) {
                        try {
                            System.out.println("Input tidak valid!");
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Main piano");
                            System.out.println("0. Batal");
                            System.out.print("Pilihan : ");
                            choiceaksi = scan.nextInt();
                            isValid = true;
                        } catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
                }
                if (choiceaksi == 0) {
                    System.out.println("Aksi tidak dilakukan!");
                } else if (choiceaksi == 1) {
                    mainpiano(scan);
                }
            }
        }
    }
}
