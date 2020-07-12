package miu.edu.cs545waa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,reason = "Already ordered product!!")
public class AlreadyOrderedProduct extends RuntimeException{
    public String getInvlaidMsg(){
        return "There is no longer remaining product! Already ordered!";
    }

}
