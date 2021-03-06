package com.epam.tc.dto;

import com.epam.tc.model.Course;
import com.epam.tc.model.User;

public class UserDTO {

    private final User user;

    public UserDTO(User user) {
        this.user = user;
    }

    public boolean canAttend(Course course) {
        return course.isSubscribed(user) && !course.isAttended(user);
    }

    public boolean canEvaluate(Course course) {
        return course.isAttended(user) && !course.hasGrade(user);
    }

    public boolean canSubscribe(Course course) {
        return !course.isSubscribed(user) && !course.isAttended(user);
    }
}
