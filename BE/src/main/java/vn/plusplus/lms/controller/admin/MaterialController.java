package vn.plusplus.lms.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewMaterialRequest;
import vn.plusplus.lms.controller.request.UpdateMaterialRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.entities.MaterialEntity;
import vn.plusplus.lms.services.MaterialService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "admin/material")
public class MaterialController {
    private static final Logger logger = LoggerFactory.getLogger(MaterialController.class);

    @Autowired
    MaterialService service;
    @Autowired
    ResponseFactory factory;

    @PostMapping
    public ResponseEntity addMaterial(@RequestBody NewMaterialRequest request) {
        logger.info("Add new material with body {}", request);
        MaterialEntity data = service.addNewMaterial(request);
        return factory.success(data,MaterialEntity.class);
    }

    @PutMapping(value = "/{material_id}")
    public ResponseEntity updateMaterial(@PathVariable(name = "material_id") Integer materialId,
                                         @RequestBody UpdateMaterialRequest request) {
        logger.info("Update material Id [{}] with body {}", materialId, request);
        MaterialEntity data = service.updateMaterial(materialId, request);
        return factory.success(data,MaterialEntity.class);
    }

    @DeleteMapping(value = "/{material_id}")
    public ResponseEntity deleteMaterial(@PathVariable(name = "material_id") Integer materialId) {
        logger.info("Delete material Id [{}]", materialId);
        String data = service.deleteMaterial(materialId);
        return factory.success(data,String.class);
    }

    @GetMapping(value = "/{material_id}")
    public ResponseEntity getMaterialById(@PathVariable(name = "material_id") Integer materialId,
                                          @RequestAttribute Payload payload) {
        logger.info("Get material Id [{}]", materialId);
        MaterialEntity data = service.getMaterialById(materialId, payload.getUserId());
        return factory.success(data,MaterialEntity.class);
    }

//    @GetMapping()
//    public ResponseEntity<Object> findMaterialBySessionId(@RequestParam(name = "session_id") Integer sessionId){
//        List<MaterialEntity> data = service.findMaterialBySessionId(sessionId);
//        return factory.success(data);
//    }
}
