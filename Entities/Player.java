package Entities;
import Items.*;

public class Player extends Entity {
    public Player(String name, int hp, int atk, int def){
        super(name, hp, atk, def);
    }

    // look at item, get its score
    // find the lowest score item on hand
    // which ones better???
    // keep the better one
    /* deprecated 
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
    */

    public void swapBetterArmour(Armour newItem){
        // find position of item
        // compare to one equipped in that position
        // keep better one
        if (newItem == null)
            return;

        Armour heldItem = null;
        switch (newItem.getPosition()){
            case HELM:
                //i 0
                heldItem = this.inv.getArmour(0);
            break;

            case CHEST:
                // i 1
                heldItem = this.inv.getArmour(1);
                break;
            
            case BOOTS:
                // i 2
                heldItem = this.inv.getArmour(2);
                break;

            case SHIELD:
                // i 3
                heldItem = this.inv.getArmour(3);
                break;
        }

        if (heldItem == null) {
            this.inv.addArmour(newItem);
        }
        // neither are null also they are the correct type :)

        this.inv.addArmour((Armour)heldItem.compareBetter(newItem));
    }

    public void swapBetterWeapon(Weapon newItem){
        // check index 4
        // compare to new one
        // keep better one
        if (newItem == null)
            return; 

        Weapon heldItem = this.inv.getWeapon();

        if (heldItem == null){
            this.inv.addWeapon(newItem);
        }
        // neither are null. also they must both be weapons

        this.inv.addWeapon((Weapon)heldItem.compareBetter(newItem));
    }

 

    // player can search a whole inventory for better items.
}
