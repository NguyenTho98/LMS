package vn.plusplus.lms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.plusplus.lms.repository.entities.MaterialEntity;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDTO {
    private Integer id;
    private String materialName;
    private String materialUrl;
    private Integer orderInSession;
    private String materialType;
    private Integer sessionId;

//    public MaterialDTO(MaterialEntity entity){
//        this.id = entity.getId();
//        this.materialName = entity.getMaterialName();
//        this.materialUrl = entity.getMaterialUrl();
//        this.orderInSession = entity.getOrderInSession();
//        this.materialType = entity.getMaterialType();
//        this.sessionId = entity.getSessionId();
//    }
}
