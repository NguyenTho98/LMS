package vn.plusplus.lms.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewUserQuizzRequest;
import vn.plusplus.lms.controller.request.UpdateUserQuizzRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.repository.entities.UserQuizzEntity;
import vn.plusplus.lms.services.UserQuizzService;

@RestController
@RequestMapping(value = "/userquizz")
public class UserQuizzController {
    private static final Logger logger = LoggerFactory.getLogger(UserQuizzController.class);

    @Autowired
    UserQuizzService service;
    @Autowired
    ResponseFactory factory;

    @PostMapping
    public ResponseEntity addNewUserQuizz(@RequestBody NewUserQuizzRequest request){
        logger.info("Add new user quizz with body {}", request);
        UserQuizzEntity data = service.addNewUserQuizz(request);
        return factory.success(data,UserQuizzEntity.class);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateUserQuizz(@PathVariable(name = "id")Integer id,
                                           @RequestBody UpdateUserQuizzRequest request){
        logger.info("Update user quizz Id [{}] with body {}", id, request);
        UserQuizzEntity data = service.updateUserQuizz(id,request);
        return factory.success(data,UserQuizzEntity.class);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUserQuizz(@PathVariable(name ="id") Integer id){
        logger.info("Delete user quizz [{}]", id);
        String data = service.deleteUserQuizz(id);
        return factory.success(data,String.class);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getUserQuizzById(@PathVariable(name ="id") Integer id,
                                            @RequestParam(name = "user_id") Integer userId){
        logger.info("Get user quizz Id [{}]", id);
        UserQuizzEntity data = service.getUserQuizzById(id,userId);
        return factory.success(data,UserQuizzEntity.class);
    }

}
