import java.util.ArrayList;
import java.util.HashMap;

public class Inventory<T> {
    private ArrayList<Item> inventory;
    private HashMap<String, Integer> details;

    public Inventory() {
        inventory = new ArrayList<Item>();
        details = new HashMap<String, Integer>();
    }

    public Inventory(ArrayList<Item> inventory, HashMap<String, Integer> details) {
        this.inventory = inventory;
        this.details = details;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public HashMap<String, Integer> getDetails() {
        return details;
    }

    public int getJumlah() {
        return inventory.size();
    }

    public <T extends Item> void addItem(T item) {
        int idx = 0;
        while (idx < inventory.size()
                && !(((Item) item).getNamaItem().equals(((Item) inventory.get(idx)).getNamaItem()))) {
            idx++;
        }
        if (idx < inventory.size()) // udah ada di inventory
        {
            details.replace(((Item) item).getNamaItem(), details.get(((Item) item).getNamaItem()) + 1);
        } else {
            inventory.add(item);
            details.put(((Item) item).getNamaItem(), 1);
        }
    }

    public <T extends Item> void removeItem(T item) {
        details.replace(((Item) item).getNamaItem(), details.get(((Item) item).getNamaItem()) - 1);
        if (details.get(((Item) item).getNamaItem()) == 0) // jika habis
        {
            details.remove(((Item) item).getNamaItem());
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getNamaItem().equals(item.getNamaItem())) {
                    inventory.remove(i);
                }
            }
        }
    }

    public void printInventory() {
        System.out.println("ISI INVENTORY :");
        int idx = 1;
        for (String nama : details.keySet()) {
            System.out.println(String.valueOf(idx) + ". " + nama + " (" + details.get(nama) + ")");
            idx++;
        }
        System.out.println("");
    }
}
