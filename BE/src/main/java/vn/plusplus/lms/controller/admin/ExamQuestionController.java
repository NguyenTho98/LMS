package vn.plusplus.lms.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewExamQuestionRequest;
import vn.plusplus.lms.controller.request.UpdateExamQuestionRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.entities.ExamQuestionEntity;
import vn.plusplus.lms.services.ExamQuestionService;

@RestController
@RequestMapping(value = "admin/examquestion")
public class ExamQuestionController {
    private static final Logger logger = LoggerFactory.getLogger(ExamQuestionController.class);

    @Autowired
    ExamQuestionService service;
    @Autowired
    ResponseFactory factory;

    @PostMapping
    public ResponseEntity addExamQuestion(@RequestBody NewExamQuestionRequest request) {
        logger.info("Add new exam question with body {}", request);
        ExamQuestionEntity data = service.addNewExamQuestion(request);
        return factory.success(data,ExamQuestionEntity.class);
    }

    @PutMapping(value = "/{examquestion_id}")
    public ResponseEntity updateExamQuestion(@PathVariable(name = "examquestion_id") Integer examQuestionId,
                                                 @RequestBody UpdateExamQuestionRequest request) {
        logger.info("Update course Id [{}] with body {}", examQuestionId, request);
        ExamQuestionEntity data = service.updateExamQuestion(examQuestionId, request);
        return factory.success(data,ExamQuestionEntity.class);
    }

    @DeleteMapping(value = "/{examquestion_id}")
    public ResponseEntity deleteExamQuestion(@PathVariable(name = "examquestion_id") Integer examQuestionId){
        logger.info("Delete Exam Question Id [{}]", examQuestionId);
        String data = service.deleteExamQuestion(examQuestionId);
        return factory.success(data,String.class);
    }

    @GetMapping(value = "/{examquestion_id}")
    public ResponseEntity getExamQuestionById(@PathVariable(name = "examquestion_id") Integer examQuestionId,
                                              @RequestAttribute Payload payload){
        logger.info("Get Exam Question Id [{}]", examQuestionId);
        ExamQuestionEntity data = service.getExamQuestionById(examQuestionId, payload.getUserId());
        return factory.success(data,ExamQuestionEntity.class);
    }
}
