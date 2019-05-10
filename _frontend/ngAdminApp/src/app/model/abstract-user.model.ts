import { UserType } from './user-type.enum';

export interface AbstractUser {
    id: number;
    name: string;
    surname: string;
    email: string;
    password: string;
    userType: UserType;
}
