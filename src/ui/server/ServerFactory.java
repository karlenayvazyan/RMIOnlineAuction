package ui.server;

import server.RMIInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ServerFactory {

    private static volatile RMIInterface lookUp;

    private static final String HOST = "localhost";
    private static final int PORT = 1099;

    private ServerFactory() {}

    public static RMIInterface getInstance() {
        if (lookUp == null) {
            synchronized (ServerFactory.class) {
                if (lookUp == null) {
                    try {
                        lookUp = (RMIInterface) Naming.lookup("rmi://" + HOST + ":" + PORT + "/auction");
                    } catch (NotBoundException | MalformedURLException | RemoteException e) {
                        throw new RuntimeException("Fail to connect server ");
                    }
                }
            }
        }
        return lookUp;
    }
}
