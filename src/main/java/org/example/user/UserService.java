package org.example.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean registerUser(User user) {
        // Sprawdź, czy podany adres email jest już zarejestrowany
        if (findByEmail(user.getEmail()) != null) {
            return false;
        }

        // Zapisz użytkownika do bazy danych
        saveUser(user);
        return true;
    }
}