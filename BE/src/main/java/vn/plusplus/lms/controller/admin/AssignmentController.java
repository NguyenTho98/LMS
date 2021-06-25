package vn.plusplus.lms.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewAssignmentRequest;
import vn.plusplus.lms.controller.request.UpdateAssignmentRequest;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.entities.AssignmentEntity;
import vn.plusplus.lms.services.AssignmentService;
import vn.plusplus.lms.factory.ResponseFactory;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "admin/assignment")
public class AssignmentController {
    private static final Logger logger = LoggerFactory.getLogger(AssignmentController.class);
    @Autowired
    AssignmentService service;
    @Autowired
    ResponseFactory factory;

    @PostMapping
    public ResponseEntity addAssignment(@RequestBody NewAssignmentRequest request) {
        logger.info("Add new assignment with body {}", request);
        AssignmentEntity data = service.addNewAssignment(request);
        return factory.success(data,AssignmentEntity.class);
    }

    @PutMapping(value = "/{assignment_id}")
    public ResponseEntity updateAssignment(@PathVariable(name = "assignment_id") Integer assignmentId,
                                             @RequestBody UpdateAssignmentRequest request) {
        logger.info("Update assignment Id [{}] with body {}", assignmentId, request);
        AssignmentEntity data= service.updateAssignment(assignmentId,request);
        return factory.success(data,AssignmentEntity.class);
    }
    @DeleteMapping(value = "/{assignment_id}")
    public ResponseEntity deleteAssignment(@PathVariable(name = "assignment_id") Integer assignmentId){
        logger.info("Delete assignment Id [{}]", assignmentId);
        String data = service.deleteAssignment(assignmentId);
        return factory.success(data,String.class);
    }
    @GetMapping(value = "/{assignment_id}")
    public ResponseEntity getAssignmentById(@PathVariable(name = "assignment_id") Integer assignmentId,
                                            @RequestAttribute Payload payload){
        logger.info("Get assignment Id [{}]", assignmentId);
        AssignmentEntity data =service.getAssignmentById(assignmentId,payload.getUserId());
        return factory.success(data,AssignmentEntity.class);
    }

//    @GetMapping()
//    public ResponseEntity<Object> findAssignmentByLessonId(@RequestParam(name = "lesson_id") Integer lessonId){
//        List<AssignmentEntity> data = service.findAssignmentByLessonId(lessonId);
//        return factory.success(data);
//    }
}
