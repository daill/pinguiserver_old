/*
 * Copyright 2019 Christian Kramer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files
 *  (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge,
 *  publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do
 *  so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.daill.pinguiserver.api;

import de.daill.pinguiserver.Greeting;
import de.daill.pinguiserver.model.Group;
import de.daill.pinguiserver.model.Institution;
import de.daill.pinguiserver.repos.InstitutionRepository;
import de.daill.pinguiserver.usecases.InstitutionUseCase;
import de.daill.pinguiserver.usecases.InstitutionUseCaseImpl;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



@RestController
public class InstitutionService {
    Logger logger = LoggerFactory.getLogger(InstitutionService.class);

    @Autowired
    private InstitutionUseCaseImpl institutionUseCase;

    @RequestMapping("/institutions/{searchText}")
    @ResponseBody
    public List<Institution> getInstitutionsByRegex(@PathVariable String searchText) {
        logger.debug("try retrieving institution by pattern: \"" + searchText + "\"");
        return institutionUseCase.findInstitutionsByRegex("/"+searchText+"/.test(this.plz)", ".*"+ searchText+ ".*");
    }

    @RequestMapping(value = "/institutions",method = RequestMethod.GET)
    public List<Institution> getAll() {
        return institutionUseCase.findAllInstitutions();
    }


    @RequestMapping(value = "/institution",method = RequestMethod.POST)
    public void addInstitution(@RequestParam(value="name", required=true) String name, @RequestParam(value="postal", required=true) int postal) {
        logger.debug("add institution");
        institutionUseCase.saveInstitution(new Institution(name, postal, new ArrayList<>()));
    }

    @RequestMapping(value = "/institution/{id}/group",method = RequestMethod.POST)
    public void addInstitutionGroup(@PathVariable("id") String institutionId, @RequestParam(value = "name", required = true) String groupName) {
        logger.debug("add group to institution: " + institutionId);
        institutionUseCase.findById(institutionId).ifPresentOrElse((institution -> institution.getGroups().add(new Group(groupName))), () -> {
            throw new ResourceNotFound();
        });
    }

    @RequestMapping("/addtest")
    public void addTestInstitution() {
        logger.debug("added test");
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group("Entengruppe"));
        groups.add(new Group("Tigergruppe"));
        groups.add(new Group("Eulengruppe"));
        institutionUseCase.saveInstitution(new Institution("test 1", 34590, groups));
    }
}
