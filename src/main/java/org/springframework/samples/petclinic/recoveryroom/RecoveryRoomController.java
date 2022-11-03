package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecoveryRoomController {
	
	RecoveryRoomService recoveryRoomService;
	
	@Autowired
    public RecoveryRoomController(RecoveryRoomService recoveryRoomService) {
        this.recoveryRoomService = recoveryRoomService;
	}
	
	@ModelAttribute("types")
    public List<RecoveryRoomType> populateRecoveryRoomTypes() {
        return this.recoveryRoomService.getAllRecoveryRoomTypes();
    }
	
	@GetMapping("/recoveryroom/create")
    public ModelAndView createRecoveryRoom() {
        ModelAndView mav = new ModelAndView("recoveryroom/createOrUpdateRecoveryRoomForm");
        mav.addObject("recoveryRoom", new RecoveryRoom());
        return mav;
    }
	
	@PostMapping(value = "/recoveryroom/create")
	public ModelAndView processCreationForm(@Valid RecoveryRoom recoveryRoom, BindingResult result) {
		ModelAndView mav;
		if (result.hasErrors()) {
			mav=new ModelAndView("recoveryroom/createOrUpdateRecoveryRoomForm");
			mav.addObject("recoveryRoom", recoveryRoom);
            mav.addObject("types", recoveryRoomService.getAllRecoveryRoomTypes());
		}
		else {
			try {
				this.recoveryRoomService.save(recoveryRoom);
			} catch (DuplicatedRoomNameException e) {
				e.printStackTrace();
			}
			mav = new ModelAndView("welcome");
		}
		return mav;
	}
    
}
