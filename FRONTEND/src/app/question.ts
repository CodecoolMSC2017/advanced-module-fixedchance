import { Answer } from './answer';

export class Question {
    id : number;
    question : string;
    questionType : string;
    answers : Answer[];
}
