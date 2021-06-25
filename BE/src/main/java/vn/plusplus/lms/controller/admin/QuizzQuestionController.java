package vn.plusplus.lms.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewQuizzQuestionRequest;
import vn.plusplus.lms.controller.request.UpdateQuizzQuestionRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.entities.QuizzQuestionEntity;
import vn.plusplus.lms.services.QuizzQuestionService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "admin/quizzquestion")
public class QuizzQuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuizzQuestionController.class);
    @Autowired
    QuizzQuestionService service;
    @Autowired
    ResponseFactory factory;

    @PostMapping
    public ResponseEntity addQuizzQuestion(@RequestBody NewQuizzQuestionRequest request) {
        logger.info("Add new quizz question with body {}", request);
        QuizzQuestionEntity data = service.addNewQuizzQuestion(request);
        return factory.success(data, QuizzQuestionEntity.class);
    }

    @PutMapping(value = "/{quizzquestion_id}")
    public ResponseEntity updateQuizzQuestion(@PathVariable(name = "quizzquestion_id") Integer quizzQuestionId,
                                              @RequestBody UpdateQuizzQuestionRequest request) {
        logger.info("Update quizz question Id [{}] with body {}", quizzQuestionId, request);
        QuizzQuestionEntity data = service.updateQuizzQuestion(quizzQuestionId, request);
        return factory.success(data, QuizzQuestionEntity.class);
    }

    @DeleteMapping(value = "/{quizzquestion_id}")
    public ResponseEntity deleteQuizzQuestion(@PathVariable(name = "quizzquestion_id") Integer quizQuestionId) {
        logger.info("Delete quizz question Id [{}]", quizQuestionId);
        String data = service.deleteQuizzQuestion(quizQuestionId);
        return factory.success(data, String.class);
    }

    @GetMapping(value = "/{quizzquestion_id}")
    public ResponseEntity getQuizzQuestionById(@PathVariable(name = "quizzquestion_id") Integer quizQuestionId,
                                               @RequestParam(name = "user_id") Integer userId) {
        logger.info("Get quizz question Id [{}]", quizQuestionId);
        QuizzQuestionEntity data = service.getQuizzQuestionById(quizQuestionId, userId);
        return factory.success(data, QuizzQuestionEntity.class);
    }

    @GetMapping
    public ResponseEntity getQuizzQuestionByQuizzId(@RequestParam(name = "quizz_id") Integer quizzId,
                                                    @RequestAttribute Payload payload) {
        logger.info("Get quizz question ID [{}]", quizzId);
        List<QuizzQuestionEntity> data = service.getQuizzQuestionsByQuizzId(quizzId, payload.getUserId());
        return factory.success(data, List.class);
    }

//    @GetMapping()
//    public ResponseEntity<Object> findQuizzQuestionByQuizzId(@RequestParam(name = "quizz_id") Integer quizzId) {
//        List<QuizzQuestionEntity> data = service.findQuizzQuestionByQuizzId(quizzId);
//        return factory.success(data);
//    }
}
