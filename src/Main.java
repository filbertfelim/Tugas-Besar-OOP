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
        List<List<?>> objectsCreatedList = new ArrayList<List<?>>();
        List<World> worldsCreatedList = new ArrayList<World>();
        List<Sim> simsCreatedList = new ArrayList<Sim>();
        List<Rumah> rumahCreatedList = new ArrayList<Rumah>();
        List<Ruangan> ruanganCreatedList = new ArrayList<Ruangan>();
        List<Point> pointsCreatedList = new ArrayList<Point>();
        List<Pekerjaan> pekerjaanCreatedList = new ArrayList<Pekerjaan>();
        List<NonMakanan> nonmakananCreatedList = new ArrayList<NonMakanan>();
        List<Masakan> masakanCreatedList = new ArrayList<Masakan>();
        List<BahanMakanan> bahanmakananCreatedList = new ArrayList<BahanMakanan>();
        List<Inventory> inventoryCreatedList = new ArrayList<Inventory>();

        objectsCreatedList.add(worldsCreatedList);
        objectsCreatedList.add(simsCreatedList);
        objectsCreatedList.add(rumahCreatedList);
        objectsCreatedList.add(ruanganCreatedList);
        objectsCreatedList.add(pointsCreatedList);
        objectsCreatedList.add(pekerjaanCreatedList);
        objectsCreatedList.add(nonmakananCreatedList);
        objectsCreatedList.add(masakanCreatedList);
        objectsCreatedList.add(bahanmakananCreatedList);
        objectsCreatedList.add(inventoryCreatedList);

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
                        // load(loadFile);
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

        boolean isGameFinish = false;
        while (!isGameFinish) {
            if (newGame) {
                // Game baru dimulai
                System.out.print("Nama sim : ");
                String namasim = scan.nextLine();
                World world = new World(namasim);
                World.addSim(scan);
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

            printGameMenu();
            System.out.print(">> ");
            String commandMenu = scan.nextLine().toLowerCase();
            switch (commandMenu) {
                case "1": // Help
                    printHelp();
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
                            save(objectsCreatedList, nameFile);
                            System.out.println("Your game is saved!");
                            savingLoop = true;
                        } else if (save.equals("n")) {
                            savingLoop = true;
                        } else {
                            System.out.println("Please type y/n!");
                        }
                    }
                    exit();
                    break;
                case "3": // View Sim Info
                    World.getActiveSim().getInfo();
                    break;
                case "4": // View Current Location
                    World.getActiveSim().getCurrentLocation();
                    break;
                case "5": // View Inventory
                    World.getActiveSim().getInventory().printInventory();
                    break;
                case "6": // Upgrade House
                case "7": // Move Room
                case "8": // Edit Room
                case "9": // Add Sim
                    World.addSim(scan);
                    break;
                case "10": // Change Sim
                    World.changeSim(scan);
                    break;
                case "11": // List Object
                    World.getActiveSim().getPosisiRuangan().listobject();
                    break;
                case "12": // Go To Object
                    World.getActiveSim().gotoObject(scan);
                    break;
                case "13": // Action

            }

        }

    }

    private static void save(List<List<?>> save, String namaFile) {

        JSONArray daftarObjek = new JSONArray();
        for (List<?> a : save) {
            for (Object b : a) {
                if (b.getClass() == Sim.class) {
                    Sim sim = (Sim) b;
                    JSONObject sims = new JSONObject();
                    sims.put("nama", sim.getNama());
                    sims.put("kekenyangan", sim.getKekenyangan());
                    sims.put("status", sim.getStatus());

                    JSONObject objekSim = new JSONObject();
                    objekSim.put("Sim", sims);
                    daftarObjek.add(objekSim);
                } else if (b.getClass() == World.class) {
                    World world = (World) b;
                    JSONObject worlds = new JSONObject();
                    worlds.put("panjang", world.getPanjang());
                    worlds.put("lebar", world.getLebar());

                    JSONObject objekWorld = new JSONObject();
                    objekWorld.put("World", worlds);
                    daftarObjek.add(objekWorld);
                }
            }
        }

        try (FileWriter file = new FileWriter(namaFile + ".json")) {
            file.write(daftarObjek.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void load(String namaFile) {
        JSONParser jsonP = new JSONParser();

        try (FileReader reader = new FileReader(namaFile + ".json")) {
            // Read JSON File
            Object obj = jsonP.parse(reader);
            JSONArray objList = (JSONArray) obj;
            System.out.println(objList);
            // Iterate over emp array
            objList.forEach(object -> parseSim((JSONObject) object));
            objList.forEach(object -> parseWorld((JSONObject) object));
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

    }

    private static void exit() {
        System.out.println("Goodbye! Lets play again later!");
        System.exit(0);
    }
}
