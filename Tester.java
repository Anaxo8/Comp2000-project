import java.util.ArrayList;
import java.util.List;

import Entities.Enemy;
import Entities.Inventory;
import Entities.Player;
import Items.Armour;
import Items.Item;
import Items.Weapon;

public class Tester {
    // class existing to hold the tester methods

    public static void EntityTester(){ 
        // Entity can damage another entity
        Player one = new Player("one", 20, 10, 5);
        Enemy two = new Enemy("two", 20, 10 ,5, null);

        one.attack(two);
        System.out.printf("Expected %d, got %d\n", 15, two.getHp());
        one.attack(two);
        System.out.printf("Expected %d, got %d\n", 10, two.getHp());

        two.attack(one);
        System.out.printf("Expected %d, got %d\n", 15, one.getHp());

        // Entity can pick up items
        Item a = new Weapon("a", 13, 3); // common
        Item b = new Armour("b", 26, 6); // uncommon
        Inventory oneInv = one.getInv();

        oneInv.showContents();
        System.out.println("--------------");

        one.addItem(a);
        oneInv.showContents();
        System.out.println("--------------");

        one.addItem(b);
        oneInv.showContents();
        System.out.println("--------------");

        oneInv.removeItem(0);
        oneInv.showContents();
        System.out.println("--------------");
        // appears to work

        // Entity can apply stat ups
        oneInv.removeItem(1);
        one.addItem(a);
        one.addItem(b);

        System.out.println(one.getStats().toString());
        one.getStatUps();
        System.out.println(one.getStats().toString());
        one.applyStatUps();
        System.out.println(one.getStats().toString());
        one.removeStatUps();
        System.out.println(one.getStats().toString());
    }

    public static void ItemSwapTester(){ 
        Item a = new Weapon("a", 13, 3); // common
        Item b = new Armour("b", 26, 6); // uncommon
        Item c = new Weapon("c", 51, 10); // rare

        Player one = new Player("one", 20, 10, 5);

        one.addItem(a);
        one.addItem(b);
        Inventory oneInv = one.getInv();

        oneInv.showContents();
        System.out.println("---------");

        one.swapBetter(c);
        oneInv.showContents(); // c should be in a's place, b should be untouched
        System.out.println("---------");

        Item g = new Weapon("a", 13, 3); // common
        Item h = new Armour("b", 26, 6); // uncommon
        Item i = new Weapon("c", 51, 10); // rare

        one.swapBetter(g);
        oneInv.showContents();
        System.out.println("---------");

        one.swapBetter(h);
        oneInv.showContents();
        System.out.println("---------");

        one.swapBetter(i);
        oneInv.showContents(); // fourth item is now "c"
        System.out.println("---------");

        Item j = new Weapon("c", 51, 10); // rare
        one.swapBetter(j);
        oneInv.showContents(); // 1st item is now "c"
        System.out.println("---------");

        Item k = new Weapon("c", 51, 10); // rare
        one.swapBetter(k);
        oneInv.showContents(); // 5th item is now "c"
        System.out.println("---------");

        Item l = new Weapon("c", 51, 10); // rare
        one.swapBetter(l);
        oneInv.showContents(); // 2nd item is now "c" (all Cs)
        System.out.println("---------");

        Item m = new Weapon("c", 51, 10); // rare
        one.swapBetter(m);
        oneInv.showContents(); // no change
        System.out.println("---------");

        one.getInv().removeItem(1);
        oneInv.showContents(); // no change
        System.out.println("---------");

        Item n = new Weapon("c", 50, 10); // rare
        one.swapBetter(n);
        oneInv.showContents(); // no change
        System.out.println("---------");

    }

    public static void InventoryTester(){
        Item a = new Weapon("a", 13, 3); // common
        Item b = new Armour("b", 26, 6); // uncommon
        Item c = new Weapon("c", 51, 10); // rare
        Item d = new Armour("d", 64, 14); // rare 
        Item e = new Weapon("e", 76, 20); // legendary
        Item f = new Armour("f", 67, 16);

        Item[] list = {a,b,c,d};

        Inventory inv = new Inventory(list);
        inv.addItem(e);

        for (int i = 0; i < inv.inventory.length && inv.inventory[i] != null; i++) {
            System.out.println(inv.inventory[i].getName());
        }

        inv.addItem(f); // fail
    }

    public static void minimumDmgTester(){
        Player one = new Player("one", 20, 10, 6);
        Enemy two = new Enemy("two", 20, 5, 5, null);

        two.attack(one);
        System.out.println(one.getHp());
    }

    public static void ItemTester(){
        Item a = new Weapon("a", 13, 3); // common
        Item b = new Armour("b", 26, 6); // uncommon
        Item c = new Weapon("c", 51, 10); // rare
        Item d = new Armour("d", 64, 14); // rare 
        Item e = new Weapon("e", 76, 20); // legendary

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(a);
        items.add(b);
        items.add(c);
        items.add(d);
        items.add(e);

        for (Item item : items) {
            System.out.println(item.getRarity());
        }
    }

    public static void combatControlTester() {
        Player pOne = new Player("one", 10, 5, 0);
        Player pTwo = new Player("two", 20, 10, 5);
        Player pThree = new Player("three", 20, 10, 5);
        Player pFour = new Player("four", 20, 15, 5);

        Enemy eOne = new Enemy("eOne", 10, 10, 5, null);
        Enemy eTwo = new Enemy("eTwo", 20, 5, 5, null);
        Enemy eThree = new Enemy("eThree", 20, 5, 5, null);
        Enemy eFour = new Enemy("eFour", 20, 15, 5, null);

        List<Player> pList = new ArrayList<>(List.of(pOne, pTwo, pThree, pFour));
        List<Enemy> eList = new ArrayList<>(List.of(eOne, eTwo, eThree, eFour));
        KillFeed killfeed = new KillFeed();

        CombatController c = new CombatController();

        System.out.println(c.runCombat(pList, eList, killfeed));

        System.out.println("pOne: " + pOne.getHp());
        System.out.println("pTwo: " + pTwo.getHp());
        System.out.println("pThree: " + pThree.getHp());
        System.out.println("pFour: " + pFour.getHp());

        System.out.println("eOne: " + eOne.getHp());
        System.out.println("eTwo: " + eTwo.getHp());
        System.out.println("eThree: " + eThree.getHp());
        System.out.println("eFour: " + eFour.getHp());

        System.out.println(killfeed.getMessages());
    }
}
