package Students.controller;

import Students.model.School;
import Students.service.ServiceSchool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;

@Controller
public class SchoolController {

    @Autowired
    private ServiceSchool serviceSchool;

    @GetMapping("/schools")
    public ModelAndView list (){
        Iterable<School> schools = serviceSchool.findAll();

        ModelAndView modelAndView =  new ModelAndView("school/list");
        modelAndView.addObject("schools", schools);
        return modelAndView;
    }

    @GetMapping("/create-school")
    public ModelAndView showForm(){
        ModelAndView modelAndView = new ModelAndView("school/create");
        modelAndView.addObject("school", new School());
        return modelAndView;

    }

    @PostMapping("/create-school")
    public ModelAndView create(@Validated @ModelAttribute ("school") School school, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("school/create");
            return modelAndView;
        }
        serviceSchool.save(school);
        ModelAndView modelAndView = new ModelAndView("school/create");
        modelAndView.addObject("message","Successfully");
        modelAndView.addObject("school", new School());
        return modelAndView;
    }

    @GetMapping("/edit-school/{id}")
    public ModelAndView show(@PathVariable Long id){
        School school = serviceSchool.findById(id);
        if(school != null){
            ModelAndView modelAndView = new ModelAndView("school/edit");
            modelAndView.addObject("school", school);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("students/error");
            return modelAndView;
        }
    }
    @PostMapping("/edit-school")
    public ModelAndView Update(@Validated @ModelAttribute ("school") School school, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("school/edit");
            return modelAndView;
        }
        serviceSchool.save(school);
        ModelAndView modelAndView = new ModelAndView("school/edit");
        modelAndView.addObject("school",school);
        modelAndView.addObject("message", "successfully");
        return modelAndView;
    }

    @GetMapping("/delete-school/{id}")
    public ModelAndView Delete(@PathVariable Long id){
        School school = serviceSchool.findById(id);
        if(school != null){
            ModelAndView modelAndView = new ModelAndView("school/delete");
            modelAndView.addObject("school",school);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("students/error");
            return modelAndView;
        }
    }

    @PostMapping("/delete-school")
    public String delete(@ModelAttribute("school") School school){
        serviceSchool.remove(school.getId());
        return "redirect:schools";
    }
}
