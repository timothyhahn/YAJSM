YAJSM - Yet Another Java Swing Minesweeper
============

Timothy Hahn
tyh25
Project 3.1
    

Java Version
-------------
Java 1.6
    

Running It
-------------
There are two easy ways to run this without compiling the code manually.

Firstly, in the directory dist/, there is a single .jar file labeled 'YAJSM-build1.jar'.
This can be executed using the command `java -jar YAJSM-build1.jar`. This is a prebuilt jar that has been tested on Windows 7, OS X, Linux, and even an ARM based computer.

Secondly, in the top level directory there is an ant build file called 'build.xml'. If you have Apache Ant and run `ant` in this directory, you will get a distributable like the one mentioned above (except it will be called `YAJSM-$(date_goes_here).jar`). If you want to run the program using ant, simply use `ant run`.

Finally, if all else fails, you should be able to go to the src/ directory and type in `javac YAJSM.java` to compile the program and then `java YAJSM` to run it.


What Works
-------------
* The Main Game window has been mostly fleshed out. 
* The menubar on top works and all of the options have been connected to a function that handles whatever that menu selection should do.
* The Statistics portion of the Main Game window does update with the correct time and mine count
* The MineField portion of the Main Game window can be left clicked for tiles to be revealed.
* Clicking on a tile on the MineField will reveal the correct tiles to reveal, in case there are other neighboring tiles that need to flip
* The Hints and Messages portion of the Main Game window has been added
* The New Game option of the menubar will create a new game
* The Settings option and the resulting SettingWindow has its basic structure laid out.
* The Statistics option and the resulting StatisticsWindow has its basic structure laid out.
* The Themes option and the resulting ThemesWindow has an alternate bare-bones structure until I rethink how it will work.
* The "default" and "dark" themes work properly.
* The Quit button will pop up a window confirming if the user wants to quit, and will quit if the user confirms.
* The menubar will change to fit the OS X look when run on an OS X machine, but keep it's standard look on any other platform.
* The About window has been finished.
* The Lose screen has its absolute basics added.


What Doesn't
-------------
* Creating a New Game will not create the new game based off of the settings. It will pop up a message explaining what will actually happen.
* The MineField does not have right clicking (Flagging/Questioning) implemented yet. Right clicking is the same as a left click for now. Also, upon losing you can continue to play, even though the mines have been revealed.
* The Settings and Statistics windows do not yet have any affects and are only laid out in a temporary fashion to show how it will look like. This will be retweaked and polished in future versions.
* The Themes window will need to be rethought out as there are a lot of things that need to be changed every time.
* Tutorial and Rules have not been done yet. Tutorial will probably be the last thing I work on, and it may be cut out. Rules is a very simple text area with scrolling and perhaps an index/search. I just need to write the copy for it.

NOTES
-------------
* Although I didn't ask you previously, I had to switch out my homebuilt audio player for an open source 3rd party library. The reason was that my own implementation of an audio player was much to slow when reacting to an action. I will discuss with you in the future.
* The project is available on Github: https://github.com/timothyhahn/cs338project