***********
README
***********

To build and run the program:

	Use the build.xml in the GSDSim directory. Run ‘ant’ to compile all the files. Next, run ‘ant run' to initialize and run the program.


program requirements:

	To run the program, the newest version of java must be installed on the system, Java 7. The map used in the program also requires an internet connection.


Note for the team, what needs to be done:

So I changed the program structure a bit more so that it’d make sense. Right now, when you navigate to the map page, if you click anywhere a pop-up appears to add the site. After you enter a name, and number of workers and click ok, a site will be added to the map. This site will then be added to the list of sites in the new SiteModuleController.java class. This will now contain the list of all sites, yet not a lis of all modules. At the end of each game day, the method in SiteModuleController, endDay() will be called. The SiteModuleController class has an instantiation of the process simulator class, so I would recommend that for each site, use the call the endOfDaySimulator() method in the ProcessSimulator class, and pass as an argument to it each site’s list of Modules. Essentially, run through the list of sites in the SiteModuleController, and for each one, call the ProcessSimulator’s methods on each sites list of modules at the end of the day. 

To test that this works, first add a site by clicking on the “Sites” tab, then click anywhere to get the popup to appear that can add a site. After you fill out the correct info, click ok to add the site. After you have added a site, click on the “Modules” tab, add the site(s) that the module will be worked on at, give the module a name and hours estimate, and click add module. This module will now exist at the site(s) selected, now once the day ends, endDay() will be called in FrontEndPane.java.

********
TO TEST
********

*******
CREDIT
*******

Some snippets of code in this project were originally written by Zach Davis, Brown class of 2013. Other parts were originally written by Luke Fraker, but inspired by Zach Davis's
support code, written for the Brown class, CS195n 2D GameEngines. Permission to use Zach's code in full has been requested and granted by Zach, so long as he is credited and it is not used for revenue in anyway.


All code in the subdirectories of the src/game/org folder was originally written by Jan Peter Stotz, and known as JMapViewer, all credit to him. Some of it has been edited since. Also, the class, game.components.GameMapController.java was based on code written by Jan Peter Stotz, as JMapViewer, and edited since then. Since it was written, some of the code ha been further edited for offline use by Paulus Schoutsen. However, the JMapViewer edited  and used here only works with an internet connection, and thus deals mainly with the original code.
