import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

class main {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        initUI(frame);
    }

    public static void initUI(JFrame frame){
        JButton chestplate = new JButton(new ImageIcon("./images/emptychestplate.png"));
        JButton boots = new JButton(new ImageIcon("./images/emptyboots.png"));
        JButton sword = new JButton(new ImageIcon("./images/emptysword.png"));
        JButton shield = new JButton(new ImageIcon("./images/emptyshield.png"));
        JButton helmet = new JButton(new ImageIcon("./images/emptyhelm.png"));

        JLabel border = new JLabel(new ImageIcon("./images/border.png"));
        JLabel bg = new JLabel(new ImageIcon("./images/bg.png"));

        border.setBounds(0,0,720,480);
        bg.setBounds(0,0,720,480);
        helmet.setBounds(100,280,48,48); 
        chestplate.setBounds(100,340,48,48);
        boots.setBounds(100,400,48,48);
        sword.setBounds(40,320, 48, 48);
        shield.setBounds(160, 320, 48, 48);

        helmet.addActionListener(e -> System.out.println("LOADED HELMET"));
        chestplate.addActionListener(e -> System.out.println("LOADED CHESTPLATE!"));
        boots.addActionListener(e -> System.out.println("LOADED BOOTS"));
        sword.addActionListener(e -> System.out.println("LOADED SWORD"));
        shield.addActionListener(e -> System.out.println("LOADED SHIELD"));

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
