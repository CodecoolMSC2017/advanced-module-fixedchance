import { Answer } from './answer';

export class Question {
    id: number;
    answers: Answer[];
    question: string;
    questionType: string;
}
