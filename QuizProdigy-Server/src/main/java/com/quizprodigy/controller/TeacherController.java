package com.quizprodigy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TeacherController {

    // Through addshop API we can delete salons in the system
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/set-exam-question")
	@Secured("shopkeeper")
	public ResponseEntity<?> deleteShop(@RequestBody String shopId) {

		return null;
	}

}
