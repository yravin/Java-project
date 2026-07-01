package com.learnapi.test.controller;
import com.learnapi.test.dto.MemberRequest;
import com.learnapi.test.dto.MemberResponse;
import com.learnapi.test.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    //----------------post member----------------------------------------------
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody MemberRequest memberRequest){
        log.info("Create book with request: {}", memberRequest);
        memberService.create(memberRequest);
        return ResponseEntity.ok().build();

    }
    //-------------------update data for member-----------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody MemberRequest memberRequest) {
        log.info("Update todo id: {} with request: {}", id, memberRequest);
        memberService.update(id, memberRequest);
        return ResponseEntity.ok().build();
    }

    //------------------------------Delete data  for member-----------------------
    @DeleteMapping("/{id}")
    public  String delete(@PathVariable Long id){
        log.info("Delete book with member {} :" ,id);
        memberService.delete(id);
        return "Delete successful";
    }
    @GetMapping("/{id}")
    public Object getBookById(@PathVariable Long id) {
        log.info("Get todo by id: {}", id);
        MemberResponse member = memberService.getById(id);
        return ResponseEntity.ok(member);
    }
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllBooks() {
        log.info("Get all book");
        List<MemberResponse> books = memberService.getAll();
        return ResponseEntity.ok(books);
    }

}
