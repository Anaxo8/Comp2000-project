import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import Entities.Enemy;
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
        james.addItem(new Items.Armour("banded Helmet", 20, 200));

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
        sword.addActionListener(e -> LoadChar(p, itemDescription, 3));
        shield.addActionListener(e -> LoadChar(p, itemDescription, 4));

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

        KillFeed killFeed = new KillFeed();

        JTextArea killFeedArea = new JTextArea();
        killFeedArea.setBounds(530, 100, 190, 130);
        killFeedArea.setEditable(false);
        killFeedArea.setOpaque(false);
        killFeedArea.setForeground(Color.WHITE); 
        killFeedArea.setFont(new Font("Arial", Font.BOLD, 14));

        // players and enemies UI (temp)
        JTextArea playerArea = new JTextArea();
        JTextArea enemyArea = new JTextArea();
        JLabel combatStatus = new JLabel();

        Player pOne = new Player("pOne", 20, 10, 5);
        Player pTwo = new Player("pTwo", 20, 10, 5);
        Player pThree = new Player("pThree", 10, 5, 1);
        Player pFour = new Player("pFour", 25, 10, 10);

        Enemy eOne = new Enemy("eOne", 20, 5, 5, null);
        Enemy eTwo = new Enemy("eTwo", 15, 5, 5, null);
        Enemy eThree = new Enemy("eThree", 20, 10, 5, null);
        Enemy eFour = new Enemy("eFour", 20, 10, 5, null);

        CombatController controller = new CombatController();

        List<Player> players = new ArrayList<>(List.of(pOne, pTwo, pThree, pFour));
        List<Enemy> enemies = new ArrayList<>(List.of(eOne, eTwo, eThree, eFour));

        playerArea.setEditable(false);
        playerArea.setOpaque(false);
        playerArea.setForeground(Color.WHITE);
        playerArea.setFont(new Font("Arial", Font.BOLD, 14));
        enemyArea.setEditable(false);
        enemyArea.setOpaque(false);
        enemyArea.setForeground(Color.WHITE);
        enemyArea.setFont(new Font("Arial", Font.BOLD, 14));
        combatStatus.setForeground(Color.WHITE);
        combatStatus.setFont(new Font("Arial", Font.BOLD, 14));

        playerArea.setBounds(80, 80, 160, 130);
        enemyArea.setBounds(280, 80, 160, 130);
        combatStatus.setBounds(170, 210, 250, 30);

        // Runs one combat attack every 700ms and refreshes the GUI to show the battle live.
        Timer combatTimer = new Timer(700, e -> {
            controller.oneAttack(players, enemies, killFeed);
            CombatController.CombatStatus status = controller.checkCombatStatus(players, enemies);
            updateCombatDisplay(players, enemies, playerArea, enemyArea, killFeedArea, killFeed, combatStatus, controller.getCurrentRound(), status);

            if(status != CombatController.CombatStatus.ONGOING) {
                ((Timer)e.getSource()).stop();
            }
        });


        frame.add(playerArea);
        frame.add(enemyArea);
        frame.add(combatStatus);

        frame.add(killFeedArea);
        frame.add(border);
        frame.add(bg);
        frame.setVisible(true);

        updateCombatDisplay(players, enemies, playerArea, enemyArea, killFeedArea, killFeed, combatStatus, 0, controller.checkCombatStatus(players, enemies));
        combatTimer.start();

        //Tester.ItemSwapTester();
    }

    // Refreshes the combat UI with current HP, kill feed, round, and combat status.
    public static void updateCombatDisplay(List<Player> players, List<Enemy> enemies, JTextArea playerArea, JTextArea enemyArea,
         JTextArea killFeedArea, KillFeed killFeed, JLabel combatStatus, int round, CombatController.CombatStatus status) {
            String playerText = "PLAYERS\n\n";

            for(Player player : players) {
                playerText += player.getName() + "      HP: " + player.getHp() + "\n";
            }
            playerArea.setText(playerText);

            String enemyText = "ENEMIES\n\n";

            for(Enemy enemy : enemies) {
                enemyText += enemy.getName() + "        HP: " + enemy.getHp() + "\n";
            }
            enemyArea.setText(enemyText);

            String killFeedText = "";
            for(String message : killFeed.getMessages()) {
                killFeedText += message + "\n";
            }
            killFeedArea.setText(killFeedText);

            combatStatus.setText("Round " + round + " - " + status);
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
