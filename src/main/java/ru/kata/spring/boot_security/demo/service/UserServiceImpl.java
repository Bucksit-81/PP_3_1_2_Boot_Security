package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger loger = Logger.getLogger(UserServiceImpl.class.getName());

    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(PasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, RoleService roleService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getall();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        Set<Role> roles = user.getRoles();
        HashSet<Role> roles2 = new  HashSet<>();

        for(Role role:roles){
            roles2.add(roleService.getByName(role.getRolename()));
        }
        user.setRoles(roles2);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.addUser(user);
    }

    @Override
    public User getUser(long id) {
        return userRepository.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteUser(id);
    }

    @Override
    @Transactional
    public void updateUser(User user, long id) {
        Set<Role> roles = user.getRoles();
        HashSet<Role> roles2 = new HashSet<>();

        for(Role role:roles){
            roles2.add(roleService.getByName(role.getRolename()));
            loger.log(Level.WARNING,role.getRole());
        }
        user.setRoles(roles2);
        userRepository.updateUser(user,id);
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.getUserByName(username);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByName(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found!");
        }

        for (Role r : user.getRoles()){
            loger.log(Level.WARNING,r.getRole());
        }

        return user;
    }

}