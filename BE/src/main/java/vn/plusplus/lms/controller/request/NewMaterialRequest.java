package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class NewMaterialRequest {
    private String materialName;
    private String materialUrl;
    private Integer orderInSession;
    private String materialType;
    private Integer sessionId;
    private String status;
}
