import java.util.ArrayList;
import java.util.Scanner;

public class World {
    private static final int panjang = 64;
    private static final int lebar = 64;
    private static ArrayList<Rumah> listofRumah;
    private static int[][] matrixWorld;
    private static ArrayList<Sim> listofSim;
    private static int waktu;
    private static int harike;
    private static Sim activeSim;

    public World(String namaSim) {
        listofRumah = new ArrayList<Rumah>();
        listofSim = new ArrayList<Sim>();
        waktu = 0; // detik
        harike = 1;
        matrixWorld = new int[64][64];
        listofRumah.add(new Rumah(namaSim, new Point(0, 0)));
        listofSim.add(new Sim(namaSim, new Point(0, 0)));
        matrixWorld[0][0] = 0;
        activeSim = listofSim.get(0);

        activeSim.getInventory().addItem(new NonMakanan("kasur single"));
        activeSim.getInventory().addItem(new NonMakanan("toilet"));
        activeSim.getInventory().addItem(new NonMakanan("kompor gas"));
        activeSim.getInventory().addItem(new NonMakanan("meja dan kursi"));
        activeSim.getInventory().addItem(new NonMakanan("jam"));
    }

    // untuk fitur load
    public World(ArrayList<Rumah> listofRumah, int[][] matrixWorld, ArrayList<Sim> listofSim, int waktu, int harike,
            Sim activeSim) {
        this.listofRumah = listofRumah;
        this.matrixWorld = matrixWorld;
        this.listofSim = listofSim;
        this.waktu = waktu;
        this.harike = harike;
        this.activeSim = activeSim;
    }

    public static int getPanjang() {
        return panjang;
    }

    public static int getLebar() {
        return lebar;
    }

    public static Sim getActiveSim() {
        return activeSim;
    }

    public static ArrayList<Rumah> getListofRumah() {
        return listofRumah;
    }

    public static int[][] getMatrixWorld() {
        return matrixWorld;
    }

    public static ArrayList<Sim> getListofSim() {
        return listofSim;
    }

    public static int getWaktu() {
        return waktu;
    }

    public static int gethariKe() {
        return harike;
    }

    public static boolean isRumah(int x, int y) {
        int idx = 0;
        boolean isFound = false;

        while ((!isFound) || (idx < listofRumah.size())) {
            if (listofRumah.get(idx).getLokasi().getX() == x) {
                if (listofRumah.get(idx).getLokasi().getY() == y) {
                    isFound = true;
                } else {
                    idx++;
                }
            } else {
                idx++;
            }
        }
        return isFound;
    }

    public static void printPeta() {
        System.out.println("_________________");
        for (int y = 0; y < lebar; y++) {
            String line = "|";
            for (int x = 0; x < panjang; x++) {
                if (!isRumah(x, y)) {
                    line += "_";
                } else {
                    line += "A";
                }
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("_________________\n");
    }

    public static void addHari() {
        harike++;
    }

    public static void addWaktu(int time) {
        if (waktu + time >= 720) {
            addHari();
            waktu = waktu + time - 720;
        } else {
            waktu += time;
        }
    }

    public static void addSim(Scanner scan) {
        System.out.print("Nama sim baru : ");
        String nama = scan.nextLine();
        boolean isDuplicate = false;
        for (int i = 0; i < listofSim.size() && !isDuplicate; i++) {
            if (listofSim.get(i).getNama().equals(nama)) {
                isDuplicate = true;
            }
        }
        while (isDuplicate) {
            System.out.println("Nama sudah dipakai!");
            System.out.print("Nama sim baru : ");
            nama = scan.nextLine();
            isDuplicate = false;
            for (int i = 0; i < listofSim.size() && !isDuplicate; i++) {
                if (listofSim.get(i).getNama().equals(nama)) {
                    isDuplicate = true;
                }
            }
        }
        if (listofRumah.size() == 64 * 64) {
            System.out.println("World sudah penuh!");
        } else {
            listofRumah.add(new Rumah(nama, new Point((listofRumah.size() % 64), (listofRumah.size() / 64))));
            if (listofRumah.size() % 64 == 0) {
                listofSim.add(new Sim(nama, new Point((listofRumah.size() % 64) - 1, (listofRumah.size() / 64) - 1)));
            } else {
                listofSim.add(new Sim(nama, new Point((listofRumah.size() % 64) - 1, listofRumah.size() / 64)));
            }
        }
    }

    public static void changeSim(Scanner scan) {
        System.out.println("UBAH SIM\n");
        boolean isValid = false;
        int idx = 1;
        while (!isValid) {
            try {
                System.out.println("Daftar sim yang ada :");
                for (int i = 0; i < listofSim.size(); i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + listofSim.get(i).getNama());
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
            System.out.println("Sim aktif tidak diubah!");
        } else {
            while (idx < 0 || idx > listofSim.size()) {
                System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
                System.out.println("Daftar sim yang ada :");
                for (int i = 0; i < listofSim.size(); i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + listofSim.get(i).getNama());
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
                    System.out.println("Sim aktif tidak diubah!");
                }
            }
            if (idx > 0 && idx <= listofSim.size()) {
                activeSim = listofSim.get(idx - 1);
                System.out.println("Sim aktif berhasil diubah!");
            }
        }

    }

    public static void removeActiveSim() {
        System.out.println("Sim " + activeSim.getNama() + " telah mati!");
        int idx = 0;
        boolean isFound = false;
        while (!isFound) {
            if (listofSim.get(idx).getNama().equals(activeSim.getNama())) {
                isFound = true;
            } else {
                idx++;
            }
        }
        listofSim.remove(idx);
    }

    public static void removeSim(Sim sim) {
        int idx = 0;
        boolean isFound = false;
        while (!isFound) {
            if (listofSim.get(idx).getNama().equals(sim.getNama())) {
                isFound = true;
            } else {
                idx++;
            }
        }
        listofSim.remove(idx);
    }

    public static void checkAllSimTimer(int duration, Scanner scan) {
        for (Sim sim : listofSim) {
            sim.addTimerBelumTidur(duration);
            sim.resetTimerBelumTidurAfterNoSleep();
            sim.addTimerBelumBAB(duration);
            sim.resetTimerBelumBAB();
            sim.addTimerWaktuKunjung(duration);
            sim.balikdariBerkunjung(scan);
            sim.addTimerBeliBarang(duration);
            sim.checkkirimBarang();
            if (sim.isDead()) {

                if (sim.getNama().equals(getActiveSim().getNama())) {
                    World.removeActiveSim();
                    World.changeSim(scan);
                } else {
                    World.removeSim(sim);
                }
            }
        }
    }
}
