import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  username = '';
  password = '';
  confirmPassword = '';
  fullName = '';
  email = '';
  errorMessage = '';
  successMessage = '';

  constructor(private http: HttpClient, private router: Router) {}
  register() {
    if (this.password !== this.confirmPassword) {
      this.errorMessage = 'Passwords do not match.';
      this.successMessage = '';
      return;
    }
  
    const registrationData = {
      username: this.username,
      password: this.password,
      fullName: this.fullName,
      email: this.email
    };
  
    this.http.post('http://localhost:8083/auth/register', registrationData)
      .subscribe(
        (res: any) => {
          console.log('Registration successful:', res);
  
          if (res.token) {
            sessionStorage.setItem('authToken', res.token);
          }
          if (res.role) {
            sessionStorage.setItem('userRole', res.role);
          }
  
          this.successMessage = 'Registration successful! Redirecting...';
          this.errorMessage = '';
  
          setTimeout(() => {
            this.router.navigate(['/movies']);
          }, 1000);
        },
        (error) => {
          console.error('Registration failed:', error);
          this.errorMessage = error.error.message || 'Registration failed. Please try again.';
          this.successMessage = '';
        }
      );
  }

  goToLogin() {
    this.router.navigate(['']);
  }

}
