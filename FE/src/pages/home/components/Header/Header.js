import React, { useState, useEffect, useRef } from 'react'
import './styles.scss'
import logo from 'components/images/logo.png'
import DropdownMenu from './InfoUser/DropdownMenu'
import Lession from './Lession/Lession';
import * as Icons from "pages/home/common/Icons";

function Header(props){
  const { currentLesson, currentCourse, lessons } = props;
  const [showUser, setShowUser] = useState(false)
  const [showLesson, setShowLesson] = useState(false)
  const myRef = useRef();
  const myRef1= useRef();
  useEffect(() => {
    document.addEventListener('mousedown', handleClickOutsideUser);
    document.addEventListener('mousedown', handleClickOutsideLesson);
    return () => {
      document.removeEventListener('mousedown', handleClickOutsideUser);
      document.removeEventListener('mousedown', handleClickOutsideLesson);
    }
  }, [])
  const handleClickOutsideUser = event => {
    if (myRef.current && !myRef.current.contains(event.target)) {
      setShowUser(false)
    }
  };
  const handleClickOutsideLesson = event => {
    if (myRef1.current && !myRef1.current.contains(event.target)) {
      setShowLesson(false)
    }
  };
    return (
      <div className="home-header-wrapper">
          <div className="d-flex top-bar">
              <div className="logo">
                  <img className="img-logo" src={logo} alt=""></img>
              </div>
              <div className="lesson">
                <div className="d-flex lesson-item">
                  <div className="item-lable-select" onClick={()=>setShowLesson(!showLesson)}  ref={myRef1}  >
                      Bài học
                      <span className="position-absolute down-icon">
                        <Icons.IconsDown />
                      </span>
                      {
                        showLesson ? (
                          <Lession lessons={lessons} setShowLesson={setShowLesson}/>
                        ) : ''
                      }

                  </div>
                  <span className="title">&nbsp; {currentLesson && currentLesson.lessonName}</span>
                </div>
              </div>
              <div className="course">
                <div className="d-flex course-item">
                  <div className="item-lable">Khoá học: </div>
                  <span>&nbsp; {currentCourse && currentCourse.courseName}</span>
                </div>
              </div>
              <div className="d-flex info-user">
                  <div className="info"></div>
                  <div className="user" onClick={()=>setShowUser(!showUser)} ref={myRef}>
                    <a>
                      <img alt="" src="https://scontent.fhan5-6.fna.fbcdn.net/v/t1.6435-1/cp0/p50x50/73033766_2535299370077667_1628779773352214528_n.jpg?_nc_cat=105&ccb=1-3&_nc_sid=7206a8&_nc_ohc=bUiOONB9zlwAX9wv2us&_nc_ht=scontent.fhan5-6.fna&tp=27&oh=bd28829bd7e5675801752297ead6da98&oe=60D70B7B"></img>
                      <span className="status active"></span>
                    </a>
                    { showUser ? (
                      <DropdownMenu />
                    ): ''}
                  </div>
              </div>
          </div>
      </div>
    )
}

export default Header;
