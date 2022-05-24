import { Ship } from './ship';

describe('Ship', () => {
  function getRandomInteger(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min)) + min;
  }

  it('should create an instance', () => {
    expect(new Ship(
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101)
    )).toBeTruthy();
  });
});
