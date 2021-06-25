package vn.plusplus.lms.config;

public class Constant {
    public static class Status{
        public static final String ACTIVE = "ACTIVE";
        public static final String DRAFF = "DRAFF";
    }
    public static class LessonType{
        public static final String KNOWLEDGE = "KNOWLEDGE";
        public static final String PRACTICE = "PRACTICE";
    }
    public static class SessionType{
        public static final String SELF_STUDY = "SELF_STUDY";
        public static final String STUDY_WITH_INSTRUCTOR = "INSTRUCTOR";
        public static final String DISCUSSION = "DISCUSSION";
    }
    public static class AssignmentType{
        public static final String NORMAL = "NORMAL";
        public static final String ADVANCE = "ADVANCE";
    }
    public static class MaterialType{
        public static final String SLIDE = "SLIDE";
        public static final String VIDEO = "VIDEO";
        public static final String PDF = "PDF";
    }
    public static class QuizzType{
        public static final String FIRST = "FIRST";
        public static final String MID = "MID";
        public static final String END = "END";
    }
    public static class ExamType{
        public static final String INPUT = "INPUT";
        public static final String MID_TERM = "MID_TERM";
        public static final String OUTPUT = "OUTPUT";
    }
    /*Using for quizz_question and exam_question table*/
    public static class QuestionType{
        public static final String RADIO = "RADIO";
        public static final String MULTI_CHOICE_2 = "MULTI_CHOICE_2";
        public static final String MULTI_CHOICE_3 = "MULTI_CHOICE_3";
        public static final String MULTI_CHOICE_4 = "MULTI_CHOICE_4";
        public static final String TEXT = "TEXT";
    }

    public static class RoleType{
        public static final String USER= "USER";
        public static final String ADMIN = "ADMIN";
    }
}
