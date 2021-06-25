package vn.plusplus.lms.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewUserExamRequest;
import vn.plusplus.lms.controller.request.UpdateUserExamRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.repository.entities.UserExamEntity;
import vn.plusplus.lms.services.UserExamService;

@RestController
@RequestMapping(value = "/userexam")
public class UserExamController {
    private static final Logger logger = LoggerFactory.getLogger(UserExamController.class);

    @Autowired
    UserExamService userExamService;
    @Autowired
    ResponseFactory factory;

    @PostMapping
    public ResponseEntity addUserExam(@RequestBody NewUserExamRequest request){
        logger.info("Add new user exam with body {}", request);
        UserExamEntity data = userExamService.addNewUserExam(request);
        return  factory.success(data,UserExamEntity.class);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateUserExam(@PathVariable(name = "id") Integer id,
                                         @RequestBody UpdateUserExamRequest request){
        logger.info("Update user exam Id [{}] with body {}", id, request);
        UserExamEntity data = userExamService.updateUserExam(id,request);
        return factory.success(data,UserExamEntity.class);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUserExam(@PathVariable(name = "id") Integer id){
        logger.info("Delete user exam Id [{}]", id);
        String data = userExamService.deleteUserExam(id);
        return  factory.success(data,String.class);
    }

    @GetMapping(value = "/{id}")
    public UserExamEntity getUserExamById(@PathVariable(name = "id") Integer id,
                                          @RequestParam(name = "user_id") Integer userId){
        logger.info("Get user exam Id [{}]", id);
        return userExamService.getUserExamById(id,userId);
    }
}
