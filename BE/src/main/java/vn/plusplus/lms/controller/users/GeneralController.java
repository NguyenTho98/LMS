package vn.plusplus.lms.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.model.*;
import vn.plusplus.lms.services.GeneralService;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/room")
public class GeneralController {
    private static final Logger logger = LoggerFactory.getLogger(GeneralController.class);

    @Autowired
    GeneralService service;
    @Autowired
    ResponseFactory factory;

    @GetMapping(value = "/lesson/{lesson_id}")
    public ResponseEntity getDataByLessonId(@PathVariable(name = "lesson_id") Integer lessonId,
                                            @RequestAttribute Payload payload) {
        logger.info("Get list data by lesson id [{}]", lessonId);
        GeneralModel data = service.getGeneralModelByIdLesson(lessonId, payload.getUserId());
        return factory.success(data);
    }

    @GetMapping(value = "/course/{course_id}")
    public ResponseEntity getListLessonByCourseId(@PathVariable(name = "course_id") Integer courseId,
                                                  @RequestAttribute Payload payload) {
        logger.info("Get list lesson by course id [{}]", courseId);
        List<LessonDTO> data = service.getListLessonByCourseId(courseId, payload.getUserId());
        return factory.success(data);
    }

    @GetMapping(value = "/quizz-question/{quizz_id}")
    public ResponseEntity getQuizzQuestionByQuizzId(@PathVariable(name = "quizz_id") Integer quizzId,
                                                    @RequestAttribute Payload payload) {
        logger.info("Get list quizz question by quizz id [{}]", quizzId);
        List<QuizzQuestionDTO> data = service.getQuizzQuestionByQuizzId(quizzId, payload.getUserId());
        return factory.success(data, List.class);
    }

    @GetMapping(value = "/slide/{material_id}")
    public ResponseEntity getSlideByMaterialId(@PathVariable(name = "material_id") Integer materialId,
                                               @RequestAttribute Payload payload) {
        logger.info("Get slide by material id [{}]", materialId);
        List<SlideDTO> data = service.getSlideByMaterialId(materialId, payload.getUserId());
        return factory.success(data,List.class);
    }


}

