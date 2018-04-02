package ui;

import server.ItemDTO;
import server.UserDTO;

import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame {

	MainFrame(UserDTO user, ItemDTO item) {
		super("Main");

		setLayout(new BorderLayout());


		TextPanel textPanel = new TextPanel();
		FormPanel formPanel = new FormPanel(item);
		Toolbar toolbar = new Toolbar(formPanel, textPanel, user, item);

//		toolbar.setStringListener(textPanel::appendText);

		add(toolbar, BorderLayout.SOUTH);
		add(textPanel, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);
		
		
		setSize(1000,800);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
}






