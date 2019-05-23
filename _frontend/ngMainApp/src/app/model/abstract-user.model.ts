export interface AbstractUser {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    password?: string;
    role: string;
}
