package com.cg.uas.utility;

public interface QueryMapper {

	public static final String query1 = "Select user from Users user where user.loginId=:ploginid and"
			+ " user.password=:ppwd and role=:prole";
	public static final String query2 = "Select programs from ProgramsScheduled programs";

	public static final String query3 = "SELECT e FROM ProgramsOffered e WHERE e.programName=:pname";

	public static final String query4 = "SELECT e FROM Application e WHERE e.applicationId=:pappid";

	public static final String query5 = "SELECT e FROM Application e WHERE e.scheduledProgramId=:pappid";

	public static final String query6 = "SELECT e FROM ProgramsScheduled e WHERE e.scheduledProgrammeId=:ppid";

	public static final String query7 = "DELETE FROM ProgramsScheduled c WHERE c.scheduledProgrammeId=:p";

}
