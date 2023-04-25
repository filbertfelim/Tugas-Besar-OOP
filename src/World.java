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

    public World(String namaSim)
    {
        listofRumah = new ArrayList<Rumah>();
        listofSim = new ArrayList<Sim>();
        waktu = 0; // detik
        harike = 1;
        matrixWorld = new int[64][64];
        listofRumah.add(new Rumah(new Point(0,0)));
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

    public static Sim getActiveSim()
    {
        return activeSim;
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
    public static void addSim(Scanner scan)
    {
        System.out.print("Nama sim baru : ");
        String nama = scan.nextLine();
        boolean isDuplicate = false;
        for (int i = 0; i < listofSim.size() && !isDuplicate; i++)
        {
            if (listofSim.get(i).getNama().equals(nama))
            {
                isDuplicate = true;
            }
        }
        while (isDuplicate)
        {
            System.out.println("Nama sudah dipakai!");
            System.out.print("Nama sim baru : ");
            nama = scan.nextLine();
            isDuplicate = false;
            for (int i = 0; i < listofSim.size() && !isDuplicate; i++)
            {
                if (listofSim.get(i).getNama().equals(nama))
                {
                    isDuplicate = true;
                }
            }
        }
        if (listofRumah.size() == 64 * 64)
        {
            System.out.println("World sudah penuh!");
        }
        else
        {
            listofRumah.add(new Rumah(new Point((listofRumah.size() % 64),(listofRumah.size() / 64))));
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
    
    public static void changeSim(Scanner scan)
    {
        System.out.println("UBAH SIM\n");
        boolean isValid = false;
        int idx = 1;
        while (!isValid)
        {
            try {
                System.out.println("Daftar sim yang ada :");
                for (int i = 0; i < listofSim.size(); i++)
                {
                    System.out.println(String.valueOf(i + 1) + ". " + listofSim.get(i).getNama());
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
            System.out.println("Sim aktif tidak diubah!");
        }
        else
        {
            while (idx < 0 || idx > listofSim.size())
            {
                System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
                System.out.println("Daftar sim yang ada :");
                for (int i = 0; i < listofSim.size(); i++)
                {
                    System.out.println(String.valueOf(i + 1) + ". " + listofSim.get(i).getNama());
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
                    System.out.println("Sim aktif tidak diubah!");
                }
            }
            if (idx > 0 && idx <= listofSim.size())
            {
                activeSim = listofSim.get(idx - 1);
                System.out.println("Sim aktif berhasil diubah!");
            }
        }
        
    }   

    // public static void changeSimifDead(Scanner scan)
    // {
    //     System.out.println("Daftar sim yang ada :");
    //     for (int i = 0; i < listofSim.size(); i++)
    //     {
    //         System.out.println(String.valueOf(i + 1) + ". " + listofSim.get(i).getNama());
    //     }
    //     boolean isValid = false;
    //     int idx = 1;
    //     while (!isValid)
    //     {
    //         try {
    //             System.out.print("Pilihan : ");
    //             idx = scan.nextInt();
    //             isValid = true;
    //         }
    //         catch (Exception e) {
    //             System.out.println("Input invalid, silahkan input angka!");
    //             scan.nextLine();
    //         }
    //     }
    //     while (idx <= 0 || idx > listofSim.size())
    //     {
    //         System.out.println("Input invalid ( diluar index ), silahkan diulangi!");
    //         System.out.println("Daftar sim yang ada :");
    //         for (int i = 0; i < listofSim.size(); i++)
    //         {
    //             System.out.println(String.valueOf(i + 1) + ". " + listofSim.get(i).getNama());
    //         }
    //         isValid = false;
    //         while (!isValid)
    //         {
    //             try {
    //                 System.out.print("Pilihan : ");
    //                 idx = scan.nextInt();
    //                 isValid = true;
    //             }
    //             catch (Exception e) {
    //                 System.out.println("Input invalid, silahkan input angka!");
    //                 scan.nextLine();
    //             }
    //         }
    //     }
    //     activeSim = listofSim.get(idx - 1);
    //     System.out.println("Sim aktif berhasil diubah!");
    // }


    public static void removeSim()
    {
        int idx = 0;
        boolean isFound = false;
        while (!isFound)
        {
            if (listofSim.get(idx).getNama().equals(activeSim.getNama()))
            {
                isFound = true;
            }
            else
            {
                idx++;
            }
        }
        listofSim.remove(idx);
    }
}
