package Entities;
import Items.Item;
import Stats.Stats;

public class Inventory {
    public Item[] inventory;

    public Inventory(){
        this.inventory = new Item[5]; 
    }

    public Inventory(Item[] inv) {
        this.inventory = new Item[5];

        int count = 0;
        while (count < inventory.length && count < inv.length){ // add the first 5 items, or add all if less than 5 items are in the passed arr.
            this.inventory[count] = inv[count];
            count++;
        }
    }

    public void addItem(Item i) { // find the first empty space, add into it, or do nothing if inventory is full
        int index = findEmptySpace();

        if (index < 0) {
            System.out.println("Inventory full!");
        }
        else {
            this.inventory[index] = i;
        }
    }

    public void removeItem(int index){ // items are deleted from existence
        // don't need to care if the space is already null, we can just set null to null in that case.
        this.inventory[index] = null;
    }

    public Stats getTotalStatUps(){ // access the items to calculate how they affect the stats
        Stats statUps = new Stats(); // 0 Stats

        for (int i = 0; i < this.inventory.length; i++){
            Item curr = this.inventory[i];
            if (curr != null) {
                statUps.statsAdd(curr.getStatUp());
            }
        }

        return statUps;
    }

    public boolean isFull() { // check if inv is full. returns true if full, false if not
        for (int i = 0; i < inventory.length; i++){
            if (inventory[i] == null) {
                return false;
            }
        }

        return true;
    }

    public Item getItem(int index){ // check if index is valid. returns null on invalid index, or the item at a valid index.
        // check within bounds
        if (index < 0 || index > inventory.length) {
            //System.out.println("fucking moron thats not a real number");
            return null;
        }
        // grab if valid
        return inventory[index];
    }

    public void showContents(){
        for (int i = 0; i < this.inventory.length; i++){
            System.out.println(this.inventory[i]);
        }
    }

    private int findEmptySpace() { // returns first empty index, or -1 if full
        for (int i = 0; i < inventory.length; i++){
            if (inventory[i] == null) {
                return i;
            }
        }

        return -1;
    }
}
