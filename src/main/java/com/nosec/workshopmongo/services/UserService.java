package com.nosec.workshopmongo.services;

import com.nosec.workshopmongo.domain.User;
import com.nosec.workshopmongo.dto.UserDTO;
import com.nosec.workshopmongo.repository.UserRepository;
import com.nosec.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(String id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User obj){
        return userRepository.insert(obj);
    }

    public void delete (String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User update (User obj){
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return userRepository.save(newObj);
    }

    public User fromDto(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

    public void updateData(User novo, User obj){
        novo.setName(obj.getName());
        novo.setEmail(obj.getEmail());
    }
}
