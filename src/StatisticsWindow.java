import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class StatisticsWindow extends JFrame implements ActionListener {

	GameWindow gw = null;
	public StatisticsWindow(GameWindow gw) {
		this.gw = gw;
	}
	public void display() {
		JPanel pStatistics = new JPanel();
		GridLayout lStatistics = new GridLayout(0,2);
		//lStatistics.setHgap(10);
		pStatistics.setLayout(lStatistics);
		JPanel pStats = new JPanel();
		pStats.setLayout(new BoxLayout(pStats, BoxLayout.Y_AXIS));
		pStats.add(new JLabel("Games Played: 5"));
		pStats.add(new JLabel("Games Won: 2"));
		pStats.add(new JLabel("Percentage Won: 40%"));
		pStats.add(new JLabel("Best Winning Streak: 1"));
		pStats.add(new JLabel("Best Losing Streak: 3"));
		pStats.add(new JLabel("Current Streak: 3 Losses"));
		pStats.add(new JLabel("Total Time Played: 14:23"));
		
		JPanel pRadioButtons = new JPanel();
		pRadioButtons.setLayout(new FlowLayout());
		pRadioButtons.add(new JRadioButton("Beginner"));
		pRadioButtons.add(new JRadioButton("Intermediate"));
		pRadioButtons.add(new JRadioButton("Expert"));
		
		JPanel pHistory = new JPanel();
		pHistory.setLayout(new BoxLayout(pHistory, BoxLayout.Y_AXIS));
		pHistory.add(new JLabel("History"));
		pHistory.add(pRadioButtons);
		pHistory.add(new TextArea("There will be a list of previously played games here"));
		pHistory.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JPanel pButtons = new JPanel();
		pButtons.add(new JButton("Reset"));
		pButtons.add(new JButton("Close"));
		
		pStatistics.add(pStats);
		pStatistics.add(pHistory);
		add(pStatistics);
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}