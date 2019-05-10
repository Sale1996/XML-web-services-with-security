import { AbstractUser } from './abstract-user.model';

export interface Agent extends AbstractUser {
    businessRegistrationNumber: string;
}
