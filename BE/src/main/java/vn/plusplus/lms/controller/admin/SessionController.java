package vn.plusplus.lms.controller.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewSessionRequest;
import vn.plusplus.lms.controller.request.UpdateSessionRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.entities.SessionEntity;
import vn.plusplus.lms.services.MaterialService;
import vn.plusplus.lms.services.QuizzService;
import vn.plusplus.lms.services.SessionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "admin/session")
public class SessionController {
    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    SessionService service;
    @Autowired
    ResponseFactory factory;
    @Autowired
    MaterialService materialService;
    @Autowired
    QuizzService quizzService;

    @PostMapping
    public ResponseEntity addSession(@RequestBody NewSessionRequest request) {
        logger.info("Add new session with body {}", request);
        SessionEntity data = service.addNewSession(request);
        return factory.success(data,SessionEntity.class);
    }

    @PutMapping(value = "/{session_id}")
    public ResponseEntity updateSession(@PathVariable(name = "session_id") Integer sessionId,
                                       @RequestBody UpdateSessionRequest request){

        logger.info("Update session Id [{}] with body {}", sessionId, request);
        SessionEntity data = service.updateSession(sessionId,request);
        return factory.success(data,SessionEntity.class);
    }

    @DeleteMapping(value = "/{session_id}")
    public ResponseEntity deleteSession (@PathVariable(name = "session_id") Integer sessionId){
        logger.info("Delete session Id [{}]", sessionId);
        String data = service.deleteSession(sessionId);
        return factory.success(data,String.class);
    }

    @GetMapping(value = "/{session_id}")
    public ResponseEntity getSessionById(@PathVariable(name = "session_id") Integer sessionId,
                                         @RequestAttribute Payload payload){
        logger.info("Get session Id [{}]", sessionId);
        SessionEntity data = service.getSessionById(sessionId, payload.getUserId());
        return factory.success(data,SessionEntity.class);
    }

//    @GetMapping()
//    public ResponseEntity<Object> findSessionByLessonId(@RequestParam(name = "lesson_id") Integer lessonId){
//        List<SessionEntity> data = service.findSessionByLessonId(lessonId);
//        return factory.success(data);
//    }
//
//    @GetMapping("/material-quizz")
//    public ResponseEntity<Object> findMaterialAndQuizzBySessionId(@RequestParam(name =
//        "session_id") Integer sessionId){
//        List<MaterialEntity> materialEntityList = materialService.findMaterialBySessionId(sessionId);
//        List<QuizzEntity> quizzEntityList = quizzService.findQuizzBySessionId(sessionId);
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("materials", materialEntityList);
//        map.put("quizzs", quizzEntityList);
//        return factory.success(map);
//    }
}
