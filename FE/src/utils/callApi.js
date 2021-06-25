import axios from 'axios';
const serviceUrl = 'http://localhost:8081/lms/v1'
const callApi = function callApi(url, {}) {
  // if (!options.mode) {
  //   options.mode = 'cors';
  // }
  // if (options.headers) {
  //   if (!options.headers['Content-Type']) {
  //     Object.assign(options.headers, { 'Content-Type': 'application/json' });
  //   }
  //   if (!options.headers['Access-Control-Allow-Origin']) {
  //     Object.assign(options.headers, { 'Access-Control-Allow-Origin': "*"});
  //   }
  // } else {
  //   options.headers = {
  //     'Content-Type': 'application/json',
  //     'Access-Control-Allow-Origin': '*',
  //     'Access-Control-Allow-Methods': 'HEAD, GET, POST, PUT, PATCH, DELETE',
  //     'Authorization': '000000',

  //   };
  // }
  let options = {};
  options.headers = {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'HEAD, GET, POST, PUT, PATCH, DELETE',
    'Authorization': '000000',

  };
  options.url = serviceUrl + url;

  return axios(options)
    .then(
      (response) => { return response; },
      (error) => { return error; },
    );
};

export default callApi;
