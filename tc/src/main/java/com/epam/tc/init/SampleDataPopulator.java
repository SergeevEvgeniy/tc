package com.epam.tc.init;

import com.epam.tc.model.Category;
import com.epam.tc.model.Course;
import com.epam.tc.model.User;
import com.epam.tc.model.UserRole;
import com.epam.tc.service.category.CategoryService;
import com.epam.tc.service.course.CourseService;
import com.epam.tc.service.evaluate.EvaluateService;
import com.epam.tc.service.user.UserService;
import com.epam.tc.service.userRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleDataPopulator {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private CategoryService categoryService;

    public void initDBvaluesIsEmpty() {
        if (categoryService.getAll().isEmpty()) {
            setDefaultCategories();
        }
        if (userRoleService.getAll().isEmpty()) {
            setDefaultUserRoles();
        }
        if (userService.getAll().isEmpty()) {
            setDefaultUsers();
        }
        if (courseService.getAll().isEmpty()) {
            setDefaultCourses();
        }
    }

    private void setDefaultCourses() {
        Course course;

        course = new Course("Draft");
        course.setOwner(userService.getUserByLogin("lecturer-a"));
        course.setCategory(categoryService.getByName("Development"));
        courseService.create(course);

        course = new Course("Proposal");
        course.setOwner(userService.getUserByLogin("lecturer-a"));
        course.setCategory(categoryService.getByName("Development"));
        courseService.create(course);

        course = new Course("Rejected");
        course.setOwner(userService.getUserByLogin("lecturer-a"));
        course.setCategory(categoryService.getByName("Development"));
        courseService.create(course);

        course = new Course("New");
        course.setOwner(userService.getUserByLogin("lecturer-a"));
        course.setCategory(categoryService.getByName("Development"));
        courseService.create(course);
        courseService.addSubscriber(course.getId(), userService.getUserByLogin("user-a"));

        course = new Course("Open");
        course.setOwner(userService.getUserByLogin("lecturer-a"));
        course.setCategory(categoryService.getByName("Development"));
        courseService.create(course);
        courseService.addSubscriber(course.getId(), userService.getUserByLogin("user-b"));
        courseService.addAttender(course.getId(), userService.getUserByLogin("user-a"));

        course = new Course("Ready");
        course.setOwner(userService.getUserByLogin("lecturer-a"));
        course.setCategory(categoryService.getByName("Project Management"));
        courseService.create(course);
        courseService.addAttender(course.getId(), userService.getUserByLogin("user-a"));
        courseService.addAttender(course.getId(), userService.getUserByLogin("user-b"));

        course = new Course("In Progress");
        course.setOwner(userService.getUserByLogin("lecturer-a"));
        course.setCategory(categoryService.getByName("Project Management"));
        courseService.create(course);
        courseService.addAttender(course.getId(), userService.getUserByLogin("user-a"));
        courseService.addAttender(course.getId(), userService.getUserByLogin("user-b"));

        course = new Course("Finished");
        course.setOwner(userService.getUserByLogin("lecturer-a"));
        course.setCategory(categoryService.getByName("Project Management"));
        courseService.create(course);
        courseService.addAttender(course.getId(), userService.getUserByLogin("user-a"));
        courseService.addAttender(course.getId(), userService.getUserByLogin("user-b"));
        evaluateService.setGrade(course, userService.getUserByLogin("user-a"), 3);
    }

    private void setDefaultUserRoles() {
        UserRole userRole;

        userRole = new UserRole("Knowledge Manager");
        userRoleService.create(userRole);

        userRole = new UserRole("Department Manager");
        userRoleService.create(userRole);

        userRole = new UserRole("Lector");
        userRoleService.create(userRole);

        userRole = new UserRole("User");
        userRoleService.create(userRole);
    }

    private void setDefaultCategories() {
        Category category;

        category = new Category("Development");
        categoryService.create(category);

        category = new Category("Project Management");
        categoryService.create(category);
    }

    private void setDefaultUsers() {
        User user;

        user = new User("km@tc.edu", "km", "123", userRoleService.getURbyName("Knowledge Manager"));
        userService.create(user);

        user = new User("dm@tc.edu", "dm", "123", userRoleService.getURbyName("Department Manager"));
        userService.create(user);

        user = new User("lecturer-a@tc.edu", "lecturer-a", "123", userRoleService.getURbyName("Lector"));
        userService.create(user);

        user = new User("lecturer-b@tc.edu", "lecturer-b", "123", userRoleService.getURbyName("Lector"));
        userService.create(user);

        user = new User("user-a@tc.edu", "user-a", "123", userRoleService.getURbyName("User"));
        userService.create(user);

        user = new User("user-b@tc.edu", "user-b", "123", userRoleService.getURbyName("User"));
        userService.create(user);

        user = new User("user-c@tc.edu", "user-c", "123", userRoleService.getURbyName("User"));
        userService.create(user);
    }
}
