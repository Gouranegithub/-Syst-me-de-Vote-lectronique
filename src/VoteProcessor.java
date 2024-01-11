import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class VoteProcessor {

    private Socket clientSocket;

    public VoteProcessor(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void processVotes() {
        try {
            // Set up communication streams
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Assume a simple protocol where the client sends their vote as a string
            String vote = reader.readLine();

            // Process the vote (you can replace this with your actual processing logic)
            boolean voteProcessed = processVote(vote);

            // Send a response back to the client
            if (voteProcessed) {
                writer.println("Vote successfully processed");
            } else {
                writer.println("Error processing vote");
            }

            // Close the communication streams
            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean processVote(String vote) {
        // Add your actual logic for processing the vote here
        // For example, store the vote in a database, update results, etc.
        System.out.println("Processing vote: " + vote);

        // For simplicity, always return true in this example
        return true;
    }
}
