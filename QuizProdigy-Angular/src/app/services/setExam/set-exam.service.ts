import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cookie } from 'ng2-cookies';

@Injectable({
  providedIn: 'root',
})
export class SetExamService {
  constructor(private http: HttpClient) {}

  setExamDetails(data: any) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + Cookie.get('token')
    );

    return this.http.post('http://localhost:8080/teacher/setexam', data, {
      headers,
    });
  }
}
