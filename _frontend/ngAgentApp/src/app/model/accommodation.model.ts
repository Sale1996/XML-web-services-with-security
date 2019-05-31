import { City } from './city.model';

export interface Accommodation {

    accommodationId: number;
    accommodationName: string;
    description: string;
    agentId: number;
    numberOfDaysBeforeCancelation: number;
    city: City;
}