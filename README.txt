***********
README
***********

*************
DEPENDENCIES
*************

	Java SE JDK 7
	Apache Ant
	Connection to wifi


**************
BUILD AND RUN
**************

	Open a terminal and navigate to the top, ‘GSDSim’ directory.
	Run ‘ant’ to compile all the files and run tests.
	Run ‘ant run’ to initialize and run the program.


*********
TESTING
*********

	All test files can be found in src/test/testing
	To test, navigate to the top, ‘GSDSim’ directory.
	Run ‘ant test’ to compile and run all tests.
	In GSDSim directory, NOT_TESTED.csv documents untested methods and why they are untested

******************
FEATURES OVERVIEW
******************

	Master Config - the master settings configuration file can be found under GSDSim/gameFiles/settings.txt. Instructions for how to appropriately edit the file can be found there as well.

	One-module Process Simulator - the Process simulator is a backend feature. While a simulation is running, the console output will provide appropriate feedback regarding how the process simulator is running.

	Multi-module Process Simultor - See above.

	Status Display - After starting the game, if you load a default scenario, you will be taken immediately to the map status display. If you choose a custom scenario, first you will have to select your sites on the map, add modules to the sites, then start the simulation. Then you will be taken to the same status display. If sites are falling behind/failing, the color will change appropriately.

	Default Scenario - Once you have started the game, at the bottom there is a button ‘Load Default Sites and Modules’. click this button, select a default scenario, and click ’Start Sim’.

	Nominal Schedule Calculator - 

	Module Completion Calculator - 

	End of Game Report - When the game ends, and all modules complete, or the user runs out of money, the game window will close and the end of game report will open. This report will show the original estimates alongside the actual time taken for each module. The user has the ability to save this report by entering a file name at the bottom and clicking save.

	Game Score Calculator - 

	Problem Simulator - 

	Intervention Interface - To use the intervention interface, when you are on the map tab, click the intervention button to open up the intervention tab. The different interventions are listed here. along with buttons to ‘enact the intervention’.

	Inquiry Interface - Once the simulation has started and you are viewing the status screen, click on any site’s dot to open the query interface. In the query interface, click on any of the button’s to query the site, and the output will be displayed in the text area at the bottom.





*******
CREDIT
*******

Some snippets of code in this project were originally written by Zach Davis,
Brown class of 2013. Other parts were originally written by Luke Fraker, but
inspired by Zach Davis's support code, written for the Brown class, CS195n 2D
GameEngines. Permission to use Zach's code in full has been requested and
granted by Zach, so long as he is credited and it is not used for revenue in
any way.


All code in the subdirectories of the src/game/org folder was originally
written by Jan Peter Stotz, and is known as JMapViewer. Some of it has been
edited since. Also, the class, game.components.GameMapController.java was based
on code written by Jan Peter Stotz, as JMapViewer, and edited since then. Since
it was written, some of the code has been further edited for offline use by
Paulus Schoutsen. However, the modified JMapViewer used here only works with an
internet connection, and thus deals mainly with the original code.
