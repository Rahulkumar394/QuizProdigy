import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SetExamService } from '../../../../../services/setExam/set-exam.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-set-exam',
  templateUrl: './set-exam.component.html',
  styleUrl: './set-exam.component.css',
})

export class SetExamComponent implements OnInit {
  examDetailsForm!: FormGroup;
  questionDetailsForm!: FormGroup;

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

  combineFormData() {
    return {
      examDetails: this.examDetailsForm.value, // Data from examDetailsForm
      questionDetails: this.questionDetailsForm.value, // Data from questionDetailsForm
    };
  }


  sendFormDataToBackend() {
    const combinedFormData = this.combineFormData(); // Combine form data
    console.log('Combined Form Data:', combinedFormData);

    // Make a POST request to your backend API
    
  }

  

  setExamDetails() {
    // Implement method to set exam details
    console.log('Exam Details:', this.examDetailsForm.value);
    console.log('Question Details:', this.questions.map(q => q.value));
  }

  loginForm!: FormGroup<any>;
  doLogin() {
    throw new Error('Method not implemented.');
  }

  questions: any[] = [];
  answers: any[] = [];
  questionValues: any[] = []; // Array to store entered values for each question

  currentQuestion: number = 1;
  totalQuestion : any  ;

  addQuestion() {
    this.questions.push({});
    this.answers.push([]); 
  }

  generateFields() {
    this.totalQuestion = this.examDetailsForm.value.totalQuestions;
    const countInput = document.getElementById(
      'question-count'
    ) as HTMLInputElement;
    if (countInput) {
      const countValue = countInput.value;
      if (!countValue) {
        Swal.fire({
          icon: 'error',
          text: 'Please Enter the number of questions first',
          title: 'Please Inter value',
        });
        return;
      }
      this.totalQuestion = parseInt(countValue);
    }
    var container = document.getElementById('questions');
    if (container) container.innerHTML = '';
    this.currentQuestion = 1;

    this.generateQuestionField(container);
  }

  generateQuestionField(container: any) {
    const nextBtn = document.querySelector('.next-btn');
    nextBtn?.classList.remove('disable');

    var questionDiv = document.createElement('div');
    
    questionDiv.className = 'question-container';

    var fieldDiv = document.createElement('div')

    fieldDiv.classList.add('option1')
    var questionLabel = document.createElement('label');
    questionLabel.textContent = 'Question ' + this.currentQuestion + ':';
    // questionDiv.appendChild(questionLabel);
    fieldDiv.appendChild(questionLabel)

    var questionInput = document.createElement('input');
    questionInput.type = 'text';
    questionInput.name = 'question' + this.currentQuestion;
    questionInput.placeholder = 'Enter your question';
    questionInput.value =
      this.questionValues[this.currentQuestion - 1]?.question || ''; // Pre-fill question field
      fieldDiv.appendChild(questionInput)


    questionDiv.appendChild(fieldDiv);

    var options = ['A', 'B', 'C', 'D'];
    for (var j = 0; j < options.length; j++) {
      var fieldDiv = document.createElement('div')
      fieldDiv.classList.add('option')
      var optionLabel = document.createElement('label');
      optionLabel.textContent = options[j] + ':';
      fieldDiv.appendChild(optionLabel);

      var optionInput = document.createElement('input');
      optionInput.type = 'text';
      optionInput.name = 'option' + this.currentQuestion + '-' + options[j];
      optionInput.placeholder = 'Option ' + options[j];
      optionInput.value =
        this.questionValues[this.currentQuestion - 1]?.options[j] || ''; // Pre-fill option fields
        fieldDiv.appendChild(optionInput);

        questionDiv.appendChild(fieldDiv);
    }

    var answerLabel = document.createElement('label');
    answerLabel.textContent = 'Answer:';
    questionDiv.appendChild(answerLabel);

    var answerInput = document.createElement('input');
    answerInput.type = 'text';
    answerInput.name = 'answer' + this.currentQuestion;
    answerInput.placeholder = 'Enter the correct answer';
    answerInput.value =
      this.questionValues[this.currentQuestion - 1]?.answer || ''; // Pre-fill answer field
    questionDiv.appendChild(answerInput);

    container.appendChild(questionDiv);
  }

  nextQuestion() {
    if (this.currentQuestion < this.totalQuestion) {
      const currentQuestionContainer = document.querySelector(
        '.question-container:last-child'
      );
      if (currentQuestionContainer) {
        const inputs = currentQuestionContainer.querySelectorAll('input');
        let filled = true;
        inputs.forEach((input: HTMLInputElement) => {
          if (input.value === '') {
            filled = false;
            return;
          }
        });

        if (!filled) {
          Swal.fire({
            icon: 'error',
            text: 'Please fill all the fields before moving to the next question.',
            title: 'Incomplete Question',
          });
          return;
        }

        // Store entered values for current question
        const questionValues = {
          question: inputs[0].value,
          options: Array.from(inputs)
            .slice(1, 5)
            .map((input: HTMLInputElement) => input.value),
          answer: inputs[5].value,
        };
        this.questionValues[this.currentQuestion - 1] = questionValues;
      }

      // Hide current question fields
      
      this.currentQuestion++;
      var container = document.getElementById('questions');
      this.generateQuestionField(container);

    } else {
      const currentQuestionContainer = document.querySelector(
        '.question-container:last-child'
      );
      if (currentQuestionContainer) {
        const inputs = currentQuestionContainer.querySelectorAll('input');
        let filled = true;
        inputs.forEach((input: HTMLInputElement) => {
          if (input.value === '') {
            filled = false;
            return;
          }
        });

        if (!filled) {
          Swal.fire({
            icon: 'error',
            text: 'Please fill all the fields before moving to the next question.',
            title: 'Incomplete Question',
          });
          return;
        }

        // Store entered values for current question
        const questionValues = {
          question: inputs[0].value,
          options: Array.from(inputs)
            .slice(1, 5)
            .map((input: HTMLInputElement) => input.value),
          answer: inputs[5].value,
        };
        this.questionValues[this.currentQuestion - 1] = questionValues;

        Swal.fire({
          title: 'Finished!',
          text: 'You have completed all the questions. Now you can Submit.',
          icon: 'success',
        });

        // Enable the Submit Questions button
        const submit = document.querySelector('.submit');
        submit?.classList.remove('disable');
      }
    }
  }

  previousQuestion() {
    if (this.currentQuestion > 1) {
      this.currentQuestion--;
      const container = document.getElementById('questions');
      if (container?.lastChild) {
        container?.removeChild(container.lastChild);
      }
      this.generateQuestionField(container);
    }
  }
  submitQuestions() {
    if (this.questionValues.length === 0) {
      Swal.fire({
        icon: 'error',
        text: 'No questions to submit. Please generate questions first.',
        title: 'No Questions',
      });
      return;
    }


    const combinedFormData = this.combineFormData(); // Combine form data
    console.log('Combined Form Data:', combinedFormData);
    

    // Displaying all questions with their options and answers
    let questionsSummary = '';
    this.questionValues.forEach((question, index) => {
      questionsSummary += `Question ${index + 1}: ${question.question}\n`;
      question.options.forEach((option: any, optionIndex: number) => {
        questionsSummary += `Option ${String.fromCharCode(
          65 + optionIndex
        )}: ${option}\n`;
      });
      questionsSummary += `Answer: ${question.answer}\n\n`;
    });

    // Printing questions summary
    console.log(questionsSummary);

    // Optionally, you can display the questions summary in a dialog box
    Swal.fire({
      title: 'Questions Summary',
      text: questionsSummary,
      icon: 'info',
    });
  }

}