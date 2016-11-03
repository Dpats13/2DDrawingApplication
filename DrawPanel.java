package twodimensionaldrawingapplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DrawPanel extends JPanel {
    private JLabel statusBar; //status label to display current x,y coordinates of mouse
    private ArrayList<MyShape> shapeList = new ArrayList<MyShape>(); //array list to store created shapes
    private String myShape = ""; //initializing myShape to avoid errors
    int x1;
    int y1;
    int x2;
    int y2;
    private Color firstColor;
    private Color secondColor;
    private int lineWidth;
    private boolean dashed = false; //initializing dashed to avoid errors 
    private float dashLength = 1; //initializing dash length to avoid errors
    private boolean gradient = false; //initializing gradient to avoid errors
    private boolean filled = false; //initializing filled to avoid errors 
    private MyShape myCurrentShape; //MyShape object to store the new shape created every time

    
    //constructor
    public DrawPanel() {
        setPreferredSize(new Dimension(600, 450));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        statusBar = new JLabel("Mouse outside panel");
        statusBar.setSize(new Dimension(100, 20));
        add(statusBar, BorderLayout.PAGE_END);
        MouseHandler mouseHandler = new MouseHandler();
        addMouseMotionListener(mouseHandler);
        addMouseListener(mouseHandler);
    }
    
    public void setMyShape(String myShape) {
        this.myShape = myShape;
    }
    
    public String getMyShape() {
        return myShape;
    }

    public Color getFirstColor() {
        return firstColor;
    }

    public void setFirstColor(Color firstColor) {
        this.firstColor = firstColor;
    }

    public Color getSecondColor() {
        return secondColor;
    }

    public void setSecondColor(Color secondColor) {
        this.secondColor = secondColor;
    }
    
    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }
    
    public void setDashed(boolean dashed) {
        this.dashed = dashed;
    }
    
    public boolean isDashed() {
        return dashed;
    }
    
    public void clearPanel() { //function to clear array list in order to clear draw panel
        shapeList.clear(); //clear array points
        repaint(); //repaint the draw panel to be clear
    } 
    
    public void undo() { //function to undo last shape drawn on panel
        if (shapeList.isEmpty() == false) {
            shapeList.remove(shapeList.size() - 1); //deleting last element from the shape array list
            repaint(); //repainting the draw panel to remove the last shape from panel
        }
        else { //nothing to be undone
            JOptionPane.showMessageDialog(null, "There is nothing to be undone", "Button Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isGradient() {
        return gradient;
    }

    public void setGradient(boolean gradient) {
        this.gradient = gradient;
    }
    
    public void setDashLength(float dashLength) {
        this.dashLength = dashLength;
    }
    
    public float getDashLength() {
        return dashLength;
    }
    
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    
    public boolean isFilled() {
        return filled;
    }
    
    //Private inner class MouseHandler to handle mouse actions on draw panel
    private class MouseHandler implements MouseMotionListener, MouseListener {
        @Override
        public void mouseDragged(MouseEvent event) {
            statusBar.setText("(" + event.getX() + "," + event.getY() + ")"); //setting status bar to display coordinates of mouse
            shapeList.get(shapeList.size() - 1).setX2(event.getX()); //setting x2 position of current shape
            shapeList.get(shapeList.size() - 1).setY2(event.getY()); //setting y2 position of current shape
            repaint();
        }
        
        @Override
        public void mousePressed(MouseEvent event) {
            //creating shape objects to be drawn based off users combo box choice
            if (getMyShape().equals("Line")) {
                myCurrentShape = new MyLine(event.getX(), event.getY(), event.getX(), event.getY(), getFirstColor(), getSecondColor(), getLineWidth(), isGradient(), isDashed(), getDashLength());
            }
            else if (getMyShape().equals("Rectangle")) {
                myCurrentShape = new MyRect(event.getX(), event.getY(), event.getX(), event.getY(), getFirstColor(), getSecondColor(), getLineWidth(), isGradient(), isDashed(), getDashLength(), isFilled());
            }
            else if (getMyShape().equals("Oval")) {
                myCurrentShape = new MyOval(event.getX(), event.getY(), event.getX(), event.getY(), getFirstColor(), getSecondColor(), getLineWidth(), isGradient(), isDashed(), getDashLength(), isFilled());
            }
            else {
                JOptionPane.showMessageDialog(null, "You must select a shape to draw", "Draw Error", JOptionPane.ERROR_MESSAGE);
            }
            if (myCurrentShape != null) { //if a shape has been selected and user starts to draw it add it to the array list
                shapeList.add(myCurrentShape);
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent event) {
           shapeList.get(shapeList.size() - 1).setX2(event.getX()); //setting x2 and y2 coordinates of final position
           shapeList.get(shapeList.size() - 1).setY2(event.getY());
           myCurrentShape = null;
           repaint();
        }
        
        @Override 
        public void mouseEntered(MouseEvent event) {
            
        }
        
        @Override 
        public void mouseExited(MouseEvent event) {
            statusBar.setText("Mouse outside panel");
        }
        
        @Override
        public void mouseClicked(MouseEvent event) {
            
        }
        
        @Override
        public void mouseMoved(MouseEvent event) {
            statusBar.setText("(" + event.getX() + "," + event.getY() + ")");
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (MyShape myShape : shapeList) { //enhanced for loop to draw all shape objects created by user
            myShape.draw(g2d);
        }
    }
}
