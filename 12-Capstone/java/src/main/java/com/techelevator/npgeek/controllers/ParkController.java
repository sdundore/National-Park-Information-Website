package com.techelevator.npgeek.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.models.Park;
import com.techelevator.npgeek.models.ParkDAO;
import com.techelevator.npgeek.models.Survey;
import com.techelevator.npgeek.models.SurveyDAO;
import com.techelevator.npgeek.models.SurveyResult;
import com.techelevator.npgeek.models.SurveyResultDAO;
import com.techelevator.npgeek.models.Weather;
import com.techelevator.npgeek.models.WeatherDAO;





@Controller
public class ParkController {

	
	@Autowired
	private ParkDAO parkDao;
	@Autowired
	private WeatherDAO weatherDao;
	@Autowired
	private SurveyDAO surveyDao;
	@Autowired
	private SurveyResultDAO surveyResultDao;
	

	@RequestMapping({"/", "/home"})
	public String displayHomePage(ModelMap map) {
		List<Park> parks = parkDao.getAllParks();
		
		map.addAttribute("parks", parks);
		return "home";
		}
	
	@RequestMapping (path = "/survey", method = RequestMethod.GET)
	public String displaySurveyPage(ModelMap map) {
		
		if (!map.containsAttribute("survey")) {
			map.put("survey", new Survey());
		}
		List<Park> parks = parkDao.getAllParks();
		
		map.addAttribute("parks", parks);
		return "survey";
	}
	
	@RequestMapping (path = "/survey", method = RequestMethod.POST)
	public String submitSurveyResults(@Valid @ModelAttribute Survey survey, BindingResult result, RedirectAttributes flash) {
		
		flash.addFlashAttribute("survey", survey);

		if (result.hasErrors()) {
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
			return "redirect:/survey";
		}
		
		surveyDao.commitSurveyToDatabase(survey);
		
		return "redirect:/surveyConfirmation";
	}
	
	@RequestMapping (path = "/surveyConfirmation", method = RequestMethod.GET)
	public String displaySurveyConfirmation(ModelMap map) {
		
		List<SurveyResult> result = surveyResultDao.getSurveyResults();
		
		map.addAttribute("result", result);
		
		return "surveyConfirmation";
	
	}
	
	@RequestMapping(path="/detail", method=RequestMethod.GET)
	public String displayParkDetails(HttpServletRequest request, ModelMap map) {
		
		String parkId = (request.getParameter("id"));
		Park thePark = parkDao.getParkById(parkId);
		List<Weather> weather = weatherDao.getWeatherReport(parkId);
		map.addAttribute("weather", weather);
		

		request.setAttribute("park", thePark);
		
		
		return "detail";
	}
	
	@RequestMapping(path = "/detail", method=RequestMethod.POST)
	public String displayPark(HttpServletRequest request, ModelMap map) {
		
		String temp = (request.getParameter("temp"));
		String parkId = (request.getParameter("id"));
		Park thePark = parkDao.getParkById(parkId);
		List<Weather> weather = weatherDao.getWeatherReport(parkId);
		map.addAttribute("weather", weather);
		map.addAttribute("temp", temp);
		

		request.setAttribute("park", thePark);
		
		return "detail";
	}
}
