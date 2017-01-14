# UnbeatableTicTacToe

Command line application for the game Tic Tac Toe.

User can choose 1 of 3 game modes; Human vs Human, Human vs Computer
or Computer vs Computer.

When playing against the computer the outcome will either be a draw
or a loss. The computer uses the minmax algorithm and will always
find a way to win or draw.

When Computer vs Computer option is chosen then, one computer
will run the minmax algorithm while the other will run a slight
variation where it uses alpha-beta pruning.


The simplest way to run this is to use an IDE:

- just open the project and run Main.java

To run on command line:

- cd into tictactoe folder
- run: *javac -cp .. *.java* to compile it
- cd out of tictactoe so that you are in the src directory.
- run *java tictactoe.Main*
