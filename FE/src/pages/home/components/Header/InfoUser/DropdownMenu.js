import React from "react";
import "./styles.scss";
import * as Icons from 'pages/home/common/Icons'
function DropdownMenu() {
  return (
    <div className="dropdown-menu-more">
      <div
        className="d-flex align-items-center justify-content-between info-active"
        onClick={(e) => {
          e.stopPropagation();
        }}
      >
        <div className="d-flex align-items-center">
          <div className="avatar">
          <img alt="" src="https://scontent.fhan5-6.fna.fbcdn.net/v/t1.6435-1/cp0/p50x50/73033766_2535299370077667_1628779773352214528_n.jpg?_nc_cat=105&ccb=1-3&_nc_sid=7206a8&_nc_ohc=bUiOONB9zlwAX9wv2us&_nc_ht=scontent.fhan5-6.fna&tp=27&oh=bd28829bd7e5675801752297ead6da98&oe=60D70B7B"></img>
            <span className="status active" />
          </div>
          <div>
            <p>Nguyễn Thọ</p>
            <div className="toggle-status-online">
              <span className="status active">
                Trực tuyến
              </span>
            </div>
          </div>
        </div>
        <span className="logout-action" title="Đăng xuất">
          <Icons.logoutIcon />
        </span>
      </div>
    </div>
  );
}
export default DropdownMenu;
