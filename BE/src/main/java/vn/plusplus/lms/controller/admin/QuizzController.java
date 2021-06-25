package vn.plusplus.lms.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewQuizzRequest;
import vn.plusplus.lms.controller.request.UpdateQuizzRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.entities.QuizzEntity;
import vn.plusplus.lms.services.QuizzService;

@RestController
@RequestMapping(value = "admin/quizz")
public class QuizzController {
    private static final Logger logger = LoggerFactory.getLogger(QuizzController.class);
    @Autowired
    QuizzService service;
    @Autowired
    ResponseFactory factory;

    @PostMapping
    public ResponseEntity addQuizz(@RequestBody NewQuizzRequest request) {
        logger.info("Add new quizz with body {}", request);
        QuizzEntity data = service.addNewQuizz(request);
        return factory.success(data,QuizzEntity.class);
    }

    @PutMapping(value = "/{quizz_id}")
    public ResponseEntity updateQuizz(@PathVariable(name = "quizz_id") Integer quizzId,
                                   @RequestBody UpdateQuizzRequest request) {
        logger.info("Update quizz Id [{}] with body {}", quizzId, request);
        QuizzEntity data = service.updateQuizz(quizzId, request);
        return factory.success(data,QuizzEntity.class);
    }

    @DeleteMapping(value = "/{quizz_id}")
    public ResponseEntity deleteQuizz(@PathVariable(name = "quizz_id") Integer quizzId) {
        logger.info("Delete quizz Id [{}]", quizzId);
        String data = service.deleteQuizz(quizzId);
        return factory.success(data,String.class);
    }

    @GetMapping(value = "/{quizz_id}")
    public ResponseEntity getQuizzById(@PathVariable(name = "quizz_id") Integer quizzId,
                                       @RequestAttribute Payload payload) {
        logger.info("Get quizz Id [{}]", quizzId);
        QuizzEntity data = service.getQuizzById(quizzId,payload.getUserId());
        return factory.success(data,QuizzEntity.class);
    }

}
