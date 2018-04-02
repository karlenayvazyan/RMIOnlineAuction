package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;


public interface RMIInterface extends Remote {

    UserDTO getUserByLoginAndPassword(String login, String password) throws RemoteException;

    void addBid(BidDTO bidDTO) throws RemoteException;

    Set<BidDTO> getBids() throws RemoteException;

    BidDTO getBidMaxAmount() throws RemoteException;

    UserDTO getUserById(Integer userId) throws RemoteException;

    String[] getItemNames() throws RemoteException;

    ItemDTO getItemByName(String selectedItem) throws RemoteException;

    UserDTO getWinner(int itemID) throws RemoteException;
}
