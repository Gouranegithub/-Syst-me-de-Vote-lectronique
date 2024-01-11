package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VoteAuthenticationService extends Remote {
    boolean authenticateVoter(String username, String password) throws RemoteException;
}
