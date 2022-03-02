[![Build Status](https://app.travis-ci.com/malletmustdie/todo-list.svg?branch=master)](https://app.travis-ci.com/malletmustdie/todo-list)

# TODO LIST
+ [Описание](#Описание-проекта)
+ [Технологии](#Используемые-технологии)
+ [Функционал](#Функционал-приложения)

## Описание проекта
Todo list - web приложение, аналог простейшего менеджера задач.

## Используемые технологии
+ **Java 14**,  **Servlet/JSP**
+ **PostgreSQL**, **Hibernate**
+ **Bootstrap**, **AJAX**, **Jquery**
+ **Maven**

## Функционал приложения
Общий вид

![alt text](https://github.com/malletmustdie/todo-list/blob/master/src/main/webapp/image/main.jpg)

Авторизация

![alt text](https://github.com/malletmustdie/todo-list/blob/master/src/main/webapp/image/auth.jpg)

Для всех полей при авторизации и регистрации стоит валидация

![alt text](https://github.com/malletmustdie/todo-list/blob/master/src/main/webapp/image/authValidation.jpg)

![alt text](https://github.com/malletmustdie/todo-list/blob/master/src/main/webapp/image/regValidation.jpg)

Для создания задачи реализована форма, задача добавляется в таблицу по нажатию кнопки "Отправить"

![alt text](https://github.com/malletmustdie/todo-list/blob/master/src/main/webapp/image/addTask.jpg)

Для отображения задач реализован чекбокс, при нажатии на него выводятся все созданные задачи.
Без чекбокса отображаются только текущие задачи.

![alt text](https://github.com/malletmustdie/todo-list/blob/master/src/main/webapp/image/allTasks.jpg)

![alt text](https://github.com/malletmustdie/todo-list/blob/master/src/main/webapp/image/unresolvedTasks.jpg)
