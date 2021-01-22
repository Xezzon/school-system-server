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
@RequestMapping
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public PageResult<Course> getCourses(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return courseService.getCoursesPaged(pageNum, pageSize);
    }

    @PostMapping("/course/{courseId}/student")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void electCourse(@PathVariable("courseId") long courseId) {
        courseService.electCourse(courseId);
    }

    @DeleteMapping("/course/{courseId}/student")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void cancelElectCourse(@PathVariable("courseId") long courseId) {
        courseService.cancelElectCourse(courseId);
    }
}
