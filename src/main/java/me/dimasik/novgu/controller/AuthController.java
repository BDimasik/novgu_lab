package me.dimasik.novgu.controller;

import me.dimasik.novgu.entity.Role;
import me.dimasik.novgu.entity.UserEntity;
import me.dimasik.novgu.repository.GroupRepository;
import me.dimasik.novgu.repository.UserRepository;
import me.dimasik.novgu.util.HttpUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.UUID;

@RestController
public final class AuthController {
    private final UserRepository userRepo;
    private final GroupRepository groupRepository;

    public AuthController(UserRepository userRepo, GroupRepository groupRepository) {
        this.userRepo = userRepo;
        this.groupRepository = groupRepository;
    }

    @PostMapping("/registration")
    public UserEntity addUser(
            @RequestParam("username") String userName,
            @RequestParam("password") String password,
            HttpServletRequest request
    ) {
        UserEntity user = new UserEntity();
        user.setUsername(userName);
        user.setPassword(password);
        UserEntity userFromDb = userRepo.findByUsername(userName);

        if (userFromDb != null) {
            return userFromDb;
        }

        user.setRoles(Collections.singleton(Role.USER));
        user.setIp(HttpUtils.getRequestIP(request));
        user.setSession(UUID.randomUUID());
        return user;
    }

    @PostMapping("/login")
    public UserEntity login(
            @RequestParam("username") String userName,
            @RequestParam("password") String password,
            HttpServletRequest request
    ) {
        UserEntity userFromDb = userRepo.findByUsername(userName);

        if (!userFromDb.getPassword().equals(password)) {
            return new UserEntity();
        }

        return userFromDb;
    }
}