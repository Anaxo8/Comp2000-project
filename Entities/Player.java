package Entities;
import Items.Item;

public class Player extends Entity {
    public Player(String name, int hp, int atk, int def){
        super(name, hp, atk, def);
    }

    // look at item, get its score
    // find the lowest score item on hand
    // which ones better???
    // keep the better one

    public void swapBetter(Item newItem) { // If inventory is full, replaces the worst held item with the new one. If not full, simply adds it in
        if (!this.inv.isFull()){ // not a full inv
            addItem(newItem);
            return;
        }

        Item worst = this.findWorst();
        int index = this.inv.indexOf(worst); // potential sus

        Item keep = worst.compareBetter(newItem); // keep the better one
        this.inv.addToIndex(keep, index);
        
    }

    private Item findWorst(){
        return this.inv.getWorst();
    }

    // player can search a whole inventory for better items.
}
