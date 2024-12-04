import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PageTitleBandComponent } from "../page-title-band/page-title-band.component";
import { CommonModule } from '@angular/common';
import { UserTypeService } from '../user-type.service';
import { UserType } from '../models/user-type.model';

@Component({
  selector: 'app-type-user-list',
  standalone: true,
  imports: [RouterModule, PageTitleBandComponent, CommonModule],
  templateUrl: './type-user-list.component.html',
  styleUrl: './type-user-list.component.css'
})
export class TypeUserListComponent {

  userTypes: UserType[] = [];

  constructor(private userTypeService: UserTypeService) {}

  ngOnInit(): void {
    this.userTypeService.getUserTypes().subscribe((data: UserType[]) => {
      this.userTypes = data;
    });
  }

  deleteUserType(id: string) {
    this.userTypeService.deleteUserTypeById(id).subscribe({
      next: (message: string) => {
        console.log(message);
        this.userTypes = this.userTypes.filter(userType => userType.userTypeId !== id);
      },
      error: (errorResponse) => {
        console.error('Error deleting user: ', errorResponse.error || errorResponse);
      }
    });
  }
}
