import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SetExamService } from '../../../../../services/setExam/set-exam.service';

@Component({
  selector: 'app-set-exam',
  templateUrl: './set-exam.component.html',
  styleUrl: './set-exam.component.css',
})
export class SetExamComponent {
  constructor(private setExamService: SetExamService,private formBuilder: FormBuilder) {}

  examDetails = new FormGroup({
    examName: new FormControl(''),
    totalQuestions: new FormControl(''),
    examDuration: new FormControl(''),
    timeAllotedPerQuestion: new FormControl(''),
    handsOn: new FormControl(''),
  });


// Step 2-> Set Question Paper


// loginForm!: FormGroup<any>;
  

  questionDetails!: FormGroup;
questions: any[] = []; // Initialize as per your requirement
answers: any[][] = []; // Initialize as per your requirement

ngOnInit(): void {
  this.initializeForm();
}

initializeForm() {
  this.questionDetails = this.formBuilder.group({
    // Initialize your form controls here dynamically based on questions and answers
  });

  // Example: If you want to initialize form controls dynamically based on questions and answers
  this.questions.forEach((question, qIndex) => {
    const questionControlName = 'question' + qIndex;
    this.questionDetails.addControl(questionControlName, this.formBuilder.control('', Validators.required));

    this.answers[qIndex].forEach((answer, aIndex) => {
      const answerControlName = 'answer' + qIndex + aIndex;
      this.questionDetails.addControl(answerControlName, this.formBuilder.control('', Validators.required));
    });

    // Add answer control for each question
    const answerControlName = 'answer' + qIndex;
    this.questionDetails.addControl(answerControlName, this.formBuilder.control('', Validators.required));
  });
}

addAnswerField(qIndex: number) {
  // Assuming answers is an array of arrays
  this.answers[qIndex].push(''); // Add an empty string as a new answer
}

addQuestion() {
  this.questions.push(''); // Add an empty string as a new question
  this.answers.push([]); // Add an empty array for answers corresponding to the new question
}















  // questionDetails = FormGroup({

  // })

  setExamDetails() {
    this.setExamService.setExamDetails(this.examDetails).subscribe(
      (data: any) => {
        console.log('Exam Details are :', data);
      },
      (error: any) => {
        console.log('Error sending Exam Details :', error);
      }
    );
  }
}
