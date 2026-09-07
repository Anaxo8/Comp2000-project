import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;

import Items.*;
import Entities.*;

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

        Tester.EntityTester();
    }

    
    

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
