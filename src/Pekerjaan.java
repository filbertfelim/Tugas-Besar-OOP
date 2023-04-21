import java.util.concurrent.ThreadLocalRandom;

public class Pekerjaan {
    private String nama;
    private int gaji;
    private String[] listPekerjaan = {"Badut Sulap", "Koki", "Polisi", "Programmer", "Dokter"};

    public Pekerjaan() {
        nama = listPekerjaan[ThreadLocalRandom.current().nextInt(0, listPekerjaan.length)];
        if (nama.equals("Badut Sulap")) {
            gaji = 15;
        } else if (nama.equals("Koki")) {
            gaji = 30;
        } else if (nama.equals("Polisi")) {
            gaji = 35;
        } else if (nama.equals("Programmer")) {
            gaji = 45;
        } else if (nama.equals("Dokter")) {
            gaji = 50;
        }
    }

    public Pekerjaan(String nama) {
        this.nama = nama;
        if (nama.equals("Badut Sulap")) {
            gaji = 15;
        } else if (nama.equals("Koki")) {
            gaji = 30;
        } else if (nama.equals("Polisi")) {
            gaji = 35;
        } else if (nama.equals("Programmer")) {
            gaji = 45;
        } else if (nama.equals("Dokter")) {
            gaji = 50;
        }
    }

    public String getNama() {
        return nama;
    }

    public int getGaji() {
        return gaji;
    }
}
