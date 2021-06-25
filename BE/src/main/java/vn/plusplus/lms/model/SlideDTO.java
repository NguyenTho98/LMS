package vn.plusplus.lms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlideDTO {
    private Integer id;
    private String slideAvatar;
    private String slideDetail;
    private String slideVideoTime;
    private String slideChunk;
    private Integer orderInMaterial;
    private Integer materialId;
}
