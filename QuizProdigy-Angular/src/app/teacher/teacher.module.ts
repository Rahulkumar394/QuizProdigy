import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { NavbarComponent } from './components/navbar/navbar.component';
import { TeacherDashboardComponent } from './teacher-dashboard/teacher-dashboard.component';
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

import { ReactiveFormsModule } from '@angular/forms';
import { SetQuestionPaperComponent } from './components/set-question-paper/set-question-paper.component';

@NgModule({
  declarations: [
    TeacherDashboardComponent,
    NavbarComponent,
    SetQuestionPaperComponent,
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
  ],
})
export class TeacherModule {}
