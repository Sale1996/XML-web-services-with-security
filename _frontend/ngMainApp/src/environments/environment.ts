// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiUrlAuth: 'http://localhost:8080/',
  apiUrlUser: 'http://localhost:8080/user',
  apiAccomodations: 'http://localhost:8081/accommodations',
  apiCities: 'http://localhost:8081/cities',
  apiMessages: 'http://localhost:8080/message',
  apiReservations: 'http://localhost:8082/reservations',
  apiRating: 'http://localhost:8082/ratings',
  apiExtraField: 'http://localhost:8081/extraFields',
  apiCategories: 'http://localhost:8081/categories',
  apiTypes: 'http://localhost:8081/types',
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
