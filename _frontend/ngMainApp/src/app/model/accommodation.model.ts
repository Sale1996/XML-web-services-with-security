import { Picture } from "./picture.model";

export interface Accommodation {

  accommodationId: number;
  accommodationName: string;
  description: string;
  city: number;
  countedNumberOfBeds: number;
  xCord: number;
  yCord: number;
  numberOfDaysBeforeCancelation: number;
  pictures: Picture[];

}
