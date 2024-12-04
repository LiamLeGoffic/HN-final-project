import { Component } from '@angular/core';
import { PageTitleBandComponent } from '../page-title-band/page-title-band.component';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../models/user.model';
import { FormsModule } from '@angular/forms';
import { UserType } from '../models/user-type.model';
import { UserTypeService } from '../user-type.service';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [CommonModule, PageTitleBandComponent, FormsModule],
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.css'
})
export class UserFormComponent {

  isEditMode: boolean = false;
  id: string | null = null;
  user: User = new User();
  userTypes: UserType[] = [];
  submitted: boolean = false;
  noChangesDetected: boolean = false;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private userTypeService: UserTypeService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');  // Récupère l'ID de la route
      this.isEditMode = !!this.id; // Si ID existe, nous sommes en mode édition
    });
    this.userTypeService.getUserTypes().subscribe((data: UserType[]) => {
      this.userTypes = data;

      if (!!this.id) {
        this.userService.getUserById(this.id).subscribe((data: User) => {
          this.user = data;

          this.user.userType = this.userTypes.find(
            (type) => type.userTypeId === this.user.userType?.userTypeId
          ) || null;
        });
        
      } else {
        this.user.userType = this.userTypes[0];
      }
    });
  }

  onSubmit(): void {
    this.submitted = true;
    this.noChangesDetected = false;

    if (!this.user.lastName || !this.user.firstName || !this.user.email || !this.user.userType) {
      console.error('All fields must be filled');
      return;
    }

    if (!this.isEmailValid(this.user.email)) {
      console.error('Invalid email format');
      return;
    }

    if (this.isEditMode) {
      this.userService.updateUser(this.user).subscribe({
        next: (message: string) => {
          console.log(message, this.user);
          this.router.navigate(['/user-list']);
        },
        error: (errorResponse) => {
          console.error('Error user update:', errorResponse.error);
          if (errorResponse.error == "No changes detected") {
            this.noChangesDetected = true;
          }
        }
      });
    } else {
      this.userService.addNewUser(this.user).subscribe({
        next: (message: string) => {
          console.log(message, this.user);
          this.router.navigate(['/user-list']);
        },
        error: (errorResponse) => {
          console.error('Error adding user:', errorResponse);
        }
      });
    }
  }

  isEmailValid(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }

}
