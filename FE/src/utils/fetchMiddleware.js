import * as API from 'constants/api';
import storage from "./storage";

export default (fetchImplementation) => (store) => (next) => (action) => {
  if (action) {
    if (action.type && action.type === "FETCH") {
      const { url, params } = action;
        const urlWithAccountId = url;
        let token= null;
        // if (store.getState().auth.accessToken) {
        //   token = store.getState().auth.accessToken;
        // } else {
        //   token = storage.get("token", false);
        // }
      return wrapAccessToken(
        urlWithAccountId,
        params,
        token,
        store
      )(fetchImplementation);
    }
    return next(action);
  }
};

const wrapAccessToken = (url, params, token, store) => (
  fetchImplementation
) => {
  debugger;
  params.headers = params.headers || {};
  params.headers["Authorization"] = "000000";
  params.headers["Origin"] = API.PORT;
  params.headers["Access-Control-Allow-Origin"] = "*";
  params.headers["Access-Control-Allow-Methods"] = "*";
  if (!params.headers["Content-Type"]) {
    params.headers["Content-Type"] = "application/json";
  }
  url = 'http://localhost:8081/lms/v1/room/slide/1';
  return fetchImplementation(url, params)
    .then((res) => {
      const body = res.json();
      if (res.ok) {
        return body;
      }
      if (res.status === 401) {
        //
      }
      throw new Error(body.message || res.statusText);
    })
    .catch((e) => {
      console.error(e);
    });
};

export const fetch = (url, params = {}) => ({
  type: "FETCH",
  url,
  params,
});
