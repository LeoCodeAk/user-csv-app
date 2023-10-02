import { Component } from '@angular/core';
import { UserService } from './user.service';

@Component({
  selector: 'app-user-upload',
  templateUrl: './user-upload.component.html',
  styleUrls: ['./user-upload.component.css']
})
export class UserUploadComponent {
  error: string | null = null;
  apiResponseMessage: string | null = null;
  selectedFile: File | null = null;

  constructor(private userService: UserService) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  upload() {
    if (this.selectedFile) {
      this.userService.uploadCSV(this.selectedFile).subscribe(
        (response: string) => {
          this.apiResponseMessage = response;          
        },
        (error) => {
          // Handle error
          this.error = error.error.message;
        }
      );
    } else {
      this.error = 'Please select a CSV file.';
    }
  }
}
