import { GeoResultToAddressPipe } from './geo-result-to-address.pipe';

describe('GeoResultToAddressPipe', () => {
  it('create an instance', () => {
    const pipe = new GeoResultToAddressPipe();
    expect(pipe).toBeTruthy();
  });
});
