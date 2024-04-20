import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { AdminService } from '../../../../../services/admin/admin.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { FormControl, FormGroup } from '@angular/forms';

export interface examInfo {
  sno: number;
  examId: string;
  examName: string;
  teacherName: string;
}

@Component({
  selector: 'app-set-exam-date',
  templateUrl: './set-exam-date.component.html',
  styleUrl: './set-exam-date.component.css',
})
export class SetExamDateComponent implements OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  examList: examInfo[] = [];
  minDate: string; // Set min date for input date
  minTime: string; // Set min time for input time
  maxTime: string; // Set max time for input time
  
  constructor(private adminService: AdminService, private route: Router) {
    // Initialize min date and time values
    const today = new Date();
    this.minDate = today.toISOString().split('T')[0]; // Today's date in YYYY-MM-DD format
    this.minTime = '00:00'; // Minimum time (e.g., 12:00 AM)
    this.maxTime = '12:00'; // Maximum time (e.g., 12:00 PM)
  }

  displayedColumns: string[] = [
    'sno',
    'examId',
    'examName',
    'teacherName',
    'schedule',
  ];

  dataSource = new MatTableDataSource<examInfo>(this.examList);

  ngOnInit(): void {
    this.adminService.getExamList().subscribe(
      (data: any) => {
        this.examList = data.map((item: any, index: number) => ({
          sno: index + 1, // Assigns a sequential number to each student
          examId: item.examId,
          examName: item.examName,
          teacherName: item.teacherName,
        }));

        this.dataSource = new MatTableDataSource<examInfo>(this.examList);
      },
      (error: any) => {
        console.log('Error message=>', error);
        Swal.fire({
          title: 'Error Occured',
          text: 'Error occured while Fetching Data',
          icon: 'error',
          confirmButtonText: 'Back',
        });
      }
    );
  }

  scheduleExam(examId: string) {
    console.log('Exami id', examId);
    localStorage.setItem('examId',examId);
    this.route.navigateByUrl('admin/setExam');
  }
}
