// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiUrlAccommodation: "http://localhost:8095/accommodations",
  apiUrlAccommodationUnit: "http://localhost:8095/accommodationUnits",
  apiUrlType: "http://localhost:8095/types",
  apiUrlAgent: "http://localhost:8095/agent",
  apiUrlCategory: "http://localhost:8095/categories",
  apiUrlCity: "http://localhost:8095/cities",
  apiUrlComment: "http://localhost:8095/comments",
  apiUrlExtraField: "http://localhost:8095/extraFields",
  apiUrlMessage: "http://localhost:8095/message",
  apiUrlPicture: "http://localhost:8095/pictures",
  apiUrlPrice: "http://localhost:8095/price",
  apiUrlReservation: "http://localhost:8095/reservations",
  apiUrlAuth: 'http://localhost:8080/',

};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
