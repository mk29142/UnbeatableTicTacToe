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

Instructions to run:

Make sure your java version is version 7 and run the following command.

- java -cp target/UnbeatableTicTacToe-1.0-SNAPSHOT.jar tictactoe.Main

To run the tests:

If you have maven already install:  
- go into the folder where pom.xml is and enter (without the quotes) "mvn package"

Otherwise:
- open the project in a IDE
- Right click on tests folder and select "run Tests in test".
