import { combineReducers } from 'redux';
import globalUI from './globalUI';
import course from './course';
import lesson from './lesson';
import session from './session';
import material from './material';
import assignment from './assignment';
import quizz from './quizz';
export default combineReducers({
  course,
  globalUI,
  lesson,
  session,
  material,
  assignment,
  quizz,
})
