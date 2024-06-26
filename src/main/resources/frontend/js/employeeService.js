const BASE_REST_API_URL = "http://localhost:8080/employee";



// Add a request interceptor
axios.interceptors.request.use(function (config) {
    config.headers["Authorization"] = getToken();
    return config;
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
});


const getAllEmployee = () => axios.get(BASE_REST_API_URL + "/fetchlist");



const addEmployee = (employee) => {
    return axios.post(BASE_REST_API_URL + "/add", employee);
}

const deleteEmployee = (employeeId) => {
    return axios.delete(BASE_REST_API_URL + `/del/${employeeId}`);
}

const updateEmployee = (employee, id) => {
    return axios.post(BASE_REST_API_URL+`/update/${id}`, employee);
}