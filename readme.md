# Simple Spring Boot 3 REST API with Spring Security 6 JWT secured Endpoints Sample

## Overview

Simple Spring Boot 3 with Spring Security 6 JWT Sample.

| User    | Role         | Password |
|---------|--------------|----------|
| `user1` | `ROLE_ADMIN` | `test`   |
| `user2` | `ROLE_USER`  | `test`   |

## Endpoints

`GET /hello` Everyone are allowed to access this endpoint, also unauthenticated.

`POST /auth` Login endpoint which requires username and password

`GET /secret` Only authenticated users are allowed to access this endpoint. Regardless of their role.

`GET /admin` Only users with admin role are allowed to access this endpoint

## Testing

There are some sample http requests in the **/test/http** folder.
You need IntelliJ Ultimate to run these requests.
Use it as a reference if you do not have IntelliJ Ultimate 🧑‍💻
