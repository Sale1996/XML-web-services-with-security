import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { City } from 'src/app/model/city.model';
import { CityService } from 'src/app/services/city.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmationModalComponent } from 'src/app/_shared/confirmation-modal/confirmation-modal.component';
import { CitiesSingleModalComponent } from './cities-single-modal/cities-single-modal.component';


@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.css']
})
export class CitiesComponent implements OnInit {

  cities$: Observable<City[]>

  constructor(private cityService: CityService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getCities();
  }

  getCities() {
    this.cities$ = this.cityService.getCities();
  }

  deleteCity(city: City) {
    const deleteModalRef = this.modalService.open(ConfirmationModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });
    deleteModalRef.componentInstance.title = 'Delete City';
    deleteModalRef.componentInstance.message = 'Are you sure you want to delete ' + city.name + '?';
    deleteModalRef.componentInstance.answer.subscribe(
      (answer: boolean) => {
        if (answer) {
          this.cityService.deleteCity(city.cityId).subscribe(
            () => {
              this.getCities();
            }
          );
        }
      }
    );
  }




  openCityModal(id?: number) {
    const agentModalRef = this.modalService.open(CitiesSingleModalComponent,
      {
        centered: true,
        backdropClass: 'custom-modal-backdrop'
      });

    if (id) {
      agentModalRef.componentInstance.id = id;
    }
    agentModalRef.componentInstance.service.subscribe(
      (city: City) => {
        if (city.cityId) {
          this.cityService.updateCity(city).subscribe(
            () => {
              this.getCities();
            }
          );
        } else {
          this.cityService.createCity(city).subscribe(
            () => {
              this.getCities();
            }
          );
        }
      }
    );
  }

}
