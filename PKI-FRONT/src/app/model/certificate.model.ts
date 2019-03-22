export interface Certificate {

  commonName: string;
  country: string;
  locality: string;
  state: string;
  organisation: string;
  organisationUnit: string;
  active: string;
  validFromDate: Date
  validToDate: Date;
  type: string;
  issuer: number;

}
