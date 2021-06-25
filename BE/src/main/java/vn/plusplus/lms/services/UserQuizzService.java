package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.config.Constant;
import vn.plusplus.lms.controller.request.NewUserCourseRequest;
import vn.plusplus.lms.controller.request.NewUserQuizzRequest;
import vn.plusplus.lms.controller.request.UpdateUserQuizzRequest;
import vn.plusplus.lms.repository.UserQuizzRepository;
import vn.plusplus.lms.repository.entities.UserQuizzEntity;

import java.sql.Timestamp;

@Service
public class UserQuizzService {
    private static final Logger logger = LoggerFactory.getLogger(UserQuizzService.class);

    @Autowired
    UserQuizzRepository userQuizzRepository;

    public UserQuizzEntity addNewUserQuizz(NewUserQuizzRequest request) {
        UserQuizzEntity entity = new UserQuizzEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        entity.setUserId(request.getUserId());
        entity.setQuizzId(request.getQuizzId());
        entity.setComment(request.getComment());
        entity.setScore(request.getScore());
        entity.setStatus(Constant.Status.DRAFF);
        entity.setCreatedTime(timestamp);
        entity.setUpdatedTime(timestamp);


        return userQuizzRepository.save(entity);
    }

    public UserQuizzEntity updateUserQuizz(Integer id, UpdateUserQuizzRequest request) {
        UserQuizzEntity entity = userQuizzRepository.findOneById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if (entity == null) {
            entity = new UserQuizzEntity();
            entity.setCreatedTime(timestamp);
        }
        if (request.getUserId() != null) {
            entity.setUserId(request.getUserId());
        }
        if (request.getQuizzId() != null) {
            entity.setQuizzId(request.getQuizzId());
        }
        if (request.getComment() != null) {
            entity.setComment(request.getComment());
        }
        if (request.getScore() != null) {
            entity.setScore(request.getScore());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }

        entity.setUpdatedTime(timestamp);
        return  userQuizzRepository.save(entity);
    }
    public String deleteUserQuizz(Integer id){
        userQuizzRepository.deleteById(id);
        return "Deleted User Quizz";
    }
    public UserQuizzEntity getUserQuizzById(Integer id,Integer userId){
        return userQuizzRepository.findOneByIdAndUserIdAndStatus(id,userId,Constant.Status.ACTIVE);
    }
}
