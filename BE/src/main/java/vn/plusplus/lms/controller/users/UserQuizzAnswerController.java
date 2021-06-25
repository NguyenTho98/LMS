package vn.plusplus.lms.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewUserQuizzAnswerRequest;
import vn.plusplus.lms.controller.request.UpdateUserQuizzAnswerRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.repository.entities.UserQuizzAnswerEntity;
import vn.plusplus.lms.services.UserQuizzAnswerService;

@RestController
@RequestMapping(value = "/userquizzanswer")
public class UserQuizzAnswerController {
    private static final Logger logger = LoggerFactory.getLogger(UserQuizzAnswerController.class);

    @Autowired
    UserQuizzAnswerService service;
    @Autowired
    ResponseFactory factory;

    @PostMapping
    public ResponseEntity addUserQuizzAnswer(@RequestBody NewUserQuizzAnswerRequest request) {
        logger.info("Add new user quizz question with body {}", request);
        UserQuizzAnswerEntity data = service.addNewUserQuizzAnswer(request);
        return factory.success(data,UserQuizzAnswerEntity.class);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateUserQuizzAnswer(@PathVariable(name = "id") Integer id,
                                                       @RequestBody UpdateUserQuizzAnswerRequest request) {
        logger.info("Update user quizz answer Id [{}] with body {}", id, request);
        UserQuizzAnswerEntity data = service.updateUserQuizzAnswer(id, request);
        return factory.success(data,UserQuizzAnswerEntity.class);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUserQuizzAnswer(@PathVariable(name = "id") Integer id) {
        logger.info("Delete user quizz answer [{}]", id);
        String data = service.deleteUserQuizzAnswer(id);
        return factory.success(data,String.class);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getUserQuizzAnswerById(@PathVariable(name = "id") Integer id,
                                                        @RequestParam(name = "user_id") Integer userId){
        logger.info("Get user quizz answer Id [{}]", id);
        UserQuizzAnswerEntity data = service.getUserQuizzAnswerById(id,userId);
        return factory.success(data,UserQuizzAnswerEntity.class);
    }

}
