package org.example.gestion_cinema.service;

import jakarta.transaction.Transactional;
import org.example.gestion_cinema.dao.AppRoleRepository;
import org.example.gestion_cinema.dao.AppUserRepository;
import org.example.gestion_cinema.entities.AppRole;
import org.example.gestion_cinema.entities.AppUser;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;

    public AccountServiceImpl(AppUserRepository appUserRepository,AppRoleRepository appRoleRepository){
        this.appRoleRepository=appRoleRepository;
        this.appUserRepository=appUserRepository;
    }

    @Override
    public AppUser addUser(AppUser user) {
        return appUserRepository.save(user);
    }

    @Override
    public AppRole addRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) throws RoleNotFoundException {
        AppUser user=appUserRepository.findByUsername(username);
        if (user == null) {
            //throw new UsernameNotFoundException("User not found with username: " + username);
        }
        AppRole role=appRoleRepository.findByRoleName(roleName);
        if (role == null) {
            throw new RoleNotFoundException("Role not found with role name: " + roleName);
        }
        System.out.println("User is "+user+" and username is: "+username);
        if(!user.getRoles().contains(role)){
            user.getRoles().add(role);
            appUserRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user=appUserRepository.findByUsername(username);
        if(user==null) throw new UsernameNotFoundException("User not found");
        return new UserPrincipal(user);
    }

    @Override
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }
}
