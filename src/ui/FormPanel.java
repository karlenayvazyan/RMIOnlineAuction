package ui;

import server.ItemDTO;
import server.RMIInterface;
import server.UserDTO;
import ui.server.ServerFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

public class FormPanel extends JPanel {

	private JTextField beatPrice;

	private static int interval;
	private static Timer timer;

	public FormPanel(ItemDTO item) {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		JLabel timer = new JLabel();

		countDownTimer(timer, item);
		JLabel nameLabel = new JLabel("Current Bid Price: ");
		beatPrice = new JTextField("Price");

		Border innerBorder = BorderFactory.createTitledBorder("Auction Process");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));


		setLayout(new GridBagLayout());

		//we have a GridBagConstarints object that we use with grid bag layout
		//it has bunch of fields and we've got to initialize those fields
		//before we use it for serious things
		GridBagConstraints gc = new GridBagConstraints();


		//////////////////FIRST ROW//////////////////


		gc.weightx = 1;
		gc.weighty = 0.1;

		//the idea of grid x and y is that we're laying out our components
		//in a grid, but the cells in the grid can be very flexibly sized and
		//they also span columns or rows
		gc.gridx = 0;
		gc.gridy = 0;
		/*gc.weightx = 1;
		gc.weighty = 1;//and the weight controls for a given cell, the weight
		//control how much space it takes up relative to other cells
*/		gc.fill = GridBagConstraints.NONE;
		//this specifies the side of the cell that the control sticks
		//to it if you like
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(nameLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(beatPrice, gc);

		add(timer);
	}

	public Double getBeatPrice() {
		String regex = "[0-9]+";
		if (!beatPrice.getText().matches(regex)) {
			throw new RuntimeException("This filed should contains only numbers");
		}
		return Double.parseDouble(beatPrice.getText());
	}

	private void countDownTimer(JLabel timer, ItemDTO item) {
		FormPanel.timer = new Timer();
		interval = item.getTimeToFinish();
		FormPanel.timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (setInterval() <= 1) {
					try {
						RMIInterface server = ServerFactory.getInstance();
						UserDTO user = server.getWinner(item.getItemID());
						JOptionPane.showMessageDialog(null, String.format("Auction winner is %s", user.getUserName()));
						timer.setText("");
					} catch (RemoteException e) {
						throw new RuntimeException("");
					}
				} else {
					timer.setText(String.valueOf(setInterval()));
				}
			}
		}, 1000, 1000);
	}

	private int setInterval() {
		if (interval == 1)
			timer.cancel();
		return --interval;
	}
}



