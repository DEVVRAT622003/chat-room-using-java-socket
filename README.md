# chat-room-using-java-socket 
Certainly! Here's a concise overview of a Java chat room project using sockets:

### Project Overview

- **Server (`ChatServer.java`)**:
  - Uses `ServerSocket` to listen for client connections.
  - Creates a `ClientHandler` thread for each client to manage communication.
  - Broadcasts messages received from one client to all connected clients.

- **Client (`ChatClient.java`)**:
  - Connects to the server using `Socket`.
  - Uses threads for sending and receiving messages to/from the server.
  - Displays received messages and sends user input to the server.

### Workflow

- **Server**: Listens for incoming client connections, manages multiple clients concurrently, and broadcasts messages between them.
  
- **Client**: Connects to the server, handles user input/output, and communicates with other clients via the server.

### Technologies Used

- **Java Sockets**: Establishes communication channels between the server and clients.
- **Threads**: Manages concurrent connections and ensures responsive communication.
- **Input/Output Streams**: Facilitates data transmission over the network.

### Features

- **Real-time Communication**: Messages are immediately visible to all connected clients.
- **Multi-client Support**: Handles multiple clients simultaneously.
- **User Interface**: Can be implemented with a console-based or GUI-based interface.
- **Error Handling**: Addresses network issues and client disconnects for robustness.

### Deployment

- **Server**: Runs on a host with a public IP address or on a local machine for testing.
- **Clients**: Connect using the server's IP address and port number.

### Conclusion

This project is ideal for learning about network programming in Java, concurrency management, and basic user interface development. It provides practical experience in building a real-time chat application using socket communication.
