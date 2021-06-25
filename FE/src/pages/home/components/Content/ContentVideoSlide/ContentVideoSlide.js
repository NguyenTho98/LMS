import React from 'react'
import './styles.scss';
import * as Icons from "pages/home/common/Icons";
import ReactPlayer from 'react-player/youtube'
function ContentVideoSlide(props){
    const { currentSlide, nextSilde, backSilde,  } = props;
    return (
      <div className="home-content-slide-video-screen-wrapper">
          <div className="card content-slide">
            <div className="content">
                {/* <ReactPlayer url='https://www.youtube.com/watch?v=71oCKxq0HAk'
                  width='100%'
                  height='100%'
                /> */}
                {
                 currentSlide && currentSlide.slideAvatar ? (
                    <img alt="" src={`${currentSlide.slideAvatar}`+'.png'} />
                  ) : ''

                }
                {/* <div className="contnet-silde-image"></div> */}
                {/* <div className="content-quizz"></div> */}
            </div>
            <div className="footer">
              <div className="footer-action">
                <span className="icon-back" onClick={()=>backSilde()}>
                  <Icons.IconsBack />
                </span>
                <span className="icon-next" onClick={()=>nextSilde()}>
                  <Icons.IconsNext />
                </span>
              </div>
            </div>
          </div>
          <div className="card content-slide-note">{currentSlide ? currentSlide.slideDetail : ''}</div>
      </div>
    )
}
export default ContentVideoSlide;
