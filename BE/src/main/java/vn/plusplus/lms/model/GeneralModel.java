package vn.plusplus.lms.model;

import lombok.Getter;
import lombok.Setter;
import vn.plusplus.lms.repository.entities.AssignmentEntity;
import vn.plusplus.lms.repository.entities.SessionEntity;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
public class GeneralModel implements Serializable {
    private List<AssignmentDTO> listAssignments;
    private List<SessionDTO> listSessions;
    private List<ListSessionModel> listDataForSession;

    public GeneralModel(List<AssignmentDTO> listAssignments, List<SessionDTO> listSessions, List<ListSessionModel> listDataForSession) {
        this.listAssignments = listAssignments;
        this.listSessions = listSessions;
        this.listDataForSession = listDataForSession;
    }
}
