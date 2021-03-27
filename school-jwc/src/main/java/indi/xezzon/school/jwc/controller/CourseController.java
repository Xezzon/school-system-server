package indi.xezzon.school.jwc.controller;

import indi.xezzon.school.common.model.Course;
import indi.xezzon.school.common.model.PagedDTO;
import indi.xezzon.school.common.model.ResultVO;
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
    public ResultVO<PagedDTO<Course>> getCourses(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        PagedDTO<Course> courses = courseService.getCoursesPaged(pageNum, pageSize);
        return new ResultVO<>(courses, "课程列表");
    }

    @PostMapping("/course/{courseId}/student")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResultVO<String> electCourse(@PathVariable("courseId") long courseId) {
        courseService.electCourse(courseId);
        return new ResultVO<>("选课成功");
    }

    @DeleteMapping("/course/{courseId}/student")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResultVO<String> cancelElectCourse(@PathVariable("courseId") long courseId) {
        courseService.cancelElectCourse(courseId);
        return new ResultVO<>("退选课成功");
    }
}
