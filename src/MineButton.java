import javax.swing.ImageIcon;
import javax.swing.JButton;


public class MineButton extends JButton{
	public int x;
	public int y;
	public MineButton() {
		x = 0;
		y = 0;
	}
	public MineButton(ImageIcon icon) {
		setIcon(icon);
		x = 0;
		y = 0;
	}

}
