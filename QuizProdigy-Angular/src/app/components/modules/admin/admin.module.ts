import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { NavbarComponent } from './components/navbar/navbar.component';

// Material

import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';

import {
  NgxMatDatetimePickerModule,
  NgxMatTimepickerModule,
} from '@angular-material-components/datetime-picker';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { ExamScheduleComponent } from './components/exam-schedule/exam-schedule.component';
import { HomeComponent } from './components/home/home.component';
import { ManageExamComponent } from './components/manage-exam/manage-exam.component';
import { ManageResultComponent } from './components/manage-result/manage-result.component';
import { ManageStudentsComponent } from './components/manage-students/manage-students.component';
import { ManageTeachersComponent } from './components/manage-teachers/manage-teachers.component';
import { SetExamDateComponent } from './components/set-exam-date/set-exam-date.component';
import { TeacherInformationComponent } from './components/teacher-information/teacher-information.component';
import { TeacherRequestComponent } from './components/teacher-request/teacher-request.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatMomentDateModule } from '@angular/material-moment-adapter';

@NgModule({
  declarations: [
    DashboardComponent,
    NavbarComponent,
    ManageStudentsComponent,
    ManageTeachersComponent,
    ManageExamComponent,
    ManageResultComponent,
    HomeComponent,
    TeacherInformationComponent,
    TeacherRequestComponent,
    SetExamDateComponent,
    ExamScheduleComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatMomentDateModule,

    //
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatTableModule,
    MatPaginator,
    MatPaginatorModule,
    MatButtonModule,
    NgxMatDatetimePickerModule,
    MatInputModule,
    NgxMatTimepickerModule,
  ],
})
export class AdminModule {}
