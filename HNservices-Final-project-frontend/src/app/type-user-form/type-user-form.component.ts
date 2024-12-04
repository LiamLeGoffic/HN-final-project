import { Component } from '@angular/core';
import { PageTitleBandComponent } from "../page-title-band/page-title-band.component";
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserTypeService } from '../user-type.service';
import { UserType } from '../models/user-type.model';

@Component({
  selector: 'app-type-user-form',
  standalone: true,
  imports: [PageTitleBandComponent, CommonModule, FormsModule],
  templateUrl: './type-user-form.component.html',
  styleUrl: './type-user-form.component.css'
})
export class TypeUserFormComponent {
  isEditMode: boolean = false;
  id: string | null = null;
  userType: UserType = new UserType();
  submitted: boolean = false;
  userTypeAlreadyExisting: string | null = null;
  noChangesDetected: boolean = false;

  constructor(private route: ActivatedRoute, private router: Router, private userTypeService: UserTypeService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');  // Récupère l'ID de la route
      this.isEditMode = !!this.id; // Si ID existe, nous sommes en mode édition
    });
    if (!!this.id) {
      this.userTypeService.getUserTypeById(this.id).subscribe((data: UserType) => {
        this.userType = data;
      })
    }
  }

  onSubmit(): void {
    this.submitted = true;
    this.userTypeAlreadyExisting = null;
    this.noChangesDetected = false;

    if (!this.userType.label) {
      console.error('All fields must be filled');
      return;
    }

    if (this.isEditMode) {
      this.userTypeService.updateUserType(this.userType).subscribe({
        next: (message: string) => {
          console.log(message);
          this.router.navigate(['/user-type-list']);
        },
        error: (errorResponse) => {
          console.error('Error user type update: ', errorResponse.error);
          if (errorResponse.error == "No changes detected") {
            this.noChangesDetected = true;
          }
        }
      });
    } else {
      this.userTypeService.addNewUserType(this.userType).subscribe({
        next: (message: string) => {
          console.log(message);
          this.router.navigate(['/user-type-list']);
        },
        error: (errorResponse) => {
          console.error('Error adding user type: ', errorResponse.error);
          if (errorResponse.error == "User type already existing") {
            this.userTypeAlreadyExisting = this.userType.label;
          }
        }
      })
    }
  }
}
