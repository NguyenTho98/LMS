import * as actionTypes from 'constants/actionTypes';

export const setQuizzItem = (item) => ({
  type: actionTypes.SET_QUIZZ_ITEM,
  item,
});
export const getQuizzQuestions = (data) => ({
  type: actionTypes.GET_QUIZZ_QUESTIONS,
  data,
});
export const showQuizzQuestionModal = (show) => ({
  type: actionTypes.SHOW_QUIZZ_QUESTION_MODAL,
  show,
});
