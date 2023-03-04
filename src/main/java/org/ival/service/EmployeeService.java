package org.ival.service;

import io.quarkus.narayana.jta.runtime.TransactionConfiguration;
import org.ival.exception.ValidationException;
import org.ival.model.Employee;
import org.ival.model.JobPosition;
import org.ival.model.LastEducation;
import org.ival.model.User;
import org.ival.model.dto.EmployeeRequest;
import org.ival.model.dto.EmployeeResponse;
import org.ival.util.DateUtil;
import org.ival.util.FormatUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmployeeService {

    @Inject
    EntityManager entityManager;
    public Response post(EmployeeRequest request,String userId) throws ParseException {
        if (!FormatUtil.isAlphabet(request.fullName) || !FormatUtil.isPhoneNumber(request.mobilePhoneNumber) ||
        !FormatUtil.isEmail(request.email) || !FormatUtil.isGender(request.gender) ||
        !FormatUtil.isDateFormat(request.dob) || !FormatUtil.isAlphabet(request.pob)) {
            throw new ValidationException("BAD_REQUEST");
        }

        if (request.jobPositionId == null || request.jobPositionId.isBlank() || request.lastEducationId == null || request.lastEducationId.isBlank() ){
            throw new ValidationException("BAD_REQUEST_JOBPOSITION_LASTEDUCATION");
        }

        Optional<JobPosition> jobPositionOptional = JobPosition.findByIdOptional(request.jobPositionId);
        Optional<LastEducation> lastEducationOptional = LastEducation.findByIdOptional(request.lastEducationId);
        if (jobPositionOptional.isEmpty() || lastEducationOptional.isEmpty() ){
            throw new ValidationException("BAD_REQUEST_OPTIONAL_EMPTY");
        }

        User user = User.findById(userId);

        persistEmploye(request,jobPositionOptional.get(), lastEducationOptional.get(), user,null);

        return Response.ok(new HashMap<>()).build();
    }

    public Response list(Integer page, String jobPositionId){
        if (page < 1 ){
            throw new ValidationException("BAD_REQUEST");
        }

        Long count  = Employee.countByJobPosition(jobPositionId);
        Double totalPage = Math.ceil(count/10.0);
        StringBuilder sb = new StringBuilder("SELECT * FROM main.employee\t");

        Integer offset = ((page-1) * 10);
        if (jobPositionId != null){
            sb.append("WHERE job_position_id = :id");
        }

        Query query = entityManager.createNativeQuery(sb.toString(), Employee.class);

        if (jobPositionId != null){
            query.setParameter("id", jobPositionId);
        }

        query.setMaxResults(10);
        query.setFirstResult(offset);
        List<Employee> list = query.getResultList();

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.totalPage = totalPage.longValue();
        employeeResponse.dataList = list;

        return Response.ok(employeeResponse).build();
    }

    public Response detail(String employeId){
        Optional<Employee> employeeOptional = Employee.findByIdOptional(employeId);
        if (employeeOptional.isEmpty()){
            throw new ValidationException("EMPLOYEE_NOT_FOUND");
        }
        return Response.ok(employeeOptional.get()).build();
    }


    public Response put(EmployeeRequest request,String userId, String employeeId) throws ParseException {

        if (employeeId == null || employeeId.isBlank()){
            throw new ValidationException("BAD_REQUEST");
        }

        if (!FormatUtil.isAlphabet(request.fullName) || !FormatUtil.isPhoneNumber(request.mobilePhoneNumber) ||
        !FormatUtil.isEmail(request.email) || !FormatUtil.isGender(request.gender) ||
        !FormatUtil.isDateFormat(request.dob) || !FormatUtil.isAlphabet(request.pob)) {
            throw new ValidationException("BAD_REQUEST");
        }

        if (request.jobPositionId == null || request.jobPositionId.isBlank() || request.lastEducationId == null || request.lastEducationId.isBlank() ){
            throw new ValidationException("BAD_REQUEST_JOBPOSITION_LASTEDUCATION");
        }

        Optional<JobPosition> jobPositionOptional = JobPosition.findByIdOptional(request.jobPositionId);
        Optional<LastEducation> lastEducationOptional = LastEducation.findByIdOptional(request.lastEducationId);
        if (jobPositionOptional.isEmpty() || lastEducationOptional.isEmpty() ){
            throw new ValidationException("BAD_REQUEST_OPTIONAL_EMPTY");
        }

        User user = User.findById(userId);

        persistEmploye(request,jobPositionOptional.get(), lastEducationOptional.get(), user, employeeId);

        return Response.ok(new HashMap<>()).build();
    }

    public Response delete(String userId, String employeeId) throws ParseException {
        User user = User.findById(userId);
        deleteEmployee(user,employeeId);
        return Response.ok(new HashMap<>()).build();
    }

    @Transactional
    @TransactionConfiguration(timeout = 30)
    public Employee persistEmploye(EmployeeRequest request, JobPosition jobPosition, LastEducation lastEducation, User user, String employeeId) throws ParseException {
        Employee employee;

        if (employeeId == null){
            employee = new Employee();
            employee.setActive(true);
            employee.setCreatedBy(user);
        } else {
            Optional<Employee> employeeOptional = Employee.findByIdOptional(employeeId);
            if (employeeOptional.isEmpty()){
                throw new ValidationException("EMPLOYEE_NOT_FOUND");
            }
            employee = employeeOptional.get();
        }

        employee.setFullName(request.fullName);
        employee.setEmail(request.email);
        employee.setPob(request.pob);
        employee.setDob(DateUtil.stringToDate(request.dob));
        employee.setGender(request.gender);
        employee.setJobPosition(jobPosition);
        employee.setLastEducation(lastEducation);
        employee.setMobilePhoneNumber(request.mobilePhoneNumber);
        employee.setUpdatedBy(user);

        Employee.persist(employee);

        return employee;

    }

    @Transactional
    @TransactionConfiguration(timeout = 30)
    public void deleteEmployee(User user, String employeeId) {
        Optional<Employee> employeeOptional = Employee.findByIdOptional(employeeId);
        if (employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            employee.setActive(false);
            employee.setUpdatedBy(user);

            Employee.persist(employee);
        }

    }
}
