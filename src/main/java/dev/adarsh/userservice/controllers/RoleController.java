package dev.adarsh.userservice.controllers;

import dev.adarsh.userservice.dtos.CreateRoleRequestDto;
import dev.adarsh.userservice.models.Role;
import dev.adarsh.userservice.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;
    public RoleController(RoleService roleService){
        this.roleService=roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(CreateRoleRequestDto requestDto){
        Role role=roleService.createRole(requestDto.getName());
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
