import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SetExamService } from '../../../../../services/setExam/set-exam.service';

@Component({
  selector: 'app-set-exam',
  templateUrl: './set-exam.component.html',
  styleUrl: './set-exam.component.css',
})
export class SetExamComponent implements OnInit {
  examDetailsForm!: FormGroup;
  questionDetailsForm!: FormGroup;
  questions: any[] = [];

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.examDetailsForm = this.formBuilder.group({
      examName: ['', Validators.required],
      totalQuestions: ['', Validators.required],
      examDuration: ['', Validators.required],
      timeAllotedPerQuestion: ['', Validators.required],
      handsOn: ['false', Validators.required]
    });

    this.questionDetailsForm = this.formBuilder.group({
      questions: this.formBuilder.array([])
    });
  }

  addQuestion() {
    const questionFormGroup = this.formBuilder.group({
      question: ['', Validators.required],
      options: this.formBuilder.array([
        this.formBuilder.control('', Validators.required),
        this.formBuilder.control('', Validators.required),
        this.formBuilder.control('', Validators.required),
        this.formBuilder.control('', Validators.required)
      ]),
      correctAnswer: ['', Validators.required]
    });

    this.questions.push(questionFormGroup);
  }

  setExamDetails() {
    // Implement method to set exam details
    console.log('Exam Details:', this.examDetailsForm.value);
    console.log('Question Details:', this.questions.map(q => q.value));
  }
}