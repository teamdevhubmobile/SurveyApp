package com.example.data.response

import com.google.gson.annotations.SerializedName

data class ShowResponse(

	@field:SerializedName("onlineExam")
	val onlineExam1: ShowResponseOnlineExam? = null,

	@field:SerializedName("onlineExamQuestions")
	val onlineExamQuestions: List<OnlineExamQuestionsItem?>? = null,

	@field:SerializedName("userExamCheck")
	val userExamCheck: ShowResponseUserExamCheck? = null,

	@field:SerializedName("questions")
	val questions: ShowResponseQuestions? = null,

	@field:SerializedName("options")
	val options: ShowResponseOptions? = null,

	@field:SerializedName("answers")
	val answers: ShowResponseAnswers? = null,

	@field:SerializedName("online_exam")
	val onlineExam: ShowResponseOnlineExam? = null
)

data class ShowResponseQuestions(

	@field:SerializedName("6")
	val jsonMember6: ShowResponseJsonMember6? = null,

	@field:SerializedName("7")
	val jsonMember7: ShowResponseJsonMember7? = null,

	@field:SerializedName("8")
	val jsonMember8: ShowResponseJsonMember8? = null
)

data class ShowResponseUserExamCheck(

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

data class ShowResponseJsonMember8(

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

data class ShowResponseJsonMember7(

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

data class ShowResponseOptions(

	@field:SerializedName("6")
	val jsonMember6: List<ShowResponseJsonMember6Item?>? = null
)

data class ShowResponseJsonMember6Item(

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("questionID")
	val questionID: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("optionID")
	val optionID: String? = null,

	@field:SerializedName("answerID")
	val answerID: String? = null,

	@field:SerializedName("typeNumber")
	val typeNumber: String? = null,

	@field:SerializedName("text")
	val text: Any? = null
)

data class ShowResponseJsonMember6(

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

data class ShowResponseOnlineExamQuestionsItem(

	@field:SerializedName("questionID")
	val questionID: String? = null,

	@field:SerializedName("onlineExamID")
	val onlineExamID: String? = null,

	@field:SerializedName("onlineExamQuestionID")
	val onlineExamQuestionID: String? = null
)

data class ShowResponseOnlineExam(

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

data class ShowResponseAnswers(

	@field:SerializedName("6")
	val jsonMember6: List<ShowResponseJsonMember6Item?>? = null
)
