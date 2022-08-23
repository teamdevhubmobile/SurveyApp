package com.example.surveyapp.response

import com.google.gson.annotations.SerializedName

data class UpdateQuestionAnswerResponse(

	@field:SerializedName("onlineExam")
	val onlineExam: OnlineExam? = null,

	@field:SerializedName("totalCorrectMark")
	val totalCorrectMark: Int? = null,

	@field:SerializedName("userExamCheck")
	val userExamCheck: UserExamCheck? = null,

	@field:SerializedName("student")
	val student: Student? = null,

	@field:SerializedName("questions")
	val questions: Questions? = null,

	@field:SerializedName("answers")
	val answers: Answers? = null,

	@field:SerializedName("section")
	val section: Section? = null,

	@field:SerializedName("questionStatus")
	val questionStatus: List<Any?>? = null,

	@field:SerializedName("totalQuestionMark")
	val totalQuestionMark: Int? = null,

	@field:SerializedName("fail")
	val fail: Int? = null,

	@field:SerializedName("onlineExamQuestions")
	val onlineExamQuestions: List<OnlineExamQuestionsItem?>? = null,

	@field:SerializedName("options")
	val options: Options? = null,

	@field:SerializedName("passingMarksinExam")
	val passingMarksinExam: List<PassingMarksinExamItem?>? = null,

	@field:SerializedName("correctAnswer")
	val correctAnswer: Int? = null,

	@field:SerializedName("class")
	val jsonMemberClass: JsonMemberClass? = null,

	@field:SerializedName("totalAnswer")
	val totalAnswer: Int? = null
)

data class Answers(

	@field:SerializedName("6")
	val jsonMember6: List<JsonMember6Item?>? = null
)

data class Options(

	@field:SerializedName("6")
	val jsonMember6: List<JsonMember6Item?>? = null
)

data class Student(

	@field:SerializedName("srstudentID")
	val srstudentID: String? = null,

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("srclasses")
	val srclasses: String? = null,

	@field:SerializedName("studentrelationID")
	val studentrelationID: String? = null,

	@field:SerializedName("hostel")
	val hostel: String? = null,

	@field:SerializedName("roll")
	val roll: String? = null,

	@field:SerializedName("sectionID")
	val sectionID: String? = null,

	@field:SerializedName("parentID")
	val parentID: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("library")
	val library: String? = null,

	@field:SerializedName("srsection")
	val srsection: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("usertypeID")
	val usertypeID: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("srname")
	val srname: String? = null,

	@field:SerializedName("srschoolyearID")
	val srschoolyearID: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("rte")
	val rte: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("createschoolyearID")
	val createschoolyearID: String? = null,

	@field:SerializedName("create_usertype")
	val createUsertype: String? = null,

	@field:SerializedName("srroll")
	val srroll: String? = null,

	@field:SerializedName("srclassesID")
	val srclassesID: String? = null,

	@field:SerializedName("sroptionalsubjectID")
	val sroptionalsubjectID: String? = null,

	@field:SerializedName("sex")
	val sex: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("active")
	val active: String? = null,

	@field:SerializedName("transport")
	val transport: String? = null,

	@field:SerializedName("srregisterNO")
	val srregisterNO: String? = null,

	@field:SerializedName("religion")
	val religion: String? = null,

	@field:SerializedName("registerNO")
	val registerNO: String? = null,

	@field:SerializedName("studentID")
	val studentID: String? = null,

	@field:SerializedName("create_username")
	val createUsername: String? = null,

	@field:SerializedName("classesID")
	val classesID: String? = null,

	@field:SerializedName("bloodgroup")
	val bloodgroup: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("dob")
	val dob: Any? = null,

	@field:SerializedName("srstudentgroupID")
	val srstudentgroupID: String? = null,

	@field:SerializedName("schoolyearID")
	val schoolyearID: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("srsectionID")
	val srsectionID: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class JsonMember7(

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("totalOption")
	val totalOption: String? = null,

	@field:SerializedName("upload")
	val upload: String? = null,

	@field:SerializedName("hints")
	val hints: String? = null,

	@field:SerializedName("groupID")
	val groupID: String? = null,

	@field:SerializedName("explanation")
	val explanation: String? = null,

	@field:SerializedName("create_usertypeID")
	val createUsertypeID: String? = null,

	@field:SerializedName("parentID")
	val parentID: String? = null,

	@field:SerializedName("subjectID")
	val subjectID: Any? = null,

	@field:SerializedName("questionBankID")
	val questionBankID: String? = null,

	@field:SerializedName("typeNumber")
	val typeNumber: String? = null,

	@field:SerializedName("levelID")
	val levelID: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null,

	@field:SerializedName("mark")
	val mark: String? = null,

	@field:SerializedName("totalQuestion")
	val totalQuestion: String? = null
)

data class JsonMember6Item(

	@field:SerializedName("answerID")
	val answerID: String? = null,

	@field:SerializedName("questionID")
	val questionID: String? = null,

	@field:SerializedName("typeNumber")
	val typeNumber: String? = null,

	@field:SerializedName("optionID")
	val optionID: String? = null,

	@field:SerializedName("text")
	val text: Any? = null,

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class Questions(

	@field:SerializedName("6")
	val jsonMember6: JsonMember6? = null,

	@field:SerializedName("7")
	val jsonMember7: JsonMember7? = null,

	@field:SerializedName("8")
	val jsonMember8: JsonMember8? = null
)

data class PassingMarksinExamItem(

	@field:SerializedName("percentage")
	val percentage: String? = null
)

data class UserExamCheck(

	@field:SerializedName("1")
	val jsonMember1: JsonMember1? = null
)

data class JsonMember6(

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("totalOption")
	val totalOption: String? = null,

	@field:SerializedName("upload")
	val upload: String? = null,

	@field:SerializedName("hints")
	val hints: String? = null,

	@field:SerializedName("groupID")
	val groupID: String? = null,

	@field:SerializedName("explanation")
	val explanation: String? = null,

	@field:SerializedName("create_usertypeID")
	val createUsertypeID: String? = null,

	@field:SerializedName("parentID")
	val parentID: String? = null,

	@field:SerializedName("subjectID")
	val subjectID: Any? = null,

	@field:SerializedName("questionBankID")
	val questionBankID: String? = null,

	@field:SerializedName("typeNumber")
	val typeNumber: String? = null,

	@field:SerializedName("levelID")
	val levelID: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null,

	@field:SerializedName("mark")
	val mark: Any? = null,

	@field:SerializedName("totalQuestion")
	val totalQuestion: String? = null
)

data class OnlineExamQuestionsItem(

	@field:SerializedName("questionID")
	val questionID: String? = null,

	@field:SerializedName("onlineExamID")
	val onlineExamID: String? = null,

	@field:SerializedName("onlineExamQuestionID")
	val onlineExamQuestionID: String? = null
)

data class Section(

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("note")
	val note: String? = null,

	@field:SerializedName("create_username")
	val createUsername: String? = null,

	@field:SerializedName("classesID")
	val classesID: String? = null,

	@field:SerializedName("teacherID")
	val teacherID: String? = null,

	@field:SerializedName("create_usertype")
	val createUsertype: String? = null,

	@field:SerializedName("section")
	val section: String? = null,

	@field:SerializedName("sectionID")
	val sectionID: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null,

	@field:SerializedName("capacity")
	val capacity: String? = null
)

data class JsonMember1(

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("sectionID")
	val sectionID: String? = null,

	@field:SerializedName("answer_option")
	val answerOption: String? = null,

	@field:SerializedName("userID")
	val userID: String? = null,

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("score")
	val score: String? = null,

	@field:SerializedName("approved")
	val approved: String? = null,

	@field:SerializedName("examtimeID")
	val examtimeID: String? = null,

	@field:SerializedName("statusID")
	val statusID: String? = null,

	@field:SerializedName("percentage")
	val percentage: String? = null,

	@field:SerializedName("totalObtainedMark")
	val totalObtainedMark: String? = null,

	@field:SerializedName("onlineExamID")
	val onlineExamID: String? = null,

	@field:SerializedName("audio")
	val audio: String? = null,

	@field:SerializedName("totalPercentage")
	val totalPercentage: String? = null,

	@field:SerializedName("totalQuestion")
	val totalQuestion: String? = null,

	@field:SerializedName("arr")
	val arr: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("onlineExamUserStatus")
	val onlineExamUserStatus: String? = null,

	@field:SerializedName("totalMark")
	val totalMark: String? = null,

	@field:SerializedName("nagetiveMark")
	val nagetiveMark: String? = null,

	@field:SerializedName("classesID")
	val classesID: String? = null,

	@field:SerializedName("totalCurrectAnswer")
	val totalCurrectAnswer: String? = null,

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("answer")
	val answer: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("age")
	val age: String? = null,

	@field:SerializedName("totalAnswer")
	val totalAnswer: String? = null
)

data class JsonMemberClass(

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("classes_numeric")
	val classesNumeric: String? = null,

	@field:SerializedName("note")
	val note: String? = null,

	@field:SerializedName("create_username")
	val createUsername: String? = null,

	@field:SerializedName("classesID")
	val classesID: String? = null,

	@field:SerializedName("teacherID")
	val teacherID: String? = null,

	@field:SerializedName("create_usertype")
	val createUsertype: String? = null,

	@field:SerializedName("classes")
	val classes: String? = null,

	@field:SerializedName("studentmaxID")
	val studentmaxID: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null
)

data class JsonMember8(

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("totalOption")
	val totalOption: String? = null,

	@field:SerializedName("upload")
	val upload: String? = null,

	@field:SerializedName("hints")
	val hints: String? = null,

	@field:SerializedName("groupID")
	val groupID: String? = null,

	@field:SerializedName("explanation")
	val explanation: String? = null,

	@field:SerializedName("create_usertypeID")
	val createUsertypeID: String? = null,

	@field:SerializedName("parentID")
	val parentID: String? = null,

	@field:SerializedName("subjectID")
	val subjectID: Any? = null,

	@field:SerializedName("questionBankID")
	val questionBankID: String? = null,

	@field:SerializedName("typeNumber")
	val typeNumber: String? = null,

	@field:SerializedName("levelID")
	val levelID: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null,

	@field:SerializedName("mark")
	val mark: String? = null,

	@field:SerializedName("totalQuestion")
	val totalQuestion: String? = null
)

data class OnlineExam(

	@field:SerializedName("create_userID")
	val createUserID: String? = null,

	@field:SerializedName("examTypeNumber")
	val examTypeNumber: String? = null,

	@field:SerializedName("img")
	val img: Any? = null,

	@field:SerializedName("description")
	val description: Any? = null,

	@field:SerializedName("sectionID")
	val sectionID: String? = null,

	@field:SerializedName("schoolYearID")
	val schoolYearID: String? = null,

	@field:SerializedName("create_usertypeID")
	val createUsertypeID: String? = null,

	@field:SerializedName("point")
	val point: String? = null,

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("random")
	val random: String? = null,

	@field:SerializedName("public")
	val jsonMemberPublic: String? = null,

	@field:SerializedName("markType")
	val markType: String? = null,

	@field:SerializedName("percentage")
	val percentage: String? = null,

	@field:SerializedName("instructionID")
	val instructionID: String? = null,

	@field:SerializedName("onlineExamID")
	val onlineExamID: String? = null,

	@field:SerializedName("judge")
	val judge: String? = null,

	@field:SerializedName("create_date")
	val createDate: String? = null,

	@field:SerializedName("userTypeID")
	val userTypeID: String? = null,

	@field:SerializedName("cost")
	val cost: String? = null,

	@field:SerializedName("examStatus")
	val examStatus: String? = null,

	@field:SerializedName("showMarkAfterExam")
	val showMarkAfterExam: String? = null,

	@field:SerializedName("published")
	val published: String? = null,

	@field:SerializedName("endDateTime")
	val endDateTime: Any? = null,

	@field:SerializedName("subjectID")
	val subjectID: String? = null,

	@field:SerializedName("bonusMark")
	val bonusMark: String? = null,

	@field:SerializedName("examfor")
	val examfor: String? = null,

	@field:SerializedName("classID")
	val classID: String? = null,

	@field:SerializedName("negativeMark")
	val negativeMark: String? = null,

	@field:SerializedName("startDateTime")
	val startDateTime: Any? = null,

	@field:SerializedName("levelID")
	val levelID: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("paid")
	val paid: String? = null,

	@field:SerializedName("studentGroupID")
	val studentGroupID: String? = null,

	@field:SerializedName("modify_date")
	val modifyDate: String? = null,

	@field:SerializedName("courseid")
	val courseid: Any? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("validDays")
	val validDays: String? = null
)
