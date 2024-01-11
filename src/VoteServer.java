import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.rmi.registry.Registry;
import RMI.VoteAuthenticationService;
import RMI.VoteAuthenticationServiceImpl;

public class VoteServer {

    private static final int PORT = 12345;
    private ServerSocket serverSocket;
    private ExecutorService executorService;

   

    public VoteServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            executorService = Executors.newFixedThreadPool(10); // You can adjust the pool size based on your requirements.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        System.out.println("Vote Server started on port " + PORT);

        try {
            // Start RMI registry
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            // Create and bind the authentication service
            VoteAuthenticationService authenticationService = new VoteAuthenticationServiceImpl();
            Naming.rebind("VoteAuthenticationService", authenticationService);

            System.out.println("RMI Authentication Service registered.");

            // Start the main server loop
            System.out.println("Vote Server started on port " + PORT);
        	while (true) {
	            try {
	                Socket clientSocket = serverSocket.accept();
	                System.out.println("New connection accepted from " + clientSocket.getInetAddress().getHostAddress());
	                
	                // Handle each client connection in a separate thread using a ThreadPool
	                executorService.execute(new ClientHandler(clientSocket, authenticationService));
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}


