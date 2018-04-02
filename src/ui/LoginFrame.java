package ui;

import server.ItemDTO;
import server.RMIInterface;
import server.UserDTO;
import ui.server.ServerFactory;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class LoginFrame extends JFrame {

    private TextField login;

    private TextField password;

    private JComboBox<String> items;

    private final RMIInterface server;

    public LoginFrame() throws HeadlessException {
        super("Login");

        server = ServerFactory.getInstance();

        setLayout(new FlowLayout());

        this.setLayout(null);

        createLabels();

        createTextFields();

        createButtons();

        createComboBox();

        setLayout(new BorderLayout());

        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createComboBox() {
        try {
            String[] itemNames = server.getItemNames();
            items = new JComboBox<>(itemNames);

            add(items);

            items.setBounds(200, 170, 100, 40);
        } catch (RemoteException e) {
            throw new RuntimeException("");
        }
    }

    private void createButtons() {
        Button submit = new Button("submit");
        Button cancel = new Button("cancel");

        submit.addActionListener(e -> {
            String loginTxt = login.getText();
            String passwordTxt = password.getText();
            String selectedItem = (String) items.getSelectedItem();
            if (loginTxt.isEmpty() || passwordTxt.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Login or password is empty");
                return;
            }
            try {
                UserDTO user = server.getUserByLoginAndPassword(loginTxt, passwordTxt);
                ItemDTO item = server.getItemByName(selectedItem);
                openMainFrame(user, item);
            } catch (RemoteException e1) {
                throw new RuntimeException("Fail to get user by login and password");
            }
        });

        cancel.addActionListener(e -> System.exit(0));

        this.add(submit);
        this.add(cancel);

        cancel.setBounds(100, 260, 70, 40);
        submit.setBounds(180, 260, 70, 40);
    }

    private void openMainFrame(UserDTO user, ItemDTO item) {
        dispose();
        new MainFrame(user, item);
    }

    private void createTextFields() {
        login = new TextField(20);
        password = new TextField(20);

        password.setEchoChar('*');

        this.add(login);
        this.add(password);

        login.setBounds(200, 100, 90, 20);
        password.setBounds(200, 140, 90, 20);
    }

    private void createLabels() {
        Label name = new Label("Login:", Label.CENTER);
        Label password = new Label("Password:", Label.CENTER);
        add(name);
        add(password);
        name.setBounds(70, 90, 90, 60);
        password.setBounds(70, 130, 90, 60);
    }
}
