import java.util.ArrayList;

public class Ruangan {
    private int panjang;
    private int lebar;
    private Point lokasi;
    private static ArrayList<Item> listofItems;

    public Ruangan(Rumah rumah, Point lokasi)
    {
        panjang = 6;
        lebar = 6;
        this.lokasi = lokasi;
        listofItems = new ArrayList<Item>();
    }

    public Point getLokasiRuangan()
    {
        return lokasi;
    }

    public void setLokasiRuangan(Point lokasi)
    {
        this.lokasi = lokasi;
    }

    public int getPanjang()
    {
        return panjang;
    }

    public int getLebar(Point lokasi)
    {
        return lebar;
    }

    public static ArrayList<Item> getListofItems()
    {
        return listofItems;
    }
}
