import { User } from './user';
import { Question } from './question';
import { Review } from './review';

export class Course {
    id : number;
    teacher : User;
    students : User[];
    questions : Question[];
    reviews : Review[];
}
