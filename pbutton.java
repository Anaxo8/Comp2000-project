import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

import Entities.Player;

public class pbutton {

    private static final String[] emptys = {
        "./images/emptyhelm.png",
        "./images/emptychestplate.png",
        "./images/emptyboots.png",
        "./images/emptysword.png",
        "./images/emptyshield.png"
    };

    private Player player;
    private JButton button;

    public pbutton(Player player, JButton button, PlayerSelection selection)
    {
        this.player = player;
        this.button = button;

        button.addActionListener(e -> selection.select(player));
    }

    /*private void loadGear(JButton[] slots, JTextArea items)
    {
        main.selectedPlayer = player;
        main.updategearscore(player);
        items.setText("");

        for(int i = 0; i < slots.length; i++)
        {
            Object item = player.getInv().getItem(i);

            if(item == null)
            {
                slots[i].setIcon(new ImageIcon(emptys[i]));
                slots[i].setToolTipText("Empty");
            }
            else{
                slots[i].setIcon(new ImageIcon(emptys[i]));
                slots[i].setToolTipText("Empty");
            }
        }

        System.out.println("Selected " + player.getName());
    }*/

    public Player getPlayer(){
        return player;
    }

    public JButton getButton() {
        return button;
    }
}
