import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import RMI.VoteAuthenticationService;

public class ClientHandler  implements Runnable {

	private Socket clientSocket;
    private VoteAuthenticationService authenticationService;

    public ClientHandler(Socket clientSocket, VoteAuthenticationService authenticationService) {
        this.clientSocket = clientSocket;
        this.authenticationService = authenticationService;
    }

    @Override
    public void run() {
        try {
            // Set up communication streams
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Example: Authentication logic
            String username = reader.readLine();
            String password = reader.readLine();

            boolean isAuthenticated = authenticationService.authenticateVoter(username, password);

            if (isAuthenticated) {
                writer.println("Authentication successful");

                // Example: Vote processing logic
                String vote = reader.readLine();
                // Process the vote as needed

                // Example: Send a response back to the client
                writer.println("Vote processed successfully");
            } else {
                writer.println("Authentication failed");
            }

            // Close the communication streams
            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
