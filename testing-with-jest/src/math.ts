export const calculateTip = (total: number, tipPercent: number = 0.1): number => {

  if (tipPercent < 0) {
    throw new Error("Can't calculate total tip with negative tip");
  }

  const tip = total * tipPercent;
  return total + tip;
}

export const add = (a: number, b: number): Promise<number> => {
  return new Promise(((resolve, reject) => {
    setTimeout(() => {
      resolve(a + b);
    }, 500);
  }))
}

export const doWork = async (): Promise<number> => {
  const firstSum = await add(1, 2);
  const secondSum = await add(2, 3);
  const thirdSum = await add(firstSum, secondSum);
  return thirdSum;
}
