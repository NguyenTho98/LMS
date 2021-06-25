package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.request.NewQuizzQuestionRequest;
import vn.plusplus.lms.controller.request.UpdateQuizzQuestionRequest;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.repository.*;
import vn.plusplus.lms.repository.entities.*;
import vn.plusplus.lms.config.Constant;

import java.sql.Timestamp;
import java.util.List;

@Service
public class QuizzQuestionService {
    private static final Logger logger = LoggerFactory.getLogger(QuizzQuestionService.class);
    @Autowired
    QuizzQuestionRepository quizzQuestionRepository;
    @Autowired
    QuizzRepository quizzRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    UserCourseRepository userCourseRepository;

    public QuizzQuestionEntity addNewQuizzQuestion(NewQuizzQuestionRequest request) {
        QuizzQuestionEntity entity = new QuizzQuestionEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        entity.setQuestion(request.getQuestion());
        entity.setOptionA(request.getOptionA());
        entity.setOptionB(request.getOptionB());
        entity.setOptionC(request.getOptionC());
        entity.setOptionD(request.getOptionD());
        entity.setAnswer(request.getAnswer());
        entity.setType(request.getType());
        entity.setOrderInQuizz(request.getOrderInQuizz());
        entity.setQuizzId(request.getQuizzId());
        entity.setCreatedTime(timestamp);
        entity.setUpdatedTime(timestamp);
        entity.setStatus(Constant.Status.DRAFF);

        return quizzQuestionRepository.save(entity);
    }

    public QuizzQuestionEntity updateQuizzQuestion(Integer id, UpdateQuizzQuestionRequest request) {
        QuizzQuestionEntity entity = quizzQuestionRepository.findOneById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (entity == null) {
            entity = new QuizzQuestionEntity();
            entity.setCreatedTime(timestamp);
        }
        if (request.getQuestion() != null) {
            entity.setQuestion(request.getQuestion());
        }
        if (request.getOptionA() != null) {
            entity.setOptionA(request.getOptionA());
        }
        if (request.getOptionB() != null) {
            entity.setOptionB(request.getOptionB());
        }
        if (request.getOptionC() != null) {
            entity.setOptionC(request.getOptionC());
        }
        if (entity.getOptionD() != null) {
            entity.setOptionD(request.getOptionD());
        }
        if (request.getAnswer() != null) {
            entity.setAnswer(request.getAnswer());
        }
        if (request.getType() != null) {
            entity.setType(request.getType());
        }
        if (request.getOrderInQuizz() != null) {
            entity.setOrderInQuizz(request.getOrderInQuizz());
        }
        if (request.getQuizzId() != null) {
            entity.setQuizzId(request.getQuizzId());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }

        entity.setUpdatedTime(timestamp);

        return quizzQuestionRepository.save(entity);
    }

    public String deleteQuizzQuestion(Integer id) {
        quizzQuestionRepository.deleteById(id);
        return "Deleted Quizz Question";
    }

    public QuizzQuestionEntity getQuizzQuestionById(Integer quizzQuestionId,Integer userId) {
        Integer courseId = quizzQuestionRepository.findCourseIdByQuizzQuestionId(quizzQuestionId);
        if(courseId == null){
            logger.info("Quizz Question Id [{}] not belong any course");
            throw new AppException(ErrorCode.QUIZZQUESTION_NOT_IN_COURSE);
        }
        UserCourseEntity userCourseEntity = userCourseRepository.findOneByUserIdAndCourseIdAndStatus(userId,courseId,Constant.Status.ACTIVE);
        if(userCourseEntity == null){
            logger.info("User Id [{}] isn't allowed to view this quizz question",userId);
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        QuizzQuestionEntity entity = quizzQuestionRepository.findOneByIdAndStatus(quizzQuestionId, Constant.Status.ACTIVE);
        return entity;
    }

    public List<QuizzQuestionEntity> getQuizzQuestionsByQuizzId(Integer quizzId,Integer userId) {
        Integer courseId = quizzRepository.findCourseIdByQuizzId(quizzId);
        if(courseId == null){
            logger.info("Quizz Id [{}] not belong any course",quizzId);
            throw new AppException(ErrorCode.QUIZZ_NOT_IN_COURSE);
        }
        UserCourseEntity userCourseEntity = userCourseRepository.findOneByUserIdAndCourseIdAndStatus(userId,courseId,Constant.Status.ACTIVE);
        if(userCourseEntity == null){
            logger.info("User Id [{}] isn't allowed to view this quizz question");
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        return quizzQuestionRepository.findAllByQuizzIdAndStatus(quizzId, Constant.Status.ACTIVE);
    }

    public List<QuizzQuestionEntity> findQuizzQuestionByQuizzId(Integer quizzId){
        List<QuizzQuestionEntity> entity =
            quizzQuestionRepository.findQuizzQuestionByQuizzId(quizzId);
        return entity;
    }
}
