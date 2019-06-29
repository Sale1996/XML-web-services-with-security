export interface Reservation {
  reservationId?: number;
  dateFrom: string;
  dateTo: string;
  finalPrice: number;
  confirmation: boolean;
  accommodationUnit: number;
  client: number;
  accommodation_id?: number;
}
