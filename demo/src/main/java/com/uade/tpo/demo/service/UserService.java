package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.PasswordHasher;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.UserAccess;
import com.uade.tpo.demo.entity.UserDTO;
import com.uade.tpo.demo.exceptions.UserEmailDuplicateException;
import com.uade.tpo.demo.exceptions.UserNotFoundException;
import com.uade.tpo.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> buscarUsuarioUnico(String userEmail) { return userRepository.buscarUnico(userEmail);}

    public UserDTO checkUser(UserAccess userAccess) throws UserNotFoundException {
        String userEmail = userAccess.getEmail();
        String possiblePassword = userAccess.getPassword();

        // Busco si hay un usuario con ese email
        Optional<User> userEncontrado = this.buscarUsuarioUnico(userEmail);

        // Si lo encontró y coincide el password devuelve un UserDTO, si no null
        if (userEncontrado.isPresent() && PasswordHasher.checkPassword(possiblePassword, userEncontrado.get().getPasswordHash())) {
            return new UserDTO(userEncontrado.get());
        } else {
            throw new UserNotFoundException();
        }
    }

    //Devuelve true si esta
    private boolean checkEmailRepetido(String userEmail) {
        return buscarUsuarioUnico(userEmail).isPresent();
    }

    @Transactional
    public UserDTO crearUser(User userCreado) throws UserEmailDuplicateException {
        //recibimos la password en plain txt y las guardamos hasheada
        String hashedPass = PasswordHasher.hashPassword(userCreado.getPasswordHash());
        userCreado.setPasswordHash(hashedPass);

        //verificamos si ya tenemos un usario con ese email
        if (!checkEmailRepetido(userCreado.getEmail())){
            userRepository.crearUser(userCreado);
            System.out.println("Usuario guardado con exito");
            return new UserDTO(userCreado);
        } else {
            throw new UserEmailDuplicateException();
        }
    }
}
