package vn.plusplus.lms.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "material")
@Getter @Setter
public class MaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "material_name")
    private String materialName;

    @Column(name = "material_url")
    private String materialUrl;

    @Column(name = "order_in_session")
    private Integer orderInSession;

    @Column(name = "material_type")
    private String materialType;

    @Column(name = "session_id")
    private Integer sessionId;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Column(name = "status")
    private String status;
}
