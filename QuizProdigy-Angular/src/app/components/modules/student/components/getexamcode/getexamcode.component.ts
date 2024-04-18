import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { StudentService } from '../../../../../services/student/student.service';
import { error } from 'console';

@Component({
  selector: 'app-getexamcode',
  templateUrl: './getexamcode.component.html',
  styleUrl: './getexamcode.component.css',
})
export class GetexamcodeComponent {
  constructor(private router: Router, private studentService: StudentService) {}

  examCode: string = '';
  isExamCodeFilled: boolean = false;

  updateExamCodeFilled(): void {
    // this.isExamCodeFilled = !!this.examCode.trim();
    this.isExamCodeFilled = this.examCode.trim() !== '';
  }
  getExam() {
    if (this.examCode.trim() == '') {
      Swal.fire({
        icon: 'info',
        title: 'Oops...',
        text: 'Please enter exam code!',
      });
    } else {
      console.log('Calling service...');
      this.studentService.getExam(this.examCode).subscribe(
        (response: any) => {
          console.log('Response Exam set in localstroage=>', response);
          localStorage.setItem('totalQuestion',response.totalQuestions);
          localStorage.setItem('totalTime',response.totalTime);
          localStorage.setItem('examId',response.examId);
          this.router.navigateByUrl('/student/examInstruction');
        },
        (error: any) => {
          console.log('ERROR exam set hone mai aa raha ahi', error.status);
          Swal.fire({
            icon: 'error',
            text: 'Exam code is invalid!',
          });
        }
      );
    }
  }
}
