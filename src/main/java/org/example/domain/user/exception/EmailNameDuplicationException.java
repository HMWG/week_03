package org.example.domain.user.exception;

public class EmailNameDuplicationException extends RuntimeException{
    public EmailNameDuplicationException() {
        super("이름이나 이메일이 중복됩니다.");
    }
}
