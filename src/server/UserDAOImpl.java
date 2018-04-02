package server;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private final Map<String, UserDTO> users;

    public UserDAOImpl() {
        users = new HashMap<>();
        UserDTO rafi = new UserDTO(95459450, "rafi", "123", "rafi_boghosians@edu.aua.am", "Komitas", true, 8000000, 500000);
        UserDTO anna = new UserDTO(43118899, "anna", "123", "shahe_tajiryan@edu.aua.am", "Davitashen", true, 50000, 10000);
        UserDTO shahe = new UserDTO(94463499, "shahe", "463499", "anna_abajyan@edu.aua.am", "Charents", true, 3000000, 250000);
        users.put(rafi.getUserName(), rafi);
        users.put(anna.getUserName(), anna);
        users.put(shahe.getUserName(), shahe);
    }

    @Override
    public UserDTO getUserByLoginAndPassword(String login, String password) {
        if (login == null || password == null) {
            throw new RuntimeException("Login or password should not be null");
        }
        UserDTO userDTO = users.get(login);
        Optional<UserDTO> user = Optional.ofNullable(userDTO)
                .filter(userDTO1 -> userDTO1.getUserPassword().equals(password));
        if (!user.isPresent()) {
            throw new RuntimeException("Incorrect login or password");
        }
        return user.get();
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        if (userId == null) {
            throw new RuntimeException("UserId should not be null");
        }
        return users.values().stream().filter(userDTO -> userDTO.getUserID() == userId).findFirst().get();
    }
}
