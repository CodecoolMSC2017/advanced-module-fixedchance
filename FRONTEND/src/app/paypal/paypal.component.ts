import { Component, OnInit } from '@angular/core';

declare const paypal : any;

@Component({
  selector: 'app-paypal',
  templateUrl: './paypal.component.html',
  styleUrls: ['./paypal.component.css']
})
export class PaypalComponent implements OnInit {
  
  
  addButton = false;

  paypalConfig = {
    env: 'sandbox',
    style: {
        label: 'checkout',
        size: 'responsive',    // small | medium | large | responsive
        shape: 'pill',     // pill | rect
        color: 'black'      // gold | blue | silver | black
    },
    client: {
        sandbox: 'AZDxjDScFpQtjWTOUtWKbyN_bDt4OgqaF4eYXlewfBP4-8aqX3PiV8e1GWU6liB2CUXlkA59kJXE7M6R',
    },
    commit: true,
    payment: (data, actions) => {
        return actions.payment.create({
            payment: {
                transactions: [{
                    amount: {
                        total: localStorage.getItem("coursePrice"),
                        currency: 'USD'
                    }
                }]
            }
        });
    },
    onAuthorize: (data, actions) => {
        return actions.payment.execute().then((payment) => {
          window.alert('Payment Complete!');
          window.location.replace("successful");
        });
    }
};

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewChecked(): void {
    if (!this.addButton) {
      paypal.Button.render(this.paypalConfig, '#paypal-button-container');
      this.addButton = true;
    }
  }
}
