package Students.fomatter;

import Students.model.School;
import Students.service.ServiceSchool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class SchoolFomatter implements Formatter<School> {

    private ServiceSchool serviceSchool;

    @Autowired
    public SchoolFomatter (ServiceSchool serviceSchool){
        this.serviceSchool = serviceSchool;
    }

    @Override
    public School parse(String text, Locale locale) throws ParseException {
        return serviceSchool.findById(Long.parseLong(text));
    }

    @Override
    public String print(School object, Locale locale) {
        return object.toString();
    }
}
