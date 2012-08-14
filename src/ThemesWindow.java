import java.awt.Dimension;
import java.awt.GridLayout;
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
	String currentTheme = "default";
	GameWindow gw = null;
	public ThemesWindow(GameWindow gw) {
		this.gw = gw;
	}
	public void display() {
		
		JRadioButton rbDefault = new JRadioButton("Default");
		JRadioButton rbDark = new JRadioButton("Dark");
		JRadioButton rbUnicorn = new JRadioButton("Unicorn");
		rbDefault.addActionListener(this);
		rbDark.addActionListener(this);
		rbUnicorn.addActionListener(this);
		
		ButtonGroup bgThemes = new ButtonGroup();
		bgThemes.add(rbDefault);
		bgThemes.add(rbDark);
		bgThemes.add(rbUnicorn);
		
		JPanel pThemes = new JPanel(new GridLayout(0,1));
		
		rbDefault.setSelected(true);
		pThemes.add(rbDefault);
		pThemes.add(rbDark);
		pThemes.add(rbUnicorn);
		
		add(pThemes);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().getClass() == JRadioButton.class) {
			JRadioButton rbToRead = (JRadioButton)arg0.getSource();
			if(rbToRead.getText().equals("Default")) {
				currentTheme = "default";
			} else if (rbToRead.getText().equals("Dark")){
				currentTheme = "dark";
			} else if (rbToRead.getText().equals("Unicorn")) {
				currentTheme = "unicorn";
			}
			ImageIcon icon = new ImageIcon();
			try{
				URL uTiles = getClass().getResource("res/images/" + currentTheme + "/tile.png");
				if(uTiles != null) {
					icon = new ImageIcon(uTiles, "A tile");
				} else {
					gw.imagesMissing();
				}
			gw.pMines.removeAll();
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					JButton mine = new JButton(icon);
					mine.setContentAreaFilled(true);
					mine.setPreferredSize(new Dimension(25,25));
					gw.mines[i][j] = mine;
					gw.mines[i][j].addMouseListener(gw);
					gw.pMines.add(gw.mines[i][j]);
				}
			}
			
			} catch (NullPointerException npe) {
				gw.imagesMissing();
			} 
			gw.invalidate();
			gw.validate();
		}
		
	}
}