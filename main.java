import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

import Entities.Player;
import Items.Item;
class main {
    public static void main(String[] args)
    {
        JButton chestplate = new JButton(new ImageIcon("./images/emptychestplate.png"));
        JButton boots = new JButton(new ImageIcon("./images/emptyboots.png"));
        JButton sword = new JButton(new ImageIcon("./images/emptysword.png"));
        JButton shield = new JButton(new ImageIcon("./images/emptyshield.png"));
        JButton helmet = new JButton(new ImageIcon("./images/emptyhelm.png"));

        helmet.setBounds(100,280,48,48); 
        chestplate.setBounds(100,340,48,48);
        boots.setBounds(100,400,48,48);
        sword.setBounds(40,320, 48, 48);
        shield.setBounds(160, 320, 48, 48);

        JFrame frame = new JFrame();
        Player james = new Player("james", 10, 10, 10);
        james.addArmour(new Items.Armour("banded Helmet", 20, 200, 0));

        initUI(frame, sword, shield, helmet, chestplate, boots, james);

        



    }

    public static void LoadChar(Player p, JTextArea ItemDescription, int index)
    {
        if(p.getInv().getItem(index) == null)
        {
            ItemDescription.setText("");
            System.out.println("NO ITEM EQUIPED");
            return;
        }

        ItemDescription.setText(p.getInv().getItem(index).toString());
        System.out.println("LOADED ITEM");
    }

    public static void initUI(JFrame frame, JButton sword, JButton shield, JButton helmet, JButton chestplate, JButton boots, Player p){

        JLabel border = new JLabel(new ImageIcon("./images/border.png"));
        JLabel bg = new JLabel(new ImageIcon("./images/bg.png"));
        JTextArea itemDescription = new JTextArea();
        itemDescription.setText("DWAODPWAKDA");
        itemDescription.setBounds(300, 300, 400, 130);
        itemDescription.setEditable(false);
        itemDescription.setOpaque(false);
        itemDescription.setForeground(Color.WHITE); 
        itemDescription.setFont(new Font("Arial", Font.BOLD, 14));


        helmet.addActionListener(e -> LoadChar(p, itemDescription, 0));
        chestplate.addActionListener(e -> LoadChar(p, itemDescription, 1));
        boots.addActionListener(e -> LoadChar(p, itemDescription, 2));
        shield.addActionListener(e -> LoadChar(p, itemDescription, 3));
        sword.addActionListener(e -> LoadChar(p, itemDescription, 4));

        border.setBounds(0,0,720,480);
        bg.setBounds(0,0,720,480);
        frame.add(itemDescription);
        frame.add(helmet);
        frame.add(chestplate);
        frame.add(boots);
        frame.add(sword);
        frame.add(shield);
        frame.setSize(730,510);
        frame.setLayout(null);

        KillFeed killFeed = KillFeedTester();

        JTextArea killFeedArea = new JTextArea();
        killFeedArea.setBounds(530, 100, 190, 130);
        killFeedArea.setEditable(false);
        killFeedArea.setOpaque(false);
        killFeedArea.setForeground(Color.WHITE); 
        killFeedArea.setFont(new Font("Arial", Font.BOLD, 14));
        
        for (String message : killFeed.getMessages()) {
            killFeedArea.append(message + "\n");
        }

        frame.add(killFeedArea);
        frame.add(border);
        frame.add(bg);
        frame.setVisible(true);

        Tester.ItemSwapTester();
    }

    public static ImageIcon loadImage(String URL){
        if(URL != null) {
            System.out.println("Loaded image" + URL);
            return new ImageIcon(URL);
        }
        else {
            System.out.println("Failed to load image!");
            return new ImageIcon("./images/fallback.jpg");
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
        try {
        killFeed.addKill("Player", "");
        } catch (IllegalArgumentException e) {
        System.out.println("KillFeed error: " + e.getMessage()); //To avoid terminating the program at IllegalArgumentException
}

        return killFeed;
    }
}
