The program is tested by using a Selenium test, that plays against the AI on the website.

It tests out that the program works on a website level, but also that the calculation of the scores is correct.
There is for now one test designed to play in a specified pattern, which should lead to the AI winning every time.
This is achieved by rotating the pattern "pprps" for a specified rotation count.

There is also a test that first plays 50 rounds the pattern "rrpsppsrp" and then swaps to "prspppspr" for 50 moves.
The AI quickly catches onto this, and comes out on a win.

The rotating pattern "rpsrpsrps..." is also an easy win. 
Not to mention if you only throw one move, like Rock all the time.


These tests can be replicated by pulling the repository, and entering the command "gradlew test" into a commandline in the root directory. This will start up the backend server, open up a chromebrowser
and perform the tests automatically.
Step-by-step: 
1. Pull a local copy of the repository to your own machine.
2. Open up a command line window in the root directory of the repository.
3. Type gradlew test
4. Wait for tests to finish
5. ChromeBrowser should automatically focus, but if it doesn't, you can focus it manually.
6. If chromebrowser does not work.. You probably are using Mac or Linux, or an older version of Windows. Don't know what to do then.

