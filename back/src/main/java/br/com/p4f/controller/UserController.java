package br.com.p4f.controller;


import br.com.p4f.dto.UserDTO;
import br.com.p4f.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void create(@Valid @RequestBody UserDTO user) {
        userService.create(user);
    }

    @GetMapping(path = "{id}")
    public UserDTO getById(@PathVariable(name = "id") Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable(name = "id") Long id,
                       @Valid @RequestBody UserDTO userDTO) {
        userService.update(id, userDTO);
    }
}
