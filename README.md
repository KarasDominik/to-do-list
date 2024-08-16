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
