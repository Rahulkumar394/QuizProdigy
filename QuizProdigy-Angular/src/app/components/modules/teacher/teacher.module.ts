import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { NavbarComponent } from './components/navbar/navbar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { TeacherRoutingModule } from './teacher-routing.module';

// Material

import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatStepperModule} from '@angular/material/stepper';
import { MatSelectModule } from '@angular/material/select';

import { ReactiveFormsModule } from '@angular/forms';
import { SetquestionComponent } from './components/setquestion/setquestion.component';
import { UpdateQuestionComponent } from './components/update-question/update-question.component';
import { ViewQuestionComponent } from './components/view-question/view-question.component';
import { ManageStudentComponent } from './components/manage-student/manage-student.component';
import { StudentInfoComponent } from './components/student-info/student-info.component';
import { StudentRequestComponent } from './components/student-request/student-request.component';
import {MatTableModule} from '@angular/material/table';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';

@NgModule({
  declarations: [
    DashboardComponent,
    NavbarComponent,
    SetquestionComponent,
    UpdateQuestionComponent,
    ViewQuestionComponent,
    ManageStudentComponent,
    StudentInfoComponent,
    StudentRequestComponent
  ],
  imports: [
    CommonModule,
    TeacherRoutingModule,
    ReactiveFormsModule,

    //
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatStepperModule,
    MatSelectModule,MatTableModule,
    MatPaginator,
    MatPaginatorModule
    
  ],
})
export class TeacherModule {}
