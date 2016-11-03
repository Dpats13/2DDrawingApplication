package twodimensionaldrawingapplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public abstract class MyShape {
    //shape characterisitcs 
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private boolean gradient;
    private boolean dashed;
    private float dashLength;
    private int lineWidth;
    private Color firstColor;
    private Color secondColor;
    
    //constructor
    public MyShape(int x1, int y1, int x2, int y2, Color firstColor, Color secondColor, int lineWidth, boolean gradient, boolean dashed, float dashLength) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.firstColor = firstColor;
        this.secondColor = secondColor;
        this.gradient = gradient;
        this.dashed = dashed;
        this.dashLength = dashLength;
        this.lineWidth = lineWidth;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public boolean isGradient() {
        return gradient;
    }

    public boolean isDashed() {
        return dashed;
    }

    public int getLineWidth() {
        return lineWidth;
    }
    
    public float getDashLength() {
        return dashLength;
    }

    public abstract void draw(Graphics2D g2d); //abstract method to draw various shapes 
    
    public Color getFirstColor() {
        return firstColor;
    }

    public Color getSecondColor() {
        return secondColor;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}