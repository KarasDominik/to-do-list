document.addEventListener("DOMContentLoaded", function () {
    updateTable();
});

function updateTable() {
    fetchTasks().then(response => {
        const tasks = response.tasks;
        if (tasks.length === 0) {
            document.getElementById("output").innerHTML = '<p>No tasks yet! Add one above!</p>';
        } else {
            document.getElementById("output").innerHTML = generateTable(tasks);
        }
    });
}

async function fetchTasks() {
    const response = await fetch("http://localhost:8080/api/v1/task");
    return await response.json();
}

function generateTable(tasks) {
    let table = `<table>`;
    table += `<tbody>`;

    tasks.forEach(task => {
        table += `<tr id="task">`;
        table += `<td><input type="checkbox" ${task.done ? 'checked' : ''} onclick="updateTask('${task.taskId}')"></td>`;
        table += `<td id="${task.done ? 'done-task' : ''}">${task.content}</td>`;
        table += `<td>
                        <svg id="trash-bin" onclick="deleteTask('${task.taskId}')" xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
                            <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
                         </svg>
                  </td>`;
        table += `</tr>`;
    });

    table += `</tbody></table>`;

    return table;
}

async function addTask() {
    const task = document.getElementById("task-input").value;
    await fetch("http://localhost:8080/api/v1/task",
        {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                content: task
            })
        });
    clearForm();
    updateTable();
}

function clearForm() {
    document.getElementById("task-input").value = "";
}

async function updateTask(taskId) {
    await fetch("http://localhost:8080/api/v1/task/" + taskId,
        {
            method: 'PUT'
        });
    updateTable();
}

async function deleteTask(taskId) {
    await fetch("http://localhost:8080/api/v1/task/" + taskId,
        {
            method: 'DELETE'
        });
    updateTable();
}