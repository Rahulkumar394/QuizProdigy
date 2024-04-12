import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { StudentRoutingModule } from './student-routing.module';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';

// import { FormsModule } from '@angular/forms';
// import { MatCardModule } from '@angular/material/card';
// import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
// import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';

import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DoExamComponent } from './components/do-exam/do-exam.component';
import { ExamInstructionsComponent } from './components/exam-instructions/exam-instructions.component';

@NgModule({
  declarations: [
    DoExamComponent,
    DashboardComponent,
    ExamInstructionsComponent,

  ],
  imports: [
    CommonModule,
    StudentRoutingModule,
    MatDialogModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
  ],
})
export class StudentModule {}
