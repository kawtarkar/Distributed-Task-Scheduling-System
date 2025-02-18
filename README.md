# Distributed-Task-Scheduling-System
A Java-based distributed system for matrix operations and image convolution processing, implementing a client-server architecture with slave nodes for parallel computation.
## Feature
- Distributed Computing: Server distributes tasks among slave nodes for parallel processing
- Matrix Operations:
  - Addition
  - Subtraction
  - Multiplication
  - Image Convolution
- Real-time Processing: Handles multiple client requests simultaneously
- Error Handling: Robust validation for matrix dimensions and operations
- Socket-based Communication: TCP/IP communication between components
## Architecture
The system consists of three main components:
**Client:** Submits matrix operations and receives results
**Server:** Manages client connections and distributes tasks
**Slaves:** Perform actual computations (matrix operations and convolution) &nbsp;
Client <-> Server <-> Slaves
## Technical Stack
- **Language:** Java
- **Communication:** TCP/IP Sockets
- **Threading:** Multi-threading for parallel processing
- **Data Structures:** 2D Arrays for matrix operations
- **Image Processing:** Custom convolution implementation
## Installation
1.Clone the repository
```bash
git clone https://github.com/kawtarkar/Distributed-Task-Scheduling-System
```
2.Compile the Java files
```bash
javac Server.java 
javac Client.java
javac Slave.java
```
3.Start the components in order:
```bash
java Server
java Slave # Run multiple instances for parallel processing
java Client
```
## Usage 
Matrix Operations
- 1.Start the server 
- 2.Launch slave nodes 
- 3.Connect with client 
- 4.Choose operation type:
  - 1: Addition
  - 2: Multiplication
  - 3: Subtraction
  - 5: Convolution
- 6.Input matrices according to prompts 
- 7.Receive computed results 
## Future Enhancements
- Add support for more matrix operations
- Implement load balancing between slaves
- Add GUI interface for client
- Improve error handling and recovery
- Add support for larger matrices


