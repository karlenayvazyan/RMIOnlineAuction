package ui;

import server.BidDTO;
import server.ItemDTO;
import server.RMIInterface;
import server.UserDTO;
import ui.server.ServerFactory;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class Toolbar extends JPanel {

	private final RMIInterface server;

	private volatile boolean isRun = false;

	public Toolbar(FormPanel formPanel, TextPanel textPanel, UserDTO user, ItemDTO item) {

		server = ServerFactory.getInstance();

		setBorder(BorderFactory.createEtchedBorder());

		JButton bidButton = new JButton("Make a bid");
		JButton defaultButton = new JButton("Make a bid by default");
		JButton stopButton = new JButton("Stop bidding");

		bidButton.addActionListener(e -> {
			try {
				Double beatPrice = formPanel.getBeatPrice();
				if (beatPrice.compareTo(item.getItemInitialPrice()) < 0) {
					JOptionPane.showMessageDialog(null, String.format("Item initial price is %s your bit should be greater then initial price", item.getItemInitialPrice()));
					return;
				}
				BidDTO bidDto = new BidDTO();
				bidDto.setBidAmount(beatPrice);
				bidDto.setUserID(user.getUserID());
				bidDto.setItemDtoId(item.getItemID());
				server.addBid(bidDto);
				textPanel.appendText(user.getUserName(), beatPrice);
			} catch (RemoteException e1) {
				throw new RuntimeException("Can't add bid");
			}
		});

		defaultButton.addActionListener(e -> {
			runAutoBiting(user, item, textPanel);
		});

		stopButton.addActionListener(e -> {
			stopAutoBiting();
		});
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(bidButton);
		add(defaultButton);
		add(stopButton);
	}

	private synchronized void runAutoBiting(UserDTO user, ItemDTO item, TextPanel textPanel) {
		isRun = true;
		new Thread(() -> {
			while (isRun) {
				try {
					BidDTO bidMaxAmount = server.getBidMaxAmount();
					if (bidMaxAmount == null) {
						addBid(user, textPanel, item, item.getItemInitialPrice() + 5.0);
					} else if (bidMaxAmount.getUserID() != user.getUserID()) {
						addBid(user, textPanel, item,bidMaxAmount.getBidAmount() + 5.0);
					}
				} catch (RemoteException | InterruptedException e1) {
					throw new RuntimeException("");
				}
			}
		}).start();
	}

	private void addBid(UserDTO user, TextPanel textPanel, ItemDTO item, double bitAmount) throws RemoteException, InterruptedException {
		BidDTO bidDto = new BidDTO();
		bidDto.setUserID(user.getUserID());
		bidDto.setBidAmount(bitAmount);
		bidDto.setItemDtoId(item.getItemID());
		server.addBid(bidDto);
		textPanel.appendText(user.getUserName(), bitAmount);
		Thread.sleep(5000);
	}

	private synchronized void stopAutoBiting() {
		isRun = false;
	}
}


