import Entities.Player;
import java.util.Objects;
import java.util.stream.IntStream;
import javax.swing.*;

public class gearscoreobserver implements selectionobserver {
    
    private final JLabel label;

    public gearscoreobserver(JLabel label)
    {
        this.label = label;
    }

    @Override 
    public void playerSelected(Player player)
    {
        label.setText("GearScore: " + calculategearscore(player));
    }

    private int calculategearscore(Player player)
    {
        return IntStream.range(0, 5).mapToObj(i -> player.getInv().getItem(i)).filter(Objects::nonNull).mapToInt(item -> item.getScore()).sum();
    }
}
