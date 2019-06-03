import { Accommodation } from './accommodation.model';

export interface Comment {

    commentId: number;
    commentBody: string;
    isApproved: boolean;
    client: number;
    accommodation: Accommodation;

}