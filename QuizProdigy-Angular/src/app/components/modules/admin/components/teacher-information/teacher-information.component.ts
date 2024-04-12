import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import Swal from 'sweetalert2';
import { AdminService } from '../../../../../services/admin/admin.service';

export interface teacherInfo {
  sno: number;
  teacherName: string;
  department: String;
  contactNo: String;
  teacherId: string;
}
@Component({
  selector: 'app-teacher-information',
  templateUrl: './teacher-information.component.html',
  styleUrl: './teacher-information.component.css',
})
export class TeacherInformationComponent implements OnInit {
  constructor(
    private adminService: AdminService,
    private route: Router
  ) {}

  teacherList: teacherInfo[] = [];

  displayedColumns: string[] = [
    'sno',
    'teacherName',
    'department',
    'contactNo',
    'teacherId',
  ];
  dataSource: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngOnInit(): void {
   
    this.adminService.getAllAcceptedTeacher().subscribe(
      (response: any) => {
        this.teacherList = response.map((item: any, index: number) => ({
          sno: index + 1, // Assigns a sequential number to each student
          teacherName: item.teacherName,
          department: item.department,
          contactNo: item.contactNo,
          teacherId: item.teacherId,
        }));
        this.dataSource = new MatTableDataSource<teacherInfo>(this.teacherList);
      },
      (error: any) => {
        Swal.fire({
          title: 'Error occured',
          text: 'Error occured while Fetching the Data',
          icon: 'error',
          confirmButtonText: 'Back'
        })
      }
    );
  }
}
