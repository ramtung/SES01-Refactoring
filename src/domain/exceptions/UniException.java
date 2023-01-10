package domain.exceptions;

import java.util.ArrayList;
import java.util.List;

public class UniException extends Exception{
    private List<EnrollmentRulesViolationException> exceptionList;

    public UniException(String message) {
        super(message);
        exceptionList = new ArrayList<>();
    }

    public void addException(EnrollmentRulesViolationException exception) {
        exceptionList.add(exception);
    }

    public List<EnrollmentRulesViolationException> getAllException() {
        return this.exceptionList;
    }
}
