package vn.plusplus.lms.services.interfaces;

import vn.plusplus.lms.model.*;
import vn.plusplus.lms.repository.entities.LessonEntity;
import vn.plusplus.lms.repository.entities.MaterialEntity;
import vn.plusplus.lms.repository.entities.QuizzEntity;

import java.util.List;

public interface UserHomeInterface {
    //1.API Get list lesson
    List<LessonDTO> getListLessonByCourseId(Integer courseId, Integer userId);

    //2.API get information of lesson
    GeneralModel getGeneralModelByIdLesson(Integer lessonId, Integer userId);

    //3. API get quiz detail by quiz id
    List<QuizzQuestionDTO> getQuizzQuestionByQuizzId(Integer quizId, Integer userId);

    //4. API get material detail by material id
    List<SlideDTO> getSlideByMaterialId(Integer materialId, Integer userId);
}
