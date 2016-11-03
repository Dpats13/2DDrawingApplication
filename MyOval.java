package twodimensionaldrawingapplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Graphics;

public class MyOval extends MyShape {
    //Oval characteristics 
    private boolean filled = false;
    private int width;
    private int height;
    
    //constructor
    public MyOval(int x1, int y1, int x2, int y2, Color firstColor, Color secondColor, int lineWidth, boolean gradient, boolean dashed, float dashLength, boolean filled) {
        super(x1, y1, x2, y2, firstColor, secondColor, lineWidth, gradient, dashed, dashLength);
        this.filled = filled;
    }
    
    public boolean isFilled() {
        return filled;
    }
    
    public boolean isOvalInQuadOne(int x1, int y1, int x2, int y2) { //logic to test where object is being drawn at 
        return x1 >= x2 && y1 >= y2;
    }
    
    public boolean isOvalInQuadTwo(int x1, int y1, int x2, int y2) {
        return x2 >= x1 && y1 >= y2;
    }
    
    public boolean isOvalInQuadThree(int x1, int y1, int x2, int y2) {
        return x1 >= x2 && y2 >= y1;
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        width = getX2() - getX1(); //normal width base case quad 4
        height = getY2() - getY1(); //normal height base case quad 4
        if (isGradient() == true) {
            g2d.setPaint(new GradientPaint(getX1(), getY1(), getFirstColor(), getX2(), getY2(), getSecondColor(), true));
        }
        else {
            g2d.setPaint(getFirstColor());
        }
        if (isDashed() == true) { //if dashed option is checked
            float[] dashLengthArray = {getDashLength()}; //dash length array for dash stroke
            g2d.setStroke(new BasicStroke(getLineWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashLengthArray, 0));
        }
        else {
            g2d.setStroke(new BasicStroke(getLineWidth()));
        } 
        
        if (isFilled() == true) {
            if (isOvalInQuadOne(getX1(), getY1(), getX2(), getY2())) {
                g2d.fillOval(getX2(), getY2(), getX1() - getX2(), getY1() - getY2());
            }
            else if (isOvalInQuadTwo(getX1(), getY1(), getX2(), getY2())) {
                g2d.fillOval(getX1(), getY2(), getX2() - getX1(), getY1() - getY2());
            }
            else if (isOvalInQuadThree(getX1(), getY1(), getX2(), getY2())) {
                g2d.fillOval(getX2(), getY1(), getX1() - getX2(), getY2() - getY1());
            }
            else { //quadrant 4 base case 
                g2d.fillOval(getX1(), getY1(), width, height);
            }
        }
        else {
            if (isOvalInQuadOne(getX1(), getY1(), getX2(), getY2())) {
                g2d.drawOval(getX2(), getY2(), getX1() - getX2(), getY1() - getY2());
            }
            else if (isOvalInQuadTwo(getX1(), getY1(), getX2(), getY2())) {
                g2d.drawOval(getX1(), getY2(), getX2() - getX1(), getY1() - getY2());
            }
            else if (isOvalInQuadThree(getX1(), getY1(), getX2(), getY2())) {
                g2d.drawOval(getX2(), getY1(), getX1() - getX2(), getY2() - getY1());
            }
            else { //quadrant 4 base case 
                g2d.drawOval(getX1(), getY1(), width, height);
            }
        }
    }
}
