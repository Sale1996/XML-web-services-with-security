export interface Certificate {
    name: string;
    date: Date;
    issuer: number;
    publicKey: string;
    country: string;
    city: string;
    state: string;
    organization: string;
    organizationUnit: string;
    type: number;
}
