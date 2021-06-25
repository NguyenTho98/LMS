
import * as actionTypes from 'constants/actionTypes'
const initState = {
  currentLesson: {},
}

export default (state = initState, action) => {
  switch (action.type) {
    case actionTypes.SET_LESSON_ITEM:
      return {
        ...state, currentLesson: action.item
      }
    default:
      return state
  }
}
