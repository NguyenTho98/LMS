package vn.plusplus.lms.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "slide")
@Getter @Setter
public class SlideEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "slide_avatar")
    private String slideAvatar;

    @Column(name = "slide_detail")
    private String slideDetail;

    @Column(name = "slide_video_time")
    private String slideVideoTime;

    @Column(name = "slide_chunk")
    private String slideChunk;

    @Column(name = "order_in_material")
    private Integer orderInMaterial;

    @Column(name = "material_id")
    private Integer materialId;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Column(name = "status")
    private String status;

}
