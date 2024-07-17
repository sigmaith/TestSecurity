package com.example.testsecurity.service;

import com.example.testsecurity.dto.JoinDTO;
import com.example.testsecurity.entity.UserEntity;
import com.example.testsecurity.exception.DuplicateUserException;
import com.example.testsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {

        // db에 이미 동일한 username을 가진 회원이 존재하는지 검증 필요
        if (userRepository.existsByUsername(joinDTO.getUsername())) {
            throw new DuplicateUserException("Username already exists: " + joinDTO.getUsername());
        }

        UserEntity newUser = new UserEntity();

        newUser.setUsername(joinDTO.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        newUser.setRole("ROLE_ADMIN");

        userRepository.save(newUser);
    }
}
