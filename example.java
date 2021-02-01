import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
public class example {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(500,500);
        f.setPreferredSize(new Dimension(500,500));
        f.setMinimumSize(new Dimension(500,500));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel j = new JPanel();
        j.setLayout(new BoxLayout(j, BoxLayout.X_AXIS));
        f.add(j);
        j.setBackground(new Color(170,170,170));
        j.add(Box.createHorizontalGlue());
        Checkbox load = new Checkbox();
        load.setMaximumSize(new Dimension(33,33));
        load.setPreferredSize(new Dimension(33,33));
        load.setMinimumSize(new Dimension(33,33));
        Checkbox loadv2 = new Checkbox();
        loadv2.setMaximumSize(new Dimension(33,33));
        loadv2.setPreferredSize(new Dimension(33,33));
        loadv2.setMinimumSize(new Dimension(33,33));
        j.setBackground(new Color(0,0,0));
        j.add(load);
        j.add(Box.createHorizontalGlue());
        j.add(loadv2);
        j.add(Box.createHorizontalGlue());
        f.pack();
        f.setVisible(true);
    }
}
