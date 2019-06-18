import { Accommodation } from './accommodation.model';
import { AccommodationUnit } from './accommodation-unit.model';

export interface Price {

    priceId: number;
    dateFrom: Date;
    dateTo: Date;
    amount: number;
    accommodationUnit: AccommodationUnit;

}