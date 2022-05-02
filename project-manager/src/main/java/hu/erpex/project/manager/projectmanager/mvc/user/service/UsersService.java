package hu.erpex.project.manager.projectmanager.mvc.user.service;

import hu.erpex.project.manager.projectmanager.mvc.user.dto.UserLoginDTO;
import hu.erpex.project.manager.projectmanager.mvc.user.dto.UsersOnTaskDTO;
import hu.erpex.project.manager.projectmanager.mvc.user.entity.UsersEntity;
import hu.erpex.project.manager.projectmanager.mvc.user.dto.UserDTO;
import hu.erpex.project.manager.projectmanager.mvc.user.dto.UserUpdateDTO;
import hu.erpex.project.manager.projectmanager.mvc.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public UsersService (PasswordEncoder _passwordEncoder){
        this.passwordEncoder = _passwordEncoder;
    }

    @Autowired
    private UsersRepository usersRepository;

    public UsersEntity findByUsername(String username){
        System.out.println(usersRepository.findByUsername(username).getId());
        return usersRepository.findByUsername(username);
    }

    public UsersEntity findByUserEmail(String email){
        return usersRepository.findByEmail(email);
    }

    public UsersEntity findById(Long userId){
        return usersRepository.findById(userId);
    }


    @Transactional
    public UserDTO save(UserDTO dto){
        UsersEntity entity = new UsersEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setUserName(dto.getUserName());
        entity.setUserEmail(dto.getUserEmail());
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //entity.setUserPassword(encoder.encode(dto.getPassword()));
        entity.setUserPassword(passwordEncoder.encode(dto.getPassword()));
        entity= usersRepository.save(entity);
        entityToDTO(entity);

        return entityToDTO(entity);
    }



    @Transactional
    public UserUpdateDTO update(UserUpdateDTO dto){
        UsersEntity entity = usersRepository.findById(dto.getId());

        if(dto.getPassword()!=null){
            entity.setUserPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if(dto.getUserEmail()!=null){
            entity.setUserEmail(dto.getUserEmail());
        }
        if(dto.getUserName()!=null){
            entity.setUserName(dto.getUserName());
        }
        entity = usersRepository.update(entity);

        return updateEntityToDTO(entity);
    }

    private UserUpdateDTO updateEntityToDTO(UsersEntity entity){
        UserUpdateDTO dto = new UserUpdateDTO();
        dto.setUserName(entity.getUserName());
        dto.setUserEmail(entity.getUserEmail());
        dto.setId(entity.getId());
        dto.setPassword("");
        return dto;
    }

    private UserDTO entityToDTO(UsersEntity entity){
        UserDTO dto = new UserDTO();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUserName(entity.getUserName());
        dto.setUserEmail(entity.getUserEmail());
        dto.setPassword("");
        return dto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = usersRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found in the database!");
        }else {

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //roleokat lehet belerakni
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), authorities);
    }

}
