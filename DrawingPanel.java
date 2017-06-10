package pictionary3;


import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    private Color currColor;
    private int currSize;
    
    private int xCoord; //x coordinate of drawings
    private int yCoord; //y coordinate of drawings
    
    private int xCoordOutline; //x coordinate of drawing guide outline thing
    private int yCoordOutline; //y coordinate of drawing guide outline thing
    
    private BufferedImage drawings;
    
    private boolean clrDrawingPanel;
    private boolean isDrawingTime;
    
    private JLabel labelTime;
    private JLabel lblPlayerOnePoints;
    private JLabel lblPlayerTwoPoints;
    
    public DrawingPanel() {     
        this.setVisible(true);
        currColor = Color.BLACK;
        currSize = 5;
        clrDrawingPanel = false;
        isDrawingTime = false;
        xCoord = -200; //outside of the panel (not seen)
        yCoord = -200; //outside of the panel (not seen)
        xCoordOutline = -200;
        xCoordOutline = -200;
        
        drawings = new BufferedImage(1300, 800, BufferedImage.TYPE_INT_ARGB);
        
        labelTime = new JLabel();
        lblPlayerOnePoints = new JLabel("Player One: 0 | ");
        lblPlayerTwoPoints = new JLabel("Player Two: 0");
        
        //adds MouseMotionListener to DrawingPanel
        this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
                xCoord = e.getX();
                yCoord = e.getY();
                xCoordOutline = e.getX();
                yCoordOutline = e.getY();
                repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {	
				xCoordOutline = e.getX();
				yCoordOutline = e.getY();
				repaint();
			}
        });
    	
    	this.add(labelTime);
    	this.add(lblPlayerOnePoints);
    	this.add(lblPlayerTwoPoints);
    	lblPlayerOnePoints.setVisible(true);
    	lblPlayerTwoPoints.setVisible(true);
    	labelTime.setVisible(true);
    }
    
    public void setXCoord(int x) {
    	xCoord = x;
    }
    
    public void setYCoord(int y) {
    	yCoord = y;
    }
    
    public void setLabelTimer(String time) {
    	labelTime.setText("Time to Draw: " + time + " | ");
    }
    
    public void setLblPlayerOnePoints(String name, int playerPoints) {
    	lblPlayerOnePoints.setText(name + ": " + playerPoints + " | ");
    }
    
    public void setLblPlayerTwoPoints(String name, int playerPoints) {
    	lblPlayerTwoPoints.setText(name + ": " + playerPoints);
    }

    public void setCurrColor(Color color) {
        currColor = color;
    }
    
    public void setCurrSize(int size) {
    	currSize = size;
    }
    
    public void setClrDrawingPanel(boolean clear) {
    	clrDrawingPanel = clear;
    }
    
    public void setIsDrawingTime(boolean drawTime) {
    	isDrawingTime = drawTime;
    }
    
    public void paintComponent(Graphics g) { 
    	super.paintComponent(g);
    	
    	Graphics gBuffer= drawings.createGraphics();
    	
    	//if isDrawingTime is true, then user can drag mouse to draw
    	if(isDrawingTime) {
    		gBuffer.setColor(currColor);
    		gBuffer.fillOval(xCoord - (currSize / 2), yCoord - (currSize / 2), currSize, currSize);
    	}
    		
    	//if clrDrawingPanel is true, clear the drawing panel by drawing white filled rectangle        
    	if (clrDrawingPanel) {
    		gBuffer.setColor(Color.WHITE);
    		gBuffer.fillRect(0, 0, 1300, 800);
    		clrDrawingPanel = false;
    	}
    	
        g.drawImage(drawings, 0, 0, this);
        
        //outlines what is going to be drawn
    	g.setColor(Color.lightGray);
    	g.drawOval(xCoordOutline - (currSize / 2), yCoordOutline - (currSize / 2), currSize, currSize);
    }
}
