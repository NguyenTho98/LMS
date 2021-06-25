package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.converter.Converter;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.model.*;
import vn.plusplus.lms.repository.*;
import vn.plusplus.lms.repository.entities.*;
import vn.plusplus.lms.config.Constant;
import vn.plusplus.lms.services.interfaces.UserHomeInterface;


import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralService implements UserHomeInterface {
    private static final Logger logger = LoggerFactory.getLogger(GeneralService.class);

    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    QuizzRepository quizzRepository;
    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    UserCourseRepository userCourseRepository;

    @Autowired
    QuizzQuestionService quizzQuestionService;

    @Autowired
    SlideService slideService;

    @Override
    public List<LessonDTO> getListLessonByCourseId(Integer courseId,Integer userId) {
        List<LessonEntity> listLesson = lessonRepository.findLessonByCourseId(courseId);

        return Converter.toList(listLesson,LessonDTO.class);
    }

    @Override
    public GeneralModel getGeneralModelByIdLesson(Integer lessonId, Integer userId){
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

        List<SessionEntity> listSession = sessionRepository.findAllByLessonIdAndStatus(lessonId,Constant.Status.ACTIVE);
        List<AssignmentEntity> listAssignments = assignmentRepository.findAllByLessonIdAndStatus(lessonId,Constant.Status.ACTIVE);

        List<ListSessionModel> listSessionModels = new ArrayList<>();
        for (SessionEntity entity: listSession) {
            List<QuizzEntity> listQuizz = quizzRepository.findAllBySessionIdAndStatus(entity.getId(),Constant.Status.ACTIVE);
            List<MaterialEntity> listMaterial = materialRepository.findAllBySessionIdAndStatus(entity.getId(),Constant.Status.ACTIVE);

            List<QuizzDTO> quizzDTOList = Converter.toList(listQuizz,QuizzDTO.class);
            List<MaterialDTO> materialDTOList = Converter.toList(listMaterial,MaterialDTO.class);

            ListSessionModel rs =  new ListSessionModel(entity.getId(),quizzDTOList,materialDTOList);
            listSessionModels.add(rs);
        }
        List<AssignmentDTO> assignmentDTOList = Converter.toList(listAssignments,AssignmentDTO.class);
        List<SessionDTO> sessionDTOList = Converter.toList(listSession,SessionDTO.class);

        GeneralModel generalModel = new GeneralModel(assignmentDTOList,sessionDTOList,listSessionModels);

        return generalModel;
    }


    @Override
    public List<QuizzQuestionDTO> getQuizzQuestionByQuizzId(Integer quizId, Integer userId) {
        List<QuizzQuestionEntity> quizzQuestionEntityList = quizzQuestionService.getQuizzQuestionsByQuizzId(quizId, userId);
        List<QuizzQuestionDTO> data = Converter.toList(quizzQuestionEntityList,QuizzQuestionDTO.class);
        return data;
    }

    @Override
    public List<SlideDTO> getSlideByMaterialId(Integer materialId, Integer userId) {
        List<SlideEntity> slideEntityList = slideService.getSlideByMaterialId(materialId,userId);
        List<SlideDTO> data = Converter.toList(slideEntityList,SlideDTO.class);
        return data;
    }

}
