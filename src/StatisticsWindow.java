import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class StatisticsWindow extends JFrame implements ActionListener {

	GameWindow gw = null;
	JTable tHistory;
	JScrollPane scrollPane;
	JPanel pHistory;
	JPanel pRadioButtons;
	int played = 0;
	int won = 0;
	float percent = 0;
//	int winningStreak = 0;
//	int losingStreak = 0;
	int currentStreak = 0;
	String streakIs = "";
	long totalTimePlayed = 0;
	
	public void genStats() {
		gw.saveRecords();
		gw.loadRecords();
		
		
		
		played = gw.records.size();
		for(int i = 0; i < gw.records.size(); i++){
			if(gw.records.get(i).isWin())
				won++;
			totalTimePlayed += gw.records.get(i).getTime();
		}
		if(played > 0) {
			percent = (float)won / (float)played;
		} else {
			percent = 0;
		}
		boolean first = true;
		boolean win = true;
		for(int i = gw.records.size() - 1; i > 0; i--) {
			if(first){
				if(gw.records.get(i).isWin()){
					streakIs = "Win";
					currentStreak++;
				} else {
					streakIs = "Loss";
					win = false;
					currentStreak++;
				}
				first = false;
			} else {
				if(win) {
					if(gw.records.get(i).isWin()){
						currentStreak++;
						streakIs = "Wins";
					} else {
						i = 0;
					}

				} else {
					if(!gw.records.get(i).isWin()){
						currentStreak++;
						streakIs = "Losses";
					} else {
						i = 0;
					}
				}
			}
			
		}
		
	}
	public StatisticsWindow(GameWindow gw) {
		this.gw = gw;
	}
	public void display() {
		setTitle("YAJSM - Statistics");
		JPanel pStatistics = new JPanel();
		GridLayout lStatistics = new GridLayout(0,2);
		//lStatistics.setHgap(10);
		pStatistics.setLayout(lStatistics);
		genStats();
		JPanel pStats = new JPanel();
		pStats.setLayout(new BoxLayout(pStats, BoxLayout.Y_AXIS));
		Font font = new Font("Verdana", Font.BOLD, 20);
		//test.setFont(font);
		JLabel gamesPlayed = new JLabel("Games Played: " + played);
		JLabel gamesWon = new JLabel("Games Won: " + won);
		JLabel percentageWon = new JLabel("Percentage Won: " + (percent * 100) + "%");
		JLabel currentSt = new JLabel("Current Streak: " + currentStreak + " " + streakIs);
		JLabel totalTime = new JLabel("Total Time Played: " + totalTimePlayed);
		
		gamesPlayed.setFont(font);
		gamesWon.setFont(font);
		percentageWon.setFont(font);
		currentSt.setFont(font);
		totalTime.setFont(font);
		
		pStats.add(Box.createRigidArea(new Dimension(20,20)));
		pStats.add(gamesPlayed);
		pStats.add(gamesWon);
		pStats.add(percentageWon);
		pStats.add(currentSt);
		pStats.add(totalTime);
		

		JButton bClose = new JButton("Close");
		pStats.add(Box.createRigidArea(new Dimension(20,280)));
		pStats.add(bClose);
		bClose.addActionListener(this);
		
		JRadioButton rbBeginner = new JRadioButton("Beginner");
		JRadioButton rbIntermediate = new JRadioButton("Intermediate");
		JRadioButton rbExpert = new JRadioButton("Expert");
		
		rbBeginner.addActionListener(this);
		rbIntermediate.addActionListener(this);
		rbExpert.addActionListener(this);
		
		 pRadioButtons = new JPanel();
		pRadioButtons.setLayout(new FlowLayout());
		pRadioButtons.add(rbBeginner);
		pRadioButtons.add(rbIntermediate);
		pRadioButtons.add(rbExpert);

		rbBeginner.setSelected(true);
		
		
		ButtonGroup bgDifficulties = new ButtonGroup();
		bgDifficulties.add(rbBeginner);
		bgDifficulties.add(rbIntermediate);
		bgDifficulties.add(rbExpert);
		
		
		pHistory = new JPanel();
		pHistory.setLayout(new BoxLayout(pHistory, BoxLayout.Y_AXIS));
		pHistory.add(new JLabel("History"));
		pHistory.add(pRadioButtons);
		
		
		loadStatistics(1);

		scrollPane = new JScrollPane(tHistory);
		tHistory.setFillsViewportHeight(true);
		pHistory.add(scrollPane);
		pHistory.setBorder(BorderFactory.createLineBorder(Color.black));
		
	
		
		pStatistics.add(pStats);
		pStatistics.add(pHistory);
		add(pStatistics);
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public void loadStatistics(int difficulty) {
		
		String columnNames[] = {"Date","Time", "Result" };

		ArrayList<Object[]> dataList = new ArrayList<Object[]>();
		for(int i = 0; i < gw.records.size(); i++){
			if(gw.records.get(i).getDifficulty() == difficulty) {
				String isWin = "";
				if(gw.records.get(i).isWin()){
					isWin = "Win";
				} else {
					isWin = "Loss";
				}
				String date =  gw.records.get(i).getTimestamp().get(Calendar.MONTH) +"/" + gw.records.get(i).getTimestamp().get(Calendar.DAY_OF_MONTH) + "/" + gw.records.get(i).getTimestamp().get(Calendar.YEAR);
				Object[] rData = {date, formatInterval(gw.records.get(i).getTime()), isWin};
				dataList.add(rData);
			}
		}

		Object[][] data = new Object[dataList.size()][3];
		for(int i = 0; i < dataList.size(); i++){
			data[i] = dataList.get(i);
		}
		
		tHistory = new JTable(data,columnNames);
		pack();
	}
	 private static String formatInterval(long l)
	    {
		 	l = l * 1000;
	        final long hr = TimeUnit.MILLISECONDS.toHours(l);
	        final long min = TimeUnit.MILLISECONDS.toMinutes(l - TimeUnit.HOURS.toMillis(hr));
	        final long sec = TimeUnit.MILLISECONDS.toSeconds(l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
	        return String.format("%02d:%02d:%02d", hr, min, sec);
	    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().getClass() == JButton.class) {
			setVisible(false);
		} else if (arg0.getSource().getClass() == JRadioButton.class){
			JRadioButton rbSelected = (JRadioButton) arg0.getSource();
			if(rbSelected.getText().equals("Beginner")) {
				loadStatistics(1);
			} else if(rbSelected.getText().equals("Intermediate")) {
				loadStatistics(2);
			} else if(rbSelected.getText().equals("Expert")) {
				loadStatistics(3);
			}
			pHistory.remove(2);
			scrollPane = new JScrollPane(tHistory);
			pHistory.add(scrollPane);
			
			invalidate();
			validate();
		}
		
	}
}