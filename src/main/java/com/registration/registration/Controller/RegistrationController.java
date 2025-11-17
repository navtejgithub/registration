package com.registration.registration.Controller;

import com.registration.registration.Entity.Registration;
import com.registration.registration.Payload.RegistrationDto;
import com.registration.registration.Service.RegistrationService;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<?> createRegistration(
            @Valid @RequestBody RegistrationDto registrationDto,
            BindingResult result

    ){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        RegistrationDto Dto = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>("Record Created/n"+Dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistration(){

        return new ResponseEntity<>(registrationService.getAllRegistration(),HttpStatus.ACCEPTED) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReg(
            @PathVariable Long id,
            @RequestBody RegistrationDto dto

    ){

        registrationService.updateRegistration(id,dto);
        return new ResponseEntity<>("Registration updated with ID:"+id,HttpStatus.FOUND) ;

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRecord(@RequestParam long id){

        registrationService.deleteRecord(id);
        return new ResponseEntity<>("Record ID:'"+id+"'is Deleted Successfully",HttpStatus.ACCEPTED);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getRegPage(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(required = false,defaultValue = "3") int size,
            @RequestParam(name = "sortBy",required = false,defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "asc",required = false) String dir
    )
    {
//        List<RegistrationDto> registrationDtoList=registrationService.getRegistrationPage(page,size);
//        return new ResponseEntity<>(registrationDtoList,HttpStatus.ACCEPTED);
        return new ResponseEntity<>(registrationService.getRegistrationPage(page,size,sortBy,dir),HttpStatus.FOUND);
    }

    @GetMapping("/{ids}")
    public ResponseEntity<?> getById(@PathVariable long ids){
        return new ResponseEntity<>(registrationService.getById(ids),HttpStatus.FOUND);

    }


}
