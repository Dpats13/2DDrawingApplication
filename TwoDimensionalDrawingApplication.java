package twodimensionaldrawingapplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TwoDimensionalDrawingApplication extends JFrame {
    DrawPanel drawPanel; //panel to draw on
    private JPanel buttonPanel; //Panel to place buttons, checkboxes, and comboxes on
    private JButton undoButton; //button to undo last action by user
    private JButton clearButton; //button to clear draw panel
    private JButton firstColorButton; //button to select primary color
    private JButton secondColorButton; //button to select secondary color
    private JLabel shapeOptionLabel; //"Shape:"
    private JComboBox shapeOptionComboBox; //combo box to select shape that is drawn
    private String[] shapeOptions = {"Select a Shape", "Line", "Rectangle", "Oval"}; //string array to hold shape options for combo box
    private JCheckBox fillOptionCheckBox; //check box for fill option
    private JCheckBox gradientOptionCheckBox; //check box for gradient option
    private JLabel lineWidthLabel; //"Line Width:"
    private JTextField lineWidthTextField; //text field to enter desired line width
    private JLabel dashLengthLabel; //"Dash Length:"
    private JTextField dashLengthTextField; //text field to enter desired dash length
    private JCheckBox dashOptionCheckBox; //check box for dashed line option
    
    //constructor
    public TwoDimensionalDrawingApplication() {
        super("2D Drawing Application"); //Setting title of window by using super constructor
        setLayout(new BorderLayout());
        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.SOUTH); //adding draw panel to the application window JFrame
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); //setting layout for button panel
        buttonPanel.setSize(new Dimension(600, 150)); //setting size of button panel
        buttonPanel.setBackground(Color.GRAY); //setting background color of button panel 
        add(buttonPanel, BorderLayout.CENTER); //assing buttonPanel to top of App Window
        ButtonHandler buttonHandler = new ButtonHandler(); //Creating button handler object to handle buttons
        GradientCheckBoxHandler gradientCheckBoxHandler = new GradientCheckBoxHandler(); //creating gradientcheck box handler object to handle gradient check box
        DashCheckBoxHandler dashCheckBoxHandler = new DashCheckBoxHandler(); //creating dash check box handler to handle dash check box
        FillCheckBoxHandler fillCheckBoxHandler = new FillCheckBoxHandler(); //creating fill check box handler object to handle fill option check box
        undoButton = new JButton("Undo"); //creating button object for undo
        undoButton.addActionListener(buttonHandler); //adding button handler to undo button
        buttonPanel.add(undoButton); //adding undo button to button panel
        clearButton = new JButton("Clear"); //creating button object for clear
        clearButton.addActionListener(buttonHandler); //adding button handler to clear button
        buttonPanel.add(clearButton); //adding clear button to panel
        shapeOptionLabel = new JLabel("Shape: "); //creating object of jlabel
        shapeOptionLabel.setForeground(Color.WHITE); //setting text to white
        buttonPanel.add(shapeOptionLabel);
        shapeOptionComboBox = new JComboBox(shapeOptions);
        shapeOptionComboBox.setMaximumRowCount(3);
        ComboBoxHandler comboBoxHandler = new ComboBoxHandler();
        shapeOptionComboBox.addItemListener(comboBoxHandler);
        buttonPanel.add(shapeOptionComboBox); //adding combo box to button panel
        fillOptionCheckBox = new JCheckBox("Filled"); //creating checkbox object for fill option
        fillOptionCheckBox.addItemListener(fillCheckBoxHandler); //adding fill check box handler to fill option check box
        buttonPanel.add(fillOptionCheckBox); //adding fill check box to button panel
        gradientOptionCheckBox = new JCheckBox("Use Gradient"); //creating check box object for gradient option
        gradientOptionCheckBox.addItemListener(gradientCheckBoxHandler); //adding check box handler to gradient option check box
        buttonPanel.add(gradientOptionCheckBox); //adding gradient option check box to button panel
        firstColorButton = new JButton("1st Color");//creating first color button object
        buttonPanel.add(firstColorButton); //adding first color button to button panel
        firstColorButton.addActionListener(buttonHandler); //adding button handler to firstColor button
        secondColorButton = new JButton("2nd Color"); //creating second color button object
        buttonPanel.add(secondColorButton); //adding second color button to button panel
        secondColorButton.addActionListener(buttonHandler); //adding button handler to secondColor button
        lineWidthLabel = new JLabel("Line Width:"); //creating line width object label
        lineWidthLabel.setForeground(Color.WHITE); //setting color of label text
        buttonPanel.add(lineWidthLabel); //adding line width label to button panel
        lineWidthTextField = new JTextField(); //creating JTextField object for line width
        lineWidthTextField.setColumns(2); //setting width of text field to 2
        TextFieldHandler textFieldHandler = new TextFieldHandler(); //text field handler object to handle inputs into text fields
        lineWidthTextField.addActionListener(textFieldHandler); //adding textFieldHandler to line width text field
        buttonPanel.add(lineWidthTextField); //adding line width text field to screen
        dashLengthLabel = new JLabel("Dash Length:"); //creating dash length object label
        dashLengthLabel.setForeground(Color.WHITE); //setting color of label text
        buttonPanel.add(dashLengthLabel); //adding dash length label to button panel
        dashLengthTextField = new JTextField(); //creating JTextField object for dash length
        dashLengthTextField.setColumns(2); //setting width of text field to 2
        dashLengthTextField.addActionListener(textFieldHandler); //adding text field handler to handle dash length text field
        buttonPanel.add(dashLengthTextField); //adding dash length text field to screen
        dashOptionCheckBox = new JCheckBox("Dashed"); //creating checkbox object for fill option
        dashOptionCheckBox.addItemListener(dashCheckBoxHandler); //adding dashCheckBoxHandler to dash check box
        buttonPanel.add(dashOptionCheckBox); //adding fill check box to button panel
        
    }
    
    //Private inner class to handle jcombobox shapeOptionBox
    private class ComboBoxHandler implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getSource() == shapeOptionComboBox) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    drawPanel.setMyShape(shapeOptions[shapeOptionComboBox.getSelectedIndex()]);
                }
            }
        }
    }
    
    //Private innter class to handle all buttons
    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == firstColorButton) {
                drawPanel.setFirstColor(JColorChooser.showDialog(TwoDimensionalDrawingApplication.this, "Choose a Color", Color.BLACK));
            }
            else if (e.getSource() == secondColorButton) {
                drawPanel.setSecondColor(JColorChooser.showDialog(TwoDimensionalDrawingApplication.this, "Choose a Color", Color.BLACK));
            }
            else if (e.getSource() == clearButton) {
                drawPanel.clearPanel(); //clear draw panel
            }
            else if (e.getSource() == undoButton) {
                drawPanel.undo(); //erase last drawn shape
            }
        }
    }
    
    //private inner class to handle line width text field and dash length text field
    private class TextFieldHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int width = 0; //width variable for line width
            float length = 0; //length variabel for dash length
            if (e.getSource() == lineWidthTextField) { //user hits enter in this text field
                try { //try and catch block to ensure that they enter an integer
                    width = Integer.parseInt(lineWidthTextField.getText());
                }
                catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null, "You must enter an Integer", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                if (width < 1 || width > 50) {
                    JOptionPane.showMessageDialog(null, "The line width must be between 1 and 50", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                drawPanel.setLineWidth(width); 
                drawPanel.requestFocus(); //remove cursor from text field so they know their value entered
                }
            }
            else if (e.getSource() == dashLengthTextField) { //user presses enter on dash length
                try { //try and catch block to ensure that they entered an integer
                    length = Integer.parseInt(dashLengthTextField.getText());
                }
                catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null, "You must enter an Integer", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                if (length < 1 || length > 50) {
                    JOptionPane.showMessageDialog(null, "The dash length must be between 1 and 50", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    drawPanel.setDashLength(length);
                    drawPanel.requestFocus(); //remove cursor from text field so they know their value entered
                }
            }
        }
    }
    
    //Private inner class to handle gradient option check box
    private class GradientCheckBoxHandler implements ItemListener {
        @Override 
        public void itemStateChanged(ItemEvent e) {
            
            if (e.getStateChange() == ItemEvent.SELECTED) { //gradient option checked
                if (drawPanel.getSecondColor() != null) {//user must choose a second color in order to do gradient option
                    drawPanel.setGradient(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "You must select a second color in order use gradient paint", "Selection Error", JOptionPane.ERROR_MESSAGE);
                    gradientOptionCheckBox.setSelected(false); //unselect gradient check box
                }
            }
            else if (e.getStateChange() == ItemEvent.DESELECTED) { //gradient option unchecked
                drawPanel.setGradient(false); //set the gradient paint to false
            }
        }
    
    }
    
    //Private inner class to handle dash option check box
    private class DashCheckBoxHandler implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            
            if (e.getStateChange() == ItemEvent.SELECTED) { //if dash option is checked
                if (dashLengthTextField.getText().equals("")) { //if dash length is empty
                    JOptionPane.showMessageDialog(null, "You must enter a dash length before selecting dashed option", "Selection Error", JOptionPane.ERROR_MESSAGE);
                    dashOptionCheckBox.setSelected(false); //deselect check box 
                }
                else {
                    drawPanel.setDashed(true);
                }
            }
            else if (e.getStateChange() == ItemEvent.DESELECTED) { //if dash option is unchecked
                drawPanel.setDashed(false);
            }
        }
    }
    
    //Private inner class to handle dash option check box
    private class FillCheckBoxHandler implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            
            if (e.getStateChange() == ItemEvent.SELECTED) { //if fill option is checked
                drawPanel.setFilled(true);
            }
            else if (e.getStateChange() == ItemEvent.DESELECTED) { //if fill option is unchecked
                drawPanel.setFilled(false);
            }
        }
    }

   
    public static void main(String[] args) {
        TwoDimensionalDrawingApplication twoDDrawingApp = new TwoDimensionalDrawingApplication();
        twoDDrawingApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        twoDDrawingApp.setSize(600,600);
        twoDDrawingApp.setVisible(true);
    }
    
}
