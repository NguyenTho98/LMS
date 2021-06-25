import React,{
  useState
} from "react";
import "./styles.scss";
import * as Icons from "pages/home/common/Icons";
import { setMaterialItem } from "../../../actions/material";
import { connect } from "react-redux";

function ContentNote(props) {
  const {
  } = props;
  return (
    <div className="home-content-note-screen-wrapper">
       <div className="card note">
        <div className="header">
          <div className="title">Ghi chú</div>
        </div>
        <div className="content">
          <div className="controls">
            <textarea name="description" placeholder="Ghi chú" />
          </div>
        </div>
      </div>
    </div>
  );
}

const mapDispatchToProps = (dispatch, ownProps) => ({
  setMaterialItem: (item) => dispatch(setMaterialItem(item))
})
export default connect(null, mapDispatchToProps)(ContentNote)
