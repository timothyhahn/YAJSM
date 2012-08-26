import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

class RulesWindow extends JFrame implements ActionListener {

	GameWindow gw = null;
	public RulesWindow(GameWindow gw) {
		this.gw = gw;
	}
	public void display() {
		
		setTitle("YAJSM - Rules");
		JPanel pRules = new JPanel();
		pRules.setLayout(new BoxLayout(pRules, BoxLayout.Y_AXIS));
		Font fTitle = new Font("Verdana", Font.BOLD, 32);
		Font fHeader = new Font("Verdana", Font.BOLD, 20);
		Font fText  = new Font("Verdana", Font.PLAIN, 16);
		
		JLabel lTitle = new JLabel("Minesweeper Rules");
		lTitle.setFont(fTitle);
		JLabel lhObjective = new JLabel("Objective:");
		lhObjective.setFont(fHeader);
		JTextArea taObjective = new JTextArea("On the Minesweeper board, hiding underneath each tile are " +
				"mines and empty positions. In order to win you must determine where the mines are located.");
		taObjective.setEditable(false);
		taObjective.setLineWrap(true);
		taObjective.setWrapStyleWord(true);
		taObjective.setFont(fText);
		
		JLabel lhHowTo = new JLabel("How To Play: ");
		lhHowTo.setFont(fHeader);
		JTextArea taHowTo = new JTextArea("To start, press any location on the board. You will reveal an empty tile either labeled with a number or surrounded by other numbered tiles," +
				" some of which may be labeled. These labeled numbers are indicating how many mines that tile is directly touching in all eight directions.\n\nTo continue" +
				", determine where you think there shouldn't be another mine. You can click on that spot to reveal even more tiles.\n\nIf you have determined a certain spot" +
				" is almost certainly a mine, you may right click the tile to flag it. If you aren't sure, right click the tile twice to put a question mark on it. If you want " +
				"to clear a flag or question mark, continuously right click it until it becomes a standard tile.");
		taHowTo.setEditable(false);
		taHowTo.setLineWrap(true);
		taHowTo.setWrapStyleWord(true);
		taHowTo.setFont(fText);
		
		JLabel lhConditions = new JLabel("Win/Lose Conditions:");
		lhConditions.setFont(fHeader);
		JTextArea taConditions = new JTextArea("If at any time you click on a mine, you will lose! \n\nOn the other hand, if you are able to flag all of the mine locations or clear every empty tile.  ");
		taConditions.setEditable(false);
		taConditions.setLineWrap(true);
		taConditions.setWrapStyleWord(true);
		taConditions.setFont(fText);
		
		
		
		pRules.add(lTitle);
		pRules.add(lhObjective);
		pRules.add(taObjective);
		pRules.add(lhHowTo);
		pRules.add(taHowTo);
		pRules.add(lhConditions);
		pRules.add(taConditions);
		
		Component[] cAll = pRules.getComponents();
		for(int i = 0; i < cAll.length; i++) {
			((JComponent)cAll[i]).setAlignmentX(LEFT_ALIGNMENT);	
		}
		
		
		setPreferredSize(new Dimension(800,500));
		add(pRules);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();		
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}