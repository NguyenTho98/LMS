import React, { useEffect } from "react";
import {
  Router,
  Route,
  Switch,
  withRouter,
} from "react-router-dom";
import { connect } from "react-redux";
import "./styles/app.scss";
import history from './utils/history';
import Home from "./pages/home/components/Home";
import login from "./pages/login/login";
import User from "./pages/user/components/user";
import fetchConfig from "./utils/fetchConfig";

class App extends React.Component {
  constructor(props) {
    super(props);
    fetchConfig();
  }
  render(){
    return (
      <Router history={history}>
        <Switch>
          <Route exact path="/user" component={User} />
          <Route exact path="/login" component={login} />
          <Route exact path="/" component={Home} />

        </Switch>
      </Router>
    );
  }
}

export default withRouter(connect(null, null)(App));
