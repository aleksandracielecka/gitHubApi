# githubApi

Certainly! Below is the README.md file structured according to the provided acceptance criteria and written in English:

Github API Consumer
Description
The project is an application that consumes the GitHub API to retrieve a list of user repositories and information about branches for each repository.

Requirements
Java 8 or higher
Maven 3.6.3 or higher
Spring Boot 2.5.5 or higher
RESTful API client (e.g., Postman) for testing endpoints
GitHub Access Token


Installation and Running
Clone the repository to your local machine:

bash
Copy code
git clone https://github.com/yourusername/github-api-consumer.git
Navigate to the project directory:

bash
Copy code
cd github-api-consumer
Compile and build the project using Maven:

Copy code
mvn clean install
Run the application:

bash
Copy code
java -jar target/github-api-consumer-1.0.0.jar
Usage
Endpoints
GET /repositories/{username} - Retrieves a list of GitHub user repositories.
GET /health - Checks the health status of the application.
Example Usage
To retrieve a list of repositories for a user with the username "exampleuser", send a GET request to the /repositories/exampleuser endpoint.

To check the health status of the application, send a GET request to the /health endpoint.

Generating Access Token
To use the GitHub API, you need to generate a personal access token with the appropriate scopes and permissions. Follow these steps to generate a token:

Go to your GitHub account settings.
1. Navigate to "Developer settings" > "Personal access tokens".
2. Click on "Generate new token".
3. Select the scopes and permissions required for your application (e.g., repo for accessing repositories).
4. Click on "Generate token" and copy the generated token.
5. Use this token in your application for authenticating requests to the GitHub API.
6. Make sure to keep your access token secure and do not expose it publicly.

Response Format
List of Repositories Response
json
Copy code
{
"repositories": [
{
"name": "repo1",
"ownerLogin": "exampleuser",
"branches": [
{
"name": "main",
"lastCommitShare": "2023-10-12T10:00:00Z"
},
{
"name": "feature-branch",
"lastCommitShare": "2023-09-28T15:30:00Z"
}
]
},
{
"name": "repo2",
"ownerLogin": "exampleuser",
"branches": [
{
"name": "main",
"lastCommitShare": "2023-10-15T09:45:00Z"
}
]
}
]
}
User Not Found Response
json
Copy code
{
"status": 404,
"message": "User not found"
}
Author
Aleksandra Cielecka - aleksandratobuch27.03@gmail.com

