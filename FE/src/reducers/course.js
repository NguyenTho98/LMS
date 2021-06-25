
import * as actionTypes from 'constants/actionTypes'
const initState = {
  currentCourse: {},
}

export default (state = initState, action) => {
  switch (action.type) {
    case actionTypes.SET_COURSE_ITEM:
      return {
        ...state, currentCourse: action.item
      }
    default:
      return state
  }
}
