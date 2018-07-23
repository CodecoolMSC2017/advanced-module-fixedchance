# ENDPOINTS:
`prefix: http://localhost:8080`
## GET

* /courses			✓
* /courses/questions		✓
* /courses/questions/`<?>`	✓
* /courses/answers		✓
* /courses/answers/`<?>`	✓
* /courses/reviews		✓
* /courses/reviews/`<?>`	✓
* /courses/topics/		✓
* /courses/topics/`<?>`		✓
* /courses/videos		✓
* /courses/videos/`<?>`		✓
* /schedules			✓
* /schedules/`<?>`		✓
* /advertisements		✓
* /advertisements/`<?>`		✓


## POST

* /login			✓
   * `{"email" : "admin", "password" : "admin"}`
* /company-login		✓
   * `{"email" : "admin", "password" : "admin"}`
* /register			✓
   * `{"userName" : "test", "email" : "test@tesst.tdset", "password" : "test", "confpassword" : "test", "firstName" : "test", "lastName" : "test", "birthDate" : "2018-01-01", "enabled" : "true"}`
* /company-register		✓
   * `{"name" : "companyNamme", "userName" : "compUser", "email" : "company@company", "password" : "psw", "subscription" : "1month"}`
* /courses
   * `?`
* /courses/questions/add
   * `?`
* /courses/reviews/add
   * `?`
* /courses/topics/add
   * `?`
* /courses/videos/add
   * `?`
* /advertisements/add
   * `?`

## PUT

## DELETE
