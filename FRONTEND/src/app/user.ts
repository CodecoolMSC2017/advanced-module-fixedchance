import { Course } from "./course";

export class User {
    id: number;
    username: string;
    email: string;
    firstName: string;
    lastName: string;
    birthDate: Date;
    experience: number;
    role: string;
    password: string;
    enabled: boolean;
    authorities: String[];
    courses : Course[];
}
