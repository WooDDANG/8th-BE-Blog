package com.leets_8th_be.kanghyeonwoo.controller;

import com.leets_8th_be.kanghyeonwoo.dto.RepeatStringRequest;
import com.leets_8th_be.kanghyeonwoo.dto.RepeatStringResponse;
import com.leets_8th_be.kanghyeonwoo.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping("/health")
    public String healthCheck() {
        return assignmentService.getHealthStatus();
    }

    @PostMapping("/string/repeat")
    public RepeatStringResponse repeatString(@RequestBody RepeatStringRequest request) {
        return assignmentService.repeatString(request);
    }
}
