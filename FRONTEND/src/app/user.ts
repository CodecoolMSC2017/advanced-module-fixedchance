import { Post } from './post';
import { Course } from './course';

export class User {
    id: number;
    username: string;
    email: string;
    firstName: string;
    lastName: string;
    birthDate: Date;
    experience: number;
    role: string;
    authorities: String[];
    courses: Course[];
    registrationDate: number;
    user: User;
}
