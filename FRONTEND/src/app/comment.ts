import { Post } from './post';
import { User } from './user';

export class Comment {

    simpleUser : User;
    post : Post;
    commentText : string;
    rating : number;
}
