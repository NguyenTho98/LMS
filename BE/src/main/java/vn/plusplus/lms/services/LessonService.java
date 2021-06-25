package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.request.NewLessonRequest;
import vn.plusplus.lms.controller.request.UpdateLessonRequest;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.repository.LessonRepository;
import vn.plusplus.lms.repository.UserCourseRepository;
import vn.plusplus.lms.repository.entities.LessonEntity;
import vn.plusplus.lms.repository.entities.UserCourseEntity;
import vn.plusplus.lms.config.Constant;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LessonService {
    private static final Logger logger = LoggerFactory.getLogger(LessonService.class);

    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    UserCourseRepository userCourseRepository;

    public LessonEntity addNewLesson(NewLessonRequest request) {
        //TODO logic here
        LessonEntity newLesson = new LessonEntity();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        newLesson.setLessonName(request.getLessonName());
        newLesson.setLessonDescription(request.getLessonDescription());
        newLesson.setLessonType(request.getLessonType());
        newLesson.setLessonAvatar(request.getLessonAvatar());
        newLesson.setCourseId(request.getCourseId());
        newLesson.setOrderInCourse(request.getOrderInCourse());
        newLesson.setCreatedTime(currentTime);
        newLesson.setUpdatedTime(currentTime);
        newLesson.setStatus("DRAFF");

        return lessonRepository.save(newLesson);
    }

    public LessonEntity updateLesson(Integer lessonId, UpdateLessonRequest request) {
        //TODO logic here
        LessonEntity rs = lessonRepository.findOneById(lessonId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (rs == null) {
            rs = new LessonEntity();
            rs.setCreatedTime(timestamp);
        }

        if (request.getLessonName() != null) {
            rs.setLessonName(request.getLessonName());
        }
        if (request.getLessonAvatar() != null) {
            rs.setLessonAvatar(request.getLessonAvatar());
        }
        if (request.getLessonType() != null) {
            rs.setLessonType(request.getLessonType());
        }
        if (request.getLessonDescription() != null) {
            rs.setLessonDescription(request.getLessonDescription());
        }
        if (request.getOrderInCourse() != null) {
            rs.setOrderInCourse(request.getOrderInCourse());
        }
        if (request.getCourseId() != null) {
            rs.setCourseId(request.getCourseId());
        }
        if (request.getStatus() != null) {
            rs.setStatus(request.getStatus());
        }
        rs.setUpdatedTime(timestamp);


        return lessonRepository.save(rs);
    }

    public String deleteLesson(Integer lessonId) {
        //TODO logic here
        lessonRepository.deleteById(lessonId);
        return "Deleted lesson";
    }

    public LessonEntity getLessonById(Integer lessonId, Integer userId) {
        //TODO logic here
        Integer courseId = lessonRepository.findCourseIdByLessonId(lessonId);
        if (courseId == null) {
            logger.info("Lesson Id [{}] not belong any course", lessonId);
            throw new AppException(ErrorCode.LESSON_NOT_IN_COURSE);
        }
        UserCourseEntity userCourseEntity = userCourseRepository.findOneByUserIdAndCourseIdAndStatus(userId,courseId,Constant.Status.ACTIVE);
        if(userCourseEntity == null){
            logger.info("User Id [{}] isn't allowed to view this lesson",userId);
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        LessonEntity entity = lessonRepository.findOneByIdAndStatus(lessonId, Constant.Status.ACTIVE);
        return entity;
    }

    public List<LessonEntity> findLessonByCourseId(Integer courseId){
        List<LessonEntity> entity = lessonRepository.findLessonByCourseId(courseId);
        return entity;
    }
}
