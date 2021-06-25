package vn.plusplus.lms.controller.admin;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewLessonRequest;
import vn.plusplus.lms.controller.request.UpdateLessonRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.entities.LessonEntity;
import vn.plusplus.lms.services.AssignmentService;
import vn.plusplus.lms.services.LessonService;
import vn.plusplus.lms.services.SessionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "admin/lesson")
public class LessonController {
    private static final Logger logger = LoggerFactory.getLogger(LessonController.class);

    @Autowired
    LessonService lessonService;
    @Autowired
    ResponseFactory factory;
    @Autowired
    SessionService sessionService;
    @Autowired
    AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity addLesson(@RequestBody NewLessonRequest request){
        logger.info("Add new lesson with body {}", request);
        LessonEntity data= lessonService.addNewLesson(request);
        return factory.success(data,LessonEntity.class);
    }
    @PutMapping(value = "/{lesson_id}")
    public ResponseEntity updateLesson(
            @PathVariable(name = "lesson_id") Integer lessonId,
            @RequestBody UpdateLessonRequest request){
        logger.info("Update lesson Id [{}] with body {}", lessonId, request);
        LessonEntity data = lessonService.updateLesson(lessonId, request);
        return factory.success(data,LessonEntity.class);
    }

    @DeleteMapping(value ="/{lesson_id}")
    public ResponseEntity deleteLesson(
            @PathVariable(name = "lesson_id") Integer lessonId){
        logger.info("Delete lesson Id [{}]", lessonId);
        String data = lessonService.deleteLesson(lessonId);
        return factory.success(data,String.class);
    }

    @GetMapping(value = "/{lesson_id}")
    public ResponseEntity getLessonById(@PathVariable(name = "lesson_id") Integer lessonId,
                                        @RequestAttribute Payload payload){
        logger.info("Get lesson Id [{}]", lessonId);
        LessonEntity data = lessonService.getLessonById(lessonId, payload.getUserId());
        return factory.success(data,LessonEntity.class);
    }

    @GetMapping()
    public ResponseEntity<Object> findLessonByCourseId(@RequestParam(name = "course_id") Integer courseId){
        List<LessonEntity> data = lessonService.findLessonByCourseId(courseId);
        return factory.success(data);
    }

//    @GetMapping("/assignment-session")
//    public ResponseEntity<Object> findAssignmentAndSessionBySessionId(@RequestParam(name = "lesson_id") Integer lessonId){
//        List<AssignmentEntity> assignmentEntityList = assignmentService.findAssignmentByLessonId(lessonId);
//        List<SessionEntity> sessionEntityList = sessionService.findSessionByLessonId(lessonId);
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("assignments", assignmentEntityList);
//        map.put("sessions", sessionEntityList);
//        return factory.success(map);
//    }
}
