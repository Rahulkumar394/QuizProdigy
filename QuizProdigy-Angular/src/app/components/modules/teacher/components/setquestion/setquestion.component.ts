import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import Swal from 'sweetalert2';
import { TeacherService } from '../../../../../services/teacher/teacher.service';
import { error } from 'console';
import { Cookie } from 'ng2-cookies';
// interface request {
//   questions: any[];
//   exam: any;
// }
@Component({
  selector: 'app-setquestion',
  templateUrl: './setquestion.component.html',
  styleUrl: './setquestion.component.css',
})
export class SetquestionComponent implements OnInit {
  showPreview!: FormGroup<any>;

  constructor(private teacherService: TeacherService) {

  }

  // setExam!: FormGroup<any>;
  setquestion!: FormGroup<any>;
  // This is used to move to the next step in the stepper
  @ViewChild('stepper') stepper!: MatStepper;

  // Setting formgroup for exam details step No 1
  setExam = new FormGroup({
    subjectName: new FormControl('', Validators.required),
    totalQuestions: new FormControl('', Validators.required),
    totalTime: new FormControl('', Validators.required),
    handsOn: new FormControl('', Validators.required),
    teacherId: new FormControl(Cookie.get('userId')),
  });

  // Here we declare members  that will be used in the HTML part of our component
  totalQuestions!: any;
  remaningQuestion: any;
  currentQuestionIndex: any = 0; // Track the current question index

  // This is questionList  which contains all questions for a test series
  // It have contains 3 fields like question, list of options and list of answer
  questionList: any[] = [];

  ngOnInit(): void {
    this.setquestion = new FormGroup({
      question: new FormControl('', Validators.required),
      optionA: new FormControl('', Validators.required),
      optionB: new FormControl('', Validators.required),
      optionC: new FormControl('', Validators.required),
      optionD: new FormControl('', Validators.required),
      answer: new FormControl('', Validators.required),
    });

    this.setExam.value.teacherId=Cookie.get('userId');
  }
  addExamObject!: any;

  setExamObject() {
    console.log('Inside setExamObject', this.setExam.value);
    if (this.setExam.valid) {
      this.totalQuestions = this.setExam.value.totalQuestions;
      this.remaningQuestion = this.totalQuestions;
      this.addExamObject = Object.assign({}, this.setExam.value);
      //Validate Feilds
      let message = this.validateExamDetails(this.addExamObject);
      if (message != '') {
        Swal.fire({
          icon: 'info',
          title: message,
          showConfirmButton: true,
        });
        return;
      } else {
        this.addExamObject.teacherId = Cookie.get('userId');
        // Call stepper.next() to move to the next step programmatically
        this.stepper.next();
      }
    } else {
      Swal.fire({
        icon: 'info',
        title: 'Please fill out all required fields.',
        showConfirmButton: true,
        // timer: 2500,
      });
    }
  }

  // This method call when user click on next button through this method we can add question in qusetionList
  addQuestion() {
    if (
      this.setquestion.valid &&
      this.currentQuestionIndex == this.questionList.length
    ) {
      // curent question increment by 1
      ++this.currentQuestionIndex;

      // remaning question decrement by 1
      --this.remaningQuestion;

      this.questionList.push({
        //getting question from setquestion and push into  questionlist array
        question: this.setquestion.value.question,

        // getting option from setquestion and push in options array after that options array push inside questionlist aaray
        options: [
          this.setquestion.value.optionA,
          this.setquestion.value.optionB,
          this.setquestion.value.optionC,
          this.setquestion.value.optionD,
        ],

        // getting answer from setquestion and push in answer array after that options array push inside questionlist aaray
        answer: [this.setquestion.value.answer],
      });

      if (this.remaningQuestion == 0) {
        // Swal.fire({
        //   icon: 'success',
        //   title: 'All Questions are added Successfully!',
        //   showConfirmButton: true,
        //   // timer: 2500,
        // });
        Swal.fire({
          title: 'All Questions are added Successfully! ',
          icon: 'success',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Save!',
        }).then((result) => {
          //if confirmation is done then call service or move to preview page
          if (result.isConfirmed) {
            this.stepper.next();
            this.teacherService
              .setExamWithQuestion(this.setExam.value, this.questionList)
              .subscribe(
                (data: any) => {
                  console.log(data);
                },
                (error) => {
                  console.log('error occured');
                }
              );
          }
          //else do nothing
          else {
            return;
          }
        });
        this.remaningQuestion = 0;
      }

      // reset the form after adding a question
      this.setquestion.reset();
    } else if (++this.currentQuestionIndex < this.questionList.length) {
      this.setquestion.patchValue({
        question: this.getcurrentQuestion().question,
        optionA: this.getcurrentQuestion().options[0],
        optionB: this.getcurrentQuestion().options[1],
        optionC: this.getcurrentQuestion().options[2],
        optionD: this.getcurrentQuestion().options[3],
        answer: this.getcurrentQuestion().answer,
      });
    } else if (this.currentQuestionIndex == this.questionList.length) {
      this.setquestion.reset();
    } else {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Please fill out the form!',
      });
    }
    console.log('This is questionList=>', this.questionList);
  }

  showPreviousQuestion() {
    if (this.currentQuestionIndex > 0) {
      --this.currentQuestionIndex;

      // this.setquestion.patchValue( this.getcurrentQuestion());
      this.setquestion.patchValue({
        question: this.getcurrentQuestion().question,
        optionA: this.getcurrentQuestion().options[0],
        optionB: this.getcurrentQuestion().options[1],
        optionC: this.getcurrentQuestion().options[2],
        optionD: this.getcurrentQuestion().options[3],
        answer: this.getcurrentQuestion().answer,
      });
    } else {
      Swal.fire({
        icon: 'info',
        title: 'Info',
        text: "You're on first question",
      });
    }
  }

  getcurrentQuestion() {
    return this.questionList[this.currentQuestionIndex];
  }


  validateExamDetails(data: any) {
    console.log("data=>",data);
    let message = '';
    if (!data.subjectName.trim() || data.subjectName.trim() == '') {
      message = 'Name field cannot be empty.';
      return message;
    } else if (data.totalQuestions <= 0) {
      message = 'Total questions should be greater than zero.';
      return message;
    } else if (data.totalTime < 1) {
      message = 'Duration should be at least one minute i.e., 60 seconds.';
      return message;
    }
    return message;
  }
}
