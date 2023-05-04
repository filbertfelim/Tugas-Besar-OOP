import java.util.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.Math;

import java.util.ArrayList;
import java.util.List;

import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Load dan save file belum beres
        List<World> worldsCreatedList = new ArrayList<World>();

        boolean newGame = true;
        boolean isGameStarting = false;
        while (!isGameStarting) {
            printStartingMenuCommands();
            System.out.print(">> ");
            String commandStartingMenu = scan.nextLine().toLowerCase();
            if (commandStartingMenu.equals("start")) {
                isGameStarting = true;

                boolean isGameLoading = false;
                while (!isGameLoading) {
                    System.out.println("Do you want to load saved game? (type y/n)");
                    System.out.print(">> ");
                    String startingGame = scan.nextLine().toLowerCase();
                    if (startingGame.equals("y")) {
                        isGameLoading = true;
                        newGame = false;
                        String loadFile = scan.nextLine().toLowerCase();
                        load(loadFile);
                        System.out.println("Enjoy playing!");
                        delay(2000);
                    } else if (startingGame.equals("n")) {
                        isGameLoading = true;
                        System.out.println("Enjoy playing!");
                        delay(2000);
                    } else {
                        System.out.println("Please type y/n!");
                        delay(2000);
                    }
                }
            } else if (commandStartingMenu.equals("help")) {
                printHelp();
                delay(2000);
            } else if (commandStartingMenu.equals("exit")) {
                exit();
            } else {
                System.out.println("Sorry, wrong command...\n");
                delay(500);
            }
        }

        if (newGame) {
            // Game baru dimulai
            System.out.print("Nama sim : ");
            String namasim = scan.nextLine();
            World world = new World(namasim);
            worldsCreatedList.add(world);
            // World.addSim(scan);
            // World.getActiveSim().getInventory().addItem(new BahanMakanan("nasi"));
            // World.getActiveSim().getInventory().addItem(new BahanMakanan("nasi"));
            // World.getActiveSim().getInventory().addItem(new BahanMakanan("kentang"));
            // World.getActiveSim().getInventory().addItem(new Masakan("nasi ayam"));
            // World.getActiveSim().getInventory().printInventory();
            // World.getActiveSim().getInventory().removeItem(new Masakan("nasi ayam"));
            // World.getActiveSim().getInventory().printInventory();
            // World.getActiveSim().getInventory().removeItem(new BahanMakanan("nasi"));
            // World.getActiveSim().getInventory().printInventory();
        }

        boolean isGameFinish = false;
        while (!isGameFinish) {

            printGameMenu();
            System.out.print(">> ");
            String commandMenu = scan.nextLine().toLowerCase();
            switch (commandMenu) {
                case "1": // Help
                    printHelp();
                    delay(2000);

                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    String enter = scan.nextLine();
                    break;
                case "2": // Exit
                    boolean savingLoop = false;
                    while (!savingLoop) {
                        System.out.println("Do you want to save this game file? (y/n)");
                        System.out.print(">> ");
                        String save = scan.nextLine().toLowerCase();
                        if (save.equals("y")) {
                            System.out.print("Please enter the save file name: ");
                            String nameFile = scan.nextLine().toLowerCase();
                            save(worldsCreatedList, nameFile);
                            System.out.println("Your game is saved!");
                            savingLoop = true;
                        } else if (save.equals("n")) {
                            savingLoop = true;
                        } else {
                            System.out.println("Please type y/n!");
                        }
                    }
                    exit();

                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    enter = scan.nextLine();
                    break;
                case "3": // View Sim Info
                    World.getActiveSim().getInfo();
                    delay(2000);
                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    enter = scan.nextLine();
                    break;
                case "4": // View Current Location
                    World.getActiveSim().getCurrentLocation();
                    delay(2000);
                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    enter = scan.nextLine();
                    break;
                case "5": // View Inventory
                    World.getActiveSim().getInventory().printInventory();
                    delay(2000);
                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    enter = scan.nextLine();
                    break;
                case "6": // Upgrade House
                    World.getActiveSim().upgraderumah(scan);
                    delay(2000);
                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    enter = scan.nextLine();
                    break;
                case "7": // Move Room
                    World.getActiveSim().pindahruangan(scan);
                    delay(2000);
                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    enter = scan.nextLine();
                    break;
                case "8": // Edit Room
                    System.out.println("Pilih salah satu opsi berikut:");
                    System.out.println("1. Meletakkan barang dari inventory");
                    System.out.println("2. Memindahkan barang yang sudah ada di ruangan");
                    int pilihan = scan.nextInt();
                    if (pilihan == 1) {
                        World.getActiveSim().memasangbarang();
                    } else if (pilihan == 2) {
                        World.getActiveSim().memindahBarang();
                    }
                    delay(2000);

                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    enter = scan.nextLine();
                    break;
                case "9": // Add Sim
                    World.addSim(scan);
                    delay(2000);

                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    enter = scan.nextLine();
                    break;
                case "10": // Change Sim
                    World.changeSim(scan);
                    delay(2000);

                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    enter = scan.nextLine();
                    break;
                case "11": // List Object
                    World.getActiveSim().getPosisiRuangan().printListOfObjek();
                    delay(2000);

                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    enter = scan.nextLine();
                    break;
                case "12": // Go To Object
                    World.getActiveSim().gotoObject(scan);
                    delay(2000);

                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    enter = scan.nextLine();
                    break;
                case "13": // Action
                    boolean isValid = false;
                    int idx = 1;
                    while (!isValid) {
                        try {
                            System.out.println("Aksi yang dapat dilakukan: ");
                            System.out.println("1. Kerja");
                            System.out.println("2. Olahraga");
                            System.out.println("3. Berkunjung");
                            System.out.println("0  Batal");
                            System.out.print("Pilihan : ");
                            idx = scan.nextInt();
                            isValid = true;
                        } catch (Exception e) {
                            System.out.println("Input invalid, silahkan input angka!");
                            scan.nextLine();
                        }
                    }
                    while (idx < 0 || idx > 3) {
                        System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
                        isValid = false;
                        while (!isValid) {
                            try {
                                System.out.println("Aksi yang dapat dilakukan: ");
                                System.out.println("1. Kerja");
                                System.out.println("2. Olahraga");
                                System.out.println("3. Berkunjung");
                                System.out.println("0  Batal");
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
                        System.out.println("Berhasil dibatalkan!");
                    } else if (idx == 1) {
                        World.getActiveSim().doKerja(scan);
                    } else if (idx == 2) {
                        World.getActiveSim().olahraga(scan);
                    } else if (idx == 3) {
                        World.getActiveSim().berkunjung(scan);
                        while (World.getActiveSim().getTimerWaktuKunjung() <= World.getActiveSim()
                                .getJatahWaktuBerkunjung()) {
                            isValid = false;
                            idx = 1;
                            while (!isValid) {
                                try {
                                    System.out.println("Anda sedang berkunjung di rumah "
                                            + World.getActiveSim().getPosisiRumah().getNama());
                                    System.out.println("Aksi yang dapat dilakukan: ");
                                    System.out.println("1. Kerja");
                                    System.out.println("2. Olahraga");
                                    System.out.println("3. Go to object");
                                    System.out.println("0  Batal");
                                    System.out.print("Pilihan : ");
                                    idx = scan.nextInt();
                                    isValid = true;
                                } catch (Exception e) {
                                    System.out.println("Input invalid, silahkan input angka!");
                                    scan.nextLine();
                                }
                            }
                            while (idx < 0 || idx > 3) {
                                System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
                                isValid = false;
                                while (!isValid) {
                                    try {
                                        System.out.println("Anda sedang berkunjung di rumah "
                                                + World.getActiveSim().getPosisiRumah().getNama());
                                        System.out.println("Aksi yang dapat dilakukan: ");
                                        System.out.println("1. Kerja");
                                        System.out.println("2. Olahraga");
                                        System.out.println("3. Go to object");
                                        System.out.println("0  Batal");
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
                                System.out.println("Berhasil dibatalkan!");
                            } else if (idx == 1) {
                                World.getActiveSim().doKerja(scan);
                            } else if (idx == 2) {
                                World.getActiveSim().olahraga(scan);
                            } else if (idx == 3) {
                                World.getActiveSim().gotoObject(scan);
                            }
                        }
                        World.getActiveSim().balikdariBerkunjung(scan);
                    }
                    delay(2000);

                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    enter = scan.nextLine();
                    break;
                case "14": // Buy Item
                    World.getActiveSim().belibarang(scan);
                    delay(2000);
                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    enter = scan.nextLine();
                    break;
            }

        }

    }

    private static void save(List<World> save, String namaFile) {

        JSONArray daftarObjek = new JSONArray();
        World world = save.get(0);

        // JSON world
        JSONObject worldJSON = new JSONObject();

        // atribut waktu (World)
        worldJSON.put("waktu", world.getWaktu());
        // atribut harike (World)
        worldJSON.put("harike", world.gethariKe());

        // jumlah rumah
        worldJSON.put("jumlahrumah", world.getListofRumah().size());
        // atribut listofRumah (World)
        JSONObject rumahJSON = new JSONObject();
        int rumahKe = 1;
        for (Rumah rumah : world.getListofRumah()) {
            // objek Rumah
            JSONObject rumahSim = new JSONObject();

            // atribut nama (Rumah)
            rumahSim.put("nama", rumah.getNama());
            // atribut lokasi (Rumah)
            rumahSim.put("lokasiX", rumah.getLokasi().getX());
            rumahSim.put("lokasiY", rumah.getLokasi().getY());

            // jumlah ruangan
            rumahSim.put("jumlahruangan", rumah.getListofRuangan().size());
            // atribut listofruangan (Rumah)
            JSONObject ruanganJSON = new JSONObject();
            for (Ruangan ruangan : rumah.getListofRuangan()) {
                // objek Ruangan
                JSONObject ruanganRumahSim = new JSONObject();

                // atribut nama (Ruangan)
                ruanganRumahSim.put("nama", ruangan.getNamaRuangan());
                // atribut ruanganke (Ruangan)
                ruanganRumahSim.put("ruanganke", ruangan.getRuanganKe());

                // atribut array ruanganTerhubung (Ruangan)
                JSONObject ruanganTerhubung = new JSONObject();
                for (int i = 0; i < ruangan.getArrayRuangTerhubung().length; i++) {
                    ruanganTerhubung.put(i, ruangan.getRuangTerhubung(i));
                }
                ruanganRumahSim.put("ruanganterhubung", ruanganTerhubung);

                // atribut array matrixRuangan (Ruangan)
                JSONObject matrixRuangan = new JSONObject();
                for (int x = 0; x < 6; x++) {
                    JSONObject isiMatriks = new JSONObject();
                    for (int y = 0; y < 6; y++) {
                        isiMatriks.put(y, ruangan.getMatrixRuangan()[x][y]);
                    }

                    matrixRuangan.put(x, isiMatriks);
                }
                ruanganRumahSim.put("matrixruangan", matrixRuangan);

                // jumlah objek
                ruanganRumahSim.put("jumlahobjek", ruangan.getListofObjek().size());
                int objekKe = 1;
                // atribut listofobjek (Ruangan)
                JSONObject objekRuanganJSON = new JSONObject();
                for (NonMakanan objek : ruangan.getListofObjek()) {
                    // atribut namaItem (NonMakanan)
                    objekRuanganJSON.put("namaitem " + objekKe, objek.getNamaItem());
                    objekKe++;
                }
                ruanganRumahSim.put("listofobjek", objekRuanganJSON);

                // atribut titikRuang
                ruanganRumahSim.put("titikruangX", ruangan.getTitikRuang().getX());
                ruanganRumahSim.put("titikruangY", ruangan.getTitikRuang().getY());

                ruanganJSON.put("Ruangan " + ruangan.getRuanganKe(), ruanganRumahSim);
            }
            rumahSim.put("listofruangan", ruanganJSON);

            // atribut matrixRumah (Rumah)
            JSONObject matrixRumah = new JSONObject();
            for (int x = 0; x < rumah.getUkuran(); x++) {
                JSONObject isiMatriks = new JSONObject();
                for (int y = 0; y < rumah.getUkuran(); y++) {
                    isiMatriks.put(y, rumah.getMatrixRumah()[x][y]);
                }

                matrixRumah.put(x, isiMatriks);
            }
            rumahSim.put("matrixrumah", matrixRumah);

            rumahJSON.put("Rumah " + rumahKe, rumahSim);
            rumahKe++;
        }
        worldJSON.put("listofrumah", rumahJSON);

        // jumlah sim
        worldJSON.put("jumlahsim", world.getListofSim().size());
        int simKe = 1;
        // atribut listofsim (World)
        JSONObject simListJSON = new JSONObject();
        for (Sim sim : world.getListofSim()) {
            // objek Sim
            JSONObject simJSON = new JSONObject();

            // atribut String nama;
            simJSON.put("nama", sim.getNama());

            // atribut Pekerjaan pekerjaan;
            JSONObject pekerjaanJSON = new JSONObject();
            pekerjaanJSON.put("nama", sim.getPekerjaan().getNama());
            pekerjaanJSON.put("gaji", sim.getPekerjaan().getGaji());
            pekerjaanJSON.put("lamabekerja", sim.getPekerjaan().getLamaBekerja());
            pekerjaanJSON.put("changeworkathari", sim.getPekerjaan().getChangeWorkAtHari());
            simJSON.put("pekerjaan", pekerjaanJSON);

            // atribut int uang;
            simJSON.put("uang", sim.getUang());

            // atribut Inventory<Item> inventory;
            JSONObject inventoryJSON = new JSONObject();
            for (String namaItem : sim.getInventory().getDetails().keySet()) {
                inventoryJSON.put(namaItem, sim.getInventory().getDetails().get(namaItem));
            }
            simJSON.put("inventory", inventoryJSON);

            // atribut int kekenyangan;
            simJSON.put("kekenyangan", sim.getKekenyangan());

            // atribut int mood;
            simJSON.put("mood", sim.getMood());

            // atribut int kesehatan;
            simJSON.put("kesehatan", sim.getKesehatan());

            // atribut String status;
            simJSON.put("status", sim.getStatus());

            // atribut Point posisi; // di dalam rumah
            simJSON.put("posisiX", sim.getPosisi().getX());
            simJSON.put("posisiY", sim.getPosisi().getY());

            // atribut Rumah rumah;
            simJSON.put("rumah", sim.getRumah().getNama());

            // atribut Rumah posisiRumah;
            simJSON.put("posisirumah", sim.getPosisiRumah().getNama());

            // atribut Ruangan posisiRuangan;
            simJSON.put("posisiruangan", sim.getPosisiRuangan().getNamaRuangan());

            // atribut int timerBelumTidur;
            simJSON.put("timerbelumtidur", sim.getTimerBelumTidur());

            // atribut int timerBelumBAB;
            simJSON.put("timerbelumbab", sim.getTimerBelumBab());

            // atribut boolean perluBAB;
            simJSON.put("perlubab", sim.getPerluBab());

            // atribut int jatahWaktuBerkunjung;
            simJSON.put("jatahwaktuberkunjung", sim.getJatahWaktuBerkunjung());

            // atribut int timerWaktuKunjung;
            simJSON.put("timerwaktukunjung", sim.getTimerWaktuKunjung());

            // atribut boolean isBerkunjung;
            simJSON.put("isberkunjung", sim.getIsBerkunjung());

            // atribut Item barangdibeli;
            simJSON.put("barangdibeli", sim.getBarangDiBeli().getNamaItem());

            // atribut int timerbarangdibeli;
            simJSON.put("timerbarangdibeli", sim.getTimerBarangDibeli());

            simListJSON.put("Sim " + simKe, simJSON);
            simKe++;
        }
        worldJSON.put("listofsim", simListJSON);

        // atribut ActiveSim (World)
        worldJSON.put("activesim", world.getActiveSim().getNama());

        daftarObjek.add(worldJSON);

        try (FileWriter file = new FileWriter("savefile/" + namaFile + ".json")) {
            file.write(daftarObjek.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void load(String namaFile) {
        JSONParser jsonP = new JSONParser();

        try (FileReader reader = new FileReader("savefile/" + namaFile + ".json")) {
            // Read JSON File
            Object obj = jsonP.parse(reader);
            JSONArray objList = (JSONArray) obj;
            System.out.println(objList);
            // Iterate over emp array
            objList.forEach(object -> parseSim((JSONObject) object));
            objList.forEach(object -> parseWorld((JSONObject) object));

            JSONObject world = (JSONObject) objList.get(0);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseSim(JSONObject object) {
        try {
            JSONObject simObj = (JSONObject) object.get("Sim");

            String nama = (String) simObj.get("nama");
            Pekerjaan pekerjaan;
            int uang = Math.toIntExact((Long) simObj.get("uang"));
            Inventory inventory;
            int kekenyangan = Math.toIntExact((Long) simObj.get("kekenyangan"));
            int mood = Math.toIntExact((Long) simObj.get("mood"));
            int kesehatan = Math.toIntExact((Long) simObj.get("kesehatan"));
            String status = (String) simObj.get("status");
            Point posisi; // di dalam rumah
            Rumah rumah;
            Rumah posisiRumah;
            Ruangan posisiRuangan;

        } catch (NullPointerException e) {

        }

    }

    private static void parseWorld(JSONObject object) {
        try {
            JSONObject worldObj = (JSONObject) object.get("World");

            int panjang = Math.toIntExact((Long) worldObj.get("panjang"));
            int lebar = Math.toIntExact((Long) worldObj.get("lebar"));
            ArrayList<Rumah> listofRumah;
            int[][] matrixWorld;
            ArrayList<Sim> listofSim;
            int waktu = Math.toIntExact((Long) worldObj.get("waktu"));
            int harike = Math.toIntExact((Long) worldObj.get("harike"));
            Sim activeSim;
        } catch (NullPointerException e) {

        }

    }

    private static void printStartingMenuCommands() {
        System.out.println("Select Command:");
        System.out.println(">>> Start");
        System.out.println(">>> Help");
        System.out.println(">>> Exit");
    }

    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {

        }
    }

    private static void printHelp() {
        // print isi help
    }

    private static void printGameMenu() {
        System.out.println("Game Menu:");
        System.out.println("1. Help");
        System.out.println("2. Exit");
        System.out.println("3. View Sim Info");
        System.out.println("4. View Current Location");
        System.out.println("5. View Inventory");
        System.out.println("6. Upgrade House");
        System.out.println("7. Move Room");
        System.out.println("8. Edit Room");
        System.out.println("9. Add Sim");
        System.out.println("10. Change Sim");
        System.out.println("11. List Object");
        System.out.println("12. Go To Object");
        System.out.println("13. Action");
        System.out.println("14. Buy Item");

    }

    private static void exit() {
        System.out.println("Goodbye! Lets play again later!");
        System.exit(0);
    }
}
