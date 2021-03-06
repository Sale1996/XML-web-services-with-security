import { AbstractUser } from './abstract-user.model';

export interface User extends AbstractUser {
    telephoneNumber: string;
    address: string;
    activated: boolean;
}
