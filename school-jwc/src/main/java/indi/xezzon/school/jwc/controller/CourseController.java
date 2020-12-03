package indi.xezzon.school.jwc.controller;

import indi.xezzon.school.common.model.Course;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xezzon
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @GetMapping("/all")
    public List<Course> courses() {
        return null;
    }
}
