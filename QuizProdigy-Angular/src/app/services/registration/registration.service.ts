import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class RegistrationService {
  constructor(private http: HttpClient) {}
  

  registerUser(userData: any, role: any) {
    console.log('Come inside RegisterService =>', userData);


    if (role === 'Student') {
      const studentData = {
        studentName: userData.name,
        studentId: userData.email,
        contactNo: userData.contactNumber,
        instituteName: userData.instituteName,
        department: userData.department,
        enrollment: userData.enrollment,
        password: userData.password
      };
      console.log("Student Data=>", studentData);
      return this.http.post('http://localhost:8080/register-student', studentData);
    } else {
      const teacherData = {      
        teacherName: userData.name,
        teacherId: userData.email,
        department: userData.department,
        contactNo: userData.contactNumber,

        instituteName: userData.instituteName,
        employeeNo: userData.employeeNo,
        password: userData.password
      };
      console.log("TeacherData=>",teacherData)
      return this.http.post(`http://localhost:8080/register-teacher`, teacherData);
    }
    
  }
}
