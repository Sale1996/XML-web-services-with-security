export interface Reservation {
    reservationId: number;
    dateFrom: string;
    dateTo: string;
    finalPrice: number;
    confirmation: boolean;
    accommodationUnit: number;
    accommodation?: number;
    client: number;
}