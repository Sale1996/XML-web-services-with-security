import { Accommodation } from './accommodation.model';

export interface Price {

    priceId: number;
    dateFrom: Date;
    dateTo: Date;
    amount: number;
    accommodation: Accommodation;

}