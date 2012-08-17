import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

class RulesWindow extends JFrame implements ActionListener {

	GameWindow gw = null;
	public RulesWindow(GameWindow gw) {
		this.gw = gw;
	}
	public void display() {
		add(new JLabel("Not yet finished"));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();		
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}