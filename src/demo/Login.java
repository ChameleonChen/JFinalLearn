package demo;

import java.util.List;

import com.jfinal.core.Controller;

public class Login extends Controller {
	
	public void index(){
		renderJsp("login.html");
	}

	public void login(){
		String userName = getPara("user_name");
		String password = getPara("password");
		
		List<User> users = User.dao.find("select * from user where name='"+userName+"'");
		if(users.isEmpty()){
			renderText("there is no "+userName);
			return;
		}
		
		renderText("hello "+userName);
		return ;
	}
	
	public void addUser(){
		
	}
}
