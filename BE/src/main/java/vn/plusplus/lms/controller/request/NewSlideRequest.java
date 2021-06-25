package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NewSlideRequest {
    private String slideAvatar;
    private String slideDetail;
    private String slideVideoTime;
    private String slideChunk;
    private Integer orderInMaterial;
    private Integer materialId;
    private String status;
}
