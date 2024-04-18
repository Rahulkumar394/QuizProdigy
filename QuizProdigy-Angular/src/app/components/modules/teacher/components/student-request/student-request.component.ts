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
  selector: 'app-student-request',
  templateUrl: './student-request.component.html',
  styleUrl: './student-request.component.css',
})
export class StudentRequestComponent implements OnInit {
  studentList: studentInfo[] = [];

  displayedColumns: string[] = [
    'sno',
    'studentName',
    'department',
    'enrollment',
    'contactNo',
    'studentId',
    'approve',
    'reject',
  ];
  dataSource: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private teacherService: TeacherService) {}

  ngOnInit(): void {
    this.teacherService.getAllRequestStudent().subscribe(
      (response: any) => {
        if (response && response.length > 0) {
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

  rejectStudent(studentId: any) {
    console.log('Click on Visiblity', studentId);
    //localStorage.setItem('approveTeacherId', teacherId);
    this.teacherService.rejectStudent(studentId).subscribe(
      (response: any) => {
        Swal.fire({
          title: 'Rejected Successfully',
          text: 'The Student Rejected',
          icon: 'success',
          confirmButtonText: 'Okay',
        }).then((result) => {
          window.location.reload();
        });
      },
      // error while approving
      (error: any) => {
        Swal.fire({
          title: 'Unable to process',
          text: 'Error occured while Rejecting',
          icon: 'error',
          confirmButtonText: 'Back',
        }).then((result) => {
          window.location.reload();
        });
      }
    );
  }
  approveStudent(studentId: any) {
    console.log('Click on Visiblity', studentId);
    //localStorage.setItem('approveTeacherId', teacherId);
    this.teacherService.approveStudent(studentId).subscribe(
      (response: any) => {
        Swal.fire({
          title: 'Approved Successfully',
          text: 'The Student Approved',
          icon: 'success',
          confirmButtonText: 'Okay',
        }).then((result) => {
          window.location.reload();
        });
      },
      // error while approving
      (error: any) => {
        Swal.fire({
          title: 'Unable to process',
          text: 'Error occured while Aprroving',
          icon: 'error',
          confirmButtonText: 'Back',
        }).then((result) => {
          window.location.reload();
        });
      }
    );
  }
}
