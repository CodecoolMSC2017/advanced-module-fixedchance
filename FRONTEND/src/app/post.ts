import { Comment } from './comment';

export class Post {
    id: number;
    postContent: string;
    topics: string[];
    userName: string;
    rating: number;
    users: string[];
    comments : Comment[];
}
