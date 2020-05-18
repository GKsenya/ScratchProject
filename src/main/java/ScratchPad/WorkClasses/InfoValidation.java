package ScratchPad.WorkClasses;

import javax.servlet.http.HttpServletRequest;

public class InfoValidation {
    private String login = null;
    private String name = null;
    private String password = null;
    private int exceptionCounter = 0;
    private HttpServletRequest request;
    private String task = null;
    private String time = null;

    public InfoValidation(HttpServletRequest request) {
        this.request = request;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getTask() {
        return task;
    }

    public String getTime() {
        return time;
    }

    public int isValid(){

        if(!request.getParameter("login").equalsIgnoreCase("")){
            login = request.getParameter("login");
        }else{
            request.setAttribute("loginMes", "Заполните поле \"Логин\".");
            exceptionCounter ++;
        }
        if(!request.getParameter("name").equalsIgnoreCase("")){
            name = request.getParameter("name");
        }else{
            request.setAttribute("nameMes", "Заполните поле \"Имя\".");
            exceptionCounter ++;
        }
        if(!request.getParameter("password").equalsIgnoreCase("")){
            password = request.getParameter("password");
        }else{
            request.setAttribute("passwordMes", "Заполните поле \"Пароль\".");
            exceptionCounter ++;
        }
        return exceptionCounter;

    }

    public int isValidTask(){

        if(!request.getParameter("task").equalsIgnoreCase("")){
            task = request.getParameter("task");
        }else{
            request.setAttribute("taskMes", "Заполните поле \"Описание\".");
            exceptionCounter ++;
        }
        try {
            if (!request.getParameter("time").equalsIgnoreCase("")) {
                time = request.getParameter("time");
            } else {
                request.setAttribute("timeMes", "Заполните поле \"время\".");
                exceptionCounter++;
            }
        } catch (NullPointerException e){
            time = null;
        }
        return exceptionCounter;
    }
}
