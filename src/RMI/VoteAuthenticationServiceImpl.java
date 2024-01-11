package RMI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;


public class VoteAuthenticationServiceImpl extends UnicastRemoteObject implements VoteAuthenticationService {
	// Map to store valid usernames and corresponding passwords (replace with a secure storage in a real system)
    private final Map<String, String> validCredentials = new HashMap<>();

    public VoteAuthenticationServiceImpl() throws RemoteException {
        // Add some sample valid credentials to the map (replace with your actual user data)
        validCredentials.put("user1", "password1");
        validCredentials.put("user2", "password2");
        // Add more users as needed...
    }

    @Override
    public boolean authenticateVoter(String username, String password) throws RemoteException {
        // Check if the provided username exists and the password matches
        return validCredentials.containsKey(username) && validCredentials.get(username).equals(password);
    }
}

