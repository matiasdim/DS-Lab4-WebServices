# Server application
Lab 04 - Ditributed systems CIS656 - Server application.

## Based on the original source code: [gae-restlet-example](https://github.com/jengelsma/gae-restlet-example)
A webservices app that  serves as an API for a [chat client](https://github.com/matiasdim/DS-Lab4-ClientChat) 

## Tree
```bash
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── edu
│   │   │   │   ├── gvsu
│   │   │   │   │   ├── restpi
│   │   │   │   │   │   ├── ErrorMessage.java
│   │   │   │   │   │   ├── OfyHelper.java
│   │   │   │   │   │   ├── User.java
│   │   │   │   │   │   ├── UserResource.java
│   │   │   │   │   │   ├── UsersResource.java
│   │   │   │   │   │   ├── WebServiceApplication.java
```
* **ErrorMessage.java:** Provided class to handle errors.
* **OfyHelper.java:** Provided class. This isrequired to let JSP's access Ofy.
* **User.java:** The user model.
* **UserResource.java:** User resource that handles GET, PUT and DELETE HTTP methods via "/v1/users/{name}" path
* **UsersResource.java:** Users resource that handles GET and POST HTTP methods via "/v1/users" path
* **WebServiceApplication.java:** The class that starts a standard-alone HTTP server to processes.
