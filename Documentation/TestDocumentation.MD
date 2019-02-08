The program is tested by using a Selenium test, that plays against the AI on the website.

It tests out that the program works on a website level, but also that the calculation of the scores is correct.
There is for now one test designed to play in a specified pattern, which should lead to the AI winning every time.
This is achieved by rotating the pattern {"btnPaper", "btnPaper", "btnRock", "btnPaper", "btnScissors"} for a specified rotation count.

These tests can be replicated by pulling the repository, and entering the command "gradlew test" into a commandline in the root directory. This will start up the backend server, open up a chromebrowser
and perform the tests automatically.
