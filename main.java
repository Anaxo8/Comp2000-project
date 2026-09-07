import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;

class main {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        JLabel helmet = new JLabel(loadImage("./helm1.png"));
        JLabel border = new JLabel(new ImageIcon("./border.png"));
        JLabel bg = new JLabel(new ImageIcon("./bg.png"));

        border.setBounds(0,0,720,480);
        bg.setBounds(0,0,720,480);
        //helmet.setBounds(150,200,220,100); 

        frame.add(border);
        //frame.add(bg);
        frame.add(helmet);
        frame.setSize(730,510);
        frame.setLayout(null);

        

        KillFeed killFeed = KillFeedTester();

        JTextArea killFeedArea = new JTextArea();
        killFeedArea.setBounds(10, 400, 250, 100);
        killFeedArea.setEditable(false);

        for (String message : killFeed.getMessages()) {
            killFeedArea.append(message + "\n");
        }

        frame.add(killFeedArea);
        frame.setVisible(true);

    }

    /* 
    public static void InventoryTester(){
        Item a = new Item("a", 13); // common
        Item b = new Item("b", 26); // uncommon
        Item c = new Item("c", 51); // rare
        Item d = new Item("d", 64); // rare 
        Item e = new Item("e", 76); // legendary
        Item f = new Item("f", 67);

        Item[] list = {a,b,c,d};

        Inventory inv = new Inventory(list);
        inv.addItem(e);

        for (int i = 0; i < inv.inventory.length && inv.inventory[i] != null; i++) {
            System.out.println(inv.inventory[i].getName());
        }

        inv.addItem(f); // fail
    }

    
    public static void ItemTester(){
        Item a = new Item("a", 13); // common
        Item b = new Item("b", 26); // uncommon
        Item c = new Item("c", 51); // rare
        Item d = new Item("d", 64); // rare 
        Item e = new Item("e", 76); // legendary

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
    */

    public static ImageIcon loadImage(String URL){
        if(URL != null) {
            System.out.println("Loaded image" + URL);
            return new ImageIcon(URL);
        }
        else {
            System.out.println("Failed to load image!");
            return new ImageIcon("./fallback.jpg");
        }
    }

    public static KillFeed KillFeedTester() {
        KillFeed killFeed = new KillFeed();

        killFeed.addKill("Player", "Enemy 1");
        killFeed.addKill("Player", "Enemy 2");
        killFeed.addKill("Player", "Enemy 3");
        killFeed.addKill("Player", "Enemy 4");
        killFeed.addKill("Player", "Enemy 5");
        killFeed.addKill("Player", "Enemy 6");

        return killFeed;
    }
}
