/**
 * Copyright 2005-2019 Talend
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: Apache 2.0 or or EPL 1.0 (the "Licenses"). You can
 * select the license that you prefer but you may not use this file except in
 * compliance with one of these Licenses.
 * 
 * You can obtain a copy of the Apache 2.0 license at
 * http://www.opensource.org/licenses/apache-2.0
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://restlet.com/products/restlet-framework
 * 
 * Restlet is a registered trademark of Restlet S.A.S.
 */

package org.restlet.test.ext.odata.deepexpand.model;

import java.util.Date;
import java.util.List;

import org.restlet.test.ext.odata.deepexpand.model.AuthenticatedUser;
import org.restlet.test.ext.odata.deepexpand.model.Company;
import org.restlet.test.ext.odata.deepexpand.model.FinancialSource;
import org.restlet.test.ext.odata.deepexpand.model.Group;
import org.restlet.test.ext.odata.deepexpand.model.InsuranceContract;
import org.restlet.test.ext.odata.deepexpand.model.JobPosting;
import org.restlet.test.ext.odata.deepexpand.model.Lesson;
import org.restlet.test.ext.odata.deepexpand.model.Multilingual;
import org.restlet.test.ext.odata.deepexpand.model.Professor;
import org.restlet.test.ext.odata.deepexpand.model.Registration;
import org.restlet.test.ext.odata.deepexpand.model.Report;
import org.restlet.test.ext.odata.deepexpand.model.Requirement;

/**
 * Generated by the generator tool for the OData extension for the Restlet
 * framework.<br>
 * 
 * @see <a
 *      href="http://praktiki.metal.ntua.gr/CoopOData/CoopOData.svc/$metadata">Metadata
 *      of the target OData service</a>
 * 
 */
public class CoOp {

    private int academicYear;

    private boolean active;

    private boolean allowCategoryPreferences;

    private boolean allowJobPostingsPreferences;

    private boolean allowLocationPreferences;

    private Date endDate;

    private String gradePolicy;

    private boolean hasGroupGrade;

    private int id;

    private boolean inRegistration;

    private boolean isGroupCoOp;

    private boolean isInsideUniversity;

    private int jobDurationDays;

    private int maxGroupSize;

    private double paymentOrderAmount;

    private Date paymentOrderDate;

    private int semester;

    private boolean setup;

    private Date startDate;

    private boolean supportingInvitations;

    private Tracking tracking;

    private Professor academicDirector;

    private List<AuthenticatedUser> authenticatedUsers;

    private List<Company> companies;

    private List<FinancialSource> financialSources;

    private List<Group> groups;

    private Professor institutionalDirector;

    private List<InsuranceContract> insuranceContracts;

    private List<JobPosting> jobPostings;

    private Lesson lesson;

    private Multilingual name;

    private List<Registration> registrations;

    private List<Report> reports;

    private List<Requirement> requirements;

    private Professor scientificDirector;

    private List<Professor> supervisingProfessors;

    /**
     * Constructor without parameter.
     * 
     */
    public CoOp() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param id
     *            The identifiant value of the entity.
     */
    public CoOp(int id) {
        this();
        this.id = id;
    }

    /**
     * Returns the value of the "academicYear" attribute.
     * 
     * @return The value of the "academicYear" attribute.
     */
    public int getAcademicYear() {
        return academicYear;
    }

    /**
     * Returns the value of the "active" attribute.
     * 
     * @return The value of the "active" attribute.
     */
    public boolean getActive() {
        return active;
    }

    /**
     * Returns the value of the "allowCategoryPreferences" attribute.
     * 
     * @return The value of the "allowCategoryPreferences" attribute.
     */
    public boolean getAllowCategoryPreferences() {
        return allowCategoryPreferences;
    }

    /**
     * Returns the value of the "allowJobPostingsPreferences" attribute.
     * 
     * @return The value of the "allowJobPostingsPreferences" attribute.
     */
    public boolean getAllowJobPostingsPreferences() {
        return allowJobPostingsPreferences;
    }

    /**
     * Returns the value of the "allowLocationPreferences" attribute.
     * 
     * @return The value of the "allowLocationPreferences" attribute.
     */
    public boolean getAllowLocationPreferences() {
        return allowLocationPreferences;
    }

    /**
     * Returns the value of the "endDate" attribute.
     * 
     * @return The value of the "endDate" attribute.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Returns the value of the "gradePolicy" attribute.
     * 
     * @return The value of the "gradePolicy" attribute.
     */
    public String getGradePolicy() {
        return gradePolicy;
    }

    /**
     * Returns the value of the "hasGroupGrade" attribute.
     * 
     * @return The value of the "hasGroupGrade" attribute.
     */
    public boolean getHasGroupGrade() {
        return hasGroupGrade;
    }

    /**
     * Returns the value of the "id" attribute.
     * 
     * @return The value of the "id" attribute.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the value of the "inRegistration" attribute.
     * 
     * @return The value of the "inRegistration" attribute.
     */
    public boolean getInRegistration() {
        return inRegistration;
    }

    /**
     * Returns the value of the "isGroupCoOp" attribute.
     * 
     * @return The value of the "isGroupCoOp" attribute.
     */
    public boolean getIsGroupCoOp() {
        return isGroupCoOp;
    }

    /**
     * Returns the value of the "isInsideUniversity" attribute.
     * 
     * @return The value of the "isInsideUniversity" attribute.
     */
    public boolean getIsInsideUniversity() {
        return isInsideUniversity;
    }

    /**
     * Returns the value of the "jobDurationDays" attribute.
     * 
     * @return The value of the "jobDurationDays" attribute.
     */
    public int getJobDurationDays() {
        return jobDurationDays;
    }

    /**
     * Returns the value of the "maxGroupSize" attribute.
     * 
     * @return The value of the "maxGroupSize" attribute.
     */
    public int getMaxGroupSize() {
        return maxGroupSize;
    }

    /**
     * Returns the value of the "paymentOrderAmount" attribute.
     * 
     * @return The value of the "paymentOrderAmount" attribute.
     */
    public double getPaymentOrderAmount() {
        return paymentOrderAmount;
    }

    /**
     * Returns the value of the "paymentOrderDate" attribute.
     * 
     * @return The value of the "paymentOrderDate" attribute.
     */
    public Date getPaymentOrderDate() {
        return paymentOrderDate;
    }

    /**
     * Returns the value of the "semester" attribute.
     * 
     * @return The value of the "semester" attribute.
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Returns the value of the "setup" attribute.
     * 
     * @return The value of the "setup" attribute.
     */
    public boolean getSetup() {
        return setup;
    }

    /**
     * Returns the value of the "startDate" attribute.
     * 
     * @return The value of the "startDate" attribute.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Returns the value of the "supportingInvitations" attribute.
     * 
     * @return The value of the "supportingInvitations" attribute.
     */
    public boolean getSupportingInvitations() {
        return supportingInvitations;
    }

    /**
     * Returns the value of the "tracking" attribute.
     * 
     * @return The value of the "tracking" attribute.
     */
    public Tracking getTracking() {
        return tracking;
    }

    /**
     * Returns the value of the "academicDirector" attribute.
     * 
     * @return The value of the "academicDirector" attribute.
     */
    public Professor getAcademicDirector() {
        return academicDirector;
    }

    /**
     * Returns the value of the "authenticatedUsers" attribute.
     * 
     * @return The value of the "authenticatedUsers" attribute.
     */
    public List<AuthenticatedUser> getAuthenticatedUsers() {
        return authenticatedUsers;
    }

    /**
     * Returns the value of the "companies" attribute.
     * 
     * @return The value of the "companies" attribute.
     */
    public List<Company> getCompanies() {
        return companies;
    }

    /**
     * Returns the value of the "financialSources" attribute.
     * 
     * @return The value of the "financialSources" attribute.
     */
    public List<FinancialSource> getFinancialSources() {
        return financialSources;
    }

    /**
     * Returns the value of the "groups" attribute.
     * 
     * @return The value of the "groups" attribute.
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Returns the value of the "institutionalDirector" attribute.
     * 
     * @return The value of the "institutionalDirector" attribute.
     */
    public Professor getInstitutionalDirector() {
        return institutionalDirector;
    }

    /**
     * Returns the value of the "insuranceContracts" attribute.
     * 
     * @return The value of the "insuranceContracts" attribute.
     */
    public List<InsuranceContract> getInsuranceContracts() {
        return insuranceContracts;
    }

    /**
     * Returns the value of the "jobPostings" attribute.
     * 
     * @return The value of the "jobPostings" attribute.
     */
    public List<JobPosting> getJobPostings() {
        return jobPostings;
    }

    /**
     * Returns the value of the "lesson" attribute.
     * 
     * @return The value of the "lesson" attribute.
     */
    public Lesson getLesson() {
        return lesson;
    }

    /**
     * Returns the value of the "name" attribute.
     * 
     * @return The value of the "name" attribute.
     */
    public Multilingual getName() {
        return name;
    }

    /**
     * Returns the value of the "registrations" attribute.
     * 
     * @return The value of the "registrations" attribute.
     */
    public List<Registration> getRegistrations() {
        return registrations;
    }

    /**
     * Returns the value of the "reports" attribute.
     * 
     * @return The value of the "reports" attribute.
     */
    public List<Report> getReports() {
        return reports;
    }

    /**
     * Returns the value of the "requirements" attribute.
     * 
     * @return The value of the "requirements" attribute.
     */
    public List<Requirement> getRequirements() {
        return requirements;
    }

    /**
     * Returns the value of the "scientificDirector" attribute.
     * 
     * @return The value of the "scientificDirector" attribute.
     */
    public Professor getScientificDirector() {
        return scientificDirector;
    }

    /**
     * Returns the value of the "supervisingProfessors" attribute.
     * 
     * @return The value of the "supervisingProfessors" attribute.
     */
    public List<Professor> getSupervisingProfessors() {
        return supervisingProfessors;
    }

    /**
     * Sets the value of the "academicYear" attribute.
     * 
     * @param academicYear
     *            The value of the "academicYear" attribute.
     */
    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    /**
     * Sets the value of the "active" attribute.
     * 
     * @param active
     *            The value of the "active" attribute.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Sets the value of the "allowCategoryPreferences" attribute.
     * 
     * @param allowCategoryPreferences
     *            The value of the "allowCategoryPreferences" attribute.
     */
    public void setAllowCategoryPreferences(boolean allowCategoryPreferences) {
        this.allowCategoryPreferences = allowCategoryPreferences;
    }

    /**
     * Sets the value of the "allowJobPostingsPreferences" attribute.
     * 
     * @param allowJobPostingsPreferences
     *            The value of the "allowJobPostingsPreferences" attribute.
     */
    public void setAllowJobPostingsPreferences(
            boolean allowJobPostingsPreferences) {
        this.allowJobPostingsPreferences = allowJobPostingsPreferences;
    }

    /**
     * Sets the value of the "allowLocationPreferences" attribute.
     * 
     * @param allowLocationPreferences
     *            The value of the "allowLocationPreferences" attribute.
     */
    public void setAllowLocationPreferences(boolean allowLocationPreferences) {
        this.allowLocationPreferences = allowLocationPreferences;
    }

    /**
     * Sets the value of the "endDate" attribute.
     * 
     * @param endDate
     *            The value of the "endDate" attribute.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets the value of the "gradePolicy" attribute.
     * 
     * @param gradePolicy
     *            The value of the "gradePolicy" attribute.
     */
    public void setGradePolicy(String gradePolicy) {
        this.gradePolicy = gradePolicy;
    }

    /**
     * Sets the value of the "hasGroupGrade" attribute.
     * 
     * @param hasGroupGrade
     *            The value of the "hasGroupGrade" attribute.
     */
    public void setHasGroupGrade(boolean hasGroupGrade) {
        this.hasGroupGrade = hasGroupGrade;
    }

    /**
     * Sets the value of the "id" attribute.
     * 
     * @param id
     *            The value of the "id" attribute.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the value of the "inRegistration" attribute.
     * 
     * @param inRegistration
     *            The value of the "inRegistration" attribute.
     */
    public void setInRegistration(boolean inRegistration) {
        this.inRegistration = inRegistration;
    }

    /**
     * Sets the value of the "isGroupCoOp" attribute.
     * 
     * @param isGroupCoOp
     *            The value of the "isGroupCoOp" attribute.
     */
    public void setIsGroupCoOp(boolean isGroupCoOp) {
        this.isGroupCoOp = isGroupCoOp;
    }

    /**
     * Sets the value of the "isInsideUniversity" attribute.
     * 
     * @param isInsideUniversity
     *            The value of the "isInsideUniversity" attribute.
     */
    public void setIsInsideUniversity(boolean isInsideUniversity) {
        this.isInsideUniversity = isInsideUniversity;
    }

    /**
     * Sets the value of the "jobDurationDays" attribute.
     * 
     * @param jobDurationDays
     *            The value of the "jobDurationDays" attribute.
     */
    public void setJobDurationDays(int jobDurationDays) {
        this.jobDurationDays = jobDurationDays;
    }

    /**
     * Sets the value of the "maxGroupSize" attribute.
     * 
     * @param maxGroupSize
     *            The value of the "maxGroupSize" attribute.
     */
    public void setMaxGroupSize(int maxGroupSize) {
        this.maxGroupSize = maxGroupSize;
    }

    /**
     * Sets the value of the "paymentOrderAmount" attribute.
     * 
     * @param paymentOrderAmount
     *            The value of the "paymentOrderAmount" attribute.
     */
    public void setPaymentOrderAmount(double paymentOrderAmount) {
        this.paymentOrderAmount = paymentOrderAmount;
    }

    /**
     * Sets the value of the "paymentOrderDate" attribute.
     * 
     * @param paymentOrderDate
     *            The value of the "paymentOrderDate" attribute.
     */
    public void setPaymentOrderDate(Date paymentOrderDate) {
        this.paymentOrderDate = paymentOrderDate;
    }

    /**
     * Sets the value of the "semester" attribute.
     * 
     * @param semester
     *            The value of the "semester" attribute.
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * Sets the value of the "setup" attribute.
     * 
     * @param setup
     *            The value of the "setup" attribute.
     */
    public void setSetup(boolean setup) {
        this.setup = setup;
    }

    /**
     * Sets the value of the "startDate" attribute.
     * 
     * @param startDate
     *            The value of the "startDate" attribute.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the value of the "supportingInvitations" attribute.
     * 
     * @param supportingInvitations
     *            The value of the "supportingInvitations" attribute.
     */
    public void setSupportingInvitations(boolean supportingInvitations) {
        this.supportingInvitations = supportingInvitations;
    }

    /**
     * Sets the value of the "tracking" attribute.
     * 
     * @param tracking
     *            The value of the "tracking" attribute.
     */
    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }

    /**
     * Sets the value of the "academicDirector" attribute.
     * 
     * @param academicDirector
     *            " The value of the "academicDirector" attribute.
     */
    public void setAcademicDirector(Professor academicDirector) {
        this.academicDirector = academicDirector;
    }

    /**
     * Sets the value of the "authenticatedUsers" attribute.
     * 
     * @param authenticatedUsers
     *            " The value of the "authenticatedUsers" attribute.
     */
    public void setAuthenticatedUsers(List<AuthenticatedUser> authenticatedUsers) {
        this.authenticatedUsers = authenticatedUsers;
    }

    /**
     * Sets the value of the "companies" attribute.
     * 
     * @param companies
     *            " The value of the "companies" attribute.
     */
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    /**
     * Sets the value of the "financialSources" attribute.
     * 
     * @param financialSources
     *            " The value of the "financialSources" attribute.
     */
    public void setFinancialSources(List<FinancialSource> financialSources) {
        this.financialSources = financialSources;
    }

    /**
     * Sets the value of the "groups" attribute.
     * 
     * @param groups
     *            " The value of the "groups" attribute.
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    /**
     * Sets the value of the "institutionalDirector" attribute.
     * 
     * @param institutionalDirector
     *            " The value of the "institutionalDirector" attribute.
     */
    public void setInstitutionalDirector(Professor institutionalDirector) {
        this.institutionalDirector = institutionalDirector;
    }

    /**
     * Sets the value of the "insuranceContracts" attribute.
     * 
     * @param insuranceContracts
     *            " The value of the "insuranceContracts" attribute.
     */
    public void setInsuranceContracts(List<InsuranceContract> insuranceContracts) {
        this.insuranceContracts = insuranceContracts;
    }

    /**
     * Sets the value of the "jobPostings" attribute.
     * 
     * @param jobPostings
     *            " The value of the "jobPostings" attribute.
     */
    public void setJobPostings(List<JobPosting> jobPostings) {
        this.jobPostings = jobPostings;
    }

    /**
     * Sets the value of the "lesson" attribute.
     * 
     * @param lesson
     *            " The value of the "lesson" attribute.
     */
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    /**
     * Sets the value of the "name" attribute.
     * 
     * @param name
     *            " The value of the "name" attribute.
     */
    public void setName(Multilingual name) {
        this.name = name;
    }

    /**
     * Sets the value of the "registrations" attribute.
     * 
     * @param registrations
     *            " The value of the "registrations" attribute.
     */
    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    /**
     * Sets the value of the "reports" attribute.
     * 
     * @param reports
     *            " The value of the "reports" attribute.
     */
    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    /**
     * Sets the value of the "requirements" attribute.
     * 
     * @param requirements
     *            " The value of the "requirements" attribute.
     */
    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    /**
     * Sets the value of the "scientificDirector" attribute.
     * 
     * @param scientificDirector
     *            " The value of the "scientificDirector" attribute.
     */
    public void setScientificDirector(Professor scientificDirector) {
        this.scientificDirector = scientificDirector;
    }

    /**
     * Sets the value of the "supervisingProfessors" attribute.
     * 
     * @param supervisingProfessors
     *            " The value of the "supervisingProfessors" attribute.
     */
    public void setSupervisingProfessors(List<Professor> supervisingProfessors) {
        this.supervisingProfessors = supervisingProfessors;
    }

}