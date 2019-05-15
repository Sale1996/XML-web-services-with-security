import { ErrorService } from './../services/error.service';
import { NotificationService } from './../services/notification.service';
import { Injectable, Injector, ErrorHandler } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })
export class GlobalErrorHandler implements ErrorHandler {

    constructor(private injector: Injector) { }

    handleError(error: Error | HttpErrorResponse) {

        const errorService = this.injector.get(ErrorService);
        // const logger = this.injector.get(LoggingService);
        const notifier = this.injector.get(NotificationService);

        let message;
        // let stackTrace;

        if (error instanceof HttpErrorResponse) {
            // Server Error
            message = errorService.getServerMessage(error);
            // stackTrace = errorService.getServerStack(error);
            notifier.showError(message);
        } else {
            // Client Error
            message = errorService.getClientMessage(error);
            // stackTrace = errorService.getClientStack(error);
            notifier.showError(message);
        }

        // console.error(error);
    }
}
