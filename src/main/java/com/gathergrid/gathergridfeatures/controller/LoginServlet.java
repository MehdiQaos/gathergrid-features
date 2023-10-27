package com.gathergrid.gathergridfeatures.controller;

import com.gathergrid.gathergridfeatures.domain.User;
import com.gathergrid.gathergridfeatures.repository.interfacesImpl.UserRepository;
import com.gathergrid.gathergridfeatures.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginServlet extends pagesServlet {
    private UserService service ;

    @Override
    public void init() {
        UserRepository<User> userRepository = new UserRepository<>();
        service = new UserService(userRepository);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String path = request.getRequestURL().toString();

        if(!path.contains("signin") && !path.contains("Signup")){
            checkSessionNotEmpty(request);
        }else  resp.sendRedirect(request.getContextPath()+"/auth/login");

        if(path.contains("signin"))
            request.getRequestDispatcher("/login.jsp").forward(request, resp);
        else if(path.contains("ShowEvent")){
            request.getRequestDispatcher("/WEB-INF/view/showEvent.jsp").forward(request, resp);
        }
        else if(path.contains("Signup"))
        request.getRequestDispatcher("/registration.jsp").forward(request, resp);
        else if(path.contains("logout")) {
            HttpSession session = request.getSession(false);
            if(session != null)
                request.getSession().invalidate();
            resp.sendRedirect(request.getContextPath()+"/auth/login");
        }
        else request.getRequestDispatcher("/WEB-INF/view/404.jsp").forward(request, resp);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURL().toString();
        if(path.contains("signin")){
            login(request,response);
        }
        else if(path.contains("Signup")){
            register(request,response);
        } else request.getRequestDispatcher("/WEB-INF/view/404.jsp").forward(request, response);
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");

        List<String> resultValidation = registreValidation(firstName, lastName, email, password);
        if (!resultValidation.isEmpty()) {
            request.setAttribute("validationErrors", resultValidation);
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        } else {
            String message = "";
            String hashed = hashPassword(password);
            Optional<User> save = service.save(new User(firstName, lastName, email, hashed));
            if (save.isPresent()) {
                message = "User successfully registered.";
                request.setAttribute("registrationMessage", message);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                message = "Email already exists. Please use a different email.";
                request.setAttribute("registrationMessage", message);
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
            }


        }
    }

    public void login (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        List<String> resultValidation = loginValidation(email, password);
        User user = new User();

        user.setEmail(email);
        user.setPassword(password);

        if(resultValidation.isEmpty()) {
            Optional<User> user1 = service.CheckEmail(user);

            if (user1.isPresent()) {
                request.getSession(true).setAttribute("user", user1.get());
                request.setAttribute("success", "You are logged in successfully");
                response.sendRedirect(request.getContextPath()+"/Dashboard");
            } else {
                request.setAttribute("validationEmail", "Email Or Password Not exists.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
        else {
            request.setAttribute("validation", "Entre your Email and Password !!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    public  List<String> loginValidation(String email , String password){

        List<String> validationErrors = new ArrayList<>();

        if (password == null || password.isBlank()) {
            validationErrors.add("Password is required.");
        }

        if (email != null && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            validationErrors.add("Invalid email format.");
        }

        return validationErrors;
    }
    public static  List<String> registreValidation(String firstName ,String lastName , String email , String password){

        List<String> validationErrors = new ArrayList<>();

        if (firstName == null || firstName.isBlank()) {
            validationErrors.add("First name is required. ");
        }

        if (lastName == null || lastName.isBlank()) {
            validationErrors.add("Last name is required.");
        }

        if (email == null || email.isBlank()) {
            validationErrors.add("Email is required.");
        }

        if (password == null || password.isBlank()) {
            validationErrors.add("Password is required.");
        }

        if (email != null && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            validationErrors.add("Invalid email format.");
        }

        return validationErrors;
    }

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    public boolean logintest (String email, String password) {

        List<String> resultValidation = loginValidation(email, password);
        User user = new User();

        user.setEmail(email);
        user.setPassword(password);

        if(resultValidation.isEmpty()) {
            Optional<User> user1 = service.CheckEmail(user);

            if (user1.isPresent()) {
               return true;
            }
        }
        return false;
    }
}
