export const getLastYearDiff = (arr) => {
  return Math.abs(arr[arr.length - 1] - arr[0]);
};

export const getLastYearDiffText = (arr) => {
  return arr[arr.length - 1] - arr[0] >= 0 ? '상승' : '하락';
};

export const getPrevQuaterDiff = (arr) => {
  return Math.abs(arr[arr.length - 1] - arr[arr.length - 2]);
};

export const getPrevQuaterDiffText = (arr) => {
  return Math.abs(arr[arr.length - 1] - arr[arr.length - 2]) >= 0 ? '상승' : '하락';
};
