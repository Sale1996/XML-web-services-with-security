import { ExtraField } from './extra-field.model';
import { Accommodation } from './accommodation.model';
import { Category } from './category.model';
import { Type } from 'src/app/model/type.model';

export interface AccommodationUnit {

  accommodationUnitId: number;
  numberOfPeople: number;
  category: Category;
  type: Type;
  accomodation: Accommodation;
  extraField: ExtraField[];

}
