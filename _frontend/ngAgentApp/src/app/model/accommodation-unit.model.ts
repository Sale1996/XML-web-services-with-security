import { Accommodation } from './accommodation.model';
import { Category } from './category.model';
import { Type } from '@angular/compiler/src/core';
import { ExtraField } from './extra-field.model';

export interface AccommodationUnit {

    accommodationUnitId: number;
    numberOfPeople: number;
    accomodation: Accommodation;
    category: Category;
    type: Type;
    extraField: Array<ExtraField>;

}