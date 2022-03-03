package com.employeesdata.employees;


import com.employeesdata.employees.exception.BusinessException;
import com.employeesdata.employees.model.Employees;
import com.employeesdata.employees.repo.Emprepo;
import com.employeesdata.employees.service.EmployeeService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.mockito.Mockito.*;


class EmployeesApplicationTests{

    @Mock
    Emprepo employeerepo;

    @InjectMocks
    EmployeeService empservice;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Before("start")
    public void testOne() throws Exception {
        when(employeerepo.findAll()).thenReturn(Stream.of(
                new Employees("vamsi","vamsi@mail.com",9876543234L,65262)
        ).collect(Collectors.toList()));
        //get all employees Test
        Assertions.assertEquals(1,empservice.getEmp().size());

    }

    @Test
    public void saveEmployee() throws Exception {
        Employees emp = Employees.builder()
                .name("nani")
                .email("nai@mail.com")
                .mobile(4874787893L)
                .salary(73847L)
                .build();
        //save employee
        when(employeerepo.save(emp)).thenReturn(emp);
        Assertions.assertEquals(emp,empservice.saveEmployee(emp));

    }
    @Test
    public void deleteEmployee(){
        Employees emp = Employees.builder()
                .name("nani")
                .email("nai@mail.com")
                .mobile(4874787893L)
                .salary(73847L)
                .build();
        empservice.deleteRecord(emp);
        verify(employeerepo,times(1)).delete(emp);
    }

    @Test
    @DisplayName("Name shouldn't be null")
    public void throwExceptionName(){
        Assertions.assertThrows(BusinessException.class,()->{
            Employees ename = Employees.builder()
                    .name("")
                    .email("vamsinatsure@gmail.com")
                    .mobile(9287834779L)
                    .salary(782892L)
                    .build();
            empservice.saveEmployee(ename);



        });

    }
    @Test
    @DisplayName("Salary shouldn't be null")
    public void throwExceptionSalary() {
        Assertions.assertThrows(BusinessException.class, () -> {
            Employees ename = Employees.builder()
                    .name("vamsi")
                    .email("vamsinatsure@gmail.com")
                    .mobile(9287834779L)
                    .salary(null)
                    .build();
            empservice.saveEmployee(ename);

        });
    }
    @Test
    @DisplayName("Email shouldn't be null")
    public void throwExceptionEmail() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Employees ename = Employees.builder()
                    .name("vamsi")
                    .email(null)
                    .mobile(9287834779L)
                    .salary(36273L)
                    .build();
            empservice.saveEmployee(ename);

        });
    }
    @ParameterizedTest
    @DisplayName("Checking InValid Mobile Nums")
    @ValueSource(strings = {"82789","6237828"})
    public void mobileCheck(String mobilenum) {
        Assertions.assertThrows(BusinessException.class,() ->{
            Employees emp = Employees.builder()
                    .name("vamsi")
                    .email("vamsi@mail.com")
                    .salary(8209L)
                    .mobile(Long.parseLong(mobilenum))
                    .build();
            empservice.saveEmployee(emp);
        });

    }
    @ParameterizedTest
    @DisplayName("Checking InValid Mobile Nums 2")
    @ValueSource(strings = {"u2bhd6570","gw7272w728","dbj878289"})
    public void mobileCheckTwo(String mobilenum) {
        Assertions.assertThrows(NumberFormatException.class,() ->{
            Employees emp = Employees.builder()
                    .name("vamsi")
                    .email("vamsi@mail.com")
                    .salary(8209L)
                    .mobile(Long.parseLong(mobilenum))
                    .build();
            empservice.saveEmployee(emp);
        });

    }
    @ParameterizedTest
    @DisplayName("Checking InValid Email Address")
    @MethodSource("emailList")
    public void emailCheck(String email) {
        Assertions.assertThrows(BusinessException.class,() ->{
            Employees emp = Employees.builder()
                    .name("vamsi")
                    .email(email)
                    .salary(8209L)
                    .mobile(4367277728L)
                    .build();
            empservice.saveEmployee(emp);
        });

    }

    private static List<String> emailList(){
        return Arrays.asList("#@%^%#$@#$@#.com",
                "@example.com",
                "Joe Smith <email@example.com>",
                "email.example.com",
                "email@example@example.com",
                ".email@example.com");
    }


}