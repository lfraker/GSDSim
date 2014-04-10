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

Release 2 Features:

	1. feat. 40: Eye candy 3.50 (3.50):
		


	2. feat. 14: End of game report 20.00 (23.50):

		-Run through the quick scenario to get the end of game report
		-Once modules have completed, game screen will disappear and end of game report will appear
		-Report displayed summarized game information, scroll up to the top to view information in order
			*Modules are displayed at top of report
				-Module name is displayed first, along with development method used for the module
				-Indented underneath the module name/development method is a list of the original
				man hour estimates and actual man hours spent for each of the 7 phases of the module
					*the actual man hours spent does not account for variations in performance level
					(developer productivity factors, etc.), it only accounts for the random 25% variation
					at each phase
			*Total effort statistics are displayed after the modules
				-Expected effort is the sum of all the original module man hour estimates
				-Actual effort is the sum of the actual number of man hours spent on each module
					*As these values are measured in man hours, they do not account for the
					number of developers working on the module, however they do account for
					variations in performance level (developer productivity factors, problems, etc.)
			*Total day statistics are displayed after the effort statistics
				-Expected days for entire project is the number of days expected to finish all modules
					*Value is gotten by finding the site with the largest expected amount of work,
					then dividing this site’s expected amount of work by 9, the number of work
					hours per day
				-Actual days for entire project is the actual number of days it took to finish every module
					*Value is gotten by dividing the actual man hours spent by 9, the number of work
					hours per day
				-Both expected and actual days values account for X days + a few hours extra. In this case
				the day value would be (X + 1), rather than rounding off the hours and saying simply X
				-The project finished statement will represent if the project either finished early or
				finished late
			*Budget statistics are displayed after total day statistics
				-Expected budget used for entire project represents the initial value set in the master
				configuration file as the project budget in Euros
				-Actual budget used for the entire project represents how much of this original budget
				was used up during the project
					*If the actual budget used is greater than the expected budget, (the project
					ran up debt), than this value will be negative
				-The leftover budget statement will represent if the project either went into debt or
				had budget to spare
			*Revenue statistics are displayed after budget statistics
				-Expected revenue 6 months after release is the original expected revenue set in the
				master configuration file
				-Actual revenue 6 months after release represents the expected revenue modified by
				whether the project finished early or late
					*Each day variation from the expected number of days till completion will result
					in the revenue being changed by + or - the expected revenue per day
						-The expected revenue per day is found by dividing the expected revenue
						for 6 months after release by (30 x 6) (30 representing an average of
						days per month and 6 representing 6 months)
			*Game score is displayed after revenue statistics
				-Expected game score is simply the expected revenue as displayed above
				-Actual game score is the actual revenue modified by the actual budget
					*If there is budget leftover, this will be added to the actual
					revenue to calculate the actual game score
					*If the project went into debt, the amount of debt will be
					subtracted from the actual revenue to calculate the actual
					game score


	3. feat. 3: Game score calc. 14.00 (37.50):

		-Displayed in the end of game report
			*Run the quick scenario to get to the end of game report
			*Scroll to the bottom of the report until Game Score is displayed
			*View expected game score and actual game score
				-Expected game score is simply the expected revenue set by the
				user in the master configuration file
				-Actual game score is the expected revenue modified by the leftover
				budget
					*calculations are explained in detail at the bottom of
					2. feat. 14: End of game report, just above this feature


	4. feat. 11: Problem simulator 30.50 (68.00):

		
	5. feat. 16: Intervention interface 12.00 (80.00):

	



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
