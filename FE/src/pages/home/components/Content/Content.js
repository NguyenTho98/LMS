import React, { useState, useEffect } from 'react'
import { connect } from 'react-redux';
import ContentAction from './ContentAction/ContentAction';
import ContentNote from './ContentNote/ContentNote';
import ContentVideoSlide from './ContentVideoSlide/ContentVideoSlide';
import './styles.scss';
function Content(props){
    const { coures,
      lessons,
      sessions,
      materials,
      slides,
      assignments,
      quizzs
   } = props;
    const [currentSlide, setCurrentSlide] = useState(slides.length> 0 ? slides[0] : {})
    useEffect(() => {
      setCurrentSlide(slides[0])
    }, [slides])
    const nextSilde = () => {
      let tm = 0;
      slides.forEach((item, index)=>{
        if(item.id === currentSlide.id){
          tm = index + 1;
        }
      })
      slides.forEach((item, index)=>{
        if(tm === index){
         setCurrentSlide(item)
        }
      })
    }
    const backSilde = () => {
      let tm = 0;
      slides.forEach((item, index)=>{
        if(item.id === currentSlide.id){
          tm = index - 1;
        }
      })
      slides.forEach((item, index)=>{
        if(tm === index){
         setCurrentSlide(item)
        }
      })
    }
    return (
      <div className="d-flex home-content-screen-wrapper">
        <div className="content-action">
          <ContentAction
            coures={coures}
            lessons={lessons}
            sessions={sessions}
            materials={materials}
            slides={slides}
            assignments={assignments}
            quizzs={quizzs}
          />
        </div>
        <div className="content-video-slide">
          <ContentVideoSlide
            currentSlide={currentSlide}
            nextSilde={nextSilde}
            backSilde={backSilde}
            slides={slides}
          />
        </div>
        <div className="content-note">
          <ContentNote/>
        </div>
      </div>
    )
}

export default connect(null, null)(Content)
