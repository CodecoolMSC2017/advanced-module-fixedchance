export class User {
    id : number;
    username : string;
    email : string;
    firstName : string;
    lastName : string;
    birthDate : Date;
    experience : number;
    role : string;
    password : string;
    enabled : boolean;
    authorities: String[] = [];

    constructor() {
        this.id = undefined;
        this.username = undefined;
        this.email = undefined;
        this.firstName = undefined;
        this.lastName = undefined;
        this.birthDate = undefined;
        this.experience = undefined;
        this.role = undefined;
        this.password = undefined;
        this.enabled = undefined;
        this.authorities = undefined;
    }
}
