package vn.plusplus.lms.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewExamRequest;
import vn.plusplus.lms.controller.request.UpdateExamRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.entities.ExamEntity;
import vn.plusplus.lms.services.ExamService;

@RestController
@RequestMapping(value = "admin/exam")
public class ExamController {
    @Autowired
    ExamService service;
    @Autowired
    ResponseFactory factory;

    private static final Logger logger = LoggerFactory.getLogger(ExamController.class);

    @PostMapping
    public ResponseEntity addExam(@RequestBody NewExamRequest request) {
        logger.info("Add new exam with body {}", request);
        ExamEntity data = service.addNewExam(request);
        return factory.success(data,ExamEntity.class);
    }

    @PutMapping(value = "/{exam_id}")
    public ResponseEntity updateExam(@PathVariable(name = "exam_id") Integer examId,
                                 @RequestBody UpdateExamRequest request) {
        logger.info("Update exam Id [{}] with body {}", examId, request);
        ExamEntity data = service.updateExam(examId, request);
        return factory.success(data,ExamEntity.class);
    }

    @DeleteMapping(value = "/{exam_id}")
    public ResponseEntity deleteExam(@PathVariable(name = "exam_id") Integer examId){
        logger.info("Delete exam Id [{}]", examId);
        String data = service.deleteExam(examId);
        return factory.success(data,String.class);
    }

    @GetMapping(value = "/{exam_id}")
    public ResponseEntity getExamById(@PathVariable(name = "exam_id") Integer examId,
                                      @RequestAttribute Payload payload){
        logger.info("Get exam Id [{}]", examId);
        ExamEntity data = service.getExamById(examId, payload.getUserId());
        return factory.success(data,ExamEntity.class);
    }

}
