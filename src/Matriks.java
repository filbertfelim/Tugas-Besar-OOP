import java.util.ArrayList;
import java.util.Scanner;;

public class Matriks {

    public class SumbuY {
        private ArrayList<Integer> yPositif = new ArrayList<Integer>(0);
        private ArrayList<Integer> yNegatif = new ArrayList<Integer>(0);
        public SumbuY () {
            
        }
        
        public void setNilaiY(int y, int nilai) {
            if (y >= 0) {
                while (yPositif.size() <= y) {yPositif.add(0);};
                
                yPositif.set(y, nilai);
            } else {
                while (yNegatif.size() <= -(y+1)) {yNegatif.add(0);};
                yNegatif.set(-(y+1), nilai);
            }
        }

        public int getNilaiY(int y) {
            if (y >= 0) {
                //System.out.println(y);
                return yPositif.get(y);
            } else {
                //System.out.print("halo");
                return yNegatif.get(-(y+1));
            }
        }

        public boolean cek(int y) {
            if (y >= 0) {
                if (y+1 > yPositif.size()) {
                    return false;
                } else {
                    return (yPositif.get(y) != 0);
                }
            } else {
                if (-(y+1)+1 > yNegatif.size()) {
                    return false;
                } else {
                    return (yNegatif.get(y) != 0);
                }

            }
        }
    }

    private ArrayList<SumbuY> xPositif = new ArrayList<SumbuY>(0);
    private ArrayList<SumbuY> xNegatif = new ArrayList<SumbuY>(0);

    public void setNilai(int x, int y, int nilai) {
        SumbuY sumbuY = new SumbuY();
        if (x >= 0) {
            while (xPositif.size() <= x) {xPositif.add(sumbuY);};
            xPositif.get(x).setNilaiY(y, nilai);
        } else {
            while (xNegatif.size() <= -(x+1)) {xNegatif.add(sumbuY);};
            xNegatif.get(-(x+1)).setNilaiY(y, nilai);
        }
    
    }

    public int getNilai(int x, int y) {
        if (cekAda(x, y)) {
            //System.out.println("halo");
            if (x>=0) {
                SumbuY sumbuY = xPositif.get(x);
                return sumbuY.getNilaiY(y);
            } else {
                SumbuY sumbuY = xNegatif.get(-(x+1));
                return sumbuY.getNilaiY(y);
            }
        } else {
            return 0;
        }
    }

    public boolean cekAda(int x, int y) {
        SumbuY sumbuY;
        if (x >= 0) {
            if (x + 1 > xPositif.size()) {
                return false;
            } else {
                sumbuY = xPositif.get(x);
            }
        } else {
            if (-(x+1) + 1 > xNegatif.size()) {
                return false;
            } else {
                sumbuY = xNegatif.get(-(x+1));   
            }
        }
        return sumbuY.cek(y);   
    }
    
    public Matriks() {

    }

    
   

    public static void main(String []args) {
        Matriks matriks = new Matriks();
        Scanner sc = new Scanner(System.in);
        int x, y, nilai;
        String perintah;
        while (true){
			perintah = sc.nextLine();
            if (perintah.equals("get")) {
				x = sc.nextInt();
                y = sc.nextInt();
                System.out.println(matriks.getNilai(x, y));
			}
            if (perintah.equals("set")) {
           		x = sc.nextInt();
                y = sc.nextInt();
				nilai = sc.nextInt();      
                matriks.setNilai(x, y, nilai);
                
            }
            if (perintah.equals("cek")) {
            	x = sc.nextInt();
                y = sc.nextInt();
                System.out.println(matriks.cekAda(x, y) ? 
                "ada":"gada");
            }
		}	
    }
    
}


