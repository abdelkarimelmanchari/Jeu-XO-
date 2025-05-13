# XO Game using Threads and Sockets in Java

## 📄 Description
This project is a console-based implementation of the classic **Tic-Tac-Toe (XO) game**, developed in **Java**.  
It utilizes a **client-server architecture**, where two players connect remotely and play against each other.

The **server** is responsible for:
- Managing game logic and validating moves.
- Coordinating turns between the players.
- Handling communication between two client applications.

Each client is managed in a separate thread, using **Java multithreading**, to ensure simultaneous and efficient gameplay.  
All communication is performed using **Java Sockets** over TCP.

---

## 🚀 Features
- Classic Tic-Tac-Toe gameplay on the console.
- Real-time two-player mode over a network.
- Client-server model using Java Sockets.
- Separate threads for each player ensuring smooth gameplay.
- Clear feedback and game state display.

---

## 💻 Technologies Used
- Java SE
- Java Sockets (`java.net`)
- Java Threads (`java.lang.Thread`)

---

## 🔧 How It Works
1. **Start the Server**
   - The server waits for two players to connect.
2. **Launch Clients**
   - Two separate clients connect to the server.
3. **Gameplay**
   - Each player is assigned a thread.
   - Players take turns sending their moves.
   - The server checks the move validity, updates the board, and informs both players.
4. **Game End**
   - The game concludes when a player wins or the board is full (draw).

