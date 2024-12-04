import { UserType } from "./user-type.model";

export class User {
    userId: string = '';
    lastName: string = '';
    firstName: string = '';
    email: string = '';
    userType: UserType | null = null ;
}
