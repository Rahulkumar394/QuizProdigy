import { Component } from '@angular/core';




export interface PeriodicElement{
  teacherName: string;
  department: String;  
  contactNo: String;
  email: string;
  sno: number;
}

const TeacherList: PeriodicElement[] = [
  {sno: 1, teacherName: 'Rahul Kumar', department: 'CSE', contactNo: '6205187001' , email:'rahulkumarcse@gmail.com'},
  {sno: 2, teacherName: 'Rahul Kumar', department: 'CSE', contactNo: '6205187001' , email:'rahulkumarcse@gmail.com'},
  {sno: 3, teacherName: 'Rahul Kumar', department: 'CSE', contactNo: '6205187001' , email:'rahulkumarcse@gmail.com'},
  {sno: 4, teacherName: 'Rahul Kumar', department: 'CSE', contactNo: '6205187001' , email:'rahulkumarcse@gmail.com'},
  {sno: 5, teacherName: 'Rahul Kumar', department: 'CSE', contactNo: '6205187001' , email:'rahulkumarcse@gmail.com'},
  {sno: 6, teacherName: 'Rahul Kumar', department: 'CSE', contactNo: '6205187001' , email:'rahulkumarcse@gmail.com'},
];

@Component({
  selector: 'app-manage-students',
  templateUrl: './manage-students.component.html',
  styleUrl: './manage-students.component.css'
})
export class ManageStudentsComponent {

displayedColumns: string[] = ['sno', 'teacherName', 'department', 'contactNo', 'email'];
dataSource = TeacherList;

}
