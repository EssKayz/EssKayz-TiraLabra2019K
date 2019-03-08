# The generic structure of the program

Controller.java - responsible for the website management of the software, container for all Game sessions
	Game.java - Object containing a game session
		AIntf.java - Interface for each AI
		> GameAI, superclass for some default methods used in most AI implementations

Time complexity analysis :
N/A, time complexity not calculated, but user experiences were, that they basically never had to wait for the AI.

Space complexity analysis :
N/A

Performance analysis :
See [Statistics](https://github.com/EssKayz/EssKayz-TiraLabra2019K/blob/master/Documentation/Statistics)
Around 50-75% winrate against randomly selected people

How the AI selection works :
1. Each AI gives their own qualified guess over what the next move should be.
2. The guess of each AI is then graded based on the AIs longterm and short-term winrate.
3. Each guess is calculated.
4. Based on the guesses, the game will then generate a random integer between 0 and the total amount of votes.

Example of this :
```
Ai1 votes for Rock, and is graded 150 points.
Ai2 votes for Paper, and is graded 50 points.
Ai3 votes for Rock, and is graded 50 points.
All votes get added together, and now we have :
	Rock : 200 points
	Paper : 50 points
	Scissors : 0 points.
The game then generates a random integer between 0 and 250.
	if the random number is between 0-0, we select Scissors.
	if the number is between 0-50, we select Paper.
	If the number is between 50-250, we select Rock.
This way we get a weighted randomization, so that if all AI's vote for Rock, we select rock. 
If the AI's then again vote equally, we essentially have a 33% possibilty for any one of the selections.
```

# Data structures
###  Array Library
1. An library for sorting string arrays. 
2. Contains a MergeSort, QuickSort

### String method library
1. Own implementation of substring from index 1 to index 2
2. Own implementation to find the last index of a substring within a string
3. Own implementation to find the first index of a substring, and an override to do same, but starting at a predefined index.

### RandomGen class
1. a Random Generation algorithm, 
2. uses bitshifting and bitwise operands on long numbers to generate pseudorandom numbers based on a long type seed generated on system time.


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
