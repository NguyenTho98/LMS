import * as actionTypes from 'constants/actionTypes';

export const setSessionItem = (item) => ({
  type: actionTypes.SET_SESSION_ITEM,
  item,
});
