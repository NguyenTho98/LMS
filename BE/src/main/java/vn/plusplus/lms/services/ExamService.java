package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.request.NewExamRequest;
import vn.plusplus.lms.controller.request.UpdateExamRequest;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.repository.ExamRepository;
import vn.plusplus.lms.repository.UserCourseRepository;
import vn.plusplus.lms.repository.entities.ExamEntity;
import vn.plusplus.lms.repository.entities.UserCourseEntity;
import vn.plusplus.lms.config.Constant;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ExamService {
    private static final Logger logger = LoggerFactory.getLogger(ExamService.class);

    @Autowired
    ExamRepository examRepository;
    @Autowired
    UserCourseRepository userCourseRepository;

    public ExamEntity addNewExam(NewExamRequest request) {
        ExamEntity entity = new ExamEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        entity.setExamName(request.getExamName());
        entity.setExamType(request.getExamType());
        entity.setOrderInCourse(request.getOrderInCourse());
        entity.setCourseId(request.getCourseId());
        entity.setCreatedTime(timestamp);
        entity.setUpdatedTime(timestamp);
        entity.setStatus("DRAFF");

        return examRepository.save(entity);
    }

    public ExamEntity updateExam(Integer examId, UpdateExamRequest request) {
        ExamEntity rs = examRepository.findOneById(examId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if (rs == null) {
            rs = new ExamEntity();
            rs.setCreatedTime(timestamp);
        }
        if (request.getExamName() != null) {
            rs.setExamName(request.getExamName());
        }
        if (request.getExamType() != null) {
            rs.setExamType(request.getExamType());
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

        return examRepository.save(rs);
    }

    public String deleteExam(Integer examId) {
        examRepository.deleteById(examId);
        return "Deleted Exam";
    }

    public ExamEntity getExamById(Integer examId,Integer userId) {
        Integer courseId = examRepository.findCourseIdByExamId(examId);
        if(courseId ==null){
            logger.info("Exam Id [{}] not belong any course",examId);
            throw new AppException(ErrorCode.EXAM_NOT_IN_COURSE);
        }
        UserCourseEntity userCourseEntity= userCourseRepository.findOneByUserIdAndCourseIdAndStatus(userId,courseId,Constant.Status.ACTIVE);
        if(userCourseEntity == null){
            logger.info("User Id [{}] isn't allowed to view this exam");
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        ExamEntity entity = examRepository.findOneByIdAndStatus(examId, Constant.Status.ACTIVE);
        return entity;
    }
}
