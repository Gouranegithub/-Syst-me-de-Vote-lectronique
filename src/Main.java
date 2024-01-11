
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		VoteServer server = new VoteServer();
		new Thread(server::startServer).start();
        

        
        
        
        
     // Introduce a delay to allow the server to fully initialize (adjust as needed)
        try {
            Thread.sleep(1000); // 1 second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
        
        // Client:
        
        
        
        VoteClient voteClient = new VoteClient("localhost", 12345); 
        voteClient.connectToServer();


        String vote = "Candidate_A"; // Replace with the actual vote content
        voteClient.sendVote(vote);
 
        voteClient.disconnectFromServer();
	}

}
