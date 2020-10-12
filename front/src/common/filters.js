import { format } from 'date-fns';

export const dateTime = date => {
  if (!date) {
    return '';
  }

  return format(new Date(date), 'dd/MM/yyyy HH:mm');
};

export const dateOnly = date => {
  if (!date) {
    return '';
  }
  return format(new Date(date), 'dd/MM/yyyy');
};

export const error = errorValue => {
  return `${errorValue[0]}`;
};

export const currency = value => {
  if (typeof value !== 'number') {
    return value;
  }
  var formatter = new Intl.NumberFormat('pt-br', {
    style: 'currency',
    currency: 'BRL',
    minimumFractionDigits: 2
  });
  return formatter.format(value);
};

export const toRound = val => {
  return Math.round(val);
};

export const toPercentage = val => {
  if (val === 0) {
    return '0%';
  }

  if (val === 1) {
    return '100%';
  }

  return (val * 100).toFixed(2) + '%';
};

