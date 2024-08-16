# To-do list application

Some wise man once said you should never call yourself a self-taught software engineer unless you've created a to-do list application, so here is mine!

## Prerequisites
Ensure you have Docker Compose installed. Follow the [Docker Compose installation guide](https://docs.docker.com/compose/install/) if you haven't done so.
## Getting Started

To run the application:

1. Clone the repository
2. Go into docker directory
3. Inside <i>docker</i> directory, run the following command to start the application:
<code> docker compose up </code>
4. Open your browser and go to: <link>http://localhost:8080</link>
5. When you are done using the application, run the following command inside <i>docker</i> directory to remove docker containers:
<code> docker compose down </code>

## App versions

v1 - The first version of the application enabled users to perform basic CRUD operations such as displaying, creating, deleting and changing the status of tasks.

v2 (current) - With this update, the functionality of creating user account and logging into the application was provided. As a result, every user can manage his own tasks and be sure that no other user can view and / or perform any operations on his tasks.

Plans for further updates:
- Change frontend implementation from basic HTML / CSS / JavaScript to React.js to enhance user experience
- Add new functionalities regarding tasks, such as recurring tasks

### What makes this app unique?

There are many to-do lists out there, but not as many good ones. The advantages of this project are:
- User interface - The very basic, but yet effective graphical interface enables us to use the application straight from the browser without the need to use Postman or other HTTP client.
- Docker - the containerization of the app made it easy for users to run it and for me to deploy it. Having the application packed into container, I have the possibility to deploy it onto AWS very quickly and easily.
- Tests - To ensure the server works as expected, there are plenty of tests that include integration, web and unit tests. The server has been thoroughly tested, which is demonstrated by its high level of test coverage equal to almost 100%.
