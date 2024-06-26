package com.ead.authuser.services.impl;

import com.ead.authuser.clients.CourseClient;
import com.ead.authuser.dtos.UserEventDto;
import com.ead.authuser.enums.ActionType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.publishers.UserEventPublishers;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CourseClient courseClient;

    @Autowired
    UserEventPublishers userEventPublishers;

    @Override
    public Optional<UserModel> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    @Override
    public void delete(UserModel userModel) {
        userRepository.delete(userModel);
    }

    @Override
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable) {
        return userRepository.findAll(spec, pageable);
    }

    @Transactional
    @Override
    public UserModel saveUser(UserModel userModel) {
        userModel = saveUser(userModel);
        userEventPublishers.publishUserEvent(userModel.convertToUserEventDto(), ActionType.CREATE);
        return userModel;
    }

    @Transactional
    @Override
    public void deleteUser(UserModel userModel) {
        delete(userModel);
        userEventPublishers.publishUserEvent(userModel.convertToUserEventDto(), ActionType.DELETE);
    }

    @Transactional
    @Override
    public UserModel updateUser(UserModel userModel) {
        userModel = saveUser(userModel);
        userEventPublishers.publishUserEvent(userModel.convertToUserEventDto(), ActionType.UPDATE);
        return userModel;
    }

    @Override
    public UserModel updatePassword(UserModel userModel) {
        return save(userModel);
    }
}
