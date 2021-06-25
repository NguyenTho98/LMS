package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.config.Constant;
import vn.plusplus.lms.controller.request.NewUserCourseRequest;
import vn.plusplus.lms.controller.request.UpdateUserCourseRequest;
import vn.plusplus.lms.repository.UserCourseRepository;
import vn.plusplus.lms.repository.entities.UserCourseEntity;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserCourseService {
    private static final Logger logger = LoggerFactory.getLogger(UserCourseService.class);

    @Autowired
    UserCourseRepository userCourseRepository;

    public UserCourseEntity addNewUserCourse(NewUserCourseRequest request) {
        UserCourseEntity entity = new UserCourseEntity();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        entity.setUserId(request.getUserId());
        entity.setCourseId(request.getCourseId());
        entity.setCreatedTime(timestamp);
        entity.setUpdatedTime(timestamp);
        entity.setStatus(Constant.Status.DRAFF);

        return userCourseRepository.save(entity);
    }

    public UserCourseEntity updateUserCourse(Integer id, UpdateUserCourseRequest request) {
        UserCourseEntity entity = userCourseRepository.findOneById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (entity == null) {
            entity = new UserCourseEntity();
            entity.setCreatedTime(timestamp);
        }
        if (request.getUserId() != null) {
            entity.setUserId(request.getUserId());
        }
        if (request.getCourseId() != null) {
            entity.setCourseId(request.getCourseId());
        }
        entity.setUpdatedTime(timestamp);

        return userCourseRepository.save(entity);
    }
    public String deleteUserCourse(Integer id){
        userCourseRepository.deleteById(id);
        return "Deleted user_course";
    }
    public UserCourseEntity getUserCourseById(Integer id){
        return userCourseRepository.findOneByIdAndStatus(id,Constant.Status.ACTIVE);
    }

    public List<UserCourseEntity> getAllUserCourseByUserId(Integer userId){
        return userCourseRepository.findAllByUserId(userId);
    }
}
