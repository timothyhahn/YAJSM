import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

class SettingsWindow extends JFrame implements ActionListener {

	GameWindow gw = null;
	public SettingsWindow(GameWindow gw) {
		this.gw = gw;
	}
	public void display() {
		JPanel pSettings = new JPanel();
		GridLayout lSettings = new GridLayout(0,2);
		//lStatistics.setHgap(10);
		pSettings.setLayout(lSettings);
		
		JPanel pDifficulty = new JPanel();
		pDifficulty.setLayout(new BoxLayout(pDifficulty, BoxLayout.Y_AXIS));
		pDifficulty.add(new JRadioButton("Beginner: " +
										"9x9, 10 mines"));
		pDifficulty.add(new JRadioButton("Intermediate: " +
										"16x16, 40 mines"));
		pDifficulty.add(new JRadioButton("Expert: " +
										  "22x22, 99 mines"));
		pDifficulty.add(new JRadioButton("Custom: "));
		pDifficulty.add(new JLabel("Height"));
		JTextField tfHeight = new JTextField();
		tfHeight.setMaximumSize(new Dimension(50,20));
		pDifficulty.add(tfHeight);
		pDifficulty.add(new JLabel("Width"));
		JTextField tfWidth = new JTextField();
		tfWidth.setMaximumSize(new Dimension(50,20));
		pDifficulty.add(tfWidth);
		pDifficulty.add(new JLabel("Mines"));
		JTextField tfMines = new JTextField();
		tfMines.setMaximumSize(new Dimension(50,20));
		pDifficulty.add(tfMines);
		pDifficulty.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel pConfiguration = new JPanel();
		pConfiguration.setLayout(new BoxLayout(pConfiguration, BoxLayout.Y_AXIS));
		pConfiguration.add(new JLabel("Sound Effects Volume"));
		JSlider sSFX = new JSlider(JSlider.HORIZONTAL,0,100,50);
		pConfiguration.add(sSFX);
		pConfiguration.add(new JLabel("Music Volume"));
		JSlider sMusic = new JSlider(JSlider.HORIZONTAL,0,100,50);
		pConfiguration.add(sMusic);
		pConfiguration.add(new JCheckBox("Show Hints"));
		pConfiguration.add(new JCheckBox("Allow Retries"));
		pConfiguration.add(new JCheckBox("Color Blind Mode"));
		
		pSettings.add(pDifficulty);
		pSettings.add(pConfiguration);
		
		add(pSettings);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}