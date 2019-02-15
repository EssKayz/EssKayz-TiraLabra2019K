The generic structure of the program is:

Controller.java - responsible for the website management of the software, container for all Game sessions
	Game.java - Object containing a game session
		AIntf.java - Interface for each AI
		> GameAI, superclass for some default methods used in most AI implementations

Time complexity analysis :
ToDo

Space complexity analysis :
ToDo

Performance analysis :
ToDo

Possible improvements and caveats:
Some more commentation on some classes (MetaStrategies, for example)
More tests are required to test own data structures, for example JUnit tests to test that own data structures
work in the same way as the structures they are replacing. Same for methods and algorithms. Maybe
time complexity comparison betweeen them aswell?


Sources :
1. > https://daniel.lawrence.lu/programming/rps/
2. > http://www.rpscontest.com/
3. > http://setosa.io/ev/markov-chains/
4. > https://bisqwit.iki.fi/story/howto/bitmath/