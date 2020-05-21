import {calculateTip, doWork} from '../src/math';

describe('math tests', () => {
  it('should add numbers', function () {
    expect(1 + 1).toBe(2);
  });

  it('should calculate tip correctly', function () {
    const result = calculateTip(2, 0.1);
    expect(result).toBe(2.2);
  });

  it('should calculate tip correctly with default tip of 0.2', function () {
    const result = calculateTip(3);
    expect(result).toBe(3.3);
  });

  it('should fail calculate tip with negative tip', function () {
    expect(() => calculateTip(1, -0.1)).toThrowError();
  });

  it('should test async function', (done) => {
    setTimeout(() => {
      expect(1 + 1).toBe(2);
      done();
    }, 2000);
  });

  test('should test async add function', (done) => {
    doWork().then(sum => {
      expect(sum).toBe(8);
      done();
    })
  }, 10000);

  test('should test async add function in another way', async () => {
    const sum = await doWork();
    expect(sum).toBe(8);
  }, 10000);
});
