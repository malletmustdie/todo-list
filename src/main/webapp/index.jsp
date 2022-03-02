<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.todo.model.User" %>
<!doctype html>
<html lang="en" xmlns:c="">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script rel="stylesheet" src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>TODO List</title>
    <script>
        $(document).ready(function () {
            showAllTasks();
            getCurrentUser();
            addNewTask();
        });

        function getCurrentUser() {
            $.ajax({
                type: 'POST',
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
                    $.ajax({
                        method: 'POST',
                        url: 'http://localhost:8080/todo/save.do',
                        data: {desc: description},
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
                url: 'http://localhost:8080/todo/items.do',
                dataType: 'json',
                success: function ($data) {
                    createTable($data);
                }
            });
        }

        function showUnresolvedTasks() {
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/todo/unresolved-items.do',
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
            let created = data['created'];
            let author = data['user'].username;
            let done = data['done'];
            result += '<tr class="rows">'
                + '<td id="id">' + id + '</td>'
                + '<td>' + desc + '</td>'
                + '<td>' + author + '</td>'
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
                url: 'http://localhost:8080/todo/update.do',
                data: {id: id},
                success: function () {
                    location.reload();
                }
            });
        }

        function deleteTask(id) {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/todo/delete.do',
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
    </script>
</head>
<body>
<%
    HttpSession hs = request.getSession();
    User user = (User) hs.getAttribute("user");
%>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header" style="font-weight: bold; font-size: larger">
                TODO list - simple task manager
            </div>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link disabled" tabindex="-1" aria-disabled="true" id="current"></a>
                        </li>
                    </ul>
                    <form class="form-inline my-2 my-lg-0">
                        <% if (user != null) { %>
                        <button type="button" class="btn btn-light text-dark me-2" onclick="logout()">Выйти</button>
                        <% } else { %>
                        <button type="button" class="btn btn-primary" onclick="signUp()">Регистрация</button>
                        <button type="button" class="btn btn-light text-dark me-2" onclick="login()">Войти</button>
                        <% } %>
                    </form>
                </div>
            </nav>
        </div>
    </div>
    <% if (user != null) { %>
    <div class="row pt-2">
        <div class="card" style="width: 100%">
            <div class="card-header" style="font-weight: bold; font-size: larger">
                Добавить новую задачу
            </div>
            <div class="card-body">
                <form id="new-task-form">
                    <div class="form-group">
                        <label for="description" style="font-weight: bold">Описание задачи</label>
                        <input type="text" class="form-control" id="description"/>
                    </div>
                    <input type="submit"/>
                </form>
            </div>
        </div>
    </div>
    <div class="row pt-3">
        <div class="form-check pb-2">
            <input type="checkbox" id="showTasks" name="showTasks" value="showTasks" checked onchange="filterTasks()">
            <label class="form-check-label" for="showTasks">Показать все задачи</label>
        </div>
    </div>
    <div class="row pt-3">
        <div class="card" style="width: 100%">
            <div class="card-header" style="font-weight: bold; font-size: larger">
                Список добавленных заданий
            </div>
            <div class="card-body">
                <table class="table table-bordered" id="table">
                    <thead>
                    <tr>
                        <th style="width: 5%">ID</th>
                        <th style="width: 25%">Description</th>
                        <th style="width: 12%">Author</th>
                        <th style="width: 15%">Created</th>
                        <th style="width: 1%">Status</th>
                        <th style="width: 1%">Action</th>
                    </tr>
                    </thead>
                    <tbody id="tableBody">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <% } %>
</div>
</body>
</html>