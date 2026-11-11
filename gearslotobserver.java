import Entities.Player;
import javax.swing.*;

public class gearslotobserver implements selectionobserver{
        private static final String[] empties = {
        "./images/emptyhelm.png",
        "./images/emptychestplate.png",
        "./images/emptyboots.png",
        "./images/emptysword.png",
        "./images/emptyshield.png"
    };

    private final JButton[] gearslots;
    
    public gearslotobserver(JButton[] gearslots)
    {
        this.gearslots = gearslots;
    }

    @Override public void playerSelected(Player player)
    {
        for (int i = 0; i < gearslots.length; i++)
        {
            Object item = player.getInv().getItem(i);

            gearslots[i].setIcon(new ImageIcon(empties[i]));
        }
    }
}
