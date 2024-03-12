const employeeTable = document.getElementById("employeetable");

const addEmployeeButton = document.getElementById("addemployee");

const updateEmployeeFormText = ``;

//get update form and make it appear when handleupdate called
//

const employeeTableContainer = document.getElementById("employeetablecontainer");
const updateEmployeeContainer = document.getElementById("updateemployeecontainer");
let updateFirstName = document.getElementById("updatefirstname");
let updateLastName = document.getElementById("updatelastname");
let updateEmail = document.getElementById("updateemail");
let updateEmployeeId = 0;
const updateEmployeeForm = document.getElementById("updateemployeeform");
const backButton = document.getElementById("backbtn").addEventListener('click', viewTable);



const viewTable = () =>{ 
    if(employeeTableContainer.style.display == 'none' && updateEmployeeContainer.style.display == 'block'){
        employeeTableContainer.style.display = 'block';
        updateEmployeeContainer.style.display = 'none';
    }else if(employeeTableContainer.style.display == 'block' && updateEmployeeContainer.style.display == 'none'){
              employeeTableContainer.style.display = 'none';
              updateEmployeeContainer.style.display = 'block';
           }
}


const handleUpdate = (btn) => {

    const employeeId = btn.id;
    const employee = JSON.parse(btn.dataset.employee)
    
    viewTable();
    updateEmployeeId = employeeId;
    updateFirstName.value = employee.firstname;
    updateLastName.value = employee.lastname;
    updateEmail.value = employee.email;

    
}


// handle delete function called when button with deletebtn class is clicked
// uses axios delete mapping function to delete employee in database
const handleDelete = (btn) => {
    const employeeId = btn.id;

    deleteEmployee(employeeId).then(() => {
        displayEmployees();
        
    })

    
}



// when page reloads, get all employees from database and load them inside table, 
// when loading each row, put employee id on each nutton so when the button is pressed, we can identify that btn
const displayEmployees = async() => {
    try {
        
    //getAllEmployees is function in api.js that fetch all employees from database
    const employees = await getAllEmployee();

    //set table header everytime display employees function gets called
    employeeTable.innerHTML = "<tr><th>First Name</th><th>Last Name</th><th>Email Id</th><th>Actions</th></tr>";

    //set each employee in row inside table tag, set employee id for each buttons dynamically
    employees.data.forEach(element => {
        const employeeJson = JSON.stringify(element);
        employeeTable.innerHTML += `<tr><td>${element.firstname}</td><td>${element.lastname}</td><td>${element.email}</td><td><button class="updatebtn" data-employee=${employeeJson} id='${element.id}'>Update</button>
        <button class="deletebtn" id='${element.id}'>Delete</button>
        <button class="viewbtn" id='${element.id}'>View</button></td></tr>`;
    });

    } catch (error) {
        console.error(error);
        
    }
    
    
    //each buttons have to be fetched from html after updating them so that every 
    //button stays updated to call delete, update and view functions can be used
    const deleteButtons = document.querySelectorAll(".deletebtn");

    deleteButtons.forEach(btn => {
       btn.addEventListener('click', () => handleDelete(btn));
    })


    const updateButtons = document.querySelectorAll(".updatebtn");

    updateButtons.forEach(btn => {
        btn.addEventListener('click', () => handleUpdate(btn))
    })


}




updateEmployeeForm.addEventListener('submit', (e)=>{
    e.preventDefault();

    const employeeToUpdate = {'firstname': updateFirstName.value, 'lastname': updateLastName.value, 'email': updateEmail.value };

    
    updateEmployee(employeeToUpdate, updateEmployeeId ).then(response =>{
        viewTable();
        displayEmployees();
        console.log("employee updated", response.data);
    }).catch(e=>{
        console.error(e);
    })

    
    
})




//submit form to create new employees in database
//first get all the elements in html

const firstNameInput = document.getElementById("firstname");
const lastNameInput = document.getElementById("lastname");
const emailInput = document.getElementById("email");
const addEmployeeForm = document.getElementById("addemployeeform");



addEmployeeButton.addEventListener('click', ()=> {
    if (addEmployeeForm.style.display === 'none') {
        addEmployeeForm.style.display = 'block';
      } else {
        addEmployeeForm.style.display = 'none';
      }
})
//then use addeventlisteners so that when details submitted, we can use the value in each input and create in db
addEmployeeForm.addEventListener('submit', (e)=> {
    e.preventDefault();

    //since employee is accepted as request body for post endpoint, 
    // create and assign the values for each field in employee object and send it as request body
    const employee = {
        'firstname': firstNameInput.value,
        'lastname': lastNameInput.value,
        'email': emailInput.value
    }

    //axios postmapping method 
    addEmployee(employee).then(response => {
        displayEmployees();
        console.log("employee created succesfully", response.data)
        
    }).catch(error=> {
        console.error(error)
    })

    firstNameInput.value = '';
    lastNameInput.value = '';
    emailInput.value = '';

})




displayEmployees();
