/* eslint-disable import/order */
import React from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import "./styles.scss";
import { connect } from "react-redux";
import { showQuizzQuestionModal } from "../../../actions/quizz";

function QuizzModal(props) {
  const { modalQuizzQuestion, showQuizzQuestionModal, quizzQuestions } = props;
  console.log("quizzQuestions", quizzQuestions);
  const handleClose = () => {
    showQuizzQuestionModal(false);
  };
  const onConfirm = () => {
    alert("xin chào");
  };
  return (
    <Modal
      show={modalQuizzQuestion}
      onHide={handleClose}
      size="lg"
      dialogClassName="modal-quizz-detail"
    >
      <Modal.Header closeButton>
        <div className="modal-title">
          <span>Hãy trả lời những câu hỏi sau</span>
        </div>
      </Modal.Header>
      <Modal.Body>
        <div className="" style={{ paddingTop: 15, paddingBottom: 15 }}>
          {quizzQuestions && quizzQuestions.length > 0
            ? quizzQuestions.map((item, index) => {
                return (
                  <div className="item" key={index}>
                    <div className="title">
                      Câu{` ${index + 1}`}: &nbsp;{item.question}
                    </div>
                    <div className="row">
                      <div className="col-6">A &nbsp; {item.optionA}</div>
                      <div className="col-6">B &nbsp;{item.optionB}</div>
                      <div className="col-6">C &nbsp;{item.optionC}</div>
                      <div className="col-6">D &nbsp;{item.optionD}</div>
                    </div>
                  </div>
                );
              })
            : ""}
        </div>
      </Modal.Body>
      <Modal.Footer>
        <React.Fragment>
          <Button variant="light" onClick={handleClose}>
            Hủy
          </Button>
          <Button variant="primary" onClick={onConfirm}>
            Nộp bài
          </Button>
        </React.Fragment>
      </Modal.Footer>
    </Modal>
  );
}

const mapStateToProps = (state, ownProps) => {
  const {
    quizz: { modalQuizzQuestion, quizzQuestions },
  } = state;
  return {
    modalQuizzQuestion,
    quizzQuestions,
  };
};
const mapDispatchToProps = (dispatch) => ({
  showQuizzQuestionModal: (show) => dispatch(showQuizzQuestionModal(show)),
});
export default connect(mapStateToProps, mapDispatchToProps)(QuizzModal);
