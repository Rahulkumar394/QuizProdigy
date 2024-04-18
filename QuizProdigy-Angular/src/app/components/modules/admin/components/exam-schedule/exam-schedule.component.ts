import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { Route, Router } from '@angular/router';
import { AdminService } from '../../../../../services/admin/admin.service';
import { error } from 'console';

@Component({
  selector: 'app-exam-schedule',
  templateUrl: './exam-schedule.component.html',
  styleUrl: './exam-schedule.component.css',
})
export class ExamScheduleComponent implements OnInit {
  dateTimeForm: FormGroup;
  minDate = new Date(); // Today's date
  examDate: string | undefined;

  constructor(private formBuilder: FormBuilder, private route: Router,private adminService:AdminService) {
    // Initialize minDate with today's date plus one day
    const today = new Date();
    this.minDate = new Date(
      today.getFullYear(),
      today.getMonth(),
      today.getDate() + 1
    );

    this.dateTimeForm = this.formBuilder.group({
      selectedDate: [''], // Initialize with an empty string or specific date
      selectedTime: [''], // Initialize with an empty string or specific time
    });
  }

  ngOnInit(): void {
    // Subscribe to form value changes if needed
    this.dateTimeForm.valueChanges.subscribe((value: any) => {
      console.log('Form Value:', value);
      // Perform any logic based on form value changes
    });
  }

  onSubmit() {
    const formValue = this.dateTimeForm.value;
    const selectedDate: Date = new Date(formValue.selectedDate);
    const selectedTime: string = formValue.selectedTime;
    const dateOnly = selectedDate.toLocaleDateString();
    console.log('Date:', dateOnly);
    console.log('Time:', selectedTime);
    if(!dateOnly && !selectedTime){
      console.log("werfghbndfghj");
    }
    this.adminService.examSchedule(dateOnly,selectedTime).subscribe(
      (response:any)=>{
        console.log(response);
        this.closeDialog();
      },
      (error:any)=>{
        console.log("ERROR ",error);
      }
    )
  }

  preventTyping(event: KeyboardEvent) {
    // Allow navigation keys (e.g., arrow keys, Tab) and basic editing (e.g., Backspace, Delete)
    if (
      [
        'ArrowUp',
        'ArrowDown',
        'ArrowLeft',
        'ArrowRight',
        'Tab',
        'Backspace',
        'Delete',
      ].indexOf(event.key) !== -1
    ) {
      return;
    }
    event.preventDefault(); // Prevent character input
  }

  closeDialog() {
    this.route.navigateByUrl('admin/setExamDate');
  }

  maxTime() {
    const now = new Date();
    const hours = now.getHours();
    const minutes = now.getMinutes();
    return `${hours}:${minutes}`;
  }
}
