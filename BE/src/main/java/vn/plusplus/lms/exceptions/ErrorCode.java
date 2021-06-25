package vn.plusplus.lms.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    SUCCESS(HttpStatus.OK, "Success", "Thành công"),
    GENERAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error", "Thất bại"),
    USER_HAS_NO_PERMISSION(HttpStatus.FORBIDDEN, "You have no permission", "Bạn không có quyền xem tài liệu này"),
    MATERIAL_NOT_IN_COURSE(HttpStatus.BAD_REQUEST, "Material is not belong any course", "Học liệu không thuộc khóa học"),
    SLIDE_NOT_IN_COURSE(HttpStatus.BAD_REQUEST, "Slide is not belong any course", "Slide không thuộc khóa học"),
    SESSION_NOT_IN_COURSE(HttpStatus.BAD_REQUEST, "Session is not belong any course", "Session không thuộc khóa học"),
    LESSON_NOT_IN_COURSE(HttpStatus.BAD_REQUEST, "Lesson is not belong any course", "Lesson không thuộc khóa học"),
    QUIZZQUESTION_NOT_IN_COURSE(HttpStatus.BAD_REQUEST, "Quizz Question is not belong any course", "Quizz Question không thuộc khóa học"),
    QUIZZ_NOT_IN_COURSE(HttpStatus.BAD_REQUEST, "Quizz is not belong any course", "Quizz không thuộc khóa học"),
    ASSIGNMENT_NOT_IN_COURSE(HttpStatus.BAD_REQUEST, "Assignment is not belong any course", "Assignment không thuộc khóa học"),
    EXAMQUESTION_NOT_IN_COURSE(HttpStatus.BAD_REQUEST, "Exam Question is not belong any course", "Exam Question không thuộc khóa học"),
    EXAM_NOT_IN_COURSE(HttpStatus.BAD_REQUEST, "Exam is not belong any course", "Exam không thuộc khóa học"),
    AUTHORIZATION_FIELD_MISSING(HttpStatus.FORBIDDEN, "Required token in header", "Yêu cầu đăng nhập"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "Token invalid", "Mã truy cập không hợp lệ"),
    ACCESS_TOKEN_EXPIRE(HttpStatus.BAD_REQUEST, "Token is expired", "Mã truy cập hết hạn"),
    API_NOT_FOUND(HttpStatus.FORBIDDEN, "API not found", "Truy cập không hợp lệ"),
    USER_DONT_HAVE_ROLE(HttpStatus.BAD_REQUEST,"User don't have role", "Người dùng không có role");

    private final HttpStatus status;
    private String code;
    private String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public HttpStatus status() {
        return status;
    }

    public String message() {
        return message;
    }
}
