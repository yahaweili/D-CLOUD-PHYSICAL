package com.ynding.cloud.person.service;

import com.ynding.cloud.common.model.bo.Query;
import com.ynding.cloud.common.model.vo.PersonVO;
import com.ynding.cloud.person.data.PersonRepository;
import com.ynding.cloud.person.entity.Person;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

/**
 * @author ynding
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(PersonVO personVO) {
        Person person = new Person();
        BeanUtils.copyProperties(personVO, person);
        return personRepository.save(person);
    }

    public List<PersonVO> findList(Query query) {
        List<Person> persons = personRepository.findAll(condition(query));
        List<PersonVO> personVOS = new ArrayList<>();
        persons.forEach(e -> {
            PersonVO personVO = new PersonVO();
            BeanUtils.copyProperties(e, personVO);
            personVOS.add(personVO);
        });
        return personVOS;
    }

    /**
     * 查询条件
     *
     * @param query
     * @return
     */
    private Specification<Person> condition(Query query) {

        return (root, cq, cb) -> {
            Predicate predicate = cb.conjunction();
            if (query.get("sex") != null) {
                predicate.getExpressions().add(cb.equal(root.get("sex"), query.get("sex")));
            }
            if (query.get("name") != null) {
                predicate.getExpressions().add(cb.like(root.get("name"), "%" + query.get("name") + "%"));
            }
            return predicate;
        };
    }


}
