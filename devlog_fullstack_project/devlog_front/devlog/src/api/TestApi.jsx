export const API_HOST = "http://localhost:8080";

export const test = async () => {
  const res = await fetch(`${API_HOST}/test`);

  return res.data;
};
