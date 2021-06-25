import React, { useEffect, useState } from 'react'
import { connect } from 'react-redux';
import callApi from '../../../utils/callApi';
import { setAssignmentItem } from '../actions/assignment';
import { setCourseItem } from '../actions/course';
import { getLession, setLessonItem } from '../actions/lesson';
import { setSessionItem } from '../actions/session';
import Content from './Content/Content';
import Header from './Header/Header';
import QuizzModal from './Modal/QuizzModal/QuizzModal';
import { fetch } from 'utils/fetchMiddleware';
import './styles.scss';
function Home(props){
  const {
    setCourseItem,
    setLessonItem,
    setSessionItem,
    currentCourse,
    currentLesson,
    currentSession,
    currentMaterial,
    setAssignmentItem,
  } = props;
  const [coures, setCoures] = useState([])
  const [lessons, setLessons] = useState([])
  const [sessions, setSessions] = useState([])
  const [materials, setMaterials] = useState([])
  const [listDataForSession, setListDataForSession] = useState([])
  const [slides, setSlides] = useState([])
  const [assignments, setAssignments] = useState([])
  const [quizzs, setQuizzs] = useState([])

  useEffect(() => {
    // getLessions(1)
    props.onTest();
    // setTimeout(() => {
    //   const url = '/room/course/1';
    //   const options = {
    //   };
    //   callApi(url,options).then((response) => {
    //      return response.json();

    //   }).then((json)=>{
    //     console.log("sonnn", json);
    //     if (json && json.data) {
    //       setLessonItem(json.data[0])
    //       setLessons(json.data);
    //       if(json.data.length > 0) {
    //         const item = json.data[0]
    //         getLessions(item.id)
    //       }
    //     }
    //   });
    // }, 1000);
  }, []);

  useEffect(() => {
    if(currentLesson && currentLesson.id){
      getLessions(currentLesson.id);
    }
  }, [currentLesson]);

  useEffect(() => {
    if(currentSession && currentSession.id){
      const result = listDataForSession.find((item)=> item.sessionId === currentSession.id)
      if(result) {
        setMaterials(result.listMaterial)
        setQuizzs(result.listQuizz)
      }
    }
  }, [currentSession]);
  // useEffect(() => {
  //   if(currentCourse && currentCourse.id){
  //     getLessions(currentCourse.id)
  //   }
  // }, [currentCourse]);

  // useEffect(() => {
  //   if(currentSession && currentSession.id){
  //     getMaterialAndQuizz(currentSession.id)
  //   }
  // }, [currentSession && currentSession.id]);

  useEffect(() => {
    if(currentMaterial && currentMaterial.id){
      getSlides(currentMaterial.id)
    }
  }, [currentMaterial]);

  const getLessions = (id) => {
    if(!id){
      return;
    }
    props.onTest();
    const options = {
      method: 'GET',
    };
    const url = `/room/lesson/${id}`
    // fetch(url,options).then((response) => {
  //     return response.json();
  //  }).then((json)=>{
  //    console.log("1111", json);
  //     if (json && json.data) {
  //       setAssignmentItem(json.data.listAssignments[0])
  //       setAssignments(json.data.listAssignments);
  //       setSessions(json.data.listSessions)
  //       setSessionItem(json.data.listSessions[0])
  //       setListDataForSession(json.data.listDataForSession)
  //       if(json.data.listAssignments[0]) {
  //         const arr = json.data.listDataForSession;
  //         const tmp = json.data.listAssignments[0];
  //         const itemSession = json.data.listSessions[0];
  //         const result = arr.find((item)=> item.sessionId === itemSession.id)
  //         if(result) {
  //           setMaterials(result.listMaterial)
  //           setQuizzs(result.listQuizz)
  //         }

  //       }
  //     }
  //   });
  }
  // const getSessions = (id) => {
  //   if(!id){
  //     return;
  //   }
  //   const options = {
  //     method: 'GET',
  //   };
  //   const url = `/session?lesson_id=${id}`
  //   callApi(url, options).then(json => {
  //     if (json && json.data.data) {
  //       console.log("session", json.data.data);
  //       setSessions(json.data.data);
  //       if(json.data.data.length > 0) {
  //         const item = json.data.data[0]
  //         setSessionItem(item)
  //         getMaterialAndQuizz(item.id)
  //       }else{
  //         setSessionItem([])
  //         setMaterials([])
  //         setSlides([])
  //       }
  //     }
  //   });
  // }

  // const getMaterialAndQuizz = (id) => {
  //   if(!id){
  //     return;
  //   }
  //   const options = {
  //     method: 'GET',
  //   };
  //   const url = `/session/material-quizz?session_id=${id}`
  //   callApi(url, options).then(json => {
  //     if (json && json.data.data) {
  //       setMaterials(json.data.data.materials);
  //       setQuizzs(json.data.data.quizzs);
  //       setSlides([])
  //     }
  //   });
  // }

  // const getMaterials = (id) => {
  //   if(!id){
  //     return;
  //   }
  //   const options = {
  //     method: 'GET',
  //   };
  //   const url = `/material?session_id=${id}`
  //   callApi(url, options).then(json => {
  //     if (json && json.data.data) {
  //       setMaterials(json.data.data);
  //       setSlides([])
  //     }
  //   });
  // }

  const getSlides = (id) => {
    if(!id){
      return;
    }
    const options = {
      method: 'GET',
    };
    const url = `/room/slide/${id}`
    callApi(url, options).then(json => {
      console.log("json.data.data", json.data.data);
      if (json && json.data.data) {
        setSlides(json.data.data);
      }
    });
  }
  // const getAssignments = (id) => {
  //   if(!id){
  //     return;
  //   }
  //   const options = {
  //     method: 'GET',
  //   };
  //   const url = `/assignment?lesson_id=${id}`
  //   callApi(url, options).then(json => {
  //     if (json && json.data.data) {
  //       console.log("assignment", json.data.data);
  //       setAssignments(json.data.data);
  //       if(json.data.data.length > 0) {
  //         const item = json.data.data[0]
  //         setAssignmentItem(item)
  //       }else{
  //         setAssignmentItem([])
  //       }
  //     }
  //   });
  // }
    return (
      <div className="home-screen-wrapper">
          <Header currentLesson={currentLesson} currentCourse={currentCourse} lessons={lessons}/>
          <Content
            coures={coures} lessons={lessons}
            sessions={sessions} materials={materials}
            slides={slides} assignments={assignments}
            quizzs={quizzs}
          />
          <QuizzModal />
      </div>
    )
}
const mapStateToProps = (state, ownProps) => {
  const { course : { currentCourse },
   lesson: { currentLesson },
   session: { currentSession },
   material: { currentMaterial },
  }= state;
  return {
    currentCourse,
    currentLesson,
    currentSession,
    currentMaterial,
  }
}
const mapDispatchToProps = (dispatch, ownProps) => ({
  onTest: () => dispatch(fetch()),
  setCourseItem: (item)=>dispatch(setCourseItem(item)),
  setLessonItem: (item)=>dispatch(setLessonItem(item)),
  setSessionItem: (item)=>dispatch(setSessionItem(item)),
  setAssignmentItem: (item)=>dispatch(setAssignmentItem(item)),
  getLession: ()=>dispatch(getLession()),
})
export default connect(mapStateToProps, mapDispatchToProps)(Home)
