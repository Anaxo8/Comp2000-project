import Entities.Enemy;
import Entities.Player;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import javax.swing.*;


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

        Dungeon dungeon = new Dungeon();
        dungeon.generate(5);

        Player pOne = new Player("pOne", 20, 10, 5);
        Player pTwo = new Player("pTwo", 20, 10, 5);
        Player pThree = new Player("pThree", 15, 5, 1);
        Player pFour = new Player("pFour", 20, 10, 10);
        List<Player> players = new ArrayList<>(Arrays.asList(pOne, pTwo, pThree, pFour));

        initUI(frame, sword, shield, helmet, chestplate, boots, pOne, dungeon, players);
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

    public static void initUI(JFrame frame, JButton sword, JButton shield, JButton helmet, JButton chestplate, JButton boots, Player p, Dungeon dungeon, List<Player> plist   ){

        JLabel border = new JLabel(new ImageIcon("./images/border.png"));
        JLabel bg = new JLabel(new ImageIcon("./images/bg.png"));
        JTextArea itemDescription = new JTextArea();
        itemDescription.setText("");
        itemDescription.setLineWrap(true);
        itemDescription.setBounds(300, 300, 130, 130);
        itemDescription.setEditable(false);
        itemDescription.setOpaque(false);
        itemDescription.setForeground(Color.WHITE); 
        itemDescription.setFont(new Font("Arial", Font.BOLD, 14));

        Player pOne = plist.get(0);
        Player pTwo = plist.get(1);
        Player pThree = plist.get(2);
        Player pFour = plist.get(3);

        JLabel gearscore = new JLabel();
        gearscore.setForeground(Color.WHITE);
        gearscore.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(gearscore);
        gearscore.setBounds(300,275,200,30);

        JButton[] gearslots = {helmet, chestplate, boots, sword, shield};

        PlayerSelection selection = new PlayerSelection();
        selection.registerobserver(new gearslotobserver(gearslots));
        selection.registerobserver(new gearscoreobserver(gearscore));

        helmet.addActionListener(e -> LoadChar(selection.getSelected(), itemDescription, 0));
        chestplate.addActionListener(e -> LoadChar(selection.getSelected(), itemDescription, 1));
        boots.addActionListener(e -> LoadChar(selection.getSelected(), itemDescription, 2));
        sword.addActionListener(e -> LoadChar(selection.getSelected(), itemDescription, 3));
        shield.addActionListener(e -> LoadChar(selection.getSelected(), itemDescription, 4));

        selection.select(pOne);

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

        List<Enemy> enemies = dungeon.getCurrentRoom().contents;

        CombatController controller = new CombatController();

        List<Player> players = new ArrayList<>(List.of(pOne, pTwo, pThree, pFour));
        List<Player> alivePlayers = new ArrayList<Player>(players);

        List<pbutton> pbuttons = new ArrayList<>();
        for (Player player : players) {
            JButton btn = new JButton();
            pbuttons.add(new pbutton(player, btn, selection));

            // Make the button invisible so the name underneath shows through
            btn.setText("");
            btn.setOpaque(false);
            btn.setContentAreaFilled(false);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            frame.add(btn);
        }

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
            controller.oneAttack(alivePlayers, enemies, killFeed);
            CombatController.CombatStatus status = controller.checkCombatStatus(alivePlayers, enemies);
            updateCombatDisplay(alivePlayers, enemies, playerArea, enemyArea, killFeedArea, killFeed, combatStatus, controller.getCurrentRound(), status);
            positionPlayerButtons(alivePlayers, pbuttons, playerArea);
            
                
            if (status == CombatController.CombatStatus.PLAYERS_WIN){
                try {
                    Thread.sleep(700); // pause for a bit to show that you win
                } catch (InterruptedException i) {
                    System.out.println("buhhhhfduhsfud");
                }
                // loot
                controller.playersLootEnemies(players); //hey this worked                
                // go next room
                Room currentRoom = dungeon.goNextRoom();
                if (currentRoom.getRole() == RoomRole.END){
                    combatStatus.setText("Players beat the dungeon!");
                    ((Timer)e.getSource()).stop(); 
                }
                
                // load new enemies
                if (currentRoom != null){
                    enemies.addAll(dungeon.getCurrentRoom().contents);
                }
                
                // heal players
                pOne.maxHeal();
                pTwo.maxHeal();
                pThree.maxHeal();
                pFour.maxHeal();
            }    
            
            if (status == CombatController.CombatStatus.ENEMIES_WIN){
                combatStatus.setText("Enemies beat the players...");
                ((Timer)e.getSource()).stop(); // only stopping when enemies win
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
        positionPlayerButtons(players, pbuttons, playerArea);
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
            Iterator<String> iterator = killFeed.iterator();

            while (iterator.hasNext()) {
            String message = iterator.next();
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

    public static void positionPlayerButtons(List<Player> players, List<pbutton> slots, JTextArea playerArea) {
    int lineHeight = playerArea.getFontMetrics(playerArea.getFont()).getHeight();

    for (pbutton slot : slots) {
        int row = players.indexOf(slot.getPlayer());
        JButton button = slot.getButton();

        if (row == -1) {
            button.setVisible(false);
            continue;
        }

        // +2 skips the "PLAYERS" heading and the blank line under it
        button.setBounds(playerArea.getX(), playerArea.getY() + (row + 2) * lineHeight, playerArea.getWidth(), lineHeight);
        button.setVisible(true);
    }
}
}
