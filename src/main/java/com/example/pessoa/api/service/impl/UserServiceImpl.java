package com.example.pessoa.api.service.impl;


import com.example.pessoa.api.entity.Group;
import com.example.pessoa.api.entity.GroupUser;
import com.example.pessoa.api.entity.User;
import com.example.pessoa.api.repository.GroupRepository;
import com.example.pessoa.api.repository.GroupUserRepository;
import com.example.pessoa.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

   private final UserRepository userRepository;
   private final GroupRepository groupRepository;
   private final GroupUserRepository groupUserRepository;
   private final PasswordEncoder passwordEncoder;

   @Transactional
   public User salvar(User user, List<String> groups){
       String encryptedPassword = passwordEncoder.encode(user.getSenha());
       user.setSenha(encryptedPassword);
       userRepository.save(user);

       List<GroupUser> listGroupUser = groups.stream().map(groupName -> {
           Optional<Group> possibleGroup = groupRepository.findByNome(groupName);
           if (possibleGroup.isPresent()) {
               Group group = possibleGroup.get();
               return new GroupUser(user, group);
           }
           return null;
       }).filter(group -> group != null).collect(Collectors.toList());
       groupUserRepository.saveAll(listGroupUser);

       return user;
   }

}
