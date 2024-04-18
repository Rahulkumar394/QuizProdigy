import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ExamInstructionsComponent } from '../exam-instructions/exam-instructions.component';
import { Router } from '@angular/router';
import { GetexamcodeComponent } from '../getexamcode/getexamcode.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  constructor(public dialog: MatDialog,private router:Router) {}
  ngOnInit(): void {
    this.openDialog();
  }

  openDialog() {
     const dialogRef = this.dialog.open(ExamInstructionsComponent);
     //const dialogRef = this.dialog.open(GetexamcodeComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      if(`${result}`){
         this.router.navigateByUrl('/student/takeexam');
           
      }else{
        return;
      }
    });
  }
}
