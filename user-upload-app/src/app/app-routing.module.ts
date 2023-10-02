import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserUploadComponent } from './user-upload/user-upload.component';

const routes: Routes = [
  // Other routes
  { path: 'upload', component: UserUploadComponent },
  { path: '', pathMatch: 'full', redirectTo: 'upload' }, 
  // Other routes
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})

export class AppRoutingModule { }
