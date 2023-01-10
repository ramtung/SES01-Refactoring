package domain;

import java.sql.Date;

public class Term {
    private String name;
    private Date startDate;

    public Term(String name) {
        this.name = name;
        this.startDate = null;
    }

    public Term(String name, Date startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Term term = (Term) o;

        if (!name.equals(term.name)) return false;
        return startDate.equals(term.startDate);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + startDate.hashCode();
        return result;
    }
}
