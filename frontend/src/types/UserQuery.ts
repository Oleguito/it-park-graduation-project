export type UserQuery = {
    id : number;
    userName : string;
    email : string;
    username : string;
    password : string;
    login : string;
    languages : string;
    role : string;
    dateInfo : string;
};

/*
"fullName": "string",
  "email": "string",
  "login": "string",
  "languages": [
    {
      "language": "string",
      "level": "string"
    }
  ],
  "role": "string",
  "createdAt": "2024-08-01T14:34:09.843Z",
  "deletedAt": "2024-08-01T14:34:09.843Z"
*/

export type UserCreateQuery = {
    fullName? : string;
    email : string;
    login? : string;
    languages? : string;
    role? : string;
    createdAt? : string;
    deletedAt? : string;
}