package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.config.Constant;
import vn.plusplus.lms.controller.request.NewUserExamAnswerRequest;
import vn.plusplus.lms.controller.request.UpdateUserExamAnswerRequest;
import vn.plusplus.lms.controller.request.UpdateUserExamRequest;
import vn.plusplus.lms.repository.UserExamAnswerRepository;
import vn.plusplus.lms.repository.entities.UserExamAnswerEntity;

import java.sql.Timestamp;

@Service
public class UserExamAnswerService {
    private final static Logger logger = LoggerFactory.getLogger(UserExamAnswerService.class);

    @Autowired
    UserExamAnswerRepository repo;

    public UserExamAnswerEntity addNewUserExamAnswer(NewUserExamAnswerRequest request) {
        UserExamAnswerEntity entity = new UserExamAnswerEntity();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        entity.setUserId(request.getUserId());
        entity.setExamQuestion(request.getExamQuestion());
        entity.setAnswer(request.getAnswer());
        entity.setCreatedTime(timestamp);
        entity.setUpdatedTime(timestamp);
        entity.setStatus(Constant.Status.DRAFF);


        return repo.save(entity);
    }

    public UserExamAnswerEntity updateUserExamAnswer(Integer id, UpdateUserExamAnswerRequest request) {
        UserExamAnswerEntity entity = repo.findOneById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (entity == null) {
            entity = new UserExamAnswerEntity();
            entity.setCreatedTime(timestamp);
        }
        if (request.getUserId() != null) {
            entity.setUserId(request.getUserId());
        }
        if (request.getExamQuestion() != null) {
            entity.setExamQuestion(request.getExamQuestion());
        }
        if (request.getAnswer() != null) {
            entity.setAnswer(request.getAnswer());
        }


        entity.setUpdatedTime(timestamp);
        return repo.save(entity);
    }

    public String deleteUserExamAnswer(Integer id){
        repo.deleteById(id);
        return "Deleted User Exam Answer";
    }

    public UserExamAnswerEntity getUserExamAnswerById(Integer id,Integer userId){
        return repo.findOneByIdAndUserId(id,userId);
    }
}
