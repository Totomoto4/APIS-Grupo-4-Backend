package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.PasswordHasher;
import com.uade.tpo.demo.entity.Role;
import com.uade.tpo.demo.entity.RoleEnum;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.exceptions.UserEmailDuplicateException;
import com.uade.tpo.demo.exceptions.UserNotFoundException;
import com.uade.tpo.demo.repository.RoleRepository;
import com.uade.tpo.demo.repository.UserRepository;
import com.uade.tpo.demo.security.AuthenticationRequest;
import com.uade.tpo.demo.security.AuthenticationResponse;
import com.uade.tpo.demo.security.JwtService;
import com.uade.tpo.demo.security.RegisterRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) throws UserEmailDuplicateException {

     Optional<Role> optionalRole;

     if (request.isAdministrator()){
         optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
     } else {
         optionalRole = roleRepository.findByName(RoleEnum.USER);
     }

        User usuarioNuevo = User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .role(optionalRole.get())
                .passwordHash(PasswordHasher.hashPassword(request.getPassword()))
                .build();

        //verificamos si ya tenemos un usario con ese email
        if (!checkEmailRepetido(usuarioNuevo.getEmail())){
            userRepository.save(usuarioNuevo);
            System.out.println("Usuario guardado con exito");

            var jwtToken = jwtService.generateToken(usuarioNuevo);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build();
        } else {
            throw new UserEmailDuplicateException();
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws UserNotFoundException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build();
        } catch (Exception e){
         throw new UserNotFoundException();
        }
    }

    public Optional<User> buscarUsuarioUnico(String userEmail) { return userRepository.findByEmail(userEmail);}

    //Devuelve true si esta
    private boolean checkEmailRepetido(String userEmail) {
        return buscarUsuarioUnico(userEmail).isPresent();
    }

}
