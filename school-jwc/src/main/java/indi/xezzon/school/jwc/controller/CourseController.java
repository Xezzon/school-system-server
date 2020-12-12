package indi.xezzon.school.jwc.controller;

import indi.xezzon.school.common.model.Course;
import indi.xezzon.school.common.model.PageResult;
import indi.xezzon.school.jwc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author xezzon
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public PageResult<Course> getCourses(@RequestParam("page_num") int pageNum, @RequestParam("page_size") int pageSize) {
        return courseService.getCoursesPaged(pageNum, pageSize);
    }

    @PostMapping("/elect")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void electCourse(long studentId, long courseId) {

    }

    @DeleteMapping("/elect")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void cancelElectCourse(long studentId, long courseId) {

    }
}
