package com.registration.registration.Service;

import com.registration.registration.Entity.Registration;
import com.registration.registration.Exceptions.ResourceNotFound;
import com.registration.registration.Payload.RegistrationDto;
import com.registration.registration.Repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    
    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Registration mapToEntity(RegistrationDto registrationDto){
//        Registration reg=new Registration();
//        reg.setId(registrationDto.getId());
//        reg.setName(registrationDto.getName());
//        reg.setEmail(registrationDto.getEmail());
//        reg.setMobile(registrationDto.getMobile());
//
//        return reg;
        return modelMapper.map(registrationDto,Registration.class);

    }
    public RegistrationDto mapToDto(Registration reg){

        return modelMapper.map(reg,RegistrationDto.class);

    }

    
    public RegistrationDto createRegistration(RegistrationDto registrationDto){

//        Registration save = registrationRepository.save(registration);
        Registration reg = mapToEntity(registrationDto);
        Registration saveReg = registrationRepository.save(reg);
        return mapToDto(saveReg);

    }



    public List<RegistrationDto> getAllRegistration() {

        List<Registration> all = registrationRepository.findAll();
        List<RegistrationDto> dtos=all.stream().map(registration -> mapToDto(registration)).collect(Collectors.toList());
        return dtos;
    }


    public void updateRegistration(Long id, RegistrationDto dto) {
//        Registration reg=mapToEntity(dto);
        Optional<Registration> idi=registrationRepository.findById(id);
        if(idi.isPresent()){
            Registration reg =idi.get();
            reg.setName(dto.getName());
            reg.setEmail(dto.getEmail());
            reg.setMobile(dto.getMobile());
            Registration save = registrationRepository.save(reg);

        }
    }

    public void deleteRecord(long id) {
        registrationRepository.deleteById(id);
    }

    public List<RegistrationDto> getRegistrationPage(int page, int size, String sortBy, String dir) {

//      for sorting direction
        Sort sort=dir.equals("asc")?Sort.by(Sort.Order.asc(sortBy)):Sort.by(Sort.Order.desc(sortBy));


//        PageRequest page1= PageRequest.of(page, size );
//        PageRequest page1= PageRequest.of(page, size , Sort.by(sortBy)); // for only ascending order
        Pageable page1= PageRequest.of(page, size ,sort); //for sorting direction
        Page<Registration> all = registrationRepository.findAll(page1);

//        to return List<RegistrationDto>
        List<Registration> reg= all.getContent();
        return reg.stream().map(r->mapToDto(r)).toList();

//        to Return only page<Registration>
//        return all.map(this::mapToDto);

    }

    public RegistrationDto getById(long ids) {
        return registrationRepository.findById(ids)
                .map(this::mapToDto)
                .orElseThrow(
                ()->new ResourceNotFound("Cannot Find Registration Details")
        );
    }
}
