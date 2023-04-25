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
        System.out.print("Nama sim : ");
        String namasim = scan.nextLine();
        World world = new World(namasim);
        World.addSim(scan);
        World.changeSim(scan);
        System.out.println(World.getActiveSim().getNama());
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
}
