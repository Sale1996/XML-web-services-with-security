import { City } from './city.model';
import { Picture } from './picture.model';

export interface Accommodation {

    accommodationId: number;
    accommodationName: string;
    description: string;
    agentId: number;
    numberOfDaysBeforeCancelation: number;
    city: City;
    picture: Picture;
}