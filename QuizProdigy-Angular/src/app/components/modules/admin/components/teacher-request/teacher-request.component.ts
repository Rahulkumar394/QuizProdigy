import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import Swal from 'sweetalert2';
import { Router, RouterLink } from '@angular/router';
import { AdminService } from '../../../../../services/admin/admin.service';

export interface teacherInfo {
  sno: number;
  teacherName: string;
  department: String;
  contactNo: String;
  teacherId: string;
}

@Component({
  selector: 'app-teacher-request',
  templateUrl: './teacher-request.component.html',
  styleUrl: './teacher-request.component.css',
})
export class TeacherRequestComponent implements OnInit {
  constructor(private adminService:AdminService,private route:Router) {}
  teacherList: teacherInfo[] = [];

  displayedColumns: string[] = [
    'sno',
    'teacherName',
    'department',
    'contactNo',
    'teacherId',
    'approve',
    'reject',
  ];
  dataSource: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngOnInit(): void {
    
    this.adminService.getAllRequestTeacher().subscribe(
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
          title: 'Error Occured',
          text: 'Error occured while Fetching Data',
          icon: 'error',
          confirmButtonText: 'Back'
        }).then(result=>{
          window.location.reload();
      })
      }
    );
  }

  // Calling service for approved Teacher
  approveTeacher(teacherId: string) {
    console.log('Click on Visiblity', teacherId);
    //localStorage.setItem('approveTeacherId', teacherId);
    this.adminService.approveTeacher(teacherId).subscribe(
      (response: any) => {
        Swal.fire({
          title: 'Approved Successfully',
          text: 'The teacher Approved',
          icon: 'success',
          confirmButtonText: 'Okay'
        }).then(result=>{
            window.location.reload();
        })
      },
      // error while approving
      (error: any) => {
        Swal.fire({
          title: 'Unable to process',
          text: 'Error occured while Aprroving',
          icon: 'error',
          confirmButtonText: 'Back'
        }).then(result=>{
          window.location.reload();
      })
      }
    );

  }

// Calling service for approved Teacher
  rejectTeacher(teacherId: string) {
    console.log('Click on Visiblity', teacherId);
    //localStorage.setItem('rejectTeacherId', teacherId);
    this.adminService.rejectTeacher(teacherId).subscribe(
      (response: any) => {
        Swal.fire({
          title: 'Reject Successfully',
          text: 'The teacher is Rejected',
          icon: 'success',
          confirmButtonText: 'Okay'
        }).then(result=>{
          window.location.reload();
      }) 
      },
      (error: any) => {
        Swal.fire({
          title: 'Unable to process',
          text: 'Error occured while Rejecting',
          icon: 'error',
          confirmButtonText: 'Back'
        }).then(result=>{
          window.location.reload();
      })
      }
    );
  }
}
