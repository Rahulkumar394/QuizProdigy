import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { TeacherService } from '../../../../../services/teacher/teacher.service';
import { MatTableDataSource } from '@angular/material/table';
import Swal from 'sweetalert2';

export interface studentInfo {
  sno: number;
  studentName: string;
  department: String;
  enrollment: String;
  contactNo: String;
  studentId: string;
}

@Component({
  selector: 'app-student-info',
  templateUrl: './student-info.component.html',
  styleUrl: './student-info.component.css'
})
export class StudentInfoComponent implements OnInit {

  studentList: studentInfo[] = [];

  displayedColumns: string[] = [
    'sno',
    'studentName',
    'department',
    'enrollment',
    'contactNo',
    'studentId',
  ];
  dataSource: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private teacherService: TeacherService) {}

  ngOnInit(): void {
    this.teacherService.getAllAcceptedStudent().subscribe(
      (response: any) => {
        if (response && response.length > 0) {
          console.log('response==', response);

          // Data found, process and display it
          this.studentList = response.map((item: any, index: number) => ({
            sno: index + 1, // Assigns a sequential number to each student
            studentName: item.studentName,
            department: item.department,
            enrollment: item.enrollment,
            contactNo: item.contactNo,
            studentId: item.studentId,
          }));
          this.dataSource = new MatTableDataSource<studentInfo>(
            this.studentList
          );
        } else {
          // No data found, handle this case (e.g., show a message)
          Swal.fire({
            title: 'No Data Found',
            text: 'No student data found.',
            icon: 'info',
            confirmButtonText: 'OK',
          });
        }
      },
      (error: any) => {
        // Handle errors
        Swal.fire({
          title: 'Error Occurred',
          text: 'Error occurred while fetching data.',
          icon: 'error',
          confirmButtonText: 'OK',
        });
      }
    );
  }
}
