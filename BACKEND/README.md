# ENDPOINTS:
`prefix: http://localhost:8080`
## GET

* /courses				✓
* /courses/questions			✓
* /courses/questions/`<id>`		✓
* /courses/answers			✓
* /courses/answers/`<id>`		✓
* /courses/reviews			✓
* /courses/reviews/`<id>`		✓
* /courses/topics/			✓
* /courses/topics/`<id>`		✓
* /courses/videos			✓
* /courses/videos/`<id>`		✓
* /schedules				✓
* /schedules/`<id>`			✓
* /advertisements			✓
* /advertisements/`<id>`		✓


## POST

* /login				✓
   * `{"email" : "admin", "password" : "admin"}`
* /company-login			✓
   * `{"email" : "admin", "password" : "admin"}`
* /register				✓
   * `{"userName" : "test", "email" : "test@tesst.tdset", "password" : "test", "confpassword" : "test", "firstName" : "test", "lastName" : "test", "birthDate" : "2018-01-01", "enabled" : "true"}`
* /company-register			✓
   * `{"name" : "companyNamme", "userName" : "compUser", "email" : "company@company", "password" : "psw", "subscription" : "1month"}`
* /courses/`<teacher_id>`		✓
   * `{"name" : "Course #3", "isValidated" : "false"}`
* /courses/`<course_id>`/questions	✓
   * `{"question" : "What is polymorphism?"}`
* /courses/`<course_id>`/reviews	✓
   * `{"rating" : "4", "description" : "Great course!"}`
* /courses/`<course_id>`/topics		✓
   * `{"name" : "Sport"}`
* /courses/`<course_id>`/videos		✓
   * `{"name" : "Python basics", "video" : "[URL]", "description" : "A video tutorial of python"}`
* /courses/`<course_id>`/questions/`<question_id>`/answers
   * `{"answer" : "Definitely", "isRight" : "false"}`
* /advertisements/`<company_id>`
   * `{"name" : "Check it out", "description" : "The best software developer job"}`
* /schedules/`<teacher_id>`
   * `{"studentId" : "1", "startTime" : "14", "date" : "2018-07-12"}`

## PUT

## DELETE
