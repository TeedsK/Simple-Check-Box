import button.PressSpreadButton;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BasicStroke;
@SuppressWarnings("serial")
/**
 * Creates a customizable checkbox 
 * @author Teeds - Theo k
 */
public class Checkbox extends PressSpreadButton {
    ArrayList<JPanel> ParentJPanels = new ArrayList<JPanel>();
    boolean active = false;
    int alpha = 255;
    Color outline = new Color(29,38,46);
    /**
     * Creates a quick, basic checkbox
     */
    public Checkbox() {
        super(new Color(0,0,0), new Color(39,48,56), new Color(38,47,55), new Color(50,68,85), 15);
        setup();
    }
    /**
     * @param background the color behind the checkbox
     */
    public Checkbox(Color background) {
        super(background, new Color(39,48,56), new Color(38,47,55), new Color(50,68,85), 15);
        setup();
    }
    /**
     * @param background the color behind the checkbox
     * @param checkBox the color of the checkbox when off
     */
    public Checkbox(Color background, Color checkBox) {
        super(background, checkBox, new Color(38,47,55), new Color(50,68,85), 15);
        setup();
    }
    /**
     * @param background the color behind the checkbox
     * @param checkBox the color of the checkbox when off
     * @param checkBoxHover the color of the checkbox when hovering
     */
    public Checkbox(Color background, Color checkBox, Color checkBoxHover) {
        super(background, checkBox, checkBoxHover, new Color(50,68,85), 15);
        setup();
    }
    /**
     * @param background the color behind the checkbox
     * @param checkBox the color of the checkbox when off
     * @param checkBoxHover the color of the checkbox when hovering
     * @param checkBoxSelected the color of the checkbox when selected
     */
    public Checkbox(Color background, Color checkBox, Color checkBoxHover, Color checkBoxSelected) {
        super(background, checkBox, checkBoxHover, checkBoxSelected, 15);
        setup();
    }
    /**
     * @param background the color behind the checkbox
     * @param checkBox the color of the checkbox when off
     * @param checkBoxHover the color of the checkbox when hovering
     * @param checkBoxSelected the color of the checkbox when selected
     * @param roundness the roundness of the checkbox
     */
    public Checkbox(Color background, Color checkBox, Color checkBoxHover, Color checkBoxSelected, int roundness) {
        super(background, checkBox, checkBoxHover, checkBoxSelected, roundness);
        setup();
    }
    /**
     * @param background the color behind the checkbox
     * @param checkBox the color of the checkbox when off
     * @param checkBoxHover the color of the checkbox when hovering
     * @param checkBoxSelected the color of the checkbox when selected
     * @param checkBoxOutline the color of the checkbox outline
     * @param roundness the roundness of the checkbox
     */
    public Checkbox(Color background, Color checkBox, Color checkBoxHover, Color checkBoxSelected, Color checkBoxOutline, int roundness) {
        super(background, checkBox, checkBoxHover, checkBoxSelected, roundness);
        outline = checkBoxOutline;
        setup();
    }

    /**
     * Sets up the rest of the checkbox
     */
    private void setup() {
        super.setPressIncrementAmount(8);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(active) {
                    uncheck();
                    changeAlphaValues(4, 3, false);
                } else {
                    check();
                    changeAlphaValues(4, 3, true);
                }
            }
        });
    }

    /**
     * paints the checkbox outline and the checkmark
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setStroke(new BasicStroke(2.4f));
        graphics.setColor(new Color(outline.getRed(),outline.getGreen(),outline.getBlue(), alphaCheck()));
        graphics.drawRoundRect(3, 3, getWidth() - 6, getHeight() - 6, 10, 10);

        int alp = 255 - alphaCheck();
        if(alp > 0) {
            graphics.setColor(new Color(255,255,255, alp));
            graphics.setStroke(new BasicStroke(2.1f));
            graphics.drawLine(getPoint(3.8) + 2, getPoint(1.9) + 2, getPoint(2.1) + 2, getPoint(1.4) + 2);
            graphics.drawLine(getPoint(2.1) + 2, getPoint(1.4) + 2,  getPoint(1.3) + 2, getPoint(3.2) + 2);
        }
    }
  
    /**
     * @param percent the position the given point should be at
     * @return
     */
    private int getPoint(double percent) {
        return (int) ((getWidth() - 4)/ percent);
    }

    /**
     * Changes the value of the alpha
     * @param on wether or not the alpha is increasing or decreasing, checkmark on/off
     * @param wanted the wanted alpha value
     */
    private void changeAlphaValues(boolean on, int wanted) {
        Thread t = new Thread() {
            public void run() {
                while(active == on && alpha != wanted) {
                    if(on) {
                        alpha -= 15;
                    } else {
                        alpha += 15;
                    }
                    alphaCheck();
                    update();
                    sleepTime(10);
                }
            }
        }; t.start();
    }

    /**
     * Checks the alpha value
     * @return the checked alpha value
     */
    private int alphaCheck() {
        if(alpha > 255) {
            alpha = 255;
            return 255;
        } else if(alpha < 0) {
            alpha = 0;
            return 0;
        } else {
            return alpha;
        }
    }

    /**
     * updates any connected jpanels
     */
    private void update() {
        revalidate();
        repaint();
        for(JPanel panel : ParentJPanels) {
            if(panel.isVisible() && panel.getBackground().getAlpha() > 0) {
                panel.revalidate();
                panel.repaint();
            }
        }
    }

    /**
     * Sleeps for the time given
     * @param time given amount of time to sleep for
     */
    private void sleepTime(int time) {
        try {
            Thread.sleep(time);
        } catch(Exception err1) {}
    }

    /**
     * Get wether or not the checkmark box is on
     * @return on or off
     */
    public boolean getActive() {
        return active;
    }

    /**
     * Marks the checkbox as being active
     */
    public void check() {
        active = true;
        changeAlphaValues(active, 0);
    }

    /**
     * Marks the checkbox as being deactive
     */
    public void uncheck() {
        active = false;
        changeAlphaValues(active, 255);
    }
}
