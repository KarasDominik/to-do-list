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

function generateTable(tasks) {
    let table = `<table>`;
    table += `<tbody>`;

    tasks.forEach(task => {
        table += `<tr id="task">`;
        table += `<td><input type="checkbox" ${task.done ? 'checked' : ''} onchange="updateTask('${task.taskId}')"></td>`;
        table += `<td id="${task.done ? 'done-task' : ''}">${task.content}</td>`;
        table += `<td><span id="trash-bin" class="glyphicon glyphicon-trash" onclick="deleteTask('${task.taskId}')"></span></td>`;
        table += `</tr>`;
    });

    table += `</tbody></table>`;

    return table;
}

async function deleteTask(taskId) {
    await fetch("http://localhost:8080/api/v1/task/" + taskId,
        {
            method: 'DELETE'
        });
    updateTable();
}

async function updateTask(taskId) {
    await fetch("http://localhost:8080/api/v1/task/" + taskId,
        {
            method: 'PUT'
        });
    updateTable();
}

async function fetchTasks() {
    const response = await fetch("http://localhost:8080/api/v1/task");
    return await response.json();
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