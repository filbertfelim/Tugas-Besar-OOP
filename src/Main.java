import java.util.Scanner;

public class Main {
    
    public static void main(String []args) {
        boolean exit = false;
        System.out.println("Sim-Plicity");
        System.out.print("1. New World\n2. Load World\n3. Exit");
        Scanner readInput = new Scanner(System.in);
        System.out.print("Enter the number: ");
        String input = readInput.next();
        switch (input) {
            case "1":
                // addSim
                // addWorld
                while(!exit) //gameplay
                {
                    
                }
                break; 
            case "2":
                // Check for avaible world,
                while(!exit) //gameplay
                {
                    
                }
                break;
            case "3":
                System.out.println("Goodbye");
                break;
        }
        

        
    }
    

}
