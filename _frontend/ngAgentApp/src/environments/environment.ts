// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiUrlAccommodation: "https://localhost:8080/accommodations",
  apiUrlAccommodationUnit: "https://localhost:8080/accommodationUnits",
  apiUrlType: "https://localhost:8080/types",
  apiUrlAgent: "https://localhost:8080/agent",
  apiUrlCategory: "https://localhost:8080/categories",
  apiUrlCity: "https://localhost:8080/cities",
  apiUrlComment: "https://localhost:8080/comments",
  apiUrlExtraField: "https://localhost:8080/extraFields",
  apiUrlMessage: "https://localhost:8080/message",
  // apiUrlPicture: "https://localhost:8080/accommodations",
  apiUrlPrice: "https://localhost:8080/price",
  apiUrlReservation: "https://localhost:8080/reservations"
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
