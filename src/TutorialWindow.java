import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

class TutorialWindow extends JFrame implements ActionListener {

	GameWindow gw = null;
	public TutorialWindow(GameWindow gw) {
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