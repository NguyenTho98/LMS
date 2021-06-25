package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.request.NewSessionRequest;
import vn.plusplus.lms.controller.request.UpdateSessionRequest;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.repository.LessonRepository;
import vn.plusplus.lms.repository.SessionRepository;
import vn.plusplus.lms.repository.UserCourseRepository;
import vn.plusplus.lms.repository.entities.LessonEntity;
import vn.plusplus.lms.repository.entities.SessionEntity;
import vn.plusplus.lms.repository.entities.UserCourseEntity;
import vn.plusplus.lms.config.Constant;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SessionService {
    private static final Logger logger = LoggerFactory.getLogger(SessionService.class);
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    UserCourseRepository userCourseRepository;

    public SessionEntity addNewSession(NewSessionRequest request) {
        SessionEntity entity = new SessionEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        entity.setSessionName(request.getSessionName());
        entity.setSessionType(request.getSessionType());
        entity.setOrderInLesson(request.getOrderInLesson());
        entity.setLessonId(request.getLessionId());
        entity.setCreatedTime(timestamp);
        entity.setUpdatedTime(timestamp);
        entity.setStatus(Constant.Status.DRAFF);

        return sessionRepository.save(entity);
    }

    public SessionEntity updateSession(Integer sessionId, UpdateSessionRequest request) {
        SessionEntity entity = sessionRepository.findOneById(sessionId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (entity == null) {
            entity = new SessionEntity();
            entity.setCreatedTime(timestamp);
        }
        if (request.getSessionName() != null) {
            entity.setSessionName(request.getSessionName());
        }
        if (request.getSessionType() != null) {
            entity.setSessionType(request.getSessionType());
        }
        if (request.getOrderInLesson() != null) {
            entity.setOrderInLesson(request.getOrderInLesson());
        }
        if (request.getLessionId() != null) {
            entity.setLessonId(request.getLessionId());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }

        entity.setUpdatedTime(timestamp);

        return sessionRepository.save(entity);
    }

    public String deleteSession(Integer sessionId) {
        sessionRepository.deleteById(sessionId);
        return "Deleted Session";
    }

    public SessionEntity getSessionById(Integer sessionId,Integer userId) {
        Integer courseId = sessionRepository.findCourseIdBySessionId(sessionId);
        if(courseId ==null){
            logger.info("Session Id [{}] not belong any course",sessionId);
            throw new AppException(ErrorCode.SESSION_NOT_IN_COURSE);
        }
        UserCourseEntity userCourseEntity = userCourseRepository.findOneByUserIdAndCourseIdAndStatus(userId,courseId,Constant.Status.ACTIVE);
        if(userCourseEntity == null){
            logger.info("User Id [{}] isn't allowed to view this session",userId);
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        SessionEntity entity = sessionRepository.findOneByIdAndStatus(sessionId, Constant.Status.ACTIVE);
        return entity;
    }

    public List<SessionEntity> findSessionByLessonId(Integer lessonId){
        List<SessionEntity> entity = sessionRepository.findSessionByLessonId(lessonId);
        return entity;
    }
}
