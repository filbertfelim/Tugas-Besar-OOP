import java.util.*;

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
}
