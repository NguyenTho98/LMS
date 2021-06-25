import * as actionTypes from 'constants/actionTypes';
import { fetch } from '../../../utils/fetchMiddleware';

export const setLessonItem = (item) => ({
  type: actionTypes.SET_LESSON_ITEM,
  item,
});

export const getLession = () => (dispatch) =>{
  return dispatch(fetch(`/v1/room/course/1`, {
    method: 'GET',
  }))
    .then((json) => {
      console.log("json", json);
    })
    .catch((e) => {
      return e;
    });
}
