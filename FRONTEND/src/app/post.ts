import { User } from './user';
import { Topic } from './topic';

export class Post {
    id: number;
    postContent: string;
    topics: string[];
    userName: string;
}
