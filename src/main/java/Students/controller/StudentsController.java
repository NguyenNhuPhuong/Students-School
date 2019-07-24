package Students.controller;


import Students.model.School;
import Students.model.Students;
import Students.service.ServiceSchool;
import Students.service.ServiceStudents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class StudentsController {
    @Autowired
    private ServiceStudents serviceStudents;

    @Autowired
    private ServiceSchool serviceSchool;

    @ModelAttribute("school")
    public Iterable<School> schools(){
        return serviceSchool.findAll();
    }

    @GetMapping("/students")
    public ModelAndView list(@RequestParam("s")Optional<String> s, Pageable pageable){

        Page<Students> students;
        if(s.isPresent()){
            students = serviceStudents.findAllByFirstNameContaining(s.get(),pageable);
        }else{
            students = serviceStudents.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("students/list");
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/create-student")
    public ModelAndView showForm(){
        ModelAndView modelAndView = new ModelAndView("students/create");
        modelAndView.addObject("student", new Students());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute ("student") Students students){
        serviceStudents.save(students);

        ModelAndView modelAndView = new ModelAndView("students/create");
        modelAndView.addObject("student", new Students());
        modelAndView.addObject("message", "Save Successfully");
        return modelAndView;
    }

    @GetMapping("/edit-student/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        Students student = serviceStudents.findById(id);
        if(student != null){
            ModelAndView modelAndView = new ModelAndView("students/edit");
            modelAndView.addObject("student",student);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("students/error");
            return modelAndView;
        }
    }
    @PostMapping("/edit-student")
    public ModelAndView Update(@ModelAttribute ("student") Students students){
        serviceStudents.save(students);
        ModelAndView modelAndView = new ModelAndView("students/edit");
        modelAndView.addObject("students",students);
        modelAndView.addObject("message","Update successfully");
        return modelAndView;
    }
    @GetMapping("/delete-student/{id}")
    public ModelAndView Delete(@PathVariable Long id){
        Students student = serviceStudents.findById(id);
        if(student != null){
            ModelAndView modelAndView = new ModelAndView("students/delete");
            modelAndView.addObject("student", student);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("students/error");
            return modelAndView;
        }
    }
    @PostMapping("/delete-student")
    public String delete(@ModelAttribute ("student") Students students){
        serviceStudents.remove(students.getId());
        return "redirect:students";
    }
}
