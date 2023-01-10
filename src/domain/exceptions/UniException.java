package domain.exceptions;

import java.util.ArrayList;
import java.util.List;

public class UniException extends Exception{
    private List<EnrollmentRulesViolationException> exceptionList;

    public UniException() {
        super("uni exception");
        exceptionList = new ArrayList<>();
    }

    public void addException(EnrollmentRulesViolationException exception) {
        exceptionList.add(exception);
    }

    public List<EnrollmentRulesViolationException> getAllException() {
        return this.exceptionList;
    }
}
