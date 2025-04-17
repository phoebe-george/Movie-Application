# 🎬 MovieApp Backend

T# 🎬 Movie App

A full-stack Movie Management Application that allows users to register, login, and explore movie information fetched from the OMDB API. Admin users can manage movie data, including adding or removing movies.

## 🛠 Technologies Used

### Frontend (Angular)
- Angular 16+
- TypeScript
- Angular Material / SCSS
- Session Storage for auth token handling
- OMDB API for fetching movie info

### Backend (Spring Boot)
- Spring Boot 3.4.x
- Spring Security (JWT-based auth)
- Spring Data JPA
- postgres
- REST APIs

---
## 🚀 Features

### 👥 User Features
- Register / Login
- View list of movies
- View detailed info about a movie
- Search for new movies via OMDB API

### 👨‍💼 Admin Features
- Add a movie (fetched from OMDB API)
- Remove a movie
- See search bar to add movies

---

## 🧾 API Endpoints

### Auth
- `POST /auth/register` - Register user
- `POST /auth/login` - Login and receive JWT

### Movie API
- `GET /movies` - Get all saved movies
- `POST /movies/add` - Add a movie (admin only)
- `DELETE /movies/delete?id=IMDB_ID` - Remove a movie
- `GET /movies/search?title=TITLE` - Search for movie from OMDB

---

## 🔐 Authentication & Roles

- JWT token is returned on successful login or registration.
- `sessionStorage` is used to store the token and role.
- Role-based behavior:
  - Admins can add/delete movies
  - Users can only view and search

---

## 🔧 Setup & Run

### 🖥 Backend (Spring Boot)

1. Clone the project
2. Open in your IDE
3. Configure `application.properties` with your postgres DB
4. Run the project on port `8083`
