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
                        worldsCreatedList.add(load(loadFile));
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
                printHelp(scan);
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
            MusicPlayer music = new MusicPlayer();
            music.plays("opening", 14450);

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
            if (World.getListofSim().size() == 0) {
                isGameFinish = true;
                break;
            }
            printGameMenu();
            System.out.print(">> ");
            String commandMenu = scan.nextLine().toLowerCase();
            switch (commandMenu) {
                case "1": // Help
                    printHelp(scan);
                    delay(2000);

                    System.out.println("Tekan enter untuk lanjut");
                    System.out.println(">> ");
                    String enter = scan.nextLine();
                    break;
                case "2": // Exit
                    boolean savingLoop = false;
                    MusicPlayer musik = new MusicPlayer();
                    musik.plays("Short", 35000);
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
                    World.getActiveSim().getPosisiRuangan().printRuangTerhubung();
                    System.out
                            .println("Posisi ruangan: " + World.getActiveSim().getPosisiRuangan().getTitikRuang().getX()
                                    + "," + World.getActiveSim().getPosisiRuangan().getTitikRuang().getY());
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
                        while (World.getActiveSim().getTimerWaktuKunjung() < World.getActiveSim()
                                .getJatahWaktuBerkunjung()
                                && !World.getActiveSim().getPosisiRumah().getNama()
                                        .equals(World.getActiveSim().getRumah().getNama())) {
                            isValid = false;
                            idx = 1;
                            while (!isValid) {
                                try {
                                    System.out.println("Anda sedang berkunjung di "
                                            + World.getActiveSim().getPosisiRumah().getNama());
                                    System.out.println("Aksi yang dapat dilakukan: ");
                                    System.out.println("1. Kerja");
                                    System.out.println("2. Olahraga");
                                    System.out.println("3. Go to object");
                                    System.out.println("4. View Sim info");
                                    System.out.println("0  Batal");
                                    System.out.print("Pilihan : ");
                                    idx = scan.nextInt();
                                    isValid = true;
                                } catch (Exception e) {
                                    System.out.println("Input invalid, silahkan input angka!");
                                    scan.nextLine();
                                }
                            }
                            while (idx < 0 || idx > 4) {
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
                                        System.out.println("4. View Sim info");
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
                            } else if (idx == 4) {
                                World.getActiveSim().getInfo();
                            }
                        }
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

            // jumlahitem
            simJSON.put("jumlahitem", sim.getInventory().getInventory().size());
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
            if (sim.getBarangDiBeli() != null) {
                simJSON.put("barangdibeli", sim.getBarangDiBeli().getNamaItem());
            } else {
                simJSON.put("barangdibeli", sim.getBarangDiBeli());
            }

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

    private static World load(String namaFile) {
        JSONParser jsonP = new JSONParser();
        World world = new World("Load");
        try (FileReader reader = new FileReader("savefile/" + namaFile + ".json")) {
            // Read JSON File
            Object obj = jsonP.parse(reader);
            JSONArray objList = (JSONArray) obj;
            System.out.println(objList);

            JSONObject worldJSON = (JSONObject) objList.get(0);
            // atribut waktu (World)
            int waktu = Math.toIntExact((Long) worldJSON.get("waktu"));
            // atribut harike (World)
            int harike = Math.toIntExact((Long) worldJSON.get("harike"));

            // jumlah rumah
            int jumlahrumah = Math.toIntExact((Long) worldJSON.get("jumlahrumah"));
            // atribut listofRumah (World)
            ArrayList<Rumah> listofRumah = new ArrayList<Rumah>(jumlahrumah);
            JSONObject listofrumahJSON = (JSONObject) worldJSON.get("listofrumah");
            for (int rumahKe = 1; rumahKe <= jumlahrumah; rumahKe++) {
                // objek Rumah
                JSONObject rumahSimJSON = (JSONObject) listofrumahJSON.get("Rumah " + rumahKe);

                // atribut nama (Rumah)
                // rumahSim.put("nama", rumah.getNama());
                String namaRumah = (String) rumahSimJSON.get("nama");
                // atribut lokasi (Rumah)
                // rumahSim.put("lokasiX", rumah.getLokasi().getX());
                int lokasiX = Math.toIntExact((Long) rumahSimJSON.get("lokasiX"));
                // rumahSim.put("lokasiY", rumah.getLokasi().getY());
                int lokasiY = Math.toIntExact((Long) rumahSimJSON.get("lokasiY"));
                Point lokasi = new Point(lokasiX, lokasiY);

                // jumlah ruangan
                // rumahSim.put("jumlahruangan", rumah.getListofRuangan().size());
                int jumlahruangan = Math.toIntExact((Long) rumahSimJSON.get("jumlahruangan"));
                // atribut listofruangan (Rumah)
                ArrayList<Ruangan> listofRuangan = new ArrayList<Ruangan>();
                JSONObject listofRuanganJSON = (JSONObject) rumahSimJSON.get("listofruangan");
                for (int ruangKe = 1; ruangKe <= jumlahruangan; ruangKe++) {
                    // objek Ruangan
                    JSONObject ruanganRumahSimJSON = (JSONObject) listofRuanganJSON.get("Rumah " + ruangKe);

                    // atribut nama (Ruangan)
                    // ruanganRumahSim.put("nama", ruangan.getNamaRuangan());
                    String namaRuangan = (String) ruanganRumahSimJSON.get("nama");
                    // atribut ruanganke (Ruangan)
                    // ruanganRumahSim.put("ruanganke", ruangan.getRuanganKe());
                    int ruanganKe = Math.toIntExact((Long) ruanganRumahSimJSON.get("ruanganke"));

                    // atribut array ruanganTerhubung (Ruangan)
                    int[] ruangTerhubung = new int[4];
                    JSONObject ruanganTerhubungJSON = (JSONObject) ruanganRumahSimJSON.get("ruanganterhubung");
                    for (int i = 0; i < 4; i++) {
                        // ruanganTerhubung.put(i, ruangan.getRuangTerhubung(i));
                        ruangTerhubung[i] = Math.toIntExact((Long) ruanganTerhubungJSON.get("" + i));
                    }
                    // ruanganRumahSim.put("ruanganterhubung", ruanganTerhubung);

                    // atribut array matrixRuangan (Ruangan)
                    int[][] matrixRuangan = new int[6][6];
                    JSONObject matrixRuanganJSON = (JSONObject) ruanganRumahSimJSON.get("matrixruangan");
                    for (int x = 0; x < 6; x++) {
                        JSONObject isiMatriksJSON = (JSONObject) matrixRuanganJSON.get("" + x);
                        for (int y = 0; y < 6; y++) {
                            // isiMatriks.put(y, ruangan.getMatrixRuangan()[x][y]);
                            matrixRuangan[x][y] = Math.toIntExact((Long) isiMatriksJSON.get("" + y));
                        }

                        // matrixRuangan.put(x, isiMatriks);
                    }
                    // ruanganRumahSim.put("matrixruangan", matrixRuangan);

                    // jumlah objek
                    // ruanganRumahSim.put("jumlahobjek", ruangan.getListofObjek().size());
                    int jumlahobjek = Math.toIntExact((Long) ruanganRumahSimJSON.get("jumlahobjek"));
                    // atribut listofobjek (Ruangan)
                    ArrayList<NonMakanan> listofObjek = new ArrayList<NonMakanan>();
                    JSONObject objekRuanganJSON = (JSONObject) ruanganRumahSimJSON.get("listofobjek");
                    for (int objekKe = 1; objekKe <= jumlahobjek; objekKe++) {
                        // atribut namaItem (NonMakanan)
                        // objekRuanganJSON.put("namaitem " + objekKe, objek.getNamaItem());
                        listofObjek.add(new NonMakanan((String) objekRuanganJSON.get("namaitem " + objekKe)));
                    }
                    // ruanganRumahSim.put("listofobjek", objekRuanganJSON);

                    // atribut titikRuang
                    // ruanganRumahSim.put("titikruangX", ruangan.getTitikRuang().getX());
                    int titikruangX = Math.toIntExact((Long) ruanganRumahSimJSON.get("titikruangX"));
                    // ruanganRumahSim.put("titikruangY", ruangan.getTitikRuang().getY());
                    int titikruangY = Math.toIntExact((Long) ruanganRumahSimJSON.get("titikruangY"));
                    Point titikRuang = new Point(titikruangX, titikruangY);

                    // ruanganJSON.put("Ruangan " + ruangan.getRuanganKe(), ruanganRumahSim);
                    listofRuangan.add(new Ruangan(namaRuangan, ruanganKe, matrixRuangan, listofObjek, ruangTerhubung,
                            titikRuang));
                }
                // rumahSim.put("listofruangan", ruanganJSON);

                listofRumah.add(new Rumah(namaRumah, lokasi, listofRuangan));
            }

            // jumlah sim
            // worldJSON.put("jumlahsim", world.getListofSim().size());
            int jumlahsim = Math.toIntExact((Long) worldJSON.get("jumlahsim"));
            // atribut listofsim (World)
            ArrayList<Sim> listofSim = new ArrayList<Sim>();
            JSONObject simListJSON = (JSONObject) worldJSON.get("listofsim");
            for (int simKe = 1; simKe <= jumlahsim; simKe++) {
                // objek Sim
                JSONObject simJSON = (JSONObject) simListJSON.get("Sim " + simKe);

                // atribut String nama;
                // simJSON.put("nama", sim.getNama());
                String namaSim = (String) simJSON.get("nama");

                // atribut Pekerjaan pekerjaan;
                JSONObject pekerjaanJSON = (JSONObject) simJSON.get("pekerjaan");
                // pekerjaanJSON.put("nama", sim.getPekerjaan().getNama());
                String namaPekerjaan = (String) pekerjaanJSON.get("nama");
                // pekerjaanJSON.put("gaji", sim.getPekerjaan().getGaji());
                int gaji = Math.toIntExact((Long) pekerjaanJSON.get("gaji"));
                // pekerjaanJSON.put("lamabekerja", sim.getPekerjaan().getLamaBekerja());
                int lamabekerja = Math.toIntExact((Long) pekerjaanJSON.get("lamabekerja"));
                // pekerjaanJSON.put("changeworkathari",
                // sim.getPekerjaan().getChangeWorkAtHari());
                int changeworkathari = Math.toIntExact((Long) pekerjaanJSON.get("changeworkathari"));
                // simJSON.put("pekerjaan", pekerjaanJSON);
                Pekerjaan pekerjaan = new Pekerjaan(namaPekerjaan, gaji, lamabekerja, changeworkathari);

                // atribut int uang;
                // simJSON.put("uang", sim.getUang());
                int uang = Math.toIntExact((Long) simJSON.get("uang"));

                // jumlahitem
                // simJSON.put("jumlahitem", sim.getInventory().getInventory().size());
                int jumlahitem = Math.toIntExact((Long) simJSON.get("jumlahitem"));
                // atribut Inventory<Item> inventory;
                Inventory<Item> inventory = new Inventory<Item>();
                JSONObject inventoryJSON = (JSONObject) simJSON.get("inventory");
                // for (String namaItem : sim.getInventory().getDetails().keySet()) {
                // inventoryJSON.put(namaItem, sim.getInventory().getDetails().get(namaItem));
                // }
                for (String namaItem : (Set<String>) inventoryJSON.keySet()) {
                    int jumlahPerItem = Math.toIntExact((Long) inventoryJSON.get(namaItem));
                    for (int i = 0; i < jumlahPerItem; i++) {
                        inventory.addItem(new Item(namaItem));
                    }
                }
                // simJSON.put("inventory", inventoryJSON);

                // atribut int kekenyangan;
                // simJSON.put("kekenyangan", sim.getKekenyangan());
                int kekenyangan = Math.toIntExact((Long) simJSON.get("kekenyangan"));

                // atribut int mood;
                // simJSON.put("mood", sim.getMood());
                int mood = Math.toIntExact((Long) simJSON.get("mood"));

                // atribut int kesehatan;
                // simJSON.put("kesehatan", sim.getKesehatan());
                int kesehatan = Math.toIntExact((Long) simJSON.get("kesehatan"));

                // atribut String status;
                // simJSON.put("status", sim.getStatus());
                String status = (String) simJSON.get("status");

                // atribut Point posisi; // di dalam rumah
                // simJSON.put("posisiX", sim.getPosisi().getX());
                int posisiX = Math.toIntExact((Long) simJSON.get("posisiX"));
                // simJSON.put("posisiY", sim.getPosisi().getY());
                int posisiY = Math.toIntExact((Long) simJSON.get("posisiY"));
                Point posisi = new Point(posisiX, posisiY);

                // atribut Rumah rumah;
                // simJSON.put("rumah", sim.getRumah().getNama());
                String namaRumahSim = (String) simJSON.get("rumah");
                Rumah rumah = new Rumah(namaRumahSim, new Point());
                for (Rumah rumahSim : listofRumah) {
                    if (rumahSim.getNama().equals(namaRumahSim)) {
                        rumah.setLokasi(rumahSim.getLokasi());
                        rumah.setListofRuangan(rumahSim.getListofRuangan());
                    }
                }

                // atribut Rumah posisiRumah;
                // simJSON.put("posisirumah", sim.getPosisiRumah().getNama());
                String namaPosisiRumahSim = (String) simJSON.get("posisirumah");
                Rumah posisiRumah = new Rumah(namaPosisiRumahSim, new Point());
                for (Rumah rumahSim : listofRumah) {
                    if (rumahSim.getNama().equals(namaPosisiRumahSim)) {
                        posisiRumah.setLokasi(rumahSim.getLokasi());
                        posisiRumah.setListofRuangan(rumahSim.getListofRuangan());
                    }
                }

                // atribut Ruangan posisiRuangan;
                // simJSON.put("posisiruangan", sim.getPosisiRuangan().getNamaRuangan());
                String namaPosisiRuangan = (String) simJSON.get("posisiruangan");
                Ruangan posisiRuangan = new Ruangan(namaPosisiRuangan, 0, new Point());
                for (Ruangan ruang : posisiRumah.getListofRuangan()) {
                    if (ruang.getNamaRuangan().equals(namaPosisiRuangan)) {
                        posisiRuangan.setRuanganKe(ruang.getRuanganKe());
                        posisiRuangan.setMatrixRuangan(ruang.getMatrixRuangan());
                        posisiRuangan.setListofObjek(ruang.getListofObjek());
                        posisiRuangan.setTitikRuang(ruang.getTitikRuang());
                        posisiRuangan.setArrayRuangTerhubung(ruang.getArrayRuangTerhubung());
                    }
                }

                // atribut int timerBelumTidur;
                // simJSON.put("timerbelumtidur", sim.getTimerBelumTidur());
                int timerBelumTidur = Math.toIntExact((Long) simJSON.get("timerbelumtidur"));

                // atribut int timerBelumBAB;
                // simJSON.put("timerbelumbab", sim.getTimerBelumBab());
                int timerBelumBAB = Math.toIntExact((Long) simJSON.get("timerbelumbab"));

                // atribut boolean perluBAB;
                // simJSON.put("perlubab", sim.getPerluBab());
                String perluBABstr = (String) simJSON.get("perlubab");
                boolean perluBAB;
                if (perluBABstr.equals("false")) {
                    perluBAB = false;
                } else {
                    perluBAB = true;
                }

                // atribut int jatahWaktuBerkunjung;
                // simJSON.put("jatahwaktuberkunjung", sim.getJatahWaktuBerkunjung());
                int jatahWaktuBerkunjung = Math.toIntExact((Long) simJSON.get("jatahwaktuberkunjung"));

                // atribut int timerWaktuKunjung;
                // simJSON.put("timerwaktukunjung", sim.getTimerWaktuKunjung());
                int timerWaktuKunjung = Math.toIntExact((Long) simJSON.get("timerwaktukunjung"));

                // atribut boolean isBerkunjung;
                // simJSON.put("isberkunjung", sim.getIsBerkunjung());
                String isBerkunjungStr = (String) simJSON.get("isberkunjung");
                boolean isBerkunjung;
                if (isBerkunjungStr.equals("false")) {
                    isBerkunjung = false;
                } else {
                    isBerkunjung = true;
                }

                // atribut Item barangdibeli;
                Item barangdibeli = null;
                String namaBarangDibeli = (String) simJSON.get("barangdibeli");
                if (!namaBarangDibeli.equals("null")) {
                    // simJSON.put("barangdibeli", sim.getBarangDiBeli().getNamaItem());
                    barangdibeli = new Item(namaBarangDibeli);
                } else {
                    // simJSON.put("barangdibeli", sim.getBarangDiBeli());
                }

                // atribut int timerbarangdibeli;
                // simJSON.put("timerbarangdibeli", sim.getTimerBarangDibeli());
                int timerbarangdibeli = Math.toIntExact((Long) simJSON.get("timerbarangdibeli"));

                // simListJSON.put("Sim " + simKe, simJSON);
                listofSim.add(new Sim(namaSim, pekerjaan, uang, inventory, kekenyangan, mood, kesehatan, status, posisi,
                        rumah, posisiRumah, posisiRuangan, timerBelumTidur, timerBelumBAB, perluBAB,
                        jatahWaktuBerkunjung, timerWaktuKunjung, isBerkunjung, barangdibeli, timerbarangdibeli));
            }
            // worldJSON.put("listofsim", simListJSON);

            // atribut ActiveSim (World)
            // worldJSON.put("activesim", world.getActiveSim().getNama());
            String namaActiveSim = (String) worldJSON.get("activesim");
            Sim activeSim = new Sim(namaActiveSim, new Point());
            for (Sim sim : listofSim) {
                if (sim.getNama().equals(namaActiveSim)) {
                    // this.nama = nama;
                    // this.pekerjaan = pekerjaan;
                    activeSim.setPekerjaan(sim.getPekerjaan());
                    // this.uang = uang;
                    activeSim.setUang(sim.getUang());
                    // this.inventory = inventory;
                    activeSim.setInventory(sim.getInventory());
                    // this.kekenyangan = kekenyangan;
                    activeSim.setKekenyangan(sim.getKekenyangan());
                    // this.mood = mood;
                    activeSim.setMood(sim.getMood());
                    // this.kesehatan = kesehatan;
                    activeSim.setKesehatan(sim.getKesehatan());
                    // this.status = status;
                    activeSim.setStatus(sim.getStatus());
                    // this.posisi = posisi;
                    activeSim.setPosisi(sim.getPosisi());
                    // this.rumah = rumah;
                    activeSim.setRumah(sim.getRumah());
                    // this.posisiRumah = posisiRumah;
                    activeSim.setPosisiRumah(sim.getPosisiRumah());
                    // this.posisiRuangan = posisiRuangan;
                    activeSim.setPosisiRuangan(sim.getPosisiRuangan());
                    // this.timerBelumTidur = timerBelumTidur;
                    activeSim.setTimerBelumTidur(sim.getTimerBelumTidur());
                    // this.timerBelumBAB = timerBelumBAB;
                    activeSim.setTimerBelumBab(sim.getTimerBelumBab());
                    // this.perluBAB = perluBAB;
                    activeSim.setPerluBab(sim.getPerluBab());
                    // this.jatahWaktuBerkunjung = jatahWaktuBerkunjung;
                    activeSim.setJatahWaktuBerkunjung(sim.getJatahWaktuBerkunjung());
                    // this.timerWaktuKunjung = timerWaktuKunjung;
                    activeSim.setTimerWaktuKunjung(sim.getTimerWaktuKunjung());
                    // this.isBerkunjung = isBerkunjung;
                    activeSim.setIsBerkunjung(sim.getIsBerkunjung());
                    // this.barangdibeli = barangdibeli;
                    activeSim.setBarangDiBeli(sim.getBarangDiBeli());
                    // this.timerbarangdibeli = timerbarangdibeli;
                    activeSim.setTimerBarangDibeli(sim.getTimerBarangDibeli());
                }
            }

            world.setListofRumah(listofRumah);
            world.setListofSim(listofSim);
            world.setWaktu(waktu);
            world.sethariKe(harike);
            world.setActiveSim(activeSim);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return world;
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

    private static void printHelp(Scanner scan) {
        // print isi help
        int choice = 0;
        boolean isValid = false;
        SingleHTP howToPlay = SingleHTP.getInstance();
        SingleCommand commandGuide = SingleCommand.getInstance();

        System.out.println("Pilih salah satu!");
        System.out.println("1. How To Play");
        System.out.println("2. Command Guide");

        while (!isValid) {
            try {
                System.out.print("Pilihan: ");
                choice = scan.nextInt();
                isValid = true;
            } catch (Exception e) {
                System.out.println("Input invalid, silahkan input angka!");
                scan.nextLine();
            }
        }
        while (choice < 0 || choice > 2) {
            System.out.println("Input invalid ( pilih diantara 2 pilihan ), silahkan diulangi!");
            isValid = false;
            while (!isValid) {
                try {
                    System.out.print("Pilihan");
                    choice = scan.nextInt();
                    isValid = true;
                } catch (Exception e) {
                    System.out.println("Input invalid, silahkan input angka dalam range!");
                    scan.nextLine();
                }
            }
        }
        if (choice == 1) {
            howToPlay.showMessage();
        } else {
            commandGuide.showMessage();
        }

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
