package com.play.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Date;

import com.play.dao.PlayDAO;
import com.play.entity.Comments;
import com.play.entity.Players;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlayController {
	public int score;
	//static boolean timeUp = false;
	
	@RequestMapping("/getUsername.htm")
	public ModelAndView getUsername(HttpServletRequest request, HttpServletResponse response){
		System.out.println("Inside getUsername request mapping");
		if(request == null)
			throw new NullPointerException();
		//ModelAndView modelAndView = new ModelAndView();
		PlayController playController = new PlayController();
		
		HttpSession session=request.getSession();
		//session.setAttribute("timeUp", false);
		session.setAttribute("playController", playController);
		String username = request.getParameter("username");
		
		//boolean userAlreadyExists = PlayDAO.userAlreadyExists(username);
		
		session.setAttribute("username", username);
		ModelAndView mv = new ModelAndView();
		//mv = playController(request,response);
		mv.setViewName("play");
		return mv;
		
	}
	
	@RequestMapping("/playAgain.htm")
	public ModelAndView playItAgain(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		PlayController playController = (PlayController)session.getAttribute("playController");
		playController.score = 0;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("play");
		return mv;
		
	}
	
	@RequestMapping("/hallOfFame.htm")
	public ModelAndView hallOfFame(HttpServletRequest request, HttpServletResponse response){
		System.out.println("Inside hall of fame");
		ModelAndView mv = new ModelAndView();
		
		HttpSession session=request.getSession();
		
		
		List<Comments> commentList = PlayDAO.getComments();
		session.setAttribute("commentList", commentList);
		
		String username = (String)session.getAttribute("username");
		PlayController playController = (PlayController)session.getAttribute("playController");
		//session.invalidate();
		Players player = new Players();
		player.setUsername(username);
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy   HH:mm:ss");
		Date date = new Date();
		player.setDate(dateFormat.format(date));
		
		player.setScore(playController.score);
		
	    boolean userInsertionSuccessful = PlayDAO.insertUserInDatabase(player);
	    
	    if(userInsertionSuccessful){
	    	List<Players> playerList = PlayDAO.getPlayerList();
			session.setAttribute("playerList", playerList);
			System.out.println("user insertion successfull : PlayController.java");
			mv.setViewName("hallOfFame");
	    }else{
	    	System.out.println("user insertion unsuccessfull : PlayController.java");
	    }
	    System.out.println("Inside hall of fame java controller");
	    return mv;
	}
	
	
	
	@RequestMapping("/link.htm")
	public void linkClicked(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//int scored = Integer.parseInt(request.getParameter("name"));
		HttpSession session=request.getSession();
		PlayController playController = (PlayController)session.getAttribute("playController");
		String Strscored = (String)request.getParameter("name");
		System.out.println(Strscored);
		int scored = Integer.parseInt(Strscored);
		playController.score += scored*10;
		

		PrintWriter pw = response.getWriter(); 
        pw.print(playController.score);
        System.out.println(playController.score);
        pw.close();
	}
	
	@RequestMapping("/comment.htm")
	public void getComment(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//int scored = Integer.parseInt(request.getParameter("name"));
		HttpSession session=request.getSession();
		String username = (String)session.getAttribute("username");
		String comment = (String)request.getParameter("comment");
		
		System.out.println(username+" inside comment");
		System.out.println(comment+" inside comment");
		
		int rows = PlayDAO.addComment(username,comment);
		if(rows == 0){
			System.out.println("Comment was not added to the database");
		}
		
		
		PrintWriter pw = response.getWriter(); 
        pw.print(username);
        pw.close();
	}
	
	
	
	@RequestMapping("/playServlet.htm")
	public void playController(HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println(" inside playController");
		ModelAndView modelAndView = new ModelAndView();
		if(request == null)
			throw new NullPointerException();
		
		String[] colorArray = {"red","blue","green","yellow","orange","black","pink","brown","grey"}; 
		int[] colorsUsedInOptions = PlayDAO.getIndexOfOptions(colorArray.length);
		int matchingColor = PlayDAO.matchingColor(colorsUsedInOptions);
		int positionOfMatchingColor = PlayDAO.getPositionOfMatchingColor();
		
		//creating a set of colors that will be available in options
		Set<Integer> setOfColorsUsedInOptions = new HashSet<Integer>();
		for(int i=0;i<4;i++ )
			setOfColorsUsedInOptions.add(colorsUsedInOptions[i]);

		JSONObject dataSet = new JSONObject();
		for(int i=0;i<4;i++){
			if(i == positionOfMatchingColor){
				
				try {
					dataSet.put("colorName"+i, colorArray[matchingColor]) ;
					dataSet.put("colorOfText"+i, colorArray[matchingColor]);
					dataSet.put("name"+i, "1");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			
			int randomNumberFrom0To8 = 0;
			randomNumberFrom0To8 =   new Random().nextInt(9);
			while(randomNumberFrom0To8 == colorsUsedInOptions[i]){
				randomNumberFrom0To8 =  new Random().nextInt(9);
			}

			try {
				dataSet.put("colorName"+i, colorArray[colorsUsedInOptions[i]]);
				dataSet.put("colorOfText"+i, colorArray[randomNumberFrom0To8]);
				dataSet.put("name"+i, "-1");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		System.out.println(dataSet.toString());
		//modelAndView.setViewName("play");
		PrintWriter pw = response.getWriter(); 
        pw.print(dataSet.toString());
        pw.close();
	}
	
	
}
