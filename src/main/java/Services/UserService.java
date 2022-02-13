package Services;

import Models.DTOs.RegisterDTO;
import Models.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    public UserService(){}

    public String register(RegisterDTO registerDTO){
        return "asdasd";
    }

}
