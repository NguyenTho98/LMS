package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.config.Constant;
import vn.plusplus.lms.controller.request.LoginUserRequest;
import vn.plusplus.lms.controller.request.NewUserRequest;
import vn.plusplus.lms.converter.Converter;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.model.CourseDTO;
import vn.plusplus.lms.model.UserDTO;
import vn.plusplus.lms.repository.CourseRepository;
import vn.plusplus.lms.repository.TokenRepository;
import vn.plusplus.lms.repository.UserRepository;
import vn.plusplus.lms.repository.UserRoleRepository;
import vn.plusplus.lms.repository.entities.CourseEntity;
import vn.plusplus.lms.repository.entities.TokenEntity;
import vn.plusplus.lms.repository.entities.UserEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    CourseRepository courseRepository;

    public UserEntity registerUser(NewUserRequest request){
        UserEntity entity = new UserEntity();

        entity.setUserName(request.getUserName());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(request.getPassword());
        entity.setPassword(password);
        entity.setStatus(Constant.Status.ACTIVE);


        return userRepository.save(entity);
    }

    public String loginUser(LoginUserRequest request){
        UserEntity entity = userRepository.findOneByUserName(request.getUserName());
        if (entity == null) {
            throw new RuntimeException("User is not existed");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Boolean check =encoder.matches(request.getPassword(),entity.getPassword());
        if (check == false) {
            throw new RuntimeException("Pass is not correct");
        }

        //TODO Dua gia tri nay ra file config
        long deltaTime = 60 * 1000*180;
        String token = UUID.randomUUID().toString();
        TokenEntity tokenEntity = tokenRepository.findOneByUserId(entity.getId());
        long now = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(now);
        if (tokenEntity == null) {
            tokenEntity = new TokenEntity();
            tokenEntity.setCreatedTime(timestamp);
        }
        tokenEntity.setToken(token);
        System.out.println(token);
        tokenEntity.setUpdatedTime(timestamp);
        tokenEntity.setExpiredTime(new Timestamp(now + deltaTime));
        tokenEntity.setUserId(entity.getId());

        tokenRepository.save(tokenEntity);
        // Tạo payload lưu vào authenCaching
        List<Integer> listApi = userRoleRepository.getListApiIdByUserId(entity.getId());
        Payload payload = new Payload();
        payload.setAccessToken(token);
        payload.setPermissions(listApi);
        payload.setUserId(entity.getId());
        payload.setExpiredTime(new Timestamp(now + deltaTime));

        tokenService.saveToCache(payload);


        return token;
    }

    public String logout(Integer userId){
        TokenEntity tokenEntity = tokenRepository.findOneByUserId(userId);
        tokenService.removeTokenFromCache(tokenEntity.getToken());
        tokenRepository.deleteById(tokenEntity.getId());
        return "Logout user id" + userId;
    }

    public String deleteUserById(Integer id){
        userRepository.deleteById(id);
        return "Delete user id ="+id;
    }

    public UserEntity getUserById(Integer id){
        return userRepository.findOneById(id);
    }

    public  UserDTO getUserInfo(Integer userId){
        UserEntity userEntity = userRepository.findOneById(userId);

        List<CourseEntity> listCourses = courseRepository.findAllById(userId);

        List<CourseDTO> data = Converter.toList(listCourses,CourseDTO.class);

        UserDTO userDTO = new UserDTO(userEntity.getUserName(),data);

        return userDTO;
    }

}
