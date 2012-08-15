import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class GameWindow extends JFrame implements MouseListener, ActionListener{
	int mineWidth = 30;
	int mineHeight = 16;
    JButton mines[][] = new JButton[mineWidth][mineHeight];
    JPanel pMaster = null;
    JPanel pMines = null;
    JTextField tfTime = null;
    JTextField tfMines = null;
    long time = 0;
    int mineCount = 10;
    Timer timer = null;
    boolean first = true;
    
    String currentTheme = "default";
	public void setUpLayout() {
		setTitle("YAJSM - Yet Another Java Swing Minesweeper");
		  System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Test");
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		pMaster = new JPanel();
		pMaster.setLayout(new BoxLayout(pMaster,BoxLayout.Y_AXIS));
		setJMenuBar(setUpMenu());
		pMaster.add(setUpStats());
		pMaster.add(setUpMines());
		pMaster.add(setUpInfo());
		add(pMaster);
		
		startTimer();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
	}
	public JPanel setUpStats() {
		JPanel pStats = new JPanel();
		pStats.setLayout(new GridLayout(2,2));
		
		JLabel lTime = new JLabel("Time");
		lTime.setHorizontalAlignment(JLabel.CENTER);
		tfTime = new JTextField(""+time);
		tfTime.setEditable(false);
		tfTime.setHorizontalAlignment(JTextField.CENTER);
		JLabel lMines = new JLabel("Mines");
		lMines.setHorizontalAlignment(JLabel.CENTER);
		tfMines = new JTextField(""+mineCount);
		tfMines.setHorizontalAlignment(JTextField.CENTER);
		tfMines.setEditable(false);
		
		pStats.add(lTime);
		pStats.add(lMines);
		pStats.add(tfTime);
		pStats.add(tfMines);
		return pStats;
	}
	public JPanel setUpInfo() {
		JPanel pInfo = new JPanel();
		JTextArea lInfo = new JTextArea("Here I will say belittling little facts about your ugly mother");
		lInfo.setPreferredSize(new Dimension(25 * mineWidth,50));
		lInfo.setLineWrap(true);
		lInfo.setEditable(false);
		lInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		pInfo.add(lInfo);
		
		return pInfo;
	}
	
	public JMenuBar setUpMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		gameMenu.setMnemonic(KeyEvent.VK_G);
		
		JMenuItem miNewGame = new JMenuItem("New Game");
		miNewGame.setMnemonic(KeyEvent.VK_N);
		JMenuItem miSettings = new JMenuItem("Settings");
		miSettings.setMnemonic(KeyEvent.VK_E);
		JMenuItem miStats = new JMenuItem("Statistics");
		miStats.setMnemonic(KeyEvent.VK_S);
		JMenuItem miThemes = new JMenuItem("Themes");
		miThemes.setMnemonic(KeyEvent.VK_T);
		miThemes.addActionListener(this);
		JMenuItem miQuit = new JMenuItem("Quit");
		miQuit.setMnemonic(KeyEvent.VK_Q);
		miQuit.addActionListener(this);
		boolean isMacOS = System.getProperty("mrj.version") != null;
	
		gameMenu.add(miNewGame);
		gameMenu.addSeparator();
		gameMenu.add(miSettings);
		gameMenu.add(miStats);
		gameMenu.add(miThemes);
		if(!isMacOS) {
			gameMenu.addSeparator();
			gameMenu.add(miQuit);		
		}
		menuBar.add(gameMenu);
		
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		JMenuItem miTutorial = new JMenuItem("Tutorial");
		miTutorial.setMnemonic(KeyEvent.VK_T);
		JMenuItem miRules = new JMenuItem("Rules");
		miRules.setMnemonic(KeyEvent.VK_R);
		JMenuItem miAbout = new JMenuItem("About");
		miAbout.addActionListener(this);
		miAbout.setMnemonic(KeyEvent.VK_A);
		
		helpMenu.add(miTutorial);
		helpMenu.add(miRules);
		helpMenu.add(miAbout);
		
		menuBar.add(helpMenu);
		
		return menuBar;
	}
	
	public JPanel setUpMines() {
		pMines = new JPanel();
		pMines.setMinimumSize(new Dimension(25 * mineWidth,25 * mineHeight));
		pMines.setMaximumSize(new Dimension(25 * mineWidth, 25 * mineHeight));
		
		pMines.setLayout(new GridLayout(mineHeight,mineWidth));
		pMines.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ImageIcon icon = null;
		try{
			URL uTiles = getClass().getResource("res/images/"+ currentTheme +"/tile.png");
			if(uTiles != null) {
				icon = new ImageIcon(uTiles, "A tile");
			} else {
				imagesMissing();
			}
		
		for(int i = 0; i < mineWidth; i++) {
			for(int j = 0; j < mineHeight; j++) {
				JButton mine = new JButton(icon);
				mine.setContentAreaFilled(true);
				mine.setPreferredSize(new Dimension(25,25));
				
				mines[i][j] = mine;
				mines[i][j].addMouseListener(this);
				pMines.add(mines[i][j]);
			}
		}
		
		} catch (NullPointerException npe) {
			imagesMissing();
		} 
		return pMines;
	}
	
	public void startTimer() {

		int speed = 1000;
		timer = new Timer(speed, this);
		timer.start();
	}
	
	public void aboutWindow() {
		JFrame jfAbout = new JFrame("About");

		JTextArea taAbout = new JTextArea("");

		taAbout.setText("hi");
		taAbout.setEditable(false);

		jfAbout.add(taAbout);
		jfAbout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jfAbout.pack();
		jfAbout.setVisible(true);

	}
	
	public void quitButtonAction() {
		final JDialog dQuit = new JDialog(this,"Quit?", true);
		final JOptionPane opQuit = new JOptionPane("Would you like to quit?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
		dQuit.setContentPane(opQuit);
		opQuit.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				 String prop = e.getPropertyName();
		            if (dQuit.isVisible() 
		             && (e.getSource() == opQuit)
		             && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
		                dQuit.setVisible(false);
		            }
			}
		});
		dQuit.pack();
		dQuit.setVisible(true);
		
		int result = ((Integer)opQuit.getValue()).intValue();
		if(result == JOptionPane.YES_OPTION){
			System.exit(0);
		} else{
			dQuit.setVisible(false);
		}
	}
	
	public void imagesMissing() {
		JOptionPane.showMessageDialog(this, "There are images missing from the directory. Please redownload the application");
		System.exit(0);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		JButton b = (JButton) o;
		try{
		    final Random randomGenerator = new Random();
		    URL uFloor = null;
			if(randomGenerator.nextInt(100) > 10) {
				uFloor = getClass().getResource("res/images/" + currentTheme + "/floor.png");
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void  run() {
						try {
							InputStream is = null;
							if(first) {
								first = false;
								 is = getClass().getResourceAsStream("res/audio/" + currentTheme + "/select1.mp3");
							} else {
								first = true;
								 is = getClass().getResourceAsStream("res/audio/"+ currentTheme + "/select2.mp3");
							}
							Player pl = new Player(is);
							pl.play();
							
						} catch (JavaLayerException jle) {
							// TODO Auto-generated catch block
							jle.printStackTrace();
						}
						
					}
				});
				
			} else {
				
				uFloor = getClass().getResource("res/images/" + currentTheme + "/mine.png");
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void  run() {
						try {
							InputStream is = getClass().getResourceAsStream("res/audio/" + currentTheme + "/explode.mp3");
							Player pl = new Player(is);
							pl.play();
							
						} catch (JavaLayerException jle) {
							// TODO Auto-generated catch block
							jle.printStackTrace();
						}
						
					}
				});
				mineCount--;
				tfMines.setText("" + mineCount);
			}
			
			if(uFloor != null) {
				ImageIcon icon = new ImageIcon(uFloor, "A floor");
				b.setIcon(icon);
			} else {
				imagesMissing();
			}
			
		} catch (NullPointerException npe) {
			imagesMissing();
		} 
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().getClass() == Timer.class){ 
			time++;
			tfTime.setText(""+time);
		}
		
		if(arg0.getSource().getClass() == JMenuItem.class){
			JMenuItem miToRead = (JMenuItem)arg0.getSource();
			if(miToRead.getText().equals("Quit")) {
				quitButtonAction();
			} else if(miToRead.getText().equals("Themes")) {
				ThemesWindow themes = new ThemesWindow(this);
				themes.display();
			} else if(miToRead.getText().equals("About")) {
				aboutWindow();
			}
		}
	
		
	}

}
