package ui;

import server.BidDTO;
import server.RMIInterface;
import server.Server;
import server.UserDTO;
import ui.server.ServerFactory;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class TextPanel extends JPanel {

	private JTextArea textArea;

	private final RMIInterface server;

	public TextPanel() {
		textArea = new JTextArea();

		server = ServerFactory.getInstance();
		
		setLayout(new BorderLayout());
		
		add(textArea, BorderLayout.CENTER);

		add(new JScrollPane(textArea), BorderLayout.CENTER);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					textArea.setText("");
					Set<BidDTO> bids = server.getBids();
					bids.forEach(bidDTO -> {
						try {
							int userID = bidDTO.getUserID();
							UserDTO userById = server.getUserById(userID);
							appendText(userById.getUserName(), bidDTO.getBidAmount());
						} catch (RemoteException e) {
							throw new RuntimeException("");
						}
					});
				} catch (RemoteException e) {
					throw new RuntimeException();
				}
			}
		}, 3000, 3000);
	}
	
	public void appendText(String login, Double price) {
		textArea.append("\n");
		textArea.append(login);
		textArea.append(": ");
		textArea.append(String.valueOf(price));
	}
}






