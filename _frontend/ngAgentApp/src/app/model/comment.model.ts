import { Accommodation } from './accommodation.model';

export interface Comment {

    commentId: number;
    commentBody: string;
    isApproved: boolean;
    clientId: number;
    accommodation: Accommodation;

}