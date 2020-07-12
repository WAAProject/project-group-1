package miu.edu.cs545waa.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason ="Image should be valid" )
public class ImageNotValidException extends RuntimeException  {
    public String getInvlaidMsg(){
        return "Only images are allowed!!";
    }
}
