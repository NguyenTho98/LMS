import fetchIntercept from 'fetch-intercept';
let serviceUrl = 'http://localhost:8081/lms/v1'

// if (API_URL) {
//   serviceUrl = API_URL;
// }

export default () => fetchIntercept.register({
  request(url, config) {
    // const token = sessionStorage.getItem('token');
    if (config == null) {
      config = {};
    }
    if (config.headers == null) {
      config.headers = {};
    }
    // if (config.headers['Authorization'] == null) {
    //   config.headers['Authorization'] = "000000";
    // }
    if (config.headers.Authorization == null) {
      config.headers.Authorization = "000000";
    }
    if (config.headers.Accept == null) {
      config.headers.Accept = 'application/json';
    }
    if (config.headers['Content-Type'] == null) {
      config.headers['Content-Type'] = 'application/json';
    }
    debugger;
    //config.headers.Authorization = "000000";
    // if (ENV === 'localhost') {
      if (!url.startsWith('http')) {
        if (!url.startsWith('/')) {
          url = `/${url}`;
        }
        url = serviceUrl + url;
      }
    // }
    return [url, config];
  },

  requestError(error) {
    // Called when an error occurred during another 'request' interceptor call
    return Promise.reject(error);
  },

  response(response) {
    // Modify the response object
    return response;
  },

  responseError(error) {
    // Handle an fetch error
    return Promise.reject(error);
  }
});
