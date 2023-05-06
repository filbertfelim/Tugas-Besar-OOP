import java.util.Scanner;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.io.*;
import java.lang.Math;

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
    private Point posisi;
    private Rumah rumah;
    private Rumah posisiRumah;
    private Ruangan posisiRuangan;
    private int timerBelumTidur;
    private int timerBelumBAB;
    private boolean perluBAB;
    private int jatahWaktuBerkunjung;
    private int timerWaktuKunjung;
    private boolean isBerkunjung;
    private Item barangdibeli;
    private int timerbarangdibeli;
    private boolean isUpgradingHouse;
    private String upgradingroomname;
    private int sisiupgradingroom;
    private int ruangterhubungupgrading;
    private int timerUpgradeHouse;

    // Konstruktor
    public Sim(String nama, Point alamatRumah) {
        this.nama = nama;
        kekenyangan = 80;
        mood = 80;
        kesehatan = 80;
        uang = 100000;
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
        jatahWaktuBerkunjung = 1;
        timerWaktuKunjung = 0;
        isBerkunjung = false;
        barangdibeli = null;
        timerbarangdibeli = -9999;
        isUpgradingHouse = false;
        timerUpgradeHouse = 0;
    }

    // untuk fitur load
    public Sim(String nama, Pekerjaan pekerjaan, int uang, Inventory<Item> inventory, int kekenyangan, int mood,
            int kesehatan,
            String status, Point posisi, Rumah rumah, Rumah posisiRumah, Ruangan posisiRuangan, int timerBelumTidur,
            int timerBelumBAB, boolean perluBAB, int jatahWaktuBerkunjung, int timerWaktuKunjung, boolean isBerkunjung,
            Item barangdibeli, int timerbarangdibeli, boolean isUpgradingHouse, String upgradingroomname,
            int sisiupgradingroom, int ruangterhubungupgrading, int timerUpgradeHouse) {
        this.nama = nama;
        this.pekerjaan = pekerjaan;
        this.uang = uang;
        this.inventory = inventory;
        this.kekenyangan = kekenyangan;
        this.mood = mood;
        this.kesehatan = kesehatan;
        this.status = status;
        this.posisi = posisi;
        this.rumah = rumah;
        this.posisiRumah = posisiRumah;
        this.posisiRuangan = posisiRuangan;
        this.timerBelumTidur = timerBelumTidur;
        this.timerBelumBAB = timerBelumBAB;
        this.perluBAB = perluBAB;
        this.jatahWaktuBerkunjung = jatahWaktuBerkunjung;
        this.timerWaktuKunjung = timerWaktuKunjung;
        this.isBerkunjung = isBerkunjung;
        this.barangdibeli = barangdibeli;
        this.timerbarangdibeli = timerbarangdibeli;
        this.isUpgradingHouse = isUpgradingHouse;
        this.upgradingroomname = upgradingroomname;
        this.sisiupgradingroom = sisiupgradingroom;
        this.ruangterhubungupgrading = ruangterhubungupgrading;
        this.timerUpgradeHouse = timerUpgradeHouse;
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

    public void setInventory(Inventory<Item> inventory) {
        this.inventory = inventory;
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

    public void setRumah(Rumah rumah) {
        this.rumah = rumah;
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

    public void setTimerBelumTidur(int timerBelumTidur) {
        this.timerBelumTidur = timerBelumTidur;
    }

    public int getTimerBelumBab() {
        return timerBelumBAB;
    }

    public void setTimerBelumBab(int timerBelumBAB) {
        this.timerBelumBAB = timerBelumBAB;
    }

    public boolean getPerluBab() {
        return perluBAB;
    }

    public void setPerluBab(boolean perluBab) {
        this.perluBAB = perluBab;
    }

    public int getJatahWaktuBerkunjung() {
        return jatahWaktuBerkunjung;
    }

    public void setJatahWaktuBerkunjung(int jatahWaktuBerkunjung) {
        this.jatahWaktuBerkunjung = jatahWaktuBerkunjung;
    }

    public int getTimerWaktuKunjung() {
        return timerWaktuKunjung;
    }

    public void setTimerWaktuKunjung(int timerWaktuKunjung) {
        this.timerWaktuKunjung = timerWaktuKunjung;
    }

    public boolean getIsBerkunjung() {
        return isBerkunjung;
    }

    public void setIsBerkunjung(boolean isBerkunjung) {
        this.isBerkunjung = isBerkunjung;
    }

    public Item getBarangDiBeli() {
        return barangdibeli;
    }

    public void setBarangDiBeli(Item barangdibeli) {
        this.barangdibeli = barangdibeli;
    }

    public int getTimerBarangDibeli() {
        return timerbarangdibeli;
    }

    public void setTimerBarangDibeli(int timerbarangdibeli) {
        this.timerbarangdibeli = timerbarangdibeli;
    }

    // private boolean isUpgradingHouse;
    public boolean getIsUpgradingHouse() {
        return isUpgradingHouse;
    }

    public void setIsUpgradingHouse(boolean isUpgradingHouse) {
        this.isUpgradingHouse = isUpgradingHouse;
    }

    // private String upgradingroomname;
    public String getUpgradingRoomName() {
        return upgradingroomname;
    }

    public void setUpgradingRoomName(String upgradingroomname) {
        this.upgradingroomname = upgradingroomname;
    }

    // private int sisiupgradingroom;
    public int getSisiUpgradingRoom() {
        return sisiupgradingroom;
    }

    public void setSisiUpgradingRoom(int sisiupgradingroom) {
        this.sisiupgradingroom = sisiupgradingroom;
    }

    // private int ruangterhubungupgrading;
    public int getRuangTerhubungUpgrading() {
        return ruangterhubungupgrading;
    }

    public void setRuangTerhubungUpgrading(int ruangterhubungupgrading) {
        this.ruangterhubungupgrading = ruangterhubungupgrading;
    }

    // private int timerUpgradeHouse;
    public int getTimerUpgradeHouse() {
        return timerUpgradeHouse;
    }

    public void setTimerUpgradeHouse(int timerUpgradeHouse) {
        this.timerUpgradeHouse = timerUpgradeHouse;
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

    public ArrayList<String> txtLoader(String namaFile) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            Scanner s = new Scanner(new File("savefile/" + namaFile + ".txt"));
            while (s.hasNextLine()) {
                list.add(s.nextLine());
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean isDead() {
        if (kesehatan <= 0 || kekenyangan <= 0 || mood <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void getInfo() {
        System.out.printf("+=========+\tPROFILE\t\t+=========\n");
        System.out.printf("Nama\t\t:" + getNama() + "\n");
        System.out.printf("Pekerjaan\t:" + getPekerjaan().getNama() + "\n");
        System.out.printf("Uang\t\t:" + getUang() + "\n\n");
        System.out.printf("+=========+\t STATS \t\t+=========\n");
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

    public void printKodeBarang() {
        System.out.printf("%-20s %-10s\n", "Barang", "Kode");
        System.out.printf("%-20s %-10s\n", "Tempat kosong", "0");
        System.out.printf("%-20s %-10s\n", "Kasur single", "1");
        System.out.printf("%-20s %-10s\n", "Kasur queen size", "2");
        System.out.printf("%-20s %-10s\n", "Kasur king size", "3");
        System.out.printf("%-20s %-10s\n", "Toilet", "4");
        System.out.printf("%-20s %-10s\n", "Kompor gas", "5");
        System.out.printf("%-20s %-10s\n", "Kompor listrik", "6");
        System.out.printf("%-20s %-10s\n", "Meja dan kursi", "7");
        System.out.printf("%-20s %-10s\n", "Jam", "8");
        System.out.printf("%-20s %-10s\n", "Play station", "9");
        System.out.printf("%-20s %-10s\n", "Lemari buku", "10");
        System.out.printf("%-20s %-10s\n", "Radio", "11");
        System.out.printf("%-20s %-10s\n", "Piano", "12");
        System.out.printf("%-20s %-10s\n", "Televisi", "13");
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
        System.out.printf("%-2s %-20s %-10s %10s\n", 13, "Televisi", 150, "2 x 1");
        System.out.printf("\n%-2s %-20s %-10s %10s\n", "No", "Bahan Makanan", "Harga", "Kekenyangan");
        System.out.printf("%-2s %-20s %-10s %10s\n", 14, "Nasi", 5, 5);
        System.out.printf("%-2s %-20s %-10s %10s\n", 15, "Kentang", 3, 4);
        System.out.printf("%-2s %-20s %-10s %10s\n", 16, "Ayam", 10, 8);
        System.out.printf("%-2s %-20s %-10s %10s\n", 17, "Sapi", 12, 15);
        System.out.printf("%-2s %-20s %-10s %10s\n", 18, "Wortel", 3, 2);
        System.out.printf("%-2s %-20s %-10s %10s\n", 19, "Bayam", 3, 2);
        System.out.printf("%-2s %-20s %-10s %10s\n", 20, "Kacang", 2, 2);
        System.out.printf("%-2s %-20s %-10s %10s\n", 21, "Susu", 2, 1);
    }

    public void printMasakan() {
        System.out.printf("%-2s %-10s %-40s %10s\n", "No", "Masakan", "Bahan", "Kekenyangan");
        System.out.printf("%-2s %-10s %-40s %10s\n", 1, "Nasi Ayam", "Nasi + Ayam", 16);
        System.out.printf("%-2s %-10s %-40s %10s\n", 2, "Nasi Kari", "Nasi + Kentang + Wortel + Sapi", 30);
        System.out.printf("%-2s %-10s %-40s %10s\n", 3, "Susu Kacang", "Susu + Kacang", 5);
        System.out.printf("%-2s %-10s %-40s %10s\n", 4, "Tumis Sayur", "Wortel + Bayam", 5);
        System.out.printf("%-2s %-10s %-40s %10s\n", 5, "Bistik", "Kentang + Sapi", 22);
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
            return new NonMakanan("televisi");
        } else if (idx == 14) {
            return new BahanMakanan("nasi");
        } else if (idx == 15) {
            return new BahanMakanan("kentang");
        } else if (idx == 16) {
            return new BahanMakanan("ayam");
        } else if (idx == 17) {
            return new BahanMakanan("sapi");
        } else if (idx == 18) {
            return new BahanMakanan("wortel");
        } else if (idx == 19) {
            return new BahanMakanan("bayam");
        } else if (idx == 20) {
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
        } else if (idx == 3 || idx == 13) {
            return 150;
        } else if (idx == 6 || idx == 9 || idx == 12) {
            return 200;
        } else if (idx == 8 || idx == 16) {
            return 10;
        } else if (idx == 14) {
            return 5;
        } else if (idx == 15 || idx == 18 || idx == 19) {
            return 3;
        } else if (idx == 17) {
            return 12;
        } else if (idx == 20 || idx == 21) {
            return 2;
        } else {
            return 0;
        }
    }

    public void addTimerBeliBarang(int duration) {
        timerbarangdibeli -= duration;
    }

    public void checkkirimBarang() {
        if (timerbarangdibeli <= 0 && timerbarangdibeli > -9999) {
            System.out.println(barangdibeli.getNamaItem() + " sudah sampai untuk " + nama + ", "
                    + barangdibeli.getNamaItem() + " dimasukkan ke inventory!");
            inventory.addItem(barangdibeli);
            barangdibeli = null;
            timerbarangdibeli = -9999;
        }

    }

    public void addTimerUpgradeHouse(int duration) {
        if (isUpgradingHouse) {
            timerUpgradeHouse += duration;
        }

    }

    public void checkUpgradeHouse() {
        if (timerUpgradeHouse >= 1080) {
            isUpgradingHouse = false;
            rumah.addRuangan(upgradingroomname, rumah.getListofRuangan().get(ruangterhubungupgrading),
                    sisiupgradingroom);
            timerUpgradeHouse = 0;
            upgradingroomname = "";
            sisiupgradingroom = -9999;
            ruangterhubungupgrading = -9999;
            System.out.println("Upgrade rumah untuk " + nama + " telah selesai!\n");
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
        if (timerBelumBAB >= 240 && perluBAB) {
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
        if (timerWaktuKunjung >= jatahWaktuBerkunjung) {
            System.out.println("Waktu berkunjung " + getNama() + " sudah habis! Saatnya pulang.");
            jatahWaktuBerkunjung = 1;
            mood = mood + (10 * (timerWaktuKunjung / 30));
            kekenyangan = kekenyangan - (10 * (timerWaktuKunjung / 30));
            timerWaktuKunjung = 0;
            isBerkunjung = false;
            int waktubalik = rumah.getLokasi().distance(posisiRumah.getLokasi());
            posisiRumah = rumah;
            posisiRuangan = posisiRumah.getListofRuangan().get(0);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(waktubalik * 1000);
                        System.out.println("Sudah sampai di rumah!");
                        World.addWaktu(waktubalik);
                        checkKondisiSim();
                        World.checkAllSimTimer(waktubalik, scan);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            System.out.println("Sedang balik ke rumah...");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException err) {
            }
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
                    checkKondisiSim();
                    World.checkAllSimTimer(10, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang buang air...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
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
                    checkKondisiSim();
                    resetTimerBelumTidurAfterSleep();
                    World.checkAllSimTimer(finalduration, scan);
                    resetTimerBelumTidurAfterSleep();
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang tidur...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
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
                        System.out.println("Kerja selesai!");
                        pekerjaan.addLamaBekerja(finalduration);
                        setKekenyangan(getKekenyangan() - (10 * finalduration / 30));
                        setMood(getMood() - (10 * finalduration / 30));
                        setUang(getUang() + (pekerjaan.getGaji() * finalduration / 240));
                        World.addWaktu(finalduration);
                        checkKondisiSim();
                        World.checkAllSimTimer(finalduration, scan);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            System.out.println("Sedang bekerja...");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException err) {
            }
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
                    checkKondisiSim();
                    World.checkAllSimTimer(finalduration, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang mandi...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
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
                    checkKondisiSim();
                    World.checkAllSimTimer(finalduration, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang main game...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
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
                    checkKondisiSim();
                    World.checkAllSimTimer(finalduration, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang baca buku...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
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
                    checkKondisiSim();
                    World.checkAllSimTimer(finalduration, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang bermain piano...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
    }

    // mendengarkan musik
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
                    checkKondisiSim();
                    World.checkAllSimTimer(finalduration, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang mendengarkan musik...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
    }

    // menonton TV
    public void nontonTV(Scanner scan) {
        boolean isValid = false;
        boolean isFilmValid = false;
        int duration = 1;
        int choiceFilm = -1;
        ArrayList<String> listOfFilm = txtLoader("ListFilm");
        while (!isFilmValid) {
            try {
                for (int i = 0; i < listOfFilm.size(); i++) {
                    String film = listOfFilm.get(i);
                    System.out.println(film);
                }
                System.out.print("Pilih film yang ingin ditonton: ");
                choiceFilm = scan.nextInt();
                isFilmValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka yang di dalam range!");
                scan.nextLine();
            }
        }
        while (choiceFilm < 0 || choiceFilm > 15) {
            System.out.println("Input invalid ( harus dalam range ), silahkan diulangi!");
            isFilmValid = false;
            while (!isFilmValid) {
                try {
                    for (int i = 0; i < listOfFilm.size(); i++) {
                        String film = listOfFilm.get(i);
                        System.out.println(film);
                    }
                    System.out.print("Pilih film yang ingin ditonton: ");
                    choiceFilm = scan.nextInt();
                    isFilmValid = true;
                } catch (Exception e) {
                    System.out.println("Input invalid, silahkan input angka yang di dalam range!");
                    scan.nextLine();
                }
            }
        }
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
                    System.out.println("Menonton film selesai!");
                    kekenyangan = kekenyangan - (5 * (finalduration / 10));
                    mood = mood + (5 * (finalduration / 10));
                    World.addWaktu(finalduration);
                    checkKondisiSim();
                    World.checkAllSimTimer(finalduration, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        String filmString = listOfFilm.get(choiceFilm-1);
        String[] arrStrings = filmString.split(". ", 2);
        System.out.println("Sedang menonton Film " + arrStrings[1]);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
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
                    checkKondisiSim();
                    World.checkAllSimTimer(finalduration, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang olahraga...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
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
                    checkKondisiSim();
                    World.checkAllSimTimer(finalduration, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang joget...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
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
                    checkKondisiSim();
                    World.checkAllSimTimer(finalduration, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang nyanyi...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
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
                    checkKondisiSim();
                    World.checkAllSimTimer(finalduration, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang stretching...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
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
                    checkKondisiSim();
                    World.checkAllSimTimer(finalduration, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang cuci WC...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
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
                    checkKondisiSim();
                    World.checkAllSimTimer(finalduration, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang cuci piring...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
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
                    checkKondisiSim();
                    World.checkAllSimTimer(10, scan);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        System.out.println("Sedang muntah...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException err) {
        }
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
            if (idx != 0) {
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
                            checkKondisiSim();
                            World.checkAllSimTimer(30, scan);
                            perluBAB = true;
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                });
                System.out.println("Sedang makan...");
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException err) {
                }
            }
        }
    }

    // masak
    public void masak(Scanner scan) {
        boolean isValid = false;
        boolean canCook = false;
        boolean cancelCook = false;
        int idx = 1;
        while (!canCook && !cancelCook) {
            while (!isValid) {
                try {
                    System.out.println("Masakan yang tersedia :\n");
                    printMasakan();
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
                System.out.println("Tidak jadi masak!");
                cancelCook = true;
            } else if (idx == 1) {
                int countbahan = 2;
                int counter = 0;
                for (Item item : inventory.getInventory()) {
                    if (item.getNamaItem().equals("nasi") || item.getNamaItem().equals("ayam")) {
                        counter++;
                    }
                }
                if (counter == countbahan) {
                    canCook = true;
                    inventory.removeItem(new BahanMakanan("nasi"));
                    inventory.removeItem(new BahanMakanan("ayam"));
                    inventory.addItem(new Masakan("nasi ayam"));
                    int kekenyangan = 16;
                } else {
                    System.out.println("Bahan tidak lengkap! silahkan masak yang lain!");
                    isValid = false;
                }
            } else if (idx == 2) {
                int countbahan = 4;
                int counter = 0;
                for (Item item : inventory.getInventory()) {
                    if (item.getNamaItem().equals("nasi") || item.getNamaItem().equals("kentang")
                            || item.getNamaItem().equals("wortel") || item.getNamaItem().equals("sapi")) {
                        counter++;
                    }
                }
                if (counter == countbahan) {
                    canCook = true;
                    inventory.removeItem(new BahanMakanan("nasi"));
                    inventory.removeItem(new BahanMakanan("kentang"));
                    inventory.removeItem(new BahanMakanan("wortel"));
                    inventory.removeItem(new BahanMakanan("sapi"));
                    inventory.addItem(new Masakan("nasi kari"));
                    int kekenyangan = 30;
                } else {
                    System.out.println("Bahan tidak lengkap! silahkan masak yang lain!");
                    isValid = false;
                }
            } else if (idx == 3) {
                int countbahan = 2;
                int counter = 0;
                for (Item item : inventory.getInventory()) {
                    if (item.getNamaItem().equals("susu") || item.getNamaItem().equals("kacang")) {
                        counter++;
                    }
                }
                if (counter == countbahan) {
                    canCook = true;
                    inventory.removeItem(new BahanMakanan("susu"));
                    inventory.removeItem(new BahanMakanan("kacang"));
                    inventory.addItem(new Masakan("susu kacang"));
                    int kekenyangan = 5;
                } else {
                    System.out.println("Bahan tidak lengkap! silahkan masak yang lain!");
                    isValid = false;
                }
            } else if (idx == 4) {
                int countbahan = 2;
                int counter = 0;
                for (Item item : inventory.getInventory()) {
                    if (item.getNamaItem().equals("wortel") || item.getNamaItem().equals("bayam")) {
                        counter++;
                    }
                }
                if (counter == countbahan) {
                    canCook = true;
                    inventory.removeItem(new BahanMakanan("wortel"));
                    inventory.removeItem(new BahanMakanan("bayam"));
                    inventory.addItem(new Masakan("tumis sayur"));
                    int kekenyangan = 5;
                } else {
                    System.out.println("Bahan tidak lengkap! silahkan masak yang lain!");
                    isValid = false;
                }
            } else if (idx == 5) {
                int countbahan = 2;
                int counter = 0;
                for (Item item : inventory.getInventory()) {
                    if (item.getNamaItem().equals("kentang") || item.getNamaItem().equals("sapi")) {
                        counter++;
                    }
                }
                if (counter == countbahan) {
                    canCook = true;
                    inventory.removeItem(new BahanMakanan("kentang"));
                    inventory.removeItem(new BahanMakanan("sapi"));
                    inventory.addItem(new Masakan("bistik"));
                    int kekenyangan = 22;
                } else {
                    System.out.println("Bahan tidak lengkap! silahkan masak yang lain!");
                    isValid = false;
                }
            } else {
                while (idx < 0 || idx > 5 || (!canCook && !cancelCook)) {
                    if (idx < 0 || idx > 5) {
                        System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
                    }
                    System.out.println("Masakan yang tersedia :\n");
                    printMasakan();
                    System.out.println("0  Batal");
                    System.out.print("Pilihan : ");
                    isValid = false;
                    while (!isValid) {
                        try {
                            System.out.println("Masakan yang tersedia :\n");
                            printMasakan();
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
                        System.out.println("Tidak jadi masak!");
                        cancelCook = true;
                    } else if (idx == 1) {
                        int countbahan = 2;
                        int counter = 0;
                        for (Item item : inventory.getInventory()) {
                            if (item.getNamaItem().equals("nasi") || item.getNamaItem().equals("ayam")) {
                                counter++;
                            }
                        }
                        if (counter == countbahan) {
                            canCook = true;
                            inventory.removeItem(new BahanMakanan("nasi"));
                            inventory.removeItem(new BahanMakanan("ayam"));
                            inventory.addItem(new Masakan("nasi ayam"));
                            int kekenyangan = 16;
                        } else {
                            System.out.println("Bahan tidak lengkap! silahkan masak yang lain!");
                            isValid = false;
                        }
                    } else if (idx == 2) {
                        int countbahan = 4;
                        int counter = 0;
                        for (Item item : inventory.getInventory()) {
                            if (item.getNamaItem().equals("nasi") || item.getNamaItem().equals("kentang")
                                    || item.getNamaItem().equals("wortel") || item.getNamaItem().equals("sapi")) {
                                counter++;
                            }
                        }
                        if (counter == countbahan) {
                            canCook = true;
                            inventory.removeItem(new BahanMakanan("nasi"));
                            inventory.removeItem(new BahanMakanan("kentang"));
                            inventory.removeItem(new BahanMakanan("wortel"));
                            inventory.removeItem(new BahanMakanan("sapi"));
                            inventory.addItem(new Masakan("nasi kari"));
                            int kekenyangan = 30;
                        } else {
                            System.out.println("Bahan tidak lengkap! silahkan masak yang lain!");
                            isValid = false;
                        }
                    } else if (idx == 3) {
                        int countbahan = 2;
                        int counter = 0;
                        for (Item item : inventory.getInventory()) {
                            if (item.getNamaItem().equals("susu") || item.getNamaItem().equals("kacang")) {
                                counter++;
                            }
                        }
                        if (counter == countbahan) {
                            canCook = true;
                            inventory.removeItem(new BahanMakanan("susu"));
                            inventory.removeItem(new BahanMakanan("kacang"));
                            inventory.addItem(new Masakan("susu kacang"));
                            int kekenyangan = 5;
                        } else {
                            System.out.println("Bahan tidak lengkap! silahkan masak yang lain!");
                            isValid = false;
                        }
                    } else if (idx == 4) {
                        int countbahan = 2;
                        int counter = 0;
                        for (Item item : inventory.getInventory()) {
                            if (item.getNamaItem().equals("wortel") || item.getNamaItem().equals("bayam")) {
                                counter++;
                            }
                        }
                        if (counter == countbahan) {
                            canCook = true;
                            inventory.removeItem(new BahanMakanan("wortel"));
                            inventory.removeItem(new BahanMakanan("bayam"));
                            inventory.addItem(new Masakan("tumis sayur"));
                            int kekenyangan = 5;
                        } else {
                            System.out.println("Bahan tidak lengkap! silahkan masak yang lain!");
                            isValid = false;
                        }
                    } else if (idx == 5) {
                        int countbahan = 2;
                        int counter = 0;
                        for (Item item : inventory.getInventory()) {
                            if (item.getNamaItem().equals("kentang") || item.getNamaItem().equals("sapi")) {
                                counter++;
                            }
                        }
                        if (counter == countbahan) {
                            canCook = true;
                            inventory.removeItem(new BahanMakanan("kentang"));
                            inventory.removeItem(new BahanMakanan("sapi"));
                            inventory.addItem(new Masakan("bistik"));
                            int kekenyangan = 22;
                        } else {
                            System.out.println("Bahan tidak lengkap! silahkan masak yang lain!");
                            isValid = false;
                        }
                    }
                }
                if (canCook) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(Math.round(1.5 * kekenyangan * 1000));
                                System.out.println("masak selesai!");
                                mood = mood + 10;
                                World.addWaktu((int) Math.round(1.5 * kekenyangan));
                                checkKondisiSim();
                                World.checkAllSimTimer((int) Math.round(1.5 * kekenyangan), scan);
                            } catch (InterruptedException e) {
                                return;
                            }
                        }
                    });
                    System.out.println("Sedang masak...");
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException err) {
                    }
                }
            }
        }
        if (canCook) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Math.round(1.5 * kekenyangan * 1000));
                        System.out.println("masak selesai!");
                        mood = mood + 10;
                        World.addWaktu((int) Math.round(1.5 * kekenyangan));
                        checkKondisiSim();
                        World.checkAllSimTimer((int) Math.round(1.5 * kekenyangan), scan);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            System.out.println("Sedang masak...");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException err) {
            }
        }
    }

    // berkunjung
    public void berkunjung(Scanner scan) {
        boolean isValid = false;
        boolean canVisit = false;
        int idx = 1;
        while (!canVisit) {
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
                canVisit = true;
            } else if (idx > 0 && idx <= World.getListofRumah().size()
                    && World.getListofRumah().get(idx - 1).getNama().equals(rumah.getNama())) {
                System.out.println("Tidak bisa berkunjung ke rumah sendiri!");
                canVisit = false;
                isValid = false;
            } else if (idx < 0 || idx > World.getListofRumah().size()) {
                while (idx < 0 || idx > World.getListofRumah().size()) {
                    System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
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
                        canVisit = true;
                    } else if (idx > 0 && idx <= World.getListofRumah().size()
                            && World.getListofRumah().get(idx - 1).getNama().equals(rumah.getNama())) {
                        System.out.println("Tidak bisa berkunjung ke rumah sendiri!");
                        canVisit = false;
                        isValid = false;
                    } else if (idx < 0 || idx > World.getListofRumah().size()) {
                        continue;
                    } else {
                        canVisit = true;
                        int waktuberkunjung = rumah.getLokasi()
                                .distance(World.getListofRumah().get(idx - 1).getLokasi());
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(waktuberkunjung * 1000);
                                    System.out.println("Berkunjung selesai!");
                                    World.addWaktu(waktuberkunjung);
                                    checkKondisiSim();
                                    World.checkAllSimTimer(waktuberkunjung, scan);
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
                        posisiRuangan = posisiRumah.getListofRuangan().get(0);
                    }
                }
            } else {
                canVisit = true;
                int waktuberkunjung = rumah.getLokasi().distance(World.getListofRumah().get(idx - 1).getLokasi());
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(waktuberkunjung * 1000);
                            System.out.println("Berkunjung selesai!");
                            World.addWaktu(waktuberkunjung);
                            checkKondisiSim();
                            World.checkAllSimTimer(waktuberkunjung, scan);
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
                posisiRuangan = posisiRumah.getListofRuangan().get(0);
            }
        }
    }

    // berpindah ruangan
    public void pindahruangan(Scanner scan) {
        boolean adaruangan = false;
        for (int x = 0; x < 4; x++) {
            if (posisiRuangan.getArrayRuangTerhubung()[x] != 0) {
                adaruangan = true;
            }
        }
        if (adaruangan) {
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
                            System.out.print("-\n");
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
                while (idx < 0 || idx > 4) {
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
                }
                if (idx == 0) {
                    System.out.println("Tidak jadi berpindah ruangan!");
                } else {
                    if (posisiRuangan.getRuangTerhubung(idx - 1) == 0) {
                        if ((idx - 1) == 0) {
                            System.out.println("Tidak ada ruangan di atas\n");
                        }
                        if ((idx - 1) == 1) {
                            System.out.println("Tidak ada ruangan di bawah\n");
                        }
                        if ((idx - 1) == 2) {
                            System.out.println("Tidak ada ruangan di kanan\n");
                        }
                        if ((idx - 1) == 3) {
                            System.out.println("Tidak ada ruangan di kiri\n");
                        }
                    } else {
                        for (Ruangan ruang : posisiRumah.getListofRuangan()) {
                            if (ruang.getRuanganKe() == posisiRuangan.getRuangTerhubung(idx - 1)) {
                                posisiRuangan = ruang;
                            }
                        }
                        System.out.println("Berhasil pindah ke ruangan " + posisiRuangan.getNamaRuangan() + "\n");

                    }

                }

            }
        } else {
            System.out.println("Tidak ada ruangan yang terhubung!\n");
        }
    }

    // melihat inventory
    public void seeinventory() {
        inventory.printInventory();
    }

    // upgrade rumah

    public void upgraderumah(Scanner scan) {
        if (!isUpgradingHouse) 
        {
            if (rumah.equals(posisiRumah))
            {
                if (uang >= 1500) 
                {
                    System.out.println("Ruangan apa yang ingin ditambah ruang tetangganya?");
                    rumah.printListOfRuangan();
                    String namaRuangan = scan.nextLine().toLowerCase();
                    for (int i = 0; i < rumah.getListofRuangan().size(); i++) {
                        if (namaRuangan.equals((rumah.getListofRuangan().get(i)).getNamaRuangan().toLowerCase())) {
                            System.out.print("Nama ruangan baru: ");
                            String namaRuanganBaru = scan.nextLine();
                            System.out.println(
                                    "Pilih sisi pada ruangan " + (rumah.getListofRuangan().get(i)).getNamaRuangan()
                                            + " untuk ditambah ruangan " + namaRuanganBaru);
                            System.out.println(
                                    "Ketik 0 untuk sisi atas, 1 untuk bawah, 2 untuk kanan, atau 3 untuk kiri");
                            int sisi = scan.nextInt();
                            if ((rumah.getListofRuangan().get(i)).getRuangTerhubung(sisi) == 0) {
                                System.out.println("Upgrade rumah berhasil! Silahkan ditunggu untuk pembangunannya!\n");
                                upgradingroomname = namaRuanganBaru;
                                sisiupgradingroom = sisi;
                                ruangterhubungupgrading = i;
                                rumah.addRuangan(namaRuanganBaru, rumah.getListofRuangan().get(i), sisi);
                            } else {
                                System.out.println("Maaf sudah ada ruangan di sisi tersebut");
                            }
                        }
                    }
                } 
                else
                {
                    System.out.println("Maaf, uang kamu tidak cukup untuk melakukan upgrade\n");
                }
            } 
            else 
            {
                System.out.println("Maaf, upgrade rumah hanya bisa dilakukan di rumah pribadiSim ini");
            }
        } 
        else 
        {
            System.out.println("Upgrade rumah sedang berjalan di rumah ini, silahkan ditunggu terlebih dahulu!\n");
        }
    }

    // beli barang
    public void belibarang(Scanner scan) {
        if (barangdibeli == null) {
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
                while (idx < 0 || idx > 21 || uang < getHargaBarang(idx)) {
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
                barangdibeli = getBarang(idx);
                Random random = new Random();
                double randomNumber = 0.1 + ((1.5 - 0.1) * random.nextDouble());
                timerbarangdibeli = (int) (randomNumber * 30);
                System.out.println("Waktu kirim : " + timerbarangdibeli + " detik");
                uang = uang - getHargaBarang(idx);
                System.out.println("Barang berhasil dibeli! Silakan ditunggu untuk pengantarannya!");
            }
        } else {
            System.out.println("Ada barang lain yang sedang diantar! silahkan ditunggu terlebih dahulu!");
        }

    }

    // memindah barang
    public void memindahBarang() {
        if (posisiRuangan.getListofObjek().isEmpty()) {
            System.out.println("Tidak ada objek untuk dipindah!");
        } else {
            if (rumah.getNama().equals(posisiRumah.getNama())) {
                posisiRuangan.printListOfObjek();
                System.out.println("Layout ruangan: ");
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
                barang.setTitikAwal(new Point(x_Current, y_Current));
                boolean berhasil = posisiRuangan.memindahBarang(barang, x_Current, y_Current, x_Baru, y_Baru);
                if (berhasil) {
                    System.out.println(barang.getNamaItem() + " berhasil dipindah");
                    System.out.println("Layout ruangan: ");
                    posisiRuangan.printMatriksRuangan();
                } else {
                    System.out.println("Barang gagal dipindah!");
                }

            }
        }
    }

    // memasang barang
    public void memasangbarang() {
        if (inventory.getJumlah() == 0) {
            System.out.println("Tidak ada item di inventory");
        } else {
            if (rumah.getNama().equals(posisiRumah.getNama())) {
                // Pilih barang di inventory
                System.out.println("Layout ruangan: ");
                posisiRuangan.printMatriksRuangan();
                seeinventory();
                System.out.println("Pilih barang yang ingin dipasang");
                Scanner scan = new Scanner(System.in);
                String namaBarang = scan.nextLine().toLowerCase();
                int idx = 0;
                if (!namaBarang.equals("kasur single") && !namaBarang.equals("kasur queen size")
                        && !namaBarang.equals("kasur king size") && !namaBarang.equals("toilet")
                        && !namaBarang.equals("kompor gas") && !namaBarang.equals("kompor listrik")
                        && !namaBarang.equals("meja dan kursi") && !namaBarang.equals("jam")
                        && !namaBarang.equals("play station") && !namaBarang.equals("lemari buku")
                        && !namaBarang.equals("radio") && !namaBarang.equals("piano")
                        && !namaBarang.equals("televisi")) {
                    System.out.println("Barang yang dipilih tidak bisa dipasang!");
                } else {
                    for (String nama : inventory.getDetails().keySet()) {
                        idx++;
                        if (namaBarang.equals(nama.toLowerCase())) {

                            NonMakanan barang = new NonMakanan(namaBarang);
                            int x;
                            int y;
                            boolean selesai = false;
                            int p = barang.getPanjang();
                            int l = barang.getLebar();
                            while (!selesai) {
                                try {
                                    System.out.println(
                                            barang.getNamaItem() + " (dimensi) : " + barang.getPanjang() + " x "
                                                    + barang.getLebar());
                                    for (int i = 0; i < barang.getLebar(); i++) {
                                        for (int j = 0; j < barang.getPanjang(); j++) {
                                            System.out.print(barang.getKodeJenisBarang());
                                        }
                                        System.out.print("\n");
                                    }
                                    System.out.println(posisiRuangan.getNamaRuangan() + ": ");
                                    printKodeBarang();
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

                                                boolean berhasil = posisiRuangan.memasangBarang(barang, x, y); // true
                                                                                                               // kalau
                                                                                                               // area
                                                                                                               // kosong,
                                                                                                               // false
                                                                                                               // kalau
                                                                                                               // ada
                                                                                                               // barang
                                                                                                               // lain
                                                if (berhasil) {
                                                    inventory.removeItem(barang);
                                                    System.out.println(barang.getNamaItem() + " berhasil dipasang");
                                                    selesai = true;
                                                    System.out.println("Layout ruangan: ");
                                                    posisiRuangan.printMatriksRuangan();
                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println("Pilih koordinat yang valid! (0-5)");
                                            }
                                        }
                                    } else if (pilihan == 2) { // Putar barang
                                        if (barang.getIsHorizontal()) {
                                            barang.setVertikal();
                                            System.out.println("Barang sekarang vertikal!");
                                        } else {
                                            barang.setHorizontal();
                                            System.out.println("Barang sekarang horizontal!");
                                        }
                                    } else if (pilihan == 3) { // Batal
                                        selesai = true;
                                        System.out.println("Berhasil dibatalkan!");
                                    } else {
                                        throw new InputMismatchException();
                                    }
                                } catch (InputMismatchException e) { // input tidak valid
                                    System.out.println("Input invalid (masukan pilihan angka yang tersedia)");
                                }
                            }
                            break;
                        } else if (idx == inventory.getJumlah()) {
                            System.out.println("Anda tidak memiliki barang tersebut");
                        } else {
                            continue;
                        }
                    }
                }
            } else {
                System.out.println("Maaf, anda hanya bisa memasang barang pada rumah pribadi Sim ini");
            }
        }

    }

    // melihat waktu
    public void seetime() {
        int menit = (720 - World.getWaktu()) / 60;
        int detik = (720 - World.getWaktu()) % 60;
        System.out.println("Sisa waktu hari ini :");
        System.out.println(String.valueOf(menit) + " menit " + String.valueOf(detik) + " detik");

        // print waktu sisa untuk beli barang
        if (barangdibeli != null) {
            System.out.println("Sisa waktu pembelian barang :");
            System.out.printf("%-20s %-30s\n", "Barang", "Sisa Waktu Pengiriman");
            menit = timerbarangdibeli / 60;
            detik = timerbarangdibeli % 60;
            System.out.printf("%-20s %-30s\n", barangdibeli.getNamaItem(),
                    menit + " menit " + detik + " detik");
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
                while (choiceaksi > 3 || choiceaksi < 0) {
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
                        System.out.println("removeActiveSim");
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
            } else if (accessed.getNamaItem().equals("televisi")) {
                int choiceaksi = 1;
                isValid = false;
                while (!isValid) {
                    try {
                        System.out.println("Aksi yang bisa dilakukan : ");
                        System.out.println("1. Nonton TV");
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
                            ;
                            System.out.println("Aksi yang bisa dilakukan : ");
                            System.out.println("1. Nonton TV");
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
                    nontonTV(scan);
                }
            }
        }
    }
}
