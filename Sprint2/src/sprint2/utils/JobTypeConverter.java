package sprint2.utils;

import javafx.util.StringConverter;

public class JobTypeConverter extends StringConverter<JobType> {
    @Override 
    public String toString(JobType job) {
        switch(job) {
            case Cook:
                return "Cook";
            case Manager:
                return "Manager";
            case Waiter:
                return "Waiter";
            default:
                return "Null";
        }
    }

    @Override 
    public JobType fromString(String string) {
        return JobType.valueOf(string);
    }
}
