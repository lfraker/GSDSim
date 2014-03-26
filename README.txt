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


******************
FEATURES OVERVIEW
******************

	The features currently implemented are as follows: There is a settings page that allows you to change global settings, or to modify the global settings file, from the app itself. There is also the ability to load default scenarios on this page. The default scenarios come with pre-coded sites and modules for each site. There is also the feature allowing the user to start a custom sim in which the user must add all the sites and add all the modules themselves. Another feature is on the sites page. By clicking anywhere on the map in a custom game, the user may add a site before the sim has started. There will then be a small popup allowing the user to customize some site features. In the custom mode, the modules tab will also be available, where the user can add modules to the site that they choose. Once the user has customized their sites and modules, they can click start sim to start the simulation. While the simulation is running in either mode, the user can view the map to see which sites are behind or on schedule based on the process simulator. The user can click on any of the site’s dots on the map to get an info pane to pop up, allowing the user to see more detailed info of the current site.


****************
TO RUN THE GAME
****************


First select the appropriate settings, difficulty, and time per day. Easy is
the only difficulty level under which the pause feature is enabled. Next, 
select ‘Choose Default Sites’, pick a scenario and click ’Start Sim’. Once the
scenario has been loaded, the ‘Settings’ tab will no longer be active or able 
to be accessed. After a scenario has been preloaded, only the ’Sites’ tab will
be active. On the ’Sites’ tab, to get a sites info, click on the circle of the
site and a pane with its infer will pop up.



******************
Notes to the team
******************

I’ve added a developer cost per day value and a developer effort per day value to each site. These defaults to 4 and 10 respectively. I’ve also added a second constructor for the site, in case you want to pass in your own values.

I’ve added a custom simulation mode in which you pick your sites, modules, etc. then click start sim to start the simulation, at which point it runs the same as the default sim. In the custom sim mode, when you pick a site, in the popup box, it will ask you for effort per developer day and cost per developer day. These are for this specific site.

I’ve added a button in the middle of the settings page that essentially opens a text viewer in which you can edit the master settings file. This is the cleanest way to add multiple parameters to the game and be able to edit them all.

As we needed multiple more global parameters, I’ve added a hash map to the frontEndPane, that maps string to string. In the editable settings file you will see that aside from difficulty and time per day, the settings after that have a ‘PCode’. this ‘PCode’ is the key in each key/value pair of the hash map. The value the user inputs is then put into the hash map as the corresponding value to the ‘PCode’. It is put in as a string though, so you will have to parse it and handle input errors when you need it. To get these values, call FrontEndPane’s method, getGlobalParam(String param), and pass in the correct ‘PCode’ to the value you need.


T: 
Completely changed the way scenarios are loaded and saved. Entire game state can be saved at any time and reloaded later. 
Gametime is not yet included.
Scenarios are just saved program states. Any saved game placed in the Scenarios folder can be loaded as a scenario.




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
