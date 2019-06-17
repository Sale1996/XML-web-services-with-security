import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  private RegisterForm: FormGroup;
  private message: string;
  private isRegistrationFailed: boolean;

  constructor(
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.RegisterForm = this.formBuilder.group({
      id: [''],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.email],
      telephoneNumber: ['', Validators.pattern('^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$')],
      address: [''],
      password: ['', Validators.minLength(6)]
    });
  }

  onSubmit() {
    // if (this.RegisterForm.valid) {
    //   this.authService.register(this.RegisterForm.value as User).subscribe(
    //     (user: User) => console.log(user)
    //   );
    // }

    if (this.RegisterForm.valid) {
      this.authService.register(this.RegisterForm.value as User);
    }

  }
}
