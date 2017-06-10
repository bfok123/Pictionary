package pictionary3;



/**
 *
 * @author Brandon
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainFrame extends JFrame{
    private JButton btnRed, btnOrange, btnYellow, btnGreen, btnDarkGreen, btnBlue, btnViolet, btnPink, 
    				btnMagenta, btnTan, btnBrown, btnGray, btnBlack, btnWhite;
    private JButton btnSize5, btnSize10, btnSize20, btnSize30, btnSize40, btnSize50, btnSize60,
    				btnSize70, btnSize80, btnSize90, btnSize100, btnSize200;
    private JButton btnClear;
    private JButton btnReady;
    private JButton btnDone;
    private JButton btnSettings;
    private DrawingPanel drawingPanel;
    private Player playerOne;
    private Player playerTwo;
    private JPanel panelForUserMessages;
    
    private int timerCounter;
    private Timer drawingTimer;
    
    public MainFrame() {            
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setTitle("Pictionary");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //creates JPanel for buttons (60x500)
        JPanel buttonPanel = new JPanel(); 
        buttonPanel.setPreferredSize(new Dimension(70, this.getHeight()));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        //creates JPanel for messages to user (JOptionPanes)
        panelForUserMessages = new JPanel();
        panelForUserMessages.setPreferredSize(new Dimension(500, 100));
        panelForUserMessages.setVisible(false);
        
        //initializes players
        playerOne = new Player("Player 1");
        playerTwo = new Player("Player 2");
        playerOne.setIsTurn(true);
        
        //initializes drawingPanel
        drawingPanel = new DrawingPanel();
        drawingPanel.setBackground(Color.WHITE);
        drawingPanel.setPreferredSize(new Dimension(this.getWidth() - 70, this.getHeight()));
        
        //make more colors
        Color brown = new Color(150, 75, 0);
        Color tan = new Color(255, 205, 148);
        Color pink = new Color(255, 192, 203);
        Color violet = new Color(143, 0, 255);
        Color green = new Color(50, 177, 65);
        
        //initializes color buttons
        btnRed = makeColorButton(Color.RED);
        btnOrange = makeColorButton(Color.ORANGE);
        btnYellow = makeColorButton(Color.YELLOW);
        btnGreen = makeColorButton(Color.GREEN); //this is a lighter shade of green
        btnDarkGreen = makeColorButton(green);
        btnBlue = makeColorButton(Color.BLUE);
        btnViolet = makeColorButton(violet);
        btnPink = makeColorButton(pink);
        btnMagenta = makeColorButton(Color.MAGENTA);
        btnTan = makeColorButton(tan);
        btnBrown = makeColorButton(brown);
        btnGray = makeColorButton(Color.GRAY);
        btnBlack = makeColorButton(Color.BLACK);
        btnWhite = makeColorButton(Color.WHITE);
        
        //initializes size buttons
        btnSize5 = makeSizeButton(5);
        btnSize10 = makeSizeButton(10);
        btnSize20 = makeSizeButton(20);
        btnSize30 = makeSizeButton(30);
        btnSize40 = makeSizeButton(40);
        btnSize50 = makeSizeButton(50);
        btnSize60 = makeSizeButton(60);
        btnSize70 = makeSizeButton(70);
        btnSize80 = makeSizeButton(80);
        btnSize90 = makeSizeButton(90);
        btnSize100 = makeSizeButton(100);
        btnSize200 = makeSizeButton(200);
        
        //initializes ready, clear, done, and settings buttons
        btnClear = makeClrButton();
        btnReady = makeRdyButton();
        btnDone = makeDoneButton();
        btnDone.setVisible(false);
        btnSettings = makeSettingsButton();
        
        //ActionListener for the drawing timer
        ActionListener listenForDrawingTimer = new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			drawingPanel.setLabelTimer("Time: " + timerCounter--);
    				
    			if(timerCounter == -1) {
    				drawingPanel.setIsDrawingTime(false);
    				drawingTimer.stop();
    				btnDone.setVisible(false);
    				guessingPeriod();
    			}
    		}
        };
    	drawingTimer = new Timer(1000, listenForDrawingTimer); //every 1 second
    	
        //adds buttons to buttonPanel (JPanel)
        buttonPanel.add(btnRed);
        buttonPanel.add(btnOrange);
        buttonPanel.add(btnYellow);
        buttonPanel.add(btnGreen);
        buttonPanel.add(btnDarkGreen);
        buttonPanel.add(btnBlue);
        buttonPanel.add(btnViolet);
        buttonPanel.add(btnPink);
        buttonPanel.add(btnMagenta);
        buttonPanel.add(btnTan);
        buttonPanel.add(btnBrown);
        buttonPanel.add(btnGray);
        buttonPanel.add(btnBlack);
        buttonPanel.add(btnWhite);
        buttonPanel.add(btnSize5);
        buttonPanel.add(btnSize10);
        buttonPanel.add(btnSize20);
        buttonPanel.add(btnSize30);
        buttonPanel.add(btnSize40);
        buttonPanel.add(btnSize50);
        buttonPanel.add(btnSize60);
        buttonPanel.add(btnSize70);
        buttonPanel.add(btnSize80);
        buttonPanel.add(btnSize90);
        buttonPanel.add(btnSize100);
        buttonPanel.add(btnSize200);
        
        buttonPanel.add(btnClear);
        buttonPanel.add(btnReady);
        buttonPanel.add(btnDone);
        buttonPanel.add(btnSettings);

        //adds buttonPanel (JPanel) to mainFrame (JFrame)
        this.add(buttonPanel, BorderLayout.WEST);
        
        //adds drawingPanel (JPanel) to mainFrame (JFrame)
        this.add(drawingPanel);
        
        //adds panelForUserMessages (JPanel) to drawingPanel (JPanel)
        drawingPanel.add(panelForUserMessages, FlowLayout.CENTER);
        
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.namePeriod();
        mainFrame.drawingPeriod();
    }
    
    //asks players for names and sets names in Player objects
    public void namePeriod() {
    	//asks and sets name for player one
		playerOne.setName(JOptionPane.showInputDialog("First player, what is your name?"));
    	
    	//asks and sets name for player two
		playerTwo.setName(JOptionPane.showInputDialog("Second player, what is your name?"));
		
		//set the point labels for each player
		drawingPanel.setLblPlayerOnePoints(playerOne.getName(), playerOne.getPoints());
		drawingPanel.setLblPlayerTwoPoints(playerTwo.getName(), playerTwo.getPoints());
    }
    
    //starts the name and drawing period of the game
    public void drawingPeriod() {
    		if(playerOne.getIsTurn()) {
    			//ask what player one is going to draw
    			playerOne.setAnswer(JOptionPane.showInputDialog(playerOne.getName() + ", what are you going to draw?")); 			
    		}
    		else {
    			//ask what player two is going to draw
    			playerTwo.setAnswer(JOptionPane.showInputDialog(playerTwo.getName() + ", what are you going to draw?"));
    		}
    	}
    
    //start guessing period of the game
    public void guessingPeriod() {
    	if(playerOne.getIsTurn()) {
    		//ask player two what player one drew
    		playerTwo.setGuess(JOptionPane.showInputDialog(playerTwo.getName() + ", what did " + playerOne.getName() + " draw?"));
    		
    		//if player two's guess is equal to player one's answer
    		if(playerTwo.getGuess().equals(playerOne.getAnswerModified())) {
    			playerTwo.addPoint();
    			JOptionPane.showInternalMessageDialog(panelForUserMessages, ("Correct! The answer was: " + playerOne.getAnswer()),
    					"", JOptionPane.INFORMATION_MESSAGE);
    			drawingPanel.setLblPlayerTwoPoints(playerTwo.getName(), playerTwo.getPoints());
    		}
    		else {
    			JOptionPane.showInternalMessageDialog(panelForUserMessages, ("Sorry, that's incorrect. The correct answer was: " + playerOne.getAnswer()),
    					"", JOptionPane.INFORMATION_MESSAGE);
    		}
    		
    		playerOne.setIsTurn(false);
    		playerTwo.setIsTurn(true);
    	}
    	else {
    		//ask player one what player two drew
    		playerOne.setGuess(JOptionPane.showInputDialog(playerOne.getName() + ", what did " + playerTwo.getName() + " draw?"));
    		
    		//if player one's guess is equal to player two's answer
    		if(playerOne.getGuess().equals(playerTwo.getAnswerModified())) {
    			playerOne.addPoint();
    			JOptionPane.showInternalMessageDialog(panelForUserMessages, ("Correct! The answer was: " + playerTwo.getAnswer()),
    					"", JOptionPane.INFORMATION_MESSAGE);
    			drawingPanel.setLblPlayerOnePoints(playerOne.getName(), playerOne.getPoints());
    		}
    		else {
    			JOptionPane.showInternalMessageDialog(panelForUserMessages, ("Sorry, that's incorrect. The correct answer was: " + playerTwo.getAnswer()),
    					"", JOptionPane.INFORMATION_MESSAGE);
    		}
    		
    		playerOne.setIsTurn(true);
    		playerTwo.setIsTurn(false);
    	}
    	btnReady.setVisible(true);
    	drawingPanel.setClrDrawingPanel(true);
    	drawingPanel.repaint();
    		
    	//if either player reaches 5 points, otherwise go through another round
    	if((playerOne.getPoints() == 5) || (playerTwo.getPoints() == 5)) {
    		if(playerOne.getPoints() == 5) {
    			JOptionPane.showInternalMessageDialog(panelForUserMessages, (playerOne.getName() + " Wins!"),
    					"Winner!", JOptionPane.INFORMATION_MESSAGE);
    		}
    		else {
    			JOptionPane.showInternalMessageDialog(panelForUserMessages, (playerTwo.getName() + " Wins!"),
    					"Winner!", JOptionPane.INFORMATION_MESSAGE);
    		}
    		playerOne.setPoints(0);
    		playerTwo.setPoints(0);
    		
    		drawingPanel.setLblPlayerOnePoints(playerOne.getName(), playerOne.getPoints());
    		drawingPanel.setLblPlayerTwoPoints(playerTwo.getName(), playerTwo.getPoints());
    		
    		//determine if players want to play again or not
    		int playOrNot = JOptionPane.showConfirmDialog(null,
    			"Do you still want to play?", "", JOptionPane.YES_NO_OPTION);
    		if(playOrNot == JOptionPane.YES_OPTION) {
    			drawingPeriod();
    		}
    		else {
    			//close the JFrame if players do not want to play anymore
    			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    		}
    	}
    	else {
    		drawingPeriod();
    	}
    }
    
    //makes button for changing color according to given background color
    public JButton makeColorButton(Color color) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
        button.setBackground(color);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawingPanel.setCurrColor(color);
                drawingPanel.setXCoord(-200); //outside of the panel (not seen)
                drawingPanel.setYCoord(-200); //outside of the panel (not seen)
            }
        });
        return button;
    }
    
    //makes button for changing brush size according to given brush size
    public JButton makeSizeButton(int size) {
    	JButton button = new JButton();
    	button.setText("" + size);
    	button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				drawingPanel.setCurrSize(size);
				drawingPanel.setXCoord(-200); //outside of the panel (not seen)
				drawingPanel.setYCoord(-200); //outside of the panel (not seen)
			}
    	});
    	return button;
    }
    
    //makes "clear" button
    public JButton makeClrButton() {
    	JButton button = new JButton("Clear");
    	button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingPanel.setClrDrawingPanel(true);
				drawingPanel.setXCoord(-200); //outside of the panel (not seen)
	    		drawingPanel.setYCoord(-200); //outside of the panel (not seen)
				drawingPanel.repaint();
			}
    	});
    	return button;
    }
    
    //makes "ready" button
    public JButton makeRdyButton() {
    	JButton button = new JButton("Ready");
    	button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.setVisible(false);
				btnDone.setVisible(true);
				drawingPanel.setIsDrawingTime(true);
				drawingTimer.start();
				timerCounter = 120;
				drawingPanel.setXCoord(-200); //outside of the panel (not seen)
				drawingPanel.setYCoord(-200); //outside of the panel (not seen)
			}
    	});
    	return button;
    }
    
    //makes "done"button
    public JButton makeDoneButton() {
    	JButton button = new JButton("Done");
    	button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				button.setVisible(false);
				timerCounter = 0;
			}
    	});
    	return button;
    }
    
    //makes "settings" button
    public JButton makeSettingsButton() {
    	JButton button = new JButton("Settings");
    	
    	JPopupMenu subSettings = new JPopupMenu();
				JMenuItem itemSetPlayerOnePoints = new JMenuItem("Set player one's points");
				JMenuItem itemSetPlayerTwoPoints = new JMenuItem("Set player two's points");
				
				itemSetPlayerOnePoints.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						playerOne.setPoints(Integer.parseInt(JOptionPane.showInputDialog(
									       "Enter a number to set player one's score to:")));
						drawingPanel.setLblPlayerOnePoints(playerOne.getName(), playerOne.getPoints());
					}
				});
				
				itemSetPlayerTwoPoints.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						playerTwo.setPoints(Integer.parseInt(JOptionPane.showInputDialog(
								"Enter a number to set player two's score to:")));
						drawingPanel.setLblPlayerTwoPoints(playerTwo.getName(), playerTwo.getPoints());
					}
				});
				
				subSettings.add(itemSetPlayerOnePoints);
				subSettings.add(itemSetPlayerTwoPoints);
    	
    	button.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				subSettings.show(e.getComponent(), e.getX(), e.getY());
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
    	});
    	return button;
    }
}
