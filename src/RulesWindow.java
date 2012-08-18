import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class RulesWindow extends JFrame implements ActionListener {

	GameWindow gw = null;
	public RulesWindow(GameWindow gw) {
		this.gw = gw;
	}
	public void display() {
		JPanel pRules = new JPanel();
		
		pRules.add(new JLabel("Not yet finished"));
		
		add(pRules);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();		
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}