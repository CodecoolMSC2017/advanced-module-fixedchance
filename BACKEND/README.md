# ENDPOINTS:
`prefix: http://localhost:8080`


## GET

#### GET all courses
* /courses

#### GET course by ID
* /courses/`<course_id>`

#### GET all courses of teacher by teacher ID
* /courses/`<teacher_id>`/courses

#### GET all questions
* /courses/questions

#### GET question by ID
* /courses/questions/`<question_id>`

#### GET questions of course by course ID
* /courses/`<course_id>`/questions

#### GET all answers
* /courses/answers

#### GET answer by ID	
* /courses/answers/`<answer_id>`

#### GET answers of question by question ID
* /courses/questions/`<question_id>`

#### GET all reviews
* /courses/reviews

#### GET review by ID
* /courses/reviews/`<review_id>`

#### GET reviews of course by course ID
* /courses/`<course_id>`/reviews

#### GET all topics
* /courses/topics/

#### GET topic by ID	
* /courses/topics/`<topic_id>`

#### GET topics of course by course ID
* /courses/`<course_id>`/topics

#### GET all videos	
* /courses/videos

#### GET video by ID	
* /courses/videos/`<video_id>`

#### GET videos of course by course ID
* /courses/`<course_id>`/videos

#### GET all schedules	
* /schedules	

#### GET schedule by ID
* /schedules/`<schedule_id>`	

#### GET schedules of teachers by teacher ID
* /schedules/`<teacher_id>`/schedules

#### GET all advertisements
* /advertisements	

#### GET advertisement by ID
* /advertisements/`<advertisement_id>`

#### GET advertisements of company by company ID
* /companies/`<company_id>`/advertisements


## POST

* /login	
   * `{"email" : "admin", "password" : "admin"}`
* /company-login	
   * `{"email" : "admin", "password" : "admin"}`
* /register	
   * `{"userName" : "test", "email" : "test@tesst.tdset", "password" : "test", "confpassword" : "test", "firstName" : "test", "lastName" : "test", "birthDate" : "2018-01-01", "enabled" : "true"}`
* /company-register	
   * `{"name" : "companyNamme", "userName" : "compUser", "email" : "company@company", "password" : "psw", "subscription" : "1month"}`
* /courses/`<teacher_id>`	
   * `{"name" : "Course #3", "isValidated" : "false"}`
* /courses/`<course_id>`/questions	
   * `{"question" : "What is polymorphism?"}`
* /courses/`<course_id>`/reviews	
   * `{"rating" : "4", "description" : "Great course!"}`
* /courses/`<course_id>`/topics	
   * `{"name" : "Sport"}`
* /courses/`<course_id>`/videos	
   * `{"name" : "Python basics", "video" : "[URL]", "description" : "A video tutorial of python"}`
* /courses/`<course_id>`/questions/`<question_id>`/answers
   * `{"answer" : "Definitely", "isRight" : "false"}`
* /advertisements/`<company_id>`
   * `{"name" : "Check it out", "description" : "The best software developer job"}`
* /schedules/`<teacher_id>`
   * `{"studentId" : "1", "startTime" : "14", "date" : "2018-07-12"}`


## PUT

* /user/`<id>`
   * `{"firstName" : "modifiedFirstName", "lastName" : "modifiedLastName", "password" : "modifiedPassword"}`
* /company/`<id>`
   * `{"name" : "modifiedName", "description" : "modifiedDescription", "password" : "modifiedPassword"}`
* /courses/`<id>`
   * `{"name" : "modifiedName"}`
* /courses/questions/`<id>`
   * `{"question" : "modifiedQuestion"}`
* /courses/reviews/`<id>`
   * `{"rating" : "1", "description" : "modifiedDescription"}`
* /courses/topics/`<id>`
   * `{"name" : "modifiedName"}`
* /courses/videos/`<id>`
   * `{"name" : "modifiedName", "video" : "modified[URL]", "description" : "modifiedDescription"}`
* /courses/answers/`<id>`
   * `{"answer" : "modifiedAnswer", "isRight" : "false"}`
* /advertisements/`<id>`
   * `{"name" : "test_company_ad", "description" : "test_company_desc"}`
* /schedules/`<id>`
   * `{"starTime" : "18"}`


## DELETE

* /user/`<id>`
* /company/`<id>`
* /courses/`<id>` !!! NOT WORKING !!!
* /courses/questions/`<id>`
* /courses/reviews/`<id>`
* /courses/topics/`<id>`
* /courses/videos/`<id>`
* /courses/answers/`<id>`
* /advertisements/`<id>` !!! NOT WORKING !!!
* /schedules/`<id>` !!! NOT WORKING !!!
