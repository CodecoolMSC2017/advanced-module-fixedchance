import { User } from './user';
import { Question } from './question';
import { Review } from './review';
import { Video } from './video';
import { Topic } from './topic';

export class Course {
    id : number;
    name : string;
    questions : Question[];
    reviews : Review[];
    students : User[];
    teacher : User;
    validated : boolean;
    videos : Video[];
    topics : Topic[];
}
