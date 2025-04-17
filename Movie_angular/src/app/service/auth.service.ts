import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  storeToken(token: string) {
    sessionStorage.setItem('authToken', token);
  }
  
  getToken(): string | null {
    return sessionStorage.getItem('authToken');
  }
  storeRole(userRole: string) {
    sessionStorage.setItem('userRole', userRole);
  }
  
  getRole(): string | null {
    return sessionStorage.getItem('userRole');
  }
}
