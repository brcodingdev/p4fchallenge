package br.com.p4f.service;

import br.com.p4f.dto.UserDTO;
import br.com.p4f.entity.User;
import br.com.p4f.exceptions.NotFoundException;
import br.com.p4f.exceptions.UnprocessableEntityException;
import br.com.p4f.mapper.UserMapper;
import br.com.p4f.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserDTO findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()) {
            throw new NotFoundException("user not found");
        }
        return UserMapper.INSTANCE.toDto(userOptional.get());
    }

    public void create(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername().toLowerCase().trim()) != null) {
            throw new UnprocessableEntityException("Usuário já cadastrado com este username");
        }

        User user = UserMapper.INSTANCE.toEntity(userDTO);
        user.setUsername(user.getUsername().toLowerCase().trim());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }

    public void update(Long id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new NotFoundException("user not found");
        }
        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
    }

    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.toDtoList(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(authority);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }

}
