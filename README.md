## Prerequisites
To run the application locally, ensure you have Docker Compose installed. If you haven't, follow the [Docker Compose installation guide](https://docs.docker.com/compose/install/).

## Getting Started

Having docker compose installed, follow these steps to run the application:

1. Clone the repository
2. Go into <i>devops/local</i> directory
3. Inside <i>devops/local</i> directory, run the following command to start the application:
<code> docker compose up </code>
4. Open your browser and go to: <link>http://localhost:8080</link>
5. When you are done using the application, run the following command inside <i>devops/local</i> directory to remove docker containers:
<code> docker compose down </code>
## Architecture
![Image](https://github.com/user-attachments/assets/323fc03b-6890-40a4-bdfb-92b7301242c5)
## Technologies Used:
- **Frontend:**
  - HTML
  - CSS
  - JavaScript
- **Backend:**
  - Spring Boot (Spring Data JPA, Spring Security)
- **Tests:**
  - Mockito, AssertJ, RestAssured, Testcontainers
- **Database:**
  - PostgreSQL, Flyway
- **Deployment:**
  - AWS
  - Docker
- **CI/CD:**
  - GitHub Actions
## About the project

Some wise man once said you should never call yourself a self-taught software engineer unless you've created a to-do list application, so here is mine!

### What makes this app unique?

There are many to-do lists out there, but not as many good ones. The advantages of this project are:
- Docker - the containerization of the app made it easy for users to run it and for me to deploy it. Thanks to the application being containerized, it can be easily deployed across all environments, from the cloud to a local machine.
- Tests - To ensure the server works as expected, there are plenty of tests that include integration, web and unit tests. The server has been thoroughly tested, which is demonstrated by its high level of test coverage equal to almost 100%.
- Continous Integration/Delivery - configured CI/CD workflow using GitHub actions made it much easier to deliver new features straight to the user.
- User interface - The very basic, but yet effective graphical interface enables us to use the application straight from the browser without the need to use Postman or other HTTP client.

## Some features
# Login page
![login](https://github.com/user-attachments/assets/87515729-d48c-4184-a8a7-96c6e25576d0)
*Enables user to log into the application*
# Register page
![register-success](https://github.com/user-attachments/assets/85b882d7-f340-4a58-9a9d-780021e46bd4)
![register](https://github.com/user-attachments/assets/60236762-8f82-44bd-bed1-bbfdaae9bedc)
*Confirm user registration*
# Task management
![notasks](https://github.com/user-attachments/assets/d2f32380-3946-4afe-9625-d9ea8751eabd)
![tasks](https://github.com/user-attachments/assets/e0d68ebc-6e63-4945-9c49-64b5e3f65e21)
*Manage your todo list*

## App versions

v1 - The first version of the application enabled users to perform basic CRUD operations such as displaying, creating, deleting and changing the status of tasks.

v2 (current) - With this update, the functionality of creating user account and logging into the application was provided. As a result, every user can manage his own tasks and be sure that no other user can view and / or perform any operations on his tasks.

Plans for further updates:
- Change frontend implementation from pure HTML / CSS / JavaScript to React.js to enhance user experience
- Add new functionalities regarding tasks, such as recurring tasks
