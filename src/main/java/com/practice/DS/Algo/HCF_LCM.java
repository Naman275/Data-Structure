package com.practice.DS.Algo;
import javax.validation.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableValidator;
import javax.validation.groups.Default;
import java.util.Set;
class HCF{
    public void check(@NotNull(message = "first name cannot be null") String ab, @NotNull  String cv){
        System.out.println("hi baby there");
    }
}
public class HCF_LCM {
    private  static Validator validator;

    public static void main(String [] args) throws NoSuchMethodException {
        //ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
       // Validator validator = factory.getValidator();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        HCF ob=new HCF();
        Class cl=ob.getClass();
        ExecutableValidator executableValidator = validator.forExecutables();
        Set<ConstraintViolation<Object>> violations = executableValidator.validateParameters(ob,cl.getMethod("check",String.class,String.class),
        new Object[]{null,null},Default.class
        );
        ob.check("",null);
    }

    public static int findHCF(@NotNull int firstNo, @NotNull int secondNo){
        return firstNo%secondNo==0?firstNo:findHCF(firstNo%secondNo,firstNo);
    }
    public static int findLCM(@NotNull  int firstNo,@NotNull  int secondNo){
        return (firstNo*secondNo)/findHCF(firstNo,secondNo);
    }
    public static void deleteMe(){
        System.out.println("hey abbay");
    }
}
