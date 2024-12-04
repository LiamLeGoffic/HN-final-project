import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserType } from './models/user-type.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserTypeService {
  private apiUrl = 'http://localhost:8080/api/user-types';

  constructor(private http: HttpClient) { }

  getUserTypes(): Observable<UserType[]> {
    return this.http.get<UserType[]>(this.apiUrl);
  }

  getUserTypeById(id: string): Observable<UserType> {
    return this.http.get<UserType>(`${this.apiUrl}/${id}`);
  }

  addNewUserType(userType: UserType): Observable<string> {
    return this.http.post(`${this.apiUrl}/new-user-type`, userType, { responseType: 'text' });
  }

  updateUserType(userType: UserType): Observable<string> {
    return this.http.put(`${this.apiUrl}/user-type-modification`, userType, { responseType: 'text' });
  }

  deleteUserTypeById(id: string): Observable<string> {
    return this.http.delete(`${this.apiUrl}/user-type-deleting/${id}`, { responseType: 'text' });
  }
}
