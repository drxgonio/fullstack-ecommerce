package com.example.ecm.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealCheckController {
  @GetMapping("check")
  public ResponseEntity<String> getCheck(){
    return ResponseEntity.ok("Check successfully");
  }
}
