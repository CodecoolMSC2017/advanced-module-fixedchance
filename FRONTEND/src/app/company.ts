import { User } from './user';

export class Company {
    id: number;
    name: string;
    registrationDate: Date;
    email: string;
    active: string;
    subscription: string;
    paymentDate: Date;
    description: string;
    user: User;
}
