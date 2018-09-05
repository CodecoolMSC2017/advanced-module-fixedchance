import { Question } from "./question";

export class Answer {
    id: number;
    answer: string;
    right: string;
    courseQuestion : Question;
}
