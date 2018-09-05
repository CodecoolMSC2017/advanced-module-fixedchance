import { User } from "./user";
import { Course } from "./course";
import { Answer } from "./answer";
import { Question } from "./question";

export class Exanswer {

    student : User;
    course : Course;
    answer : Answer[] = [];
    question : Question;
}
