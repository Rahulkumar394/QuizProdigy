import { Component, OnInit, ViewChild } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { TeacherService } from '../../../../../services/teacher/teacher.service';
import Swal from 'sweetalert2';
import { MatStepper } from '@angular/material/stepper';

@Component({
  selector: 'app-view-question',
  templateUrl: './view-question.component.html',
  styleUrl: './view-question.component.css',
})
export class ViewQuestionComponent implements OnInit {
  @ViewChild('stepper') stepper!: MatStepper; // This is used to move to the next step in the stepper

  isLinear: unknown;
  firstFormGroup: FormGroup;
  setExam: FormGroup; // Define setExam as a FormGroup secondFormGroup: FormGroup;
  setquestion!: FormGroup; // 3rdFormGroup: FormGroup;
  exam: any;
  examIds: any;
  question: any;
  questions: any[] = [];
  totalQuestions: any;
  currentQuestionIndex: any = 0;

  constructor(
    private formBuilder: FormBuilder,
    private teacherService: TeacherService
  ) {
    // Initialize the firstFormGroup form controls
    this.firstFormGroup = this.formBuilder.group({
      examId: ['', Validators.required], // Add 'examId' control here
    });

    // Initilize setExam form controls
    this.setExam = this.formBuilder.group({
      subjectName: ['', Validators.required],
      totalQuestions: ['', Validators.required],
      totalTime: ['', Validators.required],
      handsOn: ['false'], // Set default value for handsOn radio button
    });

    // intitlize setquestion form controls
    this.setquestion = new FormGroup({
      question: new FormControl('', Validators.required),
      optionA: new FormControl('', Validators.required),
      optionB: new FormControl('', Validators.required),
      optionC: new FormControl('', Validators.required),
      optionD: new FormControl('', Validators.required),
      answer: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {
    // Get all available exams for the current user and add them into the select options of "examId" field
    this.teacherService.getAllExamIdByTeacherId().subscribe(
      (response: any) => {
        this.examIds = response; // Assuming your API response contains examIds as an array
        console.log('Exam Ids : ', this.examIds);
      },
      (error: any) => {
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Error while fetcing the question' + error,
        });
      }
    );
    //-------------------------------------------------------------------------------
  }

  selectExamId() {
    const examId = this.firstFormGroup.get('examId')?.value;
    if (!examId) {
      Swal.fire({
        icon: 'warning',
        title: 'warining!',
        text: 'No exam ID selected',
      });
      return;
    } else {
      //here we call service method which will get exam and question data from server using the selected exam  id
      this.teacherService.getExamQuestionByExamId(examId).subscribe(
        (res: any) => {
          console.log('Response from backend=>', res);
          this.exam = res.exam;
          this.questions = res.questions;

          if (this.exam) {
            this.setExam.setValue({
              subjectName: this.exam.subjectName,
              totalQuestions: this.exam.totalQuestions,
              totalTime: this.exam.totalTime,
              handsOn: this.exam.isHandsOn ? 'true' : 'false',
            });
            this.totalQuestions = this.exam.totalQuestions;
          }
        },
        (error: any) => {
          console.log('Error to fetch exam question=>', error);
        }
      );
    }
    this.stepper.next();
  }

  viewQuestion() {
    this.stepper.next();
    console.log("Questions format=>",this.questions);
    if (
      this.questions &&
      this.questions.length > 0 &&
      this.currentQuestionIndex < this.questions.length
    ) {
      this.totalQuestions = this.questions.length;
      const currentQuestion = this.questions[this.currentQuestionIndex];
      
      if (currentQuestion && currentQuestion.question) {
        console.log(currentQuestion,"Current WQuestiom")
        this.setquestion.patchValue({
          question: currentQuestion.question.value,
          optionA: currentQuestion.options[0].value,
          optionB: currentQuestion.options[1].value,
          optionC: currentQuestion.options[2].value,
          optionD: currentQuestion.options[3].value,
          answer: currentQuestion.answers[0].value,
        });
        this.currentQuestionIndex++; // Move to the next question for the next button click
      } else {
        console.error('Invalid question data:', currentQuestion);
      }
    } else {
      console.error('No questions available or index out of bounds.');
    }
  }
}
