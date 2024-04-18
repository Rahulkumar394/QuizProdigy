import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import screenfull from 'screenfull';
import { isPlatformBrowser } from '@angular/common';
import { Inject, PLATFORM_ID } from '@angular/core';
import { StudentService } from '../../../../../services/student/student.service';

@Component({
  selector: 'app-do-exam',
  templateUrl: './do-exam.component.html',
  styleUrl: './do-exam.component.css',
})
export class DoExamComponent implements OnInit {
  //Declare variable
  remaningTime: any;
  totalQuestion: any;
  sno:any
  videoRef: any;
  totalTime: number | undefined;
  exam: any;
  question: any;

  constructor(
    private route: Router,
    @Inject(PLATFORM_ID) private platformId: Object,
    private studentService: StudentService
  ) {}

  ngOnInit(): void {   

    if (isPlatformBrowser(this.platformId)) {
      this.videoRef = document.getElementById('video');
      console.log(this.videoRef);
      this.setUpCamera();
    }

    if (screenfull.isEnabled) {
      screenfull.toggle();
    }
    if (screenfull.isEnabled) {
      screenfull.on('change', () => {
        if (screenfull.isFullscreen) {
          console.log('Entered full screen mode');
          // Update UI or perform actions when entering full screen
        } else {
          console.log('Exited full screen mode');
          this.route.navigateByUrl('/student');
          // Update UI or perform actions when exiting full screen
        }
      });
    }

    this.studentService
      .getExamQuestion(localStorage.getItem('examId'))
      .subscribe(
        (response: any) => {
          console.log('Responser to set exam', response);
          this.exam = response.exam;
          this.question = response.questions;
          //set exam Total time
          this.timer(this.exam.totalTime);
          //set exam total question
          this.totalQuestion = this.exam.totalQuestions;
          this.sno =  Array(this.totalQuestion).fill(0);
          console.log('set exam obj ', this.exam);
          console.log('set question obj ', this.question);
          
        },
        (error: any) => {
          console.log('Error in taking exam', error);
          
        }
      );
  }

  setUpCamera() {
    navigator.mediaDevices
      .getUserMedia({
        video: { width: 360, height: 250 },
        audio: false,
      })
      .then((stram) => {
        console.log(stram);
        this.videoRef.srcObject = stram;
      });
  }

  timer(minute: any) {
    // let minute = 1;
    let seconds: number = minute * 60;
    let textSec: any = '0';
    let statSec: number = 60;

    const prefix = minute < 10 ? '0' : '';

    const timer = setInterval(() => {
      seconds--;
      if (statSec != 0) statSec--;
      else statSec = 59;

      if (statSec < 10) {
        textSec = '0' + statSec;
      } else textSec = statSec;

      this.remaningTime = `${prefix}${Math.floor(seconds / 60)}:${textSec}`;

      if (seconds == 0) {
        console.log('finished');
        clearInterval(timer);
      }
    }, 1000);
  }
}
