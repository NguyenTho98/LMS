package vn.plusplus.lms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.plusplus.lms.repository.entities.QuizzEntity;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizzDTO {
    private Integer id;
    private String quizzName;
    private String quizzType;
    private Integer orderInSession;
    private Integer sessionId;

//    public QuizzDTO(QuizzEntity entity){
//        this.id = entity.getId();
//        this.quizzName = entity.getQuizzName();
//        this.quizzName = entity.getQuizzType();
//        this.orderInSession = entity.getOrderInSession();
//        this.sessionId = entity.getSessionId();
//    }
}
