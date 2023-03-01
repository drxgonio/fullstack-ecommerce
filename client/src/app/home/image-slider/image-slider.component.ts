import { config } from './../../../config/local';
import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { Carausel } from 'src/config/model';

@Component({
  selector: 'app-image-slider',
  templateUrl: './image-slider.component.html',
  styleUrls: ['./image-slider.component.scss'],
  providers: [NgbCarouselConfig]
})
export class ImageSliderComponent implements OnInit {

  carausel: Array<Carausel>;

  constructor(config: NgbCarouselConfig) {
    config.interval = 10000;
    config.wrap = true;
    config.keyboard = false;
  }

  ngOnInit() {
    config.carausel = [
      {
        title: "DEV",
        text: "DEV",
        imageUrl: "https://res.cloudinary.com/keyist/image/upload/v1603226535/slider1_ccifkp.png"
      },
      {
        title: "TEST",
        text: "TEST",
        imageUrl: "https://res.cloudinary.com/keyist/image/upload/v1603226536/slider2_fmrxrd.jpg"
      }
    ]
    this.carausel = config.carausel;
  }

}
