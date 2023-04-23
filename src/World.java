import java.util.ArrayList;

public class World {
    private static int panjang;
    private static int lebar;
    private static ArrayList<Rumah> listofRumah;
    private static int[][] matrixWorld;
    private static ArrayList<Sim> listofSim;
    private static int waktu;
    private static int harike;
    private static Sim activeSim;

    public World(String namaSim)
    {
        panjang = 64;
        lebar = 64;
        listofRumah = new ArrayList<Rumah>();
        listofSim = new ArrayList<Sim>();
        waktu = 0; // detik
        harike = 1;
        matrixWorld = new int[64][64];
        listofRumah.add(new Rumah());
        listofSim.add(new Sim(namaSim,new Point(0,0)));
        matrixWorld[0][0] = 0;
        activeSim = listofSim.get(0);
    }

    public static int getPanjang()
    {
        return panjang;
    }

    public static int getLebar()
    {
        return lebar;
    }

    public static ArrayList<Rumah> getListofRumah()
    {
        return listofRumah;
    }

    public static int[][] getMatrixWorld()
    {
        return matrixWorld;
    }

    public static ArrayList<Sim> getListofSim()
    {
        return listofSim;
    }

    public static int getWaktu()
    {
        return waktu;
    }

    public static int gethariKe()
    {
        return harike;
    }

    public static void addHari()
    {
        harike++;
    }
    public static void addWaktu(int time)
    {
        if (waktu + time >= 720)
        {
            addHari();
            waktu = waktu + time - 720;
        }
        else
        {
            waktu += time;
        }
    }
    public static void addSim(String nama, Point alamatRumah)
    {
        if (listofRumah.size() == 64 * 64)
        {
            System.out.println("World sudah penuh!");
        }
        else
        {
            listofRumah.add(new Rumah());
            if (listofRumah.size() % 64 == 0)
            {
                listofSim.add(new Sim(nama, new Point((listofRumah.size() % 64) - 1,(listofRumah.size() / 64) - 1)));
            }
            else
            {
                listofSim.add(new Sim(nama, new Point((listofRumah.size() % 64) - 1,listofRumah.size() / 64)));
            }       
        }
    }
    
    public static void changeSim(int idx)
    {
        activeSim = listofSim.get(idx);
    }
}
