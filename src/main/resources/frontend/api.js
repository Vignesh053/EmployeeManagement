const BASE_REST_API_URL = "http://localhost:8080/employee";

const getAllEmployee = () => axios.get(BASE_REST_API_URL + "/fetchlist");

// create posy api using axiso

const addEmployee = (employee) => {
    return axios.post(BASE_REST_API_URL + "/add", employee);
}

const deleteEmployee = (employeeId) => {
    return axios.delete(BASE_REST_API_URL + `/del/${employeeId}`);
}

const updateEmployee = (employee, id) => {
    return axios.post(BASE_REST_API_URL+`/update/${id}`, employee);
}