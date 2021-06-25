
import * as actionTypes from 'constants/actionTypes'
const initState = {
  currentMaterial: {},
}

export default (state = initState, action) => {
  switch (action.type) {
    case actionTypes.SET_MATERIAL_ITEM:
      return {
        ...state, currentMaterial: action.item
      }
    default:
      return state
  }
}
