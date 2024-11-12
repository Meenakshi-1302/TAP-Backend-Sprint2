package com.rts.tap.constants;

public class APIConstants {
	public static final String BASE_URL = "/tap";
	public static final String FRONT_END_URL = "http://localhost:3000";

	public static final String ADD_RECRUITMENT_PROCESS = "/addRecruitmentProcess";
	public static final String UPDATE_RECRUITMENT_PROCESS_LEVEL = "/updateRecruitmentProcessLevel";
	public static final String DELETE_RECRUITMENT_PROCESS_LEVEL = "/deleteRecruitmentProcessLevel/{recruitmentProcessId}";
	public static final String GET_RECRUITMENT_PROCESS_LEVELS = "/getRecruitmentProcessLevels/{mrfId}";
	public static final String GET_WORKFLOW_FOR_RECRUITMENT_PROCESS = "/getWorkflowByMrfIdForRecruitmentProcess/{mrfId}";
	public static final String INSERT_RECRUITMENT_PROCESS_LEVEL = "/insertRecruitmentProcessLevel";

	public static final String GET_EMPLOYEES = "/getAllEmployee";
	public static final String GET_EMPLOYEE_BY_ID = "/getEmployeeById/{employeeId}";

	public static final String ADD_APPROVERLEVEL_URL = "/addApproverLevel";
	public static final String UPDATE_APPROVERLEVEL_URL = "/updateApproverLevel";
	public static final String DELETE_APPROVERLEVEL_URL = "/deleteApproverLevel/{approverLevelId}";
	public static final String GET_APPROVERLEVEL_URL = "/getApproverLevel/{mrfId}";
	public static final String GET_WORKFLOW_URL = "/getWorkflow/{mrfId}";
	public static final String GET_EMPLOYEE_URL = "/getEmployee/{employeeId}";

	public static final String BASE_ASSESSMENT_URL = "tap/recruiter/assessment";
	public static final String SAVE_ASSESSMENT_URL = "/save";
	public static final String GET_ALL_ASSESSMENT_URL = "/list";
	public static final String GET_BY_ID_ASSESSMENT_URL = "/get/{id}";
	public static final String UPDATE_ASSESSMENT_URL = "/update/{id}";
	public static final String DELETE_ASSESSMENT_URL = "/delete/{id}";
	public static final String GET_ASSESSMENT_BY_MRF_ID_URL = "/getassessment/{id}";

	public static final String BASE_CANDIDATE_URL = "/candidates";
	public static final String SAVE_CANDIDATE_URL = "/post";
	public static final String GET_ALL_CANDIDATE_URL = "/all";
	public static final String GET_BY_ID_CANDIDATE_URL = "/get/{id}";
	public static final String UPDATE_CANDIDATE_URL = "/update/{id}";
	public static final String DELETE_CANDIDATE_URL = "/delete/{id}";

	public static final String BASE_MRFCANDIDATE_URL = "/mrfCandidates";
	public static final String SAVE_MRFCANDIDATE_URL = "/save";
	public static final String GET_ALL_MRFCANDIDATE_URL = "/list";
	public static final String GET_BY_ID_MRFCANDIDATE_URL = "/get/{id}";
	public static final String UPDATE_MRFCANDIDATE_URL = "/update/{id}";
	public static final String DELETE_MRFCANDIDATE_URL = "/delete/{id}";
	public static final String GET_REMAINING_MRFCANDIDATE_URL = "/remainingcandidate/{id}";

	public static final String BASE_SCORE_URL = "/scores";
	public static final String SAVE_SCORE_URL = "/post";
	public static final String GET_ALL_SCORE_URL = "/all";
	public static final String GET_BY_ID_SCORE_URL = "/get/{id}";
	public static final String UPDATE_SCORE_URL = "/update/{id}";
	public static final String DELETE_SCORE_URL = "/delete/{id}";
	public static final String GET_ASSESSED_CANDIDATE_URL = "/getcandidates/{id}";

	public static final String BASE_SCHEDULE_EMAIL_URL = "/schedule";
	public static final String SAVE_SCHEDULE_EMAIL_URL = "/post";

	public static final String ADD_ADMIN_URL = "/createadmin";

	public static final String ADD_ORGANIZATION_URL = "/createorganization";
	public static final String UPDATE_ORGANIZATION_URL = "/updateorganization/{id}";
	public static final String GETALL_ORGANIZATION_URL = "/getallorganization";

	public static final String ADD_BUSINESSUNIT_URL = "/createbusinessunit";
	public static final String GETALL_BUSINESSUNIT_URL = "/getallbusinessunit";

	public static final String ADD_DEPARTMENT_URL = "/createdepartment";
	public static final String UPDATE_DEPARTMENT_URL = "/updatedepartment/{id}";
	public static final String GETALL_DEPARTMENT_URL = "/getalldepartment";

	public static final String ADD_ROLE_URL = "/createrole";
	public static final String GETALL_ROLE_URL = "/getallrole";
	public static final String UPDATE_ROLE_URL = "/updaterole";

	public static final String CHECK_LOGIN_CREDENTIALS_URL = "/login";
	public static final String CREATE_LOGIN_URL = "/create";
	public static final String VERIFY_OTP_URL = "/verify-otp";
	public static final String RESEND_OTP_URL = "/resend-otp";

	public static final String ADD_EMPLOYEE_URL = "/createemployee";
	public static final String GETALL_EMPLOYEE_URL = "/getallemployee";
	public static final String UPDATE_EMPLOYEE_URL = "/updateemployee";

	public static final String CROSS_ORIGIN_URL = "http://localhost:3000";
	public static final String GET_ADMIN_URL = "/admin";

	public static final String ADD_MRF = "mrf/addMrf";
	public static final String UPDATE_MRF = "mrf/updateMrf/{mrfId}";
	public static final String DELETE_MRF = "mrf/deleteMrf/{mrfId}";
	public static final String GET_MRF = "mrf/getMrf/{mrfId}";
	public static final String GET_ALL_MRF = "mrf/getAllMrf";

	public static final String CLIENTS_PATH = BASE_URL + "/clients";

	public static final String GET_CLIENTBYID = "/{id}";

	public static final String UPDATE_CLIENT_APPROVED = "/UpdateCLientApprove/{id}";
	public static final String FRONTEND_URL = "http://localhost:3000";
	
	public static final String RECRUITER_DASHBOARD= BASE_URL + "/api";
	public static final String GET_TOTAL_MRF_ASSIGNED_TO_RECRUITER="/totalmrfassigned/{mrfRecruitersId}";

	// client
	public static final String GET_CLIENT_URL = "/client";

	public static final String GET_CLIENT_BY_ID = "/client-profile-by-id/{clientId}";
	public static final String GET_CLIENT_BY_EMAIL = "/client-profile-by-email/{clientEmail}";
	public static final String UPDATE_CLIENT_PROFILE_BY_ID = "/client-profile-update-by-id/{clientId}";
	public static final String UPDATE_CLIENT_PROFILE_BY_EMAIL = "/client-profile-update-by-email/{clientEmail}";
	public static final String UPDATE_CLIENT_ORGANIZATION_LOGO_BY_ID = "/client-logo-update-by-id/{clientId}/logo";

	public static final String CLIENT_RESETPASSWORD_URL = "/client-reset-password";
	public static final String CLIENT_EMAIL_CHECK_URL = "/email-exists/{clientEmail}";

	public static final String CLIENT_FORGOT_PASSWORD_SEND_OTP = "/forgot-password-send-otp/{clientEmail}";
	public static final String CLIENT_FORGOT_PASSWORD_VERIFY_OTP = "/verify-otp";
	public static final String CLIENT_FORGOT_PASSWORD_UPDATE = "/update-forgot-password";

	public static final String CLIENT_ADD = "/addclient";
	public static final String CLIENT_RESET_PASSWORD_UPDATE = "/resetpwd";

	public static final String CLIENT_SEND_OTP = "/forgot-password-send-otp/{clientEmail}";

	public static final String REQUIREMENT_REQUESTMAPPING_API = BASE_URL + "/api";
	public static final String REQUIREMENT_ADD_API = "/requirement";
	public static final String REQUIREMENT_DELETE_API = "/delete/{requirementId}";
	public static final String REQUIREMENT_UPDATE_API = "/update";
	public static final String REQUIREMENT_GETALL_REQUIREMENT_API = "/allRequirements";
	public static final String REQUIREMENT_REQUIREMENTBY_CLIENT_API = "/requirementByClientId/{clientId}";
	public static final String REQUIREMENT_COUNT_CLIENT_API = "/requirementCount/{clientId}";
	public static final String REQUIREMENT_LIST_BY_CLIENT_API = "/requirement-by-client/{clientId}";

	public static final String CLIENT_REQUESTMAPPING_API = BASE_URL + "/api";
	public static final String GET_CLIENT_CANDIDATE_HIRED = "/hiredCount/{clientId}";
	public static final String GET_CLIENT_CANDIDATE_SHORTLISTED = "/shortListedCount/{clientId}";
	public static final String GET_CLIENT_HIRED = "/hired/{requirementId}";
	public static final String GET_CLIENT_SHORTLISTED = "/shortlisted/{requirementId}";
	public static final String GET_CANDIDATE_HIRED = "/hiredCandidate/{clientId}";

	public static final String DUMMY = "/dummy/{clientId}";
	public static final String REQUESTMAPPING_SUB_REQUIREMENTS = BASE_URL + "/api";
	public static final String ADD_SUB_REQUIREMENTS = "/add-sub-requirements";
	public static final String VIEW_SUB_REQUIREMENTS = "/list-sub-requirements";

	// Recruiting Manager
	public static final String RECRUITING_MANAGER_URL = "/api/recruitingManager";
	public static final String RECRUITING_MANAGER_ASSIGN_MRF_RECRUITER = "/assignMrfs/recruiter";
	public static final String RECRUITING_MANAGER_GET_ALL_MRF = "/allMrfs/{id}";
	public static final String RECRUITING_MANAGER_GET_ALL_MRFVENDOR = "/fetch/allMrfVendors";
	public static final String RECRUITING_MANAGER_ASSIGN_MRF_VENDOR = "/assignMrfs/vendor";

	// vendor
	public static final String VENDOR_URL = "/api/vendors";
	public static final String VENDOR_UPDATE = "/{id}";
	public static final String VENDOR_DELETE = "/{id}";
	public static final String VENDOR_GET_BY_ID = "/{id}";
	public static final String VENDOR_GET_ALL = "/allVendor";
	public static final String UPDATE_VENDOR_ORGANIZATION_LOGO_BY_ID = "/vendor-logo-update-by-id/{vendorId}/logo";
	//team D vendor
	public static final String VENDOR_ADD_CANDIDATE = "/add-candidate-by-vendor";
	

	public static final String GET_CANDIDATE_SHORTLISTED = "/short-listed/{clientId}";
	public static final String GET_CANDIDATE_SHORTLISTED_REQUIREMENT = "/short-listed-requirement/{requirementId}";
	public static final String GET_CANDIDATE_HIRED_REQUIREMENT = "/hired-requirement/{requirementId}";
}
