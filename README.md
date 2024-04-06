# Employee Management Application

## Overview

An Employee management full stack application with spring boot, HTML, CSS, and JS. 

## Features

- Login/Registration
- Employee listing
- Add/update employee details
- Admin privileges for deleting employees

## Technology Stack

- Backend: Spring Boot
- Frontend: HTML, CSS, JavaScript
- Security: Spring Security
- Database: MySQL with Spring JPA
- Communication: Axios for AJAX calls

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MySQL Server 5.7 or higher

### Installation

1. clone the repository:
```
git clone https://github.com/Vignesh053/EmployeeManagement.git
```

2. Setup database:
```
//In app.properties use your own database credentials
spring.datasource.url=jdbc:mysql://localhost:3306/my_db(your existing database name)
spring.datasource.username=root(your username)
spring.datasource.password=password(your password)
```

3. configuring database:
```
// insert users and roles in mysql workbench

insert into users(email,password,username)values("john@gamil.com","$2a$10$db6Ul1dzzUYvuqmsLqJhpecwcjTVqL4nKREHneVmjDH4yrD.qTIM2","john");

insert into users(email,password,username)values("admin@gmail.com","$2a$10$db6Ul1dzzUYvuqmsLqJhpecwcjTVqL4nKREHneVmjDH4yrD.qTIM2","admin");

insert into roles(name)values("ROLE_ADMIN");

insert into roles(name)values("ROLE_USER");

insert into users_roles values(1,1);

insert into users_roles values(2,1);

insert into users_roles values(2,2);
```
   this will make sure necessary details in database are in place.


### Running the Application

1. after installation, open intellij or any other IDE of your choice and run the backend application(MiniProjectApplication), this will open terminal with springboot logo along with the message saying application started successfully.

2. now for the frontend, open index.html or login.html.

3. login with user credentials to access the main page where you can add, edit or delete employee.

User:
```
username - john
password - password
```
Admin:
```
username - admin
password - password
```

## Screenshots

### Login Page

![Login Page](https://github.com/Vignesh053/EmployeeManagement/blob/192413a035a5fff33820963be7b4cdf54bb61a3c/src/main/resources/screenshots/login.png)

### Registration Page

![Registration Page](https://github.com/Vignesh053/EmployeeManagement/blob/192413a035a5fff33820963be7b4cdf54bb61a3c/src/main/resources/screenshots/register.png)

![Registration Page](https://github.com/Vignesh053/EmployeeManagement/blob/192413a035a5fff33820963be7b4cdf54bb61a3c/src/main/resources/screenshots/registerConfirm.png)

User saved in database with USER role

![Users table in database](https://github.com/Vignesh053/EmployeeManagement/blob/192413a035a5fff33820963be7b4cdf54bb61a3c/src/main/resources/screenshots/UserIndatabase.png)

### Main page for user

![Employee List](https://github.com/Vignesh053/EmployeeManagement/blob/192413a035a5fff33820963be7b4cdf54bb61a3c/src/main/resources/screenshots/employeeList_user.png)

### Main page for admin

![Employee List](https://github.com/Vignesh053/EmployeeManagement/blob/192413a035a5fff33820963be7b4cdf54bb61a3c/src/main/resources/screenshots/employeeList_admin.png)

### Adding new employee

![Add employee form](https://github.com/Vignesh053/EmployeeManagement/blob/192413a035a5fff33820963be7b4cdf54bb61a3c/src/main/resources/screenshots/addemployee.png)

### After succesfully adding new employee

![Employee list with new employee](https://github.com/Vignesh053/EmployeeManagement/blob/192413a035a5fff33820963be7b4cdf54bb61a3c/src/main/resources/screenshots/afterAddEmployee.png)

### Updating existing employee

![update employee form](https://github.com/Vignesh053/EmployeeManagement/blob/192413a035a5fff33820963be7b4cdf54bb61a3c/src/main/resources/screenshots/updateEmployee.png)

### After succesfully updating employee

![Employee list with updated employee](https://github.com/Vignesh053/EmployeeManagement/blob/192413a035a5fff33820963be7b4cdf54bb61a3c/src/main/resources/screenshots/afterUpdateEmployee.png)

### Deleting an Employee
--- before deleting
![Employee list](https://github.com/Vignesh053/EmployeeManagement/blob/192413a035a5fff33820963be7b4cdf54bb61a3c/src/main/resources/screenshots/employeeList_admin.png)

--- after deleting
![Employee list](https://github.com/Vignesh053/EmployeeManagement/blob/192413a035a5fff33820963be7b4cdf54bb61a3c/src/main/resources/screenshots/employeeDelete.png)
