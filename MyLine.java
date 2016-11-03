package twodimensionaldrawingapplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Graphics;

public class MyLine extends MyShape {
    //constructor
    public MyLine(int x1, int y1, int x2, int y2, Color firstColor, Color secondColor, int lineWidth, boolean gradient, boolean dashed, float dashLength) {
        super(x1, y1, x2, y2, firstColor, secondColor, lineWidth, gradient, dashed, dashLength);
    }

    @Override
    public void draw(Graphics2D g2d) {
        
        if (isGradient() == true) { //if gradient paint option is checked
            g2d.setPaint(new GradientPaint(getX1(), getY1(), getFirstColor(), getX2(), getY2(), getSecondColor(), true));
        }
        else { //if its not
            g2d.setPaint(getFirstColor());
        }
        if (isDashed() == true) { //if dashed option is checked
            float[] dashLengthArray = {getDashLength()}; //dash length array for dash stroke
            g2d.setStroke(new BasicStroke(getLineWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashLengthArray, 0));
        }
        else { //if its not
            g2d.setStroke(new BasicStroke(getLineWidth()));
        }
        g2d.drawLine(getX1(), getY1(), getX2(), getY2());
    }
}
