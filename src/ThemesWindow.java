import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class ThemesWindow extends JFrame implements ActionListener {

	GameWindow gw = null;
	public ThemesWindow(GameWindow gw) {
		this.gw = gw;
	}
	public void display() {
		setTitle("YAJSM - Themes");
		JButton bDefault = new JButton();
		JButton bDark = new JButton();
		JButton bUnicorn = new JButton();
		
		URL uTitle = null;
		uTitle = getClass().getResource("res/images/default/title.png");
		ImageIcon icon = new ImageIcon(uTitle, "default");
		bDefault.setIcon(icon);
		
		URL uDarkTitle = null;
		uDarkTitle = getClass().getResource("res/images/dark/title.png");
		ImageIcon darkIcon = new ImageIcon(uDarkTitle, "dark");
		bDark.setIcon(darkIcon);
		
		URL uUnicornTitle = null;
		uUnicornTitle = getClass().getResource("res/images/unicorn/title.png");
		ImageIcon unicornIcon = new ImageIcon(uUnicornTitle, "unicorn");
		bUnicorn.setIcon(unicornIcon);
		
		bDefault.addActionListener(this);
		bDark.addActionListener(this);
		bUnicorn.addActionListener(this);

		JPanel pThemes = new JPanel(new GridLayout(0,1));

		pThemes.add(bDefault);
		pThemes.add(bDark);
		pThemes.add(bUnicorn);
		
		add(pThemes);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().getClass() == JButton.class) {
			JButton bToRead = (JButton)arg0.getSource();
			if(bToRead.getIcon().toString().contains("default")) {
				gw.currentTheme = "default";
			}else if(bToRead.getIcon().toString().contains("dark")) {
				gw.currentTheme = "dark";
			} else if (bToRead.getIcon().toString().contains("unicorn")) {
				gw.currentTheme = "unicorn";
			}
			ImageIcon icon = new ImageIcon();
			try{
				URL uTiles = getClass().getResource("res/images/" + gw.currentTheme + "/tile.png");
				if(uTiles != null) {
					icon = new ImageIcon(uTiles, "A tile");
				} else {
					gw.imagesMissing();
				}
			gw.pMines.removeAll();
			for(int i = 0; i < gw.mf.boardWidth; i++) {
				for(int j = 0; j < gw.mf.boardHeight; j++) {
					MineButton mine = new MineButton(icon);
					mine.setContentAreaFilled(true);
					mine.setPreferredSize(new Dimension(25,25));
					gw.mineButtons[i][j] = mine;
					gw.mineButtons[i][j].addMouseListener(gw);
					gw.mineButtons[i][j].x = i;
					gw.mineButtons[i][j].y = j;
					gw.pMines.add(gw.mineButtons[i][j]);
				
				}
			}
			gw.pMines.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} catch (NullPointerException npe) {
				gw.imagesMissing();
			} 
			gw.invalidate();
			gw.validate();
			
			setVisible(false);
		}
		
		
	}
}