package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.request.NewExamQuestionRequest;
import vn.plusplus.lms.controller.request.UpdateExamQuestionRequest;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.repository.ExamQuestionRepository;
import vn.plusplus.lms.repository.ExamRepository;
import vn.plusplus.lms.repository.UserCourseRepository;
import vn.plusplus.lms.repository.entities.ExamEntity;
import vn.plusplus.lms.repository.entities.ExamQuestionEntity;
import vn.plusplus.lms.repository.entities.UserCourseEntity;
import vn.plusplus.lms.config.Constant;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ExamQuestionService {
    private static final Logger logger = LoggerFactory.getLogger(ExamQuestionService.class);

    @Autowired
    ExamQuestionRepository examQuestionRepository;
    @Autowired
    UserCourseRepository userCourseRepository;

    public ExamQuestionEntity addNewExamQuestion(NewExamQuestionRequest request) {
        ExamQuestionEntity entity = new ExamQuestionEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        entity.setQuestion(request.getQuestion());
        entity.setOptionA(request.getOptionA());
        entity.setOptionB(request.getOptionB());
        entity.setOptionC(request.getOptionC());
        entity.setOptionD(request.getOptionD());
        entity.setAnswer(request.getAnswer());
        entity.setType(request.getType());
        entity.setOrderInExam(request.getOrderInExam());
        entity.setExamId(request.getExamId());
        entity.setCreatedTime(timestamp);
        entity.setUpdatedTime(timestamp);
        entity.setStatus(Constant.Status.DRAFF);

        return examQuestionRepository.save(entity);
    }

    public ExamQuestionEntity updateExamQuestion(Integer examQuestionId, UpdateExamQuestionRequest request) {
        ExamQuestionEntity entity = examQuestionRepository.findOneById(examQuestionId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (entity == null) {
            entity = new ExamQuestionEntity();
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
        if (request.getOptionD() != null) {
            entity.setOptionD(request.getOptionD());
        }
        if (request.getAnswer() != null) {
            entity.setAnswer(request.getAnswer());
        }
        if (request.getType() != null) {
            entity.setType(request.getType());
        }
        if (request.getOrderInExam() != null) {
            entity.setOrderInExam(request.getOrderInExam());
        }
        if (request.getExamId() != null) {
            entity.setExamId(request.getExamId());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
        entity.setUpdatedTime(timestamp);


        return examQuestionRepository.save(entity);
    }

    public String deleteExamQuestion(Integer examQuestionId) {
        examQuestionRepository.deleteById(examQuestionId);
        return "Deleted ExamQuestion";
    }

    public ExamQuestionEntity getExamQuestionById(Integer examQuestionId, Integer userId) {
        Integer courseId = examQuestionRepository.findCourseIdByExamQuestionId(examQuestionId);
        if (courseId == null) {
            logger.info("Exam Question Id [{}] not belong any course", examQuestionId);
            throw new AppException(ErrorCode.EXAMQUESTION_NOT_IN_COURSE);
        }
        UserCourseEntity userCourseEntity = userCourseRepository.findOneByUserIdAndCourseIdAndStatus(userId, courseId, Constant.Status.ACTIVE);
        if (userCourseEntity == null) {
            logger.info("User Id [{}] isn't allowed to view this exam question", userId);
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        ExamQuestionEntity entity = examQuestionRepository.findOneByIdAndStatus(examQuestionId, Constant.Status.ACTIVE);
        return entity;
    }
}
