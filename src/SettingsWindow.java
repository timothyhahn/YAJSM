import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

class SettingsWindow extends JFrame implements ActionListener, KeyListener {

	GameWindow gw = null;
	JRadioButton rbBeginner;
	JRadioButton rbIntermediate;
	JRadioButton rbExpert;
	JRadioButton rbCustom;
	JTextField tfHeight;
	JTextField tfWidth;
	JTextField tfMines;
	JSlider sSFX;
	JSlider sMusic;
	JCheckBox cbHints;
	JCheckBox cbRetries;
	JCheckBox cbColorBlind;
	
	public SettingsWindow(GameWindow gw) {
		this.gw = gw;
	}
	public void display() {
		JPanel pSettings = new JPanel();
		GridLayout lSettings = new GridLayout(0,2);
		pSettings.setLayout(lSettings);

		
		//Add Difficulty Settings
		JPanel pDifficulty = new JPanel();
		pDifficulty.setLayout(new BoxLayout(pDifficulty, BoxLayout.Y_AXIS));
		rbBeginner = new JRadioButton("Beginner: 9x9, 10 mines");
		rbIntermediate = new JRadioButton("Intermediate: 16x16, 40 mines");
		rbExpert =  new JRadioButton("Expert: 22x22, 99 mines");
		rbCustom = new JRadioButton("Custom: Size from 5-50, Mines from 1 to 99 ");
		ButtonGroup bgDifficulty = new ButtonGroup();
		bgDifficulty.add(rbBeginner);
		bgDifficulty.add(rbIntermediate);
		bgDifficulty.add(rbExpert);
		bgDifficulty.add(rbCustom);
		rbBeginner.setToolTipText("Beginner");
		rbIntermediate.setToolTipText("Intermediate");
		rbExpert.setToolTipText("Expert");
		rbCustom.setToolTipText("Custom");
		rbBeginner.addActionListener(this);
		rbIntermediate.addActionListener(this);
		rbExpert.addActionListener(this);
		rbCustom.addActionListener(this);
		
		
		tfHeight = new JTextField();
		tfHeight.setMaximumSize(new Dimension(50,20));
		tfWidth = new JTextField();
		tfWidth.setMaximumSize(new Dimension(50,20));
		tfMines = new JTextField();
		tfMines.setMaximumSize(new Dimension(50,20));
		tfHeight.setEditable(false);
		tfWidth.setEditable(false);
		tfMines.setEditable(false);
		tfHeight.addKeyListener(this);
		tfWidth.addKeyListener(this);
		tfMines.addKeyListener(this);
		tfHeight.setToolTipText("Height");
		tfWidth.setToolTipText("Width");
		tfMines.setToolTipText("Mines");
		
		pDifficulty.add(rbBeginner);
		pDifficulty.add(rbIntermediate);
		pDifficulty.add(rbExpert);
		pDifficulty.add(rbCustom);
		pDifficulty.add(new JLabel("Height"));
		pDifficulty.add(tfHeight);
		pDifficulty.add(new JLabel("Width"));
		pDifficulty.add(tfWidth);
		pDifficulty.add(new JLabel("Mines"));
		pDifficulty.add(tfMines);
		pDifficulty.setBorder(BorderFactory.createLineBorder(Color.black));
		
		rbBeginner.addActionListener(this);
		rbIntermediate.addActionListener(this);
		rbExpert.addActionListener(this);
		rbCustom.addActionListener(this);
		
		//Load Difficulty Settings
		switch(gw.settings.difficultyMode){
		case 1:
			rbBeginner.setSelected(true);
			break;
		case 2:
			rbIntermediate.setSelected(true);
			break;
		case 3:
			rbExpert.setSelected(true);
			break;
		default:
			rbCustom.setSelected(true);
			tfHeight.setEditable(true);
			tfWidth.setEditable(true);
			tfMines.setEditable(true);
			tfHeight.setText(gw.settings.boardHeight + "");
			tfWidth.setText(gw.settings.boardWidth + "");
			tfMines.setText(gw.settings.mineCount + "");
			break;
		}
		
		
		//Add Configuration Settings
		
		JPanel pConfiguration = new JPanel();
		pConfiguration.setLayout(new BoxLayout(pConfiguration, BoxLayout.Y_AXIS));
		
		sSFX = new JSlider(JSlider.HORIZONTAL,0,100,50);
		sMusic = new JSlider(JSlider.HORIZONTAL,0,100,50);
		cbHints = new JCheckBox("Show Hints");
		cbRetries = new JCheckBox("Allow Retries");
		cbColorBlind = new JCheckBox("Color Blind Mode");
		
		JPanel pButtons = new JPanel(new FlowLayout());
		JButton bAccept = new JButton("Accept");
		JButton bCancel = new JButton("Cancel");
		pButtons.add(bAccept);
		pButtons.add(bCancel);
		bAccept.addActionListener(this);
		bCancel.addActionListener(this);
		
		pConfiguration.add(new JLabel("Sound Effects Volume"));
		pConfiguration.add(sSFX);
		pConfiguration.add(new JLabel("Music Volume"));
		pConfiguration.add(sMusic);
		pConfiguration.add(cbHints);
		pConfiguration.add(cbRetries);
		pConfiguration.add(cbColorBlind);
		pConfiguration.add(Box.createRigidArea(new Dimension(20,30)));
		pConfiguration.add(pButtons);
		
		Component[] cAll = pConfiguration.getComponents();
		for(int i = 0; i < cAll.length; i++) {
			((JComponent)cAll[i]).setAlignmentX(LEFT_ALIGNMENT);
		}
		
		//Load Configuration Settings
		
		sSFX.setValue(gw.settings.soundVolume);
		sMusic.setValue(gw.settings.musicVolume);
		cbHints.setSelected(gw.settings.showHints);
		cbRetries.setSelected(gw.settings.allowRetries);
		cbColorBlind.setSelected(gw.settings.colorBlindMode);
		
		pSettings.add(pDifficulty);
		pSettings.add(pConfiguration);
		
		add(pSettings);
		
		setTitle("YAJSM - Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource().getClass() == JButton.class){
			JButton jbToRead = (JButton)arg0.getSource();
			String selection = jbToRead.getText();
			
			if(selection.equals("Accept")) {
				//Save Difficulty
				if(rbBeginner.isSelected()) {
					gw.settings.difficultyMode = 1;
					gw.settings.boardHeight = 9;
					gw.settings.boardWidth = 9;
					gw.settings.mineCount = 10;
				} else if(rbIntermediate.isSelected()) {
					gw.settings.difficultyMode = 2;
					gw.settings.boardHeight = 16;
					gw.settings.boardWidth = 16;
					gw.settings.mineCount = 40;
				} else if(rbExpert.isSelected()) {
					gw.settings.difficultyMode = 3;
					gw.settings.boardHeight = 22;
					gw.settings.boardWidth = 22;
					gw.settings.mineCount = 99;
				} else {
					gw.settings.difficultyMode = 4;
					gw.settings.boardHeight = Integer.parseInt(tfHeight.getText());
					gw.settings.boardWidth = Integer.parseInt(tfWidth.getText());
					gw.settings.mineCount = Integer.parseInt(tfMines.getText());
				}
				//Save Configuration
				gw.settings.soundVolume = sSFX.getValue();
				gw.settings.musicVolume = sMusic.getValue();
				gw.settings.showHints = cbHints.isSelected();
				gw.settings.allowRetries = cbRetries.isSelected();
				gw.settings.colorBlindMode = cbColorBlind.isSelected();
				//Save settings
				gw.settings.save();
				this.setVisible(false);
				gw.newGame(false);
			} else if (selection.equals("Cancel")) {
				this.setVisible(false);
			}
			
		} else if (arg0.getSource().getClass() == JRadioButton.class) {
			JRadioButton selected = (JRadioButton) arg0.getSource();
			if(selected.getToolTipText().equals("Custom")) {
				tfHeight.setEditable(true);
				tfWidth.setEditable(true);
				tfMines.setEditable(true);
			} else {
				tfHeight.setEditable(false);
				tfWidth.setEditable(false);
				tfMines.setEditable(false);
			}
		}
	
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {

		if(arg0.getSource().getClass() == JTextField.class) {
			JTextField tfTyped = (JTextField) arg0.getSource();
			try {
				//int value = Integer.parseInt(tfTyped.getText());
				if(tfTyped.getToolTipText().equals("Mines")) {
					int mines = Integer.parseInt(tfMines.getText());
					int width = Integer.parseInt(tfWidth.getText());
					int height = Integer.parseInt(tfHeight.getText());
					if(mines > (width * height )) {
						tfTyped.setText("");
						JOptionPane.showMessageDialog(this, "You can't have more mines than there are spaces.");
					}
				} else {
					if(tfTyped.getToolTipText().equals("Width")) {
						if(Integer.parseInt(tfWidth.getText()) < 1 || Integer.parseInt(tfWidth.getText()) > 50  ) {
							tfTyped.setText("");
						}
					} else {
						if(Integer.parseInt(tfHeight.getText()) < 1 || Integer.parseInt(tfHeight.getText()) > 50  ) {
							tfTyped.setText("");
						}
					}
				}
			} catch (NumberFormatException nfe) {
				tfTyped.setText("");
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}