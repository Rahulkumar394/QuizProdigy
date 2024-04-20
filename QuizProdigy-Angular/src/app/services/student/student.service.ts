import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cookie } from 'ng2-cookies';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  answerMap: any;
  constructor(private http: HttpClient) {}



  getExam(examId: any) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    return this.http.get('http://localhost:8080/student/getexam/'+examId, { headers });
  }
  getExamQuestion(examId: any) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    return this.http.get('http://localhost:8080/student/take-exam/'+examId, { headers });
  }

  submitExam(answer:any){
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );   
    const obj={
      studentId:Cookie.get('userId'),
      examId:localStorage.getItem('examId'),
      answerList:answer
    };

    console.log("PRINT answer Inside submitExam student Service OBJECT+",obj);
    return this.http.post('http://localhost:8080/student/submitExam', obj , { headers });
  }
}
