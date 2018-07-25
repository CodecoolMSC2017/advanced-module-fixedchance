import { User } from './user';
import { Question } from './question';
import { Review } from './review';
import { Video } from './video';

export class Course {
    id : number;
    name : string;
    questions : Question[];
    reviews : Review[];
    students : User[];
    teacher : User;
    validated : boolean;
    videos : Video[];

    constructor() {
        this.id = undefined;
        this.name = undefined;
        this.questions = undefined;
        this.reviews = undefined;
        this.students = undefined;
        this.teacher = undefined;
        this.validated = undefined;
        this.videos = undefined;
    }
}
