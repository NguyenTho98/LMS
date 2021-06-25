package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSessionRequest {
    private String sessionName;
    private Integer orderInLesson;
    private String sessionType;
    private Integer lessionId;
    private String status;
}
