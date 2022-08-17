package br.com.xmetrocubo.services;

import br.com.xmetrocubo.data.vo.v1.PersonVO;
import br.com.xmetrocubo.data.vo.v2.PersonVOV2;
import br.com.xmetrocubo.exceptions.ResourceNotFoundException;
import br.com.xmetrocubo.mapper.DozerMapper;
import br.com.xmetrocubo.mapper.custom.PersonMapper;
import br.com.xmetrocubo.model.Person;
import br.com.xmetrocubo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper personMapper;

    public PersonVO findById(Long id){
        logger.info("Finding one PersonVO");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll(){
        logger.info("Finding all people");
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating one PersonVO");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("Creating one PersonVO");
        var entity = personMapper.convertVoToEntity(person);
        var vo =  personMapper.convertEntityToVo(repository.save(entity));
        return vo;
    }

    public PersonVO update(PersonVO PersonVO) {
        logger.info("Updating one PersonVO");
        var entity = repository.findById(PersonVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setFirstName(PersonVO.getFirstName());
        entity.setLastName(PersonVO.getLastName());
        entity.setAddress(PersonVO.getAddress());
        entity.setGender(PersonVO.getGender());
        var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one PersonVO");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

}
