package vn.plusplus.lms.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.LoginUserRequest;
import vn.plusplus.lms.controller.request.NewUserRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.model.UserDTO;
import vn.plusplus.lms.repository.entities.UserEntity;
import vn.plusplus.lms.services.UserService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    ResponseFactory factory;

    @GetMapping
    public ResponseEntity getUserInfo(@RequestAttribute Payload payload){
        logger.info("Get information of user Id [{}]", payload.getUserId());
        UserDTO data = userService.getUserInfo(payload.getUserId());
        return factory.success(data,UserDTO.class);
    }
    @PostMapping(value = "/login")
    public ResponseEntity loginUser(@RequestBody LoginUserRequest request) {
        logger.info("Login with user's name: " + request.getUserName());
        String data = userService.loginUser(request);
        return factory.success(data,String.class);
    }

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@RequestBody NewUserRequest request){
        logger.info("Register user "+request.getUserName());
        UserEntity data = userService.registerUser(request);
        return factory.success(data,UserEntity.class);
    }

    @GetMapping(value = "/logout")
    public ResponseEntity logoutUser(@RequestAttribute Payload payload){
        logger.info("Logout user id [{}]",payload.getUserId());
        String data = userService.logout(payload.getUserId());
        return factory.success(data,String.class);
    }

    @DeleteMapping(value = "admin/{user_id}")
    public ResponseEntity deleteUser(@PathVariable(name = "user_id") Integer userId){
        logger.info("Delete user [{}]",userId);
        String data = userService.deleteUserById(userId);
        return factory.success(data,String.class);
    }

    @GetMapping(value = "admin/{user_id}")
    public ResponseEntity getUserById(@PathVariable(name = "user_id") Integer userId){
        logger.info("Get infor user [{}]",userId);
        UserEntity data =  userService.getUserById(userId);
        return factory.success(data,UserEntity.class);
    }



}
