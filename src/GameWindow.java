import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.InputStream;
import java.net.URL;

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
    MineButton mineButtons[][];
    JPanel pMaster = null;
    JPanel pMines = null;
    JTextField tfTime = null;
    JTextField tfMines = null;
    long time;
    int minesLeft;
    Timer timer = null;
    boolean firstClick;
    boolean firstMove;
    MineField mf;
    public GameWindow() {
    	mf = new MineField();
    	mineButtons = new MineButton[mf.boardWidth][mf.boardHeight];
    	mf.generateMines();
    	mf.generateNumbers();
    	minesLeft = mf.mineCount;
    	//mf.display();
    	firstClick = true;
    	firstMove = true;
    	time = 0;
    }
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
		centerWindow(this);
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
		tfMines = new JTextField(""+minesLeft);
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
		JTextArea lInfo = new JTextArea("Here I will say lots of things to advise/insult the player.");
		lInfo.setPreferredSize(new Dimension(25 * mf.boardWidth,50));
		lInfo.setLineWrap(true);
		lInfo.setEditable(false);
		
		pInfo.add(lInfo);
		
		return pInfo;
	}
	
	public JMenuBar setUpMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		gameMenu.setMnemonic(KeyEvent.VK_G);
		
		JMenuItem miNewGame = new JMenuItem("New Game");
		miNewGame.setMnemonic(KeyEvent.VK_N);
		miNewGame.addActionListener(this);
		JMenuItem miSettings = new JMenuItem("Settings");
		miSettings.setMnemonic(KeyEvent.VK_E);
		miSettings.addActionListener(this);
		JMenuItem miStats = new JMenuItem("Statistics");
		miStats.setMnemonic(KeyEvent.VK_S);
		miStats.addActionListener(this);
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
		miTutorial.addActionListener(this);
		JMenuItem miRules = new JMenuItem("Rules");
		miRules.setMnemonic(KeyEvent.VK_R);
		miRules.addActionListener(this);
		JMenuItem miAbout = new JMenuItem("About");
		miAbout.addActionListener(this);
		miAbout.setMnemonic(KeyEvent.VK_A);
		
		helpMenu.add(miTutorial);
		helpMenu.add(miRules);
		helpMenu.add(miAbout);
		
		menuBar.add(helpMenu);
		
		return menuBar;
	}
	public void centerWindow(JFrame jf) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width / 2) - (jf.getWidth() / 2);
		int y = (screen.height/2) - (jf.getHeight() / 2);
		jf.setBounds(x,y, jf.getWidth(), jf.getHeight());
	}
	public JPanel setUpMines() {
		pMines = new JPanel();
		pMines.setMinimumSize(new Dimension(25 * mf.boardWidth,25 * mf.boardHeight));
		pMines.setMaximumSize(new Dimension(25 * mf.boardWidth, 25 * mf.boardHeight));
		
		pMines.setLayout(new GridLayout(mf.boardHeight,mf.boardWidth));
		pMines.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ImageIcon icon = null;
		try{
			URL uTiles = getClass().getResource("res/images/"+ currentTheme +"/tile.png");
			if(uTiles != null) {
				icon = new ImageIcon(uTiles, "A tile");
			} else {
				imagesMissing();
			}
		
		for(int i = 0; i < mf.boardWidth; i++) {
			for(int j = 0; j < mf.boardHeight; j++) {
				MineButton mine = new MineButton(icon);
				mine.setContentAreaFilled(true);
				mine.setPreferredSize(new Dimension(25,25));
				
				mineButtons[i][j] = new MineButton();
				mineButtons[i][j] = mine;
				mineButtons[i][j].y = j;
				mineButtons[i][j].x = i;
				mineButtons[i][j].addMouseListener(this);
				pMines.add(mineButtons[i][j]);
			}
		}
		
		} catch (NullPointerException npe) {
			npe.printStackTrace();
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
		
		JPanel pAbout = new JPanel();
		JTextArea taAbout = new JTextArea("");
		
		taAbout.setText("YAJSM - Yet Another Java Swing Minesweeper\n" +
				"Version 0.2\n" +
				"Created by Timothy Hahn\n" +
				"Drexel University\n");
		taAbout.setEditable(false);
		
		pAbout.add(taAbout);
		jfAbout.add(pAbout);
		jfAbout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jfAbout.pack();
		jfAbout.setVisible(true);
		centerWindow(jfAbout);
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
	
	public void newButtonAction() {
		JOptionPane.showMessageDialog(this, "For now, the new button will a MineField that is 16x16 with 40 mines (an intermediate level game).\n" +
											"Later this will simply ask to confirm if the user wants a new game and will create a game based on the settings.");
		mf = new MineField(16,16,40);
    	mineButtons = new MineButton[mf.boardWidth][mf.boardHeight];
    	mf.generateMines();
    	mf.generateNumbers();
    	minesLeft = mf.mineCount;
    	//mf.display();
    	firstClick = true;
    	firstMove = true;
    	time = 0;
		pMines.removeAll();
		pMines.setMinimumSize(new Dimension(25 * mf.boardWidth,25 * mf.boardHeight));
		pMines.setMaximumSize(new Dimension(25 * mf.boardWidth, 25 * mf.boardHeight));
		
		pMines.setLayout(new GridLayout(mf.boardHeight,mf.boardWidth));
		pMines.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ImageIcon icon = new ImageIcon();
		try{
			URL uTiles = getClass().getResource("res/images/" + currentTheme + "/tile.png");
			if(uTiles != null) {
				icon = new ImageIcon(uTiles, "A tile");
			} else {
				imagesMissing();
			}
		for(int i = 0; i < mf.boardWidth; i++) {
			for(int j = 0; j < mf.boardHeight; j++) {
				MineButton mine = new MineButton(icon);
				mine.setContentAreaFilled(true);
				mine.setPreferredSize(new Dimension(25,25));
				mineButtons[i][j] = mine;
				mineButtons[i][j].addMouseListener(this);
				mineButtons[i][j].x = i;
				mineButtons[i][j].y = j;
				pMines.add(mineButtons[i][j]);
			
			}
		}
		} catch (NullPointerException npe) {
			imagesMissing();
		} 
		
		pack();
	}
	
	public void imagesMissing() {
		JOptionPane.showMessageDialog(this, "There are images missing from the directory. Please redownload the application");
		System.exit(0);
		
	}
	
	public void revealZeros(MineButton source){
		if(source.getIcon().toString().contains("tile")){
			if(mf.mines[source.x][source.y] == 0) {
				revealButton(source);
				boolean[] f = mf.checkSurrounding(source.x, source.y);
				if(f[1])
					revealZeros(mineButtons[source.x - 1][source.y]);
				if(f[3])
					revealZeros(mineButtons[source.x][source.y - 1]);
				if(f[4])
					revealZeros(mineButtons[source.x][source.y + 1]);
				if(f[6])
					revealZeros(mineButtons[source.x + 1][source.y]);
			} 
			else if(mf.mines[source.x][source.y] == 9){
				revealButton(source);
				for(int i = 0; i < mf.mineCount; i++){
					revealButton(mineButtons[mf.minePos[i][0]][mf.minePos[i][1]]);
				}
				JOptionPane.showMessageDialog(this, "You lost");
				timer.stop();
			}
			else
			{
				revealButton(source);
			}
		}
	}
	public void revealButton(MineButton mb) {
		try{
		    URL uFloor = null;

			if(mf.mines[mb.x][mb.y] != 9) {
				uFloor = getClass().getResource("res/images/" + currentTheme + "/floor.png");
				if(mf.mines[mb.x][mb.y] > 0) 
					uFloor = getClass().getResource("res/images/" + currentTheme + "/" + mf.mines[mb.x][mb.y] + ".png");
			} else {
				
				uFloor = getClass().getResource("res/images/" + currentTheme + "/mine.png");
			}
			
			if(uFloor != null) {
				ImageIcon icon = new ImageIcon(uFloor, "A floor");
				mb.setIcon(icon);
			} else {
				imagesMissing();
			}
			
		} catch (NullPointerException npe) {
			imagesMissing();
		} 
	}
	public void playSound(MineButton mb) {
		if(mb.getIcon().toString().contains("tile")) {
			if(mf.mines[mb.x][mb.y] != 9) {
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void  run() {
						try {
							InputStream is = null;
							if(firstClick) {
								firstClick = false;
								 is = getClass().getResourceAsStream("res/audio/" + currentTheme + "/select1.mp3");
							} else {
								firstClick = true;
								 is = getClass().getResourceAsStream("res/audio/"+ currentTheme + "/select2.mp3");
							}
							Player pl = new Player(is);
							pl.play();
						} catch (JavaLayerException jle) {
							jle.printStackTrace();
						}
						
					}
				});
				
			} else {
				
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void  run() {
						try {
							InputStream is = getClass().getResourceAsStream("res/audio/" + currentTheme + "/explode.mp3");
							Player pl = new Player(is);
							pl.play();
							
						} catch (JavaLayerException jle) {
							jle.printStackTrace();
						}
						
					}
				});
				minesLeft--;
				tfMines.setText("" + minesLeft);
			}
		}
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
		MineButton mb = (MineButton) o;

		boolean isMine = true;
		if(firstMove) {
			firstMove = false;
			while(isMine){
				if(mf.mines[mb.x][mb.y] == 9){
					mf.generateMines();
					mf.generateNumbers();
			//		System.out.println("New board generated as first click blew up");
					//mf.display();
				} else {
					isMine = false;
				}
				
			}
		}
		playSound(mb);
		revealZeros(mb);
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
			String selection = miToRead.getText();
			
			if(selection.equals("Quit")) {
				quitButtonAction();
			} else if(selection.equals("New Game")) {
				newButtonAction();
			} else if(selection.equals("Settings")) {
				SettingsWindow settings = new SettingsWindow(this);
				settings.display();
				centerWindow(settings);
			} else if(selection.equals("Statistics")) {
				StatisticsWindow statistics = new StatisticsWindow(this);
				statistics.display();
				centerWindow(statistics);
			} else if(selection.equals("Themes")) {
				ThemesWindow themes = new ThemesWindow(this);
				themes.display();
				centerWindow(themes);
			} else if(selection.equals("Rules")) {
				RulesWindow rules = new RulesWindow(this);
				rules.display();
				centerWindow(rules);
			} else if(selection.equals("Tutorial")) {
				TutorialWindow tutorial = new TutorialWindow(this);
				tutorial.display();
				centerWindow(tutorial);
			} else if(selection.equals("About")) {
				aboutWindow();
			}
		}
	
		
	}

}
