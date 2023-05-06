public class SingleCommand {

    //create an object of SingleCommand for Design Pattern Implementation
    private static SingleCommand instance;
 
    //make the constructor private so that this class cannot be
    //instantiated
    private SingleCommand(){}
 
    //Get the only object available
    public static SingleCommand getInstance(){
        if(instance == null){
            synchronized(SingleCommand.class){
                if(instance == null){
                    instance = new SingleCommand();
                }
            }
        }
       return instance;
    }
 
    public void showMessage(){
        System.out.printf("\n"+"===============================\t\tCOMMAND GUIDE\t\t==============================="+"\n\n");
        System.out.println("1.  Help akan meminta kamu memilih 2 pilihan antara How To Play dan Command Guide");
        System.out.println("2.  Exit akan mengakhiri permainan");
        System.out.println("3.  View Sim Info akan menampilkan profil dan stats dari Sim yang sedang dimainkan");
        System.out.println("4.  View Current Location akan menampilkan lokasi persis dari Sim yang sedang dimainkan");
        System.out.println("5.  View Inventory akan menampilkan barang apa yang dimiliki Sim yang sedang dimainkan ");
        System.out.println("6.  Upgrade House akan meminta kamu untuk memasukkan ruangan apa yang akan ditambahkan ruangan tetangga,");
        System.out.println("    sehingga dapat dikatakan bahwa menu Upgrade House berarti menambah ruangan dalam rumah");
        System.out.println("7.  Move Room akan memindahkan ruangan yang saling terhubung agar dapat dpindahkan");
        System.out.println("8.  Edit Room akan meminta kamu untuk memilih 2 opsi yaitu 1. Meletakkan barang dari inventory dan");
        System.out.println("    2. Memindahkan barang yang sudah ada di ruangan. ");
        System.out.println(" -  Jika kamu memilih untuk meletakkan barang dari inventory menu akan berpindah ke menu iventory yang");
        System.out.println("    akan menampilkan keseluruhan inventory milikmu");
        System.out.println("    Kemudian silakan pilih barang apa yang akam kamu taruh di dalam ruangan");
        System.out.println(" -  Jika kamu memilih memindahkan barang yang sudah ada di ruangan, maka kamu akan diminta untuk memasukkan");
        System.out.println("    koordinat lokasi penempatan barang yang baru");
        System.out.println("9.  Add Sim akan meminta kamu untuk memasukkan nama SIm yang baru untuk dapat dimainkan");
        System.out.println("10. Change Sim akan meminta kamu untuk memasukkan nama Sim yang sudah dibuat sebelumnya untuk dapat diganti");
        System.out.println("11. List Of Object akan menampilkan barang apa saja yang ada di dalam ruangan");
        System.out.println("12. Go To Object akan meminta kamu memilih untuk menuju ke barang apa yang berada di ruangan untuk dapat dilakukan aksi,");
        System.out.println("    jangan lupa barang harus ada di ruangan terlebih dahulu agar dapat didekati!");
        System.out.println("13. Action akan meminta kamu untuk memilih untuk melakukan aksi apa terhadap object yang ada di dekatmu");
        System.out.println("14. Buy Item akan meminta kamu untuk memilih barang apa yang ingin kamu beli, pastikan kamu ada uang yang cukup yaa!");
        System.out.println("***Selamat bermain!!***");
    }
 }