export class User {
    id : number;
    email : string;
    firstName : string;
    lastName : string;
    birthDate : Date;
    experience : number;
    role : string;

    constructor() {
        this.id = undefined;
        this.email = undefined;
        this.firstName = undefined;
        this.lastName = undefined;
        this.birthDate = undefined;
        this.experience = undefined;
        this.role = undefined;
    }
}
