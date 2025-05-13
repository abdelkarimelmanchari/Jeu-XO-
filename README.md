XO Game using Threads and Sockets in Java
Description
This project is a console-based implementation of the classic Tic-Tac-Toe (XO) game, developed in Java. It is designed using a client-server architecture, allowing two players to compete against each other over a network.

The server handles:

Game logic and rules enforcement.

Coordination of player turns.

Communication between the two clients.

Each client (player) is connected through Java sockets, and each is managed in a separate thread, enabling smooth, simultaneous gameplay and interaction.

Features
Console-based Tic-Tac-Toe gameplay.

Supports two players over a TCP network.

Uses multithreading for efficient player turn management.

Robust communication handled via Java Sockets.

Clear separation of client and server responsibilities.

Technologies Used
Java SE

Java Sockets (java.net package)

Java Threads (java.lang.Thread)

How it Works
The server waits for two players to connect.

Each player runs as a client application and connects to the server.

The server starts a dedicated thread for each player.

The players take turns making their moves.

The server validates moves, updates the game board, and informs both players of the game status (win, draw, ongoing).

The game ends when one player wins or the board is full.

Getting Started
Requirements
Java JDK 8 or higher.

IDE or terminal with Java compiler.

How to Run
Start the Server:

Compile and run the server class.

Start the Clients:

Compile and run the client class on two separate terminals or machines.

Follow the prompts to play the game.

Author
Abdelkarim El Manchari
