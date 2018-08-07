import { Component, ViewEncapsulation, ModuleWithProviders } from '@angular/core';
import { AOS } from '../../bower_components/aos/dist/aos';
import { OrderPipe } from 'ngx-order-pipe';

declare var require: any;
@Component({
  encapsulation: ViewEncapsulation.None,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss', '../../bower_components/aos/dist/aos.css']
})
export class AppComponent {
  title = 'app';

  ngOnInit() {
    const eos = require('../../bower_components/aos/dist/aos');
    eos.init({
      duration: 1500,
    });
  }
}
