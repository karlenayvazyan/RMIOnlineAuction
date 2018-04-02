package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Comparator;
import java.util.Set;

public class Server extends UnicastRemoteObject implements RMIInterface {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    private BidDAO bidDAO;

    private ItemDAO itemDAO;

    //UnicastRemoteObject constructor
    private Server() throws RemoteException {
        super();
        userDAO = new UserDAOImpl();
        bidDAO = new BidDAOImpl();
        itemDAO = new ItemDAOImpl();
    }

    @Override
    public UserDTO getUserByLoginAndPassword(String login, String password) {
        if (login == null || password == null) {
            throw new RuntimeException("User or Password should not be null");
        }
        return userDAO.getUserByLoginAndPassword(login, password);
    }

    @Override
    public void addBid(BidDTO bidDTO) {
        if (bidDTO == null) {
            throw new RuntimeException("bidDto should not be null");
        }
        bidDAO.addBid(bidDTO);
    }

    @Override
    public Set<BidDTO> getBids() {
        return bidDAO.getBids();
    }

    @Override
    public BidDTO getBidMaxAmount() {
        return bidDAO.getBids()
                .stream()
                .max(Comparator.comparing(BidDTO::getBidAmount))
                .orElse(null);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        if (userId == null) {
            throw new RuntimeException("UserId should not be null");
        }
        return userDAO.getUserById(userId);
    }

    @Override
    public String[] getItemNames() {
        return itemDAO.getItems()
                .stream()
                .map(ItemDTO::getItemName)
                .toArray(String[]::new);
    }

    @Override
    public ItemDTO getItemByName(String selectedItem) {
        return itemDAO.getItems()
                .stream()
                .filter(itemDTO -> itemDTO.getItemName().equals(selectedItem))
                .findAny()
                .get();
    }

    @Override
    public UserDTO getWinner(int itemID) {
        return bidDAO.getBids()
                .stream()
                .filter(bidDTO -> bidDTO.getItemDtoId() == itemID)
                .max(Comparator.comparing(BidDTO::getBidAmount))
                .map(bidDTO -> userDAO.getUserById(bidDTO.getUserID()))
                .orElse(null);
    }

    public static void main(String[] args) {
        try {
            String host = "localhost";
            int port = 1099;
            LocateRegistry.createRegistry(port);
            Registry reg = LocateRegistry.getRegistry(host, port);
            reg.rebind("auction", new Server());
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }

}