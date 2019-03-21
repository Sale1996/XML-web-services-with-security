export interface Certificate {
    commonName: string;
    validFromDate: Date;
    validToDate: Date;
    issuer: any;
    publicKey: string;
    serialNumber: string;
    country: string;
    active: boolean
    state: string;
    organisation: string;
    organisationUnit: string;
    type: number;
    locality: string;

}
