import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username = '';
  password = '';

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    const loginData = {
      username: this.username,
      password: this.password
    };
  
    console.log('Login Data:', loginData);
  
    this.http.post('http://localhost:8083/auth/login', loginData)
      .subscribe(
        (res: any) => {
          console.log('Login successful:', res);
  
          if (res.token) {
            sessionStorage.setItem('authToken', res.token);
          }
          if (res.role) {
            sessionStorage.setItem('userRole', res.role);
          }
            this.router.navigate(['/movies']);
        },
        (error) => {
          console.error('Login failed:', error);
        }
      );
  }
  goToRegister() {
    this.router.navigate(['/register']);
  }
  
}