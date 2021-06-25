package vn.plusplus.lms.controller.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewSlideRequest;
import vn.plusplus.lms.controller.request.UpdateSlideRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.entities.SlideEntity;
import vn.plusplus.lms.services.SlideService;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "admin/slide")
public class SlideController {
    private static final Logger logger = LoggerFactory.getLogger(SlideController.class);
    @Autowired
    SlideService service;
    @Autowired
    ResponseFactory factory;

    @PostMapping
    public ResponseEntity addSlide(@RequestBody NewSlideRequest request) {
        logger.info("Add new slide with body {}", request);
        SlideEntity data = service.addNewSlide(request);
        return factory.success(data,SlideEntity.class);
    }

    @PutMapping(value = "/{slide_id}")
    public ResponseEntity updateSlide(@PathVariable(name = "slide_id") Integer slideId,
                                   @RequestBody UpdateSlideRequest request) {

        logger.info("Update slide Id [{}] with body {}", slideId, request);
        SlideEntity data =service.updateSlide(slideId, request);
        return factory.success(data,SlideEntity.class);
    }

    @DeleteMapping(value = "/{slide_id}")
    public ResponseEntity deleteSlide(@PathVariable(name = "slide_id") Integer slideId) {
        logger.info("Delete slide Id [{}]", slideId);
        String data = service.deleteSlide(slideId);
        return factory.success(data,String.class);
    }

    @GetMapping(value = "/{slide_id}")
    public ResponseEntity getSlideById(@PathVariable(name = "slide_id") Integer slideId,
                                    @RequestParam(name = "user_id") Integer userId) {
        logger.info("Get slide Id [{}]", slideId);
        SlideEntity data = service.getSlideById(slideId,userId);
        return factory.success(data,SlideEntity.class);
    }

    @GetMapping
    public ResponseEntity getSlideByMaterialId(@RequestParam(name = "material_id") Integer materialId,
                                               @RequestAttribute Payload payload) {
        logger.info("Get slide by Material_Id [{}]", materialId);
        List<SlideEntity> data = service.getSlideByMaterialId(materialId, payload.getUserId());
        return factory.success(data, List.class);
    }

//    @GetMapping()
//    public ResponseEntity<Object> findSlideByMaterialId(@RequestParam(name = "material_id") Integer materialId){
//        List<SlideEntity> data = service.findSlideByMaterialId(materialId);
//        return factory.success(data);
//    }
}
