import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class TutorialWindow extends JFrame implements ActionListener {

	GameWindow gw = null;
	public TutorialWindow(GameWindow gw) {
		this.gw = gw;
	}
	public void display() {
		JPanel pTutorial = new JPanel();
		pTutorial.add(new JLabel("Not yet finished"));
		add(pTutorial);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}