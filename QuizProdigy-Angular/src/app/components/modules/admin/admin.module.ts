import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

// Material

import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';

import { ReactiveFormsModule } from '@angular/forms';
import { ManageStudentsComponent } from './components/manage-students/manage-students.component';
import { ManageTeachersComponent } from './components/manage-teachers/manage-teachers.component';
import { ManageExamComponent } from './components/manage-exam/manage-exam.component';
import { ManageResultComponent } from './components/manage-result/manage-result.component';
import { HomeComponent } from './components/home/home.component';
import { TeacherInformationComponent } from './components/teacher-information/teacher-information.component';
import {MatTableModule} from '@angular/material/table';
import { TeacherRequestComponent } from './components/teacher-request/teacher-request.component';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';


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
    TeacherRequestComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,

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
  ]
})
export class AdminModule { }
