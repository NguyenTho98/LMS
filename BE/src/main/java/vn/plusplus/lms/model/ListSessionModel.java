package vn.plusplus.lms.model;


import lombok.Getter;
import lombok.Setter;
import vn.plusplus.lms.repository.entities.MaterialEntity;
import vn.plusplus.lms.repository.entities.QuizzEntity;


import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ListSessionModel implements Serializable {
    private Integer sessionId;
    private List<QuizzDTO> listQuizz;
    private List<MaterialDTO> listMaterial;

    public ListSessionModel(Integer sessionId, List<QuizzDTO> listQuizz, List<MaterialDTO> listMaterial) {
        this.sessionId = sessionId;
        this.listQuizz = listQuizz;
        this.listMaterial = listMaterial;
    }

}
