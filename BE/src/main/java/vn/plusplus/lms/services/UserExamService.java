package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.config.Constant;
import vn.plusplus.lms.controller.request.NewUserExamRequest;
import vn.plusplus.lms.controller.request.UpdateUserExamRequest;
import vn.plusplus.lms.repository.UserExamRepository;
import vn.plusplus.lms.repository.entities.UserExamEntity;

import java.sql.Timestamp;

@Service
public class UserExamService {
    private static final Logger logger = LoggerFactory.getLogger(UserExamService.class);

    @Autowired
    UserExamRepository userExamRepository;

    public UserExamEntity addNewUserExam(NewUserExamRequest request) {
        UserExamEntity entity = new UserExamEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        entity.setUserId(request.getUserId());
        entity.setExamId(request.getExamId());
        entity.setScore(request.getScore());
        entity.setStatus(Constant.Status.DRAFF);
        entity.setComment(request.getComment());
        entity.setCreatedTime(timestamp);
        entity.setUpdatedTime(timestamp);


        return userExamRepository.save(entity);
    }

    public UserExamEntity updateUserExam(Integer id, UpdateUserExamRequest request) {
        UserExamEntity entity = userExamRepository.findOneById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (entity == null) {
            entity = new UserExamEntity();
            entity.setCreatedTime(timestamp);
        }
        if (request.getUserId() != null) {
            entity.setUserId(request.getUserId());
        }
        if (request.getExamId() != null) {
            entity.setExamId(request.getExamId());
        }
        if (request.getScore() != null) {
            entity.setScore(request.getScore());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
        if (request.getComment() != null) {
            entity.setComment(request.getComment());
        }
        entity.setUpdatedTime(timestamp);

        return userExamRepository.save(entity);
    }

    public String deleteUserExam(Integer id){
        userExamRepository.deleteById(id);
        return "Deleted user exam";
    }
    public UserExamEntity getUserExamById(Integer id,Integer userId){
        return userExamRepository.findOneByIdAndUserId(id,userId);
    }
}
