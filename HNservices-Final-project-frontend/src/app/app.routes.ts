import { Routes } from '@angular/router';
import { TypeUserListComponent } from './type-user-list/type-user-list.component';
import { TypeUserFormComponent } from './type-user-form/type-user-form.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'user-list',
        pathMatch: 'full'
    },
    {
        path: 'user-type-list',
        component: TypeUserListComponent,
    },
    {
        path: 'user-type-form/:id',
        component: TypeUserFormComponent,
    },
    {
        path: 'user-type-form',
        component: TypeUserFormComponent,
    },
    {
        path: 'user-list',
        component: UserListComponent,
    },
    {
        path: 'user-form',
        component: UserFormComponent,
    },
    {
        path: 'user-form/:id',
        component: UserFormComponent,
    }
];
