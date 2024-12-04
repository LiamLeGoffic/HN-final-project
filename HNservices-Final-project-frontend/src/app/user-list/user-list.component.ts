import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PageTitleBandComponent } from '../page-title-band/page-title-band.component';
import { User } from '../models/user.model';
import { UserService } from '../user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [RouterModule, PageTitleBandComponent, CommonModule],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent {

  users: User[] = [];
  sortedUsers: User[] = [];
  sortKey: string = 'lastName';
  asc: boolean = true;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUsers().subscribe((data: User[]) => {
      this.users = data;
      this.sortUsers();
    });
  }

  sortUsers(): void {
    this.sortedUsers = [...this.users].sort((a, b) => {
        let fieldA: string;
        let fieldB: string;

        if (this.sortKey === 'userType.label') {
            fieldA = a.userType?.label?.toLowerCase() || '';
            fieldB = b.userType?.label?.toLowerCase() || '';
        } else {
            fieldA = (a[this.sortKey as keyof User] || '').toString().toLowerCase();
            fieldB = (b[this.sortKey as keyof User] || '').toString().toLowerCase();
        }

        const comparison = fieldA.localeCompare(fieldB);
        return this.asc ? comparison : -comparison;
    });
  }

  applyFilter(sortKey: string): void {
    this.asc = this.sortKey === sortKey? !this.asc: true;
    this.sortKey = sortKey;
    this.sortUsers();
  }

  deleteUser(id: string) {
    this.userService.deleteUserById(id).subscribe({
      next: (message) => {
        console.log(message);
        this.users = this.users.filter(user => user.userId !== id);
        this.sortUsers();
      },
      error: (errorMessage) => {
        console.error('Error deleting user:', errorMessage);
      }
    });
  }

}
