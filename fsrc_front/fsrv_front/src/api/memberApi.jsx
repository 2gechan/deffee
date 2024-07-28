import axios from "axios";

export const API_SERVER_HOST = "http://localhost:8000";

const prefix = `${API_SERVER_HOST}/api/member`;

export const join = async (memberObj) => {
  const res = await axios.post(`${prefix}/`, memberObj);

  return res.data;
};
