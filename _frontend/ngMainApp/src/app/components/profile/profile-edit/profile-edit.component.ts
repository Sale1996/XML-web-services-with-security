import { AuthService } from 'src/app/services/auth.service';
import { UserService } from './../../../services/user.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditComponent implements OnInit {

  updateForm: FormGroup;
  user: Observable<User>;
  userEmail: string;
  userLog: User;


  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.updateForm = this.formBuilder.group({
      id: [''],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      telephoneNumber: ['', Validators.pattern('^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$')],
      address: ['', Validators.maxLength(50)]
    });

    this.userEmail = this.authService.getEmailFromToken(localStorage.getItem('access_token'));

    this.getUserByEmail();
  }

  getUserByEmail(): void {
    this.user = this.userService.getUserByEmail(this.userEmail).pipe(
      tap(user => this.updateForm.patchValue(user))
    );
  }

  onSubmit() {
    if (this.updateForm.valid) {

      this.userService.updateUser(this.updateForm.value).subscribe((response) => {
        console.log('Response is: ', response);
        location.reload();
     },
     (error) => {
        // catch the error
        console.error('An error occurred, ', error);
     }
     );
     }
  }



}
