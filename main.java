import java.io.*;
import javax.swing.*;

class GFG {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        JButton button = new JButton("GFG Click");

        button.setBounds(150,200,220,50);
        JLabel helmet = new JLabel(loadImage("./helm1.png"));
        helmet.setBounds(150,200,220,100); 
        //frame.add(button);
        frame.add(helmet);
        frame.setSize(500,600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static ImageIcon loadImage(String URL)
    {
        if(URL != null)
        {
            System.out.println("Loaded image" + URL);
            return new ImageIcon(URL);
        }
        else
        {
            System.out.println("Failed to load image!");
            return new ImageIcon("./fallback.jpg");
        }

    }
}
