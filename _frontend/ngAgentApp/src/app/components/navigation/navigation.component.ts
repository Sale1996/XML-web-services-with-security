import { Component, OnInit } from '@angular/core';
import { Accommodation } from 'src/app/model/accommodation.model';
import { Observable } from 'rxjs';
import { AccommodationService } from 'src/app/services/accommodation.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  accommodaiton$: Observable<Accommodation>;


  constructor(private accommodationService: AccommodationService) { }

  ngOnInit() {
    // this.getAccommodationService();
  }


  getAccommodationService() {
    this.accommodaiton$ = this.accommodationService.getAccommodationById(1);
    // localStorage.setItem('accommodation', this.accommodaiton);

  }

}
