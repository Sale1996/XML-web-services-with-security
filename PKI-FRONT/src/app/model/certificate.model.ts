import { Issuer } from './issuer.model';

export interface Certificate {

  commonName: string;
  country: string;
  locality: string;
  serialNumber: string;
  state: string;
  organisation: string;
  organisationUnit: string;
  active: string;
  validFromDate: Date;
  validToDate: Date;
  type: string;
  issuer: Issuer;

}
