
import * as actionTypes from 'constants/actionTypes'
const initState = {
  currentSession: {},
}

export default (state = initState, action) => {
  switch (action.type) {
    case actionTypes.SET_SESSION_ITEM:
      return {
        ...state, currentSession: action.item
      }
    default:
      return state
  }
}
