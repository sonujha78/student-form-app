# Student Form Application

A simple web-based Student Form Application demonstrating application development,
database management, GitLab, Jenkins CI/CD, and Tomcat deployment.

## Tech Stack
- Java (Jakarta Servlet + JSP)
- Maven (build tool)
- PostgreSQL (database)
- Apache Tomcat 10.1 (deployment, Docker)
- GitLab CE (version control, Docker)
- Jenkins (CI/CD pipeline)

## Project Structure
student-form-app/
  src/main/java/com/studentform/   - Servlet + DB connection code
  src/main/webapp/                 - JSP form + web.xml
  pom.xml                          - Maven build config
  docker-compose.yml               - Tomcat + GitLab containers
  docs/                            - Setup documentation & screenshots

## Database
Database: projectForm
Table: formDetails (firstName, lastName, dob, gender, highestqualification, year_of_passing, mobilenumber)

## Build
Run: mvn clean package
Produces target/form.war

## Deployment
WAR is deployed to Tomcat (Docker container) via volume-mounted webapps folder.
Application accessible at: http://<host>:8080/form/

## CI/CD
Jenkins pipeline fetches source from GitLab, builds the WAR via Maven, and auto-deploys it to Tomcat on every commit.
