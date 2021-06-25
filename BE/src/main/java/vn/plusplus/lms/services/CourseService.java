package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.request.NewCourseRequest;
import vn.plusplus.lms.controller.request.UpdateCourseRequest;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.repository.CourseRepository;
import vn.plusplus.lms.repository.UserCourseRepository;
import vn.plusplus.lms.repository.entities.CourseEntity;
import vn.plusplus.lms.repository.entities.UserCourseEntity;
import vn.plusplus.lms.config.Constant;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserCourseRepository userCourseRepository;

    public CourseEntity addNewCourse(NewCourseRequest request) {
        CourseEntity newCourse = new CourseEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        newCourse.setCourseName(request.getCourseName());
        newCourse.setCourseAvatar(request.getCourseAvatar());
        newCourse.setCourseDescription(request.getCourseDescription());
        newCourse.setJourney(request.getJourney());
        newCourse.setLanguage(request.getLanguage());
        newCourse.setLessonQuantity(request.getLessonQuantity());
        newCourse.setOrderInJourney(request.getOrderInJourney());
        newCourse.setCreatedTime(timestamp);
        newCourse.setUpdatedTime(timestamp);
        newCourse.setStatus(Constant.Status.DRAFF);

        return courseRepository.save(newCourse);
    }

    public CourseEntity updateCourse(Integer courseId, UpdateCourseRequest request) {
        CourseEntity rs = courseRepository.findOneById(courseId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (rs == null) {
            rs = new CourseEntity();
            rs.setCreatedTime(timestamp);
        }
        if (request.getCourseName() != null) {
            rs.setCourseName(request.getCourseName());
        }
        if (request.getCourseAvatar() != null) {
            rs.setCourseAvatar(request.getCourseAvatar());
        }
        if (request.getCourseDescription() != null) {
            rs.setCourseDescription(request.getCourseDescription());
        }
        if (request.getJourney() != null) {
            rs.setJourney(request.getJourney());
        }
        if (request.getLanguage() != null) {
            rs.setLanguage(request.getLanguage());
        }
        if (request.getLessonQuantity() != null) {
            rs.setLessonQuantity(request.getLessonQuantity());
        }
        if (request.getOrderInJourney() != null) {
            rs.setOrderInJourney(request.getOrderInJourney());
        }
        if (request.getStatus() != null) {
            rs.setStatus(request.getStatus());
        }
        rs.setUpdatedTime(timestamp);


        return courseRepository.save(rs);
    }
    public CourseEntity getCourseById(Integer courseId,Integer userId){
        UserCourseEntity userCourseEntity = userCourseRepository.findOneByUserIdAndCourseIdAndStatus(userId,courseId,Constant.Status.ACTIVE);
        if(userCourseEntity == null){
            logger.info("User Id [{}] isn't allowed to view this course",userId);
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        CourseEntity entity = courseRepository.findOneByIdAndStatus(courseId, Constant.Status.ACTIVE);
        return entity;
    }

    public String deleteCourse(Integer courseId){
        courseRepository.deleteById(courseId);
        return "Deleted course";
    }

}
