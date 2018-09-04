import { Component, OnInit } from '@angular/core'; 
import * as $ from 'jquery';

import * as data from './generated.json';


@Component({
selector: 'app-places-list',
templateUrl: './places-list.component.html',
styleUrls: ['./places-list.component.css']
})

export class PlacesListComponent implements OnInit {

places : any[] = [];
constructor() { }
  ngOnInit() {
  
  var i = 0;
  while(data[i]){
  	this.places.push(data[i])
  	i++;
  }

  console.log(this.places);

  $(function() {
    Metis.metisSortable();
    Metis.MetisTable();
  
  });


}

}

