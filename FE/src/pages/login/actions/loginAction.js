import * as actionTypes from "actions/actionTypes";
import { fetch } from "utils/fetchMiddleware";
import callApi from "utils/callApi";
import storage from "../../../utils/storage";
import history from '../../../utils/history';

export const login = (user = {}) => (dispatch, getState) => {
  const data = {
    userName: user.email,
    password: user.password.trim(),
  };
  const options = {
    method: "POST",
    data: data,
  };
  const url='/user/login'
  return callApi(url, options)
    .then((res) => {
      console.log("res", res);
      if (res && res.data) {
        dispatch(receiveAccessToken(res.data.data));
        return token;
      }
    })
    .catch((err) => {
      return err;
    });
};

// export const checkInfoUser = (token) => (dispatch, getState) => {
//   return dispatch(fetch(`${API_USER}/checkUser`, {
//     method: 'GET',
//     headers: {
//       'X-APP-PAGE-TOKEN': token,
//     }
//   }))
//     .then((json) => {
//       if (json && json.role) {
//         if (token) dispatch(receiveAccessToken(token));
//         dispatch(receiveAccount(json));
//       } else {
//         history.push("/login");
//         storage.clear();
//       }
//       return json;
//     })
//     .catch((e) => {
//       return e;
//     });
// };

export const receiveAccessToken = accessToken => ({
  type: actionTypes.RECEIVE_ACCESS_TOKEN,
  accessToken,
});

// export const receiveAccount = account => ({
//   type: actionTypes.RECEIVE_USER_ACCOUNT,
//   account,
// });

export const logout = () => ({
  type: actionTypes.LOGOUT,
});
