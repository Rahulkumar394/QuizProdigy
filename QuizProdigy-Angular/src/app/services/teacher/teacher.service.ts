import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cookie } from 'ng2-cookies';

@Injectable({
  providedIn: 'root',
})
export class TeacherService {
  constructor(private http: HttpClient) {}

  // through this service method we call the API to set a Exam and it's questions,options and answer
  setExamWithQuestion(exam: any, questionList: any) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    const obj = {
      exam: exam,
      questions: questionList,
    };
    return this.http.post(
      'http://localhost:8080/teacher/set-exam-question',     obj,      { headers }
    );
  }

  //  get all examIds of teacher by calling /teacher/all-exams API
  getAllExamIdByTeacherId() {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    const teacherId = Cookie.get('userId');
    return this.http.get(
      'http://localhost:8080/teacher/get-allexamid-by-teacherid/' + teacherId,
      { headers }
    );
  }

  //  retrieve an exam  and questions, options and answer by its ExamId using /teacher/exam-question/examId API
  getExamQuestionByExamId(examId: any) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    return this.http.get(
      'http://localhost:8080/teacher/exam-question/' + examId,
      { headers }
    );
  }

  // Delete Exam by its ExamId using /teacher/delete-exam/examId API
  deleteExamByExamId(examId: any) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    return this.http.get(
      'http://localhost:8080/teacher/delete-exam/' + examId,
      { headers }
    );
  }

  //
  //  update exam by its ExamId using /teacher/update-exam-question/examId API
  updateExamByExam(updateExam: any, updatedQuestions: any) {
    console.log('teaserservice updateservice');
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    const obj = {
      exam: updateExam,
      questions: updatedQuestions,
    };
    return this.http.post(
      'http://localhost:8080/teacher/update-exam-question',
      obj,
      { headers }
    );
  }

  // getting all the students who have status is pending
  getAllRequestStudent() {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    return this.http.get('http://localhost:8080/teacher/pending-student', {
      headers,
    });
  }
   // Through thie fn we get all Accepted Request
   getAllAcceptedStudent() {
    const headers=new HttpHeaders().set('Authorization','Bearer '+Cookie.get('token'));
    console.log("Getting teacher with headers", headers);
    return this.http.get('http://localhost:8080/teacher/accepted-student',{headers});
  }
    // through this fn we approve student by studentid
    approveStudent(studentId:String){
      const headers=new HttpHeaders().set('Authorization','Bearer '+Cookie.get('token'));
      return this.http.get('http://localhost:8080/teacher/approve-request/'+studentId,{headers});
    }                 

    // through this fn we reject student by studentid
    rejectStudent(studentId:String){
      // /approve-request/{email}
      const headers=new HttpHeaders().set('Authorization','Bearer '+Cookie.get('token'));
      return this.http.get('http://localhost:8080/teacher/reject-request/'+studentId,{headers});
    }
}
