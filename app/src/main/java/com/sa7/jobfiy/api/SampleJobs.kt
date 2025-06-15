package com.sa7.jobfiy.api

data class SampleJobs(
    val jobs: List<JobSearch>
) {
    companion object {
        fun getSampleJobs(): List<JobSearch> {
            return listOf(
                JobSearch(
                    company_name = "Ethio Telecom",
                    formatted_relative_time = "2 days ago",
                    id = "1",
                    link = "https://ethiotelecom.et",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 172800000,
                    salary = Salary(25000.0, 35000.0, "ETB"),
                    title = "Senior Software Engineer"
                ),
                JobSearch(
                    company_name = "Dashen Bank",
                    formatted_relative_time = "1 day ago",
                    id = "2",
                    link = "https://dashenbanksc.com",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 86400000,
                    salary = Salary(20000.0, 30000.0, "ETB"),
                    title = "Mobile App Developer"
                ),
                JobSearch(
                    company_name = "Commercial Bank of Ethiopia",
                    formatted_relative_time = "3 days ago",
                    id = "3",
                    link = "https://cbe.com.et",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 259200000,
                    salary = Salary(22000.0, 32000.0, "ETB"),
                    title = "Full Stack Developer"
                ),
                JobSearch(
                    company_name = "Ethiopian Airlines",
                    formatted_relative_time = "4 days ago",
                    id = "4",
                    link = "https://ethiopianairlines.com",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 345600000,
                    salary = Salary(28000.0, 40000.0, "ETB"),
                    title = "IT Project Manager"
                ),
                JobSearch(
                    company_name = "Awash Bank",
                    formatted_relative_time = "5 days ago",
                    id = "5",
                    link = "https://awashbank.com",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 432000000,
                    salary = Salary(18000.0, 28000.0, "ETB"),
                    title = "Junior Software Developer"
                ),
                JobSearch(
                    company_name = "Safaricom Ethiopia",
                    formatted_relative_time = "1 day ago",
                    id = "6",
                    link = "https://safaricom.et",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 86400000,
                    salary = Salary(30000.0, 45000.0, "ETB"),
                    title = "DevOps Engineer"
                ),
                JobSearch(
                    company_name = "EthioSwitch",
                    formatted_relative_time = "2 days ago",
                    id = "7",
                    link = "https://ethioswitch.com",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 172800000,
                    salary = Salary(25000.0, 35000.0, "ETB"),
                    title = "Backend Developer"
                ),
                JobSearch(
                    company_name = "Ethio Digital Solutions",
                    formatted_relative_time = "3 days ago",
                    id = "8",
                    link = "https://ethiodigital.et",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 259200000,
                    salary = Salary(20000.0, 30000.0, "ETB"),
                    title = "UI/UX Designer"
                ),
                JobSearch(
                    company_name = "Abyssinia Bank",
                    formatted_relative_time = "4 days ago",
                    id = "9",
                    link = "https://abyssinia.com.et",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 345600000,
                    salary = Salary(22000.0, 32000.0, "ETB"),
                    title = "System Administrator"
                ),
                JobSearch(
                    company_name = "Ethio ICT Solutions",
                    formatted_relative_time = "5 days ago",
                    id = "10",
                    link = "https://ethioict.com",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 432000000,
                    salary = Salary(18000.0, 25000.0, "ETB"),
                    title = "IT Support Specialist"
                ),
                JobSearch(
                    company_name = "Ethio Telecom",
                    formatted_relative_time = "6 days ago",
                    id = "11",
                    link = "https://ethiotelecom.et",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 518400000,
                    salary = Salary(35000.0, 50000.0, "ETB"),
                    title = "Technical Lead"
                ),
                JobSearch(
                    company_name = "Dashen Bank",
                    formatted_relative_time = "7 days ago",
                    id = "12",
                    link = "https://dashenbanksc.com",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 604800000,
                    salary = Salary(28000.0, 40000.0, "ETB"),
                    title = "Database Administrator"
                ),
                JobSearch(
                    company_name = "Ethiopian Airlines",
                    formatted_relative_time = "8 days ago",
                    id = "13",
                    link = "https://ethiopianairlines.com",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 691200000,
                    salary = Salary(25000.0, 35000.0, "ETB"),
                    title = "QA Engineer"
                ),
                JobSearch(
                    company_name = "Safaricom Ethiopia",
                    formatted_relative_time = "9 days ago",
                    id = "14",
                    link = "https://safaricom.et",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 777600000,
                    salary = Salary(22000.0, 32000.0, "ETB"),
                    title = "Frontend Developer"
                ),
                JobSearch(
                    company_name = "Commercial Bank of Ethiopia",
                    formatted_relative_time = "10 days ago",
                    id = "15",
                    link = "https://cbe.com.et",
                    locality = "Addis Ababa",
                    location = "Addis Ababa, Ethiopia",
                    pub_date_ts_milli = System.currentTimeMillis() - 864000000,
                    salary = Salary(30000.0, 45000.0, "ETB"),
                    title = "Security Engineer"
                )
            )
        }
    }
}

class AuthRepository {
    fun login(email: String, password: String): Result<Unit> {
        // Implement your login logic here
        return Result.success(Unit)
    }
} 