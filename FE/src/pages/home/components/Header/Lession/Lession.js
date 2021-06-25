import React from "react";
import "./styles.scss";
import * as Icons from "pages/home/common/Icons";
import { connect } from "react-redux";
import { setLessonItem } from "../../../actions/lesson";
function Lession(props) {
  const { currentLesson, setLessonItem, lessons, setShowLesson } = props;
  return (
    <div className="lesson-dropdown-menu-more">
      <div
        className="d-flex align-items-center justify-content-between info-active"
        onClick={(e) => {
          e.stopPropagation();
        }}
      >
        <div className="content">
          {lessons && lessons.length > 0 ? (
            <React.Fragment>
              <div className="drop-down-menu-session">
                {lessons.map((item, index) => {
                  const selected = currentLesson.id === item.id;
                  return (
                    <div
                      className="item"
                      key={index}
                      onClick={() => {
                        setLessonItem(item);
                        setShowLesson(false)
                      }}
                      style={{ background: `${selected ? "#ebf6ff" : ""}` }}
                    >
                      <div className="item-lable text-ellipsis">
                        {item.lessonName}
                      </div>
                    </div>
                  );
                })}
              </div>
            </React.Fragment>
          ) : (
            <div className="is-empty">
              <Icons.IconsDocument />
              <div className="no-document">Không có chi tiết học liệu</div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
const mapStateToProps = (state) => {
  const {
    lesson: { currentLesson },
  } = state;
  return {
    currentLesson,
  };
};
const mapDispatchToProps = (dispatch) => ({
  setLessonItem: (item) => dispatch(setLessonItem(item)),
});
export default connect(mapStateToProps, mapDispatchToProps)(Lession);
