import { Answer } from './answer';

export class Question {
    id : number;
    answers : Answer[];
    question : string;
    questionType : string;

    constructor() {
        this.id = undefined;
        this.answers = undefined;
        this.question = undefined;
        this.questionType = undefined;
    }
}
