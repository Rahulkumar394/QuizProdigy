import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';

const routes: Routes = [
  {
    path:"",
    component:LoginComponent
  },
  {
    path:"register",
    component:RegistrationComponent
  },
  {
    path:"teacher",
    loadChildren:()=>import('./components/modules/teacher/teacher.module').then(m=>m.TeacherModule),
    
  },
  {
    path:'admin',
    loadChildren:()=>import("./components/modules/admin/admin.module").then(m => m.AdminModule),
  },
  {
    path:'student',
    loadChildren:()=>import("./components/modules/student/student.module").then(m => m.StudentModule),
    
  },
  {
    path:'forgot-passowrd',
    component:ForgotPasswordComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
