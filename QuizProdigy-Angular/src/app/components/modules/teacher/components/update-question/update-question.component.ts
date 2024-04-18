import { Component, OnInit, ViewChild } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { TeacherService } from '../../../../../services/teacher/teacher.service';
import Swal from 'sweetalert2';
import { Cookie } from 'ng2-cookies';

@Component({
  selector: 'app-update-question',
  templateUrl: './update-question.component.html',
  styleUrl: './update-question.component.css',
})
export class UpdateQuestionComponent implements OnInit {
  @ViewChild('stepper') stepper!: MatStepper; // This is used to move to the next step in the stepper
  firstFormGroup: FormGroup;
  isLinear = false;
  setExam: FormGroup; // Define setExam as a FormGroup secondFormGroup: FormGroup;
  setquestion!: FormGroup<any>; // 3rdFormGroup: FormGroup;
  examIds: any; // Initialize with an empty array
  exam: any; //Store the exam details
  questions: any; //Store the questions
  totalQuestions!: any; // through this we can get the total number of questions
  currentQuestionIndex: any = 0; // Track the current question index
  //to store update exam details
  updateExamDetails: any;
  updateQuestionList: any[] = [];

  constructor(
    private _formBuilder: FormBuilder,
    private teacherService: TeacherService
  ) {
    // Initialize the firstFormGroup form controls
    this.firstFormGroup = this._formBuilder.group({
      examId: ['', Validators.required], // Add 'examId' control here
    });

    // Initilize setExam form controls
    this.setExam = this._formBuilder.group({
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
      (error) => {
        console.log('Error', error);
      }
    );
    //-------------------------------------------------------------------------------
  }

  selectExamId() {
    const examId = this.firstFormGroup.get('examId')?.value;
    // Write code for set Exam  function call here
    if (!examId) {
      Swal.fire({
        icon: 'warning',
        title: 'Warning!',
        text: 'No exam ID selected!',
      });
      return;
    } else {
      //here we call service method which will get exam and question data from server using the selected exam  id
      this.teacherService.getExamQuestionByExamId(examId).subscribe(
        (res: any) => {
          this.exam = res.exam;
          // const exam = res.exam;
          //Call the set exam method
          this.setExamObject();
          this.questions = res.questions;
        },
        (error: any) => {
          console.log('Error to fetch exam question=>', error);
        }
      );
      this.stepper.next();
    }
  }

  setExamObject() {
    if (this.exam) {
      this.setExam.setValue({
        subjectName: this.exam.subjectName,
        totalQuestions: this.exam.totalQuestions,
        totalTime: this.exam.totalTime,
        handsOn: this.exam.isHandsOn ? 'true' : 'false',
      });
    }
  }

  setQuestionObject() {
    if (this.setExam.valid) {
      this.updateExamDetails = Object.assign({}, this.setExam.value);
      this.updateExamDetails.teacherId = Cookie.get('userId');
      this.updateExamDetails.examId = this.exam.examId;
      //Validate Feilds
      let message = this.validateExamDetails(this.updateExamDetails);
      // if there is any error in the form then return
      if (message != '') {
        Swal.fire({
          icon: 'info',
          title: message,
          showConfirmButton: true,
          timer: 6000,
        });
        return;
      } else {
      }
    } else {
      Swal.fire({
        icon: 'info',
        title: 'Please fill out all required fields.',
        showConfirmButton: true,
        // timer: 2500,
      });
      return;
    }
    // set the question object
    if (this.questions && this.questions.length > 0) {
      this.totalQuestions = this.questions.length;
      const currentQuestion = this.questions[this.currentQuestionIndex];
      this.setquestion.patchValue({
        question: currentQuestion.question.value,
        optionA: currentQuestion.options[0].value,
        optionB: currentQuestion.options[1].value,
        optionC: currentQuestion.options[2].value,
        optionD: currentQuestion.options[3].value,
        answer: currentQuestion.answers[0].value,
      });
      this.stepper.next();
    }
  }

  nextQuestion() {
    // Move to the next question if available
    if (this.currentQuestionIndex < this.questions.length - 1) {
      this.currentQuestionIndex++;

      const currentQuestion = this.questions[this.currentQuestionIndex];

      // Update the form with the new question, options, and answer
      this.setquestion.patchValue({
        question: currentQuestion.question.value,
        optionA: currentQuestion.options[0].value,
        optionB: currentQuestion.options[1].value,
        optionC: currentQuestion.options[2].value,
        optionD: currentQuestion.options[3].value,
        answer: currentQuestion.answers[0].value,
      });
    } else {
      // If there are no more questions, you can handle it as needed
      Swal.fire({
        icon: 'info',
        title: 'No more question to update',
        showConfirmButton: true,
      });
      // Optionally, you can show a message to the user or navigate to a different step
    }
  }

  // NO NEED TO REQUIRED
  // showPreviousQuestion() {
  //   // Move to the previous question if available
  //   if (this.currentQuestionIndex > 0) {
  //     this.currentQuestionIndex--;
  //     this.setQuestionObject(); // Update the form with the previous question
  //   } else {
  //     // If there are no previous questions, you can handle it as needed
  //     Swal.fire({
  //       icon: 'info',
  //       title: ' No previous questions',
  //       showConfirmButton: true,
  //     });
  //     // Optionally, you can show a message to the user or disable the "Previous" button
  //   }
  // }

  deleteExam() {
    console.log('Delete Exam Id=>', this.exam.examId);
    Swal.fire({
      icon: 'warning',
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonText: 'No',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes',
    }).then((result) => {
      if (result.isConfirmed) {
        this.teacherService.deleteExamByExamId(this.exam.examId).subscribe(
          (response: any) => {
            Swal.fire('Deleted!', 'Your file has been deleted.', 'success');
            console.log('Response=>', response);
          },
          (error: any) => {
            console.log('Error=>', error);
          }
        );
        
        this.stepper.previous();
      } else {
      }
    });
  }
  updateQuestion() {
    // Move to the next question if available
    if (this.currentQuestionIndex < this.questions.length) {
      const currentQuestion = this.questions[this.currentQuestionIndex];
      // Add the updated question, options, answer, and IDs to the updateQuestionList
      this.updateQuestionList.push({
        question: {
          id: currentQuestion.question.id,
          value: this.setquestion.value.question,
        },
        options: [
          {
            id: currentQuestion.options[0].id,
            value: this.setquestion.value.optionA,
          },
          {
            id: currentQuestion.options[1].id,
            value: this.setquestion.value.optionB,
          },
          {
            id: currentQuestion.options[2].id,
            value: this.setquestion.value.optionC,
          },
          {
            id: currentQuestion.options[3].id,
            value: this.setquestion.value.optionD,
          },
        ],
        answers: [
          {
            id: currentQuestion.answers[0].id,
            value: this.setquestion.value.answer,
          },
        ],
      });
    }
  }

  // Through this method we call service to update the exam details

  done() {
    this.teacherService
      .updateExamByExam(this.updateExamDetails, this.updateQuestionList)
      .subscribe(
        (data: any) => {
          console.log('Update question list is ', data);
        },
        (error: any) => {
          console.log('Error in updating question list is ', error);
        }
      );
  }

  validateExamDetails(data: any) {
    let message = '';
    if (!data.subjectName.trim() || data.subjectName.trim() == '') {
      message = 'Name field cannot be empty.';
      return message;
      // } else if (data.totalQuestions <= 0) {
      //   message = 'Total questions should be greater than zero.';
      //   return message;
    } else if (data.totalTime < 1) {
      message = 'Duration should be at least one minute i.e., 60 seconds.';
      return message;
    }
    return message;
  }
}
