package com.ead.course.services.impl;

import com.ead.course.repositories.CourseRepository;
import com.ead.course.services.CourseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Spring bean
public class CourseUserServiceImpl implements CourseUserService {

    @Autowired
    CourseRepository courseUSerRepository;

}
