package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.request.NewMaterialRequest;
import vn.plusplus.lms.controller.request.UpdateMaterialRequest;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.repository.LessonRepository;
import vn.plusplus.lms.repository.MaterialRepository;
import vn.plusplus.lms.repository.SessionRepository;
import vn.plusplus.lms.repository.UserCourseRepository;
import vn.plusplus.lms.repository.entities.LessonEntity;
import vn.plusplus.lms.repository.entities.MaterialEntity;
import vn.plusplus.lms.repository.entities.SessionEntity;
import vn.plusplus.lms.repository.entities.UserCourseEntity;
import vn.plusplus.lms.config.Constant;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MaterialService {
    private static final Logger logger = LoggerFactory.getLogger(MaterialService.class);

    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    UserCourseRepository userCourseRepository;

    public MaterialEntity addNewMaterial(NewMaterialRequest request) {
        MaterialEntity entity = new MaterialEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        entity.setMaterialName(request.getMaterialName());
        entity.setMaterialType(request.getMaterialType());
        entity.setMaterialUrl(request.getMaterialUrl());
        entity.setSessionId(request.getSessionId());
        entity.setOrderInSession(request.getOrderInSession());
        entity.setCreatedTime(timestamp);
        entity.setUpdatedTime(timestamp);
        entity.setStatus(Constant.Status.DRAFF);

        return materialRepository.save(entity);
    }

    public MaterialEntity updateMaterial(Integer materialId, UpdateMaterialRequest request) {
        MaterialEntity entity = materialRepository.findOneById(materialId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (entity == null) {
            entity = new MaterialEntity();
            entity.setCreatedTime(timestamp);
        }
        if (request.getMaterialName() != null) {
            entity.setMaterialName(request.getMaterialName());
        }
        if (request.getMaterialType() != null) {
            entity.setMaterialType(request.getMaterialType());
        }
        if (request.getMaterialUrl() != null) {
            entity.setMaterialUrl(request.getMaterialUrl());
        }
        if (request.getSessionId() != null) {
            entity.setSessionId(request.getSessionId());
        }
        if (request.getOrderInSession() != null) {
            entity.setOrderInSession(request.getOrderInSession());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
        entity.setUpdatedTime(timestamp);


        return materialRepository.save(entity);
    }

    public String deleteMaterial(Integer materialId) {
        materialRepository.deleteById(materialId);
        return "Deleted Material";
    }

    public MaterialEntity getMaterialById(Integer materialId, Integer userId) {
        Integer courseId = materialRepository.findCourseIdByMaterialId(materialId);
        if (courseId == null) {
            logger.info("Material Id [{}] is not belong any course");
            throw new AppException(ErrorCode.MATERIAL_NOT_IN_COURSE);
        }
        UserCourseEntity userCourseEntity = userCourseRepository.findOneByUserIdAndCourseIdAndStatus(userId, courseId, Constant.Status.ACTIVE);
        if (userCourseEntity == null) {
            logger.info("User Id [{}] isn't allowed to view this material", userId);
        }
        MaterialEntity entity = materialRepository.findOneByIdAndStatus(materialId, Constant.Status.ACTIVE);
        return entity;
    }

    public List<MaterialEntity> findMaterialBySessionId(Integer sessionId){
        List<MaterialEntity> entity = materialRepository.findMaterialBySessionId(sessionId);
        return entity;
    }
}
