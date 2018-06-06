package com.tjube.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.model.Penalti;
import com.tjube.service.PenaltiService;

@Controller
@RequestMapping("/penalti")
@Configuration
@ComponentScan("com.tjube.service")
public class PenaltiController
{
	private static final Logger logger = Logger.getLogger(PenaltiController.class);

	public PenaltiController()
	{
		System.out.println("PenaltiController()");
	}

	@Autowired
	private PenaltiService penaltiService;

	@RequestMapping(value = "")
	public ModelAndView homePenalti(ModelAndView model)
		throws IOException
	{
		return new ModelAndView("redirect:penalti/");
	}

	@RequestMapping(value = "/")
	public ModelAndView listPenalti(ModelAndView model)
		throws IOException
	{
		List<Penalti> listPenalti = penaltiService.getAllPenaltis();
		model.addObject("listPenalti", listPenalti);
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/newPenalti", method = RequestMethod.GET)
	public ModelAndView newPenalti(ModelAndView model)
	{
		Penalti penalti = new Penalti();
		model.addObject("penalti", penalti);
		model.setViewName("PenaltiForm");
		return model;
	}

	@RequestMapping(value = "/savePenalti", method = RequestMethod.POST)
	public ModelAndView savePenalti(@ModelAttribute Penalti penalti)
	{
		if (penalti.getId() == 0)
		{ // if employee id is 0 then creating the
				// employee other updating the employee
			penaltiService.addPenalti(penalti);
		}
		else
		{
			penaltiService.updatePenalti(penalti);
		}
		return new ModelAndView("redirect:/penalti/");
	}

	@RequestMapping(value = "/deletePenalti", method = RequestMethod.GET)
	public ModelAndView deletePenalti(HttpServletRequest request)
	{
		int penaltiId = Integer.parseInt(request.getParameter("id"));
		penaltiService.deletePenalti(penaltiId);
		return new ModelAndView("redirect:/penalti/");
	}

	@RequestMapping(value = "/editPenalti", method = RequestMethod.GET)
	public ModelAndView editPenalti(HttpServletRequest request)
	{
		int penaltiId = Integer.parseInt(request.getParameter("id"));
		Penalti penalti = penaltiService.getPenalti(penaltiId);
		ModelAndView model = new ModelAndView("PenaltiForm");
		model.addObject("penalti", penalti);

		return model;
	}
}
