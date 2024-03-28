package com.auth1.authlearning.service;

import com.auth1.authlearning.dtos.LoginRequestDto;
import com.auth1.authlearning.model.Token;
import com.auth1.authlearning.model.User;
import com.auth1.authlearning.repository.TokenRepository;
import com.auth1.authlearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public User signUp(String email, String password, String name){
//        User user = new User(name, email, password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setName(name);
        return userRepository.save(user);
    }

    public Token login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new RuntimeException("Invalid userOptional or password");
        }

        User user = userOptional.get();
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid userOptional or password");
        }
        Token token = new Token();
        token.setUser(user);
        token.setValue(UUID.randomUUID().toString());

        Date expiredDate = getExpiredDate();

        token.setExpireAt(expiredDate);

        return tokenRepository.save(token);
    }

    private Date getExpiredDate(){
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(new Date());
        calendarDate.add(Calendar.DAY_OF_MONTH, 30);
        Date expiredDate = calendarDate.getTime();
        return expiredDate;
    }
}
