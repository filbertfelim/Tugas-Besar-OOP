import java.util.concurrent.ThreadLocalRandom;

public class Pekerjaan {
    private String nama;
    private int gaji;
    private String[] listPekerjaan = {"Badut Sulap", "Koki", "Polisi", "Programmer", "Dokter"};
    private int lamaBekerja = 0;

    public Pekerjaan() throws Exception {
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
        } else {
            throw new Exception("Input tidak ada di daftar kerja!");
        }
    }

    public Pekerjaan(String nama) throws Exception {
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
        } else {
            throw new Exception("Input tidak ada di daftar kerja!");
        }
    }

    public String getNama() {
        return nama;
    }

    public int getGaji() {
        return gaji;
    }

    public int getLamaBekerja() {
        return lamaBekerja;
    }

    public void addLamaBekerja(int num) {
        lamaBekerja += num;
    }

    public boolean ableToChangeWork(int uang, int gajiBaru) {
        if ((lamaBekerja / 60) >= 12 && uang >= (gajiBaru / 2)) {
            return true;
        } else {
            return false;
        }
    }

    public void changeWork(Sim sim, String newWork) throws Exception {
        int gajiBaru;
        if (newWork.equals("Badut Sulap")) {
            gajiBaru = 15;
        } else if (newWork.equals("Koki")) {
            gajiBaru = 30;
        } else if (newWork.equals("Polisi")) {
            gajiBaru = 35;
        } else if (newWork.equals("Programmer")) {
            gajiBaru = 45;
        } else if (newWork.equals("Dokter")) {
            gajiBaru = 50;
        } else {
            throw new Exception("Input tidak ada di daftar kerja!");
        }

        if (ableToChangeWork(sim.getUang(), gajiBaru)) {
            nama = newWork;
            gaji = gajiBaru;
            lamaBekerja = 0;
        }
    }

    public void doKerja(Sim sim, int waktu) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(waktu);
                    addLamaBekerja(waktu);
                    sim.setKekenyangan(sim.getKekenyangan() - (10 * waktu / 30));
                    sim.setMood(sim.getMood() - (10 * waktu / 30));
                    sim.setUang(sim.getUang() + (gaji * waktu / 240));
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        thread.start();
    }
}
