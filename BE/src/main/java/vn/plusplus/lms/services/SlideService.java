package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.request.NewSlideRequest;
import vn.plusplus.lms.controller.request.UpdateSlideRequest;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.repository.*;
import vn.plusplus.lms.repository.entities.*;
import vn.plusplus.lms.config.Constant;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SlideService {
    private static final Logger logger = LoggerFactory.getLogger(SlideService.class);
    @Autowired
    SlideRepository slideRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    UserCourseRepository userCourseRepository;

    public SlideEntity addNewSlide(NewSlideRequest request) {
        SlideEntity entity = new SlideEntity();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        entity.setSlideAvatar(request.getSlideAvatar());
        entity.setSlideDetail(request.getSlideDetail());
        entity.setSlideChunk(request.getSlideChunk());
        entity.setMaterialId(request.getMaterialId());
        entity.setSlideVideoTime(request.getSlideVideoTime());
        entity.setOrderInMaterial(request.getOrderInMaterial());
        entity.setCreatedTime(timestamp);
        entity.setUpdatedTime(timestamp);
        entity.setStatus(Constant.Status.DRAFF);

        return slideRepository.save(entity);
    }

    public SlideEntity updateSlide(Integer slideId, UpdateSlideRequest request) {
        SlideEntity entity = slideRepository.findOneById(slideId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (entity == null) {
            entity = new SlideEntity();
            entity.setCreatedTime(timestamp);
        }
        if (request.getMaterialId() != null) {
            entity.setSlideAvatar(request.getSlideAvatar());
        }
        if (request.getSlideDetail() != null) {
            entity.setSlideDetail(request.getSlideDetail());
        }
        if (request.getSlideChunk() != null) {
            entity.setSlideChunk(request.getSlideChunk());
        }
        if (request.getMaterialId() != null) {
            entity.setMaterialId(request.getMaterialId());
        }
        if (request.getSlideVideoTime() != null) {
            entity.setSlideVideoTime(request.getSlideVideoTime());
        }
        if (request.getOrderInMaterial() != null) {
            entity.setOrderInMaterial(request.getOrderInMaterial());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }

        entity.setUpdatedTime(timestamp);

        return slideRepository.save(entity);
    }

    public String deleteSlide(Integer slideId) {
        slideRepository.deleteById(slideId);
        return "Deleted Slide";
    }

    public SlideEntity getSlideById(Integer slideId, Integer userId) {
        Integer courseID = slideRepository.findCourseIdBySlideId(slideId);
        if (courseID == null) {
            logger.info("SlideId [{}] is not belong any course");
            throw new AppException(ErrorCode.SLIDE_NOT_IN_COURSE);
        }
        UserCourseEntity userCourseEntity = userCourseRepository.findOneByUserIdAndCourseIdAndStatus(userId, courseID, Constant.Status.ACTIVE);
        if (userCourseEntity == null) {
            logger.info("User Id [{}] isn't allowed to view this slide", userId);
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        SlideEntity entity = slideRepository.findSlideByIdAndStatus(slideId, Constant.Status.ACTIVE);

        return entity;
    }

    public List<SlideEntity> getSlideByMaterialId(Integer materialId, Integer userId) {
        /*Check userId can view material first
         * Using native query for best performance hear*/
        Integer courseId = materialRepository.findCourseIdByMaterialId(materialId);
        if (courseId == null) {
            logger.info("MaterialID [{}] is not belong any course", materialId);
            throw new AppException(ErrorCode.MATERIAL_NOT_IN_COURSE);
        }
        UserCourseEntity userCourseEntity = userCourseRepository.findOneByUserIdAndCourseIdAndStatus(userId, courseId, Constant.Status.ACTIVE);
        if (userCourseEntity == null) {
            logger.info("UserId [{}] isn't allowed to view materialId [{}]", userId, materialId);
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        List<SlideEntity> listSlide = slideRepository.findAllByMaterialIdAndStatus(materialId, Constant.Status.ACTIVE);
        return listSlide;
    }
}
