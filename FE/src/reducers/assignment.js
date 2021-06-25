
import * as actionTypes from 'constants/actionTypes'
const initState = {
  currentAssignment: {},
}

export default (state = initState, action) => {
  switch (action.type) {
    case actionTypes.SET_ASSIGNMENT_ITEM:
      return {
        ...state, currentAssignment: action.item
      }
    default:
      return state
  }
}
