import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cookie } from 'ng2-cookies';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(private http: HttpClient) {}

  // Through thie fn we get all pending Request
  getAllRequestTeacher() {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    console.log('Getting teacher with headers', headers);
    return this.http.get('http://localhost:8080/admin/pending-teacher', {
      headers,
    });
  }

  // Through thie fn we get all Accepted Request
  getAllAcceptedTeacher() {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    console.log('Getting teacher with headers', headers);
    return this.http.get('http://localhost:8080/admin/accepted-teacher', {
      headers,
    });
  }

  // through this fn we approve teacher by teacherId
  approveTeacher(teacherId: String) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    console.log('Getting teacher with headers', headers, teacherId);
    return this.http.get(
      'http://localhost:8080/admin/approve-request/' + teacherId,
      { headers }
    );
  }

  // through this fn we reject teacher by teacherId
  rejectTeacher(teacherId: String) {
    // /approve-request/{email}
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    console.log('Getting teacher with headers', headers, teacherId);
    return this.http.get(
      'http://localhost:8080/admin/reject-request/' + teacherId,
      { headers }
    );
  }

  getStudents() {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    console.log('Getting students with headers', headers);
    return this.http.get('http://localhost:8080/admin/accepted-student', {
      headers,
    });
  }

  getExamList() {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    console.log('Getting students with headers', headers);
    return this.http.get('http://localhost:8080/admin/examInfo', { headers });
  }

  examSchedule(date: string, time: string) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );
    console.log("Getting Date and time",date,time);
    const body = {
      examDate: date,
      examTime: time,
      examId: localStorage.getItem('examId'),
    };
    return this.http.post(
      'http://localhost:8080/admin/set-exam-timetable ',
      body,
      { headers }
    );
  }
}
