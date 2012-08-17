import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class StatisticsWindow extends JFrame implements ActionListener {

	GameWindow gw = null;
	public StatisticsWindow(GameWindow gw) {
		this.gw = gw;
	}
	public void display() {
		JPanel pDisplay = new JPanel();
		pDisplay.add(new JLabel("Not yet finished"));
		add(pDisplay);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}