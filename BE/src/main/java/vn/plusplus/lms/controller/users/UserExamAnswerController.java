package vn.plusplus.lms.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewUserExamAnswerRequest;
import vn.plusplus.lms.controller.request.UpdateUserExamAnswerRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.repository.entities.UserExamAnswerEntity;
import vn.plusplus.lms.services.UserExamAnswerService;

@RestController
@RequestMapping(value = "/userexamanswer")
public class UserExamAnswerController {
    private static final Logger logger = LoggerFactory.getLogger(UserExamAnswerController.class);

    @Autowired
    UserExamAnswerService service;
    @Autowired
    ResponseFactory factory;

    @PostMapping
    public ResponseEntity addNewUserExamAnswer(NewUserExamAnswerRequest request){
        logger.info("Add new user exam answer with body {}",request);
        UserExamAnswerEntity data = service.addNewUserExamAnswer(request);
        return factory.success(data,UserExamAnswerEntity.class);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity updateUserExamAnswer(@PathVariable(name = "id") Integer id,
                                                     @RequestBody UpdateUserExamAnswerRequest request){
        logger.info("Update user exam Id [{}] with body {}", id, request);
        UserExamAnswerEntity data = service.updateUserExamAnswer(id,request);
        return factory.success(data,UserExamAnswerEntity.class);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUserExamAnswer(@PathVariable(name = "id") Integer id){
        logger.info("Delete user exam answer Id [{}]", id);
        String data = service.deleteUserExamAnswer(id);
        return factory.success(data,String.class);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getUserExamAnswerById(@PathVariable(name = "id") Integer id,
                                                      @RequestParam(name = "user_id") Integer userId){
        logger.info("Get slide Id [{}]", id);
        UserExamAnswerEntity data = service.getUserExamAnswerById(id,userId);
        return factory.success(data,UserExamAnswerEntity.class);
    }
}
