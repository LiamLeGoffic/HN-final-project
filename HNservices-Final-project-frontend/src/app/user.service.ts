import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  getUserById(id: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${id}`);
  }

  addNewUser(user: User): Observable<string> {
    return this.http.post(`${this.apiUrl}/new-user`, user, { responseType: 'text' });
  }

  updateUser(user: User): Observable<string> {
    return this.http.put(`${this.apiUrl}/user-modification`, user, { responseType: 'text' })
  }

  deleteUserById(id: string): Observable<string> {
    return this.http.delete(`${this.apiUrl}/user-deleting/${id}`, { responseType: 'text' });
  }
}
