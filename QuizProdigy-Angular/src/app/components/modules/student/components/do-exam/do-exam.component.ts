import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import screenfull from 'screenfull';
import { isPlatformBrowser } from '@angular/common';
import { Inject, PLATFORM_ID } from '@angular/core';
import { StudentService } from '../../../../../services/student/student.service';
import { Cookie } from 'ng2-cookies';

@Component({
  selector: 'app-do-exam',
  templateUrl: './do-exam.component.html',
  styleUrl: './do-exam.component.css',
})
export class DoExamComponent implements OnInit {
  //Declare variable
  remaningTime: any;
  totalQuestion: any;
  sno: any;
  videoRef: any;
  totalTime: number | undefined;
  exam: any;
  questions: any[] = [];
  question: any;
  currentQuestionIndex: number = 0; // Track current question index
  answerList:any[]=[];

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

    this.studentService.getExamQuestion(localStorage.getItem('examId'))
      .subscribe(
        (response: any) => {
          console.log('Responser to set exam', response);
          this.exam = response.exam;
          this.questions = response.questions;
          //set exam Total time
          this.timer(this.exam.totalTime);
          //set exam total question
          this.totalQuestion = this.exam.totalQuestions;
          this.sno = Array(this.totalQuestion).fill(0);
          this.loadQuestion(this.currentQuestionIndex);
          console.log('set exam obj ', this.exam);
          console.log('set question obj ', this.questions);
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

  // Load a specific question by index
  loadQuestion(index: number) {
    this.question = this.questions[index];
    console.log('load question value is=>', this.question.question.value);
  }

  // Go to the previous question
  previousQuestion() {
    if (this.currentQuestionIndex > 0) {
      this.currentQuestionIndex--;
      this.loadQuestion(this.currentQuestionIndex);
    }
  }

  // Go to the next question
  nextQuestion() {
    if (this.currentQuestionIndex < this.totalQuestion - 1) {
      this.currentQuestionIndex++;
      this.loadQuestion(this.currentQuestionIndex);
    }
  }

  // through this fn we navigate particular question
  naviagteQuestion(qno: number) {
    this.currentQuestionIndex = qno - 1;
    this.loadQuestion(this.currentQuestionIndex);
  }

  // answer save inside answerMap
  getAnswer(correctOption: string, questionId: string) {
   this.answerList.push({
    id:questionId,
    value:correctOption
   })
  }

  //Submit exam
  doSubitExam() {
    console.log("AnswerList ",this.answerList);

    // calling  service 
    this.studentService.submitExam(this.answerList).subscribe(
      (response:any)=>{
        console.log(response);
      },(error:any)=>{
        console.log(error);
      }
    )

  }
}
