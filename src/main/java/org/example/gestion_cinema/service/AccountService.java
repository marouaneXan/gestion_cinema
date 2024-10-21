package org.example.gestion_cinema.service;

import org.example.gestion_cinema.entities.AppRole;
import org.example.gestion_cinema.entities.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface AccountService {
    AppUser addUser(AppUser user);
    AppRole addRole(AppRole role);
    void addRoleToUser(String username,String roleName) throws RoleNotFoundException;
    UserDetails loadUserByUsername(String username);
    List<AppUser> getUsers();

}
