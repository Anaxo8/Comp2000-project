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

    public void addToIndex(Item item, int index) { // overwrite an index
        if (index >= this.inventory.length)
            return;

        this.inventory[index] = item; 
    }

    public void removeItem(int index){ // items are deleted from existence
        // don't need to care if the space is already null, we can just set null to null in that case.
        if (index >= this.inventory.length)
            return;
        
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

    public int indexOf(Item item){
        for (int i = 0; i < this.inventory.length; i++){
            if (this.inventory[i] == item){ // hash comparison should work for this case
                return i;
            }
        }
        return -1; // didnt find it.
    }

    public void showContents(){
        for (int i = 0; i < this.inventory.length; i++){
            System.out.println(this.inventory[i]);
        }
    }

    public boolean isEmpty() {
        for (int i = 0; i < this.inventory.length; i++){
            if (this.inventory[i] != null)
                return false;
        }
        return true;
    }

    public Item getWorst(){
        if (this.isEmpty())
            return null;

        Item worst = this.inventory[0]; // might be null

        for (int i = 0; i < this.inventory.length && worst == null; i++){ // finds first item in the list. there must be at least 1
            Item curr = this.inventory[i];
            if (curr != null){
                worst = curr;
            }
        }

        for (int i = 0; i < this.inventory.length; i++){
            Item curr = this.inventory[i];
            if (curr != null){
                worst = curr.compareWorse(worst); // compares to all the non null indexes. we want the worse one
            }
        }

        return worst;
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
