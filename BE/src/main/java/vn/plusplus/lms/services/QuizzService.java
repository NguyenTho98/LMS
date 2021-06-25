package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.request.NewQuizzRequest;
import vn.plusplus.lms.controller.request.UpdateQuizzRequest;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.repository.LessonRepository;
import vn.plusplus.lms.repository.QuizzRepository;
import vn.plusplus.lms.repository.SessionRepository;
import vn.plusplus.lms.repository.UserCourseRepository;
import vn.plusplus.lms.repository.entities.LessonEntity;
import vn.plusplus.lms.repository.entities.QuizzEntity;
import vn.plusplus.lms.repository.entities.SessionEntity;
import vn.plusplus.lms.repository.entities.UserCourseEntity;
import vn.plusplus.lms.config.Constant;

import java.sql.Timestamp;
import java.util.List;

@Service
public class QuizzService {
    private static final Logger logger = LoggerFactory.getLogger(QuizzService.class);

    @Autowired
    QuizzRepository quizzRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    UserCourseRepository userCourseRepository;


    public QuizzEntity addNewQuizz(NewQuizzRequest request) {
        QuizzEntity entity = new QuizzEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        entity.setQuizzName(request.getQuizzName());
        entity.setQuizzType(request.getQuizzType());
        entity.setOrderInSession(request.getOrderInSession());
        entity.setSessionId(request.getSessionId());
        entity.setCreatedTime(timestamp);
        entity.setUpdatedTime(timestamp);
        entity.setStatus(Constant.Status.DRAFF);

        return quizzRepository.save(entity);
    }

    public QuizzEntity updateQuizz(Integer quizzId, UpdateQuizzRequest request) {
        QuizzEntity entity = quizzRepository.findOneById(quizzId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if (entity == null) {
            entity = new QuizzEntity();
            entity.setCreatedTime(timestamp);
        }
        if (request.getQuizzName() != null) {
            entity.setQuizzName(request.getQuizzName());
        }
        if (request.getQuizzType() != null) {
            entity.setQuizzType(request.getQuizzType());
        }
        if (request.getOrderInSession() != null) {
            entity.setOrderInSession(request.getOrderInSession());
        }
        if (request.getSessionId() != null) {
            entity.setSessionId(request.getSessionId());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }

        entity.setUpdatedTime(timestamp);

        return quizzRepository.save(entity);
    }

    public String deleteQuizz(Integer quizzId) {
        quizzRepository.deleteById(quizzId);
        return "Deleted Quizz";
    }

    public QuizzEntity getQuizzById(Integer quizzId, Integer userId) {
        Integer courseId = quizzRepository.findCourseIdByQuizzId(quizzId);
        if (courseId == null) {
            logger.info("Quizz Id [{}] not belong any course", quizzId);
            throw new AppException(ErrorCode.QUIZZ_NOT_IN_COURSE);
        }
        UserCourseEntity userCourseEntity = userCourseRepository.findOneByUserIdAndCourseIdAndStatus(userId, courseId, Constant.Status.ACTIVE);
        if (userCourseEntity == null) {
            logger.info("User Id [{}] isn't allowed to view this quizz question");
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        QuizzEntity entity = quizzRepository.findOneByIdAndStatus(quizzId, Constant.Status.ACTIVE);
        return entity;
    }

    public List<QuizzEntity> findQuizzBySessionId(Integer sessionId){
        List<QuizzEntity> entity = quizzRepository.findQuizzBySessionId(sessionId);
        return entity;
    }
}
