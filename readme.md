# 2019 Deep Space
## Frist Robotics Competition - *Destination: Deep Space*

This is our code for the 2019 FIRST Robotics Competition (FRC) - *Destination: Deep Space*.

For the first 10 weeks of development, most of the code was focused on a primative version of our swerve drive code. When we had our first competition during week 4, we had severe mechanical problems with a few of our swerve drive modules, which prevented us from using them. From this point on, we began development on a standard 4-cim tank drive using the KOP chassis and ToughBox Minis, which we then used in our week 6 competition.

In conclusion, please feel free to check out our code at the last commit. It should be very instructional for simple bots. If you are looking for our swerve drive code, you will need to go back to the commit immediately before the first "tank drive" commit.

During development of the swerve drive, we had a few problems, most of which were related to encoder data and optimization. The most persistent problem that we had--which ended up having a very elegant and simple solution--was the optimization of the setpoint for the azimuth on our swerve modules. We initially had them rotating 180 degrees when switching from forward to reverse, which is clearly not optimal. We ended up simply comparing the error and inverted error, and if the inverted error was less, then treat that as the error, and invert the drive loop motor.

Developing the swerve drive was a very fun challenge, and I look forward to working on it again in the future, as well as whatever other challenges come our way.

Good luck out there!

-- Brenden Campbell

Programming Captain, FRC #6635 JaXon Falcon Works
