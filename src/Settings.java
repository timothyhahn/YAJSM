import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Settings implements Serializable {
	/**
	 * 
	 */
	public static final int SUCCESS = 1;
	public static final int NO_SAVE_FILE = 2;
	public static final int SAVE_IO_ERROR = 3;
	public static final int LOAD_READ_ERROR = 4;
	private static final long serialVersionUID = 5262669237007809368L;
	public int difficultyMode; //Beginner: 1, Intermediate: 2, Expert: 3, Custom: 4
	public int boardHeight;
	public int boardWidth;
	public int mineCount;
	public int soundVolume;
	public int musicVolume ;
	public boolean showHints;
	public boolean allowRetries;
	public boolean colorBlindMode;
	public Settings() {
		difficultyMode  = 1;
		boardHeight = 10;
		boardWidth = 10;
		mineCount = 9;
		soundVolume = 50;
		musicVolume = 50;
		showHints = true;
		allowRetries = true;
		colorBlindMode = false;
	}
	
	public int save() {
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("settings.ser");
	         ObjectOutputStream out =
	                            new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	          fileOut.close();
	          return SUCCESS;
	      }catch(IOException i)
	      {
	          i.printStackTrace(); 
	          return SAVE_IO_ERROR;
	      }
	}
	public int load() {
		 try
         {
            FileInputStream fileIn =
                          new FileInputStream("settings.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Settings s = (Settings) in.readObject();
            this.difficultyMode = s.difficultyMode;
            this.boardHeight = s.boardHeight;
            this.boardWidth = s.boardWidth;
            this.mineCount = s.mineCount;
            this.soundVolume = s.soundVolume;
    		this.musicVolume = s.soundVolume;
    		this.showHints = s.showHints;
    		this.allowRetries = s.allowRetries;
    		this.colorBlindMode = s.colorBlindMode;
            
            in.close();
            fileIn.close();
            return SUCCESS;
        }catch(IOException i)
        {
            i.printStackTrace();
            return NO_SAVE_FILE;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Settings class not found");
            c.printStackTrace();
            return LOAD_READ_ERROR;
        }
	}
}
