package esteb.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class PanelUI extends JPanel {

    /**
     * @return the roundbottomLeft
     */
    public int getRoundbottomLeft() {
        return roundbottomLeft;
    }

    /**
     * @param roundbottomLeft the roundbottomLeft to set
     */
    public void setRoundbottomLeft(int roundbottomLeft) {
        this.roundbottomLeft = roundbottomLeft;
    }

    /**
     * @return the roundtopRight
     */
    public int getRoundtopRight() {
        return roundtopRight;
    }

    /**
     * @param roundtopRight the roundtopRight to set
     */
    public void setRoundtopRight(int roundtopRight) {
        this.roundtopRight = roundtopRight;
    }

    /**
     * @return the roundbottomRight
     */
    public int getRoundbottomRight() {
        return roundbottomRight;
    }

    /**
     * @param roundbottomRight the roundbottomRight to set
     */
    public void setRoundbottomRight(int roundbottomRight) {
        this.roundbottomRight = roundbottomRight;
    }

    /**
     * @return the roundtopLeft
     */
    public int getRoundtopLeft() {
        return roundtopLeft;
    }

    /**
     * @param roundtopLeft the roundtopLeft to set
     */
    public void setRoundtopLeft(int roundtopLeft) {
        this.roundtopLeft = roundtopLeft;
    }

    private int roundbottomLeft=0;
    private int roundtopRight=0;
    private int roundbottomRight=0;
    private int roundtopLeft=0;

    public PanelUI(){
        setOpaque(false);
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Area area = new Area(createRoundTopLeft());
        if (getRoundtopRight() > 0) {
            area.intersect(new Area(createRoundTopRight()));
        }
        if (getRoundbottomLeft() > 0) {
            area.intersect(new Area(createRoundBottomLeft()));
        }
        if (getRoundbottomRight() > 0) {
            area.intersect(new Area(createRoundBottomRight()));
        }
        g2.fill(area);
        g2.dispose();
        super.paintComponent(g);
    }

    private Shape createRoundTopLeft(){
        int w = getWidth();
        int h = getHeight();
        int rx = Math.min(w, getRoundtopLeft());
        int ry = Math.min(h, getRoundtopLeft());
        Area area = new Area(new RoundRectangle2D.Double(0, 0, w, h, rx, ry));
        area.add(new Area(new Rectangle2D.Double(rx-2, 0, w-rx/2, h)));
        area.add(new Area(new Rectangle2D.Double(0, ry/2, w, h-ry/2)));
        return area;
    }

    private Shape createRoundTopRight(){
        int w = getWidth();
        int h = getHeight();
        int rx = Math.min(w, getRoundbottomRight());
        int ry = Math.min(h, getRoundbottomRight());
        Area area = new Area(new RoundRectangle2D.Double(0, 0, w, h, rx, ry));
        area.add(new Area(new Rectangle2D.Double(0, 0, w-rx/2, h)));
        area.add(new Area(new Rectangle2D.Double(0, ry/2, w, h-ry/2)));
        return area;
    }

    private Shape createRoundBottomLeft(){
        int w = getWidth();
        int h = getHeight();
        int rx = Math.min(w, getRoundbottomLeft());
        int ry = Math.min(h, getRoundbottomLeft());
        Area area = new Area(new RoundRectangle2D.Double(0, 0, w, h, rx, ry));
        area.add(new Area(new Rectangle2D.Double(rx/2, 0, w-rx/2, h)));
        area.add(new Area(new Rectangle2D.Double(0, 0, w, h-ry/2)));
        return area;
    }

    private Shape createRoundBottomRight(){
        int w = getWidth();
        int h = getHeight();
        int rx = Math.min(w, getRoundbottomRight());
        int ry = Math.min(h, getRoundbottomRight());
        Area area = new Area(new RoundRectangle2D.Double(0, 0, w, h, rx, ry));
        area.add(new Area(new Rectangle2D.Double(0, 0, w-rx/2, h)));
        area.add(new Area(new Rectangle2D.Double(0, 0, w, h-ry/2)));
        return area;
    }
}