import { Pipe, PipeTransform } from '@angular/core';
import { Address } from '../models/address';

@Pipe({
  name: 'geoResultToAddress',
})
export class GeoResultToAddressPipe implements PipeTransform {
  transform(addressArray: any[]): Address {
    console.log(addressArray);

    let address: Address = new Address();
    for (let comp of addressArray) {
      let street: string = '';
      switch (comp.types[0]) {
        case 'street_number':
          address.street = comp.long_name;
          break;
        case 'route':
          address.street += ' ' + comp.long_name;
          break;
        case 'locality':
          address.city = comp.long_name;
          break;
        case 'administrative_area_level_1':
          address.state = comp.short_name;
          break;
        case 'postal_code':
          address.zip = comp.long_name;
          break;
      }
    }
    return address;
  }
}
