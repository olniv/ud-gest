package fr.fo.ud.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.fo.ud.business.api.IBusinessBranche;
import fr.fo.ud.business.api.IBusinessEntreprise;
import fr.fo.ud.business.api.IBusinessEvent;
import fr.fo.ud.business.api.IBusinessFederation;
import fr.fo.ud.business.api.IBusinessSection;
import fr.fo.ud.business.api.IBusinessSyndicat;
import fr.fo.ud.business.api.IBusinessUd;
import fr.fo.ud.entity.Event;

@Controller
public class IndexController {
	
	private static final int ID__FORM_BRANCHE = 1;
	
	private static final int ID__FORM_ENTREPRISE = 2;
	
	private static final int ID_FORM_FEDERATION = 3;
	
	private static final int ID_FORM_SECTION= 4;
	
	private static final int ID_FORM_SYNDICAT = 5;
	
	private static final int ID_FORM_UD = 6;
	
	
	@Autowired
	private IBusinessEvent buEvent;
	
	@Autowired
	private IBusinessBranche buBranche;
	
	@Autowired
	private IBusinessEntreprise buEntreprise;
	
	@Autowired
	private IBusinessFederation buFederation;
	
	@Autowired
	private IBusinessSection buSection;
	
	@Autowired
	private IBusinessSyndicat buSyndicat;
	
	@Autowired
	private IBusinessUd buUd;
	
	@RequestMapping(value="/ud-gest/show-index", method=RequestMethod.GET)
	public String showIndex(Model model) {
		model.addAttribute("branches", buBranche.getAllLibelles());
		model.addAttribute("entreprises", buEntreprise.getAllLibelles());
		model.addAttribute("federations", buFederation.getAllLibelles());
		model.addAttribute("sections", buSection.getAllLibelles());
		model.addAttribute("syndicats", buSyndicat.getAllLibelles());
		model.addAttribute("uds", buUd.getAllLibelles());
		return "index";
	}
	
	@RequestMapping(value="/ud-gest/fill-calendar", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Event> fillCalendar() {
		List<Event> events = buEvent.getAll();
		return events;
	}
	
	@RequestMapping(value="/ud-gest/add-event", method=RequestMethod.POST)
	public String addEvent(@RequestParam(name="title") String title,
						   @RequestParam(name="start") @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
						   @RequestParam(name="end") @DateTimeFormat(pattern="yyyy-MM-dd") Date end,
						   @RequestParam(name="description") String description,
						   @RequestParam(name="organisation") int organisation,
						   @RequestParam(name="branche", required=false) String branche,
						   @RequestParam(name="entreprise", required=false) String entreprise,
						   @RequestParam(name="federation", required=false) String federation,
						   @RequestParam(name="section", required=false) String section,
						   @RequestParam(name="syndicat", required=false) String syndicat,
						   @RequestParam(name="ud", required=false) String ud) {
		
		Event event = new Event();
		event.setTitle(title);
		event.setStart(start);
		event.setEnd(end);
		event.setDescription(description);
		
		if (organisation == ID__FORM_BRANCHE) {
			System.out.println("Dans le if de la branche");
			event.setBranche(buBranche.getByLibelle(branche));
		}
		if (organisation == ID__FORM_ENTREPRISE) {
			System.out.println("Dans le if de l'entreprise");
			System.out.println(buEntreprise.getByLibelle(entreprise).toString());
			event.setEntreprise(buEntreprise.getByLibelle(entreprise));
		}
		if (organisation == ID_FORM_FEDERATION) {
			System.out.println("Dans le if de la federation");
			event.setFederation(buFederation.getByLibelle(federation));
		}
		if (organisation == ID_FORM_SECTION) {
			System.out.println("Dans le if de la section");
			event.setSection(buSection.getByLibelle(section));
		}
		if (organisation == ID_FORM_SYNDICAT) {
			System.out.println("Dans le if du syndicat");
			event.setSyndicat(buSyndicat.getByLibelle(syndicat));
		}
		if (organisation == ID_FORM_UD) {
			System.out.println("Dans le if de l'union départementale");
			event.setUd(buUd.getByLibelle(ud));
		}
		buEvent.add(event);
		return "redirect:/ud-gest/show-index";
	}
	
}
