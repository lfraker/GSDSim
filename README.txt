***********
README
***********

*************
DEPENDENCIES
*************

	Java SE JDK 7
	Apache Ant
	JUnit - to run tests, optional


**************
BUILD AND RUN
**************

	Run ‘ant’ to compile all the files and run tests.
	Run ‘ant run’ to initialize and run the program.


*************
REQUIREMENTS
*************

	Java 7 is required; previous versions are not supported.
	The OpenStreetMap display used in the program requires an internet connection.


********
TO TEST
********

First, navigate to the ‘sites’ tab, and provided that you have an internet
connection, you should see a map. On this map, right-click, hold and drag to
pan, use the mouse wheel or buttons/slider in the top left to zoom, and lastly
left-click anywhere to open up a pop-up window to add a site. The pop-up window
will ask you for a site name, number of workers, and the time zone difference
from the default time zone, which we will say to be Ireland. (i.e: Ireland is
0). Click ok, and you should see a little yellow circle popup with the site
name next to it. Next, navigate to the ‘modules’ tab. To add a module, first
select all the sites that will be working on this module. To do this, highlight
the site’s name in the drop down box on the top right, and click the add site
button. You should see the site’s name appear in the center of the screen. Do
this until all sites have been selected. If you make a mistake, simply click
clear sites to clear the list, then start over. Next, enter a name for the
module, and lastly enter a float for the estimated time the module will take.
Then click add module, and this module will be added to the selected sites.
Lastly, to simulate the end of the day, click the large ‘Test End of Day Sim’
button, and this will simulate the end of the day.


*******
Note for the team, what needs to be done:
*******

So I changed the program structure a bit more so that it’d make sense. Right
now, when you navigate to the map page, if you click anywhere a pop-up appears
to add the site. After you enter a name, and number of workers and click ok, a
site will be added to the map. This site will then be added to the list of
sites in the new SiteModuleController.java class. This will now contain the
list of all sites, yet not a lis of all modules. At the end of each game day,
the method in SiteModuleController, endDay() will be called. The
SiteModuleController class has an instantiation of the process simulator class,
so I would recommend that for each site, use the call the endOfDaySimulator()
method in the ProcessSimulator class, and pass as an argument to it each site’s
list of Modules. Essentially, run through the list of sites in the
SiteModuleController, and for each one, call the ProcessSimulator’s methods on
each sites list of modules at the end of the day.

To test that this works, first add a site by clicking on the “Sites” tab, then
click anywhere to get the popup to appear that can add a site. After you fill
out the correct info, click ok to add the site. After you have added a site,
click on the “Modules” tab, add the site(s) that the module will be worked on
at, give the module a name and hours estimate, and click add module. This
module will now exist at the site(s) selected, now once the day ends, endDay()
will be called in FrontEndPane.java.

As a final note, each site now contains an instance of a MapMarkerDot,
representing the sites dot on the map. If a site falls behind during the end of
day sim, call mapMarker.setBehind(), if it’s on time, call
mapMarker.setOnTime(), and if its ahead, call   mapMarker.setAhead(). This will
update the marker’s color on the map accordingly.


Thomas -
Added 'hourlyUpdate()' to the SiteModuleController - does work on all modules
at all active sites. Should be called on the hour, every hour. At the moment
you can test it by doing the above and clicking the "Test End Of Day Sim"
button. Output should appear in the command line for debugging purposes.




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
