import { AddVideo } from './add-video';
import { AddQuestion } from './add-question';

export class AddCourse {

    courseName: string;
    topics: string[] = [];
    videos: AddVideo[] = [];
    questions: AddQuestion[] = [];

    constructor() {
        for (let i = 0; i < 3; i++) {
            this.videos.push(new AddVideo());
            this.questions.push(new AddQuestion());
        }
    }
}
