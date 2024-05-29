package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.PasswordHasher;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.UserDTO;
import com.uade.tpo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> buscarTodosUsuarios(){
        return userRepository.buscarTodos();
    }

    private User buscarUsuarioUnico(String username) { return userRepository.buscarUnico(username);}

    public boolean checkUser(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String posiblePassword = userDTO.getPassword();
        Optional<User> usuarioBuscado = Optional.of(buscarUsuarioUnico(username));
        return PasswordHasher.checkPassword(posiblePassword, usuarioBuscado.get().getPasswordHash());
    }
}
