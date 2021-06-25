package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.request.NewAssignmentRequest;
import vn.plusplus.lms.controller.request.UpdateAssignmentRequest;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.repository.AssignmentRepository;
import vn.plusplus.lms.repository.LessonRepository;
import vn.plusplus.lms.repository.UserCourseRepository;
import vn.plusplus.lms.repository.entities.AssignmentEntity;
import vn.plusplus.lms.repository.entities.LessonEntity;
import vn.plusplus.lms.repository.entities.UserCourseEntity;
import vn.plusplus.lms.config.Constant;


import java.sql.Timestamp;
import java.util.List;


@Service
public class AssignmentService {
    private static final Logger logger = LoggerFactory.getLogger(AssignmentService.class);
    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    UserCourseRepository userCourseRepository;

    public AssignmentEntity addNewAssignment(NewAssignmentRequest request) {
        AssignmentEntity entity = new AssignmentEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        entity.setAssignmentTitle(request.getAssignmentTitle());
        entity.setAssignmentType(request.getAssignmentType());
        entity.setAssignmentDescription(request.getAssignmentDescription());
        entity.setOrderInLesson(request.getOrderInLesson());
        entity.setLessonId(request.getLessonId());
        entity.setCreatedTime(timestamp);
        entity.setUpdatedTime(timestamp);
        entity.setStatus(Constant.Status.DRAFF);

        return assignmentRepository.save(entity);
    }

    public AssignmentEntity updateAssignment(Integer assignmentId, UpdateAssignmentRequest request) {
        AssignmentEntity rs = assignmentRepository.findOneById(assignmentId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (rs == null) {
            rs = new AssignmentEntity();
            rs.setCreatedTime(timestamp);
        }
        if (request.getAssignmentTitle() != null) {
            rs.setAssignmentTitle(request.getAssignmentTitle());
        }
        if (request.getAssignmentType() != null) {
            rs.setAssignmentType(request.getAssignmentType());
        }
        if (request.getAssignmentDescription() != null) {
            rs.setAssignmentDescription(request.getAssignmentDescription());
        }
        if (request.getOrderInLesson() != null) {
            rs.setOrderInLesson(request.getOrderInLesson());
        }
        if (request.getLessonId() != null) {
            rs.setLessonId(request.getLessonId());
        }
        if (request.getStatus() != null) {
            rs.setStatus(request.getStatus());
        }

        rs.setUpdatedTime(timestamp);


        return assignmentRepository.save(rs);
    }
    public String deleteAssignment(Integer assignmentId){
        assignmentRepository.deleteById(assignmentId);
        return "Deleted Assignment";
    }
    public AssignmentEntity getAssignmentById(Integer assignmentId,Integer userId){
        Integer courseId = assignmentRepository.findCourseIdByAssignmentId(assignmentId);
        if( courseId == null){
            logger.info("Assignment Id [{}] not belong any course",assignmentId);
            throw new AppException(ErrorCode.ASSIGNMENT_NOT_IN_COURSE);
        }
        UserCourseEntity userCourseEntity = userCourseRepository.findOneByUserIdAndCourseIdAndStatus(userId,courseId,Constant.Status.ACTIVE);
        if(userCourseEntity == null){
            logger.info("User Id [{}] isn't allowed to view this assignment");
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        AssignmentEntity entity = assignmentRepository.findOneByIdAndStatus(assignmentId, Constant.Status.ACTIVE);
        return entity;
    }

    public List<AssignmentEntity> findAssignmentByLessonId(Integer lessonId){
        List<AssignmentEntity> entity = assignmentRepository.findAssignmentByLessonId(lessonId);
        return entity;
    }
}
