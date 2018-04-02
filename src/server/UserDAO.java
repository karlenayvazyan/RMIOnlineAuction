package server;

public interface UserDAO {

    UserDTO getUserByLoginAndPassword(String login, String password);

    UserDTO getUserById(Integer userId);
}
