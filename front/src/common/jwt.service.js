import jwt from 'jsonwebtoken';

const TOKEN_KEY = 'token';
const SECRET = "secret123456789112233566777hhhggsssssaa";

export const getToken = () => {
  return window.localStorage.getItem(TOKEN_KEY);
};

export const saveUserLogged = usr => {
  window.localStorage.setItem(TOKEN_KEY, usr.token);
};

export const destroyToken = () => {
  window.localStorage.removeItem(TOKEN_KEY);
};

export const getUserLogged = () => {
  const token = window.localStorage.getItem(TOKEN_KEY);
  if (!token) {
    return '';
  }
  const tokenDecoded = jwt.verify(token, SECRET);
  return {name: tokenDecoded.sub, role: tokenDecoded.role}
};

export default { getToken, saveUserLogged, destroyToken, getUserLogged };
