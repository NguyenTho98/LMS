package vn.plusplus.lms.controller.admin;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewCourseRequest;
import vn.plusplus.lms.controller.request.UpdateCourseRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.CourseRepository;
import vn.plusplus.lms.repository.entities.CourseEntity;
import vn.plusplus.lms.services.CourseService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "admin/course")
@RequiredArgsConstructor
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;
    private final ResponseFactory factory;
    private final CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity addCourse(@RequestBody NewCourseRequest request) {
        logger.info("Add new course with body {}", request);
        CourseEntity data = courseService.addNewCourse(request);
        return factory.success(data,CourseEntity.class);
    }

    @PutMapping(value = "/{course_id}")
    public ResponseEntity updateCourse(@PathVariable(name = "course_id") Integer courseId,
                                       @RequestBody UpdateCourseRequest request) {
        logger.info("Update course Id [{}] with body {}", courseId, request);
        CourseEntity data = courseService.updateCourse(courseId, request);
        return factory.success(data, CourseEntity.class);
    }

    @GetMapping(value = "/{course_id}")
    public ResponseEntity getCourseById(@PathVariable(name = "course_id") Integer courseId,
                                        @RequestAttribute Payload payload) {
        logger.info("Get course Id [{}]", courseId);
        CourseEntity data = courseService.getCourseById(courseId, payload.getUserId());
        return factory.success(data, CourseEntity.class);
    }

    @DeleteMapping(value = "/{course_id}")
    public ResponseEntity deleteCourse(@PathVariable(name = "course_id") Integer courseId) {
        logger.info("Delete course Id [{}]", courseId);
        String data = courseService.deleteCourse(courseId);
        return factory.success(data, String.class);
    }

    @GetMapping(value = "")
    public ResponseEntity findAll() {
        List<CourseEntity> data = courseRepository.findAll();
        return factory.success(data);
    }
}
