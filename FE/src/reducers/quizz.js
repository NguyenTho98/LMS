
import * as actionTypes from 'constants/actionTypes'
const initState = {
  currentQuizz: {},
  modalQuizzQuestion: false,
  quizzQuestions:[],
}

export default (state = initState, action) => {
  switch (action.type) {
    case actionTypes.GET_QUIZZ_QUESTIONS:
      return {
        ...state, quizzQuestions: action.data
      }
    case actionTypes.SET_QUIZZ_ITEM:
      return {
        ...state, currentQuizz: action.item
      }
    case actionTypes.SHOW_QUIZZ_QUESTION_MODAL:
      return {
        ...state, modalQuizzQuestion: action.show
      }
    default:
      return state
  }
}
