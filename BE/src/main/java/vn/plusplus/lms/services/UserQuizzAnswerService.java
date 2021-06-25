package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.config.Constant;
import vn.plusplus.lms.controller.request.NewUserQuizzAnswerRequest;
import vn.plusplus.lms.controller.request.UpdateUserQuizzAnswerRequest;
import vn.plusplus.lms.repository.UserQuizzAnswerRepository;
import vn.plusplus.lms.repository.entities.UserQuizzAnswerEntity;

import java.sql.Timestamp;

@Service
public class UserQuizzAnswerService {
    private static final Logger logger = LoggerFactory.getLogger(UserQuizzAnswerService.class);

    @Autowired
    UserQuizzAnswerRepository userQuizzAnswerRepository;

    public UserQuizzAnswerEntity addNewUserQuizzAnswer(NewUserQuizzAnswerRequest request) {
        UserQuizzAnswerEntity entity = new UserQuizzAnswerEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        entity.setUserId(request.getUserId());
        entity.setQuizzQuestion(request.getQuizzQuestion());
        entity.setAnswer(request.getAnswer());
        entity.setCreatedTime(timestamp);
        entity.setUpdatedTime(timestamp);
        entity.setStatus(Constant.Status.DRAFF);

        return userQuizzAnswerRepository.save(entity);
    }

    public UserQuizzAnswerEntity updateUserQuizzAnswer(Integer id, UpdateUserQuizzAnswerRequest request) {
        UserQuizzAnswerEntity entity = userQuizzAnswerRepository.findOneById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if (entity == null) {
            entity = new UserQuizzAnswerEntity();
            entity.setCreatedTime(timestamp);
        }
        if (request.getUserId() != null) {
            entity.setUserId(request.getUserId());
        }
        if (request.getQuizzQuestion() != null) {
            entity.setQuizzQuestion(request.getQuizzQuestion());
        }
        if (request.getAnswer() != null) {
            entity.setAnswer(request.getAnswer());
        }


        entity.setUpdatedTime(timestamp);
        return userQuizzAnswerRepository.save(entity);
    }

    public String deleteUserQuizzAnswer (Integer id){
        userQuizzAnswerRepository.deleteById(id);
        return "Deleted User Quizz Answer";
    }

    public UserQuizzAnswerEntity getUserQuizzAnswerById(Integer id,Integer userId){
        return userQuizzAnswerRepository.findOneByIdAndUserIdAndStatus(id,userId,Constant.Status.ACTIVE);
    }

}
