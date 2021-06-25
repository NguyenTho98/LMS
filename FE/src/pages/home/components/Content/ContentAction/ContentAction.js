import React, { useState, useEffect, useRef } from "react";
import * as Icons from "pages/home/common/Icons";
import "./styles.scss";
import { connect } from "react-redux";
import { setCourseItem } from "../../../actions/course";
import { setLessonItem } from "../../../actions/lesson";
import { setSessionItem } from "../../../actions/session";
import { setMaterialItem } from "../../../actions/material";
import { getQuizzQuestion, getQuizzQuestions, showQuizzQuestionModal } from "../../../actions/quizz";
import callApi from "../../../../../utils/callApi";
function ContentAction(props) {
  const {
    lessons,
    sessions,
    setLessonItem,
    currentLesson,
    currentSession,
    setMaterialItem,
    setSessionItem,
    materials,
    currentAssignment,
    slides,
    assignments,
    quizzs,
    showQuizzQuestionModal,
    getQuizzQuestions,
  } = props;
  const [showDropMenuSession, setShowDropMenuSession] = useState(false);
  const [showDropMenuAssign, setShowDropMenuAssign] = useState(false);
  const [showDropMenuCourse, setShowDropMenuCourse] = useState(false);
  const [showDropMenuLesson, setShowDropMenuLesson] = useState(false);
  const myRef = useRef();
  const myRef1 = useRef();
  const myRefCourse = useRef();
  const myRefLesson = useRef();
  useEffect(() => {
    document.addEventListener("mousedown", handleClickOutsideSession);
    document.addEventListener("mousedown", handleClickOutsideAssign);
    document.addEventListener("mousedown", handleClickOutsideCourse);
    document.addEventListener("mousedown", handleClickOutsideLesson);
    return () => {
      document.removeEventListener("mousedown", handleClickOutsideSession);
      document.removeEventListener("mousedown", handleClickOutsideAssign);
      document.removeEventListener("mousedown", handleClickOutsideCourse);
      document.removeEventListener("mousedown", handleClickOutsideLesson);
    };
  }, []);
  const handleClickOutsideSession = (event) => {
    if (myRef.current && !myRef.current.contains(event.target)) {
      setTimeout(() => {
        setShowDropMenuSession(false);
      }, 200);
    }
  };
  const handleClickOutsideAssign = (event) => {
    if (myRef1.current && !myRef1.current.contains(event.target)) {
      setTimeout(() => {
        setShowDropMenuAssign(false);
      }, 200);
    }
  };
  const handleClickOutsideCourse = (event) => {
    if (myRefCourse.current && !myRefCourse.current.contains(event.target)) {
      setTimeout(() => {
        setShowDropMenuCourse(false);
      }, 200);
    }
  };
  const handleClickOutsideLesson = (event) => {
    if (myRefLesson.current && !myRefLesson.current.contains(event.target)) {
      setTimeout(() => {
        setShowDropMenuLesson(false);
      }, 200);
    }
  };
  const onGetQuizzQuestions = (id) => {
    showQuizzQuestionModal(true)
    if(!id){
      return;
    }
    const options = {
      method: 'GET',
    };
    const url = `/quizzquestion?quizz_id=${id}`
    callApi(url, options).then(json => {
      if (json && json.data.data) {
        getQuizzQuestions(json.data.data)
      }
    });
  }
  return (
    <div className="home-content-action-screen-wrapper">
      <div className="home-content-left">
        <div className="card session">
          <div className="header">
            <div>Chọn học phần</div>
          </div>
          <div className="content">
            {sessions && sessions.length > 0 ? (
              <div
                className="div-active-lable"
                ref={myRef}
                onClick={() => setShowDropMenuSession(!showDropMenuSession)}
              >
                <div className="text-ellipsis lable">
                  {currentSession.id
                    ? currentSession.sessionName
                    : "Vui lòng học phần"}
                </div>
                <span className="position-absolute down-icon">
                  <Icons.IconsDown />
                </span>
              </div>
            ) : (
              <div className="is-empty">
                <Icons.IconsDocument />
                <div className="no-document">Không có chi tiết học liệu</div>
              </div>
            )}
            {showDropMenuSession ? (
              <React.Fragment>
                <div className="card drop-down-menu-session">
                  {sessions.length > 0 &&
                    sessions.map((item, index) => {
                      const selected = currentSession.id === item.id;
                      return (
                        <div
                          className="item"
                          key={index}
                          onClick={() => {
                            setSessionItem(item);
                          }}
                          style={{ background: `${selected ? "#ebf6ff" : ""}` }}
                        >
                          <div className="item-lable text-ellipsis">
                            {item.sessionName}
                          </div>
                        </div>
                      );
                    })}
                </div>
              </React.Fragment>
            ) : (
              ""
            )}
          </div>
        </div>
        <div className="card assignment">
          <div className="header">
            <div>Chọn bài học</div>
          </div>
          <div className="content">
            {assignments && assignments.length > 0 ? (
              <div
                className="div-active-lable"
                ref={myRef1}
                onClick={() => setShowDropMenuAssign(!showDropMenuAssign)}
              >
                <div className="text-ellipsis lable">
                  {currentAssignment
                    ? currentAssignment.assignmentTitle
                    : "Vui lòng bài học"}
                </div>
                <span className="position-absolute down-icon">
                  <Icons.IconsDown />
                </span>
              </div>
            ) : (
              <div className="is-empty">
                <Icons.IconsDocument />
                <div className="no-document">Không có chi tiết học liệu</div>
              </div>
            )}
            {showDropMenuAssign ? (
              <React.Fragment>
                <div className="card drop-down-menu-session">
                  {assignments.map((item, index) => {
                    const selected = currentAssignment.id === item.id;
                    return (
                      <div
                        className="item"
                        key={index}
                        onClick={() => {
                          setItemAssign(item);
                        }}
                        style={{ background: `${selected ? "#ebf6ff" : ""}` }}
                      >
                        <div className="item-lable text-ellipsis">
                          {item.assignmentTitle}
                        </div>
                      </div>
                    );
                  })}
                </div>
              </React.Fragment>
            ) : (
              ""
            )}
          </div>
        </div>
        <div className="card material">
          <div className="header">
            <div className="title">Học liệu</div>
          </div>
          <div className="content">
            {materials && quizzs && (quizzs.length > 0 || materials.length) > 0 ? (
              <React.Fragment>
                {materials.map((item, index) => {
                  return (
                    <div
                      className="d-flex item"
                      key={index}
                      onClick={() => setMaterialItem(item)}
                    >
                      <div className="icon" style={{ width: 24 }}>
                        <Icons.IconsDocument1 />
                      </div>
                      <div className="item-lable text-ellipsis">
                        {item.materialName}
                      </div>
                    </div>
                  );
                })}
                {
                  quizzs.map((item, index) => {
                    return (
                      <div
                        className="d-flex item"
                        key={index}
                        onClick={() => {
                          onGetQuizzQuestions(item.id)
                        }}
                      >
                        <div className="icon" style={{ width: 24 }}>
                          <Icons.IconsQuizz />
                        </div>
                        <div className="item-lable text-ellipsis">
                          {item.quizzName}
                        </div>
                      </div>
                    );
                  })
                }
              </React.Fragment>
            ) : (
              <div className="is-empty">
                <Icons.IconsDocument />
                <div className="no-document">Không có học liệu</div>
              </div>
            )}
          </div>
        </div>
        <div className="card slide">
          <div className="header">
            <div className="title">Chi tiết học liệu</div>
          </div>
          <div className="content">
            {slides.length > 0 ? (
              slides.map((item, index) => {
                return (
                  <div className="d-flex item" key={index}>
                    <div className="icon">
                      <Icons.IconsSlide />
                    </div>
                    <div className="item-lable text-ellipsis">
                      {item.slideDetail}
                    </div>
                  </div>
                );
              })
            ) : (
              <div className="is-empty">
                <Icons.IconsDocument />
                <div className="no-document">Không có chi tiết học liệu</div>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
const mapStateToProps = (state) => {
  const {
    course: { currentCourse },
    lesson: { currentLesson },
    session: { currentSession },
    assignment: { currentAssignment },
  } = state;
  return {
    currentCourse,
    currentLesson,
    currentSession,
    currentAssignment,
  };
};
const mapDispatchToProps = (dispatch) => ({
  setCourseItem: (item) => dispatch(setCourseItem(item)),
  setLessonItem: (item) => dispatch(setLessonItem(item)),
  setSessionItem: (item) => dispatch(setSessionItem(item)),
  setMaterialItem: (item) => dispatch(setMaterialItem(item)),
  showQuizzQuestionModal: (show) => dispatch(showQuizzQuestionModal(show)),
  getQuizzQuestions: (data) => dispatch(getQuizzQuestions(data)),
});
export default connect(mapStateToProps, mapDispatchToProps)(ContentAction);
