import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import RMI.VoteAuthenticationService;
import java.rmi.Naming;
import java.util.Scanner;

public class VoteClient {

	
	private VoteAuthenticationService authenticationService;
	
    private String serverHost;
    private int serverPort;
    private Socket socket;
    
   

    public VoteClient(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        // Initialize the RMI authentication service
        try {
            authenticationService = (VoteAuthenticationService) Naming.lookup("rmi://localhost/VoteAuthenticationService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void connectToServer() {
        try {
            // Establish a connection to the server
            socket = new Socket(serverHost, serverPort);
            System.out.println("Connected to the server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void sendVote(String vote) {
        try {
            // Set up communication streams
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            
          
            try {

                // Prompt the user for username and password
                Scanner scanner = new Scanner(System.in);

                System.out.print("Enter your username: ");
                String username = scanner.nextLine();

                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

               
            	
                
                // Authenticate voter using RMI service
                boolean isAuthenticated = authenticationService.authenticateVoter(username, password);
  
        
                if (isAuthenticated) {
                	// Send the vote to the server
                	writer.println(vote);
                	
                	 // Receive and print the server's response
                    String response = reader.readLine();
                    System.out.println("Server response: " + response);
                
                } else {
                    
                    System.out.println("Authentication failed. Closing connection.");
                    // Close the connection or handle the situation accordingly
                
                }
             // Close the streams
                reader.close();
                writer.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            
    
            
            
           

            // Close the communication streams (do not close the socket to allow for multiple votes)
           //reader.close();
            //writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnectFromServer() {
        try {
            // Close the socket when done
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
