import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;
    private ArrayList<Integer> jumlah;

    public Inventory() {
        items = new ArrayList<Item>();
        jumlah = new ArrayList<Integer>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Integer> getJumlah() {
        return jumlah;
    }

    public void add(Item item) {
        boolean check = false;
        int index = -1;

        for (Item i : items) {
            index++;
            if (i.getNamaItem().equals(item.getNamaItem())) {
                check = true;
                break;
            }
        }
        if (check) {
            jumlah.set(index, jumlah.get(index) + 1);
        } else {
            items.add(item);
            jumlah.add(1);
        }
    }

    public void remove(String name) throws Exception {
        boolean check = false;
        int index = -1;

        for (Item i : items) {
            index++;
            if (i.getNamaItem().equals(name)) {
                check = true;
                break;
            }
        }

        if (check) {
            jumlah.set(index, jumlah.get(index) - 1);
            if (jumlah.get(index) == 0) {
                items.remove(index);
                jumlah.remove(index);
            }
        } else {
            throw new Exception("Kamu tidak mempunyai objek tersebut!");
        }
    }

    public void printInventory() {
        for (int i = 0; i < items.size(); i++) {
            System.out.print((items.get(i)).getNamaItem() + ":   ");
            System.out.println(jumlah.get(i));
        }
        // System.out.println("====================");
    }
}
