import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { AdminService } from '../../../../../services/admin/admin.service';

export interface studentInfo {
  sno: number;
  studentName: string;
  department: String;
  contactNo: String;
  studentId: string;
}

@Component({
  selector: 'app-manage-students',
  templateUrl: './manage-students.component.html',
  styleUrl: './manage-students.component.css',
})
export class ManageStudentsComponent implements OnInit {
  studentList: studentInfo[] = [];
  constructor(private adminService: AdminService) {}

  displayedColumns: string[] = [
    'sno',
    'studentName',
    'department',
    'contactNo',
    'email',
    'action',
  ];
  dataSource :any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngOnInit(): void {
    this.adminService.getStudents().subscribe(
      (data: any) => {
        this.studentList = data.map((item: any, index: number) => ({
          sno: index + 1, // Assigns a sequential number to each student
          studentName: item.studentName,
          department: item.department,
          contactNo: item.contactNo,
          studentId: item.studentId,
        }));
        this.dataSource = new MatTableDataSource<studentInfo>(this.studentList)
      },
      (error: any) => {
        console.log('Inside manage Student error', error);
      }
    );
  }

  getStudents(studentId:string) {
    console.log('Click on Visiblity',studentId);
    localStorage.setItem('selectedStudentId', studentId);
  }
}
