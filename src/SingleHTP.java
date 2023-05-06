public class SingleHTP {

    //create an object of SingleHTP for Design Pattern Implementation
    private static SingleHTP instance;
 
    //make the constructor private so that this class cannot be
    //instantiated
    private SingleHTP(){}
 
    //Get the only object available
    public static SingleHTP getInstance(){
      if (instance == null){
         synchronized(SingleCommand.class){
            if(instance == null){
                instance = new SingleHTP();
            }
        }
      }
       return instance;
    }
 
    public void showMessage(){
      System.out.printf("\n"+"===============================\t\tHOW TO PLAY\t\t==============================="+"\n\n");
       System.out.println("1.  Pada awal permainan pilih salah satu diantara 3 command, yaitu Start, Help, dan Exit");
       System.out.println("2.  Apabila kamu memilih Help, penjelasannya berada pada Command Guide");
       System.out.println("3.  Apabila kamu memilih Exit, penjelasannya berada pada Command Guide");
       System.out.println("4.  Apabila kamu memilih Start, maka permainan akan dimulai dengan");
       System.out.println("    konfirmasi apakah kamu mau melakukan load file yang sudah pernah disimpan sebelumnya");
       System.out.println(" -  Apabila iya silakan ketik (y), lalu tuliskan nama file yang ingin kamu load");
       System.out.println(" -  Apabila tidak silakan klik (n) dan kamu akan memulai permainan dari awal dengan mode new game");
       System.out.println("5.  Setelah kamu masuk ke dalam permainan dari mode new game, silakan ketikkan nama Sim kamu");
       System.out.println("6.  Game akan berpindah menu menjadi menu game yang berisi 14 command yang dapat kamu gunakan selama permainan");
    }
 }