import java.io.Serializable;
import java.util.Calendar;


public class Record implements Serializable {
	private Calendar timestamp;
	private int difficulty;
	private long time;
	private boolean win;
	public Record(){
		timestamp = Calendar.getInstance();
		difficulty = 0;
		time = 0;
		win = false;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public boolean isWin() {
		return win;
	}
	public void setWin(boolean win) {
		this.win = win;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}
	
} 
