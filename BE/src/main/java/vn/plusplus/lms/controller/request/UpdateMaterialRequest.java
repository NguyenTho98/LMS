package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMaterialRequest {
    private String materialName;
    private String materialUrl;
    private Integer orderInSession;
    private String materialType;
    private Integer sessionId;
    private String status;
}
