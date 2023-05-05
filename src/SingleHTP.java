public class SingleHTP {

    //create an object of SingleHTP for Design Pattern Implementation
    private static SingleHTP instance = new SingleHTP();
 
    //make the constructor private so that this class cannot be
    //instantiated
    private SingleHTP(){}
 
    //Get the only object available
    public static SingleHTP getInstance(){
       return instance;
    }
 
    public void showMessage(){
       System.out.println("Pada awal permainan pilih salah satu diantara 3 command, yaitu Start, Help, dan Exit");
       System.out.println("Apabila kamu memilih Help, maka menu akan menampilkan menu Help yang berisi penjelasan dari command");
       System.out.println("Jika kamu memilih Exit, maka permainan selesai");
       System.out.println("Jika kamu memilih Start, maka permainan akan dimulai dengan");
       System.out.println("konfirmasi apakah kamu mau melakukan load file yang sudah pernah disimpan sebelumnya");
       System.out.println("Jika iya silakan ketik (y), lalu tuliskan nama file yang ingin kamu load");
       System.out.println("Jika tidak silakan klik (n) dan kamu akan memulai permainan dari awal dengan mode new game");
       System.out.println("Setelah kamu masuk ke dalam permainan dari mode new game, silakan ketikkan nama Sim kamu");
       System.out.println("Game akan berpindah menu menjadi menu game yang berisi 14 command yang dapat kamu gunakan selama permainan");
    }
 }