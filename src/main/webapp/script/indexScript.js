$(document).ready(function () {
    getCurrentUser();
    showCategories();
    showAllTasks();
    addNewTask();
});

function showCategories() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/categories',
        dataType: 'json',
        success: function (data) {
            let options;
            $.each(data, function(index, object) {
                options += '<option value="' + object.id + '">' + object.name + '</option>';
            });

            $('#cIds').html(options);
        }
    });
}

function getCurrentUser() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/current',
        dataType: 'json',
        success: function (data) {
            let username = data['username'];
            if (username === "Unregistered user") {
                document.getElementById("current")
                    .innerHTML = "Welcome! Please, sign in or sign up."
            } else {
                document.getElementById("current")
                    .innerHTML = "Welcome back, " + username
            }
        }
    });
}

function addNewTask() {
    $("#new-task-form").submit(
        function () {
            let description = $("#description").val();
            let ids = $("#cIds").val();
            $.ajax({
                method: 'POST',
                url: 'http://localhost:8080/todo/save',
                dataType: 'json',
                data: JSON.stringify({desc : description, cIds : ids}),
                success: function (data) {
                    let result = fillTable(data);
                    $('#tableBody tr:last').after(result);
                }
            });
            location.reload();
        });
}

function filterTasks() {
    let check = $("#showTasks").prop("checked");
    if (check) {
        showAllTasks();
    } else {
        showUnresolvedTasks();
    }
}

function showAllTasks() {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/todo/items',
        dataType: 'json',
        success: function ($data) {
            createTable($data);
        }
    });
}

function showUnresolvedTasks() {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/todo/unresolved-items',
        dataType: 'json',
        success: function ($data) {
            createTable($data);
        }
    });
}

function createTable(data) {
    let items = data;
    let rows = document.getElementsByClassName("rows");
    for (let i = 0; i < rows.length; i++) {
        rows[i].innerHTML = '';
    }
    let result = '';
    for (let i = 0; i < items.length; i++) {
        result += fillTable(items[i]);
    }
    document.getElementById("tableBody").innerHTML = result;
}

function fillTable(data) {
    let result = '';
    let id = data['id'];
    let desc = data['description'];
    let categories = data['categories'];
    let created = data['created'];
    let author = data['user'].username;
    let done = data['done'];
    result += '<tr class="rows">'
        + '<td id="id">' + id + '</td>'
        + '<td>' + desc + '</td><td>';
    for (let i = 0; i < categories.length; i++) {
        result += categories[i]['name'] + '<br>';
    }
    result += '</td><td>' + author + '</td>'
        + '<td>' + created.toLocaleString() + '</td>';
    if (done) {
        result += '<td>'
            + '<img src="image/true.png" width="15" height="15" alt="">'
            + '</td>'
            + '<td>'
            + '<button type="submit" class="btn btn-primary" onclick="deleteTask(' + id + ')">Delete</button>'
            + '</td>'
            + '</tr>'
    } else {
        result += '<td>'
            + '<img src="image/false.png" width="15" height="15" alt="">'
            + '</td>'
            + '<td>'
            + '<button type="submit" class="btn btn-primary" onclick="setStatusDone(' + id + ')">Done</button>'
            + '</td>'
            + '</tr>'

    }
    return result;
}

function setStatusDone(id) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/update',
        data: {id: id},
        success: function () {
            location.reload();
        }
    });
}

function deleteTask(id) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/delete',
        data: {id: id},
        success: function () {
            location.reload();
        }
    });
}

function signUp() {
    window.location.href = "http://localhost:8080/todo/reg.do";
}

function login() {
    window.location.href = "http://localhost:8080/todo/auth.do";
}

function logout() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/logout.do',
        success: function () {
            location.reload();
        }
    });
}