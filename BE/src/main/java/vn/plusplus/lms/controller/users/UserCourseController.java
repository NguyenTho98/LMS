package vn.plusplus.lms.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewUserCourseRequest;
import vn.plusplus.lms.controller.request.UpdateUserCourseRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.repository.entities.UserCourseEntity;
import vn.plusplus.lms.services.UserCourseService;
import java.util.List;

@RestController
@RequestMapping(value = "admin/user-course")
public class UserCourseController {
    private static final Logger logger = LoggerFactory.getLogger(UserCourseController.class);

    @Autowired
    UserCourseService userCourseService;
    @Autowired
    ResponseFactory factory;

    @PostMapping
    public ResponseEntity addNewUserCourse(@RequestBody NewUserCourseRequest request){
        logger.info("Add new user course with body {}",request);
        UserCourseEntity data = userCourseService.addNewUserCourse(request);
        return factory.success(data,UserCourseEntity.class);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateUserCourse(@PathVariable(name = "id") Integer id,
                                             @RequestBody UpdateUserCourseRequest request){
        logger.info("Update user course Id [{}] with body {}",id,request);
        UserCourseEntity data = userCourseService.updateUserCourse(id, request);
        return factory.success(data,UserCourseEntity.class);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUserCourse(@PathVariable(name = "id") Integer id){
        logger.info("Delete user course Id [{}]",id);
        String data = userCourseService.deleteUserCourse(id);
        return factory.success(data,String.class);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getUserCourseById(@PathVariable(name = "id") Integer id){
        logger.info("Get slide by Material_Id [{}]",id);
        UserCourseEntity data = userCourseService.getUserCourseById(id);
        return factory.success(data,UserCourseEntity.class);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity getAllUserCourseByUserId(@RequestParam(name = "user_id") Integer userId){
        logger.info("Get all course of user Id [{}]",userId);
        List<UserCourseEntity> data = userCourseService.getAllUserCourseByUserId(userId);
        return factory.success(data,List.class);
    }
}
