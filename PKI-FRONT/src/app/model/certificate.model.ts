export interface Certificate {
    name: string;
    date: Date;
    issuer: number;
    publicKey: string;
    serialNumber: string;
    country: string;
    active: boolean
    city: string;
    state: string;
    organization: string;
    organizationUnit: string;
    type: number;


}
