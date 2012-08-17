public class YAJSM {

	public static void main (String[] args){ 

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void  run() {
				GameWindow gw = new GameWindow();
				gw.setUpLayout();
			}
		});
	}
}
