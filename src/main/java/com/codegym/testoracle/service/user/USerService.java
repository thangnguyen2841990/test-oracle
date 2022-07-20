package com.codegym.testoracle.service.user;

import com.codegym.testoracle.model.dto.UserPrincipal;
import com.codegym.testoracle.model.entity.Role;
import com.codegym.testoracle.model.entity.User;
import com.codegym.testoracle.repository.IRoleRepository;
import com.codegym.testoracle.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class USerService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {
        String password = user.getPassword();
        String encodePassword = passwordEncoder.encode(password);//Mã hóa pass của người dùng
        user.setPassword(encodePassword);
        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findByName("ROLE_USER");
        roles.add(new Role( role.getId(), role.getName()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User saveShop(User user) {
        String password = user.getPassword();
        String encodePassword = passwordEncoder.encode(password);//Mã hóa pass của người dùng
        user.setPassword(encodePassword);
        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findByName("ROLE_SHOP");
        roles.add(new Role( role.getId(), role.getName()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User saveAdmin(User user) {
        String password = user.getPassword();
        String encodePassword = passwordEncoder.encode(password);//Mã hóa pass của người dùng
        user.setPassword(encodePassword);
        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findByName("ROLE_ADMIN");
        roles.add(new Role( role.getId(), role.getName()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return UserPrincipal.build(user);
    }
}
